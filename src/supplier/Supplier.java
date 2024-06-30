package supplier;

import java.util.ArrayList;
import java.util.List;

import address.Address;
import address.validation.AddressValidation;
import products.Product;
import products.validation.ProductValidation;
import result.Result;
import supplier.validation.SupplierValidations;

public class Supplier {
	private int id;
	private String name;
	private String description;
	private String phone;
	private String email;
	private Address address;
	private List<Product> products;

	public Supplier() {
		super();
		this.address = new Address();
		this.products = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Result setName(String name) {
		Result result = SupplierValidations.validateName(name);
		if (result.isFailure())
			return result;

		this.name = name;
		return Result.success();
	}

	public String getDescription() {
		return description;
	}

	public Result setDescription(String description) {
		Result result = SupplierValidations.validateDescription(description);
		if (result.isFailure())
			return result;

		this.description = description;
		return Result.success();
	}

	public String getPhone() {
		return phone;
	}

	public Result setPhone(String phone) {
		Result result = SupplierValidations.validatePhone(phone);
		if (result.isFailure())
			return result;

		this.phone = phone;
		return Result.success();
	}

	public String getEmail() {
		return email;
	}

	public Result setEmail(String email) {
		Result result = SupplierValidations.validateEmail(email);
		if (result.isFailure())
			return result;

		this.email = email;
		return Result.success();
	}

	public Address getAddress() {
		return address;
	}

	public Result setAddress(Address address) {
		Result result = AddressValidation.validateAll(address);
		if (result.isFailure())
			return result;
		
		this.address = address;
		return Result.success();
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	@Deprecated
	public Result setProducts(List<Product> products) {
		
		for (Product p : products) {
			Result validation = ProductValidation.validateAll(p);
			if (validation.isFailure())
				return validation;
		}
		
		this.products = products;
		
		return Result.success();
	}

	@Override
	public String toString() {
		return "ID: " + (id == 0 ? "?" : id) + "\n"
				+ "Nome: " + (name == null ? "" : name) + "\n"
				+ "Descrição: " + (description == null ? "" : description) + "\n"
				+ "Telefone: " + (phone == null ? "" : phone) + "\n"
				+ "E-mail: " + (email == null ? "" : email) + "\n"
				+ "Endereço: " + (address == null ? "" : address.toString());
	}
}
