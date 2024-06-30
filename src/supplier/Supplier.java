package supplier;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import address.Address;
import address.validation.AddressValidation;
import products.Product;
import result.Result;
import supplier.validation.SupplierValidations;

public class Supplier {
	@SerializedName("id")
	private int id;
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("description")
	private String description;
	
	@SerializedName("phone")
	private String phone;
	
	@SerializedName("email")
	private String email;
	
	@SerializedName("address")
	private Address address;
	
	@SerializedName("products")
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
