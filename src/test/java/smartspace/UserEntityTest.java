package smartspace;

import smartspace.data.UserEntity;
import smartspace.data.UserRole;

public class UserEntityTest {
	public static void test() {
		UserEntity<String> userEntity = new UserEntity<>();
		
		String expected = "string";
		userEntity.setAvatar(expected);
		if (!userEntity.getAvatar().equals(expected)) {
			throw new RuntimeException(
				"Error while testing setAvatar() & getAvatar(). "
				+ "Expected: " + expected
				+ ". However, actual: " + userEntity.getAvatar());
		}
		
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
		
		String expectedUserEmail = "userEmail@mail.com";
		userEntity.setUserEmail(expectedUserEmail);
		if (!(userEntity.getUserEmail().equals(expectedUserEmail))) {
			throw new RuntimeException(
				"Error while testing setUserEmail() & getUserEmail(). "
				+ "Expected: " +expectedUserEmail
				+ ". However, actual: " + userEntity.getUserEmail());
		}
		
		userEntity.setUsername(expected);
		if (!(userEntity.getUsername().equals(expected))) {
			throw new RuntimeException(
				"Error while testing setUsername() & getUsername(). "
				+ "Expected: " +expected
				+ ". However, actual: " + userEntity.getUsername());
		}
		
		String expectedUserSmartspace = "UserSmartspace";
		userEntity.setUserSmatspace(expectedUserSmartspace);
		if (!(userEntity.getUserSmatspace().equals(expectedUserSmartspace))) {
			throw new RuntimeException(
				"Error while testing setUserSmatspacee() & getUserSmatspace(). "
				+ "Expected: " + expectedUserSmartspace
				+ ". However, actual: " + userEntity.getUserSmatspace());
		}
		
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
}
