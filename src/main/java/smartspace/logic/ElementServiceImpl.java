package smartspace.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import smartspace.dao.AdvancedElementDao;
import smartspace.data.ElementEntity;
import smartspace.layout.ElementBoundary;

@Service
public class ElementServiceImpl implements ElementService {
	private AdvancedElementDao<String> ElementsDao;
	
	public ElementServiceImpl(AdvancedElementDao<String> ElementsDao) {
		this.ElementsDao = ElementsDao;
	}
	
	@Transactional
	@Override
	public List<ElementEntity> importElements(List<ElementEntity> entities) {
		
			List<ElementEntity> elementsEntites=new ArrayList<ElementEntity>();
		for (ElementEntity entity : entities) {
			if(entity.getElementSmartspace().equals("inbala1"))
					throw new RuntimeException("You are trying to import elements from your own project-can't do that");
			else elementsEntites.add(this.ElementsDao.create(entity));
		}
		return elementsEntites;
		
	}

	@Override
	public List<ElementEntity> ExportElements(int size, int page) {
		return this.ElementsDao.readAll(size, page);
	}

	@Override
	public ElementEntity createElement(ElementEntity entity) {
		// TODO Auto-need to do validation
		return this.ElementsDao.create(entity);
	}

	@Override
	public void update(ElementEntity convertToEntity,String elementSmartspace,String elementId) {
		// TODO Auto-validate function
		
		convertToEntity.setKey(elementSmartspace+"!"+elementId);
		this.ElementsDao.update(convertToEntity);
		
	}

	@Override
	public ElementEntity retriveElement(String elementSmartspace, String elementId) {
		// TODO Auto-validate function
		
		return this.ElementsDao.readById(elementSmartspace+"!"+elementId).get();
	}

	@Override
	public List<ElementEntity> getElements(int size, int page) {
		// TODO Auto-validate function
		
		return this.ElementsDao.readAll(size, page);
	}

	@Override
	public List<ElementEntity> getElementsByName(String name, int size, int page) {
		
		return this.ElementsDao.readElementsByNamePattern(name, size, page);

	}

	@Override
	public List<ElementEntity> getElementsByType(String value, int size, int page) {
		
		return this.ElementsDao.readElementsByType(value, size, page);

	}

	@Override
	public List<ElementEntity> getElementsByDistance(double x, double y, double distance, int size, int page) {
				return this.ElementsDao.readElementsByDistance(x, y, distance, size, page);
	}
	
	
	



}
