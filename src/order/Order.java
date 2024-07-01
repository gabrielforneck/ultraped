package order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import consoleinterface.table.ConsoleTable;
import consoleinterface.table.ConsoleTableColumn;
import order.validation.OrderValidation;
import result.Result;
import util.date.DateUtils;

public class Order {
	@SerializedName("id")
	private int id;
	
	@SerializedName("creationDate")
	private Date creationDate;
	
	@SerializedName("deliveryDate")
	private Date deliveryDate;
	
	@SerializedName("products")
	private List<OrderProduct> products;
	
	@SerializedName("situation")
	private EOrderSituation situation;
	
	@SerializedName("price")
	private Double price;
	
	public Order() {
		products = new ArrayList<>();
		situation = EOrderSituation.NEW;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getOrderProductsQuantity() {
		return products.size();
	}
	
	public OrderProduct getOrderProduct(int index) {
		return products.get(index);
	}

	public Result setOrderProduct(int index, OrderProduct p) {
		Result validationResult = OrderValidation.productValidation(p, this);
		if (validationResult.isFailure())
			return validationResult;

		products.set(index, p);
		
		return Result.success();
	}
	
	public void removeOrderProduct(int index) {
		products.remove(index);
	}
	
	public Result addOrderProduct(OrderProduct p) {
		Result validationResult = OrderValidation.productValidation(p, this);
		if (validationResult.isFailure())
			return validationResult;

		products.add(p);
		
		return Result.success();
	}
	
	public boolean orderProductExists(int productID) {
		return getOrderProductIndexByProductID(productID) != -1;
	}
	
	public int getOrderProductIndexByProductID(int productID) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getProduct().getId() == productID)
				return i;
		}
		
		return -1;
	}
	
	public EOrderSituation getSituation() {
		return situation;
	}
	
	public Result cancel() {
		if (situation == EOrderSituation.DELIVERED)
			return Result.failure("O pedido não pode ser cancelado pois já foi entregue.");
		
		if (situation == EOrderSituation.CANCELED)
			return Result.failure("O pedido já está cancelado.");
		
		situation = EOrderSituation.CANCELED;
		return Result.success();
	}
	
	public Result deliver() {
		if (situation == EOrderSituation.DELIVERED)
			return Result.failure("O pedido já foi entregue.");
		
		if (situation == EOrderSituation.CANCELED)
			return Result.failure("O pedido não pode ser entregue pois está cancelado.");
		
		deliveryDate = DateUtils.getCurrentSystemDate();
		situation = EOrderSituation.DELIVERED;
		return Result.success();
	}
	
	public void setTotalPrice(double price) {
		this.price = price;
	}
	
	public double getTotalPrice() {
		if (price != null)
			return price;

		double total = 0.0;
		
		for (OrderProduct p : products) {
			total += p.getTotalValue();
		}
		
		return total * 1.17; //ICMS
	}
	
	public static ConsoleTable<Order> getDefaultConsoleTable() {
		List<ConsoleTableColumn<Order>> columns = new ArrayList<>();
		
		columns.add(new ConsoleTableColumn<Order>(5, "ID", (o) -> o.getId()));
		columns.add(new ConsoleTableColumn<Order>(15, "Criação", (o) -> DateUtils.ApplyDefaultDateFormat(o.getCreationDate())));
		columns.add(new ConsoleTableColumn<Order>(20, "Quantidade de itens", (o) -> o.getId()));
		columns.add(new ConsoleTableColumn<Order>(20, "Total", (o) -> o.getTotalPrice()));

		return new ConsoleTable<Order>(columns);
	}
	
	@Override
	public String toString() {
		return "ID: " + (id == 0 ? "?" : id) + "\n"
				+ "Quantidade de itens: " + getOrderProductsQuantity() + "\n"
				+ "Total do pedido: " + getTotalPrice();
	}
}
