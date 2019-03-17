package smartspace;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import smartspace.data.ElementEntity;
import smartspace.data.Location;

public class ElementEntityTest {
	public static void test() {
		
		ElementEntity<String> elementEntity = new ElementEntity<>();

		
		String expectedElementId = "9";
		elementEntity.setElementId(expectedElementId);
		if (!elementEntity.getElementId().equals(expectedElementId)) {
			throw new RuntimeException(
				"Error while testing setElementId() & getElementId(). "
				+ "Expected: " + expectedElementId
				+ ". However, actual: " + elementEntity.getElementId());
		}
		
		String expected = "string";
		elementEntity.setElementSmartspace(expected);
		if (!elementEntity.getElementSmartspace().equals(expected)) {
			throw new RuntimeException(
				"Error while testing setElementSmartspace() & getElementSmartspace(). "
				+ "Expected: " + expected 
				+ ". However, actual: " + elementEntity.getElementSmartspace());
		}

		
		elementEntity.setType(expected);
		if (!elementEntity.getType().equals(expected)) {
			throw new RuntimeException(
				"Error while testing setType() & getType(). "
				+ "Expected: " + expected 
				+ ". However, actual: " + elementEntity.getType());
		}
		
		Location expectedLocation = new Location();
		elementEntity.setLocation(expectedLocation);
		if (!elementEntity.getLocation().equals(expectedLocation)) {
			throw new RuntimeException(
				"Error while testing setLocation() & getLocation(). "
				+ "Expected: " + expectedLocation 
				+ ". However, actual: " + elementEntity.getLocation());
		}

		String expectedCreatorEmail = "CreatorEmail@mail.com";
		elementEntity.setCreatorEmail(expectedCreatorEmail);
		if (!elementEntity.getCreatorEmail().equals(expectedCreatorEmail)) {
			throw new RuntimeException(
				"Error while testing setCreatorEmail() & getCreatorEmail(). "
				+ "Expected: " + expectedCreatorEmail 
				+ ". However, actual: " + elementEntity.getCreatorEmail());
		}

		elementEntity.setCreatorSmartspace(expected);
		if (!elementEntity.getCreatorSmartspace().equals(expected)) {
			throw new RuntimeException(
				"Error while testing setCreatorSmartspace() & getCreatorSmartspace(). "
				+ "Expected: " + expected 
				+ ". However, actual: " + elementEntity.getCreatorSmartspace());
		}
		
		elementEntity.setName(expected);
		if (!elementEntity.getName().equals(expected)) {
			throw new RuntimeException(
				"Error while testing setName() & getName(). "
				+ "Expected: " + expected 
				+ ". However, actual: " + elementEntity.getName());
		}
		
		Map<String, Object> expectedMap = new HashMap<>();
		expectedMap.put(expected, "ddd");
		elementEntity.setMoreAttributes(expectedMap);
		if (!elementEntity.getMoreAttributes().equals(expectedMap)) {
			throw new RuntimeException(
				"Error while testing setMoreAttributes() & getMoreAttributes(). "
				+ "Expected: " + expected 
				+ ". However, actual: " + elementEntity.getMoreAttributes());
		}

		Date expectedDate = new Date();
		elementEntity.setCreationTimestamp(expectedDate);
		if (!elementEntity.getCreationTimestamp().equals(expectedDate)) {
			throw new RuntimeException(
				"Error while testing setCreationTimestamp() + getCreationTimestamp(). "
				+ "Expected: " + expectedDate 
				+ ". However, actual: " + elementEntity.getCreationTimestamp());
		}

		boolean expectedExpiraition = true;
		elementEntity.setExpired(expectedExpiraition);
		if (elementEntity.isExpired() != expectedExpiraition) {
			throw new RuntimeException(
				"Error while testing setCreationTimestamp() + getCreationTimestamp(). "
				+ "Expected: " + expectedExpiraition 
				+ ". However, actual: " + elementEntity.isExpired());
		}
		
		expectedExpiraition = false;
		elementEntity.setExpired(expectedExpiraition);
		if (elementEntity.isExpired() != expectedExpiraition) {
			throw new RuntimeException(
				"Error while testing setCreationTimestamp() + getCreationTimestamp(). "
				+ "Expected: " + expectedExpiraition 
				+ ". However, actual: " + elementEntity.isExpired());
		}

		//actionEntity.setKey(expected);
		if (!elementEntity.getKey().equals(expectedCreatorEmail+expectedElementId)) {
			throw new RuntimeException(
				"Error while testing getKey(). "
				+ "Expected: " + expectedCreatorEmail+expectedElementId 
				+ ". However, actual: " + elementEntity.getKey());
		}
	}
}
