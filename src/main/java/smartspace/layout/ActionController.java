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

import smartspace.data.ActionEntity;
import smartspace.data.ElementEntity;
import smartspace.logic.ActionService;


@RestController
public class ActionController {
	private ActionService actionService;
	
	@Autowired
	public ActionController(ActionService actionService) {
		this.actionService = actionService;
	}	
	
	@RequestMapping(
			path="/smartspace/admin/actions/{adminSmartspace}/{adminEmail}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ActionBoundary[] exportActionEntities (
			@PathVariable("adminSmartspace") String adminSmartspace,
			@PathVariable("adminEmail") String adminEmail,
			@RequestParam(name="size", required=false, defaultValue="10") int size, 
			@RequestParam(name="page", required=false, defaultValue="0") int page) {
		return
		this.actionService
		.ExportActions(size, page)
			.stream()
			.map(ActionBoundary::new)
			.collect(Collectors.toList())
			.toArray(new ActionBoundary[0]);
	}
	
	@RequestMapping(
			path="/smartspace/admin/actions/{adminSmartspace}/{adminEmail}",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
			public ActionBoundary[] importActionEntites (
			@PathVariable("adminSmartspace") String adminSmartspace,
			@PathVariable("adminEmail") String adminEmail,
			@RequestBody ActionBoundary[] actions) {
		List<ActionEntity> actionEntites=new ArrayList<ActionEntity>();
		


		for (ActionBoundary action : actions) {
			actionEntites.add(action.convertToEntity());
		}
				
				
			return	this.actionService
				.importActions(actionEntites)
					.stream()
					.map(ActionBoundary::new)
					.collect(Collectors.toList())
					.toArray(new ActionBoundary[0]);		
						
			}
	@RequestMapping(
			path="/smartspace/actions",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
			public ActionBoundary invokeAnAction (
			@RequestBody ActionBoundary action) {
				
				return new ActionBoundary(this.actionService.invokeAction(action.convertToEntity()));
			}
	
	
						

	
}
