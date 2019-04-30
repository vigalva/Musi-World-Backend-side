package smartspace.dao.memory;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Point;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import smartspace.dao.ElementDao;
import smartspace.data.ElementEntity;
import smartspace.data.util.EntityFactory;





@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties= {"spring.profiles.active=default"})
public class MemoeryElementDaoIntegrationTest {
	private ElementDao<String> elementDao;
	private EntityFactory factory;
	
	@Autowired
	public void setMessageDao(ElementDao<String> elementDao) {
		this.elementDao = elementDao;
	}
	
	@Autowired
	public void setFactory(EntityFactory factory) {
		this.factory = factory;
	}
	
	@Before
	public void setup() {
		this.elementDao.deleteAll();
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
//		List<ElementEntity<String>> allMessages = 
//		IntStream.range(1, 21) // int Stream
//			.mapToObj(num->"element #" + num) // String Stream
//			.map(text-> // MessageEntity Stream
//					this.factory.createNewElement(
//							"dummy", 
//							null, 
//							null, 
//							new Date(), 
//							null, 
//							null, 
//							false, 
//							null))
//			.map(this.elementDao::create) // MessageEntity Stream
//			.collect(Collectors.toList());
//		
//		// THEN the dao contains 20 message
//		assertThat(this.elementDao.readAll())
////			.hasSize(20)
//			.containsAll(allMessages);
//	}
	
	@Test
	public void testCreateUpdateReadByKeyDeleteAllReadAll() throws Exception{
		// GIVEN we have a dao
		// AND we have a factory
		
		// WHEN I create a new message
		// AND I update the message
		// AND I read the message by key
		// AND I delete all
		// AND I read all
		String text = "Test";
		Map<String, Object> details = new HashMap<>();
		details.put("size", text.length());
		details.put("font", "Arial-7px");
		details.put("language", Locale.ENGLISH);
		ElementEntity element1 = this.factory.createNewElement(text, null, null, new Date(), null, null, false, details);
		element1 = this.elementDao.create(element1);
		ElementEntity initElement = new ElementEntity();
		initElement.setKey(element1.getKey());
		
		Map<String, Object> updatedDetails = new HashMap<>(element1.getMoreAttributes());
		updatedDetails.put("location", new double[] {1.0, 6.0});
		updatedDetails.put("locationList", Arrays.asList(new double[] {1.0, 6.0}));
		updatedDetails.put("locationPoint", new Point(1, 6));
		ElementEntity update = new ElementEntity();
		update.setKey(element1.getKey());
		update.setMoreAttributes(updatedDetails);
		this.elementDao.update(update);
		
		Optional<ElementEntity > elementOp = this.elementDao.readById(element1.getKey());
		
		this.elementDao.deleteAll();
		
		List<ElementEntity> listAfterDeletion = this.elementDao.readAll();
		
		// THEN the initially generated message key is not null
		// AND the message read using key is present
		// AND the key of the message read is not null
		// AND the list after deletion is empty
		assertThat(initElement.getKey())
			.isNotNull();
		assertThat(elementOp)
			.isPresent()
			.get()
			.extracting("key")
			.doesNotContain((String)null);
		assertThat(listAfterDeletion)
			.isEmpty();		
	}
}
