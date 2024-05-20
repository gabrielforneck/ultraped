package supplier.validation;

import result.Result;
import supplier.Supplier;

public final class SupplierValidations {
	
	public static Result validateName(String name) {
		if (name == null || name.length() == 0)
			return Result.Failure("O nome do fornecedor não pode ser vazio.");
		
		return Result.Success();
	}
	
	public static Result validateDescription(String description) {
		if (description == null || description.length() == 0)
			return Result.Failure("A descrição do fornecedor não pode ser vazio.");

		return Result.Success();
	}
	
	public static Result validatePhone(String phone) {
		if (phone == null || phone.length() == 0)
			return Result.Failure("O telefone do fornecedor não pode ser vazio.");
		
		return Result.Success();
	}
	
	public static Result validateEmail(String email) {
		if (email == null || email.length() == 0)
			return Result.Failure("O e-mail do fornecedor não pode ser vazio.");

		return Result.Success();
	}
	
	public static Result validateAll(Supplier supplier) {
		Result validationResult = validateName(supplier.getName());
		if (validationResult.isFailure())
			return validationResult;

		validationResult = validateDescription(supplier.getDescription());
		if (validationResult.isFailure())
			return validationResult;
		
		validationResult = validatePhone(supplier.getPhone());
		if (validationResult.isFailure())
			return validationResult;

		return validateEmail(supplier.getEmail());
	}
}
