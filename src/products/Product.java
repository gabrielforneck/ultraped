package products;

import products.validation.ProductValidation;
import result.Result;
import stock.Stock;

public class Product {
	private int id;
	private String name;
	private String description;
	private Stock stock;

	public Product() {
		this.stock = new Stock(0, 0.0);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Result setName(String name) {
		Result result = ProductValidation.validateName(name);
		if (result.isFailure())
			return result;
		
		this.name = name;
		
		return Result.success();
	}

	public String getDescription() {
		return description;
	}

	public Result setDescription(String description) {
		Result result = ProductValidation.validateDescription(description);
		if (result.isFailure())
			return result;
		
		this.description = description;
		
		return Result.success();
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ID: " + (id == 0 ? "?" : id) + "\n"
				+ "Nome: " + (name == null ? "" : name) + "\n"
				+ "Descrição: " + (description == null ? "" : description);
	}

}
