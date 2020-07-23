package bll.validators;

/**
 * An interface used to create some validators of the data
 * @author Alina Somcutean
 *
 * @param <T> a generic class
 */
public interface Validator<T> {

	/**
	 * The header of the validator method
	 * @param t an object of a generic class
	 */
	public int validate(T t);
}
