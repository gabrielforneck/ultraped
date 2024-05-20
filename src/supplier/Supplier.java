package supplier;

import address.Address;
import address.validation.AddressValidation;
import repositories.EntityWithID;
import result.Result;
import supplier.validation.SupplierValidations;

public class Supplier extends EntityWithID {
	private String name;
	private String description;
	private String phone;
	private String email;
	private Address address;

	public Supplier() {
		super();
		this.address = new Address();
	}

	public String getName() {
		return name;
	}

	public Result setName(String name) {
		Result result = SupplierValidations.validateName(name);
		if (result.isFailure())
			return result;

		this.name = name;
		return Result.Success();
	}

	public String getDescription() {
		return description;
	}

	public Result setDescription(String description) {
		Result result = SupplierValidations.validateDescription(description);
		if (result.isFailure())
			return result;

		this.description = description;
		return Result.Success();
	}

	public String getPhone() {
		return phone;
	}

	public Result setPhone(String phone) {
		Result result = SupplierValidations.validatePhone(phone);
		if (result.isFailure())
			return result;

		this.phone = phone;
		return Result.Success();
	}

	public String getEmail() {
		return email;
	}

	public Result setEmail(String email) {
		Result result = SupplierValidations.validateEmail(email);
		if (result.isFailure())
			return result;

		this.email = email;
		return Result.Success();
	}

	public Address getAddress() {
		return address;
	}

	public Result setAddress(Address address) {
		Result result = AddressValidation.validateAll(address);
		if (result.isFailure())
			return result;
		
		this.address = address;
		return Result.Success();
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
