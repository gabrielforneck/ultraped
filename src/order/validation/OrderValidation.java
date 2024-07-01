package order.validation;

import order.Order;
import order.OrderProduct;
import result.Result;

public final class OrderValidation {
	public static Result productValidation(OrderProduct p, Order currentOrder) {
		if (p == null)
			return Result.failure("Não pode ser adicionado um produto vazio à lista do pedido.");
		
		Result validationResult = OrderProductValidation.validateAll(p);
		if (validationResult.isFailure())
			return validationResult;
		
		if (currentOrder.orderProductExists(p.getProduct().getId()))
			return Result.failure("Esse item já existe.");
		
		return Result.success();
	}
	
	public static Result validateAll(Order order) {
		if (order.getOrderProductsQuantity() == 0)
			return Result.failure("O pedido deve ter pelo menos um produto.");
		
		return Result.success();
	}
}
