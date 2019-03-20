package smartspace.dao.memory;

import java.util.List;
import java.util.Optional;

import smartspace.dao.UserDao;
import smartspace.data.UserEntity;

public class MemoryUserDao<UserKey> implements UserDao<UserKey>{

	@Override
	public UserEntity create(UserEntity userEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<UserEntity> readById(UserKey userKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserEntity> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(UserEntity userEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

}
