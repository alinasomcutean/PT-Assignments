package presentation;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bll.ClientBLL;
import bll.ProductBLL;
import model.Client;
import model.Product;

/**
 * Class that creates the graphical user interface for placing a new order.
 * @author Alina Somcutean
 *
 */
public class OrderView extends JFrame{
	private ProductBLL productBLL = new ProductBLL();
	private ClientBLL clientBLL = new ClientBLL();

	private JComboBox listOfProducts = new JComboBox();
	private JComboBox listOfClients = new JComboBox();
	
	private JTextField insertedQuantity = new JTextField();
	
	private JButton addButton = new JButton("Add product");
	private JButton orderButton = new JButton("Order");

	/**
	 * Constructor in which are set all the labels, text fields, buttons and combo boxes.
	 */
	public OrderView() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setBounds(new Rectangle(700, 400));
		this.setTitle("Order");
		
		JLabel product = new JLabel("Please select a product: ");
		product.setBounds(30, 10, 200, 30);
		product.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(product);
		
		List<Product> products = productBLL.findAllProducts();
		for(int i = 0; i < products.size(); i++) {
			listOfProducts.addItem(products.get(i));
		}
		listOfProducts.setBounds(70, 50, 600, 30);
		getContentPane().add(listOfProducts);
		
		JLabel client = new JLabel("Plese select a client: ");
		client.setBounds(30, 120, 200, 30);
		client.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(client);
		
		List<Client> clients = clientBLL.findAllClients();
		for(int i = 0; i < clients.size(); i++) {
			listOfClients.addItem(clients.get(i));
		}
		listOfClients.setBounds(70, 150, 600, 30);
		getContentPane().add(listOfClients);
		
		JLabel quantity = new JLabel("Plese insert the quantity: ");
		quantity.setBounds(30, 230, 200, 30);
		quantity.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(quantity);
		
		insertedQuantity.setBounds(240, 237, 100, 20);
		getContentPane().add(insertedQuantity);
		
		addButton.setBounds(80, 300, 120, 30);
		getContentPane().add(addButton);
		
		orderButton.setBounds(300, 300, 120, 30);
		getContentPane().add(orderButton);
	}

	public int getInsertedQuantity() {
		return Integer.parseInt(insertedQuantity.getText());
	}
	
	/**
	 * Header of a method for the add button
	 * @param actionListener
	 */
	public void addActionListener(final ActionListener actionListener) {
		addButton.addActionListener(actionListener);
	}
	
	/**
	 * Header of a method for the order button
	 * @param actionListener
	 */
	public void orderActionListener(final ActionListener actionListener) {
		orderButton.addActionListener(actionListener);
	}
	
	public Product getSelectedProduct() {
		return (Product) listOfProducts.getSelectedItem();
	}
	
	public Client getSelectedClient() {
		return (Client) listOfClients.getSelectedItem();
	}
}
