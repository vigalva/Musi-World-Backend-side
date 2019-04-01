package smartspace.dao.rdb;

import java.util.List;
import java.util.Optional;

import smartspace.data.UserEntity;

public class RdbCrudUserEntity<K> implements UserEntityCrud<K>{

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(UserEntity<K> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends UserEntity<K>> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsById(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<UserEntity<K>> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<UserEntity<K>> findAllById(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<UserEntity<K>> findById(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends UserEntity<K>> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends UserEntity<K>> Iterable<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntityCrud<K> create(UserEntityCrud<K> message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserEntityCrud<K>> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<UserEntityCrud<K>> readByKey(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(UserEntityCrud<K> update) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

}
