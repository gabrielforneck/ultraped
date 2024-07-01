package costumer.repository;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import costumer.Costumer;
import repositories.exceptions.NotFoundException;

public class CostumerRepository {
	@SerializedName("costumers")
	private List<Costumer> costumers;

	public CostumerRepository() {
		this.costumers = new ArrayList<>();
	}
	
	public CostumerRepository(List<Costumer> costumers) {
		this.costumers = costumers;
	}

	public void save(Costumer costumer) {
		costumer.setId(getNextID());
		costumers.add(costumer);
	}

	public void update(Costumer costumer) {
		int oldRecordIndex = getIndexByID(costumer.getId());
		if (oldRecordIndex == -1)
			throw new NotFoundException("Registro não encontrado.");

		costumers.set(oldRecordIndex, costumer);
	}

	public void delete(int id) {
		int recordIndex = getIndexByID(id);
		if (recordIndex == -1)
			throw new NotFoundException("Registro não encontrado.");

		costumers.remove(recordIndex);
	}

	public boolean exists(int iD) {
		return getIndexByID(iD) != -1;
	}

	public int getNextID() {
		int max = 0;

		for (Costumer record : costumers)
			if (record.getId() > max)
				max = record.getId();

		return ++max;
	}

	private int getIndexByID(int iD) {

		for (int i = 0; i < costumers.size(); i++)
			if (costumers.get(i).getId() == iD)
				return i;

		return -1;
	}

	public Costumer getByID(int iD) {

		for (Costumer record : costumers)
			if (record.getId() == iD)
				return record;

		return null;
	}

	public List<Costumer> getAll() {
		return costumers;
	}
	
	public int getCount() {
		return costumers.size();
	}
	
	public List<Costumer> getByName(String nameFilter) {
		if (nameFilter == null)
			return new ArrayList<Costumer>();
		
		List<Costumer> searchResult = new ArrayList<>();
		
		for (Costumer c : costumers)
			if (c.getName().contains(nameFilter))
				searchResult.add(c);
		
		return searchResult;
	}
	
	public Costumer getByEmail(String email) {

		for (Costumer c : costumers) {
			if (c.getEmail().equalsIgnoreCase(email))
				return c;
		}
		
		return null;
	}
	
	//TODO: getAllOrders
}
