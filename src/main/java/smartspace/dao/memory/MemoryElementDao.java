package smartspace.dao.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import smartspace.dao.ElementDao;
import smartspace.data.ElementEntity;

//@Repository
public class MemoryElementDao implements ElementDao<String> {
	
	private  Map<String, ElementEntity> memory;
	private String smartspace;
	private String elementid;

	public MemoryElementDao() {
		this.memory = Collections.synchronizedSortedMap(new TreeMap<>());
		
	}
	
	@Value("${smartspace.name:smartspace}")
	public void setSmartspace(String smartspace) {
		this.smartspace = smartspace;
	}
	@Value("${ElementId.name:0}")
	public void setElemnetId(String elementid) {
		this.elementid=elementid;
	}
	
	@Override
	public ElementEntity create(ElementEntity elementEntity) {
		elementEntity.setKey(smartspace + "!" + elementid);
		this.memory.put(elementEntity.getKey(), elementEntity);
		return elementEntity;
	}

	@Override
	public Optional<ElementEntity> readById(String elementkey) {
		ElementEntity elemntEntity = this.memory.get(elementkey);
		if (elemntEntity != null) {
			return Optional.of(elemntEntity);
		}else {
			return Optional.empty();
		}
	}

	@Override
	public List<ElementEntity> readAll() {
		return new ArrayList<>(this.memory.values());
	}

	@Override
	public void update(ElementEntity elementEntity) {
		ElementEntity existing = 
				this.readById(elementEntity.getKey()).
				orElseThrow(()->new RuntimeException("no message entity with key: " + elementEntity.getKey()));
			
			if (elementEntity.getName() != null) {
				existing.setName(elementEntity.getName());
			}

			if (elementEntity.getMoreAttributes() != null) {
				existing.setMoreAttributes(elementEntity.getMoreAttributes());
			}
			
			if (elementEntity.getCreatorEmail()!= null) {
				existing.setCreatorEmail(elementEntity.getCreatorEmail());
			}
			
			if (elementEntity.getLocation()!=null) {
				existing.setLocation(elementEntity.getLocation());
			}
		
	}

	@Override
	public void deleteByKey(String elementKey) {
		memory.remove(elementKey);
		
	}

	@Override
	public void delete(ElementEntity elementEntity) {
		memory.remove(elementEntity.getKey(),elementEntity);
		
	}

	@Override
	public void deleteAll() {
		memory.clear();
		
	}

	

	

}
