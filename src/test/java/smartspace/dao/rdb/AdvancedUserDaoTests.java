package smartspace.dao.rdb;

import static org.assertj.core.api.Assertions.assertThat;


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


import smartspace.dao.AdvancedUserDao;

import smartspace.data.UserEntity;
import smartspace.data.UserRole;
import smartspace.data.util.EntityFactory;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties= {"spring.profiles.active=default"})
public class AdvancedUserDaoTests {
	private AdvancedUserDao<String> userDao;
	private EntityFactory factory;
	
	@Autowired
	public void setMessageDao(AdvancedUserDao<String> actionDao) {
		this.userDao = actionDao;
	}

	@Autowired
	public void setFactory(EntityFactory factory) {
		this.factory = factory;
	}
	
	@After
	public void tearDown() {
		this.userDao.deleteAll();
	}
	
	@Test
	public void testReadAllWithPagination() throws Exception{
		// GIVEN the database contains only 20 messages 
		IntStream.range(0, 20)
			.mapToObj(i->this.factory.createNewUser("dummy mail"+i, "dummy smart sapce", "dummy user", "dummy avatar", UserRole.PLAYER, 100))
			.forEach(this.userDao::create);
		
		// WHEN I read 3 messages from page 6
		List<UserEntity> actual = this.userDao.readAll(3, 6);
		
		// THEN I receive 2 messages
		assertThat(actual)
			.hasSize(2);
		
		tearDown();
	}
	
	@Test
	public void testReadAllWithPaginationAndSortByName() throws Exception{
		// GIVEN the database contains only 10 elements 
		IntStream.range(0, 10)
		.mapToObj(i->this.factory.createNewUser("dummy mail"+i, "dummy smart sapce", "User"+i, "dummy avatar", UserRole.PLAYER, 100))
		.forEach(this.userDao::create);
		
		// WHEN I read 2 elements from page 3 and sorting by Name
		List<UserEntity> actual = this.userDao.readAll("username", 2, 3);
		
		// THEN I receive actions with name containig: "6","7"
		assertThat(actual)
			.usingElementComparatorOnFields("username")
			.containsExactly(
					factory.createNewUser("dummy mail", "dummy smart sapce", "User6", "dummy avatar", UserRole.PLAYER, 100),
					factory.createNewUser("dummy mail", "dummy smart sapce", "User7", "dummy avatar", UserRole.PLAYER, 100));		
		tearDown();
	}



	
}















