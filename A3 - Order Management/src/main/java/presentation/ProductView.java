package presentation;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Product;

/**
 * Class that creates the graphical user interface specific for the product table.
 * @author Alina Somcutean
 *
 */
public class ProductView extends JFrame{
	public JTable table = new JTable();
	private GenerateTable<Product> generate = new GenerateTable<Product>();
	private Product product = new Product();
	
	private JTextField insertId = new JTextField();
	private JTextField insertName = new JTextField();
	private JTextField insertPrice = new JTextField();
	private JTextField insertStock = new JTextField();
	
	private JButton insertButton = new JButton("Insert");
	private JButton updateButton = new JButton("Update");
	private JButton findAllButton = new JButton("Find All");
	private JButton findByIdButton = new JButton("Find By Id");
	private JButton deleteButton = new JButton("Delete");
	
	/**
	 * Constructor in which are set all the labels, text fields, buttons and table.
	 */
	public ProductView() {
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setBounds(new Rectangle(1000, 600));
		this.setTitle("Product");
		table = generate.createTable(product);
		getContentPane().add(table);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0, 0, 600, 600);
		getContentPane().add(scroll);
		
		JLabel id = new JLabel("Id: ");
		id.setBounds(650, 50, 70, 30);
		id.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(id);
		
		insertId.setBounds(730, 57, 200, 20);
		getContentPane().add(insertId);
		
		JLabel name = new JLabel("Name: ");
		name.setBounds(650, 90, 70, 30);
		name.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(name);
		
		insertName.setBounds(730, 97, 200, 20);
		getContentPane().add(insertName);
		
		JLabel price = new JLabel("Price: ");
		price.setBounds(650, 130, 70, 30);
		price.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(price);
		
		insertPrice.setBounds(730, 137, 200, 20);
		getContentPane().add(insertPrice);
		
		JLabel stock = new JLabel("Stock: ");
		stock.setBounds(650, 170, 70, 30);
		stock.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(stock);
		
		insertStock.setBounds(730, 177, 200, 20);
		getContentPane().add(insertStock);
		
		insertButton.setBounds(670, 240, 100, 40);
		getContentPane().add(insertButton);
		
		updateButton.setBounds(850, 240, 100, 40);
		getContentPane().add(updateButton);
		
		findAllButton.setBounds(670, 330, 100, 40);
		getContentPane().add(findAllButton);
		
		findByIdButton.setBounds(850, 330, 100, 40);
		getContentPane().add(findByIdButton);
		
		deleteButton.setBounds(750, 420, 100, 40);
		getContentPane().add(deleteButton);
	}
	
	public int getInsertedId() {
		return Integer.parseInt(insertId.getText());
	}
	
	public String getInsertedName() {
		return insertName.getText();
	}
	
	public int getInsertedPrice() {
		return Integer.parseInt(insertPrice.getText());
	}
	
	public int getInsertedStock() {
		return Integer.parseInt(insertStock.getText());
	}
	
	/**
	 * Header of a method for the insert button
	 * @param actionListener
	 */
	public void insertActionListener(final ActionListener actionListener) {
		insertButton.addActionListener(actionListener);
	}
	
	/**
	 * Header of a method for the update button
	 * @param actionListener
	 */
	public void updateActionListener(final ActionListener actionListener) {
		updateButton.addActionListener(actionListener);
	}
	
	/**
	 * Header of a method for the find all button
	 * @param actionListener
	 */
	public void findAllActionListener(final ActionListener actionListener) {
		findAllButton.addActionListener(actionListener);
	}
	
	/**
	 * Header of a method for the find by id button
	 * @param actionListener
	 */
	public void findByIdActionListener(final ActionListener actionListener) {
		findByIdButton.addActionListener(actionListener);
	}
	
	/**
	 * Header of a method for the delete button
	 * @param actionListener
	 */
	public void deleteActionListener(final ActionListener actionListener) {
		deleteButton.addActionListener(actionListener);
	}
}
