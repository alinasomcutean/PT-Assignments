package businesslayer;

import java.util.List;
import java.util.Map;

public interface RestaurantProcessing {
	/**
	 * Adds a new menu to the list
	 * @pre menu must be different from null
	 * @post Dimension of the list is incremented with 1
	 */
	public void createNewMenuItem(MenuItem menu);
	/**
	 * Delete a menu item from the list after its name
	 * @pre name given as parameter must be different from null
	 * @post returns 1 if the menu was not deleted, otherwise returns 0
	 */
	public int deleteMenuItem(String name);
	/**
	 * Edit a base product from menu
	 * @pre menu and name given as parameters must be different from null
	 * @post the base product is modified with the name and the price specified
	 */
	public void editBaseProduct(MenuItem menu, String name, int price);
	/**
	 * Edit a composite product from menu
	 * @pre the names given as parameter must be different from null
	 * @post change the name of a composite product with the newName
	 */
	public void editCompositeProduct(String name, String newName);
	/**
	 * Add a new order in the map
	 * @pre order and list must be different from null
	 * @post a new item is added in the map
	 */
	public void createNewOrder(Order order, List<MenuItem> list);
}
