package model;

/**
 * This is a class that contains all the information about a product that is in the database also
 * @author Alina
 *
 */
public class Product {
	private int id;
	private String name;
	private int price;
	private int stock;
	
	/**
	 * Empty constructor
	 */
	public Product(){	
		
	}
	
	/**
	 * Constructor with all the information
	 * @param id of the product
	 * @param name of the product
	 * @param price of the product
	 * @param stock of the product
	 */
	public Product(int id, String name, int price, int stock){
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}
	
	/**
	 * Constructor with all the information without the id
	 * @param name of the product
	 * @param price of the product
	 * @param stock of the product
	 */
	public Product(String name, int price, int stock){
		this.name = name;
		this.price = price;
		this.stock = stock;
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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	/**
	 * This method returns all the information of the product as a String
	 */
	public String toString() {
		return "Product [id = " + id + ", name = " + name + ", price = " + price + ", stock = " + stock + "]";
	}
}
