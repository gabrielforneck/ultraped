package order.repository;

import java.util.ArrayList;
import java.util.List;

import application.Program;
import order.EOrderSituation;
import order.Order;
import repositories.exceptions.NotFoundException;
import util.date.DateUtils;

public class OrderRepository {

	private List<Order> orders;

	public OrderRepository() {
		this.orders = new ArrayList<>();
	}
	
	public OrderRepository(List<Order> orders) {
		this.orders = orders;
	}

	public void save(Order order) {
		order.setId(getNextID());
		order.setCreationDate(DateUtils.getCurrentSystemDate());
		orders.add(order);
	}
	public void update(Order order) {
		int oldRecordIndex = getIndexByID(order.getId());
		if (oldRecordIndex == -1)
			throw new NotFoundException("Registro não encontrado.");

		orders.set(oldRecordIndex, order);
	}

	public boolean exists(int iD) {
		return getIndexByID(iD) != -1;
	}

	public int getNextID() {
		int max = 0;
		List<Order> allOrders = Program.applicationData.customerRepository.getAllOrders();

		for (Order o : allOrders)
			if (o.getId() > max)
				max = o.getId();

		return ++max;
	}

	private int getIndexByID(int iD) {

		for (int i = 0; i < orders.size(); i++)
			if (orders.get(i).getId() == iD)
				return i;

		return -1;
	}

	public Order getByID(int iD) {

		for (Order record : orders)
			if (record.getId() == iD)
				return record;

		return null;
	}

	public List<Order> getAll() {
		return orders;
	}
	
	public int getCount() {
		return orders.size();
	}
	
	public List<Order> getOrdersInProgress() {
		List<Order> ordersInProgress = new ArrayList<>();
		
		for (Order o : orders)
			if (o.getSituation() == EOrderSituation.NEW)
				ordersInProgress.add(o);
		
		return ordersInProgress;
	}
}
