package smartspace.logic;

import java.util.Collection;
import java.util.List;

import smartspace.data.ElementEntity;
import smartspace.layout.ElementBoundary;

public interface ElementService {

	public List<ElementEntity> importElements(String smartspace, String email,List<ElementEntity> convertToEntity);
	public List<ElementEntity> ExportElements(String smartspace, String email,int size, int page);
	public ElementEntity createElement(String smartspace, String email,ElementEntity entity);
	public void update(String smartspace, String email,ElementEntity convertToEntity,String elementSmartspace,String elementId);
	public ElementEntity retriveElement(String smartspace, String email,String elementSmartspace, String elementId);
	public List<ElementEntity> getElements(String smartspace, String email,int size, int page);
	public List<ElementEntity> getElementsByName(String smartspace, String email,String name, int size, int page);
	public List<ElementEntity> getElementsByType(String smartspace, String email,String value, int size, int page);
	public List<ElementEntity> getElementsByDistance(String smartspace, String email,double x ,double y, double distance, int size, int page);

}
