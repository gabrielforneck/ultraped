package products.repository;

import java.util.ArrayList;
import java.util.List;

import application.Program;
import products.Product;
import repositories.exceptions.NotFoundException;

public class ProductRepository {

	private List<Product> products;

	public ProductRepository() {
		this.products = new ArrayList<>();
	}
	
	public ProductRepository(List<Product> products) {
		this.products = products;
	}

	public void save(Product product) {
		product.setId(getNextID());
		products.add(product);
	}

	public void update(Product product) {
		int oldRecordIndex = getIndexByID(product.getId());
		if (oldRecordIndex == -1)
			throw new NotFoundException("Registro não encontrado.");

		products.set(oldRecordIndex, product);
	}

	public void delete(int id) {
		int recordIndex = getIndexByID(id);
		if (recordIndex == -1)
			throw new NotFoundException("Registro não encontrado.");

		products.remove(recordIndex);
	}

	public boolean exists(int iD) {
		return getIndexByID(iD) != -1;
	}

	public int getNextID() {
		int max = 0;
		List<Product> allProducts = Program.applicationData.supplierRepository.getAllProducts();

		for (Product p : allProducts)
			if (p.getId() > max)
				max = p.getId();

		return ++max;
	}

	private int getIndexByID(int iD) {

		for (int i = 0; i < products.size(); i++)
			if (products.get(i).getId() == iD)
				return i;

		return -1;
	}

	public Product getByID(int iD) {

		for (Product record : products)
			if (record.getId() == iD)
				return record;

		return null;
	}

	public List<Product> getAll() {
		return products;
	}
	
	public int getCount() {
		return products.size();
	}
	
	public List<Product> getByName(String nameFilter) {
		if (nameFilter == null)
			return new ArrayList<>();
		
		List<Product> searchResult = new ArrayList<>();
		
		for (Product p : products)
			if (p.getName().contains(nameFilter))
				searchResult.add(p);
		
		return searchResult;
	}
	
	public List<Product> getByIDOrNameOrDescription(String filter) {
		int idFilter = 0;
		List<Product> searchResult = new ArrayList<>();
		
		try {
			idFilter = Integer.parseInt(filter);
		} catch (NumberFormatException ex) {
			idFilter = 0;
		}
		
		for (Product p : products)
			if (p.getId() == idFilter || p.getName().toLowerCase().contains(filter.toLowerCase()) || p.getDescription().toLowerCase().contains(filter.toLowerCase()))
				searchResult.add(p);
		
		return searchResult;
	}
	
	public List<Product> getAllWithStock() {
		List<Product> productsWithStock = new ArrayList<>();
		
		for (Product p : products)
			if (p.getStock().getQuantity() > 0)
				productsWithStock.add(p);
		
		return productsWithStock;
	}
	
	public void setData(List<Product> products) {
		this.products = products;
	}

}
