package smartspace.dao.rdb;

import java.util.List;
import java.util.Optional;

import smartspace.data.ActionEntity;

public class RdbCrudActionEntity<K> implements ActionEntityCrud<K>{

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(ActionEntity<K> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends ActionEntity<K>> arg0) {
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
	public Iterable<ActionEntity<K>> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ActionEntity<K>> findAllById(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ActionEntity<K>> findById(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ActionEntity<K>> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ActionEntity<K>> Iterable<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionEntity<K> create(ActionEntity<K> message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActionEntity<K>> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ActionEntity<K>> readByKey(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ActionEntity<K> update) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

}
