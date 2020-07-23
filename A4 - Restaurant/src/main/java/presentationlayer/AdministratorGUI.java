package presentationlayer;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AdministratorGUI extends JFrame{
	public JTable table = new JTable();
	private GenerateTable generate = new GenerateTable();
	
	private JTextField insertNameBaseProduct = new JTextField();
	private JTextField insertPriceBaseProduct = new JTextField();
	private JButton addBaseProduct = new JButton("Add base product");
	
	private JTextField insertNameCompositeProduct = new JTextField();
	private JButton addToCompositeProduct = new JButton("Add to composite product");
	private JButton addCompositeProduct = new JButton("Add product to menu");
	
	private JButton viewMenu = new JButton("View Menu");
	private JButton deleteProduct = new JButton("Delete product");
	
	private JTextField insertEditBaseProductName = new JTextField();
	private JTextField insertEditBaseProductPrice = new JTextField();
	private JButton editBaseProduct = new JButton("Edit base product");
	
	private JTextField insertEditCompositeProductName = new JTextField();
	private JButton editCompositeProduct = new JButton("Edit composite product");
	
	public AdministratorGUI() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(new Rectangle(1000, 500));
		this.setTitle("Administrator Window");
		
		table = generate.createTable();
		getContentPane().add(table);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(new Rectangle(400, 500));
		getContentPane().add(scroll);
		
		JLabel nameBaseProduct = new JLabel("Name: ");
		nameBaseProduct.setBounds(420, 20, 80, 30);
		nameBaseProduct.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(nameBaseProduct);
		
		insertNameBaseProduct.setBounds(500, 27, 100, 20);
		getContentPane().add(insertNameBaseProduct);
		
		JLabel priceBaseProduct = new JLabel("Price: ");
		priceBaseProduct.setBounds(420, 60, 80, 30);
		priceBaseProduct.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(priceBaseProduct);
		
		insertPriceBaseProduct.setBounds(500, 67, 100, 20);
		getContentPane().add(insertPriceBaseProduct);
		
		addBaseProduct.setBounds(440, 100, 150, 40);
		getContentPane().add(addBaseProduct);	
		
		JLabel nameCompositeProduct = new JLabel("Name: ");
		nameCompositeProduct.setBounds(700, 20, 80, 30);
		nameCompositeProduct.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(nameCompositeProduct);
		
		insertNameCompositeProduct.setBounds(780, 27, 100, 20);
		getContentPane().add(insertNameCompositeProduct);
		
		addToCompositeProduct.setBounds(730, 70, 200, 40);
		getContentPane().add(addToCompositeProduct);
		
		addCompositeProduct.setBounds(730, 130, 200, 40);
		getContentPane().add(addCompositeProduct);
		
		viewMenu.setBounds(500, 200, 150, 40);
		getContentPane().add(viewMenu);
		
		deleteProduct.setBounds(700, 200, 150, 40);
		getContentPane().add(deleteProduct);
		
		JLabel baseProductName = new JLabel("Name: ");
		baseProductName.setBounds(420, 280, 80, 30);
		baseProductName.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(baseProductName);
		
		insertEditBaseProductName.setBounds(500, 287, 100, 20);
		getContentPane().add(insertEditBaseProductName);
		
		JLabel baseProductPrice = new JLabel("Price: ");
		baseProductPrice.setBounds(420, 310, 80, 30);
		baseProductPrice.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(baseProductPrice);
		
		insertEditBaseProductPrice.setBounds(500, 317, 100, 20);
		getContentPane().add(insertEditBaseProductPrice);
		
		editBaseProduct.setBounds(440, 350, 150, 40);
		getContentPane().add(editBaseProduct);
		
		JLabel compositeProductName = new JLabel("Name: ");
		compositeProductName.setBounds(700, 280, 80, 30);
		compositeProductName.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(compositeProductName);
		
		insertEditCompositeProductName.setBounds(780, 287, 100, 20);
		getContentPane().add(insertEditCompositeProductName);
		
		editCompositeProduct.setBounds(730, 340, 200, 40);
		getContentPane().add(editCompositeProduct);
	}
	
	public void viewMenuActionListener(final ActionListener actionListener) {
		viewMenu.addActionListener(actionListener);
	}
	
	public void deleteProductActionListener(final ActionListener actionListener) {
		deleteProduct.addActionListener(actionListener);
	}
	
	public void addBaseProductActionListener(final ActionListener actionListener) {
		addBaseProduct.addActionListener(actionListener);
	}
	
	public void addToCompositeProductActionListener(final ActionListener actionListener) {
		addToCompositeProduct.addActionListener(actionListener);
	}
	
	public void addCompositeProductActionListener(final ActionListener actionListener) {
		addCompositeProduct.addActionListener(actionListener);
	}
	
	public void editBaseProductActionListener(final ActionListener actionListener) {
		editBaseProduct.addActionListener(actionListener);
	}
	
	public void editCompositeProductActionListener(final ActionListener actionListener) {
		editCompositeProduct.addActionListener(actionListener);
	}
	
	public String getBaseProductName() {
		return insertNameBaseProduct.getText();
	}
	
	public int getBaseProductPrice() {
		return Integer.parseInt(insertPriceBaseProduct.getText());
	}
	
	public String getCompositeProductName() {
		return insertNameCompositeProduct.getText();
	}
	
	public String getEditBaseProductName() {
		return insertEditBaseProductName.getText();
	}
	
	public int getEditBaseProductPrice() {
		return Integer.parseInt(insertEditBaseProductPrice.getText());
	}
	
	public String getEditCompositeProductName() {
		return insertEditCompositeProductName.getText();
	}
}
