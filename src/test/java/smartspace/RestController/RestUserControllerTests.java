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
import smartspace.layout.NewUserForm;
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
	public void testCreateNewUser() throws Exception{
		//GIVEN the database is clean
		
		//WHEN I create a new user
		NewUserForm form=new NewUserForm();
		form.setAvatar("dummy avatar");
		form.setEmail("someemail@gmail.com");
		form.setRole("PLAYER");
		form.setUsername("mydummyusername");
		UserBoundary response = this.restTemplate
				.postForObject(
						this.baseUrl+"users",
						form, 
						UserBoundary.class);
		
		// THEN the database contains 1 user
		// AND the returned user is similar to the user in the database
		
		assertThat(
				this.userDao.readAll())
			.hasSize(1)
			.usingElementComparatorOnFields("key")
			.containsExactly(response.convertToEntity());
		
		tearDown();
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
		newUser.setRole("PLAYER");
		newUser.setUsername("dummy user");
		users.add(newUser);
		
		UserBoundary[] response = this.restTemplate
			.postForObject(
					this.baseUrl+"admin/users/{adminSmartspace}/{adminEmail}",
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
	public void testLoginAndRetriveUserDetails() throws Exception{
		
		//GIVEN I have empty database
		
		
		
		
		//WHEN I create new user and he Logs in
		
		UserBoundary bound=new UserBoundary();
		UserBoundaryKey key=new UserBoundaryKey();
		key.setEmail("someemail@gmail.com");
		key.setSmartspace("inbala1");
		bound.setAvatar("some avatar");
		bound.setKey(key);
		bound.setRole("PLAYER");
		bound.setUsername("username");
		
		UserEntity user=bound.convertToEntity();
		this.userDao.create(user);
		
		UserBoundary response=this.restTemplate
					.getForObject(
						this.baseUrl+"users/login/{userSmartspace}/{userEmail}"
						, UserBoundary.class,bound.getKey().getSmartspace(),bound.getKey().getEmail());
		
		//THEN the user details are recived
		assertThat(this.userDao.readById(response.getKey().getSmartspace()
				+"!"+response.getKey().getEmail()).get().getUserEmail())
		.isEqualTo(user.getUserEmail());
		
		assertThat(this.userDao.readById(response.getKey().getSmartspace()
				+"!"+response.getKey().getEmail()).get().getUserSmatspace())
		.isEqualTo(user.getUserSmatspace());
		
		tearDown();
	}
	@Test
	public void testGetUsersUsingPagination() throws Exception{
		
		// GIVEN the database contains 38 messages
		int totalSize = 38;
		UserBoundary newUser= new UserBoundary();
		newUser.setAvatar("dummy avtar");
		UserBoundaryKey key=new UserBoundaryKey();
		key.setEmail("dummy mail");
		key.setSmartspace("dummy smartspace");
		newUser.setKey(key);
		newUser.setPoints(300);
		newUser.setRole("PLAYER");
		newUser.setUsername("dummy user");
		List<UserEntity> allEntities = 
		IntStream.range(1, totalSize + 1)
			.mapToObj(i->key.getSmartspace() + i)
			.map(user->new UserEntity(
					user, 
					newUser.getKey().getEmail(),
					newUser.getUsername(), 
					newUser.getAvatar(), 
					UserRole.valueOf(newUser.getRole()), 
					newUser.getPoints()))
			
			.collect(Collectors.toList());
		
		allEntities=this.userService.importUsers(allEntities);
		
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
	public void testGetUsersUsingPaginationWithNoResult() throws Exception{
		// GIVEN the database contains 38 messages
				int totalSize = 30;
				UserBoundary newUser= new UserBoundary();
				newUser.setAvatar("dummy avtar");
				UserBoundaryKey key=new UserBoundaryKey();
				key.setEmail("dummy mail");
				key.setSmartspace("dummy smartspace");
				newUser.setKey(key);
				newUser.setPoints(300);
				newUser.setRole("PLAYER");
				newUser.setUsername("dummy user");
				List<UserEntity> allEntities = 
				IntStream.range(1, totalSize + 1)
					.mapToObj(i->key.getSmartspace()+ i)
					.map(user->new UserEntity(
							user, 
							newUser.getKey().getEmail(),
							newUser.getUsername(), 
							newUser.getAvatar(), 
							UserRole.valueOf(newUser.getRole()), 
							newUser.getPoints()))
					
					.collect(Collectors.toList());
				
				allEntities=this.userService.importUsers(allEntities);
				
			
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
	
	@Test
	public void TestUpdateUserDetailsExceptHisPoints() throws Exception{
		
		//GIVEN empty database
		
		//WHEN I create a user and update his details
		UserBoundary bound=new UserBoundary();
		UserBoundaryKey key=new UserBoundaryKey();
		key.setEmail("someemail@gmail.com");
		key.setSmartspace("inbala1");
		bound.setAvatar("some avatar");
		bound.setKey(key);
		bound.setRole("PLAYER");
		bound.setUsername("username");
		
		UserEntity user=bound.convertToEntity();
		this.userDao.create(user);
		
		bound=new UserBoundary(user);
		
		bound.setAvatar("something elses");
		bound.setUsername("otherUserName");
		
		this.restTemplate
		.put(
			this.baseUrl+"users/login/{userSmartspace}/{userEmail}",
			bound,bound.getKey().getSmartspace(),bound.getKey().getEmail());
		//THEN the user is saved with his new details
		
		
		
		assertThat(this.userDao.readById(user.getKey()))
		.isPresent()
		.get()
		.extracting("avatar")
		.containsExactly(bound.getAvatar());
		
		assertThat(this.userDao.readById(user.getKey()))
		.isPresent()
		.get()
		.extracting("username")
		.containsExactly(bound.getUsername());
		
		tearDown();
		
	}

	
	
	
}
