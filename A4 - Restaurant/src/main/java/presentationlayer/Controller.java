package presentationlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import businesslayer.BaseProduct;
import businesslayer.CompositeProduct;
import businesslayer.MenuItem;
import businesslayer.Order;
import businesslayer.Restaurant;
import datalayer.CreateBill;
import datalayer.RestaurantSerializator;

public class Controller extends Observable{
	private MainView mainView = new MainView();
	private Restaurant restaurant = new Restaurant();
	private RestaurantSerializator restaurantSer = new RestaurantSerializator();
	
	private List<MenuItem> list = restaurantSer.deserializationMenu();
	private Map<Order, List<MenuItem>> hashMap = restaurantSer.deserializationOrder();
	private List<MenuItem> orderList = new ArrayList<MenuItem>();
	private Order order;
	
	private AdministratorGUI admin = new AdministratorGUI();
	private List<MenuItem> productsForCompositeProduct = new ArrayList<MenuItem>();
	
	private WaiterGUI waiter;
	private ChefGUI chef = new ChefGUI();
	
	public Controller() {
		this.addObserver(chef);
		mainView();
	}
	
	public void mainView() {
		mainView.administratorActionListener(e -> {
			admin.setVisible(true);
			administratorButtons();
		});
		
		mainView.waiterActionListener(e -> {
			waiter = new WaiterGUI(list);
			waiter.setVisible(true);
			waiterButtons();
		});
		
		mainView.chefActionListener(e -> {
			chef.setVisible(true);
		});
	}
	
	private void updateTableForMenu(List<MenuItem> list) {
		DefaultTableModel model = (DefaultTableModel) admin.table.getModel();
		model.setRowCount(list.size());
		for(int i = 0; i < list.size(); i++) {
			model.setValueAt(list.get(i).getName(), i, 0);
			model.setValueAt(list.get(i).computePrice(), i, 1);
		}
		admin.table.setModel(model);
	}
	
	private void updateTableForOrder(Map<Order, List<MenuItem>> map) {
		DefaultTableModel model = (DefaultTableModel) waiter.table.getModel();
		model.setRowCount(map.size());
		int i = 0;
		for(Entry<Order, List<MenuItem>> entry : map.entrySet()) {
			model.setValueAt(entry.getKey().getOrderId(), i, 0);
			model.setValueAt(restaurant.toString(entry.getValue()), i, 1);
			model.setValueAt(entry.getKey().getTable(), i, 2);
			i++;
		}
		waiter.table.setModel(model);
	}
	
	private void setWarningMessage(String s) {
		JOptionPane optionPane = new JOptionPane(s, JOptionPane.WARNING_MESSAGE);
		JDialog dialog = optionPane.createDialog("Warning!");
		dialog.setVisible(true);
	}
	
	private void administratorButtons() {
		admin.viewMenuActionListener(e -> {
			List<MenuItem> list = new ArrayList<MenuItem>();
			list = restaurantSer.deserializationMenu();
			updateTableForMenu(list);
		});
		
		admin.deleteProductActionListener(e -> {
			int row = admin.table.getSelectedRow();
			String name = admin.table.getValueAt(row, 0).toString();
			List<MenuItem> list = new ArrayList<MenuItem>();
			list = restaurantSer.deserializationMenu();
			restaurant.setMenu(list);
			int delete = restaurant.deleteMenuItem(name);
			if(delete == 1) {
				setWarningMessage("This product cannot be deleted!");
			}
			restaurantSer.serializationMenu(list);
			updateTableForMenu(list);
		});
		
		admin.addBaseProductActionListener(e -> {
			String name = admin.getBaseProductName();
			int price = admin.getBaseProductPrice();
			BaseProduct baseProduct = new BaseProduct(name, price);
			List<MenuItem> list = new ArrayList<MenuItem>();
			list = restaurantSer.deserializationMenu();
			restaurant.setMenu(list);
			restaurant.createNewMenuItem(baseProduct);
			restaurantSer.serializationMenu(list);
			updateTableForMenu(list);
		});
		
		admin.editBaseProductActionListener(e -> {
			String newName = admin.getEditBaseProductName();
			int newPrice = admin.getEditBaseProductPrice();
			int row = admin.table.getSelectedRow();
			String name = admin.table.getValueAt(row, 0).toString();
			int price = Integer.parseInt(admin.table.getValueAt(row, 1).toString());
			BaseProduct baseProduct = new BaseProduct(name, price);
			List<MenuItem> list = new ArrayList<MenuItem>();
			list = restaurantSer.deserializationMenu();
			restaurant.setMenu(list);
			restaurant.editBaseProduct(baseProduct, newName, newPrice);
			restaurantSer.serializationMenu(list);
			updateTableForMenu(list);
		});
		
		admin.addToCompositeProductActionListener(e -> {
			int row = admin.table.getSelectedRow();
			String name = admin.table.getValueAt(row, 0).toString();
			List<MenuItem> list = new ArrayList<MenuItem>();
			list = restaurantSer.deserializationMenu();
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).getName().equals(name) == true) {
					productsForCompositeProduct.add(list.get(i));
				}
			}	
		});
		
		admin.addCompositeProductActionListener(e -> {
			String name = admin.getCompositeProductName();
			CompositeProduct compositeProduct = new CompositeProduct(name, productsForCompositeProduct);
			List<MenuItem> list = new ArrayList<MenuItem>();
			list = restaurantSer.deserializationMenu();
			restaurant.setMenu(list);
			restaurant.createNewMenuItem(compositeProduct);
			restaurantSer.serializationMenu(list);
			updateTableForMenu(list);
			productsForCompositeProduct = new ArrayList<MenuItem>();
		});
		
		admin.editCompositeProductActionListener(e -> {
			String newName = admin.getEditCompositeProductName();
			int row = admin.table.getSelectedRow();
			String name = admin.table.getValueAt(row, 0).toString();
			List<MenuItem> list = new ArrayList<MenuItem>();
			list = restaurantSer.deserializationMenu();
			restaurant.setMenu(list);
			restaurant.editCompositeProduct(name, newName);
			restaurantSer.serializationMenu(list);
			updateTableForMenu(list);
		});
	}
	
	private void waiterButtons() {
		waiter.viewOrdersActionListener(e -> {
			updateTableForOrder(hashMap);
		});
		
		waiter.addToOrderActionListener(e -> {
			orderList.add(waiter.getSelectedProduct());
		});
		
		waiter.addOrderActionListener(e -> {
			int id = waiter.getOrderId();
			int table = waiter.getOrderTable();
			order = new Order(id, table);
			restaurant.setOrders(hashMap);
			restaurant.createNewOrder(order, orderList);
			for(int i = 0; i < orderList.size(); i++) {
				if(orderList.get(i) instanceof CompositeProduct) {
					setChanged();
					notifyObservers();
				}
			}
			restaurantSer.serializationOrder(hashMap);
			updateTableForOrder(hashMap);
		});
		
		waiter.computeBillActionListener(e -> {
			CreateBill bill = new CreateBill(orderList, order);
			orderList = null;
		});
	}
}
