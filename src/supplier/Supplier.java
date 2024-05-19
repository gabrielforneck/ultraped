package supplier;

import result.Result;
import supplier.validation.SupplierValidations;

public class Supplier {
	private int id;
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
		Result nameResult = SupplierValidations.validateName(name);
		if (!nameResult.isFailure())
			return nameResult;
		
		this.name = name;
		return Result.Success();
	}

	public String getDescription() {
		return description;
	}

	public Result setDescription(String description) {
		if (description == null || description.length() == 0)
			return Result.Failure("A descrição do fornecedor não pode ser vazio.");

		this.description = description;
		return Result.Success();
	}

	public String getPhone() {
		return phone;
	}

	public Result setPhone(String phone) {
		if (phone == null || phone.length() == 0)
			return Result.Failure("O telefone do fornecedor não pode ser vazio.");
		
		this.phone = phone;
		return Result.Success();
	}

	public String getEmail() {
		return email;
	}

	public Result setEmail(String email) {
		if (email == null || email.length() == 0)
			return Result.Failure("O e-mail do fornecedor não pode ser vazio.");

		this.email = email;
		return Result.Success();
	}
}
