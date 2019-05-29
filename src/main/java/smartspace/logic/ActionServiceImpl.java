package smartspace.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import smartspace.aop.RoleValidation;
import smartspace.dao.AdvancedActionDao;
import smartspace.dao.AdvancedElementDao;
import smartspace.dao.AdvancedUserDao;
import smartspace.data.ActionEntity;
import smartspace.data.ElementEntity;
import smartspace.data.UserEntity;
import smartspace.data.UserRole;

@Service

public class ActionServiceImpl implements ActionService {
	private AdvancedActionDao<String> ActionsDao;
	private AdvancedElementDao<String> ElementDao;
	private AdvancedUserDao<String> userDao;

	public ActionServiceImpl(AdvancedActionDao<String> ActionsDao, AdvancedElementDao<String> ElementDao,AdvancedUserDao<String> userDao) {
		this.ActionsDao = ActionsDao;
		this.ElementDao = ElementDao;
		this.userDao=userDao;
	}

	@RoleValidation(permissions = {UserRole.ADMIN})
	@Transactional
	@Override
	public List<ActionEntity> importActions(String smartspace, String email,List<ActionEntity> actions) {
		List<ActionEntity> actionEntites = new ArrayList<ActionEntity>();
		List<ElementEntity> elements = this.ElementDao.readAll();
		boolean foundElement=false;
		for (ActionEntity action : actions) {
			for (ElementEntity element : elements) {
				if (element.getElementSmartspace().equals(action.getElementSmartspace())) {
					foundElement=true;
					break;
				}
			}
			if (!foundElement)
				throw new RuntimeException("You are trying to import action with no element");
			else {
				if (action.getActionSmartspace().equals("inbal1"))
					throw new RuntimeException("You are trying to import actions from your project");
				else
					actionEntites.add(this.ActionsDao.create(action));

			}
		}
		return actionEntites;
	}

	@RoleValidation(permissions = {UserRole.ADMIN})
	@Override
	public List<ActionEntity> ExportActions(String smartspace, String email,int size, int page) {
		return this.ActionsDao.readAll(size, page);
	}

	@RoleValidation(permissions = {UserRole.PLAYER})
	@Override
	public ActionEntity invokeAction(String smartspace, String email,ActionEntity convertToEntity) {
		// TODO Auto-validate methodes
		if (convertToEntity.getActionType().equals("echo")) {
			UserEntity user=this.userDao.readById(smartspace+"!"+email).get();
			user.setPoints(user.getPoints()+10);
			this.userDao.update(user);
			return this.ActionsDao.create(convertToEntity);
		}
		else if (convertToEntity.getActionType().equals("check in")) {
			UserEntity user=this.userDao.readById(smartspace+"!"+email).get();
			user.setPoints(user.getPoints()+10);
			this.userDao.update(user);
			return this.ActionsDao.create(convertToEntity);
		}
		else if (convertToEntity.getActionType().equals("check out")) {
			UserEntity user=this.userDao.readById(smartspace+"!"+email).get();
			user.setPoints(user.getPoints()+10);
			this.userDao.update(user);
			return this.ActionsDao.create(convertToEntity);
		}
		else if (convertToEntity.getActionType().equals("buy album")) {
			UserEntity user=this.userDao.readById(smartspace+"!"+email).get();
			user.setPoints(user.getPoints()+10);
			this.userDao.update(user);
			return this.ActionsDao.create(convertToEntity);
		}
		else if (convertToEntity.getActionType().equals("buy tickets")) {
			UserEntity user=this.userDao.readById(smartspace+"!"+email).get();
			user.setPoints(user.getPoints()+10);
			this.userDao.update(user);
			return this.ActionsDao.create(convertToEntity);
		}
		else if (convertToEntity.getActionType().equals("cancel tickets")) {
			UserEntity user=this.userDao.readById(smartspace+"!"+email).get();
			user.setPoints(user.getPoints()+10);
			this.userDao.update(user);
			return this.ActionsDao.create(convertToEntity);
		}
		else if (convertToEntity.getActionType().equals("rate album")) {
			UserEntity user=this.userDao.readById(smartspace+"!"+email).get();
			user.setPoints(user.getPoints()+10);
			this.userDao.update(user);
			return this.ActionsDao.create(convertToEntity);
		}
		else if (convertToEntity.getActionType().equals("preview")) {
			UserEntity user=this.userDao.readById(smartspace+"!"+email).get();
			user.setPoints(user.getPoints()+10);
			this.userDao.update(user);
			return this.ActionsDao.create(convertToEntity);
		}
		
		return this.ActionsDao.create(convertToEntity);
	}
}
