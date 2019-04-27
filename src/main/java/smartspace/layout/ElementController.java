package smartspace.layout;

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

import smartspace.data.ElementEntity;
import smartspace.data.UserEntity;
import smartspace.logic.ElementService;
import smartspace.logic.UserService;

@RestController
public class ElementController {
	private ElementService elementService;
	
	@Autowired
	public ElementController(ElementService elementService) {
		this.elementService = elementService;
	}	
	
	@RequestMapping(
			path="/smartspace/admin/element/{adminSmartspace}/{adminEmail}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary[] ExportElements (
			@PathVariable("adminSmartspace") String adminSmartspace,
			@PathVariable("adminEmail") String adminEmail,
			@RequestParam(name="size", required=false, defaultValue="10") int size, 
			@RequestParam(name="page", required=false, defaultValue="0") int page) {
		return
		this.elementService
			.ExportElements(size, page)
			.stream()
			.map(ElementBoundary::new)
			.collect(Collectors.toList())
			.toArray(new ElementBoundary[0]);
	}

	
	@RequestMapping(
			path="/smartspace/admin/element/{adminSmartspace}/{adminEmail}",
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary[] getElement (
			@PathVariable("adminSmartspace") String adminSmartspace,
			@PathVariable("adminEmail") String adminEmail,
			@RequestBody ElementBoundary[] elements) { 
		return
			(ElementBoundary[]) this.elementService.importElements(elements).toArray();
	}

	
}
