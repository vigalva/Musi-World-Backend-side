package smartspace.data;

import java.util.Date;
import java.util.Map;

public class ElementEntity<K> implements SmartspaceEntity<String>{
	private String elementSmartspace;
	private String elementId;
	private Location location;
	private String name;
	private String type;
	private java.util.Date creationTimestamp;
	private boolean expired;
	private String creatorSmartspace;
	private String creatorEmail;
	private Map<String,Object> moreAttributes;
	
	public ElementEntity(String name, String type, Location location, Date creationTimeStamp,
			String creatorEmail, String creatorSmartspace, boolean expired, Map<String, Object> moreAttributes) {
		this.name=name;
		this.type=type;
		this.location=location;
		this.creationTimestamp=creationTimeStamp;
		this.creatorEmail=creatorEmail;
		this.creatorSmartspace=creatorSmartspace;
		this.expired=expired;
		this.moreAttributes=moreAttributes;
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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

	public ElementEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getKey() {
		return (String) (elementSmartspace+"!"+elementId);
	}

	@Override
	public void setKey(String key) {
		String parts[]=key.split("!");
		this.elementSmartspace=parts[0];
		this.elementId=parts[1];
		
	}

}
