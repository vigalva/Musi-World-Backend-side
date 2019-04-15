package smartspace.dao.rdb;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.stream.IntStream;
//import java.util.stream.Stream;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import smartspace.dao.AdvancedActionDao;
import smartspace.data.ActionEntity;
import smartspace.data.util.EntityFactory;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties= {"spring.profiles.active=default"})
public class AdvancedActionDaoTests {
	private AdvancedActionDao<String> actionDao;
	private EntityFactory factory;
	
	@Autowired
	public void setMessageDao(AdvancedActionDao<String> actionDao) {
		this.actionDao = actionDao;
	}

	@Autowired
	public void setFactory(EntityFactory factory) {
		this.factory = factory;
	}
	
	@After
	public void tearDown() {
		this.actionDao.deleteAll();
	}
	
	@Test
	public void testReadAllWithPagination() throws Exception{
		// GIVEN the database contains only 20 messages 
		IntStream.range(0, 20)
			.mapToObj(i->this.factory.createNewAction("duumy element", "dummy smartspace", "Warning", new Date(), "dummy mail", "dummy smartspace", new HashMap<>()))
			.forEach(this.actionDao::create);
		
		// WHEN I read 3 messages from page 6
		List<ActionEntity> actual = this.actionDao.readAll(3, 6);
		
		// THEN I receive 2 messages
		assertThat(actual)
			.hasSize(2);
		
		tearDown();
	}
	
	@Test
	public void testReadAllWithPaginationAndSortByType() throws Exception{
		// GIVEN the database contains only 10 actions 
		IntStream.range(0, 10)
			.mapToObj(i->this.factory.createNewAction("duumy element", "dummy smartspace", "warning"+i, new Date(), "dummy mail", "dummy smartspace", new HashMap<>()))
			.forEach(this.actionDao::create);
		
		// WHEN I read 2 actions from page 3 and sorting by ActionType
		List<ActionEntity> actual = this.actionDao.readAll("ActionType", 2, 3);
		
		// THEN I receive actions with type containig: "6","7"
		assertThat(actual)
			.usingElementComparatorOnFields("ActionType")
			.containsExactly(
					factory.createNewAction("duumy element", "dummy smartspace", "warning6", new Date(), "dummy mail", "dummy smartspace", new HashMap<>()),
					factory.createNewAction("duumy element", "dummy smartspace", "warning7", new Date(), "dummy mail", "dummy smartspace", new HashMap<>()));
		
		tearDown();
	}

//	@Test
//	public void testReadAllWithPaginationFromTheStartAndSortByType() throws Exception{
//		// GIVEN the database contains only 10 messages 
//		IntStream.range(0, 10)
//			.mapToObj(i->this.factory.createNewMessageEntity(
//					"text #" + i, 
//					new Name("test", "Tester"), 
//					SeverityEnum.WARNING, 
//					new HashMap<String, Object>()))
//			.forEach(this.messageDao::create);
//		
//		// WHEN I read 3 messages from page 0 and sorting by text
//		List<MessageEntity> actual = this.messageDao.readAll("text", 3, 0);
//		
//		// THEN I receive messages with text containig: "0","1","2"
//		assertThat(actual)
//			.usingElementComparatorOnFields("text")
//			.containsExactly(
//					factory.createNewMessageEntity("text #0", null, null, null),
//					factory.createNewMessageEntity("text #1", null, null, null),
//					factory.createNewMessageEntity("text #2", null, null, null));
//	}

	
}















