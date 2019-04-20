package smartspace.dao.rdb;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.HashMap;
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

import smartspace.dao.ElementDao;

import smartspace.data.ElementEntity;
import smartspace.data.Location;
import smartspace.data.util.EntityFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties= {"spring.profiles.active=default"})
public class RdbElementIntegrationTests {
	private ElementDao<String> elementDao;
	private EntityFactory factory;
	
	@Autowired
	public void setUserDao(ElementDao<String> elementDao) {
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
		this.elementDao.deleteAll();
	}
	
	@Test
	// Method to check creation of 1 element
	public void testCreateElement () throws Exception {
		// GIVEN the database is clean
		
		// WHEN we create a new Lement and store it in DB
		ElementEntity newElement=factory.createNewElement("element", "album", new Location(), new Date(), "dummy mail", "dummy smartspace", false, new HashMap<>());
		this.elementDao.create(newElement);
		// THEN the user is stored 
		assertThat(this.elementDao.readAll())
		.usingElementComparatorOnFields("key")
		.contains(newElement); // This test works
		teardown();
	}
	
	@Test
	// Testing the readById
	public void testReadById () throws Exception {
		// GIVEN the database has a user with user key we wish to find
		ElementEntity newElement=factory.createNewElement("element", "album", new Location(), new Date(), "dummy mail", "dummy smartspace", false, new HashMap<>());
		this.elementDao.create(newElement);
		String elemenKey = newElement.getKey();
		// WHEN we want to read a user by its Id
		Optional<ElementEntity> someOptUser = this.elementDao.readById(elemenKey);
		assertThat(someOptUser.get().getKey()).isEqualToIgnoringCase(newElement.getKey());
		teardown();
	}
	
	@Test
	// Test to check readAll method and deleteAll
	public void testCreateManyElementsAndDeletionOfAll () throws Exception {
		// GIVEN the database is clean
		
		// WHEN we create 20 users with and store it in DB
		LongStream
		.range(1, 21).mapToObj(i->this.factory.createNewElement("dummy name", "dummy type", new Location(), new Date(), "dummy creator mail", "dumym creator smartspace", false,	new HashMap<>()))
		.map(this.elementDao::create);
		List<ElementEntity> elements = this.elementDao.readAll();
		
		
		// THEN the users are stored with unique id
		assertThat(this.elementDao.readAll()).containsAll(elements); 
	
		this.elementDao.deleteAll();
		assertThat(this.elementDao.readAll()).isEmpty();
	}
	
	@Test
	// Test the update method of a element
	public void testUpdateElement () throws Exception {
		// GIVEN empty DB
		
		// WHEN we create elemnt and Update his attributes
		ElementEntity newelement=this.factory.createNewElement("dummy name", "dummy type", new Location(), new Date(), "dummy creator mail", "dumym creator smartspace", false,	new HashMap<>());
		this.elementDao.create(newelement);
		
		HashMap<String, Object> details=new HashMap<String, Object>();
		details.put("Long number", 1000);
		Location loc=new Location();
		loc.setX(1000);
		loc.setY(2000);
		newelement.setType("dummy type2");
		newelement.setName("dummyname2");
		newelement.setMoreAttributes(details);
		newelement.setLocation(loc);
		newelement.setExpired(true);
		newelement.setCreatorSmartspace("another thing");
		newelement.setCreationTimestamp(new Date());
		newelement.setCreatorEmail("other email");
		this.elementDao.update(newelement);
		//THEN the attributes are updated in DB

		Optional<ElementEntity> Updatedelement=this.elementDao.readById(newelement.getKey());
		assertThat(newelement.getType()).isEqualTo(Updatedelement.get().getType());
		assertThat(newelement.getName()).isEqualTo(Updatedelement.get().getName());
		assertThat(newelement.getMoreAttributes()).isEqualTo(Updatedelement.get().getMoreAttributes());
		assertThat(newelement.getLocation().getX()).isEqualTo(Updatedelement.get().getLocation().getX());
		assertThat(newelement.getLocation().getY()).isEqualTo(Updatedelement.get().getLocation().getY());
		assertThat(newelement.getCreatorSmartspace()).isEqualTo(Updatedelement.get().getCreatorSmartspace());
		assertThat(newelement.getCreatorEmail()).isEqualTo(Updatedelement.get().getCreatorEmail());
		assertThat(newelement.getCreationTimestamp()).isEqualTo(Updatedelement.get().getCreationTimestamp());


		teardown();
		

	}
	@Test
	// Test the update method of a element
	public void testDeleteByKey() throws Exception{
		
		//GIVEN empty DB
		
		//WHEN we create new element and try to delete it by key
		ElementEntity newelement=this.factory.createNewElement("dummy name", "dummy type", new Location(), new Date(), "dummy creator mail", "dumym creator smartspace", false,	new HashMap<>());
		this.elementDao.create(newelement);
		assertThat(this.elementDao.readAll()).isNotEmpty();
		this.elementDao.deleteByKey(newelement.getKey());
		
		
		//Then the database is empty again
		assertThat(this.elementDao.readAll()).isEmpty();
		
		teardown();

	}
	@Test
	// Test the update method of a element
	public void testDeleteByElement() throws Exception{
		
		//GIVEN empty DB
		
		//WHEN we create new element and try to delete it 
		ElementEntity newelement=this.factory.createNewElement("dummy name", "dummy type", new Location(), new Date(), "dummy creator mail", "dumym creator smartspace", false,	new HashMap<>());
		this.elementDao.create(newelement);
		assertThat(this.elementDao.readAll()).isNotEmpty();
		this.elementDao.delete(newelement);
		
		
		//Then the database is empty again
		assertThat(this.elementDao.readAll()).isEmpty();
		
		teardown();

	}
}
