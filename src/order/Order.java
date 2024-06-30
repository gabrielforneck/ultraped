package order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	public int getProductsQuantity() {
		return products.size();
	}
	
	public OrderProduct getProduct(int index) {
		return products.get(index);
	}

	public void setProduct(int index, OrderProduct p) {
		products.set(index, p);
	}
	
	public void removeProduct(int index) {
		products.remove(index);
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
}
