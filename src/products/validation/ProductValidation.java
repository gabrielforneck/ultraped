package products.validation;

import products.Product;
import result.Result;

public final class ProductValidation {
	public static Result validateName(String name) {
		if (name == null || name.length() == 0)
			return Result.Failure("O nome do produto não pode ser vazio.");
		
		return Result.Success();
	}
	
	public static Result validateDescription(String description) {
		if (description == null || description.length() == 0)
			return Result.Failure("A descrição do produto não pode ser vazio.");

		return Result.Success();
	}
	
	public static Result validateAll(Product product) {
		Result validationResult = validateName(product.getName());
		if (validationResult.isFailure())
			return validationResult;

		return validateDescription(product.getDescription());
	}
}
