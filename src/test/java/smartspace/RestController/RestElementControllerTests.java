package smartspace.RestController;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

import smartspace.dao.AdvancedElementDao;
import smartspace.data.ElementEntity;
import smartspace.data.Location;

import smartspace.layout.ElementBoundary;
import smartspace.layout.UserBoundary;
import smartspace.logic.ElementService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties= {"spring.profiles.active=default"})

public class RestElementControllerTests {
	
	private int port;
	private String baseUrl;
	private RestTemplate restTemplate;
	private AdvancedElementDao<String> elementDao;
	private ElementService elementService;
	
	@Autowired
	public void setMessageDao(AdvancedElementDao<String> elementDao) {
		this.elementDao = elementDao;
	}
	
	@Autowired
	public void setMessageService(ElementService elementService) {
		this.elementService = elementService;
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
		this.elementDao
			.deleteAll();
	}
	



	@Test
	public void testImportElementsToDataBase() throws Exception{
		// GIVEN the database is clean 
		String adminSmartspace="dummy smartspace";
		String adminEmail="dummy mail";
		// WHEN I import array of 1 UserBoundaries
		List<ElementBoundary> elements = new ArrayList<>();
		ElementBoundary newElement= new ElementBoundary();
		newElement.setName("dummy name");
		newElement.setType("dummy type");
		newElement.setCreatorSmartspace("test");
		newElement.setCreatorEmail("dummy creator email");
		newElement.setLocationY(1.2);
		newElement.setExpired(false);
		
		elements.add(newElement);
		
		ElementBoundary[] response = this.restTemplate
			.postForObject(
					this.baseUrl+"admin/useres/{adminSmartspace}/{adminEmail}",
					elements, 
					ElementBoundary[].class,adminSmartspace, adminEmail);
		
		// THEN the database contains 1 message
		// AND the returned message is similar to the message in the database
		assertThat(
				this.elementDao.readAll())
			.hasSize(1)
			.usingElementComparatorOnFields("key")
			.containsExactly(response[0].convertToEntity());
		
		tearDown();
	}

	

	@Test
	public void testGetMessagesUsingPagination() throws Exception{
		
		// GIVEN the database contains 38 messages
		int totalSize = 38;
		ElementBoundary newElement= new ElementBoundary();
		newElement.setName("dummy name");
		newElement.setType("dummy type");
		newElement.setCreatorSmartspace("test");
		newElement.setCreatorEmail("dummy creator email");
		newElement.setLocationY(1.2);
		newElement.setLocationX(1.2);
		Location l  = new Location();
		
		newElement.setExpired(false);
		
		List<ElementEntity> allEntities = 
				IntStream.range(1, totalSize + 1)
					.mapToObj(i->newElement.getKey()+ i)
					.map(element->new ElementEntity(
							newElement.getName(), 	
							newElement.getType(), 
							l,
							newElement.getCreationTimestamp(),
							newElement.getCreatorEmail(),
							newElement.getElementSmartspace(),
							newElement.isExpired(),
							new HashMap<>()))
		
					.collect(Collectors.toList());
		
		List<ElementBoundary> allBoundaris=new ArrayList<>();
		for (ElementEntity e : allEntities) {
			allBoundaris.add(new ElementBoundary(e));
		}
		ElementBoundary[] all=allBoundaris.toArray(new ElementBoundary[0]);
		allEntities=this.elementService.importElements(all);
		
		List<ElementBoundary> lastEight = 
				allEntities
				.stream()
				.skip(30)
				.map(ElementBoundary::new)
				.collect(Collectors.toList());
		
		// WHEN I getUsers using page #3 of size 10

		
		int size = 10;
		int page = 3;
		String adminSmartspace="dummy smartspace";
		String adminEmail="dummy mail";
		ElementBoundary[] results = 
		  this.restTemplate
			.getForObject(
					this.baseUrl +"admin/users/{adminSmartspace}/{adminEmail}"+"?size={size}&page={page}", 
					ElementBoundary[].class,adminSmartspace, adminEmail, 
					size, page);
		
		ArrayList<ElementBoundary> arrayList = new ArrayList<ElementBoundary>(Arrays.asList(results));
		System.err.println(arrayList);
		System.err.println(lastEight
				);
		// THEN the response contains 8 messages
		assertThat(results)
			.usingElementComparatorOnFields("username")
			.containsExactlyElementsOf(lastEight);
		
	}

	@Test
	public void testGetMessagesUsingPaginationWithoutNoResult() throws Exception{
		
		// GIVEN the database contains 38 messages
		int totalSize = 38;
		ElementBoundary newElement= new ElementBoundary();
		newElement.setName("dummy name");
		newElement.setType("dummy type");
		newElement.setCreatorSmartspace("test");
		newElement.setCreatorEmail("dummy creator email");
		newElement.setLocationY(1.2);
		newElement.setLocationX(1.2);
		Location l  = new Location();
		
		newElement.setExpired(false);
		
		List<ElementEntity> allEntities = 
				IntStream.range(1, totalSize + 1)
					.mapToObj(i->newElement.getKey()+ i)
					.map(element->new ElementEntity(
							newElement.getName(), 	
							newElement.getType(), 
							l,
							newElement.getCreationTimestamp(),
							newElement.getCreatorEmail(),
							newElement.getElementSmartspace(),
							newElement.isExpired(),
							new HashMap<>()))
		
					.collect(Collectors.toList());
		
		List<ElementBoundary> allBoundaris=new ArrayList<>();
		for (ElementEntity e : allEntities) {
			allBoundaris.add(new ElementBoundary(e));
		}
		ElementBoundary[] all=allBoundaris.toArray(new ElementBoundary[0]);
		allEntities=this.elementService.importElements(all);

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
