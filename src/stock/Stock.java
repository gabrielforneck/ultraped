package stock;

import com.google.gson.annotations.SerializedName;

import result.Result;
import stock.validation.StockValidation;

public class Stock {
	@SerializedName("quantity")
	public int quantity;
	
	@SerializedName("price")
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
		return Result.success();
	}
	
	public Result remQuantity(int quantity) {
		Result result = StockValidation.validateRemQuantity(this, quantity);
		if (result.isFailure())
			return result;
		
		this.quantity -= quantity;
		return Result.success();
	}

	public double getPrice() {
		return price;
	}

	public Result setPrice(double price) {
		Result result = StockValidation.validatePrice(price);
		if (result.isFailure())
			return result;
		
		this.price = price;
		return Result.success();
	}
	
	@Override
	public String toString() {
		return "Quantidade: " + quantity
				+ ", preço: " + price;
	}
	
}
