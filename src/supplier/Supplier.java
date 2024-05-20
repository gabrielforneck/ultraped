package supplier;

import repositories.EntityWithID;
import result.Result;
import supplier.validation.SupplierValidations;

public class Supplier extends EntityWithID {
	private String name;
	private String description;
	private String phone;
	private String email;

	public Supplier() {
		super();
	}

	public Supplier(int id, String name, String description, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.phone = phone;
		this.email = email;
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
}
