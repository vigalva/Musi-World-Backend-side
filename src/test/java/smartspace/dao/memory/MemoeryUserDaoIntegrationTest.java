package smartspace.dao.memory;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import smartspace.dao.UserDao;
import smartspace.data.UserEntity;
import smartspace.data.UserRole;
import smartspace.data.util.EntityFactory;



@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties= {"spring.profiles.active=default"})
public class MemoeryUserDaoIntegrationTest {
	private UserDao<String> userDao;
	private EntityFactory factory;
	
	@Autowired
	public void setMessageDao(UserDao<String> userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setFactory(EntityFactory factory) {
		this.factory = factory;
	}
	
	@Before
	public void setup() {
		this.userDao.deleteAll();
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
//		List<UserEntity> allMessages = 
//		IntStream.range(1, 21) // int Stream
//			.mapToObj(num->"user #" + num) // String Stream
//			.map(text-> // MessageEntity Stream
//					this.factory.createNewUser(
//						null, 
//						null,
//						"test",
//						null, 
//						UserRole.PLAYER,
//						0))
//			.map(this.userDao::create) // MessageEntity Stream
//			.collect(Collectors.toList());
//		
//		// THEN the dao contains 20 message
//		assertThat(this.userDao.readAll())
//			.hasSize(20);
//			//.containsAll(allMessages);
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
		int points=100;
		UserEntity user1 = this.factory.createNewUser(null, null, text, null, null, points);
		user1 = this.userDao.create(user1);
		UserEntity initUser = new UserEntity();
		initUser.setKey(user1.getKey());
		
		int updatedPoints=500;
		UserEntity update = new UserEntity();
		update.setKey(user1.getKey());
		update.setPoints(updatedPoints);
		this.userDao.update(update);
		
		Optional<UserEntity > userOp = this.userDao.readById(user1.getKey());
		
		this.userDao.deleteAll();
		
		List<UserEntity> listAfterDeletion = this.userDao.readAll();
		
		// THEN the initially generated message key is not null
		// AND the message read using key is present
		// AND the key of the message read is not null
		// AND the list after deletion is empty
		assertThat(initUser.getKey())
			.isNotNull();
		assertThat(userOp)
			.isPresent()
			.get()
			.extracting("key")
			.doesNotContain((String)null);
		assertThat(listAfterDeletion)
			.isEmpty();		
	}
}
