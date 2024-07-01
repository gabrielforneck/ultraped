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

	public static Result validateEmail(String email, int currentCustomerID) {
		if (email == null || email.length() == 0)
			return Result.failure("O e-mail do cliente deve ser preenchido.");

		if (!email.contains("@"))
			return Result.failure("E-mail inválido.");

		Customer c = Program.applicationData.customerRepository.getByEmail(email);
		if (c != null && c.getId() != currentCustomerID)
			return Result.failure("Já exite um cliente cadastrado com este e-mail.");

		return Result.success();
	}

	public static Result validateCreditCard(String creditCard) {
		if (creditCard == null || creditCard.length() == 0)
			return Result.failure("O cartão de crédito do cliente deve ser preenchido.");

		return Result.success();
	}

	public static Result validateAll(Customer customer) {
		Result validationResult;

		if ((validationResult = validateName(customer.getName())).isFailure())
			return validationResult;

		if ((validationResult = validatePhone(customer.getPhone())).isFailure())
			return validationResult;

		if ((validationResult = validateEmail(customer.getEmail(), customer.getId())).isFailure())
			return validationResult;

		if ((validationResult = validateCreditCard(customer.getCreditCard())).isFailure())
			return validationResult;

		return AddressValidation.validateAll(customer.getAddress());
	}
}
