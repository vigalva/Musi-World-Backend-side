package smartspace.dao;

import java.util.List;
import java.util.Optional;

import smartspace.data.UserEntity;

public interface UserDao<UserKey> {
	public UserEntity<String> create(UserEntity<String> userEntity);
	public Optional<UserEntity<String>> readById(String userKey);
	public List<UserEntity<String>> readAll();
	public void update(UserEntity<String> userEntity);
	public void deleteAll();
}
