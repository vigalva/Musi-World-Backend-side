package smartspace.logic;
import java.util.List;

import smartspace.data.ActionEntity;
import smartspace.layout.ActionBoundary;

public interface ActionService {
		public List<ActionEntity> importActions(List<ActionEntity> actions); 
		public List<ActionEntity> ExportActions(int size, int page);
		public ActionEntity invokeAction(ActionEntity convertToEntity);	
}
