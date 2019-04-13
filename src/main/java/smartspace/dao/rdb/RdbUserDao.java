package smartspace.dao.rdb;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import smartspace.dao.UserDao;
import smartspace.data.UserEntity;

@Repository
public class RdbUserDao implements UserDao<String> {

	private UserCrud userCrud;
	private String smartspace;
	private String userEmail;

	@Autowired
	public RdbUserDao(UserCrud userCrud) {
		this.userCrud = userCrud;

	}

	@Value("${smartspace.name:smartspace}")
	public void setSmartspace(String smartspace) {
		this.smartspace = smartspace;
	}

	@Value("${userEmail:dummy mail}")
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	@Transactional
	public UserEntity create(UserEntity user) {
		// TODO replace this with key generated by DB
		if (user.getUserEmail() != null)
			user.setKey(smartspace + "!" + user.getUserEmail());
		else
			user.setKey(smartspace + "!" + userEmail);
		// SQL: INSERT
		if (!this.userCrud.existsById(user.getKey())) {
			return this.userCrud.save(user);
		} else {
			throw new RuntimeException("user already exists with key: " + user.getKey());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserEntity> readAll() {
		List<UserEntity> rv = new ArrayList<>();
		// SQL: SELECT
		this.userCrud.findAll().forEach(rv::add);
		return rv;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<UserEntity> readById(String key) {
		// SQL: SELECT
		return this.userCrud.findById((String) key);
	}

	@Override
	@Transactional
	public void update(UserEntity update) {
		UserEntity existing = this.readById(update.getKey())
				.orElseThrow(() -> new RuntimeException("no user entity with key: " + update.getKey()));

		if (update.getAvatar() != null) {
			existing.setAvatar(update.getAvatar());
		}
		if (update.getPoints() != 0) {
			existing.setPoints(update.getPoints());
		}
		if (update.getRole() != null) {
			existing.setRole(update.getRole());
		}
//		if (update.getUserEmail()!=null) {
//			existing.setUserEmail(update.getUserEmail());
//		}
		if (update.getUsername() != null) {
			existing.setUsername(update.getUsername());
		}
//		if (update.getUserSmatspace()!=null) {
//			existing.setUserSmatspace(update.getUserSmatspace());
//		}
		// SQL: UPDATE
		this.userCrud.save(existing);
	}

	@Override
	@Transactional
	public void deleteAll() {
		// SQL: DELETE
		this.userCrud.deleteAll();
	}

}
