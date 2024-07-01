package order.validation;

import application.Program;
import order.OrderProduct;
import products.Product;
import products.repository.ProductRepository;
import result.Result;

public final class OrderProductValidation {
	
	public static Result validateProduct(Product p) {
		if (p == null)
			return Result.failure("Deve ser selecionado um produto.");
		
		return Result.success();
	}
	
	public static Result validateQuantity(int quantity, Product currentProduct) {
		if (quantity <= 0)
			return Result.failure("Quantidade inválida");
		
		if (currentProduct != null && new ProductRepository(Program.applicationData.supplierRepository.getAllProducts()).getByID(currentProduct.getId()).getStock().getQuantity() < quantity)
			return Result.failure("Quantidade do estoque excedida.");
		
		return Result.success();
	}
	
	public static Result validateAll(OrderProduct orderProduct) {
		Result validationResult;
		if ((validationResult = validateProduct(orderProduct.getProduct())).isFailure())
			return validationResult;
		
		return validateQuantity(orderProduct.getQuantity(), orderProduct.getProduct());
	}
}
