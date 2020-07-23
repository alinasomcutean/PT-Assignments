package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import model.Polinom;

/**
 * This class creates the graphical interface
 * @author Alina Somcutean
 *
 */
public class GUI  extends JFrame{
	
	public JFrame frame = new JFrame("Operations on polynomials");
	private JTextField pol_1 = new JTextField();
	private JTextField pol_2 = new JTextField();
	private JButton sum = new JButton();
	private JButton diff = new JButton();
	private JButton product = new JButton();
	private JButton division = new JButton();
	private JButton derivate = new JButton();
	private JButton integrate = new JButton();
	public JLabel label = new JLabel();					
	public JLabel l = new JLabel();

	/**
	 * This methods executes all other methods from this class
	 */
	public GUI()
	{
		prepareGUI();
		sumActionListener(null);
		diffActionListener(null);
		productActionListener(null);
		divisionActionListener(null);
		derivActionListener(null);	
		integrateActionListener(null);
	}
	
	/**
	 * This methods executes all other methods from this class
	 */
	private void prepareGUI()
	{
		JLabel label_pol_1 = new JLabel();
		JLabel label_pol_2 = new JLabel();
		JLabel label_result = new JLabel();
		
		frame.setSize(500, 500);
		frame.setLayout(null);
		
		label_pol_1.setText("Enter the first polynomial:");
		label_pol_1.setBounds(0, 10, 500, 30);
		label_pol_1.setHorizontalAlignment(JLabel.CENTER);
		label_pol_1.setFont(new Font("TimesRoman", Font.BOLD, 18));
		frame.add(label_pol_1);
		
		pol_1.setBounds(50, 40, 400, 30);
		frame.add(pol_1);
		
		label_pol_2.setText("Enter the second polynomial");
		label_pol_2.setBounds(0, 80, 500, 30);
		label_pol_2.setHorizontalAlignment(JLabel.CENTER);
		label_pol_2.setFont(new Font("TimesRoman", Font.BOLD, 18));
		frame.add(label_pol_2);
		
		pol_2.setBounds(50, 120, 400, 30);
		frame.add(pol_2);
		
		sum.setBounds(40, 180, 100, 40);
		sum.setText("Sum");
		frame.add(sum);
		
		diff.setBounds(150, 180, 100, 40);
		diff.setText("Difference");
		frame.add(diff);
		
		product.setBounds(260, 180, 100, 40);
		product.setText("Product");
		frame.add(product);
		
		division.setBounds(370, 180, 100, 40);
		division.setText("Division");
		frame.add(division);
		
		derivate.setBounds(130, 240, 100, 40);
		derivate.setText("Derivate");
		frame.add(derivate);
		
		integrate.setBounds(270, 240, 100, 40);
		integrate.setText("Integration");
		frame.add(integrate);
		
		label_result.setText("The result is");
		label_result.setBounds(0, 300, 500, 30);
		label_result.setHorizontalAlignment(JLabel.CENTER);
		label_result.setFont(new Font("TimesRoman", Font.BOLD, 18));
		frame.add(label_result);
		
		frame.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method activate the button such that the addition between 2 polynomials is performed and the result is displayed
	 * @param actionListener
	 */
	public void sumActionListener(final ActionListener actionListener)
	{
		sum.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{			
						String str = pol_1.getText();
						String str_1 = pol_2.getText();
						Polinom p1 = new Polinom();
						Polinom p2 = new Polinom();
						Polinom p3 = new Polinom();
						String result;;
						p1.createMonoms(str);
						p2.createMonoms(str_1);
						p3 = p1.sum(p2);
						result = p3.polinomToString();
						label.setText(result);
						label.setBounds(50, 340, 400, 30);
						label.setHorizontalAlignment(JLabel.CENTER);
						label.setFont(new Font("TimesRoman", Font.BOLD, 18));
						frame.add(label);
					}
					
				});
	}
	
	/**
	 * This method activate the button such that the subtraction between 2 polynomials is performed and the result is displayed
	 * @param actionListener
	 */
	public void diffActionListener(final ActionListener actionListener)
	{
		diff.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{				
						String str = pol_1.getText();
						String str_1 = pol_2.getText();
						Polinom p1 = new Polinom();
						Polinom p2 = new Polinom();
						Polinom p3 = new Polinom();
						String result;;
						p1.createMonoms(str);
						p2.createMonoms(str_1);
						p3 = p1.diff(p2);
						result = p3.polinomToString();
						label.setText(result);
						label.setBounds(50, 340, 400, 30);
						label.setHorizontalAlignment(JLabel.CENTER);
						label.setFont(new Font("TimesRoman", Font.BOLD, 18));
						frame.add(label);
					}
				});
	}
	
	/**
	 * This method activate the button such that the product between 2 polynomials is performed and the result is displayed
	 * @param actionListener
	 */
	public void productActionListener(final ActionListener actionListener)
	{
		product.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{			
						String str = pol_1.getText();
						String str_1 = pol_2.getText();
						Polinom p1 = new Polinom();
						Polinom p2 = new Polinom();
						Polinom p3 = new Polinom();
						String result;;
						p1.createMonoms(str);
						p2.createMonoms(str_1);
						p3 = p1.product(p2);
						result = p3.polinomToString();
						label.setText(result);
						label.setBounds(50, 340, 400, 30);
						label.setHorizontalAlignment(JLabel.CENTER);
						label.setFont(new Font("TimesRoman", Font.BOLD, 18));
						frame.add(label);
					}
				});
	}
	
	/**
	 * This method activate the button such that the division between 2 polynomials is performed and the remainder and quotient are displayed
	 * @param actionListener
	 */
	public void divisionActionListener(final ActionListener actionListener)
	{
		division.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{	
						String str = pol_1.getText();
						String str_1 = pol_2.getText();
						Polinom p1 = new Polinom();
						Polinom p2 = new Polinom();
						Polinom[] p3 = new Polinom[2];
						String result, remainder;
						p1.createMonoms(str);
						p2.createMonoms(str_1);
						p3 = p1.divide(p2);
						result = p3[0].polinomToString();
						remainder = p3[1].polinomToString();
						label.setText(result);
						label.setBounds(50, 340, 400, 30);
						label.setHorizontalAlignment(JLabel.CENTER);
						label.setFont(new Font("TimesRoman", Font.BOLD, 18));
						frame.add(label);
						l.setText(remainder);
						l.setBounds(50, 380, 440, 30);
						l.setHorizontalAlignment(JLabel.CENTER);
						l.setFont(new Font("TimesRoman", Font.BOLD, 18));
						frame.add(l);
					}
				});
	}
	
	/**
	 * This method activate the button such that the derivation of the first polynomial is performed and the result is diplayed
	 *  @param actionListener
	 */
	public void derivActionListener(final ActionListener actionListener)
	{
		derivate.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{					
						String str = pol_1.getText();
						Polinom p1 = new Polinom();
						Polinom p3 = new Polinom();
						String result;;
						p1.createMonoms(str);
						p3 = p1.derivation();
						result = p3.polinomToString();
						label.setText(result);
						label.setBounds(50, 340, 400, 30);
						label.setHorizontalAlignment(JLabel.CENTER);
						label.setFont(new Font("TimesRoman", Font.BOLD, 18));
						frame.add(label);
					}
				});
	}
	 
	 /**
		 * This method activate the button such that the integration of the first polynomial is performed and the result is diplayed
		 *  @param actionListener
		 */
	public void integrateActionListener(final ActionListener actionListener)
	{
		integrate.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String str = pol_1.getText();
						Polinom p1 = new Polinom();
						Polinom p3 = new Polinom();
						String result;;
						p1.createMonoms(str);
						p3 = p1.integration();
						result = p3.polinomToString();
						label.setText(result);
						label.setBounds(50, 340, 400, 30);
						label.setHorizontalAlignment(JLabel.CENTER);
						label.setFont(new Font("TimesRoman", Font.BOLD, 18));
						frame.add(label);
					}
				});
	}
	
}
