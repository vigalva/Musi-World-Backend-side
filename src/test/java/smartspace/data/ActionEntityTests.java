package smartspace.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import smartspace.data.ActionEntity;

public class ActionEntityTests {
	public void test_get_set_ActionId() {
		//GIVEN initialized ActionEntity & ActionId
		ActionEntity<String> actionEntity = new ActionEntity<>();
		String expectedActionId = "ActionId";
		
		actionEntity.setActionId(expectedActionId);
		if (!actionEntity.getActionId().equals(expectedActionId)) {
			throw new RuntimeException(
				"Error while testing setActionId() & getActionId(). "
				+ "Expected: " + expectedActionId 
				+ ". However, actual: " + actionEntity.getActionId());
		}
		
	}
	public void test_get_set_ActionSmartspace() {
		//GIVEN initialized ActionEntity & ActionSmartspace
		ActionEntity<String> actionEntity = new ActionEntity<>();
		String expectedActionSmartspace = "ActionSmartspace";
		
		actionEntity.setActionSmartspace(expectedActionSmartspace);
		if (!actionEntity.getActionSmartspace().equals(expectedActionSmartspace)) {
			throw new RuntimeException(
				"Error while testing setActionSmartspace() & getActionSmartspace(). "
				+ "Expected: " + expectedActionSmartspace 
				+ ". However, actual: " + actionEntity.getActionSmartspace());
		}
	}
	public void test_get_set_ElementId() {
		//GIVEN initialized ActionEntity & ElementId
		ActionEntity<String> actionEntity = new ActionEntity<>();
		String expectedElementId = "ElementId";
		
		actionEntity.setElementId(expectedElementId);
		if (!actionEntity.getElementId().equals(expectedElementId)) {
			throw new RuntimeException(
				"Error while testing setElementId() & getElementId(). "
				+ "Expected: " + expectedElementId
				+ ". However, actual: " + actionEntity.getElementId());
		}
		
	}	
	public void test_get_set_ActionType() {
		//GIVEN initialized ActionEntity & ActionType
		ActionEntity<String> actionEntity = new ActionEntity<>();
		String expectedActionType = "ActionType";
		
		actionEntity.setActionType(expectedActionType);
		if (!actionEntity.getActionType().equals(expectedActionType)) {
			throw new RuntimeException(
				"Error while testing setActionType() & getActionType(). "
				+ "Expected: " + expectedActionType 
				+ ". However, actual: " + actionEntity.getActionType());
		}
	}
	public void test_get_set_ElementSmartspace() {
		//GIVEN initialized ActionEntity & ElementSmartspace
		ActionEntity<String> actionEntity = new ActionEntity<>();
		String expectedElementSmartspace = "ElementSmartspace";
		
		actionEntity.setElementSmartspace(expectedElementSmartspace);
		if (!actionEntity.getElementSmartspace().equals(expectedElementSmartspace)) {
			throw new RuntimeException(
				"Error while testing setElementSmatspace() & getElementSmatspace(). "
				+ "Expected: " + expectedElementSmartspace 
				+ ". However, actual: " + actionEntity.getElementSmartspace());
		}
	}
	public void test_get_set_PlayerEmail() {
		//GIVEN initialized ActionEntity & PlayerEmail
		ActionEntity<String> actionEntity = new ActionEntity<>();
		String expectedPlayerEmail = "PlayerEmail@mail.com";
		
		actionEntity.setPlayerEmail(expectedPlayerEmail);
		if (!actionEntity.getPlayerEmail().equals(expectedPlayerEmail)) {
			throw new RuntimeException(
				"Error while testing setPlayerEmail() & getPlayerEmail(). "
				+ "Expected: " + expectedPlayerEmail 
				+ ". However, actual: " + actionEntity.getPlayerEmail());
		}
	}
	public void test_get_set_PlayerSmatspace() {
		//GIVEN initialized ActionEntity & PlayerSmatspace
		ActionEntity<String> actionEntity = new ActionEntity<>();
		String expectedPlayerSmatspace = "PlayerSmatspace";
		
		actionEntity.setPlayerSmatspace(expectedPlayerSmatspace);
		if (!actionEntity.getPlayerSmatspace().equals(expectedPlayerSmatspace)) {
			throw new RuntimeException(
				"Error while testing setPlayerSmatspace() & getPlayerSmatspace(). "
				+ "Expected: " + expectedPlayerSmatspace 
				+ ". However, actual: " + actionEntity.getPlayerSmatspace());
		}
	}
	public void test_get_set_MoreAttributes() {
		//GIVEN initialized ActionEntity & Map of Attributes
		ActionEntity<String> actionEntity = new ActionEntity<>();
		String expectedMoreAttributes = "MoreAttributes";
		
		Map<String, Object> expectedMap = new HashMap<>();
		expectedMap.put(expectedMoreAttributes, "ddd");
		actionEntity.setMoreAttributes(expectedMap);
		if (!actionEntity.getMoreAttributes().equals(expectedMap)) {
			throw new RuntimeException(
				"Error while testing setMoreAttributes() & getMoreAttributes(). "
				+ "Expected: " + expectedMoreAttributes
				+ ". However, actual: " + actionEntity.getMoreAttributes());
		}
	}
	public void test_get_set_Date() {
		//GIVEN initialized ActionEntity & Date
		ActionEntity<String> actionEntity = new ActionEntity<>();
		Date expectedDate = new Date();
		
		actionEntity.setCreationTimestamp(expectedDate);
		if (!actionEntity.getCreationTimestamp().equals(expectedDate)) {
			throw new RuntimeException(
				"Error while testing setCreationTimestamp() + getCreationTimestamp(). "
				+ "Expected: " + expectedDate 
				+ ". However, actual: " + actionEntity.getCreationTimestamp());
		}
	}
	public void test_get_set_Key() {
		//GIVEN initialized ActionEntity, ActionId & ActionSmartspace.
		ActionEntity<String> actionEntity = new ActionEntity<>();
		String expectedActionId = "ActionId";
		String expectedActionSmartspace = "ActionSmartspace";
		actionEntity.setActionId(expectedActionId);
		actionEntity.setActionSmartspace(expectedActionSmartspace);
		
		if (!actionEntity.getKey().equals(expectedActionId+'!'+expectedActionSmartspace)) {
			throw new RuntimeException(
				"Error while testing setKey() & getKey(). "
				+ "Expected: " + expectedActionId+'!'+expectedActionSmartspace
				+ ". However, actual: " + actionEntity.getKey());
		}
		actionEntity.setKey(expectedActionSmartspace+'!'+expectedActionId);
		if (!actionEntity.getKey().equals(expectedActionSmartspace+'!'+expectedActionId)) {
			throw new RuntimeException(
				"Error while testing getKey(). "
				+ "Expected: " + expectedActionSmartspace+'!'+expectedActionId
				+ ". However, actual: " + actionEntity.getKey());
		}
	}

	public void test() {
		test_get_set_ActionId();
		test_get_set_ActionSmartspace();
		test_get_set_ElementId();
		test_get_set_ActionType();
		test_get_set_ElementSmartspace();
		test_get_set_PlayerEmail();
		test_get_set_PlayerSmatspace();
		test_get_set_MoreAttributes();
		test_get_set_Date();
		test_get_set_Key();
		
	}
}
