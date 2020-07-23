package model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * This is a class that creates a list with the clients for a specific cash register
 * @author Alina Somcutean
 *
 */
public class Queue implements Runnable{
	
	private BlockingQueue<Client> clients;
	private int max = 100;
	private boolean work;
	private String leavingClient = "";
	
	/**
	 * In this constructor is initialized the list of clients and the boolean variable for thread is set initially true
	 */
	public Queue() 
	{
		this.clients = new ArrayBlockingQueue<Client>(max);
		work = true;
	}
	
	/**
	 * Method that add a client to the list
	 * @param c Client
	 */
	public void addClient(Client c)
	{
		clients.add(c);
	}
	
	/**
	 * Method that return the size of the list
	 * @return size of the list
	 */
	public int getSize()
	{
		return clients.size();
	}
	
	/**
	 * Setter for boolean variable which controls the thread
	 * @param b
	 */
	public void setWork(boolean b)
	{
		this.work = b;
	}
	
	/**
	 * Method that creates a string with ID of all the clients from the list
	 * @return queue as a string
	 */
	public String toString() {
		String queue = "";
		for(Client c : clients) {
			queue = queue + " " + c.getID();
		}
		return queue; 
	}
	
	/**
	 * Getter for leaving client
	 * @return Client as a string
	 */
	public String getLeavingClient()
	{
		return leavingClient;
	}
	
	/**
	 * Setter for string leavingClient. Here, the string is set to ""
	 */
	public void setLeavingClient()
	{
		leavingClient = "";
	}

	/**
	 * Here is created a thread which sleeps if list of clients is empty. Otherwise, the serving time of the first client, which reach the cash register is decremented with 1. When the serving time reaches value 0, the client is removed from the list and the string leavingClient is modified with a specific message
	 */
	public void run() {
		while(work) {
			try {
				if(clients.size() == 0) {
					Thread.sleep(1000);
				}
				else {
					Client c = clients.element();
					int aux = c.getServingTime();
					for(int i = 0; i < aux; i++)
					{
						c.setServingTime(c.getServingTime() - 1);
						Thread.sleep(1000);
					}
					clients.remove();
					leavingClient = leavingClient + "Client " + c.getID() + " left\n";
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
