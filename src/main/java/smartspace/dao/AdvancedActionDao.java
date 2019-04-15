package smartspace.dao;

import java.util.List;

import smartspace.data.ActionEntity;

public interface AdvancedActionDao<K> extends ActionDao{
	
	public List<ActionEntity> readAll(int size, int page);
	public List<ActionEntity> readAll(String sortBy, int size, int page);

}
