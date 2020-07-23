package presentationlayer;

import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainView extends JFrame{
	private JFrame main = new JFrame("Main Window");
	private JButton administrator = new JButton("Administrator");
	private JButton waiter = new JButton("Waiter");
	private JButton chef = new JButton("Chef");
	
	public MainView() {
		main.setBounds(new Rectangle(730, 200));
		main.setDefaultCloseOperation(EXIT_ON_CLOSE);
		main.getContentPane().setLayout(null);
		
		administrator.setBounds(new Rectangle(150, 50));
		administrator.setLocation(50,50);
		main.add(administrator);
		
		waiter.setBounds(new Rectangle(150, 50));
		waiter.setLocation(280, 50);
		main.add(waiter);
		
		chef.setBounds(new Rectangle(150, 50));
		chef.setLocation(510, 50);
		main.add(chef);
		
		main.setLocationRelativeTo(null);
		main.setVisible(true);
	}
	
	public void administratorActionListener(final ActionListener actionListener) {
		administrator.addActionListener(actionListener);
	}
	
	public void waiterActionListener(final ActionListener actionListener) {
		waiter.addActionListener(actionListener);
	}
	
	public void chefActionListener(final ActionListener actionListener) {
		chef.addActionListener(actionListener);
	}
}
