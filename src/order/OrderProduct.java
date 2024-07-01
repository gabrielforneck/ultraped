package order;

import com.google.gson.annotations.SerializedName;

import order.validation.OrderProductValidation;
import products.Product;
import result.Result;

public class OrderProduct {

	@SerializedName("product")
	private Product product;
	
	@SerializedName("quantity")
	private int quantity;
	
	public OrderProduct() {
	}

	public OrderProduct(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public Result setProduct(Product product) {
		Result validationResult = OrderProductValidation.validateProduct(product);
		if (validationResult.isFailure())
			return validationResult;

		this.product = product;
		
		return Result.success();
	}

	public int getQuantity() {
		return quantity;
	}

	public Result setQuantity(int quantity) {
		Result validationResult = OrderProductValidation.validateQuantity(quantity, product);
		if (validationResult.isFailure())
			return validationResult;
		
		this.quantity = quantity;
		return Result.success();
	}
	
	public double getTotalValue() {
		if (product == null)
			return 0.0;
		
		return product.getStock().getPrice() * quantity;
	}
	
	@Override
	public String toString() {
		return (product == null ? "" : (product.toString() + "\n\n"))
				+ "Quantidade: " + quantity + "\n"
				+ (product == null ? "" : ("Total do item: R$ " + getTotalValue()));
	}
}
