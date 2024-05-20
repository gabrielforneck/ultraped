package repositories;

import java.util.List;

import repositories.exceptions.NotFoundException;

public class Repository<T extends EntityWithID> {
	private List<T> data;

	public Repository(List<T> data) {
		super();
		this.data = data;
	}

	public void save(T newRecord) {
		newRecord.setId(getNextID());
		data.add(newRecord);
	}

	public void update(T record) {
		int oldRecordIndex = getIndexByID(record.getId());
		if (oldRecordIndex == -1)
			throw new NotFoundException("Registro não encontrado.");

		data.set(oldRecordIndex, record);
	}

	public void delete(T record) {
		int recordIndex = getIndexByID(record.getId());
		if (recordIndex == -1)
			throw new NotFoundException("Registro não encontrado.");

		data.remove(recordIndex);
	}
	
	public boolean exists(int iD) {
		return getIndexByID(iD) != -1;
	}

	public int getNextID() {
		int max = 0;

		for (T record : data)
			if (record.getId() > max)
				max = record.getId();

		return ++max;
	}

	private int getIndexByID(int iD) {

		for (int i = 0; i < data.size(); i++)
			if (data.get(i).getId() == iD)
				return i;

		return -1;
	}

	public T getByID(int iD) {

		for (T record : data)
			if (record.getId() == iD)
				return record;

		return null;
	}

	public List<T> getData() {
		return data;
	}
}
