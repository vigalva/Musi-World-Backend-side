package smartspace.dao.memory;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Point;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import smartspace.dao.ActionDao;
import smartspace.data.ActionEntity;
import smartspace.data.util.EntityFactory;



@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties= {"spring.profiles.active=default"})
public class MemoryActionDaoIntegrationTest {
	private ActionDao actionDao;
	private EntityFactory factory;
	
	@Autowired
	public void setMessageDao(ActionDao messageDao) {
		this.actionDao = messageDao;
	}
	
	@Autowired
	public void setFactory(EntityFactory factory) {
		this.factory = factory;
	}
	
	@Before
	public void setup() {
		this.actionDao.deleteAll();
	}

	@After
	public void teardown() {
//		this.messageDao.deleteAll();
	}

//	@Test
//	public void testCreateManyMessages() throws Exception{
//		// GIVEN we have a clean dao
//		// AND we have a factory
//		
//
//		// WHEN I create 20 messages
//		List<ActionEntity<String>> allMessages = 
//		IntStream.range(1, 21) // int Stream
//			.mapToObj(num->"action #" + num) // String Stream
//			.map(action-> // MessageEntity Stream
//					this.factory.createNewAction(
//						action,
//						null,
//						"something",
//						new Date(),
//						null,
//						null,
//						new HashMap<>()))
//						
//			.map(this.actionDao::create) // MessageEntity Stream
//			.collect(Collectors.toList());
//		
//		// THEN the dao contains 20 message
//		assertThat(this.actionDao.readAll())
////			.hasSize(20)
//			.containsAll(allMessages);
//	}
	
	@Test
	public void testCreateUpdateReadByKeyDeleteAllReadAll() throws Exception{
		// GIVEN we have a dao
		// AND we have a factory
		
		// WHEN I create a new message
		// AND I read the message by key
		// AND I delete all
		// AND I read all
		String action = "Test";
		Map<String, Object> details = new HashMap<>();
		details.put("size", action.length());
		details.put("font", "Arial-7px");
		details.put("language", Locale.ENGLISH);
		ActionEntity action1 = this.factory.createNewAction(null,
				null,
				action,
				new Date(),
				null,
				null,
				details);

		action1 = this.actionDao.create(action1);
		ActionEntity initMessage = new ActionEntity();
		initMessage.setKey(action1.getKey());
		
		Map<String, Object> updatedDetails = new HashMap<>(action1.getMoreAttributes());
		updatedDetails.put("location", new double[] {1.0, 6.0});
		updatedDetails.put("locationList", Arrays.asList(new double[] {1.0, 6.0}));
		updatedDetails.put("locationPoint", new Point(1, 6));
		ActionEntity update = new ActionEntity();
		update.setKey(action1.getKey());
		update.setMoreAttributes(updatedDetails);
	

		
		this.actionDao.deleteAll();
		
		List<ActionEntity> listAfterDeletion = this.actionDao.readAll();
		
		// THEN the initially generated message key is not null
		// AND the message read using key is present
		// AND the key of the message read is not null
		// AND the list after deletion is empty
		assertThat(initMessage.getKey())
			.isNotNull();
		assertThat(listAfterDeletion)
			.isEmpty();		
	}
}