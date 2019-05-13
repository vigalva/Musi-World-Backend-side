package smartspace.layout;

public class NewUserForm {
	private String email;
	private String role;
	private String username;
	private String avatar;
	
	public NewUserForm() {
		// TODO Auto-generated constructor stub
	}
	public String getAvatar() {
		return avatar;
	}
	public String getEmail() {
		return email;
	}
	public String getRole() {
		return role;
	}
	public String getUsername() {
		return username;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
