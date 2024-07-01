package customer.validation;

import address.validation.AddressValidation;
import application.Program;
import customer.Customer;
import result.Result;

public final class CustomerValidation {
	public static Result validateName(String name) {
		if (name == null || name.length() == 0)
			return Result.failure("O nome do cliente deve ser preenchido.");
		
		return Result.success();
	}
	
	public static Result validatePhone(String phone) {
		if (phone == null || phone.length() == 0)
			return Result.failure("O telefone do cliente deve ser preenchido.");
		
		return Result.success();
	}
	
	public static Result validateEmail(String email, int currentCostumerID) {
		if (email == null || email.length() == 0)
			return Result.failure("O e-mail do cliente deve ser preenchido.");
		
		if (!email.contains("@"))
			return Result.failure("E-mail inválido.");
		
		Customer c = Program.applicationData.costumerRepository.getByEmail(email);
		if (c != null && c.getId() != currentCostumerID)
			return Result.failure("Já exite um cliente cadastrado com este e-mail.");
		
		return Result.success();
	}
	
	public static Result validateCreditCard(String creditCard) {
		if (creditCard == null || creditCard.length() == 0)
			return Result.failure("O cartão de crédito do cliente deve ser preenchido.");
		
		return Result.success();
	}
	
	public static Result validateAll(Customer costumer) {
		Result validationResult;
		
		if ((validationResult = validateName(costumer.getName())).isFailure())
			return validationResult;
		
		if ((validationResult = validatePhone(costumer.getPhone())).isFailure())
			return validationResult;
		
		if ((validationResult = validateEmail(costumer.getEmail(), costumer.getId())).isFailure())
			return validationResult;
		
		if ((validationResult = validateCreditCard(costumer.getCreditCard())).isFailure())
			return validationResult;
		
		return AddressValidation.validateAll(costumer.getAddress());
	}
}
