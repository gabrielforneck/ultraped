package supplier.validation;

import address.validation.AddressValidation;
import application.Program;
import result.Result;
import supplier.Supplier;

public final class SupplierValidations {

	public static Result validateName(String name) {
		if (name == null || name.length() == 0)
			return Result.failure("O nome do fornecedor não pode ser vazio.");

		return Result.success();
	}

	public static Result validateDescription(String description) {
		if (description == null || description.length() == 0)
			return Result.failure("A descrição do fornecedor não pode ser vazio.");

		return Result.success();
	}

	public static Result validatePhone(String phone) {
		if (phone == null || phone.length() == 0)
			return Result.failure("O telefone do fornecedor não pode ser vazio.");

		return Result.success();
	}

	public static Result validateEmail(String email) {
		if (email == null || email.length() == 0)
			return Result.failure("O e-mail do fornecedor não pode ser vazio.");

		return Result.success();
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

		validationResult = validateEmail(supplier.getEmail());
		if (validationResult.isFailure())
			return validationResult;

		return AddressValidation.validateAll(supplier.getAddress());
	}
	
	public static Result canBeDeleted(Supplier supplier) {
		if (Program.applicationData.supplierRepository.hasSomeProductInSomeOrder(supplier))
			return Result.failure("O fornecedor não pode ser removido pois tem produtos em pedidos.");
		
		return Result.success();
	}
}
