package smartspace.logic;
import java.util.List;

import smartspace.data.ActionEntity;
import smartspace.layout.ActionBoundary;

public interface ActionService {
		public List<ActionEntity> importActions(String smartspace, String email,List<ActionEntity> actions); 
		public List<ActionEntity> ExportActions(String smartspace, String email,int size, int page);
		public ActionEntity invokeAction(String smartspace, String email,ActionEntity convertToEntity);	
}
