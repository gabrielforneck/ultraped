package products;

import products.validation.ProductValidation;
import repositories.EntityWithID;
import result.Result;

public class Product extends EntityWithID {
	private String name;
	private String description;

	public Product() {
	}

	public String getName() {
		return name;
	}

	public Result setName(String name) {
		Result result = ProductValidation.validateName(name);
		if (result.isFailure())
			return result;
		
		this.name = name;
		
		return Result.Success();
	}

	public String getDescription() {
		return description;
	}

	public Result setDescription(String description) {
		Result result = ProductValidation.validateDescription(description);
		if (result.isFailure())
			return result;
		
		this.description = description;
		
		return Result.Success();
	}

	@Override
	public String toString() {
		return "ID: " + (id == 0 ? "?" : id) + "\n"
				+ "Nome: " + (name == null ? "" : name) + "\n"
				+ "Descrição: " + (description == null ? "" : description);
	}

}
