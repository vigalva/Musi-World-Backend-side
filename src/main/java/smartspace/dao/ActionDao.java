package smartspace.dao;

import java.util.List;

import smartspace.data.ActionEntity;

public interface ActionDao {
	public ActionEntity<String> create(ActionEntity<String> actionEntity);
	public List<ActionEntity<String>> readAll();
	public void deleteAll();

}
