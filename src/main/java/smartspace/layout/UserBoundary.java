package smartspace.layout;

import smartspace.data.UserEntity;
import smartspace.data.UserRole;

public class UserBoundary {
	private UserBoundaryKey key;
	private String role;
	private String username;
	private String avatar;
	private long points;
	
	public UserBoundary() {
		
	}
	
	public UserBoundary(UserEntity entity) {
		
		this.key=new UserBoundaryKey();
		this.key.setSmartspace(entity.getUserSmatspace());
		this.key.setEmail(entity.getUserEmail());
		this.username=entity.getUsername();
		this.role=entity.getRole().toString();
		this.avatar=entity.getAvatar();
		this.points=entity.getPoints();
		 
	}
	public String getAvatar() {
		return avatar;
	}
	public long getPoints() {
		return points;
	}
	public String getUsername() {
	return username;
	}
	public String getRole() {
		return role;
	}
	public UserBoundaryKey getKey() {
		return key;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public void setKey(UserBoundaryKey key) {
		this.key = key;
	}
	public void setPoints(long points) {
		this.points = points;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public UserEntity convertToEntity() {
		
		UserEntity entity=new UserEntity();
		
		entity.setUserSmatspace(this.getKey().getSmartspace());
		entity.setUserEmail(this.getKey().getEmail());
		entity.setUsername(this.getUsername());
		entity.setAvatar(this.getAvatar());
		if (this.getRole()!=null)
			entity.setRole(UserRole.valueOf(this.getRole()));
		entity.setPoints(this.getPoints());
		return entity;
	}
	
	@Override
	public String toString() {
		if (key.getSmartspace()!=null)
		return "UserBoundary [key=" + key.getSmartspace()+ " "+key.getEmail() + ", username=" + username + ", avatar=" + avatar + 
				", role=" + role+ ", points=" + points  + "]"+"\n";
		return key.getEmail();
	}

}
