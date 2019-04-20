package smartspace.layout;

import org.springframework.web.bind.annotation.RestController;

import smartspace.logic.ElementService;

@RestController
public class ElementController {
	private ElementService elementService;
	
	public ElementController(ElementService elementService) {
		this.elementService = elementService;
	}

}
