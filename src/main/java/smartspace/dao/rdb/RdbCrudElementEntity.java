package smartspace.dao.rdb;

import java.util.List;
import java.util.Optional;

import smartspace.data.ElementEntity;

public class RdbCrudElementEntity<K> implements ElementEntityCrud<K> {

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(ElementEntity<K> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends ElementEntity<K>> arg0) {
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
	public Iterable<ElementEntity<K>> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ElementEntity<K>> findAllById(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ElementEntity<K>> findById(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ElementEntity<K>> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ElementEntity<K>> Iterable<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ElementEntity<K> create(ElementEntity<K> message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElementEntity<K>> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ElementEntity<K>> readByKey(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ElementEntity<K> update) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

}
