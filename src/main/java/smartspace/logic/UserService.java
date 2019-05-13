package smartspace.logic;

import java.util.List;

import smartspace.data.UserEntity;
import smartspace.layout.UserBoundary;

public interface UserService {
	public List<UserEntity> importUsers(List<UserEntity> entities);
	public List<UserEntity> ExportUsers(int size, int page);
	public UserEntity createUser(UserEntity convertToEntity);
	public UserEntity loginAndRetriveDetails(UserEntity convertToEntity);
	public void updateUser(UserEntity convertToEntity);

}
