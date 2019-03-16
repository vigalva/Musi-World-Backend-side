package smartspace.dao;

import java.util.List;
import java.util.Optional;

import smartspace.data.ElementEntity;
import smartspace.data.UserEntity;

public interface ElementDao<ElementKey>{
	public ElementEntity create(ElementEntity elementEntity);
	public Optional<ElementEntity> readById(ElementKey elementKey);
	public List<ElementEntity> readAll();
	public void update(ElementEntity elementEntity);
	public void deleteByKey(ElementKey elementKey);
	public void delete(ElementEntity elementEntity);
	public void deleteAll();
}
