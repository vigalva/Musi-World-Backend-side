package smartspace.RestController;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import smartspace.layout.ElementBoundaryKey;
import smartspace.layout.LatLng;
import smartspace.layout.UserBoundaryKey;
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
	public void testCreateNewElementToDataBase() throws Exception{
		//Given the database is clean
		String managerSmartspace="dummy smartspace";
		String managerEmail="dummy mail";
		UserBoundaryKey dummyCreator=new UserBoundaryKey();
		
		dummyCreator.setEmail("dummy mail");
		dummyCreator.setSmartspace("some smartspace");
		
		ElementBoundaryKey dummyKey=new ElementBoundaryKey();
		
		dummyKey.setId("some id");
		dummyKey.setSmartspace("some smartspace");
		
		LatLng dummlocation=new LatLng();
		dummlocation.setLan(100);
		dummlocation.setLat(200);
		//when i create new element and read it
		ElementBoundary newElement= new ElementBoundary();
		newElement.setKey(dummyKey);
		newElement.setName("dummy name");
		newElement.setElementType("dummy type");
		newElement.setCreator(dummyCreator);
		newElement.setLatlng(dummlocation);
		newElement.setCreated(new Date());
		newElement.setExpired(false);
		newElement.setElementProperties(new HashMap<>());
		
		ElementBoundary response = this.restTemplate
				.postForObject(
						this.baseUrl+"/elements/{managerSmartspace}/{managerEmail}",
						newElement, 
						ElementBoundary.class,managerSmartspace, managerEmail);
		// THEN the database contains 1 message
				// AND the returned element is similar to the elemnt in the database
				assertThat(
						this.elementDao.readAll())
					.hasSize(1)
				.usingElementComparatorOnFields("key")
				.containsExactly(response.convertToEntity());
				
				tearDown();
	}
	@Test
	public void testImportElementsToDataBase() throws Exception{
		// GIVEN the database is clean 
		String adminSmartspace="dummy smartspace";
		String adminEmail="dummy mail";
		UserBoundaryKey dummyCreator=new UserBoundaryKey();
		
		dummyCreator.setEmail("dummy mail");
		dummyCreator.setSmartspace("some smartspace");
		
		ElementBoundaryKey dummyKey=new ElementBoundaryKey();
		
		dummyKey.setId("some id");
		dummyKey.setSmartspace("some smartspace");
		
		LatLng dummlocation=new LatLng();
		dummlocation.setLan(100);
		dummlocation.setLat(200);
		// WHEN I import array of 1 ElementBoundaries
		List<ElementBoundary> elements = new ArrayList<>();
		
		ElementBoundary newElement= new ElementBoundary();
		newElement.setKey(dummyKey);
		newElement.setName("dummy name");
		newElement.setElementType("dummy type");
		newElement.setCreator(dummyCreator);
		newElement.setLatlng(dummlocation);
		newElement.setCreated(new Date());
		newElement.setExpired(false);
		newElement.setElementProperties(new HashMap<>());
		
		elements.add(newElement);

		
		ElementBoundary[] response = this.restTemplate
			.postForObject(
					this.baseUrl+"admin/elements/{adminSmartspace}/{adminEmail}",
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
	public void testGetElementsUsingPagination() throws Exception{
		
		// GIVEN the database contains 38 messages
		int totalSize = 38;
		ElementBoundary newElement= new ElementBoundary();
		UserBoundaryKey dummyCreator=new UserBoundaryKey();
		
		dummyCreator.setEmail("dummy mail");
		dummyCreator.setSmartspace("some smartspace");
		
		ElementBoundaryKey dummyKey=new ElementBoundaryKey();
		
		dummyKey.setId("some id");
		dummyKey.setSmartspace("some smartspace");
		
		LatLng dummlocation=new LatLng();
		dummlocation.setLan(100);
		dummlocation.setLat(200);
		// WHEN I import array of 38 ElementBoundaries
		
		
		
		newElement.setKey(dummyKey);
		newElement.setName("dummy name");
		newElement.setElementType("dummy type");
		newElement.setCreator(dummyCreator);
		newElement.setLatlng(dummlocation);
		newElement.setCreated(new Date());
		newElement.setExpired(false);
		newElement.setElementProperties(new HashMap<>());
		
		List<ElementEntity> allEntities = 
				IntStream.range(1, totalSize + 1)
					.mapToObj(i->newElement.getKey().getSmartspace()+ i)
					.map(elem->new ElementEntity(
							newElement.getName(), 
							newElement.getElementType(), 
							new Location(newElement.getLatlng().getLng(),newElement.getLatlng().getLat()),
							newElement.getCreated(), 
							newElement.getCreator().getEmail(),
							newElement.getCreator().getSmartspace(), 
							newElement.isExpired(), 
							newElement.getElementProperties()))
		
					.collect(Collectors.toList());
				
		for (int i=0;i<allEntities.size();i++) {
			allEntities.get(i).setElementSmartspace(dummyKey.getSmartspace());
			allEntities.get(i).setElementId(dummyKey.getId());

		}
		allEntities=this.elementService.importElements(allEntities);
		

		
		List<ElementBoundary> lastEight = 
				allEntities
				.stream()
				.skip(30)
				.map(ElementBoundary::new)
				.collect(Collectors.toList());
		
		// WHEN I getElements using page #3 of size 10 i get 8 messages

		
		int size = 10;
		int page = 3;
		String adminSmartspace="dummy smartspace";
		String adminEmail="dummy mail";
		ElementBoundary[] results = 
		  this.restTemplate
			.getForObject(
					this.baseUrl +"admin/elements/{adminSmartspace}/{adminEmail}"+"?size={size}&page={page}", 
					ElementBoundary[].class,adminSmartspace, adminEmail, 
					size, page);
		
		ArrayList<ElementBoundary> arrayList = new ArrayList<ElementBoundary>(Arrays.asList(results));	
		assertThat(results)
			.usingElementComparatorOnFields("name")
			.containsExactlyElementsOf(lastEight);
		
	

	}
	@Test
	public void testGetMessagesUsingPaginationWithoutNoResult() throws Exception{
		
		// GIVEN the database contains 38 messages
		
		// GIVEN the database contains 38 messages
		int totalSize = 30;
		ElementBoundary newElement= new ElementBoundary();
		UserBoundaryKey dummyCreator=new UserBoundaryKey();
		
		dummyCreator.setEmail("dummy mail");
		dummyCreator.setSmartspace("some smartspace");
		
		ElementBoundaryKey dummyKey=new ElementBoundaryKey();
		
		dummyKey.setId("some id");
		dummyKey.setSmartspace("some smartspace");
		
		LatLng dummlocation=new LatLng();
		dummlocation.setLan(100);
		dummlocation.setLat(200);
		// WHEN I import array of 30 ElementBoundaries
		
		newElement.setKey(dummyKey);
		newElement.setName("dummy name");
		newElement.setElementType("dummy type");
		newElement.setCreator(dummyCreator);
		newElement.setLatlng(dummlocation);
		newElement.setCreated(new Date());
		newElement.setExpired(false);
		newElement.setElementProperties(new HashMap<>());
		
		List<ElementEntity> allEntities = 
				IntStream.range(1, totalSize + 1)
					.mapToObj(i->newElement.getKey().getSmartspace()+ i)
					.map(elem->new ElementEntity(
							newElement.getName(), 
							newElement.getElementType(), 
							new Location(newElement.getLatlng().getLng(),newElement.getLatlng().getLat()),
							newElement.getCreated(), 
							newElement.getCreator().getEmail(),
							newElement.getCreator().getSmartspace(), 
							newElement.isExpired(), 
							newElement.getElementProperties()))
		
					.collect(Collectors.toList());
				
		
		for (int i=0;i<allEntities.size();i++) {
			allEntities.get(i).setElementSmartspace(dummyKey.getSmartspace());
			allEntities.get(i).setElementId(dummyKey.getId());

		}
		allEntities=this.elementService.importElements(allEntities);
		

		
		List<ElementBoundary> lastEight = 
				allEntities
				.stream()
				.skip(30)
				.map(ElementBoundary::new)
				.collect(Collectors.toList());
		
		// WHEN I getElements using page #3 of size 10 nothing happens

		
		int size = 10;
		int page = 3;
		String adminSmartspace="dummy smartspace";
		String adminEmail="dummy mail";
		ElementBoundary[] results = 
		  this.restTemplate
			.getForObject(
					this.baseUrl +"admin/elements/{adminSmartspace}/{adminEmail}"+"?size={size}&page={page}", 
					ElementBoundary[].class,adminSmartspace, adminEmail, 
					size, page);
		
		assertThat(results)
			.usingElementComparatorOnFields("name")
			.containsExactlyElementsOf(lastEight);
		
		
	}
	@Test
	public void testUpdateElement() throws Exception{
		
		//GIVEN i have empty database
		
		//WHEN i add new element and try to update his details
		
		UserBoundaryKey dummyCreator=new UserBoundaryKey();
		
		dummyCreator.setEmail("dummy mail");
		dummyCreator.setSmartspace("some smartspace");
		
		ElementBoundaryKey dummyKey=new ElementBoundaryKey();
		
		dummyKey.setId("some id");
		dummyKey.setSmartspace("some smartspace");
		
		LatLng dummlocation=new LatLng();
		dummlocation.setLan(100);
		dummlocation.setLat(200);
		//when i create new element and read it
		ElementBoundary newElement= new ElementBoundary();
		newElement.setKey(dummyKey);
		newElement.setName("dummy name");
		newElement.setElementType("dummy type");
		newElement.setCreator(dummyCreator);
		newElement.setLatlng(dummlocation);
		newElement.setCreated(new Date());
		newElement.setExpired(false);
		newElement.setElementProperties(new HashMap<>());
		ElementEntity element=newElement.convertToEntity();
		this.elementDao.create(element);
		
		System.err.println(element.getElementId());
		ElementBoundary updated= newElement;
		newElement.setName("other name");
		newElement.setElementType("other type ");
		updated.setKey(null);
		
		this.restTemplate
		.put(
			this.baseUrl+"elements/{managerSmartspace}/{managerEmail}/{elementSmartspace}/{elementId}",
			updated,
			element.getCreatorSmartspace(),
			element.getCreatorEmail(),
			element.getElementSmartspace(),
			element.getElementId());
		//THEN the details are updated
		assertThat(this.elementDao.readById(element.getKey()))
		.isPresent()
		.get()
		.extracting("name")
		.containsExactly(updated.getName());
		
		assertThat(this.elementDao.readById(element.getKey()))
		.isPresent()
		.get()
		.extracting("type")
		.containsExactly(updated.getElementType());
		
		tearDown();
		
	}
	
	@Test
	public void testGetSpecificElement() {
		
		//GIVEN an empty database
		
		//WHEN i create an element and read it from database
		
		
		UserBoundaryKey dummyCreator=new UserBoundaryKey();
		
		dummyCreator.setEmail("dummy mail");
		dummyCreator.setSmartspace("some smartspace");
		
		ElementBoundaryKey dummyKey=new ElementBoundaryKey();
		
		dummyKey.setId("some id");
		dummyKey.setSmartspace("some smartspace");
		
		LatLng dummlocation=new LatLng();
		dummlocation.setLan(100);
		dummlocation.setLat(200);
		//when i create new element and read it
		ElementBoundary newElement= new ElementBoundary();
		newElement.setKey(dummyKey);
		newElement.setName("dummy name");
		newElement.setElementType("dummy type");
		newElement.setCreator(dummyCreator);
		newElement.setLatlng(dummlocation);
		newElement.setCreated(new Date());
		newElement.setExpired(false);
		newElement.setElementProperties(new HashMap<>());
		
		ElementEntity entity=newElement.convertToEntity();
		this.elementDao.create(entity);

		ElementBoundary result = 
				  this.restTemplate
					.getForObject(
							this.baseUrl +"elements/{userSmartspace}/{userEmail}/{elementSmartspace}/{elementId}", 
							ElementBoundary.class,
							entity.getCreatorSmartspace(), 
							entity.getCreatorEmail(),
							entity.getElementSmartspace(),
							entity.getElementId());
		//THEN i will get the element i wanted
		
		assertThat(result.getKey().getSmartspace())
		.isEqualTo(entity.getElementSmartspace());
		
		assertThat(result.getKey().getId())
		.asString()
		.isEqualTo(entity.getElementId());
	}
	

}
