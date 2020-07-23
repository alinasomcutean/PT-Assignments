package presentationlayer;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import businesslayer.MenuItem;

public class WaiterGUI extends JFrame{
	public JTable table = new JTable();
	private GenerateTableOrder generate = new GenerateTableOrder();
	
	private JTextField insertId = new JTextField();
	private JTextField insertTable = new JTextField();
	private JComboBox<MenuItem> menuList = new JComboBox<MenuItem>();
	private JButton addToOrder = new JButton("Add to order");
	private JButton addOrder = new JButton("Add new order");
	private JButton computeBill = new JButton("Compute bill");
	private JButton viewOrders = new JButton("View orders");
	
	public WaiterGUI(List<MenuItem> list) {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(new Rectangle(1000, 400));
		this.setTitle("Waiter Window");
		
		table = generate.createTable();
		getContentPane().add(table);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(new Rectangle(550, 400));
		getContentPane().add(scroll);
		
		JLabel id = new JLabel("Id: ");
		id.setBounds(570, 20, 70, 30);
		id.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(id);
		
		insertId.setBounds(640, 27, 100, 20);
		getContentPane().add(insertId);
		
		JLabel table = new JLabel("Table: ");
		table.setBounds(780, 20, 70, 30);
		table.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(table);
		
		insertTable.setBounds(850, 27, 100, 20);
		getContentPane().add(insertTable);
		
		for(int i = 0; i < list.size(); i++) {
			menuList.addItem(list.get(i));
		}
		menuList.setBounds(580, 60, 400, 30);
		getContentPane().add(menuList);
		
		addToOrder.setBounds(590, 120, 150, 40);
		getContentPane().add(addToOrder);
		
		addOrder.setBounds(800, 120, 150, 40);
		getContentPane().add(addOrder);
		
		computeBill.setBounds(690, 180, 150, 40);
		getContentPane().add(computeBill);
		
		viewOrders.setBounds(690, 240, 150, 40);
		getContentPane().add(viewOrders);
	}
	
	public int getOrderId() {
		return Integer.parseInt(insertId.getText());
	}	
	
	public int getOrderTable() {
		return Integer.parseInt(insertTable.getText());
	}
	
	public MenuItem getSelectedProduct() {
		return (MenuItem) menuList.getSelectedItem();
	}
	
	public void addOrderActionListener(final ActionListener actionListener) {
		addOrder.addActionListener(actionListener);
	}
	
	public void viewOrdersActionListener(final ActionListener actionListener) {
		viewOrders.addActionListener(actionListener);
	}
	
	public void addToOrderActionListener(final ActionListener actionListener) {
		addToOrder.addActionListener(actionListener);
	}
	
	public void computeBillActionListener(final ActionListener actionListener) {
		computeBill.addActionListener(actionListener);
	}
}
