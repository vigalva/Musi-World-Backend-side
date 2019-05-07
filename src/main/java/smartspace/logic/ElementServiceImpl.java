package smartspace.logic;

import java.util.ArrayList;
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



}
