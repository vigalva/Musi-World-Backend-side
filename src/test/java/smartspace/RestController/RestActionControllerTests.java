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

import smartspace.dao.AdvancedActionDao;
import smartspace.dao.AdvancedElementDao;
import smartspace.data.ActionEntity;
import smartspace.data.ElementEntity;
import smartspace.data.Location;
import smartspace.layout.ActionBoundary;
import smartspace.layout.ActionBoundaryKey;
import smartspace.layout.ElementBoundary;
import smartspace.layout.ElementBoundaryKey;
import smartspace.layout.LatLng;
import smartspace.layout.UserBoundaryKey;
import smartspace.logic.ActionService;
import smartspace.logic.ElementService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties= {"spring.profiles.active=default"})

public class RestActionControllerTests {
	
	private int port;
	private String baseUrl;
	private RestTemplate restTemplate;
	private AdvancedActionDao<String> actionDao;
	private ActionService actionService;
	
	@Autowired
	public void setMessageDao(AdvancedActionDao<String> actionDao) {
		this.actionDao = actionDao;
	}
	
	@Autowired
	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
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
		this.actionDao
			.deleteAll();
	}

	@Test
	public void testImportActionsToDataBase() throws Exception{
		// GIVEN the database is clean 
		String adminSmartspace="dummy smartspace";
		String adminEmail="dummy mail";
		
		UserBoundaryKey dummyCreator=new UserBoundaryKey();
		dummyCreator.setEmail("dummy mail");
		dummyCreator.setSmartspace("dummy smartspace");
		
		ElementBoundaryKey dummyElement=new ElementBoundaryKey();
		
		dummyElement.setId("dummy id");
		dummyElement.setSmartspace("dummy smartspace");
		
		ActionBoundaryKey dummyKey=new ActionBoundaryKey();
		dummyKey.setId("some id");
		dummyKey.setSmartspace("some somartspace");
		
		List<ActionBoundary> actions = new ArrayList<>();
		ActionBoundary action= new ActionBoundary();
		action.setCreated(new Date());
		action.setElement(dummyElement);action.setActionKey(dummyKey);
		action.setPlayer(dummyCreator);
		action.setProperties(new HashMap<>());
		action.setType("dummy Type");
		
		actions.add(action);
		

		
		ActionBoundary[] response = this.restTemplate
			.postForObject(
					this.baseUrl+"admin/actions/{adminSmartspace}/{adminEmail}",
					actions, 
					ActionBoundary[].class,adminSmartspace, adminEmail);
	
		// THEN the database contains 1 message
		// AND the returned message is similar to the message in the database
		assertThat(
				this.actionDao.readAll())
			.hasSize(1)
		.usingElementComparatorOnFields("key")
		.containsExactly(response[0].convertToEntity());
		
	tearDown();
	}

	

	@Test
	public void testGetActionUsingPagination() throws Exception{
		
		// GIVEN the database contains 38 messages
		int totalSize = 38;
		
		ActionBoundary newAction=new ActionBoundary();
		
		ElementBoundaryKey dummyElement= new ElementBoundaryKey();
		UserBoundaryKey dummyCreator=new UserBoundaryKey();
		ActionBoundaryKey dummyKey=new ActionBoundaryKey();

		
		dummyCreator.setEmail("dummy mail");
		dummyCreator.setSmartspace("some smartspace");
		
		
		dummyElement.setId("some id");
		dummyElement.setSmartspace("some smartspace");
		
		dummyKey.setId("some id");
		dummyKey.setSmartspace("some smartspace");
		

//		// WHEN I import array of 38 ElementBoundaries
		
		newAction.setCreated(new Date());
		newAction.setElement(dummyElement);
		newAction.setActionKey(dummyKey);
		newAction.setPlayer(dummyCreator);
		newAction.setProperties(new HashMap<>());
		newAction.setType("dummy Type");

		
		List<ActionEntity> allEntities = 
				IntStream.range(1, totalSize + 1)
					.mapToObj(i->newAction.getActionKey().getSmartspace()+ i)
					.map(action->new ActionEntity(
							newAction.getElement().getId(), 
							newAction.getElement().getSmartspace(),
							newAction.getType(),
							newAction.getCreated(), 
							newAction.getPlayer().getEmail(),
							newAction.getPlayer().getSmartspace(),
							newAction.getProperties()))
		
					.collect(Collectors.toList());
				
		
		for (int i=0;i<allEntities.size();i++) {
			allEntities.get(i).setActionSmartspace(dummyKey.getSmartspace());
			allEntities.get(i).setActionId(dummyKey.getId());

		}
		allEntities=this.actionService.importActions(allEntities);
		

		
		List<ActionBoundary> lastEight = 
				allEntities
				.stream()
				.skip(30)
				.map(ActionBoundary::new)
				.collect(Collectors.toList());
		
		// WHEN I getElements using page #3 of size 10 i get 8 messages

		
		int size = 10;
		int page = 3;
		String adminSmartspace="dummy smartspace";
		String adminEmail="dummy mail";
		ActionBoundary[] results = 
		  this.restTemplate
			.getForObject(
					this.baseUrl +"admin/actions/{adminSmartspace}/{adminEmail}"+"?size={size}&page={page}", 
					ActionBoundary[].class,adminSmartspace, adminEmail, 
					size, page);
		
		ArrayList<ActionBoundary> arrayList = new ArrayList<ActionBoundary>(Arrays.asList(results));	
		assertThat(results)
			.usingElementComparatorOnFields("type")
			.containsExactlyElementsOf(lastEight);
		
	

	}
	@Test
	public void testGetMessagesUsingPaginationWithoutNoResult() throws Exception{
		
		
	 //GIVEN the database is clean 
		int totalSize = 30;
		
		ActionBoundary newAction=new ActionBoundary();
		
		ElementBoundaryKey dummyElement= new ElementBoundaryKey();
		UserBoundaryKey dummyCreator=new UserBoundaryKey();
		ActionBoundaryKey dummyKey=new ActionBoundaryKey();

		
		dummyCreator.setEmail("dummy mail");
		dummyCreator.setSmartspace("some smartspace");
		
		
		dummyElement.setId("some id");
		dummyElement.setSmartspace("some smartspace");
		
		dummyKey.setId("some id");
		dummyKey.setSmartspace("some smartspace");
		

//		// WHEN I import array of 38 ElementBoundaries
		
		newAction.setCreated(new Date());
		newAction.setElement(dummyElement);
		newAction.setActionKey(dummyKey);
		newAction.setPlayer(dummyCreator);
		newAction.setProperties(new HashMap<>());
		newAction.setType("dummy Type");

		
		List<ActionEntity> allEntities = 
				IntStream.range(1, totalSize + 1)
					.mapToObj(i->newAction.getActionKey().getSmartspace()+ i)
					.map(action->new ActionEntity(
							newAction.getElement().getId(), 
							newAction.getElement().getSmartspace(),
							newAction.getType(),
							newAction.getCreated(), 
							newAction.getPlayer().getEmail(),
							newAction.getPlayer().getSmartspace(),
							newAction.getProperties()))
		
					.collect(Collectors.toList());
				
		
		for (int i=0;i<allEntities.size();i++) {
			allEntities.get(i).setActionSmartspace(dummyKey.getSmartspace());
			allEntities.get(i).setActionId(dummyKey.getId());

		}
		allEntities=this.actionService.importActions(allEntities);
		

		
		List<ActionBoundary> lastEight = 
				allEntities
				.stream()
				.skip(30)
				.map(ActionBoundary::new)
				.collect(Collectors.toList());
		
		// WHEN I getElements using page #3 of size 10 i get 8 messages

		
		int size = 10;
		int page = 3;
		String adminSmartspace="dummy smartspace";
		String adminEmail="dummy mail";
		ActionBoundary[] results = 
		  this.restTemplate
			.getForObject(
					this.baseUrl +"admin/actions/{adminSmartspace}/{adminEmail}"+"?size={size}&page={page}", 
					ActionBoundary[].class,adminSmartspace, adminEmail, 
					size, page);
		
		ArrayList<ActionBoundary> arrayList = new ArrayList<ActionBoundary>(Arrays.asList(results));
		
		assertThat(results)
			.usingElementComparatorOnFields("name")
			.containsExactlyElementsOf(lastEight);
		
		
	}

	

}
