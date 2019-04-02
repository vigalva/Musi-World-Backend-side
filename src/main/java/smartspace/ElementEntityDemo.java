package smartspace;

import java.awt.Point;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import smartspace.dao.ElementDao;
import smartspace.data.ElementEntity;
import smartspace.data.Location;
import smartspace.data.util.EntityFactory;

@Component
@Profile("production")
public class ElementEntityDemo implements CommandLineRunner {
	private EntityFactory factory;
	private ElementDao<String> elementDao;

	@Autowired
	public ElementEntityDemo(EntityFactory factory, ElementDao<String> elementDao) {
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
		
		ElementEntity element1 = this.factory.createNewElement("dummy name", "dummy type", new Location(), new Date(), "dummy mail", "dummy smartspace", false, new HashMap<>());
		System.err.println("new Element:\n" + element1);
		
		element1 = this.elementDao.create(element1);
		System.err.println(" element created: " + element1.getKey());
		
		Map<String, Object> updatedDetails = new HashMap<>(element1.getMoreAttributes());
		updatedDetails.put("location", new double[] {1.0, 6.0});
		updatedDetails.put("locationList", Arrays.asList(new double[] {1.0, 6.0}));
		updatedDetails.put("locationPoint", new Point(1, 6));
		ElementEntity update = new ElementEntity();
		update.setKey(element1.getKey());
		update.setMoreAttributes(updatedDetails);
		update.setName("New Something");
		this.elementDao.update(update);
		
		Optional<ElementEntity> elementOP = this.elementDao.readById(element1.getKey());
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
