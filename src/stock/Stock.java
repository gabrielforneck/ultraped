package stock;

import result.Result;
import stock.validation.StockValidation;

public class Stock {
	public int quantity;
	public double price;
	
	public Stock(int quantity, double price) {
		super();
		this.quantity = quantity;
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public Result addQuantity(int quantity) {
		Result result = StockValidation.validateAddQuantity(quantity);
		if (result.isFailure())
			return result;
		
		this.quantity += quantity;
		return Result.Success();
	}
	
	public Result remQuantity(int quantity) {
		Result result = StockValidation.validateRemQuantity(this, quantity);
		if (result.isFailure())
			return result;
		
		this.quantity -= quantity;
		return Result.Success();
	}

	public double getPrice() {
		return price;
	}

	public Result setPrice(double price) {
		Result result = StockValidation.validatePrice(price);
		if (result.isFailure())
			return result;
		
		this.price = price;
		return Result.Success();
	}
	
	@Override
	public String toString() {
		return "Quantidade: " + quantity
				+ ", preço: " + price;
	}
	
	@Override
	public Stock clone() {
		return new Stock(quantity, price);
	}
	
	public void clear() {
		this.price = 0;
		this.quantity = -1;
	}
	
	public void copyTo(Stock s) {
		s.setPrice(price);
		s.setQuantity(quantity);
	}

	private void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
