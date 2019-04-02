package smartspace.data;

import smartspace.data.UserEntity;
import smartspace.data.UserRole;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties= {"spring.profiles.active=default"})
public class UserEntityTests {
	@Test
	public void test_get_set_Avatar() {
		//GIVEN initialized ActionEntity & Avatar
		UserEntity userEntity = new UserEntity();
		String expectedAvatar = "string";
		
		userEntity.setAvatar(expectedAvatar);
		assertThat(userEntity.getAvatar()).isEqualTo(expectedAvatar);
	}
	@Test
	public void test_get_set_Points() {
		//GIVEN initialized ActionEntity & max
		UserEntity userEntity = new UserEntity();
		long max = new Long(1000);
		
		for(long i = -max;i<max ;i++) {
			userEntity.setPoints(i);
			assertThat(userEntity.getPoints()).isEqualTo(i);
		}
	}
	@Test
	public void test_get_set_UserRole() {
		//GIVEN initialized ActionEntity
		UserEntity userEntity = new UserEntity();
		
		UserRole expectedUserRole = UserRole.MANAGER;
		userEntity.setRole(expectedUserRole);
		assertThat(userEntity.getRole()).isEqualTo(expectedUserRole);

		expectedUserRole = UserRole.PLAYER;
		userEntity.setRole(expectedUserRole);
		assertThat(userEntity.getRole()).isEqualTo(expectedUserRole);
	}
	@Test
	public void test_get_set_UserEmail() {
		//GIVEN initialized ActionEntity & UserEmail
		UserEntity userEntity = new UserEntity();
		String expectedUserEmail = "userEmail@mail.com";
		
		userEntity.setUserEmail(expectedUserEmail);
		assertThat(userEntity.getUserEmail()).isEqualTo(expectedUserEmail);
	}
	@Test
	public void test_get_set_Username() {
		//GIVEN initialized ActionEntity & User Name
		UserEntity userEntity = new UserEntity();
		String expectedUsername = "Username";
		
		userEntity.setUsername(expectedUsername);
		assertThat(userEntity.getUsername()).isEqualTo(expectedUsername);
	}
	@Test
	public void test_get_set_UserSmartspace() {
		//GIVEN initialized ActionEntity & UserName
		UserEntity userEntity = new UserEntity();
		String expectedUserSmartspace = "UserSmartspace";
		
		userEntity.setUserSmatspace(expectedUserSmartspace);
		assertThat(userEntity.getUserSmatspace()).isEqualTo(expectedUserSmartspace);
	}
	@Test
	public void test_get_set_Key() {
		//GIVEN initialized ActionEntity & UserEmail
		UserEntity userEntity = new UserEntity();
		String expectedUserSmartspace = "UserSmartspace";
		String expectedUserEmail = "userEmail@mail.com";

		userEntity.setUserEmail(expectedUserEmail);
		userEntity.setUserSmatspace(expectedUserSmartspace);
		assertThat(userEntity.getKey()).isEqualTo(expectedUserSmartspace+'!'+expectedUserEmail);
		
		userEntity.setKey(expectedUserEmail+'!'+expectedUserSmartspace);
		assertThat(userEntity.getKey()).isEqualTo(expectedUserEmail+'!'+expectedUserSmartspace);
	}
}
