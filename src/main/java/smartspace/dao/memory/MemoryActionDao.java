package smartspace.dao.memory;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import smartspace.dao.ActionDao;
import smartspace.data.ActionEntity;

//@Repository
public class MemoryActionDao implements ActionDao {
	
	private Map<String, ActionEntity> memory;
	private String actionId;
	private String smartspace;
	
	public MemoryActionDao() {
		this.memory = Collections.synchronizedSortedMap(new TreeMap<>());
		
	}
	
	@Value("${smartspace.name:smartspace}")
	public void setSmartspace(String smartspace) {
		this.smartspace = smartspace;
	}
	
	@Value("${actionId:0}")
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	
	@Override
	public ActionEntity create(ActionEntity actionEntity) {
		actionEntity.setKey(smartspace + "!" + actionId);
		this.memory.put(actionEntity.getKey(),actionEntity);
		return actionEntity;
	}

	@Override
	public List<ActionEntity> readAll() {
		return new ArrayList<>(this.memory.values());
	}

	@Override
	public void deleteAll() {
		memory.clear();

	}

}
