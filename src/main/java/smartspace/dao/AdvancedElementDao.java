package smartspace.dao;

import java.util.List;

import smartspace.data.ElementEntity;

public interface AdvancedElementDao<ElementKey> extends ElementDao<ElementKey>{
	
	public List<ElementEntity> readAll(int size, int page);
	public List<ElementEntity> readAll(String sortBy, int size, int page);
	public List<ElementEntity> readElementsByNamePattern(String name, int size, int page);
	public List<ElementEntity> readElementsByType(String type, int size, int page);
	public List<ElementEntity> readElementsByDistance(double x, double y, double distance, int size, int page);

}