package model;

/**
 * This class is a class that contains all the information about a client that is in the database also
 * @author Alina Somcutean
 *
 */
public class Client {
	private int id;
	private String name;
	private int age;
	private String email;
	private String address;
	
	/**
	 * Empty constructor
	 */
	public Client() {
		
	}
	
	/**
	 * Constructor with all the information without the id
	 * @param name of the client
	 * @param age of the client
	 * @param email of the client
	 * @param address of the client
	 */
	public Client(String name, int age, String email, String address) {
		this.name = name;
		this.age = age;
		this.email = email;
		this.address = address;
	}
	
	/**
	 * Constructor with all the information
	 * @param id of the client
	 * @param name of the client
	 * @param age of the client
	 * @param email of the client
	 * @param address of the client
	 */
	public Client(int id, String name, int age, String email, String address) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.address = address;
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
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * This method returns all the information of the client as a String
	 */
	public String toString() {
		return "Client [id = " + id + ", name = " + name + ", email = " + email + ", address = " + address  + "]";
	}
}
