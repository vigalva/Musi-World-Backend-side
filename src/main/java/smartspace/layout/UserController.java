package smartspace.layout;

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
		importUsers(userEntites)
			.stream()
			.map(UserBoundary::new)
			.collect(Collectors.toList())
			.toArray(new UserBoundary[0]);		
				
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
		ExportUsers(size, page)
			.stream()
			.map(UserBoundary::new)
			.collect(Collectors.toList())
			.toArray(new UserBoundary[0]);
	}

}
