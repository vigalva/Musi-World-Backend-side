package smartspace.layout;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import smartspace.data.UserEntity;
import smartspace.logic.UserService;

@RestController
public class UserController {
	
	private UserService userSerivce;
	
	@Autowired
	public UserController(UserService userSerivce) {
		this.userSerivce = userSerivce;
	}
	
	@RequestMapping(
	path="/smartspace/admin/users/{adminSmartspace}/{adminEmail}",
	method=RequestMethod.POST,
	consumes=MediaType.APPLICATION_JSON_VALUE,
	produces=MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary[] importUserEntites (
	@PathVariable("adminSmartspace") String adminSmartspace,
	@PathVariable("adminEmail") String adminEmail,
	@RequestBody UserBoundary[] users) {
		List<UserEntity> userEntites=new ArrayList<UserEntity>();
		for (UserBoundary user : users) {
			userEntites.add(user.convertToEntity());
		}
		
	return	this.userSerivce.
		importUsers(adminSmartspace,adminEmail,userEntites)
			.stream()
			.map(UserBoundary::new)
			.collect(Collectors.toList())
			.toArray(new UserBoundary[0]);		
				
	}
	
	@RequestMapping(
			path="/smartspace/users",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
			public UserBoundary createANewUser (
			@RequestBody NewUserForm form) {
				
				UserBoundary user= new UserBoundary();
				UserBoundaryKey key=new UserBoundaryKey();
				
				key.setEmail(form.getEmail());
				user.setAvatar(form.getAvatar());
				
				user.setRole(form.getAvatar());
				user.setRole(form.getRole());
				user.setUsername(form.getUsername());
				user.setKey(key);
				return new UserBoundary(this.userSerivce.createUser(user.convertToEntity()));
						
			}
				
		
	
	
	private UserBoundary newUserBoundary(UserBoundary createUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(
			path="/smartspace/admin/users/{adminSmartspace}/{adminEmail}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary[] exportUserEntities (
			@PathVariable("adminSmartspace") String adminSmartspace,
			@PathVariable("adminEmail") String adminEmail,
			@RequestParam(name="size", required=false, defaultValue="10") int size, 
			@RequestParam(name="page", required=false, defaultValue="0") int page) {
		return
		this.userSerivce.
		ExportUsers(adminSmartspace,adminEmail,size, page)
			.stream()
			.map(UserBoundary::new)
			.collect(Collectors.toList())
			.toArray(new UserBoundary[0]);
	}
	
	@RequestMapping(
			path="/smartspace/users/login/{userSmartspace}/{userEmail}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary loginValidUserAndRetriveUserDetails (
			@PathVariable("userSmartspace") String userSmartspace,
			@PathVariable("userEmail") String userEmail) {
			
			UserBoundary user=new UserBoundary();
			UserBoundaryKey key=new UserBoundaryKey();
			key.setEmail(userEmail);
			key.setSmartspace(userSmartspace);
			user.setKey(key);
			
			return new UserBoundary(this.userSerivce.loginAndRetriveDetails(userSmartspace,userEmail,user.convertToEntity()));
			
	}
	
	@RequestMapping(
			path="/smartspace/users/login/{userSmartspace}/{userEmail}",
			method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public void updateUserDetailsExceptThierPoints (
			@PathVariable("userSmartspace") String userSmartspace,
			@PathVariable("userEmail") String userEmail,
			@RequestBody UserBoundary update) {
			
			this.userSerivce.updateUser(userSmartspace,userEmail,update.convertToEntity());
	}


}
