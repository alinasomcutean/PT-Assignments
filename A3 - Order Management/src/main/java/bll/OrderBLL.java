package bll;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

import dao.OrderDAO;
import model.Order;

/**
 * This class contains a method for each query for the order table. Each of them calls the specific method from the data access layer and returns the result.
 * @author Alina Somcutean
 *
 */
public class OrderBLL {
	private OrderDAO orderDAO; 
	
	public OrderBLL() {
		orderDAO = new OrderDAO();
	}
	
	public Order findOrderById(int id) {
		Order order = orderDAO.findById(id);
		if(order == null) {
			throw new NoSuchElementException("The order with the id " + id + " was not found!");
		}
		return order;
	}
	
	public List<Order> findAllOrders(){
		List<Order> list = orderDAO.findALL();
		if(list == null) {
			throw new NoSuchElementException("Order table is empty!");
		}
		return list;
	}
	
	public Order insertOrder(Order o) {
		Order order = orderDAO.insert(o);
		if(order == null) {
			throw new NoSuchElementException("There is no order to insert");
		}
		return order;
	}
}
