package smartspace.logic;

import java.util.List;

import smartspace.data.ElementEntity;
import smartspace.layout.ElementBoundary;

public interface ElementService {

	public List<ElementEntity> importElements(List<ElementEntity> convertToEntity);
	public List<ElementEntity> ExportElements(int size, int page);
	public ElementEntity createElement(ElementEntity entity);

}
