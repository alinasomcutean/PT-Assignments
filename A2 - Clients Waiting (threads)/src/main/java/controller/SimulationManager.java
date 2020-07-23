package controller;

import java.util.ArrayList;
import java.util.Random;

import model.*;
import view.Log;

/**
 * This class creates the list of clients and sends message to the graphical interface
 * @author Alina
 *
 */
public class SimulationManager implements Runnable{

	private int nrClients;
	private int nrQueues;
	private int minArrivingTime;
	private int maxArrivingTime;
	private int minServingTime;
	private int maxServingTime;
	private int simulationTime;
	private ArrayList<Client> clients;
	private ArrayList<Client> aux;
	private Scheduler scheduler;
	private Log log;
	
	/**
	 * This constructor initialize the list of the clients and add new clients in the list with the waiting and serving time generated random
	 * @param nrClients Total number of the clients
	 * @param nrQueues Total number of the cash registers
	 * @param minArrivingTime The minimal arriving time
	 * @param maxArrivingTime The maximum arriving time
	 * @param minServingTime The minimal serving time
	 * @param maxServingTime The maximum serving time
	 * @param simulationTime The total time of the simulation
	 */
	public SimulationManager(int nrClients, int nrQueues, int minArrivingTime, int maxArrivingTime, int minServingTime, int maxServingTime, int simulationTime)
	{
		this.nrClients = nrClients;
		this.nrQueues = nrQueues;
		this.minArrivingTime = minArrivingTime;
		this.maxArrivingTime = maxArrivingTime;
		this.minServingTime = minServingTime;
		this.maxServingTime = maxServingTime;
		this.simulationTime = simulationTime;
		clients = new ArrayList<Client>();
		aux = new ArrayList<Client>();
		scheduler = new Scheduler(nrQueues, nrClients);
		
		clients.add(new Client(0, randInt(minArrivingTime, maxArrivingTime), randInt(minServingTime, maxServingTime)));
		for(int i = 1; i < nrClients; i++)
		{
			clients.add(new Client(i, clients.get(i-1).getArrivingTime() + randInt(minArrivingTime, maxArrivingTime), randInt(minServingTime, maxServingTime)));
		}
		
		for(int i = 0; i < clients.size(); i++)
		{
			aux.add(clients.get(i));
		}
	}
	
	/**
	 * Method which generates a random number in an interval
	 * @param min Minimum number
	 * @param max Maximum number
	 * @return A random number
	 */
	public int randInt(int min, int max)
	{
		Random rand = new Random();
		int randomNr = rand.nextInt((max - min) + 1) + min;
		return randomNr;
	}
	
	/**
	 * Method which process a string such that takes from a string only the id of the client
	 * @param text A string which contains the ID of a client
	 * @return The id of the client
	 */
	public int clientID(String text)
	{
		String[] str = text.split(" ");
		int id = Integer.parseInt(str[1]);
		return id;
	}

	/**
	 * A thread for the simulation. Here are control all other threads.
	 */
	public void run() {
			int currentTime = 1;
			log = new Log();
			int clientsServed = 0;
			int clientsLeft = 0;
			float clientsServingTime = 0;
			float averageWaitingTime = 0;
			while(currentTime <= simulationTime)
			{
				try 
				{
					String time = String.valueOf(currentTime);
					time = time + "\n";
					log.setLogEvents(time);
					for(int i=0; i<clients.size(); i++)
					{
						String clientArrived = "";
						if(clients.get(i).getArrivingTime() == currentTime)
						{
							clientArrived = scheduler.addClient(clients.get(i));
							clients.remove(i);
							log.setLogEvents(clientArrived);
							clientsServed++;
							clientsServingTime += clients.get(i).getServingTime();
							
							
						}
					}
					log.setQ1Text(scheduler.queueToString(0));
					if(nrQueues > 1)
					{
						log.setQ2Text(scheduler.queueToString(1));
					}
					if(nrQueues > 2)
					{
						log.setQ3Text(scheduler.queueToString(2));
					}
					if(scheduler.getLeavingClientQ1() != "")
					{
						int neededClientID = clientID(scheduler.getLeavingClientQ1());
						clientsLeft++;
						averageWaitingTime = averageWaitingTime + (currentTime - aux.get(neededClientID).getArrivingTime());
						log.setLogEvents(scheduler.getLeavingClientQ1());
						scheduler.setLeavingClientQ1();
					}
					if(nrQueues > 1)
					{	
						if(scheduler.getLeavingClientQ2() != "")
						{
							int neededClientID = clientID(scheduler.getLeavingClientQ2());
							clientsLeft++;
							averageWaitingTime = averageWaitingTime + (currentTime - aux.get(neededClientID).getArrivingTime());
							log.setLogEvents(scheduler.getLeavingClientQ2());
							scheduler.setLeavingClientQ2();
						}
					}
					if(nrQueues > 2)
					{
						if(scheduler.getLeavingClientQ3() != "")
						{
							int neededClientID = clientID(scheduler.getLeavingClientQ3());
							clientsLeft++;
							averageWaitingTime = averageWaitingTime + (currentTime - aux.get(neededClientID).getArrivingTime());
							log.setLogEvents(scheduler.getLeavingClientQ3());
							scheduler.setLeavingClientQ3();
						}
					}
					Thread.sleep(1000);
					currentTime++;
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			log.setAverageServingTime(String.valueOf(clientsServingTime/clientsServed));
			log.setAverageWaitingTime(String.valueOf(averageWaitingTime/clientsLeft));
			for(int i = 0; i < nrQueues; i++)
			{
				scheduler.getQueues().get(i).setWork(false);
			}
		} 	
}
