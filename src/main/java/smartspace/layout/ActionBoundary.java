package smartspace.layout;

import java.util.Date;
import java.util.Map;

import smartspace.data.ActionEntity;

public class ActionBoundary {

	private ActionBoundaryKey actionKey;
	private ElementBoundaryKey element;
	private UserBoundaryKey player;
	private String type;
	private Date created;
	private Map<String, Object> properties;

	public ActionBoundary() {
	}

	public ActionBoundary(ActionEntity action) {
		
		this.actionKey=new ActionBoundaryKey();
		this.element=new ElementBoundaryKey();
		this.player=new UserBoundaryKey();
		this.actionKey.setId(action.getActionId());
		this.actionKey.setSmartspace(action.getActionSmartspace());
		this.element.setId(action.getElementId());
		this.element.setSmartspace(action.getElementSmartspace());
		this.player.setEmail(action.getPlayerEmail());
		this.player.setSmartspace(action.getPlayerSmatspace());
		this.type=action.getActionType();
		this.created=action.getCreationTimestamp();
		this.properties=action.getMoreAttributes();
	}

	public ActionEntity convertToEntity() {
		ActionEntity action = new ActionEntity();
		
		action.setActionId(this.getActionKey().getId());
		action.setActionSmartspace(this.getActionKey().getSmartspace());
		action.setActionType(this.getType());
		action.setCreationTimestamp(this.getCreated());
		action.setElementId(this.getElement().getId());
		action.setElementSmartspace(this.getElement().getSmartspace());
		action.setMoreAttributes(this.getProperties());
		action.setPlayerEmail(this.getPlayer().getEmail());
		action.setPlayerSmatspace(this.getPlayer().getSmartspace());
		

		return action;
	}
	
	public ActionBoundaryKey getActionKey() {
		return actionKey;
	}
	public Date getCreated() {
		return created;
	}
	public ElementBoundaryKey getElement() {
		return element;
	}
	public Map<String, Object> getProperties() {
		return properties;
	}
	public String getType() {
		return type;
	}
	public UserBoundaryKey getPlayer() {
		return player;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public void setElement(ElementBoundaryKey element) {
		this.element = element;
	}
	public void setActionKey(ActionBoundaryKey actionKey) {
		this.actionKey = actionKey;
	}
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setPlayer(UserBoundaryKey player) {
		this.player = player;
	}
	
	
}