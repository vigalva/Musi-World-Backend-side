package smartspace.dao.rdb;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import smartspace.data.ActionEntity;

public interface ActionEntityCrud<K> extends CrudRepository<ActionEntity<K>, String> {
	public ActionEntity<K> create(ActionEntity<K> message);
	public List<ActionEntity<K>> readAll();
	public Optional<ActionEntity<K>> readByKey (K key);
	public void update (ActionEntity<K> update);
	public void deleteAll();
}
