package address;

import address.validation.AddressValidation;
import result.Result;

public class Address {
	private String street;
	private String number;
	private String complement;
	private String district;
	private String cep;
	private String city;
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
	
	public void clear() {
		this.street = null;
		this.number = null;
		this.district = null;
		this.cep = null;
		this.city = null;
		this.complement = null;
		this.state = null;
	}

	@Override
	public String toString() {
		return "Rua " + (street != null ? street : "") + ", número " + (number != null ? number : "")
				+ (complement != null && complement.length() > 0 ? ", complemento " + complement : "") + ", bairro "
				+ (district != null ? district : "") + ", CEP " + (cep != null ? cep : "") + ", cidade " + (city != null ? city : "") + " - " + (state != null ? state : "");
	}
	
	@Override
	public Address clone() {
		return new Address(street, number, complement, district, cep, city, state);
	}
	
	public void copyTo(Address address) {
		address.setStreet(this.getStreet());
		address.setNumber(this.getNumber());
		address.setComplement(this.getComplement());
		address.setDistrict(this.getDistrict());
		address.setCep(this.getCep());
		address.setCity(this.getCity());
		address.setState(this.getState());
	}
}
