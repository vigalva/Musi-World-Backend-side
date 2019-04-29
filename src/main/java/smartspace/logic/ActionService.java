package smartspace.logic;
import java.util.List;

import smartspace.data.ActionEntity;
import smartspace.layout.ActionBoundary;

public interface ActionService {
		public List<ActionEntity> importActions(ActionBoundary[] convertToEntity);
		public List<ActionEntity> ExportActions(int size, int page);	
}
