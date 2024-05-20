package address.validation;

import address.Address;
import result.Result;

public final class AddressValidation {

	public static Result streetValidation(String street) {
		if (street == null || street.length() == 0)
			return Result.Failure("A rua não pode ser vazio.");

		return Result.Success();
	}

	public static Result numberValidation(String number) {
		if (number == null || number.length() == 0)
			return Result.Failure("O número não pode ser vazio.");

		return Result.Success();
	}

	public static Result districtValidation(String district) {
		if (district == null || district.length() == 0)
			return Result.Failure("O bairro não pode ser vazio.");

		return Result.Success();
	}

	public static Result cepValidation(String cep) {
		if (cep == null || cep.length() == 0)
			return Result.Failure("O CEP não pode ser vazio.");

		return Result.Success();
	}

	public static Result cityValidation(String city) {
		if (city == null || city.length() == 0)
			return Result.Failure("A cidade não pode ser vazio.");

		return Result.Success();
	}

	public static Result stateValidation(String state) {
		if (state == null || state.length() == 0)
			return Result.Failure("O estado não pode ser vazio.");

		return Result.Success();
	}

	public static Result validateAll(Address address) {
		Result validationResult = streetValidation(address.getStreet());
		if (validationResult.isFailure())
			return validationResult;

		validationResult = numberValidation(address.getNumber());
		if (validationResult.isFailure())
			return validationResult;

		validationResult = districtValidation(address.getDistrict());
		if (validationResult.isFailure())
			return validationResult;

		validationResult = cepValidation(address.getCep());
		if (validationResult.isFailure())
			return validationResult;

		validationResult = cityValidation(address.getCity());
		if (validationResult.isFailure())
			return validationResult;

		return stateValidation(address.getState());
	}
}
