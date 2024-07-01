package order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import order.validation.OrderValidation;
import result.Result;

public class Order {
	private int id;
	private Date creationDate;
	private Date deliveryDate;
	private List<OrderProduct> products;
	private EOrderSituation situation;
	
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
		
		situation = EOrderSituation.DELIVERED;
		return Result.success();
	}
	
	public double getTotalPrice() {
		double total = 0.0;
		
		for (OrderProduct p : products) {
			total += p.getTotalValue();
		}
		
		return total;
	}
	
	@Override
	public String toString() {
		return "ID: " + (id == 0 ? "?" : id) + "\n"
				+ "Quantidade de itens: " + getOrderProductsQuantity() + "\n"
				+ "Total do pedido: " + getTotalPrice();
	}
}
