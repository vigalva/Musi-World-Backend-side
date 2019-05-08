package smartspace.logic;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import smartspace.dao.AdvancedActionDao;
import smartspace.dao.AdvancedElementDao;
import smartspace.data.ActionEntity;
import smartspace.data.ElementEntity;
import smartspace.layout.ActionBoundary;
@Service

public class ActionServiceImpl implements ActionService{
private AdvancedActionDao<String> ActionsDao;
private AdvancedElementDao<String> ElementDao;

	
	public ActionServiceImpl(AdvancedActionDao<String> ActionsDao,AdvancedElementDao<String> ElementDao) {
		this.ActionsDao = ActionsDao;
		this.ElementDao=ElementDao;
		
	}
	
	
	@Transactional
	@Override
	public List<ActionEntity> importActions(List<ActionEntity> actions) {
			List<ActionEntity> actionEntites=new ArrayList<ActionEntity>();
			List<ElementEntity>elements= ElementDao.readAll();
		for (ActionEntity action : actions) {
			for (ElementEntity element:elements ) {
				if (!element.getElementSmartspace().equals(action.getElementSmartspace())
						&&!element.getElementId().equals(action.getElementId()))
					throw new RuntimeException("You are trying to import actions from element who wasn't been imported");

			}
			if(action.getActionSmartspace().equals("inbala1"))
					
					throw new RuntimeException("You are trying to import actions from your own project-can't do that");
			else actionEntites.add(this.ActionsDao.create(action));
		}
		return actionEntites;	
	}

	@Override
	public List<ActionEntity> ExportActions(int size, int page) {
		return this.ActionsDao.readAll(size, page);
	}
}
