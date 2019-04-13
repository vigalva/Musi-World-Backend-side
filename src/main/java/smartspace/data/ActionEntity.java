package smartspace.data;

import java.util.Date;
import java.util.Map;

import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import smartspace.dao.rdb.MapToJsonConverter;

@Entity
@Table(name="ACTIONS")
public class ActionEntity implements SmartspaceEntity<String>{
	private String actionSmartspace;
	private String actionId;
	private String elementSmatspace;
	private String elementId;
	private String playerSmartspace;
	private String playerEmail;
	private String actionType;
	private java.util.Date creationTimestamp;
	private Map<String,Object> moreAttributes;

	public ActionEntity(String elementId, String elementSmartspace, String actionType,
			Date creationTimestamp, String playerEmail, String playerSmartspace, Map<String, Object> moreAttributes) {
		this.elementId=elementId;
		this.elementSmatspace=elementSmartspace;
		this.actionType=actionType;
		this.creationTimestamp=creationTimestamp;
		this.playerEmail=playerEmail;
		this.playerSmartspace=playerSmartspace;
		this.moreAttributes=moreAttributes;
	}
	@Transient
	public String getActionSmartspace() {
		return actionSmartspace;
	}

	public void setActionSmartspace(String actionSmartspace) {
		this.actionSmartspace = actionSmartspace;
	}
	@Transient
	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getElementSmartspace() {
		return elementSmatspace;
	}

	public void setElementSmartspace(String elementSmatspace) {
		this.elementSmatspace = elementSmatspace;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getPlayerSmatspace() {
		return playerSmartspace;
	}

	public void setPlayerSmatspace(String playerSmatspace) {
		this.playerSmartspace = playerSmatspace;
	}

	public String getPlayerEmail() {
		return playerEmail;
	}

	public void setPlayerEmail(String playerEmail) {
		this.playerEmail = playerEmail;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public java.util.Date getCreationTimestamp() {
		return creationTimestamp;
	}
	
	public void setCreationTimestamp(java.util.Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	@Lob
	@Convert(converter=MapToJsonConverter.class)
	public Map<String, Object> getMoreAttributes() {
		return moreAttributes;
	}

	public void setMoreAttributes(Map<String, Object> moreAttributes) {
		this.moreAttributes = moreAttributes;
	}

	public ActionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@Override	public String getKey() {
		return  (String) (actionId +"!"+ actionSmartspace);
	}

	@Override
	public void setKey(String key) {
		String[] parts=key.split("!");
		this.actionId=parts[0];
		this.actionSmartspace=parts[1];
		
	}

}
