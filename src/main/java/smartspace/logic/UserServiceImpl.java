package smartspace.logic;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import smartspace.aop.RoleValidation;
import smartspace.dao.AdvancedUserDao;
import smartspace.data.UserEntity;
import smartspace.data.UserRole;
import smartspace.layout.UserBoundary;
@Service
public class UserServiceImpl implements UserService {
	private AdvancedUserDao<String> userDao;
	
	@Autowired	
	public UserServiceImpl(AdvancedUserDao<String> userDao) {
		this.userDao = userDao;
	}
	
	@RoleValidation(permissions = {UserRole.ADMIN})
	@Transactional
	@Override
	public  List<UserEntity> importUsers(String smartspace, String email,List<UserEntity> entities) {
		List<UserEntity> userEntites=new ArrayList<UserEntity>();
		for (UserEntity entity : entities) {
			if(entity.getUserSmatspace().equals("inbala1"))
					throw new RuntimeException("You are trying to import users from your own project-can't do that");
			else userEntites.add(this.userDao.create(entity));
		}
		return userEntites;
		
	}

	@RoleValidation(permissions = {UserRole.ADMIN})
	@Override
	public List<UserEntity> ExportUsers(String smartspace, String email,int size, int page) {
		return this.userDao.readAll(size, page);
	}

	@Override
	public UserEntity createUser(UserEntity convertToEntity) {
		// TODO Auto-validate method
		if (!convertToEntity.getUserEmail().contains("@")){
			return new UserEntity();
		}
		return this.userDao.create(convertToEntity);
		
	}

	@Override
	public UserEntity loginAndRetriveDetails(String smartspace, String email,UserEntity convertToEntity) {
		
		// TODO Auto-validate function

		return this.userDao.readById(convertToEntity.getUserSmatspace()
									+"!"+convertToEntity.getUserEmail()).get();
	}

	@Override
	public void updateUser(String smartspace, String email,UserEntity convertToEntity) {
		// TODO Auto-validate function
		
		convertToEntity.setPoints(-1);
		convertToEntity.setKey(convertToEntity.getUserSmatspace()+"!"+convertToEntity.getUserEmail());
		this.userDao.update(convertToEntity);
	}

}
