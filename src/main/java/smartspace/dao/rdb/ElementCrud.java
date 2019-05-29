package smartspace.dao.rdb;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import smartspace.data.ElementEntity;

public interface ElementCrud extends PagingAndSortingRepository<ElementEntity, String> {

	public List<ElementEntity> 
	findAllByNameLike(
			@Param("name") String name, 
			Pageable pageable);

	public List<ElementEntity> 
	findAllByTypeLike(
			@Param("type") String type,
			Pageable pageable);
	
	public List<ElementEntity> 
	findAllByLocationXBetweenAndLocationYBetween(
			@Param("minX") double minX, 
			@Param("maxX") double maxX, 
			@Param("minY") double minY, 
			@Param("maxY") double maxY,
			Pageable pageable);
	
	public List<ElementEntity> 
	findAllByNameLikeAndExpiredIs(
			@Param("name") String name, 
			@Param("expired") boolean expired,
			Pageable pageable);
	
	public List<ElementEntity> 
	findAllByTypeLikeAndExpiredIs(
			@Param("type") String type,
			@Param("expired") boolean expired,
			Pageable pageable);
	
	public List<ElementEntity> 
	findAllByLocationXBetweenAndLocationYBetweenAndExpiredIs(
			@Param("minX") double minX, 
			@Param("maxX") double maxX, 
			@Param("minY") double minY, 
			@Param("maxY") double maxY,
			@Param("expired") boolean expired,
			Pageable pageable);
	

}
