package stock.validation;

import result.Result;
import stock.Stock;

public final class StockValidation {
	
	public static Result validateAddQuantity(int quantityToAdd) {
		if (quantityToAdd < 0)
			return Result.failure("Você não pode adicionar uma quantidade negativa.");
		
		return Result.success();
	}
	
	public static Result validateRemQuantity(Stock stock, int quantityToRem) {
		if (quantityToRem < 0)
			return Result.failure("Você não pode remover uma quantidade negativa.");
		
		if (stock.getQuantity() - quantityToRem < 0)
			return Result.failure("Quantidade para remover muito alta.");
		
		return Result.success();
	}
	
	public static Result validatePrice(double price) {
		if (price < 0.0)
			return Result.failure("O preço não pode ser negativo.");
		
		return Result.success();
	}
	
	public static Result validateAll(Stock stock) {
		if (stock.getQuantity() < 0)
			return Result.failure("A quantidade em estoque não pode permanecer negativa.");
		
		return validatePrice(stock.getPrice());
	}
	
}
