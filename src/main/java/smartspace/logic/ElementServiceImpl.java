package smartspace.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import smartspace.aop.RoleValidation;
import smartspace.dao.AdvancedElementDao;
import smartspace.dao.AdvancedUserDao;
import smartspace.data.ElementEntity;
import smartspace.data.UserEntity;
import smartspace.data.UserRole;
import smartspace.layout.ElementBoundary;

@Service
public class ElementServiceImpl implements ElementService {
	private AdvancedElementDao<String> ElementsDao;
	private AdvancedUserDao<String> UserDao;
	public ElementServiceImpl(AdvancedElementDao<String> ElementsDao,  AdvancedUserDao<String> UserDao) {
		this.ElementsDao = ElementsDao;
		this.UserDao=UserDao;
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
		entity.setCreationTimestamp(new Date());
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
		UserEntity user=this.UserDao.readById(smartspace+"!"+email).get();
		if (user.getRole().toString().equals("PLAYER")) {
			ElementEntity element= this.ElementsDao.readById(elementSmartspace+"!"+elementId).get();
			if (element.isExpired()==true)
				return new ElementEntity();
		}
		return this.ElementsDao.readById(elementSmartspace+"!"+elementId).get();
	}

	@RoleValidation(permissions = {UserRole.MANAGER, UserRole.PLAYER})
	@Override
	public List<ElementEntity> getElements(String smartspace, String email,int size, int page) {
		// TODO Auto-validate function
		UserEntity user=this.UserDao.readById(smartspace+"!"+email).get();
		if (user.getRole().toString().equals("PLAYER")) {
			List<ElementEntity> notExpired=this.ElementsDao.readAll(size, page);
			for (ElementEntity e:notExpired) {
				if (e.isExpired()==true)
					notExpired.remove(e);
			}
			return notExpired;
		}
		
		return this.ElementsDao.readAll(size, page);
	}

	@RoleValidation(permissions = {UserRole.MANAGER, UserRole.PLAYER})
	@Override
	public List<ElementEntity> getElementsByName(String smartspace, String email,String name, int size, int page) {
		
		UserEntity user=this.UserDao.readById(smartspace+"!"+email).get();
		if (user.getRole().toString().equals("PLAYER"))
			return this.ElementsDao.readElementsByNamePatternForPlayer(name, size, page);
		
		return this.ElementsDao.readElementsByNamePatternForManager(name, size, page);

	}

	@RoleValidation(permissions = {UserRole.MANAGER, UserRole.PLAYER})
	@Override
	public List<ElementEntity> getElementsByType(String smartspace, String email,String value, int size, int page) {
		
		UserEntity user=this.UserDao.readById(smartspace+"!"+email).get();
		if (user.getRole().toString().equals("PLAYER"))
			return this.ElementsDao.readElementsByTypeForPlayer(value, size, page);
		
		return this.ElementsDao.readElementsByTypeForManager(value, size, page);

	}

	@RoleValidation(permissions = {UserRole.MANAGER, UserRole.PLAYER})
	@Override
	public List<ElementEntity> getElementsByDistance(String smartspace, String email,double x, double y, double distance, int size, int page) {
				
				UserEntity user=this.UserDao.readById(smartspace+"!"+email).get();
				if (user.getRole().toString().equals("PLAYER"))
					return this.ElementsDao.readElementsByDistanceForPlayer(x, y, distance, size, page);

				return this.ElementsDao.readElementsByDistanceForManager(x, y, distance, size, page);
	}
	
	
	



}
