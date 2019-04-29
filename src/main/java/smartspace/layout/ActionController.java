package smartspace.layout;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import smartspace.data.ElementEntity;
import smartspace.logic.ActionService;

@RestController
public class ActionController {

	private ActionService actionService;

	@Autowired
	public ActionController(ActionService actionService) {
		this.actionService = actionService;
	}

	@RequestMapping(path = "/smartspace/admin/action/{adminSmartspace}/{adminEmail}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ActionBoundary[] ExportActions(@PathVariable("adminSmartspace") String adminSmartspace,
			@PathVariable("adminEmail") String adminEmail,
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page) {

		return this.actionService.ExportActions(size, page).stream().map(ActionBoundary::new)
				.collect(Collectors.toList()).toArray(new ActionBoundary[0]);
	}

	@RequestMapping(path = "/smartspace/admin/action/{adminSmartspace}/{adminEmail}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ActionBoundary[] getActions(@PathVariable("adminSmartspace") String adminSmartspace,
			@PathVariable("adminEmail") String adminEmail, @RequestBody ActionEntity[] elements) {
		return (ActionBoundary[]) this.elementService.importElements(elements).toArray();
	}
}
