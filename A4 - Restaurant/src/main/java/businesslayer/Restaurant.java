package businesslayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Restaurant implements RestaurantProcessing{

	private Map<Order, List<MenuItem>> hashMap = new HashMap<Order, List<MenuItem>>();
	private List<MenuItem> menuItems = new ArrayList<MenuItem>();
	
	public boolean invariant(Map<Order, List<MenuItem>> map) {
		for(Entry<Order, List<MenuItem>> entry : map.entrySet()) {
			if(entry.getValue().size() == 0) {
				return false;
			}
		}
		return true;
	}
	
	public void createNewMenuItem(MenuItem menu) {
		assert(invariant(hashMap) == true);
		assert(menu != null);
		int dim = menuItems.size();
		menuItems.add(menu);
		assert(menuItems.size() == dim + 1);
		assert(invariant(hashMap) == true);
	}

	public int deleteMenuItem(String name) {
		assert(invariant(hashMap) == true);
		assert(name != null);
		int dim = menuItems.size();
		for(int i = 0; i < menuItems.size(); i++) {
			String productName = menuItems.get(i).getName();
			if(productName.equals(name) == true) {
				if((menuItems.get(i) instanceof BaseProduct) == true) {
					for(int j = 0; j < menuItems.size(); j++) {
						if((menuItems.get(j) instanceof CompositeProduct) == true) {
							if(menuItems.get(j).containsBaseProduct(productName) == true) {
								assert(dim == menuItems.size());
								assert(invariant(hashMap) == true);
								return 1;
							}
						}
					}
					menuItems.remove(menuItems.get(i));
					assert(menuItems.size() == dim-1);
					assert(invariant(hashMap) == true);
					return 0;
				}
				else {
					menuItems.remove(menuItems.get(i));
					assert(menuItems.size() == dim-1);
					assert(invariant(hashMap) == true);
					return 0;
				}
			}
		}
		assert(menuItems.size() == dim-1);
		assert(invariant(hashMap) == true);
		return 0;
	}

	public void editBaseProduct(MenuItem menu, String name, int price) {
		assert(invariant(hashMap) == true);
		assert(menu != null);
		assert(name != null);
		assert(price != 0);
		int index = 0;
		int indexList = 0;
		for(int i = 0; i < menuItems.size(); i++) {
			if(menu.getName().equals(menuItems.get(i).getName()) == true) {
				index = i;
				menuItems.get(i).setName(name);
				menuItems.get(i).setPrice(price);
			}
			if((menuItems.get(i) instanceof CompositeProduct) == true) {
				for(int j = 0; j < menuItems.get(i).getBaseProductList().size(); j++) {
					if(menu.getName().equals(menuItems.get(i).getBaseProductList().get(j).getName()) == true) {
						indexList= j;
						menuItems.get(i).getBaseProductList().get(j).setName(name);
						menuItems.get(i).getBaseProductList().get(j).setPrice(price);
					}
				}
				assert(menuItems.get(i).getBaseProductList().get(indexList).getName() == name);
				assert(menuItems.get(i).getBaseProductList().get(indexList).computePrice() == price);
				assert(invariant(hashMap) == true);
			}
		}
		assert(menuItems.get(index).getName() == name);
		assert(menuItems.get(index).computePrice() == price);
		assert(invariant(hashMap) == true);
	}
	
	public void editCompositeProduct(String name, String newName) {
		assert(invariant(hashMap) == true);
		assert(name != null);
		assert(newName != null);
		int index = 0;
		for(int i = 0; i < menuItems.size(); i++) {
			if(name.equals(menuItems.get(i).getName()) == true) {
				index = i;
				menuItems.get(i).setName(newName);
			}
		}
		assert(menuItems.get(index).getName() == newName);
		assert(invariant(hashMap) == true);
	}

	public void createNewOrder(Order order, List<MenuItem> list) {
		assert(invariant(hashMap) == true);
		assert(order != null);
		assert(list != null);
		int dim = hashMap.size();
		hashMap.put(order, list);
		assert(hashMap.size() == dim+1);
		assert(invariant(hashMap) == true);
	}
	
	public List<MenuItem> getMenuItem(){
		return this.menuItems;
	}
	
	public void setMenu(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}
	
	public void setOrders(Map<Order, List<MenuItem>> hashMap) {
		this.hashMap = hashMap;
	}
	
	public String toString(List<MenuItem> list) {
		String str = "";
		for(int i = 0; i < list.size(); i++) {
			str += list.get(i).getName();
			str += " ";
		}
		return str;
	}
}
