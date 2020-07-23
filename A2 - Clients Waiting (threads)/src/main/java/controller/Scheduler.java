package controller;
import java.util.ArrayList;

import model.Client;
import model.Queue;

/**
 * This class arranges the clients at the cash registers, putting a new client at a cash register that has fewer clients
 * @author Alina Somcutean
 *
 */
public class Scheduler{

	private ArrayList<Queue> queue;
	private int nrOfQueues;
	private int maxNrClientsPerQueue;
	
	/**
	 * This constructor creates a new list of cash register and for each cash register creates a new list with the clients of that cash register and creates a thread with that object
	 * @param nrOfQueues Total number of the cash registers/queues
	 * @param maxNrClientsPerQueue Total number of the clients
	 */
	public Scheduler(int nrOfQueues, int maxNrClientsPerQueue)
	{
		this.maxNrClientsPerQueue = maxNrClientsPerQueue;
		this.nrOfQueues = nrOfQueues;
		queue = new ArrayList();
		
		for(int i = 0; i < nrOfQueues; i++)
		{
			Queue q = new Queue();
			queue.add(q);
			Thread t = new Thread(q);
			t.start();
		}
	}
	
	/**
	 * This method add a new client at the cash register with fewer clients
	 * @param c client
	 * @return A string which says which client was added to a cash register list
	 */
	public String addClient(Client c)
	{
		String str = "";
		int min = maxNrClientsPerQueue;
		int nrQueue = 0;
		for(int i = 0; i < nrOfQueues; i++)
		{
			if(queue.get(i).getSize() < min)
			{
				min = queue.get(i).getSize();
				nrQueue = i;
			}
		}
		
		queue.get(nrQueue).addClient(c);
		str = "Client " + c.getID() + " arrived at the cash register\n";
		return str;
	}
	
	/**
	 * Method which transform the list of the clients of a specific cash register to a string
	 * @param nrQueue a specific cash register
	 * @return A string with the clients from a cash register
	 */
	public String queueToString(int nrQueue)
	{
		String str = "";
		str = queue.get(nrQueue).toString();
		return str;
	}
	
	/**
	 * Getter for leaving client from the first cash register
	 * @return Client as a string
	 */
	public String getLeavingClientQ1()
	{
		return queue.get(0).getLeavingClient();
	}

	/**
	 * Getter for leaving client from the second cash register
	 * @return Client as a string
	 */
	public String getLeavingClientQ2()
	{
		return queue.get(1).getLeavingClient();
	}

	/**
	 * Getter for leaving client from the third cash register
	 * @return Client as a string
	 */
	public String getLeavingClientQ3()
	{
		return queue.get(2).getLeavingClient();
	}
	
	/**
	 * Getter for list of cash registers
	 * @return
	 */
	public synchronized ArrayList<Queue> getQueues()
	{
		return queue;
	}
	
	/**
	 * Setter for leaving client from first cash register
	 */
	public void setLeavingClientQ1()
	{
		queue.get(0).setLeavingClient();
	}

	/**
	 * Setter for leaving client from second cash register
	 */
	public void setLeavingClientQ2()
	{
		queue.get(1).setLeavingClient();
	}

	/**
	 * Setter for leaving client from third cash register
	 */
	public void setLeavingClientQ3()
	{
		queue.get(2).setLeavingClient();
	}
}
