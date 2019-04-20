package smartspace.dao.rdb;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
//import smartspace.dao.ActionDao;
import smartspace.data.ActionEntity;

import smartspace.data.util.EntityFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = { "spring.profiles.active=default" })
public class RdbActionIntegrationTests<K> {
	private ActionDao actionDao;
	private EntityFactory factory;

	@Autowired
	public void setActionDao(ActionDao actionDao) {
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
	// Method to check creation of 1 action
	public void testCreateAction() throws Exception {
		// GIVEN the database is clean

		// WHEN we create a new action with type Test and store it in DB
		String Type = "Test";
		ActionEntity newAction = factory.createNewAction("dummy ID", "dummy smartspace", Type, new Date(), "dummy mail",
				"dummy smartspace", new HashMap<>());
		this.actionDao.create(newAction);
		
		assertThat(this.actionDao.readAll()).usingElementComparatorOnFields("key").contains(newAction);// this test
																										// works
		teardown();

	}

	@Test
	// Test to check readAll method and deleteAll
	public void testCreateManyActionsAndDeletion() throws Exception {
		// GIVEN the database is clean

		// WHEN we create 20 actions with type Test and store it in DB
		String Type = "Test";
		IntStream
				.range(1, 21).mapToObj(i -> this.factory.createNewAction("dummy id", "dummy smartspace", Type,
						new Date(), "dummy mail", "dummy smartspace", new HashMap<String, Object>()))
				.map(this.actionDao::create);
		List<ActionEntity> actions = this.actionDao.readAll();

		// System.err.println(newAction.getKey());
		// THEN the actions is stored with uniqueid
		assertThat(this.actionDao.readAll()).containsAll(actions);

		this.actionDao.deleteAll();
		assertThat(this.actionDao.readAll()).isEmpty();
	}
}
