package bll;

import java.util.List;
import java.util.NoSuchElementException;

import dao.OrderHasProductDAO;
import model.Order;
import model.OrderHasProduct;

/**
 * This class contains a method for each query for the orderHasProduct table. Each of them calls the specific method from the data access layer and returns the result.
 * @author Alina Somcutean
 *
 */
public class OrderHasProductBLL {
	private OrderHasProductDAO orderHasProductDAO;
	
	public OrderHasProductBLL() {
		orderHasProductDAO = new OrderHasProductDAO();
	}
	
	public OrderHasProduct findOrderById(int id) {
		OrderHasProduct order = orderHasProductDAO.findById(id);
		if(order == null) {
			throw new NoSuchElementException("The order with the id " + id + " was not found!");
		}
		return order;
	}
	
	public List<OrderHasProduct> findAllOrders(){
		List<OrderHasProduct> list = orderHasProductDAO.findALL();
		if(list == null) {
			throw new NoSuchElementException("Order table is empty!");
		}
		return list;
	}
	
	public OrderHasProduct insertOrder(OrderHasProduct o) {
		OrderHasProduct order = orderHasProductDAO.insert(o);
		if(order == null) {
			throw new NoSuchElementException("There is no order to insert");
		}
		return order;
	}
}
