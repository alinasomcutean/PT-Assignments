package datalayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import businesslayer.MenuItem;
import businesslayer.Order;

public class RestaurantSerializator implements Serializable{
	
	private String menuFileName = "Menu.ser";
	private String orderFileName = "Order.ser";
	
	public void serializationMenu(List<MenuItem> menu) {
		try {
			FileOutputStream fileOut = new FileOutputStream(menuFileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(menu);
			out.close();
			fileOut.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void serializationOrder(Map<Order, List<MenuItem>> order) {
		try {
			FileOutputStream fileOut = new FileOutputStream(orderFileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(order);
			out.close();
			fileOut.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<MenuItem> deserializationMenu(){
		List<MenuItem> list = new ArrayList<MenuItem>();
		try {
			FileInputStream fileIn = new FileInputStream(menuFileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			list = (List<MenuItem>) in.readObject();
			in.close();
			fileIn.close();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return list;
	} 
	
	public Map<Order, List<MenuItem>> deserializationOrder(){
		Map<Order, List<MenuItem>> map = new HashMap<Order, List<MenuItem>>();
		try {
			FileInputStream fileIn = new FileInputStream(orderFileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			map = (Map<Order, List<MenuItem>>) in.readObject();
			in.close();
			fileIn.close();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return map;
	}
}
