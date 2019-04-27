package smartspace.layout;

import java.util.Map;

import smartspace.data.ElementEntity;
import smartspace.data.Location;


public class ElementBoundary {
	private String key;
	private String elementSmartspace;
	private String elementId;
	private Double locationX;
	private Double locationY;
	private String name;
	private String type;
	private java.util.Date creationTimestamp;
	private boolean expired;
	private String creatorSmartspace;
	private String creatorEmail;
	private Map<String,Object> moreAttributes;
	
	public ElementBoundary() {
		
	}
	
	public ElementBoundary(ElementEntity elementEntity) {
		key = elementEntity.getKey();
		elementSmartspace = elementEntity.getElementSmartspace();
		elementId = elementEntity.getElementId();
		if(elementEntity.getLocation()!=null) {
			locationX = elementEntity.getLocation().getX();
			locationY = elementEntity.getLocation().getY();
		}else {
			locationX = null;
			locationY = null;			
		}
		name = elementEntity.getName();
		type = elementEntity.getType();
		creationTimestamp = elementEntity.getCreationTimestamp();
		expired = elementEntity.isExpired();
		creatorSmartspace = elementEntity.getCreatorSmartspace();
		creatorEmail = elementEntity.getCreatorEmail();
		moreAttributes = elementEntity.getMoreAttributes();
	}

	public ElementEntity convertToEntity() {
		ElementEntity elementEntity = new ElementEntity();
		elementEntity.setElementSmartspace(elementSmartspace);
		elementEntity.setElementId(elementId );
		Location l  = new Location();
		l.setX(locationX);
		l.setY(locationY);
		elementEntity.setLocation(l);
		elementEntity.setName(name);
		elementEntity.setType(type);
		elementEntity.setCreationTimestamp(creationTimestamp);
		elementEntity.setExpired(expired);
		elementEntity.setCreatorSmartspace(creatorSmartspace);
		elementEntity.setCreatorEmail(creatorEmail);
		elementEntity.setMoreAttributes(moreAttributes);
		return elementEntity;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getElementSmartspace() {
		return elementSmartspace;
	}

	public void setElementSmartspace(String elementSmartspace) {
		this.elementSmartspace = elementSmartspace;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public Double getLocationX() {
		return locationX;
	}

	public void setLocationX(Double locationX) {
		this.locationX = locationX;
	}

	public Double getLocationY() {
		return locationY;
	}

	public void setLocationY(Double locationY) {
		this.locationY = locationY;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public java.util.Date getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(java.util.Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public String getCreatorSmartspace() {
		return creatorSmartspace;
	}

	public void setCreatorSmartspace(String creatorSmartspace) {
		this.creatorSmartspace = creatorSmartspace;
	}

	public String getCreatorEmail() {
		return creatorEmail;
	}

	public void setCreatorEmail(String creatorEmail) {
		this.creatorEmail = creatorEmail;
	}

	public Map<String, Object> getMoreAttributes() {
		return moreAttributes;
	}

	public void setMoreAttributes(Map<String, Object> moreAttributes) {
		this.moreAttributes = moreAttributes;
	}
	
}
