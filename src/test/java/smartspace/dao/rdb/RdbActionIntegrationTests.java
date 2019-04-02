package smartspace.dao.rdb;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Point;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

import smartspace.dao.ActionDao;
import smartspace.data.ActionEntity;
import smartspace.data.util.EntityFactory;





@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties= {"spring.profiles.active=default"})
public class RdbActionIntegrationTests<K> {
	private ActionDao actionDao;
	private EntityFactory factory;
	
	@Autowired
	public void setMessageDao(ActionDao actionDao) {
		this.actionDao = actionDao;
	}
	
	@Autowired
	public void setFactory(EntityFactory factory) {
		this.factory = factory;
	}
	
	@Before
	public void setup() {
		this.actionDao.deleteAll();
	}

	@After
	public void teardown() {
	this.actionDao.deleteAll();

	}

	@Test
	public void testCreateSimpleAction () throws Exception{
		// GIVEN the database is clean
		
		// WHEN we create a new action with type "dummy" and store it in DB
		String Type = "Test";
		ActionEntity newAction=factory.createNewAction
				("dummy ID", "dummy smartspace", Type, new Date(), "dummy mail", "dummy smartspace", new HashMap<>());
		// THEN the action is stored 
		assertThat(this.actionDao.readAll().contains(newAction));
		
	}
	
	
	
	
}
