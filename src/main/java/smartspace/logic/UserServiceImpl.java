package smartspace.logic;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import smartspace.dao.AdvancedUserDao;
import smartspace.data.UserEntity;
import smartspace.layout.UserBoundary;
@Service
public class UserServiceImpl implements UserService {
	private AdvancedUserDao<String> userDao;
	
	@Autowired	
	public UserServiceImpl(AdvancedUserDao<String> userDao) {
		this.userDao = userDao;
	}
	
	@Transactional
	@Override
	public  List<UserEntity> importUsers(UserBoundary[] convertToEntity) {
		List<UserEntity> userEntites=new ArrayList<UserEntity>();
		for (UserBoundary boundary : convertToEntity) {
			if(boundary.convertToEntity().getUserSmatspace().equals("inbala1"))
					throw new RuntimeException("You are trying to import users from your own project-can't do that");
			else userEntites.add(this.userDao.create(boundary.convertToEntity()));
		}
		return userEntites;
		
	}

	@Override
	public List<UserEntity> ExportUsers(int size, int page) {
		return this.userDao.readAll(size, page);
	}

}
