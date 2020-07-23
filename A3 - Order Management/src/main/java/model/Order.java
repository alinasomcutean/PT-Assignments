package model;

/**
 * This is a class that contains the the id of a new order and the id of the client that is in the database also
 * @author Alina Somcutean
 *
 */
public class Order {
	private int id;
	private int idClient;
	
	/**
	 * Empty constructor
	 */
	public Order() {
		
	}
	
	/**
	 * Constructor with all the information
	 * @param id of the order
	 * @param idClient of the client
	 */
	public Order(int id, int idClient) {
		this.id = id;
		this.idClient = idClient;
	}
	
	/**
	 * Constructor only with the id of the client
	 * @param idClient of the client
	 */
	public Order(int idClient) {
		this.idClient = idClient;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdClient() {
		return idClient;
	}
	
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	
	/**
	 * This method returns all the information of the order as a String
	 */
	public String toString() {
		return "Order [id = " + id + ", idClient = " + idClient + "]";
	}
}
