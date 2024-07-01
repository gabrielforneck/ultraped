package customer.repository;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import customer.Customer;
import repositories.exceptions.NotFoundException;

public class CustomerRepository {
	@SerializedName("customers")
	private List<Customer> customers;

	public CustomerRepository() {
		this.customers = new ArrayList<>();
	}
	
	public CustomerRepository(List<Customer> customers) {
		this.customers = customers;
	}

	public void save(Customer customer) {
		customer.setId(getNextID());
		customers.add(customer);
	}

	public void update(Customer customer) {
		int oldRecordIndex = getIndexByID(customer.getId());
		if (oldRecordIndex == -1)
			throw new NotFoundException("Registro não encontrado.");

		customers.set(oldRecordIndex, customer);
	}

	public void delete(int id) {
		int recordIndex = getIndexByID(id);
		if (recordIndex == -1)
			throw new NotFoundException("Registro não encontrado.");

		customers.remove(recordIndex);
	}

	public boolean exists(int iD) {
		return getIndexByID(iD) != -1;
	}

	public int getNextID() {
		int max = 0;

		for (Customer record : customers)
			if (record.getId() > max)
				max = record.getId();

		return ++max;
	}

	private int getIndexByID(int iD) {

		for (int i = 0; i < customers.size(); i++)
			if (customers.get(i).getId() == iD)
				return i;

		return -1;
	}

	public Customer getByID(int iD) {

		for (Customer record : customers)
			if (record.getId() == iD)
				return record;

		return null;
	}

	public List<Customer> getAll() {
		return customers;
	}
	
	public int getCount() {
		return customers.size();
	}
	
	public List<Customer> getByName(String nameFilter) {
		if (nameFilter == null)
			return new ArrayList<Customer>();
		
		List<Customer> searchResult = new ArrayList<>();
		
		for (Customer c : customers)
			if (c.getName().contains(nameFilter))
				searchResult.add(c);
		
		return searchResult;
	}
	
	public Customer getByEmail(String email) {

		for (Customer c : customers) {
			if (c.getEmail().equalsIgnoreCase(email))
				return c;
		}
		
		return null;
	}
	
	//TODO: getAllOrders
}
