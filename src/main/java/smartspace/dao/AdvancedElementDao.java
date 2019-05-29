package smartspace.dao;

import java.util.List;

import smartspace.data.ElementEntity;

public interface AdvancedElementDao<ElementKey> extends ElementDao<ElementKey>{
	
	public List<ElementEntity> readAll(int size, int page);
	public List<ElementEntity> readAll(String sortBy, int size, int page);
	public List<ElementEntity> readElementsByNamePatternForPlayer(String name, int size, int page);
	
	public List<ElementEntity> readElementsByNamePatternForManager(String name, int size, int page);

	public List<ElementEntity> readElementsByTypeForPlayer(String type, int size, int page);
	public List<ElementEntity> readElementsByTypeForManager(String type, int size, int page);

	public List<ElementEntity> readElementsByDistanceForManager(double x, double y, double distance, int size, int page);
	public List<ElementEntity> readElementsByDistanceForPlayer(double x, double y, double distance, int size, int page);


}