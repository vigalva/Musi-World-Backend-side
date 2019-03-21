package smartspace;


import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import smartspace.dao.ActionDao;
import smartspace.data.ActionEntity;
import smartspace.data.util.EntityFactory;

@Component
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
		
		ActionEntity<String> action1 = factory.createNewAction(null, null, "Something", new Date(), null, null, null);
		System.err.println("new action: " + action1.getKey());
		
		
		action1 = this.actionDao.create(action1);
		System.err.println("action created is " + action1.getKey());
		
	
		
		this.actionDao.deleteAll();
		
		if (this.actionDao.readAll().isEmpty()) {
			System.err.println("successfully deleted all messages");
		}else {
			throw new RuntimeException("Error! messages still exist after deletion");
		}
		
	}

}
