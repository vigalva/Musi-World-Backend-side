package smartspace.dao.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import smartspace.dao.UserDao;
import smartspace.data.UserEntity;

//@Repository
public class MemoryUserDao implements UserDao<String>{
	
	private  Map<String,UserEntity> memory;
	private String smartspace;
	private String userEmail;
	
	public  MemoryUserDao() {
		this.memory = Collections.synchronizedSortedMap(new TreeMap<>());;
		
	}
	
	@Value("${smartspace.name:smartspace}")
	public void setSmartspace(String smartspace) {
		this.smartspace = smartspace;
	}
	@Value("${userEmail.name:demo@demo.com}")
	public void setUserEmail(String email) {
		this.userEmail=email;
	}
	@Override
	public UserEntity create(UserEntity userEntity) {
		userEntity.setKey(smartspace + "!" + userEmail);
		this.memory.put(userEntity.getKey(), userEntity);
		return userEntity;
	}

	@Override
	public Optional<UserEntity> readById(String userKey) {
		UserEntity userEntity = this.memory.get(userKey);
		if (userEntity != null) {
			return Optional.of(userEntity);
		}else {
			return Optional.empty();
		}
	}

	@Override
	public List<UserEntity> readAll() {
		
		return new ArrayList<>(this.memory.values());
	}

	@Override
	public void update(UserEntity userEntity) {
		UserEntity existing = 
				this.readById( userEntity.getKey()).
				orElseThrow(()->new RuntimeException("no message entity with key: " + userEntity.getKey()));
			
			if (userEntity.getAvatar() != null) {
				existing.setAvatar(userEntity.getAvatar());
			}
			if (userEntity.getPoints() != 0) {
				existing.setPoints(userEntity.getPoints());
			}
			if (userEntity.getUserEmail() != null) {
				existing.setUserEmail(userEntity.getUserEmail());
			}
			if (userEntity.getUsername() != null) {
				existing.setUsername(userEntity.getUsername());
			}
			
				
	}

	@Override
	public void deleteAll() {
		memory.clear();
		
	}

}
