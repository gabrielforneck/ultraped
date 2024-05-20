package repositories;

import java.util.List;

public class Repository<T extends EntityWithID> {
	private List<T> data;

	public Repository(List<T> data) {
		super();
		this.data = data;
	}
	
	public void saveNew(T newRecord) {
		newRecord.setId(getNextID());
		data.add(newRecord);
	}
	
	public int getNextID() {
		int max = 0;
		
		for (T record : data)
			if (record.getId() > max)
				max = record.getId();
		
		return ++max;
	}
	
	public List<T> getData() {
		return data;
	}
}
