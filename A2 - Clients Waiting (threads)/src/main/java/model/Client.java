package model;
/**
 * This is a class that keep the ID, arriving time and serving time of a client
 * @author Alina Somcutean
 *
 */
public class Client {

	private int arrivingTime;
	private int servingTime;
	private int ID;
	
	public Client(int ID, int arrivingTime, int servingTime) 
	{
		this.arrivingTime = arrivingTime;
		this.servingTime = servingTime;
		this.ID = ID;
	}
	
	/**
	 * Get the arriving time of the client
	 * @return the arriving time of a client
	 */
	public int getArrivingTime() 
	{
		return this.arrivingTime;
	}
	
	/**
	 * Get the serving time of a client
	 * @return the serving time of a client
	 */
	public int getServingTime() 
	{
		return this.servingTime;
	}
	
	/**
	 * Get the ID of a client
	 * @return the ID of a client
	 */
	public int getID() 
	{
		return this.ID;
	}
	
	/**
	 * This is a setter for arriving time of a client
	 * @param arriving
	 */
	public void setArrivingTime(int arriving) 
	{
		this.arrivingTime = arriving;
	}
	
	/**
	 * This is a setter for serving time of a client
	 * @param serving
	 */
	public void setServingTime(int serving) 
	{
		this.servingTime = serving;
	}
	
	/**
	 * This is a setter for ID of a client
	 * @param ID
	 */
	public void setID(int ID)
	{
		this.ID = ID;
	}
	
}
