package costumer.validation;

import address.validation.AddressValidation;
import costumer.Costumer;
import result.Result;

public final class CostumerValidation {
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
	
	public static Result validateEmail(String email) {
		if (email == null || email.length() == 0)
			return Result.failure("O e-mail do cliente deve ser preenchido.");
		
		if (!email.contains("@"))
			return Result.failure("E-mail inválido.");
		
		return Result.success();
	}
	
	public static Result validateCreditCard(String creditCard) {
		if (creditCard == null || creditCard.length() == 0)
			return Result.failure("O cartão de crédito do cliente deve ser preenchido.");
		
		return Result.success();
	}
	
	public static Result validateAll(Costumer costumer) {
		Result validationResult;
		
		if ((validationResult = validateName(costumer.getName())).isFailure())
			return validationResult;
		
		if ((validationResult = validatePhone(costumer.getPhone())).isFailure())
			return validationResult;
		
		if ((validationResult = validateEmail(costumer.getEmail())).isFailure())
			return validationResult;
		
		if ((validationResult = validateCreditCard(costumer.getCreditCard())).isFailure())
			return validationResult;
		
		return AddressValidation.validateAll(costumer.getAddress());
	}
}
