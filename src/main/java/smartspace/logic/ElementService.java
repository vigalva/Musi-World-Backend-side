package smartspace.logic;

import java.util.List;

import smartspace.data.ElementEntity;

public interface ElementService {

	public ElementEntity createElement(ElementEntity convertToEntity);
	public List<ElementEntity> getElement(int size, int page);
	public List<ElementEntity> getMessagesByPattern(String pattern, String sortBy, int size, int page);

}
