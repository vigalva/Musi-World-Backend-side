package smartspace;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import smartspace.dao.UserDao;
import smartspace.data.UserEntity;
import smartspace.data.UserRole;
import smartspace.data.util.EntityFactory;

@Component
@Profile("production")
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
		
		UserEntity user1 = this.factory.createNewUser("dummy", "dummy", "dummy userName", "dummy avatar", UserRole.PLAYER, 100);

		System.err.println("new user:\n" + user1);
		
		user1 = this.userDao.create(user1);
		System.err.println("new user: " + user1.getKey());
		
		
		UserEntity update = new UserEntity();
		update.setKey(user1.getKey());
		update.setPoints(10);
		update.setUsername("somethine else");
		this.userDao.update(update);
		
		Optional<UserEntity> userOP = this.userDao.readById(user1.getKey());
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
