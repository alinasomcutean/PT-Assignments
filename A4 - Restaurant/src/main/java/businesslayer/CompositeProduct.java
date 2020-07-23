package businesslayer;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct implements MenuItem{
	private List<MenuItem> baseProducts = new ArrayList<MenuItem>();
	private String name;
	private int price = 0;
	
	public CompositeProduct(String name) {
		this.name = name;
	}
	
	public CompositeProduct(String name, List<MenuItem> baseProducts) {
		this.name = name;
		this.baseProducts = baseProducts;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int computePrice() {
		for(MenuItem menu : baseProducts) {
			price += menu.computePrice();
		}
		return this.price;
	}

	@Override
	public void setPrice(int price) {

	}
	
	public boolean containsBaseProduct(String name) {
		for(MenuItem menu : baseProducts) {
			if(menu.getName().equals(name) == true) {
				return true;
			}
		}
		return false;
	}
	
	public List<MenuItem> getBaseProductList(){
		return this.baseProducts;
	}
	
	public String toString() {
		String str = "";
		str += this.name;
		str += ": ";
		for(MenuItem list : baseProducts) {
			str = str + list.getName() + ", ";
		}
		return str;
	}
}
