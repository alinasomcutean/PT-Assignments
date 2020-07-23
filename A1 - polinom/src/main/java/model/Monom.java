package model;

/**
 * This class find the coefficient and the exponential of a monom
 * @author Alina Somcutean
 *
 */
public class Monom {

	private double coefficient;
	private int exponential;

	/**
	 *Assign the coefficient and exponential value to this monom
	 * @param coefficient has type float
	 * @param exponential has type int
	 */
	public Monom(double coefficient, int exponential)
	{
		this.coefficient = coefficient;
		this.exponential = exponential;
	}

	/**
	 * This method return the coefficient of a monom
	 */
	public double getCoefficient()
	{
		return coefficient;
	}
	
	/**
	 * This method return the exponential of a monom
	 */
	public int getExponential()
	{
		return exponential;
	}
	
	/**
	 * This method execute the addition between 2 monoms
	 * @param m2 is a monom
	 * @return result as a monom
	 */
	public Monom sum(Monom m2) 
	{
		Monom m3 = new Monom(0,0);
		m3.coefficient = this.coefficient + m2.coefficient;
		m3.exponential = m2.exponential;
		return m3;
	}
	
	/**
	 * This method execute the subtraction between 2 monoms
	 * @param m2 is a monom
	 * @return result as a monom
	 */
	public Monom diff(Monom m2)
	{
		Monom m3 = new Monom(0,0);
		m3.coefficient = this.coefficient - m2.coefficient;
		m3.exponential = m2.exponential;
		return m3;
	}
	
	/**
	 * This method execute the product between 2 monoms
	 * @param m2 is a monom
	 * @return result as a monom
	 */
	public Monom product(Monom m2)
	{
		Monom m3 = new Monom(0,0);
		m3.coefficient = this.coefficient * m2.coefficient;
		m3.exponential = this.exponential + m2.exponential;
		return m3;
	}
	
	/**
	 * This method execute the division between 2 monoms
	 * @param m2 is a monom
	 * @return result as a monom
	 */
	public Monom division(Monom m2)
	{
		Monom m3 = new Monom(0,0);
		m3.coefficient = this.coefficient / m2.coefficient;
		m3.exponential = this.exponential - m2.exponential;
		return m3;
	}
	
	/**
	 * This method execute the derivation of a monom
	 * @return result as a monom
	 */
	public Monom derivation()
	{
		Monom m3 = new Monom(0,0);
		m3.coefficient = this.coefficient * this.exponential;
		m3.exponential = this.exponential - 1;
		return m3;
	}
	
	/**
	 * This method execute the integrationof a  monom
	 * @return result as a monom
	 */
	public Monom integration()
	{
		Monom m3 = new Monom(0,0);
		m3.exponential = this.exponential + 1;
		m3.coefficient = this.coefficient / m3.exponential;
		return m3;
	}
}
