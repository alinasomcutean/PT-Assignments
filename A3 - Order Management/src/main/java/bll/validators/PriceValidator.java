package bll.validators;

import model.Product;

/**
 * This class contains a method which checks if the inserted price is greater than 0
 * @author Alina Somcutean
 *
 */
public class PriceValidator implements Validator<Product> {

	//@Override
	public int validate(Product t) {
		if(t.getPrice() <= 0) {
			return 0;
		}
		else {
			return 1;
		}
	}

}