package smartspace.logic;

import java.util.List;

import smartspace.data.ElementEntity;

public interface ElementService {

	public ElementEntity importElements(ElementEntity[] convertToEntity);
	public List<ElementEntity> ExportElements(int size, int page);

}
