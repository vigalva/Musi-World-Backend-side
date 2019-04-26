package smartspace.layout;

public class UserBoundaryKey {
	private String smartspace;
	private String email;
	
	public UserBoundaryKey() {
		
	}
		public UserBoundaryKey(String email) {
		this.email=email;
	}
	public String getEmail() {
		return email;
	}
	public String getSmartspace() {
		return smartspace;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSmartspace(String smartspace) {
		this.smartspace = smartspace;
	}
}
