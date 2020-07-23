package presentation;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.OrderHasProductBLL;
import bll.ProductBLL;
import bll.validators.AgeValidator;
import bll.validators.EmailValidator;
import bll.validators.PriceValidator;
import bll.validators.StockValidator;
import model.Client;
import model.Order;
import model.OrderHasProduct;
import model.Product;

/**
 * Class that implements all the methods for all the buttons in the application
 * @author Alina Somcutean
 *
 */
public class Controller {
	private View view = new View();
	private ProductView productView = new ProductView();
	private ProductBLL productBLL = new ProductBLL();
	private PriceValidator priceValidator = new PriceValidator();
	private StockValidator stockValidator = new StockValidator();
	
	private ClientView clientView = new ClientView();
	private ClientBLL clientBLL = new ClientBLL();
	private AgeValidator ageValidator = new AgeValidator();
	private EmailValidator emailValidator = new EmailValidator();
	
	private OrderView orderView = new OrderView();
	private OrderHasProductBLL orderHasProductBLL = new OrderHasProductBLL();
	private Client client = new Client();
	private List<Product> products = new ArrayList<Product>();
	private OrderBLL orderBLL = new OrderBLL();
	private Order newOrder = new Order();
	private List<Integer> quantityList = new ArrayList<Integer>();
	
	public Controller() {
		mainView();
	}
	
	/**
	 * Method which implements the buttons from the main window.
	 */
	private void mainView() {
		view.productActionListener(e -> {
			productView.setVisible(true);
			productViewButtons();
		});
		
		view.clientActionListener(e -> {
			clientView.setVisible(true);
			clientViewButtons();
		});
		
		view.orderActionListener(e -> {
			orderView.setVisible(true);
			orderViewButtons();
		});
	}
	
	/**
	 * Method which creates a new dialog with a warning message when something goes wrong.
	 * @param s the message that has to be displayed
	 */
	private void setWarningMessage(String s) {
		JOptionPane optionPane = new JOptionPane(s, JOptionPane.WARNING_MESSAGE);
		JDialog dialog = optionPane.createDialog("Warning!");
		dialog.setVisible(true);
	}
	
	/**
	 * Method which implements all the buttons from the product window.
	 */
	private void productViewButtons() {
		productView.findAllActionListener(e -> {
			DefaultTableModel model = (DefaultTableModel) productView.table.getModel();
			List<Product> list = productBLL.findAllProducts();
			model.setRowCount(list.size());
			for(int i = 0; i < list.size(); i++) {
				model.setValueAt(list.get(i).getId(), i, 0);
				model.setValueAt(list.get(i).getName(), i, 1);
				model.setValueAt(list.get(i).getPrice(), i, 2);
				model.setValueAt(list.get(i).getStock(), i, 3);
			}
			productView.table.setModel(model);
		});
		
		productView.findByIdActionListener(e -> {
			int id = productView.getInsertedId();
			DefaultTableModel model = (DefaultTableModel) productView.table.getModel();
			Product product = productBLL.findProductById(id);
			if(product != null) {
				model.setRowCount(1);
				model.setValueAt(product.getId(), 0, 0);
				model.setValueAt(product.getName(), 0, 1);
				model.setValueAt(product.getPrice(), 0, 2);
				model.setValueAt(product.getStock(), 0, 3);
				productView.table.setModel(model);
			}
			else {
				setWarningMessage("The product with the desire id doesn't exist!");
			}
		});
		
		productView.insertActionListener(e -> {
			String name = productView.getInsertedName();
			int price = productView.getInsertedPrice();
			int stock = productView.getInsertedStock();
			
			Product newProduct = new Product(name, price, stock);
			if(priceValidator.validate(newProduct) == 0) {
				setWarningMessage("A product cannot have the price less then 1!");
			}
			else {
				if(stockValidator.validate(newProduct) == 0) {
					setWarningMessage("The minimum stock of a product is 0!");
				}
				else {
					DefaultTableModel model = (DefaultTableModel) productView.table.getModel();
					Product product = productBLL.insertProduct(newProduct);
					List<Product> list = productBLL.findAllProducts();
					list.add(product);
					model.setRowCount(list.size()-1);
					for(int i = 0; i < list.size()-1; i++) {
						model.setValueAt(list.get(i).getId(), i, 0);
						model.setValueAt(list.get(i).getName(), i, 1);
						model.setValueAt(list.get(i).getPrice(), i, 2);
						model.setValueAt(list.get(i).getStock(), i, 3);
					}
					productView.table.setModel(model);
				}
			}
		});
		
		productView.updateActionListener(e -> {
			String name = productView.getInsertedName();
			int price = productView.getInsertedPrice();
			int stock = productView.getInsertedStock();
			
			Product newProduct = new Product(name, price, stock);
			if(priceValidator.validate(newProduct) == 0) {
				setWarningMessage("A product cannot have the price less then 1!");
			}
			else {
				if(stockValidator.validate(newProduct) == 0) {
					setWarningMessage("The minimum stock of a product is 0!");
				}
				else {
					int id = productView.getInsertedId();
					DefaultTableModel model = (DefaultTableModel) productView.table.getModel();
					Product product = productBLL.updateProduct(newProduct, id);
					List<Product> list = productBLL.findAllProducts();
					model.setRowCount(list.size());
					for(int i = 0; i < list.size(); i++) {
						model.setValueAt(list.get(i).getId(), i, 0);
						model.setValueAt(list.get(i).getName(), i, 1);
						model.setValueAt(list.get(i).getPrice(), i, 2);
						model.setValueAt(list.get(i).getStock(), i, 3);
					}
					productView.table.setModel(model);
				}
			}
		
		});
		
		productView.deleteActionListener(e -> {
			int id = productView.getInsertedId();
			productBLL.deleteProductById(id);
			DefaultTableModel model = (DefaultTableModel) productView.table.getModel();
			List<Product> list = productBLL.findAllProducts();
			model.setRowCount(list.size());
			for(int i = 0; i < list.size(); i++) {
				model.setValueAt(list.get(i).getId(), i, 0);
				model.setValueAt(list.get(i).getName(), i, 1);
				model.setValueAt(list.get(i).getPrice(), i, 2);
				model.setValueAt(list.get(i).getStock(), i, 3);
			}
			productView.table.setModel(model);
		});
	}
	
	/**
	 * Method which implements all the buttons from the client window.
	 */
	private void clientViewButtons() {
		clientView.findAllActionListener(e -> {
			DefaultTableModel model = (DefaultTableModel) clientView.table.getModel();
			List<Client> list = clientBLL.findAllClients();
			model.setRowCount(list.size());
			for(int i = 0; i < list.size(); i++) {
				model.setValueAt(list.get(i).getId(), i, 0);
				model.setValueAt(list.get(i).getName(), i, 1);
				model.setValueAt(list.get(i).getAge(), i, 2);
				model.setValueAt(list.get(i).getEmail(), i, 3);
				model.setValueAt(list.get(i).getAddress(), i, 4);
			}
			clientView.table.setModel(model);
		});
		
		clientView.findByIdActionListener(e -> {
			int id = clientView.getInsertedId();
			DefaultTableModel model = (DefaultTableModel) clientView.table.getModel();
			Client client = clientBLL.findClientById(id);
			if(client != null) {
				model.setRowCount(1);
				model.setValueAt(client.getId(), 0, 0);
				model.setValueAt(client.getName(), 0, 1);
				model.setValueAt(client.getAge(), 0, 2);
				model.setValueAt(client.getEmail(), 0, 3);
				model.setValueAt(client.getAddress(), 0, 4);
				clientView.table.setModel(model);
			}
			else {
				setWarningMessage("The client with the desire id doesn't exist!");
			}
		});
		
		clientView.insertActionListener(e -> {
			String name = clientView.getInsertedName();
			int age = clientView.getInsertedAge();
			String mail = clientView.getInsertedMail();
			String address = clientView.getInsertedAddress();

			Client newClient = new Client(name, age, mail, address);
			if(ageValidator.validate(newClient) == 0) {
				setWarningMessage("The client doesn't have a valid age!");
			}
			else {
				if(emailValidator.validate(newClient) == 0) {
					setWarningMessage("The client doesn't have a valid email!");
				}
				else {
					DefaultTableModel model = (DefaultTableModel) clientView.table.getModel();
					Client client = clientBLL.insertClient(newClient);
					List<Client> list = clientBLL.findAllClients();
					list.add(client);
					model.setRowCount(list.size()-1);
					for(int i = 0; i < list.size()-1; i++) {
						model.setValueAt(list.get(i).getId(), i, 0);
						model.setValueAt(list.get(i).getName(), i, 1);
						model.setValueAt(list.get(i).getAge(), i, 2);
						model.setValueAt(list.get(i).getEmail(), i, 3);
						model.setValueAt(list.get(i).getAddress(), i, 4);
					}
					clientView.table.setModel(model);
				}
			}
		});
		
		clientView.updateActionListener(e -> {
			String name = clientView.getInsertedName();
			int age = clientView.getInsertedAge();
			String mail = clientView.getInsertedMail();
			String address = clientView.getInsertedAddress();
			
			Client newClient = new Client(name, age, mail, address);
			if(ageValidator.validate(newClient) == 0) {
				setWarningMessage("The client doesn't have a valid age!");
			}
			else {
				if(emailValidator.validate(newClient) == 0) {
					setWarningMessage("The client doesn't have a valid email!");
				}
				else {	
					int id = clientView.getInsertedId();
					DefaultTableModel model = (DefaultTableModel) clientView.table.getModel();
					Client client = clientBLL.updateClient(newClient, id);
					List<Client> list = clientBLL.findAllClients();
					model.setRowCount(list.size());
					for(int i = 0; i < list.size(); i++) {
						model.setValueAt(list.get(i).getId(), i, 0);
						model.setValueAt(list.get(i).getName(), i, 1);
						model.setValueAt(list.get(i).getAge(), i, 2);
						model.setValueAt(list.get(i).getEmail(), i, 3);
						model.setValueAt(list.get(i).getAddress(), i, 4);
					}
					clientView.table.setModel(model);
				}
			}
		});
		
		clientView.deleteActionListener(e -> {
			int id = clientView.getInsertedId();
			clientBLL.deleteClientById(id);
			DefaultTableModel model = (DefaultTableModel) clientView.table.getModel();
			List<Client> list = clientBLL.findAllClients();
			model.setRowCount(list.size());
			for(int i = 0; i < list.size(); i++) {
				model.setValueAt(list.get(i).getId(), i, 0);
				model.setValueAt(list.get(i).getName(), i, 1);
				model.setValueAt(list.get(i).getAge(), i, 2);
				model.setValueAt(list.get(i).getEmail(), i, 3);
				model.setValueAt(list.get(i).getAddress(), i, 4);
			}
			clientView.table.setModel(model);
		});
	}
	
	/**
	 * Method which implements all the buttons from the new order window.
	 */
	private void orderViewButtons() {
		orderView.addActionListener(e -> {
			client = orderView.getSelectedClient();
			Product p = orderView.getSelectedProduct();
			products.add(p);
			int quantity = orderView.getInsertedQuantity();
			quantityList.add(quantity);
			if(p.getStock() == 0) {
				setWarningMessage("The stock is empty for this product!");
			}
			else {
				if(quantity > p.getStock()) {
					setWarningMessage("The maximum quantity for this product is " + p.getStock());
				}
				else {
					int newStock = p.getStock() - quantity;
					Product prod = new Product(p.getName(), p.getPrice(), newStock);
					productBLL.updateProduct(prod, p.getId());
				}
			}
		});
		
		orderView.orderActionListener(e -> {
			newOrder = new Order(client.getId());
			Order order = orderBLL.insertOrder(newOrder);
			int id = findLastOrderId();
			for(int i = 0; i < products.size(); i++) {
				OrderHasProduct newOrderHasProduct = new OrderHasProduct(id, products.get(i).getId());
				OrderHasProduct orderHasProduct = orderHasProductBLL.insertOrder(newOrderHasProduct);
			}
			CreatePDF pdf = new CreatePDF(client, products, quantityList, id);
			products = new ArrayList<Product>();
		});
	}
	
	private int findLastOrderId() {
		int id = 0;
		List<Order> list = orderBLL.findAllOrders();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId() > id) {
				id = list.get(i).getId();
			}
		}
		return id;
	}
}
