package view;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Class that creates a new frame where the information from the simulation are displayed
 * @author Alina Somcutean
 *
 */
public class Log extends JFrame{

	private JFrame log;
	
	private JLabel q1 = new JLabel();
	private JLabel q2 = new JLabel();
	private JLabel q3 = new JLabel();
	
	private JLabel q1Text = new JLabel(); 
	private JLabel q2Text = new JLabel();
	private JLabel q3Text = new JLabel(); 
	
	private JLabel averageServingTime = new JLabel();
	private JLabel averageServingTimeText = new JLabel();
	private JLabel averageWaitingTime = new JLabel();
	private JLabel averageWaitingTimeText = new JLabel();
	
	private JTextArea logEvents = new JTextArea();
	private JScrollPane scroll = new JScrollPane(logEvents);
	
	/**
	 * Constructor which sets the labels and text area and add all the information from the simulation
	 */
	public Log()
	{
		log = new JFrame("Log Events");
		log.setBounds(600, 200, 700, 700);
		log.setDefaultCloseOperation(EXIT_ON_CLOSE);
		log.getContentPane().setLayout(null);
		
		q1.setText("Queue 1 : ");
		q1.setBounds(30, 20, 100, 30);
		q1.setFont(new Font("TimesRoman", Font.BOLD, 17));
		
		q2.setText("Queue 2 : ");
		q2.setBounds(30, 60, 100, 30);
		q2.setFont(new Font("TimesRoman", Font.BOLD, 17));
		
		q3.setText("Queue 3 : ");
		q3.setBounds(30, 100, 100, 30);
		q3.setFont(new Font("TimesRoman", Font.BOLD, 17));

		q1Text.setBounds(130, 20, 200, 30);
		q2Text.setBounds(130, 60, 200, 30);
		q3Text.setBounds(130, 100, 200, 30);
		
		averageServingTime.setText("Average serving time is ");
		averageServingTime.setBounds(30, 450, 300, 30);
		averageServingTime.setFont(new Font("TimesRoman", Font.BOLD, 17));
		
		averageServingTimeText.setBounds(340, 450, 100, 30);
		
		averageWaitingTime.setText("Average waiting time is ");
		averageWaitingTime.setBounds(30, 500, 300, 30);
		averageWaitingTime.setFont(new Font("TimesRoman", Font.BOLD, 17));
		
		averageWaitingTimeText.setBounds(340, 500, 100, 30);
		
		scroll.setBounds(150, 150, 400, 250);
		scroll.setVisible(true);
		logEvents.setEditable(false);
		
		log.add(q1);
		log.add(q2);
		log.add(q3);
		
		log.add(q1Text);
		log.add(q2Text);
		log.add(q3Text);
		
		log.add(averageServingTime);
		log.add(averageServingTimeText);
		
		log.add(averageWaitingTime);
		log.add(averageWaitingTimeText);
		
		log.add(scroll);
		log.setVisible(true);
	}
	
	/**
	 * Method which set the text for the first cash register
	 * @param text A string that has to be displayed
	 */
	public void setQ1Text(String text)
	{
		q1Text.setText(text);
		q1Text.setFont(new Font("TimesRoman", Font.BOLD, 17));
	}

	/**
	 * Method which set the text for the second cash register
	 * @param text A string that has to be displayed
	 */
	public void setQ2Text(String text)
	{
		q2Text.setText(text);
		q2Text.setFont(new Font("TimesRoman", Font.BOLD, 17));
	}

	/**
	 * Method which set the text for the third cash register
	 * @param text A string that has to be displayed
	 */
	public void setQ3Text(String text)
	{
		q3Text.setText(text);
		q3Text.setFont(new Font("TimesRoman", Font.BOLD, 17));
	}
	
	/**
	 * Method which update the text in the text area during the simulation
	 * @param text A string that has to be displayed
	 */
	public void setLogEvents(String text)
	{
		logEvents.setText(logEvents.getText() + text);
		logEvents.setFont(new Font("TimesRoman", Font.BOLD, 17));
	}
	
	/**
	 * Method which sets the text for the average serving time
	 * @param text Average of the serving time as a string
	 */
	public void setAverageServingTime(String text)
	{
		averageServingTimeText.setText(text);
		averageServingTimeText.setFont(new Font("TimesRoman", Font.BOLD, 17));
	}
	
	/**
	 * Method which sets the text for the average waiting time
	 * @param text average of the waiting time as a string
	 */
	public void setAverageWaitingTime(String text)
	{
		averageWaitingTimeText.setText(text);
		averageWaitingTimeText.setFont(new Font("TimesRoman", Font.BOLD, 17));
	}
}
