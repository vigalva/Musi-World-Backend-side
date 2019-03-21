package smartspace;

import java.awt.Point;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import smartspace.dao.UserDao;
import smartspace.data.UserEntity;
import smartspace.data.util.EntityFactory;

@Component
public class UserEntityDemo implements CommandLineRunner {
	private EntityFactory factory;
	private UserDao<String> userDao;

	@Autowired
	public UserEntityDemo(EntityFactory factory, UserDao<String> userDao) {
		this.factory = factory;
		this.userDao = userDao;
	}

//	@Autowired
//	public void setFactory(DummyEntityFactory factory) {
//		this.factory = factory;
//	}
//	
//	@Autowired
//	public void setMessageDao(MessageDao<String> messageDao) {
//		this.messageDao = messageDao;
//	}

	@Override
	public void run(String... args) throws Exception {
		
		UserEntity<String> user1 = this.factory.createNewUser(null, null, "demo", "demo", null, 0);

		System.err.println("new user:\n" + user1);
		
		user1 = this.userDao.create(user1);
		System.err.println("new user: " + user1.getKey());
		
		
		UserEntity<String> update = new UserEntity<String>();
		update.setKey(user1.getKey());
		update.setPoints(10);
		update.setUsername("somethine else");
		this.userDao.update(update);
		
		Optional<UserEntity<String>> userOP = this.userDao.readById(user1.getKey());
		if (userOP.isPresent()) {
			user1 = userOP.get(); 
		}else {
			throw new RuntimeException("Error! message vanished after update");
		}
		System.err.println("updated element: " + user1+ "updated details is: "+user1.getPoints()+ " "+user1.getUsername());
		
		this.userDao.deleteAll();
		
		if (this.userDao.readAll().isEmpty()) {
			System.err.println("successfully deleted all messages");
		}else {
			throw new RuntimeException("Error! messages still exist after deletion");
		}
		
	}

}
