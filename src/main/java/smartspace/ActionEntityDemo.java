package smartspace;


import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import smartspace.dao.ActionDao;
import smartspace.data.ActionEntity;
import smartspace.data.util.EntityFactory;

@Component
@Profile("production")
public class ActionEntityDemo implements CommandLineRunner {
	private EntityFactory factory;
	private ActionDao actionDao;
	
	@Autowired
	public ActionEntityDemo(EntityFactory factory, ActionDao actionDao) {
		this.factory = factory;
		this.actionDao = actionDao;
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
		
		ActionEntity action1 = factory.createNewAction("dummyId", "dummy smartspace", "Test", new Date(), "dummy play email", "dummy player smartspace", new HashMap<>());
		System.err.println("new action: " + action1.getKey());
		
		
		action1 = this.actionDao.create(action1);
		System.err.println("action created is " + action1.getKey());
		
	
		
		this.actionDao.deleteAll();
		
		if (this.actionDao.readAll().isEmpty()) {
			System.err.println("successfully deleted all actions");
		}else {
			throw new RuntimeException("Error! messages still exist after deletion");
		}
		
	}

}
