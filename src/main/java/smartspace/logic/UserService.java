package smartspace.logic;

import java.util.List;

import smartspace.data.UserEntity;
import smartspace.layout.UserBoundary;

public interface UserService {
	public List<UserEntity> importUsers(String smartspace, String email,List<UserEntity> entities);
	public List<UserEntity> ExportUsers(String smartspace, String email,int size, int page);
	public UserEntity createUser(UserEntity convertToEntity);
	public UserEntity loginAndRetriveDetails(String smartspace, String email,UserEntity convertToEntity);
	public void updateUser(String smartspace, String email,UserEntity convertToEntity);

}
