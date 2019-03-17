package smartspace.data;

import java.util.Map;

public class ActionEntity<K> implements SmartspaceEntity<K>{
	private String actionSmartspace;
	private String actionId;
	private String elementSmatspace;
	private String elementId;
	private String playerSmatspace;
	private String playerEmail;
	private String actionType;
	private java.util.Date creationTimestamp;
	private Map<String,Object> moreAttributes;

	
	public String getActionSmartspace() {
		return actionSmartspace;
	}

	public void setActionSmartspace(String actionSmartspace) {
		this.actionSmartspace = actionSmartspace;
	}

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
		return playerSmatspace;
	}

	public void setPlayerSmatspace(String playerSmatspace) {
		this.playerSmatspace = playerSmatspace;
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

	public java.util.Date getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(java.util.Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

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

	@Override
	public K getKey() {
		return (K) (playerEmail + actionId);
	}

	@Override
	public void setKey(K key) {
		// TODO Auto-generated method stub
		
	}

}
