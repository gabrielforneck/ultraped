package address;

import com.google.gson.annotations.SerializedName;

import address.validation.AddressValidation;
import result.Result;

public class Address {
	@SerializedName("street")
	private String street;
	
	@SerializedName("number")
	private String number;
	
	@SerializedName("complement")
	private String complement;
	
	@SerializedName("district")
	private String district;
	
	@SerializedName("cep")
	private String cep;
	
	@SerializedName("city")
	private String city;
	
	@SerializedName("state")
	private String state;

	public Address() {
	}

	public Address(String street, String number, String complement, String district, String cep, String city,
			String state) {
		super();
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.district = district;
		this.cep = cep;
		this.city = city;
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public Result setStreet(String street) {
		Result validationResult = AddressValidation.streetValidation(street);
		if (validationResult.isFailure())
			return validationResult;

		this.street = street;

		return Result.success();
	}

	public String getNumber() {
		return number;
	}

	public Result setNumber(String number) {
		Result validationResult = AddressValidation.numberValidation(number);
		if (validationResult.isFailure())
			return validationResult;

		this.number = number;

		return Result.success();
	}

	public String getComplement() {
		return complement;
	}

	public Result setComplement(String complement) {
		this.complement = complement;
		return Result.success();
	}

	public String getDistrict() {
		return district;
	}

	public Result setDistrict(String district) {
		Result validationResult = AddressValidation.districtValidation(district);
		if (validationResult.isFailure())
			return validationResult;

		this.district = district;

		return Result.success();
	}

	public String getCep() {
		return cep;
	}

	public Result setCep(String cep) {
		Result validationResult = AddressValidation.cepValidation(cep);
		if (validationResult.isFailure())
			return validationResult;

		this.cep = cep;

		return Result.success();
	}

	public String getCity() {
		return city;
	}

	public Result setCity(String city) {
		Result validationResult = AddressValidation.cityValidation(city);
		if (validationResult.isFailure())
			return validationResult;

		this.city = city;

		return Result.success();
	}

	public String getState() {
		return state;
	}

	public Result setState(String state) {
		Result validationResult = AddressValidation.stateValidation(state);
		if (validationResult.isFailure())
			return validationResult;

		this.state = state;

		return Result.success();
	}

	@Override
	public String toString() {
		return "Rua " + (street != null ? street : "") + ", número " + (number != null ? number : "")
				+ (complement != null && complement.length() > 0 ? ", complemento " + complement : "") + ", bairro "
				+ (district != null ? district : "") + ", CEP " + (cep != null ? cep : "") + ", cidade " + (city != null ? city : "") + " - " + (state != null ? state : "");
	}
}
