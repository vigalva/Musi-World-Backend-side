package smartspace.data;

import smartspace.data.UserEntity;
import smartspace.data.UserRole;

public class UserEntityTests {

	public void test_get_set_Avatar() {
		//GIVEN initialized ActionEntity & Avatar
		UserEntity<String> userEntity = new UserEntity<>();
		String expectedAvatar = "string";
		
		userEntity.setAvatar(expectedAvatar);
		if (!userEntity.getAvatar().equals(expectedAvatar)) {
			throw new RuntimeException(
				"Error while testing setAvatar() & getAvatar(). "
				+ "Expected: " + expectedAvatar
				+ ". However, actual: " + userEntity.getAvatar());
		}
	}
	public void test_get_set_Points() {
		//GIVEN initialized ActionEntity & max
		UserEntity<String> userEntity = new UserEntity<>();
		long max = new Long(1000);
		
		for(long i = -max;i<max ;i++) {
			userEntity.setPoints(i);
			if (!(userEntity.getPoints()==i)) {
				throw new RuntimeException(
					"Error while testing setPoints() & getPoints(). "
					+ "Expected: " + i
					+ ". However, actual: " + userEntity.getPoints());
			}
		}
	}
	public void test_get_set_UserRole() {
		//GIVEN initialized ActionEntity
		UserEntity<String> userEntity = new UserEntity<>();
		
		UserRole expectedUserRole = UserRole.MANAGER;
		userEntity.setRole(expectedUserRole);
		if (!(userEntity.getRole()==expectedUserRole)) {
			throw new RuntimeException(
				"Error while testing setRole() & getRole(). "
				+ "Expected: " +expectedUserRole
				+ ". However, actual: " + userEntity.getRole());
		}
		expectedUserRole = UserRole.PLAYER;
		userEntity.setRole(expectedUserRole);
		if (!(userEntity.getRole()==expectedUserRole)) {
			throw new RuntimeException(
				"Error while testing setRole() & getRole(). "
				+ "Expected: " +expectedUserRole
				+ ". However, actual: " + userEntity.getRole());
		}
	}
	public void test_get_set_UserEmail() {
		//GIVEN initialized ActionEntity & UserEmail
		UserEntity<String> userEntity = new UserEntity<>();
		String expectedUserEmail = "userEmail@mail.com";
		
		userEntity.setUserEmail(expectedUserEmail);
		if (!(userEntity.getUserEmail().equals(expectedUserEmail))) {
			throw new RuntimeException(
				"Error while testing setUserEmail() & getUserEmail(). "
				+ "Expected: " +expectedUserEmail
				+ ". However, actual: " + userEntity.getUserEmail());
		}
	}
	public void test_get_set_Username() {
		//GIVEN initialized ActionEntity & User Name
		UserEntity<String> userEntity = new UserEntity<>();
		String expectedUsername = "Username";
		
		userEntity.setUsername(expectedUsername);
		if (!(userEntity.getUsername().equals(expectedUsername))) {
			throw new RuntimeException(
				"Error while testing setUsername() & getUsername(). "
				+ "Expected: " +expectedUsername
				+ ". However, actual: " + userEntity.getUsername());
		}
	}
	public void test_get_set_UserSmartspace() {
		//GIVEN initialized ActionEntity & UserName
		UserEntity<String> userEntity = new UserEntity<>();
		String expectedUserSmartspace = "UserSmartspace";
		
		userEntity.setUserSmatspace(expectedUserSmartspace);
		if (!(userEntity.getUserSmatspace().equals(expectedUserSmartspace))) {
			throw new RuntimeException(
				"Error while testing setUserSmatspacee() & getUserSmatspace(). "
				+ "Expected: " + expectedUserSmartspace
				+ ". However, actual: " + userEntity.getUserSmatspace());
		}
	}
	public void test_get_set_Key() {
		//GIVEN initialized ActionEntity & UserEmail
		UserEntity<String> userEntity = new UserEntity<>();
		String expectedUserSmartspace = "UserSmartspace";
		String expectedUserEmail = "userEmail@mail.com";

		userEntity.setUserEmail(expectedUserEmail);
		userEntity.setUserSmatspace(expectedUserSmartspace);
		if(!userEntity.getKey().equals(expectedUserSmartspace+'!'+expectedUserEmail)) {
			throw new RuntimeException(
					"Error while testing getKey(). "
					+ "Expected: " + expectedUserSmartspace+'!'+expectedUserEmail
					+ ". However, actual: " + userEntity.getKey());
		}
		
		userEntity.setKey(expectedUserEmail+'!'+expectedUserSmartspace);
		if(!userEntity.getKey().equals(expectedUserEmail+'!'+expectedUserSmartspace)) {
			throw new RuntimeException(
					"Error while testing setKey() & getKey(). "
					+ "Expected: " + expectedUserEmail+'!'+expectedUserSmartspace
					+ ". However, actual: " + userEntity.getKey());
		}
	}

	public void test() {
		test_get_set_Avatar();
		test_get_set_Points();
		test_get_set_UserRole();
		test_get_set_UserEmail();
		test_get_set_Username();
		test_get_set_UserSmartspace();
				
	}
}
