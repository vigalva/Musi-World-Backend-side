package smartspace.logic;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import smartspace.dao.AdvancedActionDao;
import smartspace.data.ActionEntity;
import smartspace.layout.ActionBoundary;
@Service

public class ActionServiceImpl implements ActionService{
private AdvancedActionDao<String> ActionsDao;
	
	public ActionServiceImpl(AdvancedActionDao<String> ActionsDao) {
		this.ActionsDao = ActionsDao;
	}
	
	@Transactional
	@Override
	public List<ActionEntity> importActions(ActionBoundary[] convertToEntity) {
			List<ActionEntity> actionEntites=new ArrayList<ActionEntity>();
			
		for (ActionBoundary boundary : convertToEntity) {
			if(boundary.convertToEntity().getActionSmartspace().equals("inbala1"))
					throw new RuntimeException("You are trying to import actions from your own project-can't do that");
			else actionEntites.add(this.ActionsDao.create(boundary.convertToEntity()));
		}
		return actionEntites;	
	}

	@Override
	public List<ActionEntity> ExportActions(int size, int page) {
		return this.ActionsDao.readAll(size, page);
	}
}
