package businesslayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable{
	private int orderId;
	private Date date;
	private int table;
	
	public Order(int orderId, int table) {
		this.orderId = orderId;
		this.table = table;
		this.date = new Date();
	}
	
	@Override
	public int hashCode() {
		return orderId;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null) {
			return false;
		}
		if(this.getClass() != o.getClass()) {
			return false;
		}
		Order order = (Order) o;
		return orderId != order.orderId && (!date.equals(order.date)) && table != order.table;
	}
	
	public int getOrderId() {
		return this.orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public int getTable() {
		return this.table;
	}
	
	public void setTable(int table) {
		this.table = table;
	}
}
