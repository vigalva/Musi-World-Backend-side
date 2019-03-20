package smartspace.data.util;

import java.util.Date;
import java.util.Map;

import smartspace.data.ActionEntity;
import smartspace.data.ElementEntity;
import smartspace.data.Location;
import smartspace.data.UserEntity;
import smartspace.data.UserRole;

public class EntityFactoryIImplementation implements EntityFactory {

	@Override
	public UserEntity<String> createNewUser(String userEmail, String userSmartspace, String username, String avatar,
			UserRole role, long points) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ElementEntity<String> createNewElement(String name, String type, Location location, Date creationTimeStamp,
			String creatorEmail, String creatorSmartspace, boolean expired, Map<String, Object> moreAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionEntity<String> createNewAction(String elementId, String elementSmartspace, String actionType,
			Date creationTimestamp, String playerEmail, String playerSmartspace, Map<String, Object> moreAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

}
