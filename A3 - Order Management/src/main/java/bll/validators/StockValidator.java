package bll.validators;

import model.Product;

/**
 * This class contains a method which checks if inserted stock is greater or equal with 0
 * @author Alina Somcutean
 *
 */
public class StockValidator implements Validator<Product>{

	@Override
	public int validate(Product t) {
		if(t.getStock() < 0) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
