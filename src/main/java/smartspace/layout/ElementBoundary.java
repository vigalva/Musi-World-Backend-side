package smartspace.layout;

import java.util.Date;
import java.util.Map;

import smartspace.data.ElementEntity;
import smartspace.data.Location;


public class ElementBoundary {
	private ElementBoundaryKey key;
	private String name;
	private String elementType;
	private boolean expired;
	private Date created;
	private UserBoundaryKey creator;
	private LatLng latlng;
	private Map<String,Object> elementProperties;
	
	public ElementBoundary() {
		
	}
	
	public ElementBoundary(ElementEntity elementEntity) {
		
		this.key=new ElementBoundaryKey();
		
		this.latlng= new LatLng();
		this.creator=new UserBoundaryKey();
		
		this.key.setId(elementEntity.getElementId());
		this.key.setSmartspace(elementEntity.getElementSmartspace());
		this.name=elementEntity.getName();
		this.elementType=elementEntity.getType();
		this.expired=elementEntity.isExpired();
		this.created=elementEntity.getCreationTimestamp();
		this.creator.setEmail(elementEntity.getCreatorEmail());
		this.creator.setSmartspace(elementEntity.getCreatorSmartspace());
		
		if (elementEntity.getLocation()==null)
			this.latlng=null;
		else {
			latlng.setLan(elementEntity.getLocation().getX());
			latlng.setLat(elementEntity.getLocation().getY());
		}
		this.elementProperties=elementEntity.getMoreAttributes();
		
	}

	public ElementEntity convertToEntity() {
		ElementEntity entity=new ElementEntity();
		
		entity.setCreationTimestamp(this.getCreated());
		entity.setCreatorEmail(this.getCreator().getEmail());
		entity.setCreatorSmartspace(this.getCreator().getSmartspace());
		if (this.getKey()!=null) {
		entity.setElementId(this.getKey().getId());
		entity.setElementSmartspace(this.getKey().getSmartspace());
		}
		entity.setExpired(this.isExpired());
		if (this.latlng==null)
			entity.setLocation(null);
		else {
			entity.setLocation(new Location());
			entity.getLocation().setX(this.getLatlng().getLng());
			entity.getLocation().setY(this.getLatlng().getLat());
		}
		entity.setMoreAttributes(this.getElementProperties());
		entity.setName(this.getName());
		entity.setType(this.getElementType());
		
		return entity;
	
	}
	
	public Date getCreated() {
		return created;
	}
	public UserBoundaryKey getCreator() {
		return creator;
	}
	public Map<String, Object> getElementProperties() {
		return elementProperties;
	}
	public String getElementType() {
		return elementType;
	}
	public ElementBoundaryKey getKey() {
		return key;
	}
	public LatLng getLatlng() {
		return latlng;
	}
	public String getName() {
		return name;
	}
	public boolean isExpired() {
		return expired;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public void setCreator(UserBoundaryKey creator) {
		this.creator = creator;
	}
	public void setElementProperties(Map<String, Object> elementProperties) {
		this.elementProperties = elementProperties;
	}
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	public void setKey(ElementBoundaryKey key) {
		this.key = key;
	}
	public void setLatlng(LatLng latlng) {
		this.latlng = latlng;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
