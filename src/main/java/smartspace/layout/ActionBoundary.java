package smartspace.layout;

import java.util.Date;
import java.util.Map;

import smartspace.data.ActionEntity;

public class ActionBoundary {

	private ActionBoundaryKey key;
	private ElementBoundaryKey elementKey;
	private UserBoundaryKey userKey;
	private String playerSmartspace;
	private String playerEmail;
	private Date creationTimestamp;
	private Map<String, Object> moreAttributes;

	public ActionBoundary() {
	}

	public ActionBoundary(ActionEntity action) {
		this.key.setSmartspace(action.getActionSmartspace());
		this.key.setActionId(action.getActionId());
		this.key.setActionType(action.getActionType());
		this.elementKey.setSmartspace(action.getElementSmartspace());
		this.elementKey.setId(action.getElementId());
		this.userKey.setSmartspace(action.getPlayerSmatspace());
		this.userKey.setEmail(action.getPlayerEmail());
		this.creationTimestamp = action.getCreationTimestamp();
		this.moreAttributes = action.getMoreAttributes();
	}

	public ActionEntity convertToEntity() {
		ActionEntity action = new ActionEntity();
		action.setKey(this.key.getSmartspace() + "#" + this.key.getActionId());
		action.setActionSmartspace(this.getActionSmartspace());
		action.setActionId(this.getActionId());
		action.setElementSmartspace(this.getElementSmartspace());
		action.setElementId(this.getElementId());
		action.setPlayerSmatspace(this.getPlayerSmartspace());
		action.setPlayerEmail(this.getPlayerEmail());
		action.setActionType(this.getActionType());
		action.setCreationTimestamp(this.getCreationTimestamp());
		action.setMoreAttributes(this.getMoreAttributes());

		return action;
	}

	@Override
	public String toString() {
		return "ActionBoundary [actionSmartspace=" + key.getSmartspace() + ", userId=" + key.getActionId() + ", elementSmartspace="
				+ elementKey.getSmartspace() + ", elementId=" + elementKey.getId() + ", playerSmartspace=" + playerSmartspace
				+ ", playerEmail=" + playerEmail + ",actionType=" + key.getActionType() + ",creationTimestamp"
				+ creationTimestamp + ",moreAttributes" + moreAttributes + "]";
	}

	public String getActionSmartspace() {
		return key.getSmartspace();
	}

	public void setActionSmartspace(String actionSmartspace) {
		key.setSmartspace(actionSmartspace);
	}

	public String getActionId() {
		return key.getActionId();
	}

	public void setActionId(String actionId) {
		key.setActionId(actionId);
	}

	public String getElementSmartspace() {
		return elementKey.getSmartspace();
	}

	public void setElementSmartspace(String elementSmartspace) {
		elementKey.setSmartspace(elementSmartspace);
	}

	public String getElementId() {
		return elementKey.getId();
	}

	public void setElementId(String elementId) {
		elementKey.setId(elementId);
	}

	public String getPlayerSmartspace() {
		return userKey.getSmartspace();
	}

	public void setPlayerSmartspace(String playerSmartspace) {
		userKey.setSmartspace(playerSmartspace);
	}

	public String getPlayerEmail() {
		return userKey.getEmail();
	}

	public void setPlayerEmail(String playerEmail) {
		userKey.setEmail(playerEmail);
	}

	public String getActionType() {
		return key.getActionType();
	}

	public void setActionType(String actionType) {
		key.setActionType(actionType);
	}

	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public Map<String, Object> getMoreAttributes() {
		return moreAttributes;
	}

	public void setMoreAttributes(Map<String, Object> moreAttributes) {
		this.moreAttributes = moreAttributes;
	}
}