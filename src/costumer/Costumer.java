package costumer;

import com.google.gson.annotations.SerializedName;

import address.Address;
import address.validation.AddressValidation;
import costumer.validation.CostumerValidation;
import result.Result;

public class Costumer {
	@SerializedName("id")
	private int id;
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("phone")
	private String phone;
	
	@SerializedName("email")
	private String email;
	
	@SerializedName("creditCard")
	private String creditCard;
	
	@SerializedName("address")
	private Address address;

	public Costumer() {
		address = new Address();
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
		Result validationResult = CostumerValidation.validateName(name);
		if (validationResult.isFailure())
			return validationResult;

		this.name = name;
		return Result.success();
	}

	public String getPhone() {
		return phone;
	}

	public Result setPhone(String phone) {
		Result validationResult = CostumerValidation.validatePhone(phone);
		if (validationResult.isFailure())
			return validationResult;

		this.phone = phone;
		return Result.success();
	}

	public String getEmail() {
		return email;
	}

	public Result setEmail(String email) {
		Result validationResult = CostumerValidation.validateEmail(email);
		if (validationResult.isFailure())
			return validationResult;

		this.email = email;
		return Result.success();
	}

	public String getCreditCard() {
		return creditCard;
	}

	public Result setCreditCard(String creditCard) {
		Result validationResult = CostumerValidation.validateCreditCard(creditCard);
		if (validationResult.isFailure())
			return validationResult;

		this.creditCard = creditCard;
		
		return Result.success();
	}

	public Address getAddress() {
		return address;
	}

	public Result setAddress(Address address) {
		Result validationResult = AddressValidation.validateAll(address);
		if (validationResult.isFailure())
			return validationResult;

		this.address = address;
		
		return Result.success();
	}
	
	@Override
	public String toString() {
		return "ID: " + (id == 0 ? "?" : id) + "\n"
				+ "Nome: " + (name == null ? "" : name) + "\n"
				+ "Telefone: " + (phone == null ? "" : phone) + "\n"
				+ "E-mail: " + (email == null ? "" : email) + "\n"
				+ "Cartão de crédito: " + (creditCard == null ? "" : creditCard) + "\n"
				+ "Endereço: " + (address == null ? "" : address.toString());
	}
}
