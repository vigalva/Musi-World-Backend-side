//package smartspace.layout;
//
//import java.util.stream.Collectors;
//
//import org.assertj.core.util.Arrays;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import smartspace.logic.ElementService;
//
//@RestController
//public class ElementController {
//	
//	private ElementService elementService;
//	
//	@Autowired
//	public ElementController(ElementService elementService) {
//		this.elementService = elementService;
//	}
//
//
//	@RequestMapping(
//			path="/elementdemo/{type}/{sortBy}",
//			method=RequestMethod.POST,
//			consumes=MediaType.APPLICATION_JSON_VALUE,
//			produces=MediaType.APPLICATION_JSON_VALUE)
//	public ElementBoundary createElement (
//			@RequestBody ElementBoundary element) {		
//		return new ElementBoundary(
//				this.elementService
//					.importElements(
//							Arrays.array(element.convertToEntity())
//							)
//					);
//	}
//	
//	@RequestMapping(
//			path="/elementdemo",
//			method=RequestMethod.GET,
//			produces=MediaType.APPLICATION_JSON_VALUE)
//	public ElementBoundary[] getElement (
//			@RequestParam(name="size", required=false, defaultValue="10") int size, 
//			@RequestParam(name="page", required=false, defaultValue="0") int page) {
//		return
//		this.elementService
//			.ExportElements(size, page)
//			.stream()
//			.map(ElementBoundary::new)
//			.collect(Collectors.toList())
//			.toArray(new ElementBoundary[0]);
//	}
//	/*
//	@RequestMapping(
//			path="/elementdemo/{type}/{sortBy}",
//			method=RequestMethod.GET,
//			produces=MediaType.APPLICATION_JSON_VALUE)
//	public ElementBoundary[] getElements (
//			@PathVariable("type") String pattern,
//			@PathVariable("sortBy") String sortBy,
//			@RequestParam(name="size", required=false, defaultValue="10") int size, 
//			@RequestParam(name="page", required=false, defaultValue="0") int page) {
//		return
//		this.elementService
//			.getMessagesByPattern(pattern, sortBy, size, page)
//			.stream()
//			.map(ElementBoundary::new)
//			.collect(Collectors.toList())
//			.toArray(new ElementBoundary[0]);
//			
//	}
//*/	
//}
