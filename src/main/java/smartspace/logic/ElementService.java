package smartspace.logic;

import java.util.List;

import smartspace.data.ElementEntity;
import smartspace.layout.ElementBoundary;

public interface ElementService {

	public List<ElementEntity> importElements(ElementBoundary[] convertToEntity);
	public List<ElementEntity> ExportElements(int size, int page);

}
