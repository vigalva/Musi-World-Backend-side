package smartspace.dao.rdb;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import smartspace.data.UserEntity;

public interface UserEntityCrud<K> extends CrudRepository<UserEntity<K>, String>{
	public UserEntityCrud<K> create(UserEntityCrud<K> message);
	public List<UserEntityCrud<K>> readAll();
	public Optional<UserEntityCrud<K>> readByKey (K key);
	public void update (UserEntityCrud<K> update);
	public void deleteAll();
}
