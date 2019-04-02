package smartspace.data;

import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.HashMap;
import java.util.Map;

import smartspace.data.ElementEntity;
import smartspace.data.Location;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties= {"spring.profiles.active=default"})
public class ElementEntityTests {
	@Test
	public void test_get_set_ElementId() {
		//GIVEN initialized ElementEntity & ElementId
		ElementEntity elementEntity = new ElementEntity();
		String expectedElementId = "9";
		
		elementEntity.setElementId(expectedElementId);
		assertThat(elementEntity.getElementId()).isEqualTo(expectedElementId);
	}
	@Test
	public void test_get_set_ElementSmartspace() {
		//GIVEN initialized ElementEntity & ElementSmartspace
		ElementEntity elementEntity = new ElementEntity();
		String expectedElementSmartspace = "ElementSmartspace";
		
		elementEntity.setElementSmartspace(expectedElementSmartspace);
		assertThat(elementEntity.getElementSmartspace()).isEqualTo(expectedElementSmartspace);
	}
	@Test
	public void test_get_set_Type() {
		//GIVEN initialized ElementEntity & ElementType
		ElementEntity elementEntity = new ElementEntity();
		String expectedType = "Type";
		
		elementEntity.setType(expectedType);
		assertThat(elementEntity.getType()).isEqualTo(expectedType);
	}
	@Test
	public void test_get_set_Location() {
		//GIVEN initialized ElementEntity & Location
		ElementEntity elementEntity = new ElementEntity();
		Location expectedLocation = new Location();

		elementEntity.setLocation(expectedLocation);
		assertThat(elementEntity.getLocation()).isEqualTo(expectedLocation);
	}
	@Test
	public void test_get_set_CreatorEmail() {
		//GIVEN initialized ElementEntity & CreatorEmail
		ElementEntity elementEntity = new ElementEntity();
		String expectedCreatorEmail = "CreatorEmail@mail.com";
		
		elementEntity.setCreatorEmail(expectedCreatorEmail);
		assertThat(elementEntity.getCreatorEmail()).isEqualTo(expectedCreatorEmail);
	}
	@Test
	public void test_get_set_CreatorSmartspace() {
		//GIVEN initialized ElementEntity & expectedCreatorSmartspace
		ElementEntity elementEntity = new ElementEntity();
		String expectedCreatorSmartspace = "CreatorSmartspace";
		
		elementEntity.setCreatorSmartspace(expectedCreatorSmartspace);
		assertThat(elementEntity.getCreatorSmartspace()).isEqualTo(expectedCreatorSmartspace);
	}
	@Test
	public void test_get_set_Name() {
		//GIVEN initialized ElementEntity & Name
		ElementEntity elementEntity = new ElementEntity();
		String expectedName = "CreatorSmartspace";
		
		elementEntity.setName(expectedName);
		assertThat(elementEntity.getName()).isEqualTo(expectedName);
	}
	@Test
	public void test_get_set_MoreAttributes() {
		//GIVEN initialized ElementEntity & MoreAttributes
		ElementEntity elementEntity = new ElementEntity();
		String expectedMoreAttributes = "MoreAttributes";
		
		Map<String, Object> expectedMap = new HashMap<>();
		expectedMap.put(expectedMoreAttributes, "ddd");
		elementEntity.setMoreAttributes(expectedMap);
		assertThat(elementEntity.getMoreAttributes()).isEqualTo(expectedMap);
	}
	@Test
	public void test_get_set_Date() {
		//GIVEN initialized ElementEntity & Date
		ElementEntity elementEntity = new ElementEntity();
		Date expectedDate = new Date();
		
		elementEntity.setCreationTimestamp(expectedDate);
		assertThat(elementEntity.getCreationTimestamp()).isEqualTo(expectedDate);
	}
	@Test
	public void test_get_set_Expiraition() {
		//GIVEN initialized ElementEntity
		ElementEntity elementEntity = new ElementEntity();
		
		boolean expectedExpiraition = true;
		elementEntity.setExpired(expectedExpiraition);
		assertThat(elementEntity.isExpired()).isEqualTo(expectedExpiraition);
		
		expectedExpiraition = false;
		elementEntity.setExpired(expectedExpiraition);
		assertThat(elementEntity.isExpired()).isEqualTo(expectedExpiraition);
	}
	@Test
	public void test_get_set_Key() {
		//GIVEN initialized ElementEntity
		ElementEntity elementEntity = new ElementEntity();
		String expectedElementId = "9";
		String expectedElementSmartspace = "ElementSmartspace";
		
		elementEntity.setElementId(expectedElementId);
		elementEntity.setElementSmartspace(expectedElementSmartspace);
		assertThat(elementEntity.getKey()).isEqualTo(expectedElementSmartspace+'!'+expectedElementId);
		
		elementEntity.setKey(expectedElementId+'!'+expectedElementSmartspace);
		assertThat(elementEntity.getKey()).isEqualTo(expectedElementId+'!'+expectedElementSmartspace);
	}

}
