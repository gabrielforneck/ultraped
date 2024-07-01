package customer.repository;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import customer.Customer;
import repositories.exceptions.NotFoundException;

public class CostumerRepository {
	@SerializedName("costumers")
	private List<Customer> costumers;

	public CostumerRepository() {
		this.costumers = new ArrayList<>();
	}
	
	public CostumerRepository(List<Customer> costumers) {
		this.costumers = costumers;
	}

	public void save(Customer costumer) {
		costumer.setId(getNextID());
		costumers.add(costumer);
	}

	public void update(Customer costumer) {
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

		for (Customer record : costumers)
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

	public Customer getByID(int iD) {

		for (Customer record : costumers)
			if (record.getId() == iD)
				return record;

		return null;
	}

	public List<Customer> getAll() {
		return costumers;
	}
	
	public int getCount() {
		return costumers.size();
	}
	
	public List<Customer> getByName(String nameFilter) {
		if (nameFilter == null)
			return new ArrayList<Customer>();
		
		List<Customer> searchResult = new ArrayList<>();
		
		for (Customer c : costumers)
			if (c.getName().contains(nameFilter))
				searchResult.add(c);
		
		return searchResult;
	}
	
	public Customer getByEmail(String email) {

		for (Customer c : costumers) {
			if (c.getEmail().equalsIgnoreCase(email))
				return c;
		}
		
		return null;
	}
	
	//TODO: getAllOrders
}
