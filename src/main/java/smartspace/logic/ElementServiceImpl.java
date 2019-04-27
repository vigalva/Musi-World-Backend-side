package smartspace.logic;

import java.util.ArrayList;
import java.util.List;

import smartspace.dao.AdvancedElementDao;

import smartspace.data.ElementEntity;
import smartspace.layout.ElementBoundary;


public class ElementServiceImpl implements ElementService {
	private AdvancedElementDao<String> ElementsDao;

	@Override
	public List<ElementEntity> importElements(ElementBoundary[] convertToEntity) {
			List<ElementEntity> elementsEntites=new ArrayList<ElementEntity>();
		for (ElementBoundary boundary : convertToEntity) {
			if(boundary.convertToEntity().getElementSmartspace().equals("inbala1"))
					throw new RuntimeException("You are trying to import users from your own project-can't do that");
			else elementsEntites.add(this.ElementsDao.create(boundary.convertToEntity()));
		}
		return elementsEntites;
		
	}

	@Override
	public List<ElementEntity> ExportElements(int size, int page) {
		return this.ElementsDao.readAll(size, page);
	}



}
