package model;

/**
 * This class is a class that contains the ids of the product of every order that is in the database also
 * @author Alina Somcutean
 *
 */
public class OrderHasProduct {
	private int entryId;
	private int orderId;
	private int productId;
	
	/**
	 * Empty constructor
	 */
	public OrderHasProduct() {
		
	}
	
	/**
	 *  Constructor with all the information without the id
	 * @param orderId id of the order
	 * @param productId id of the product
	 */
	public OrderHasProduct(int orderId, int productId) {
		this.orderId = orderId;
		this.productId = productId;
	}
		
	/**
	 *  Constructor with all the information
	 * @param orderId id of the order
	 * @param productId id fo the product
	 */
	public OrderHasProduct(int entryId, int orderId, int productId) {
		this.entryId = entryId;
		this.orderId = orderId;
		this.productId = productId;
	}
	
	public int getEntryId() {
		return this.entryId;
	}
	
	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}
	
	public int getOrderId() {
		return this.orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getProductId() {
		return this.productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	/**
	 * This method returns the id of an order together with the id of the product that is in that order as a String
	 */
	public String toString() {
		return "OrderHasProduct [orderId = " + orderId + ", productId = " + productId + "]";
	}
}
