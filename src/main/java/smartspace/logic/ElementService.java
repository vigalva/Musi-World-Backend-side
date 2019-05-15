package smartspace.logic;

import java.util.Collection;
import java.util.List;

import smartspace.data.ElementEntity;
import smartspace.layout.ElementBoundary;

public interface ElementService {

	public List<ElementEntity> importElements(List<ElementEntity> convertToEntity);
	public List<ElementEntity> ExportElements(int size, int page);
	public ElementEntity createElement(ElementEntity entity);
	public void update(ElementEntity convertToEntity,String elementSmartspace,String elementId);
	public ElementEntity retriveElement(String elementSmartspace, String elementId);
	public List<ElementEntity> getElements(int size, int page);
	public List<ElementEntity> getElementsByName(String name, int size, int page);
	public List<ElementEntity> getElementsByType(String value, int size, int page);
	public List<ElementEntity> getElementsByDistance(double x ,double y, double distance, int size, int page);

}
