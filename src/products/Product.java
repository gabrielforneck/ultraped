package products;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import products.validation.ProductValidation;
import result.Result;
import stock.Stock;

public class Product {
	@SerializedName("id")
	private int id;
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("description")
	private String description;
	
	@SerializedName("stock")
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
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "ID: " + (id == 0 ? "?" : id) + "\n"
				+ "Nome: " + (name == null ? "" : name) + "\n"
				+ "Descrição: " + (description == null ? "" : description);
	}

}
