package smartspace.data;
 
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Table(name="USERS")
public class UserEntity implements SmartspaceEntity<String>{
	private String userSmatspace;
	private String userEmail;
	private String username;
	private String avatar;
	private UserRole role;
	private long points;
	
	public UserEntity(String userEmail, String userSmartspace, String username, String avatar,
			UserRole role, long points) {
		this.userEmail=userEmail;
		this.userSmatspace=userSmartspace;
		this.username=username;
		this.avatar=avatar;
		this.role=role;
		this.points=points;
		
	}
	public String getUserSmatspace() {
		return userSmatspace;
	}

	public void setUserSmatspace(String userSmatspace) {
		this.userSmatspace = userSmatspace;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	@Enumerated(EnumType.STRING)
	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}

	public UserEntity() {
		super();
	}
	@Id
	@Override
	public String getKey() {
		return (String) (userSmatspace+ "!"+ userEmail);
	}

	@Override
	public void setKey(String key) {
		String parts[]=key.split("!");
		this.userSmatspace=parts[0];
		this.userEmail=parts[1];
	}
	

}
