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

import smartspace.dao.AdvancedElementDao;
import smartspace.data.ElementEntity;
import smartspace.data.Location;
import smartspace.data.util.EntityFactory;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties= {"spring.profiles.active=default"})
public class AdvancedElementDaoTests {
	private AdvancedElementDao<String> elementDao;
	private EntityFactory factory;
	
	@Autowired
	public void setMessageDao(AdvancedElementDao<String> actionDao) {
		this.elementDao = actionDao;
	}

	@Autowired
	public void setFactory(EntityFactory factory) {
		this.factory = factory;
	}
	
	@After
	public void tearDown() {
		this.elementDao.deleteAll();
	}
	
	@Test
	public void testReadAllWithPagination() throws Exception{
		// GIVEN the database contains only 20 messages 
		IntStream.range(0, 20)
			.mapToObj(i->this.factory.createNewElement("dummy elemnt", "dummy type", new Location(), new Date(), "dummy mail", "dummy mail", false, new HashMap<>()))
			.forEach(this.elementDao::create);
		
		// WHEN I read 3 messages from page 6
		List<ElementEntity> actual = this.elementDao.readAll(3, 6);
		
		// THEN I receive 2 messages
		assertThat(actual)
			.hasSize(2);
		
		tearDown();
	}
	
	@Test
	public void testReadAllWithPaginationAndSortByName() throws Exception{
		// GIVEN the database contains only 10 elements 
		IntStream.range(0, 10)
		.mapToObj(i->this.factory.createNewElement("something"+i, "dummy type", new Location(), new Date(), "dummy mail", "dummy mail", false, new HashMap<>()))
		.forEach(this.elementDao::create);
		
		// WHEN I read 2 elements from page 3 and sorting by Name
		List<ElementEntity> actual = this.elementDao.readAll("Name", 2, 3);
		
		// THEN I receive actions with name containisg: "6","7"
		assertThat(actual)
			.usingElementComparatorOnFields("Name")
			.containsExactly(
					factory.createNewElement("something6", "dummy type", new Location(), new Date(), "dummy mail", "dummy mail", false, new HashMap<>()),
					factory.createNewElement("something7", "dummy type", new Location(), new Date(), "dummy mail", "dummy mail", false, new HashMap<>()));
		
		tearDown();
	}



	
}















