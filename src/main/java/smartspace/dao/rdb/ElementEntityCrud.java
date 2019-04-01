package smartspace.dao.rdb;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import smartspace.data.ElementEntity;

public interface ElementEntityCrud<K> extends CrudRepository<ElementEntity<K>, String> {
	public ElementEntity<K> create(ElementEntity<K> message);
	public List<ElementEntity<K>> readAll();
	public Optional<ElementEntity<K>> readByKey (K key);
	public void update (ElementEntity<K> update);
	public void deleteAll();
}
