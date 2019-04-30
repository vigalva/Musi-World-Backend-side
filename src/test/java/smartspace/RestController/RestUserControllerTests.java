package smartspace.RestController;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import smartspace.dao.AdvancedUserDao;
import smartspace.data.UserEntity;
import smartspace.data.UserRole;
import smartspace.layout.UserBoundary;
import smartspace.layout.UserBoundaryKey;
import smartspace.logic.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties= {"spring.profiles.active=default"})

public class RestUserControllerTests {
	
	private int port;
	private String baseUrl;
	private RestTemplate restTemplate;
	private AdvancedUserDao<String> userDao;
	private UserService userService;
	
	@Autowired
	public void setMessageDao(AdvancedUserDao<String> userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setMessageService(UserService userService) {
		this.userService = userService;
	}
	
	@LocalServerPort
	public void setPort(int port) {
		this.port = port;
	}
	
	@PostConstruct
	public void init(){
		this.baseUrl = "http://localhost:" + port + "/smartspace/";
		this.restTemplate = new RestTemplate();
	}

	@After
	public void tearDown() {
		this.userDao
			.deleteAll();
	}
	
	@Test
	public void testImportUsersToDataBase() throws Exception{
		// GIVEN the database is clean 
		String adminSmartspace="dummy smartspace";
		String adminEmail="dummy mail";
		// WHEN I import array of 1 UserBoundaries
		List<UserBoundary> users = new ArrayList<>();
		UserBoundary newUser= new UserBoundary();
		newUser.setAvatar("dummy avtar");
		UserBoundaryKey key=new UserBoundaryKey();
		key.setEmail("dummy mail");
		key.setSmartspace("dummy smartspace");
		newUser.setKey(key);
		newUser.setPoints(300);
		newUser.setRole(UserRole.PLAYER);
		newUser.setUsername("dummy user");
		users.add(newUser);
		
		UserBoundary[] response = this.restTemplate
			.postForObject(
					this.baseUrl+"admin/useres/{adminSmartspace}/{adminEmail}",
					users, 
					UserBoundary[].class,adminSmartspace, adminEmail);
	
		
		// THEN the database contains 1 message
		// AND the returned message is similar to the message in the database
		assertThat(
				this.userDao.readAll())
			.hasSize(1)
			.usingElementComparatorOnFields("key")
			.containsExactly(response[0].convertToEntity());
		
		tearDown();
	}

	@Test
	public void testGetMessagesUsingPagination() throws Exception{
		
		// GIVEN the database contains 38 messages
		int totalSize = 38;
		UserBoundary newUser= new UserBoundary();
		newUser.setAvatar("dummy avtar");
		UserBoundaryKey key=new UserBoundaryKey();
		key.setEmail("dummy mail");
		key.setSmartspace("dummy smartspace");
		newUser.setKey(key);
		newUser.setPoints(300);
		newUser.setRole(UserRole.PLAYER);
		newUser.setUsername("dummy user");
		List<UserEntity> allEntities = 
		IntStream.range(1, totalSize + 1)
			.mapToObj(i->key.getSmartspace() + i)
			.map(user->new UserEntity(
					user, 
					newUser.getKey().getEmail(),
					newUser.getUsername(), 
					newUser.getAvatar(), 
					newUser.getRole(), 
					newUser.getPoints()))
			
			.collect(Collectors.toList());
		List<UserBoundary> allBoundaris=new ArrayList<>();
		for (UserEntity e : allEntities) {
			allBoundaris.add(new UserBoundary(e));
		}
		UserBoundary[] all=allBoundaris.toArray(new UserBoundary[0]);
		allEntities=this.userService.importUsers(all);
		
		List<UserBoundary> lastEight = 
				allEntities
				.stream()
				.skip(30)
				.map(UserBoundary::new)
				.collect(Collectors.toList());
		
		// WHEN I getUsers using page #3 of size 10

		
		int size = 10;
		int page = 3;
		String adminSmartspace="dummy smartspace";
		String adminEmail="dummy mail";
		UserBoundary[] results = 
		  this.restTemplate
			.getForObject(
					this.baseUrl +"admin/users/{adminSmartspace}/{adminEmail}"+"?size={size}&page={page}", 
					UserBoundary[].class,adminSmartspace, adminEmail, 
					size, page);
		
		ArrayList<UserBoundary> arrayList = new ArrayList<UserBoundary>(Arrays.asList(results));
		System.err.println(arrayList);
		System.err.println(lastEight
				);
		// THEN the response contains 8 messages
		assertThat(results)
			.usingElementComparatorOnFields("username")
			.containsExactlyElementsOf(lastEight);
		
	}

	@Test
	public void testGetMessagesUsingPaginationWithNoResult() throws Exception{
		// GIVEN the database contains 38 messages
				int totalSize = 30;
				UserBoundary newUser= new UserBoundary();
				newUser.setAvatar("dummy avtar");
				UserBoundaryKey key=new UserBoundaryKey();
				key.setEmail("dummy mail");
				key.setSmartspace("dummy smartspace");
				newUser.setKey(key);
				newUser.setPoints(300);
				newUser.setRole(UserRole.PLAYER);
				newUser.setUsername("dummy user");
				List<UserEntity> allEntities = 
				IntStream.range(1, totalSize + 1)
					.mapToObj(i->key.getSmartspace()+ i)
					.map(user->new UserEntity(
							user, 
							newUser.getKey().getEmail(),
							newUser.getUsername(), 
							newUser.getAvatar(), 
							newUser.getRole(), 
							newUser.getPoints()))
					
					.collect(Collectors.toList());
				List<UserBoundary> allBoundaris=new ArrayList<>();
				for (UserEntity e : allEntities) {
					allBoundaris.add(new UserBoundary(e));
				}
				UserBoundary[] all=allBoundaris.toArray(new UserBoundary[0]);
				allEntities=this.userService.importUsers(all);
				
			
				// WHEN I getUsers using page #3 of size 10

				
				int size = 10;
				int page = 3;
				String adminSmartspace="dummy smartspace";
				String adminEmail="dummy mail";
				UserBoundary[] results = 
				  this.restTemplate
					.getForObject(
							this.baseUrl +"admin/users/{adminSmartspace}/{adminEmail}?page={page}&size={size}", 
							UserBoundary[].class,adminSmartspace, adminEmail, 
							size, page);
				
				
				
		
		// THEN the response contains no messages
		assertThat(results)
			.isEmpty();
	}

	
	
	
}
