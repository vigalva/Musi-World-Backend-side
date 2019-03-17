package smartspace.data;

public class UserEntity<K> implements SmartspaceEntity<K>{
	private String userSmatspace;
	private String userEmail;
	private String username;
	private String avatar;
	private UserRole role;
	private long points;
	

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

	@Override
	public K getKey() {
		return (K) userEmail;
	}

	@Override
	public void setKey(K key) {
		// TODO Auto-generated method stub
		
	}

}
