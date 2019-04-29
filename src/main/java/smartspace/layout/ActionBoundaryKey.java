package smartspace.layout;

public class ActionBoundaryKey {
	private String actionType;
	private String smartspace;
	private String actionId;

	public ActionBoundaryKey() {
	}

	public ActionBoundaryKey(String actionType) {
		this.actionType = actionType;
	}

	public String getActionType() {
		return actionType;
	}

	public String getSmartspace() {
		return smartspace;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public void setSmartspace(String smartspace) {
		this.smartspace = smartspace;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

}