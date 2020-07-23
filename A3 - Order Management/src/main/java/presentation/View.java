package presentation;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Class that creates the graphical user interface of the main window.
 * @author Alina Somcutean
 *
 */
public class View extends JFrame{
	private JFrame main = new JFrame("Main Window");
	private JButton product = new JButton("Product");
	private JButton client = new JButton("Client");
	private JButton order = new JButton("New Order");
	
	/**
	 * Method which sets all the buttons
	 */
	public View() {
		main.setBounds(new Rectangle(500, 200));
		main.setDefaultCloseOperation(EXIT_ON_CLOSE);
		main.getContentPane().setLayout(null);
		
		product.setBounds(new Rectangle(100, 50));
		product.setLocation(40, 40);
		main.add(product);
		
		client.setBounds(new Rectangle(100, 50));
		client.setLocation(190, 40);
		main.add(client);
		
		order.setBounds(new Rectangle(100, 50));
		order.setLocation(340, 40);
		main.add(order);
		
		main.setLocationRelativeTo(null);
		main.setVisible(true);
	}
	
	/**
	 * Header of a method for the product button
	 * @param actionListener
	 */
	public void productActionListener(final ActionListener actionListener) {
		product.addActionListener(actionListener);
	}
	
	/**
	 * Header of a method for the client button
	 * @param actionListener
	 */
	public void clientActionListener(final ActionListener actionListener) {
		client.addActionListener(actionListener);
	}
	
	/**
	 * Header of a method for the new order button
	 * @param actionListener
	 */
	public void orderActionListener(final ActionListener actionListener) {
		order.addActionListener(actionListener);
	}
}
