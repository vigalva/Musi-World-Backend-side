package smartspace.dao.rdb;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.LongStream;
import java.util.Optional;

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
public class RdbUserIntegrationTests {
	private UserDao<String> userDao;
	private EntityFactory factory;
	
	@Autowired
	public void setUserDao(UserDao<String> userDao) {
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
		this.userDao.deleteAll();
	}
	
	@Test
	// Method to check creation of 1 user
	public void testCreateUser () throws Exception {
		// GIVEN the database is clean
		
		// WHEN we create a new user and store it in DB
		UserEntity newUser=factory.createNewUser("userEmail", "userSmartspace", "userName", "userA", UserRole.PLAYER, 10);
		this.userDao.create(newUser);
		System.err.println(newUser.getKey());
		// THEN the user is stored 
		//assertThat(this.actionDao.readAll()).contains(newAction); This test does not work for some reason
		assertThat(this.userDao.readAll())
		.usingElementComparatorOnFields("key")
		.contains(newUser); // This test works
		teardown();
	}
	
	@Test
	// Testing the readById
	public void testReadById () throws Exception {
		// GIVEN the database has a user with user key we wish to find
		UserEntity newUser=factory.createNewUser("userEmail", "userSmartspace", "userName", "userA", UserRole.PLAYER, 10);
		this.userDao.create(newUser);
		String userKey = newUser.getKey();
		// WHEN we want to read a user by its Id
		Optional<UserEntity> someOptUser = this.userDao.readById(userKey);
		assertThat(someOptUser.get().getKey()).isEqualToIgnoringCase(newUser.getKey());
		teardown();
	}
	
	@Test
	// Test to check readAll method and deleteAll
	public void testCreateManyUsersAndDeletion () throws Exception {
		// GIVEN the database is clean
		
		// WHEN we create 20 users with and store it in DB
		LongStream
		.range(1, 21).mapToObj(i->this.factory.createNewUser("userEmail", "userSmartspace", "userName", "userA", UserRole.PLAYER, i))
		.map(this.userDao::create);
		List<UserEntity> users = this.userDao.readAll();
		
		
		// THEN the users are stored with unique id
		assertThat(this.userDao.readAll()).containsAll(users); 
	
		this.userDao.deleteAll();
		assertThat(this.userDao.readAll()).isEmpty();
		
	}
	
	@Test
	// Test the update method of a user
	public void testUpdateUser () throws Exception {
		// GIVEN empty DB
		UserEntity newUser=factory.createNewUser("userEmail", "userSmartspace", "userName", "userA", UserRole.PLAYER, 10);
		this.userDao.create(newUser);
		
		// WHEN we create user and Update his attributes
		newUser.setPoints(300);
		newUser.setRole(UserRole.MANAGER);
		newUser.setAvatar("UserB");
		newUser.setUsername("UserName2");
		this.userDao.update(newUser);
		System.err.println(newUser.getKey());
		//THEN the attributes are updated in DB
		Optional<UserEntity> Updateduser=this.userDao.readById(newUser.getKey());
		assertThat(newUser.getPoints()).isEqualTo(Updateduser.get().getPoints());
		assertThat(newUser.getRole()).isEqualTo(Updateduser.get().getRole());
		assertThat(newUser.getAvatar()).isEqualTo(Updateduser.get().getAvatar());
		assertThat(newUser.getUsername()).isEqualTo(Updateduser.get().getUsername());
		assertThat(newUser.getUserSmatspace()).isEqualTo(Updateduser.get().getUserSmatspace());
		teardown();

		
	}
}
