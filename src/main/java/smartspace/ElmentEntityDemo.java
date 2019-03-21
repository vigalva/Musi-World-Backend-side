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

import smartspace.dao.ElementDao;
import smartspace.data.ElementEntity;
import smartspace.data.util.EntityFactory;

@Component
public class ElmentEntityDemo implements CommandLineRunner {
	private EntityFactory factory;
	private ElementDao<String> elementDao;

	@Autowired
	public ElmentEntityDemo(EntityFactory factory, ElementDao<String> elementDao) {
		this.factory = factory;
		this.elementDao = elementDao;
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
		
		ElementEntity<String> element1 = this.factory.createNewElement("something", "something", null, null, null, null, false, new HashMap<>());

		System.err.println("new Element:\n" + element1);
		
		element1 = this.elementDao.create(element1);
		System.err.println("stored message: " + element1.getKey());
		
		Map<String, Object> updatedDetails = new HashMap<>(element1.getMoreAttributes());
		updatedDetails.put("location", new double[] {1.0, 6.0});
		updatedDetails.put("locationList", Arrays.asList(new double[] {1.0, 6.0}));
		updatedDetails.put("locationPoint", new Point(1, 6));
		ElementEntity<String> update = new ElementEntity<String>();
		update.setKey(element1.getKey());
		update.setMoreAttributes(updatedDetails);
		update.setName("New Something");
		this.elementDao.update(update);
		
		Optional<ElementEntity<String>> elementOP = this.elementDao.readById(element1.getKey());
		if (elementOP.isPresent()) {
			element1 = elementOP.get(); 
		}else {
			throw new RuntimeException("Error! message vanished after update");
		}
		System.err.println("updated element: " + element1+ "updated details is: "+ element1.getName()+ " "+element1.getMoreAttributes());
		
		this.elementDao.deleteAll();
		
		if (this.elementDao.readAll().isEmpty()) {
			System.err.println("successfully deleted all messages");
		}else {
			throw new RuntimeException("Error! messages still exist after deletion");
		}
		
	}

}
