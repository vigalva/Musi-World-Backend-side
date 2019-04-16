package smartspace.dao.rdb;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import smartspace.dao.AdvancedElementDao;
//import smartspace.dao.ElementDao;
import smartspace.data.ElementEntity;

@Repository
public class RdbElementDao implements AdvancedElementDao<String> {

	private ElementCrud elementCrud;
	private String smartspace;
	private String elementId;
	private GeneratorIdCrud generatorIdCrud; 
	
	

	@Autowired
	public RdbElementDao(ElementCrud elementCrud, GeneratorIdCrud generatorIdCrud) {
		this.elementCrud = elementCrud;
		this.generatorIdCrud=generatorIdCrud;

		
	}

	@Value("${smartspace.name:smartspace}")
	public void setSmartspace(String smartspace) {
		this.smartspace = smartspace;
	}
	@Value("${elementId:0}")
	public void setElementId(String elementid) {
		this.elementId = elementid;
	}
	

	@Override
	@Transactional
	public ElementEntity create(ElementEntity element) {
		
		GeneratorId idEntity = this.generatorIdCrud.save(new GeneratorId());
		element.setKey(smartspace + "!" + elementId+ idEntity.getId());
		this.generatorIdCrud.delete(idEntity);
		// SQL: INSERT
		if (!this.elementCrud.existsById(element.getKey())) {
			return this.elementCrud.save(element);
		}else {
			throw new RuntimeException("element already exists with key: " + element.getKey());
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<ElementEntity> readAll() {
		List<ElementEntity> rv = new ArrayList<>();
		// SQL: SELECT
		this.elementCrud.findAll()
			.forEach(rv::add);
		return rv;
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<ElementEntity> readById(String  key) {
		// SQL: SELECT
		return this.elementCrud.findById((String)key);
	}

	@Override
	@Transactional
	public void update(ElementEntity update) {
		ElementEntity existing = 
				this.readById( update.getKey())
				.orElseThrow(()->new RuntimeException("no message entity with key: " + update.getKey()));
			
		if (update.getCreatorEmail() != null) {
			existing.setCreatorEmail(update.getCreatorEmail());
		}

		if (update.getMoreAttributes() != null) {
			existing.setMoreAttributes(update.getMoreAttributes());
		}
		
		if (update.getLocation()!=null) {
			existing.setLocation(update.getLocation());
		}
		
		if (update.getCreatorSmartspace()!=null) {
			existing.setCreatorSmartspace(update.getCreatorSmartspace());
		}
		if (update.getCreationTimestamp()!=null) {
			existing.setCreationTimestamp(update.getCreationTimestamp());
		}
//		if (update.getElementId()!=null) {
//			existing.setElementId(update.getElementId());
//		}
		if (update.getName()!=null) {
			existing.setName(update.getName());
		}
		if (update.getType()!=null) {
			existing.setType(update.getType());
		}
//		if (update.getElementSmartspace()!=null) {
//			existing.setElementSmartspace(update.getElementSmartspace());
//		}
	
		

		// SQL: UPDATE
		this.elementCrud.save(existing);
	}

	@Override
	@Transactional
	public void deleteAll() {
		// SQL: DELETE
		this.elementCrud.deleteAll();
	}

	@Override
	public void deleteByKey(String elementKey) {
		elementCrud.deleteById((String)elementKey);
		
	}

	@Override
	public void delete(ElementEntity elementEntity) {
		elementCrud.delete(elementEntity);
		
	}

	@Override
	public List<ElementEntity> readAll(int size, int page) {
		return this.elementCrud.findAll(PageRequest.of(page, size)).getContent();
	}

	@Override
	public List<ElementEntity> readAll(String sortBy, int size, int page) {
		return this.elementCrud.findAll(PageRequest.of(page, size, Direction.ASC, sortBy)).getContent();

	}

}
