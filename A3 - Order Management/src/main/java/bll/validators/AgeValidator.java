
package bll.validators;

import model.Client;

/**
 * This is a class contains a method which checks if the inserted age is between 14 and 80 year old.
 * @author Alina Somcutean
 *
 */
public class AgeValidator implements Validator<Client>{

	public static final int MIN_AGE = 14;
	public static final int MAX_AGE = 80;
	@Override
	public int validate(Client t) {
		if(t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
