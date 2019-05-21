package smartspace.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import smartspace.aop.RoleValidation;
import smartspace.dao.AdvancedElementDao;
import smartspace.data.ElementEntity;
import smartspace.data.UserRole;
import smartspace.layout.ElementBoundary;

@Service
public class ElementServiceImpl implements ElementService {
	private AdvancedElementDao<String> ElementsDao;
	
	public ElementServiceImpl(AdvancedElementDao<String> ElementsDao) {
		this.ElementsDao = ElementsDao;
	}
	
	@RoleValidation(permissions = {UserRole.ADMIN})
	@Transactional
	@Override
	public List<ElementEntity> importElements(String smartspace, String email,List<ElementEntity> entities) {
		
			List<ElementEntity> elementsEntites=new ArrayList<ElementEntity>();
		for (ElementEntity entity : entities) {
			if(entity.getElementSmartspace().equals("inbala1"))
					throw new RuntimeException("You are trying to import elements from your own project-can't do that");
			else elementsEntites.add(this.ElementsDao.create(entity));
		}
		return elementsEntites;
		
	}

	@RoleValidation(permissions = {UserRole.ADMIN})
	@Override
	public List<ElementEntity> ExportElements(String smartspace, String email,int size, int page) {
		return this.ElementsDao.readAll(size, page);
	}

	@RoleValidation(permissions = {UserRole.MANAGER})
	@Override
	public ElementEntity createElement(String smartspace, String email,ElementEntity entity) {
		// TODO Auto-need to do validation
		return this.ElementsDao.create(entity);
	}

	@RoleValidation(permissions = {UserRole.MANAGER})
	@Override
	public void update(String smartspace, String email,ElementEntity convertToEntity,String elementSmartspace,String elementId) {
		// TODO Auto-validate function
		
		convertToEntity.setKey(elementSmartspace+"!"+elementId);
		this.ElementsDao.update(convertToEntity);
		
	}

	@RoleValidation(permissions = {UserRole.MANAGER, UserRole.PLAYER})
	@Override
	public ElementEntity retriveElement(String smartspace, String email,String elementSmartspace, String elementId) {
		// TODO Auto-validate function
		
		return this.ElementsDao.readById(elementSmartspace+"!"+elementId).get();
	}

	@RoleValidation(permissions = {UserRole.MANAGER, UserRole.PLAYER})
	@Override
	public List<ElementEntity> getElements(String smartspace, String email,int size, int page) {
		// TODO Auto-validate function
		
		return this.ElementsDao.readAll(size, page);
	}

	@RoleValidation(permissions = {UserRole.MANAGER, UserRole.PLAYER})
	@Override
	public List<ElementEntity> getElementsByName(String smartspace, String email,String name, int size, int page) {
		
		return this.ElementsDao.readElementsByNamePattern(name, size, page);

	}

	@RoleValidation(permissions = {UserRole.MANAGER, UserRole.PLAYER})
	@Override
	public List<ElementEntity> getElementsByType(String smartspace, String email,String value, int size, int page) {
		
		return this.ElementsDao.readElementsByType(value, size, page);

	}

	@RoleValidation(permissions = {UserRole.MANAGER, UserRole.PLAYER})
	@Override
	public List<ElementEntity> getElementsByDistance(String smartspace, String email,double x, double y, double distance, int size, int page) {
				return this.ElementsDao.readElementsByDistance(x, y, distance, size, page);
	}
	
	
	



}
