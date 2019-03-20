package smartspace.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import smartspace.data.ActionEntity;

public class ActionEntityTest {
	public static void test() {
		
		ActionEntity<String> actionEntity = new ActionEntity<>();

		String expectedActionId = "ElementId";
		actionEntity.setActionId(expectedActionId);
		if (!actionEntity.getActionId().equals(expectedActionId)) {
			throw new RuntimeException(
				"Error while testing setActionId() & getActionId(). "
				+ "Expected: " + expectedActionId 
				+ ". However, actual: " + actionEntity.getActionId());
		}
		
		String expectedActionSmartspace = "smart";
		actionEntity.setActionSmartspace(expectedActionSmartspace);
		if (!actionEntity.getActionSmartspace().equals(expectedActionSmartspace)) {
			throw new RuntimeException(
				"Error while testing setActionSmartspace() & getActionSmartspace(). "
				+ "Expected: " + expectedActionSmartspace 
				+ ". However, actual: " + actionEntity.getActionSmartspace());
		}
		
		String expected = "string";
		actionEntity.setElementId(expected);
		if (!actionEntity.getElementId().equals(expected)) {
			throw new RuntimeException(
				"Error while testing setElementId() & getElementId(). "
				+ "Expected: " + expected
				+ ". However, actual: " + actionEntity.getElementId());
		}
		

		
		actionEntity.setActionType(expected);
		if (!actionEntity.getActionType().equals(expected)) {
			throw new RuntimeException(
				"Error while testing setActionType() & getActionType(). "
				+ "Expected: " + expected 
				+ ". However, actual: " + actionEntity.getActionType());
		}
		

		actionEntity.setElementSmartspace(expected);
		if (!actionEntity.getElementSmartspace().equals(expected)) {
			throw new RuntimeException(
				"Error while testing setElementSmatspace() & getElementSmatspace(). "
				+ "Expected: " + expected 
				+ ". However, actual: " + actionEntity.getElementSmartspace());
		}

		String expectedPlayerEmail = "PlayerEmail@mail.com";
		actionEntity.setPlayerEmail(expectedPlayerEmail);
		if (!actionEntity.getPlayerEmail().equals(expectedPlayerEmail)) {
			throw new RuntimeException(
				"Error while testing setPlayerEmail() & getPlayerEmail(). "
				+ "Expected: " + expectedPlayerEmail 
				+ ". However, actual: " + actionEntity.getPlayerEmail());
		}
		
		actionEntity.setPlayerSmatspace(expected);
		if (!actionEntity.getPlayerSmatspace().equals(expected)) {
			throw new RuntimeException(
				"Error while testing setPlayerSmatspace() & getPlayerSmatspace(). "
				+ "Expected: " + expected 
				+ ". However, actual: " + actionEntity.getPlayerSmatspace());
		}
		
		Map<String, Object> expectedMap = new HashMap<>();
		expectedMap.put(expected, "ddd");
		actionEntity.setMoreAttributes(expectedMap);
		if (!actionEntity.getMoreAttributes().equals(expectedMap)) {
			throw new RuntimeException(
				"Error while testing setMoreAttributes() & getMoreAttributes(). "
				+ "Expected: " + expected 
				+ ". However, actual: " + actionEntity.getMoreAttributes());
		}
		
		Date expectedDate = new Date();
		actionEntity.setCreationTimestamp(expectedDate);
		if (!actionEntity.getCreationTimestamp().equals(expectedDate)) {
			throw new RuntimeException(
				"Error while testing setCreationTimestamp() + getCreationTimestamp(). "
				+ "Expected: " + expectedDate 
				+ ". However, actual: " + actionEntity.getCreationTimestamp());
		}

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
}
