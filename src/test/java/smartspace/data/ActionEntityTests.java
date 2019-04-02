package smartspace.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties= {"spring.profiles.active=default"})
public class ActionEntityTests {
	
	@Test
	public void test_get_set_ActionId() {
		//GIVEN initialized ActionEntity & ActionId
		ActionEntity actionEntity = new ActionEntity();
		String expectedActionId = "ActionId";
		
		actionEntity.setActionId(expectedActionId);
		assertThat(actionEntity.getActionId()).isEqualTo(expectedActionId);
		
	}
	
	@Test
	public void test_get_set_ActionSmartspace() {
		//GIVEN initialized ActionEntity & ActionSmartspace
		ActionEntity actionEntity = new ActionEntity();
		String expectedActionSmartspace = "ActionSmartspace";
		
		actionEntity.setActionSmartspace(expectedActionSmartspace);

		assertThat(actionEntity.getActionSmartspace()).isEqualTo(expectedActionSmartspace);
		}
	
	@Test
	public void test_get_set_ElementId() {
		//GIVEN initialized ActionEntity & ElementId
		ActionEntity actionEntity = new ActionEntity();
		String expectedElementId = "ElementId";
		
		actionEntity.setElementId(expectedElementId);
		assertThat(actionEntity.getElementId()).isEqualTo(expectedElementId);
		
	}	

	@Test
	public void test_get_set_ActionType() {
		//GIVEN initialized ActionEntity & ActionType
		ActionEntity actionEntity = new ActionEntity();
		String expectedActionType = "ActionType";
		
		actionEntity.setActionType(expectedActionType);
		assertThat(actionEntity.getActionType()).isEqualTo(expectedActionType);
	}

	@Test
	public void test_get_set_ElementSmartspace() {
		//GIVEN initialized ActionEntity & ElementSmartspace
		ActionEntity actionEntity = new ActionEntity();
		String expectedElementSmartspace = "ElementSmartspace";
		
		actionEntity.setElementSmartspace(expectedElementSmartspace);
		assertThat(actionEntity.getElementSmartspace()).isEqualTo(expectedElementSmartspace);
	}

	@Test
	public void test_get_set_PlayerEmail() {
		//GIVEN initialized ActionEntity & PlayerEmail
		ActionEntity actionEntity = new ActionEntity();
		String expectedPlayerEmail = "PlayerEmail@mail.com";
		
		actionEntity.setPlayerEmail(expectedPlayerEmail);
		assertThat(actionEntity.getPlayerEmail()).isEqualTo(expectedPlayerEmail);
	}

	@Test
	public void test_get_set_PlayerSmatspace() {
		//GIVEN initialized ActionEntity & PlayerSmatspace
		ActionEntity actionEntity = new ActionEntity();
		String expectedPlayerSmatspace = "PlayerSmatspace";
		
		actionEntity.setPlayerSmatspace(expectedPlayerSmatspace);
		assertThat(actionEntity.getPlayerSmatspace()).isEqualTo(expectedPlayerSmatspace);
	}

	@Test
	public void test_get_set_MoreAttributes() {
		//GIVEN initialized ActionEntity & Map of Attributes
		ActionEntity actionEntity = new ActionEntity();
		String expectedMoreAttributes = "MoreAttributes";
		
		Map<String, Object> expectedMap = new HashMap<>();
		expectedMap.put(expectedMoreAttributes, "ddd");
		actionEntity.setMoreAttributes(expectedMap);
		assertThat(actionEntity.getMoreAttributes()).isEqualTo(expectedMap);
	}

	@Test
	public void test_get_set_Date() {
		//GIVEN initialized ActionEntity & Date
		ActionEntity actionEntity = new ActionEntity();
		Date expectedDate = new Date();
		
		actionEntity.setCreationTimestamp(expectedDate);
		assertThat(actionEntity.getCreationTimestamp()).isEqualTo(expectedDate);
	}

	@Test
	public void test_get_set_Key() {
		//GIVEN initialized ActionEntity, ActionId & ActionSmartspace.
		ActionEntity actionEntity = new ActionEntity();
		String expectedActionId = "ActionId";
		String expectedActionSmartspace = "ActionSmartspace";
		actionEntity.setActionId(expectedActionId);
		actionEntity.setActionSmartspace(expectedActionSmartspace);

		assertThat(actionEntity.getKey()).isEqualTo(expectedActionId+'!'+expectedActionSmartspace);
		
		actionEntity.setKey(expectedActionSmartspace+'!'+expectedActionId);
		assertThat(actionEntity.getKey()).isEqualTo(expectedActionSmartspace+'!'+expectedActionId);

	}

}
