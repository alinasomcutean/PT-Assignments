package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import controller.SimulationManager;

/**
 * Class that creates the graphical interface with the user
 * @author Alina Somcutean
 *
 */
public class GUI extends JFrame{
		
	private JFrame frame;
	
	private JTextField nrOfQueues;
	private JTextField nrOfClients;
	private JTextField minArrive;
	private JTextField maxArrive;
	private JTextField minServing;
	private JTextField maxServing;
	private JTextField simulationTime;
	
	private JTextPane queues;
	private JTextPane clients;
	private JTextPane arriveTime;
	private JTextPane servingTime;
	private JTextPane simulation;
	
	private JLabel seconds;
	private JLabel seconds_1;
	
	private JButton start;
	private SimulationManager s;
	
	/**
	 * Constructor that creates the entire graphical interface
	 */
	public GUI()
	{
		prepareGUI();
		startActionListener(null);
	}
	
	/**
	 * Method which sets all the labels and text fields
	 */
	private void prepareGUI()
	{
		frame = new JFrame("Simulation Manager");
		frame.setBounds(600, 200, 600, 450);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		queues = new JTextPane();
		queues.setText("Insert the number of queues: ");
		queues.setBounds(40, 20, 300, 50);
		queues.setFont(new Font("TimesRoman", Font.BOLD, 17));
		queues.setOpaque(false);
		queues.setEditable(false);
		
		nrOfQueues = new JTextField();
		nrOfQueues.setBounds(350, 25, 50, 25);
		
		clients = new JTextPane();
		clients.setText("Insert the number of clients: ");
		clients.setBounds(40, 80, 300, 50);
		clients.setFont(new Font("TimesRoman", Font.BOLD, 17));
		clients.setOpaque(false);
		clients.setEditable(false);
		
		nrOfClients = new JTextField();
		nrOfClients.setBounds(350, 85, 50, 25);
		
		arriveTime = new JTextPane();
		arriveTime.setText("Insert the interval of arriving time: ");
		arriveTime.setBounds(40, 140, 300, 50);
		arriveTime.setFont(new Font("TimesRoman", Font.BOLD, 17));
		arriveTime.setEditable(false);
		arriveTime.setOpaque(false);
		
		minArrive = new JTextField();
		minArrive.setBounds(350, 145, 50, 25);
		
		maxArrive = new JTextField();
		maxArrive.setBounds(430, 145, 50, 25);
		
		servingTime = new JTextPane();
		servingTime.setText("Insert the interval of service time: ");
		servingTime.setBounds(40, 200, 300, 50);
		servingTime.setFont(new Font("TimesRoman", Font.BOLD, 17));
		servingTime.setEditable(false);
		servingTime.setOpaque(false);
		
		minServing = new JTextField();
		minServing.setBounds(350, 205, 50, 25);
		
		maxServing = new JTextField();
		maxServing.setBounds(430, 205, 50, 25);
		
		seconds = new JLabel();
		seconds.setText("seconds");
		seconds.setBounds(500, 133, 100, 50);
		seconds.setFont(new Font("TimesRoman", Font.BOLD, 17));
			
		seconds_1 = new JLabel();
		seconds_1.setText("seconds");
		seconds_1.setBounds(500, 193, 100, 50);
		seconds_1.setFont(new Font("TimesRoman", Font.BOLD, 17));
		
		simulation = new JTextPane();
		simulation.setText("Simulation time: ");
		simulation.setBounds(40, 260, 300, 50);
		simulation.setFont(new Font("TimesRoman", Font.BOLD, 17));
		simulation.setEditable(false);
		simulation.setOpaque(false);
		
		simulationTime = new JTextField();
		simulationTime.setBounds(350, 265, 50, 25);
		
		start = new JButton();
		start.setBounds(200, 320, 140, 40);
		start.setText("Start simulation");
		
		frame.add(start);
		
		frame.add(seconds_1);
		frame.add(seconds);
		frame.add(queues);
		frame.add(clients);
		frame.add(arriveTime);
		frame.add(servingTime);
		frame.add(simulation);
		
		frame.add(nrOfQueues);
		frame.add(nrOfClients);
		frame.add(minArrive);
		frame.add(maxArrive);
		frame.add(minServing);
		frame.add(maxServing);
		frame.add(simulationTime);
		
		frame.setVisible(true);
	}
	
	/**
	 * Method that implements an action listener for the button start of the simulation. This method takes the inputs from the graphical interface and creates a new object of type SimulationManager and start the entire simulation
	 * @param actionListener
	 */
	public void startActionListener(final ActionListener actionListener)
	{
		start.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						int nrQueues, nrClients, minArrivingTime, maxArrivingTime, minServingTime, maxServingTime, simulation;
						nrQueues = Integer.parseInt(nrOfQueues.getText());
						nrClients = Integer.parseInt(nrOfClients.getText());
						minArrivingTime = Integer.parseInt(minArrive.getText());
						maxArrivingTime = Integer.parseInt(maxArrive.getText());
						minServingTime = Integer.parseInt(minServing.getText());
						maxServingTime = Integer.parseInt(maxServing.getText());
						simulation = Integer.parseInt(simulationTime.getText());
						s = new SimulationManager(nrClients, nrQueues, minArrivingTime, maxArrivingTime, minServingTime, maxServingTime, simulation);
						Thread t = new Thread(s);
						t.start();
					}
				});
	}
	
}
