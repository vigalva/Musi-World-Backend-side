package smartspace.logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import smartspace.aop.RoleValidation;
import smartspace.dao.AdvancedActionDao;
import smartspace.dao.AdvancedElementDao;
import smartspace.dao.AdvancedUserDao;
import smartspace.data.ActionEntity;
import smartspace.data.ElementEntity;
import smartspace.data.UserEntity;
import smartspace.data.UserRole;

@Service

public class ActionServiceImpl implements ActionService, LineListener {
	private AdvancedActionDao<String> ActionsDao;
	private AdvancedElementDao<String> ElementDao;
	private AdvancedUserDao<String> userDao;
	private boolean isPlayCompleted = false;
	
	
	public ActionServiceImpl(AdvancedActionDao<String> ActionsDao, AdvancedElementDao<String> ElementDao,
			AdvancedUserDao<String> userDao) {
		this.ActionsDao = ActionsDao;
		this.ElementDao = ElementDao;
		this.userDao = userDao;
	}

	@RoleValidation(permissions = { UserRole.ADMIN })
	@Transactional
	@Override
	public List<ActionEntity> importActions(String smartspace, String email, List<ActionEntity> actions) {
		List<ActionEntity> actionEntites = new ArrayList<ActionEntity>();
		List<ElementEntity> elements = this.ElementDao.readAll();
		boolean foundElement = false;
		for (ActionEntity action : actions) {
			for (ElementEntity element : elements) {
				if (element.getElementSmartspace().equals(action.getElementSmartspace())) {
					foundElement = true;
					break;
				}
			}
			if (!foundElement)
				throw new RuntimeException("You are trying to import action with no element");
			else {
				if (action.getActionSmartspace().equals("inbala1"))
					throw new RuntimeException("You are trying to import actions from your project");
				else
					actionEntites.add(this.ActionsDao.create(action));

			}
		}
		return actionEntites;
	}

	@RoleValidation(permissions = { UserRole.ADMIN })
	@Override
	public List<ActionEntity> ExportActions(String smartspace, String email, int size, int page) {
		return this.ActionsDao.readAll(size, page);
	}

	@RoleValidation(permissions = { UserRole.PLAYER })
	@Override
	public ActionEntity invokeAction(String smartspace, String email, ActionEntity convertToEntity) {
		// TODO Auto-validate methodes
		if (this.ElementDao.readById(convertToEntity.getElementSmartspace()+"!"+convertToEntity.getElementId()).get().isExpired()==true) {
			return new ActionEntity();
		}
		convertToEntity.setCreationTimestamp(new Date());
		UserEntity user = this.userDao.readById(smartspace + "!" + email).get();
		user.setPoints(user.getPoints() + 10);
		this.userDao.update(user);

		if (convertToEntity.getActionType().equals("check in")) {
			ElementEntity entity = this.ElementDao
					.readById(convertToEntity.getElementSmartspace() + convertToEntity.getElementId()).get();
			Map<String, Object> properties = entity.getMoreAttributes();
			int numOfCheckIns = (int) properties.get("check in");
			numOfCheckIns++;
			properties.replace("check in", numOfCheckIns);
			entity.setMoreAttributes(properties);
			this.ElementDao.update(entity);
		} 
		else if (convertToEntity.getActionType().equals("check out")) {
			ElementEntity entity = this.ElementDao
					.readById(convertToEntity.getElementSmartspace() + convertToEntity.getElementId()).get();
			Map<String, Object> properties = entity.getMoreAttributes();
			int numOfCheckIns = (int) properties.get("check out");
			numOfCheckIns++;
			properties.replace("check out", numOfCheckIns);
			entity.setMoreAttributes(properties);
			this.ElementDao.update(entity);

		}

		else if (convertToEntity.getActionType().equals("buy tickets")) {
			ElementEntity entity = this.ElementDao
					.readById(convertToEntity.getElementSmartspace() + "!"+convertToEntity.getElementId()).get();
			Map<String, Object> properties = entity.getMoreAttributes();
			int numOfTickets = (int) properties.get("tickets");
			numOfTickets--;
			properties.replace("tickets", numOfTickets);
			entity.setMoreAttributes(properties);
			this.ElementDao.update(entity);
		} 
	
		else if (convertToEntity.getActionType().equals("cancel tickets")) {
			ElementEntity entity = this.ElementDao
					.readById(convertToEntity.getElementSmartspace() + "!"+convertToEntity.getElementId()).get();
			Map<String, Object> properties = entity.getMoreAttributes();
			int numOfTickets = (int) properties.get("tickets");
			numOfTickets++;
			properties.replace("tickets", numOfTickets);
			entity.setMoreAttributes(properties);
			this.ElementDao.update(entity);
		} 
		else if (convertToEntity.getActionType().equals("rate album")) {
			ElementEntity entity = this.ElementDao
					.readById(convertToEntity.getElementSmartspace() + "!"+convertToEntity.getElementId()).get();
			Map<String, Object> properties = entity.getMoreAttributes();
			int rating = (int) properties.get("rating");
			rating++;
			properties.replace("rating", rating);
			entity.setMoreAttributes(properties);
			this.ElementDao.update(entity);
		} 
		else if (convertToEntity.getActionType().equals("preview")) {
			ElementEntity entity = this.ElementDao
					.readById(convertToEntity.getElementSmartspace() + "!"+convertToEntity.getElementId()).get();
			Map<String, Object> properties = entity.getMoreAttributes();
			String preview = (String) properties.get("priview");
			
			File audioFile = new File(preview);
			
			try {
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

				AudioFormat format = audioStream.getFormat();

				DataLine.Info info = new DataLine.Info(Clip.class, format);

				Clip audioClip = (Clip) AudioSystem.getLine(info);

				audioClip.addLineListener(this);

				audioClip.open(audioStream);

				audioClip.start();

				while (!isPlayCompleted) {
					// wait for the playback completes
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}

				audioClip.close();

			} catch (UnsupportedAudioFileException ex) {
				System.out.println("The specified audio file is not supported.");
				ex.printStackTrace();
			} catch (LineUnavailableException ex) {
				System.out.println("Audio line for playing back is unavailable.");
				ex.printStackTrace();
			} catch (IOException ex) {
				System.out.println("Error playing the audio file.");
				ex.printStackTrace();
			}

		}

		return this.ActionsDao.create(convertToEntity);
	}

	@Override
	public void update(LineEvent event) {
		LineEvent.Type type = event.getType();

		if (type == LineEvent.Type.START) {
			System.out.println("Playback started.");

		} else if (type == LineEvent.Type.STOP) {
			isPlayCompleted = true;
			System.out.println("Playback completed.");
		}

	}
}
