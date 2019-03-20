package smartspace.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import smartspace.data.ElementEntity;
import smartspace.data.Location;

public class ElementEntityTests {
	public void test_get_set_ElementId() {
		//GIVEN initialized ElementEntity & ElementId
		ElementEntity<String> elementEntity = new ElementEntity<>();
		String expectedElementId = "9";
		
		elementEntity.setElementId(expectedElementId);
		if (!elementEntity.getElementId().equals(expectedElementId)) {
			throw new RuntimeException(
				"Error while testing setElementId() & getElementId(). "
				+ "Expected: " + expectedElementId
				+ ". However, actual: " + elementEntity.getElementId());
		}
	}
	public void test_get_set_ElementSmartspace() {
		//GIVEN initialized ElementEntity & ElementSmartspace
		ElementEntity<String> elementEntity = new ElementEntity<>();
		String expectedElementSmartspace = "ElementSmartspace";
		
		elementEntity.setElementSmartspace(expectedElementSmartspace);
		if (!elementEntity.getElementSmartspace().equals(expectedElementSmartspace)) {
			throw new RuntimeException(
				"Error while testing setElementSmartspace() & getElementSmartspace(). "
				+ "Expected: " + expectedElementSmartspace 
				+ ". However, actual: " + elementEntity.getElementSmartspace());
		}
	}
	public void test_get_set_Type() {
		//GIVEN initialized ElementEntity & ElementType
		ElementEntity<String> elementEntity = new ElementEntity<>();
		String expectedType = "Type";
		
		elementEntity.setType(expectedType);
		if (!elementEntity.getType().equals(expectedType)) {
			throw new RuntimeException(
				"Error while testing setType() & getType(). "
				+ "Expected: " + expectedType 
				+ ". However, actual: " + elementEntity.getType());
		}
		
	}
	public void test_get_set_Location() {
		//GIVEN initialized ElementEntity & Location
		ElementEntity<String> elementEntity = new ElementEntity<>();
		Location expectedLocation = new Location();

		elementEntity.setLocation(expectedLocation);
		if (!elementEntity.getLocation().equals(expectedLocation)) {
			throw new RuntimeException(
				"Error while testing setLocation() & getLocation(). "
				+ "Expected: " + expectedLocation 
				+ ". However, actual: " + elementEntity.getLocation());
		}		
	}
	public void test_get_set_CreatorEmail() {
		//GIVEN initialized ElementEntity & CreatorEmail
		ElementEntity<String> elementEntity = new ElementEntity<>();
		String expectedCreatorEmail = "CreatorEmail@mail.com";
		
		elementEntity.setCreatorEmail(expectedCreatorEmail);
		if (!elementEntity.getCreatorEmail().equals(expectedCreatorEmail)) {
			throw new RuntimeException(
				"Error while testing setCreatorEmail() & getCreatorEmail(). "
				+ "Expected: " + expectedCreatorEmail 
				+ ". However, actual: " + elementEntity.getCreatorEmail());
		}	
	}
	public void test_get_set_CreatorSmartspace() {
		//GIVEN initialized ElementEntity & expectedCreatorSmartspace
		ElementEntity<String> elementEntity = new ElementEntity<>();
		String expectedCreatorSmartspace = "CreatorSmartspace";
		
		elementEntity.setCreatorSmartspace(expectedCreatorSmartspace);
		if (!elementEntity.getCreatorSmartspace().equals(expectedCreatorSmartspace)) {
			throw new RuntimeException(
				"Error while testing setCreatorSmartspace() & getCreatorSmartspace(). "
				+ "Expected: " + expectedCreatorSmartspace 
				+ ". However, actual: " + elementEntity.getCreatorSmartspace());
		}
	}
	public void test_get_set_Name() {
		//GIVEN initialized ElementEntity & Name
		ElementEntity<String> elementEntity = new ElementEntity<>();
		String expectedName = "CreatorSmartspace";
		
		elementEntity.setName(expectedName);
		if (!elementEntity.getName().equals(expectedName)) {
			throw new RuntimeException(
				"Error while testing setName() & getName(). "
				+ "Expected: " + expectedName
				+ ". However, actual: " + elementEntity.getName());
		}
	}
	public void test_get_set_MoreAttributes() {
		//GIVEN initialized ElementEntity & MoreAttributes
		ElementEntity<String> elementEntity = new ElementEntity<>();
		String expectedMoreAttributes = "MoreAttributes";
		
		Map<String, Object> expectedMap = new HashMap<>();
		expectedMap.put(expectedMoreAttributes, "ddd");
		elementEntity.setMoreAttributes(expectedMap);
		if (!elementEntity.getMoreAttributes().equals(expectedMap)) {
			throw new RuntimeException(
				"Error while testing setMoreAttributes() & getMoreAttributes(). "
				+ "Expected: " + expectedMoreAttributes 
				+ ". However, actual: " + elementEntity.getMoreAttributes());
		}
	}
	public void test_get_set_Date() {
		//GIVEN initialized ElementEntity & Date
		ElementEntity<String> elementEntity = new ElementEntity<>();
		Date expectedDate = new Date();
		
		elementEntity.setCreationTimestamp(expectedDate);
		if (!elementEntity.getCreationTimestamp().equals(expectedDate)) {
			throw new RuntimeException(
				"Error while testing setCreationTimestamp() + getCreationTimestamp(). "
				+ "Expected: " + expectedDate 
				+ ". However, actual: " + elementEntity.getCreationTimestamp());
		}
	}
	public void test_get_set_Expiraition() {
		//GIVEN initialized ElementEntity
		ElementEntity<String> elementEntity = new ElementEntity<>();
		
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
	}
	public void test_get_set_Key() {
		//GIVEN initialized ElementEntity
		ElementEntity<String> elementEntity = new ElementEntity<>();
		String expectedElementId = "9";
		String expectedElementSmartspace = "ElementSmartspace";
		
		elementEntity.setElementId(expectedElementId);
		elementEntity.setElementSmartspace(expectedElementSmartspace);
		if (!elementEntity.getKey().equals(expectedElementSmartspace+'!'+expectedElementId)) {
			throw new RuntimeException(
				"Error while testing getKey(). "
				+ "Expected: " + expectedElementSmartspace+'!'+expectedElementId
				+ ". However, actual: " + elementEntity.getKey());
		}
		
		elementEntity.setKey(expectedElementId+'!'+expectedElementSmartspace);
		if (!elementEntity.getKey().equals(expectedElementId+'!'+expectedElementSmartspace)) {
			throw new RuntimeException(
				"Error while testing setKey() & getKey(). "
				+ "Expected: " + expectedElementId+'!'+expectedElementSmartspace
				+ ". However, actual: " + elementEntity.getKey());
		}
	}

	public void test() {
		test_get_set_ElementId();
		test_get_set_ElementSmartspace();
		test_get_set_Type();
		test_get_set_Location();
		test_get_set_CreatorEmail();
		test_get_set_CreatorSmartspace();
		test_get_set_Name();
		test_get_set_MoreAttributes();
		test_get_set_Date();
		test_get_set_Expiraition();
		test_get_set_Key();
	}
}
