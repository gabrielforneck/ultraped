package supplier.repository;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import repositories.exceptions.NotFoundException;
import supplier.Supplier;

public class SupplierRepository {

	@SerializedName("suppliers")
	private List<Supplier> suppliers;

	public SupplierRepository() {
		this.suppliers = new ArrayList<>();
	}
	
	public SupplierRepository(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public void save(Supplier supplier) {
		supplier.setId(getNextID());
		suppliers.add(supplier);
	}

	public void update(Supplier supplier) {
		int oldRecordIndex = getIndexByID(supplier.getId());
		if (oldRecordIndex == -1)
			throw new NotFoundException("Registro não encontrado.");

		suppliers.set(oldRecordIndex, supplier);
	}

	public void delete(int id) {
		int recordIndex = getIndexByID(id);
		if (recordIndex == -1)
			throw new NotFoundException("Registro não encontrado.");

		suppliers.remove(recordIndex);
	}

	public boolean exists(int iD) {
		return getIndexByID(iD) != -1;
	}

	public int getNextID() {
		int max = 0;

		for (Supplier record : suppliers)
			if (record.getId() > max)
				max = record.getId();

		return ++max;
	}

	private int getIndexByID(int iD) {

		for (int i = 0; i < suppliers.size(); i++)
			if (suppliers.get(i).getId() == iD)
				return i;

		return -1;
	}

	public Supplier getByID(int iD) {

		for (Supplier record : suppliers)
			if (record.getId() == iD)
				return record;

		return null;
	}

	public List<Supplier> getAll() {
		return suppliers;
	}
	
	public int getCount() {
		return suppliers.size();
	}
	
	public List<Supplier> getByName(String nameFilter) {
		if (nameFilter == null)
			return new ArrayList<Supplier>();
		
		List<Supplier> searchResult = new ArrayList<Supplier>();
		
		for (Supplier s : suppliers)
			if (s.getName().contains(nameFilter))
				searchResult.add(s);
		
		return searchResult;
	}
}
