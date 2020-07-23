package businesslayer;

import java.util.List;

public class BaseProduct implements MenuItem{
	private String name;
	private int price;
	
	public BaseProduct() {
		
	}
	
	public BaseProduct(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int computePrice() {
		return this.price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public boolean containsBaseProduct(String name) {
		return true;
	}

	@Override
	public List<MenuItem> getBaseProductList() {
		return null;
	}
	
	public String toString() {
		return this.name;
	}
}
