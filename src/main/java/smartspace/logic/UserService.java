package smartspace.logic;

import java.util.List;

import smartspace.data.UserEntity;
import smartspace.layout.UserBoundary;

public interface UserService {
	public List<UserEntity> importUsers(UserBoundary[] convertToEntity);
	public List<UserEntity> ExportUsers(int size, int page);

}
