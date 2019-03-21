package smartspace.dao;

import java.util.List;
import java.util.Optional;

import smartspace.data.ElementEntity;
import smartspace.data.UserEntity;

public interface ElementDao<ElementKey>{
	public ElementEntity<String> create(ElementEntity<String> elementEntity);
	public Optional<ElementEntity<String>> readById(String elementkey);
	public List<ElementEntity<String>> readAll();
	public void update(ElementEntity<String> elementEntity);
	public void deleteByKey(String  elementKey);
	public void delete(ElementEntity<String> elementEntity);
	public void deleteAll();
}
