package businesslayer;

import java.io.Serializable;
import java.util.List;

public interface MenuItem extends Serializable{
	public String getName();
	public void setName(String name);
	public abstract int computePrice();
	public void setPrice(int price);
	public boolean containsBaseProduct(String name);
	public List<MenuItem> getBaseProductList();
	public String toString();
}
