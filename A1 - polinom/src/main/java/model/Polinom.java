package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class executes all the operations between 2 polynomials
 * @author Alina Somcutean
 *
 */
public class Polinom {
	
	private String REGEX = "([+-]?[^-+]+)";
	private static Pattern pattern;
	private static Matcher matcher;
	private ArrayList<Monom> list;
	
	/**
	 * 
	 */
	public Polinom()
	{
		this.list = new ArrayList<Monom>();
	}
	 
	/**
	 * This method creats a list of monoms
	 * @param polynomial - as a string
	 */
	public void createMonoms(String polynomial)
	{
		ArrayList<Monom> monomList = new ArrayList<Monom>();
		float coeff;
		int exp;
		
		pattern = Pattern.compile(REGEX);
	    matcher = pattern.matcher(polynomial);
	    
	    while(matcher.find())
	    {
	    	String monom = matcher.group();
	    	
	    	String[] number = monom.split("x");
	    	coeff = Float.parseFloat(number[0]);
	    	String str;
	    	str = number[1].substring(1);
	    	exp = Integer.parseInt(str);
	    	
	    	Monom new_monom = new Monom(coeff, exp);
	    	monomList.add(new_monom);
	    }
		this.list = monomList;
	}
	
	/**
	 * This method initialize a list of monoms with coefficient 0
	 * @param dim - list's size
	 * @return list which was created
	 */
	public ArrayList<Monom> init(int dim)
	{
		ArrayList<Monom> aux = new ArrayList<Monom>();
		for(int i = 0; i <= dim; i++)
		{
			aux.add(new Monom(0,dim-i));
		}
		return aux;
	}
	
	/**
	 * This method executes the addition between 2 polynomials
	 * @param p - a polynomial of type Polinom
	 * @return the result as a polynomial
	 */
	public Polinom sum(Polinom p)
	{
		Polinom result = new Polinom();
		ArrayList<Monom> l = new ArrayList<Monom>();
		int degree = -1;
		if(this.list.get(0).getExponential() > p.list.get(0).getExponential())
			degree = this.list.get(0).getExponential();
		else
			degree = p.list.get(0).getExponential();
		l = init(degree);
		for(int i = 0; i < this.list.size(); i++)
		{
			l.set(degree - this.list.get(i).getExponential(), new Monom(this.list.get(i).getCoefficient(), this.list.get(i).getExponential()));
		}
		for(int i = 0; i < p.list.size(); i++)
		{
			Monom m = new Monom(p.list.get(i).getCoefficient(), p.list.get(i).getExponential());
			l.set(degree - p.list.get(i).getExponential(), l.get(degree - p.list.get(i).getExponential()).sum(m));
		}
		result.list = l;
		return result;
	}
	
	/**
	 * This method executes the subtraction between 2 polynomials
	 * @param p - a polynomial of type Polinom
	 * @return the result as a polynomial
	 */
	public Polinom diff(Polinom p)
	{
		Polinom result = new Polinom();
		ArrayList<Monom> l = new ArrayList<Monom>();
		int degree;
		if(this.list.get(0).getExponential() > p.list.get(0).getExponential())
			degree = this.list.get(0).getExponential();
		else
			degree = p.list.get(0).getExponential();
		l = init(degree);
		for(int i = 0; i < this.list.size(); i++)
		{
			l.set(degree - this.list.get(i).getExponential(), new Monom(this.list.get(i).getCoefficient(), this.list.get(i).getExponential()));
		}
		for(int i = 0; i < p.list.size(); i++)
		{
			Monom m = new Monom(p.list.get(i).getCoefficient(), p.list.get(i).getExponential());
			l.set(degree - p.list.get(i).getExponential(), l.get(degree - p.list.get(i).getExponential()).diff(m));
		}
		result.list = l;
		
		return result;
	}
	
	/**
	 * This method executes the product between 2 polynomials
	 * @param p - a polynomial of type Polinom
	 * @return the result as a polynomial
	 */
	public Polinom product(Polinom p)
	{
		Polinom result = new Polinom();
		ArrayList<Monom> l = new ArrayList<Monom>();
		int degree = this.list.get(0).getExponential() + p.list.get(0).getExponential();
		l = init(degree);
		for(int i = 0; i < this.list.size(); i++)
		{
			Monom m1 = new Monom(this.list.get(i).getCoefficient(), this.list.get(i).getExponential());
			for(int j = 0; j < p.list.size(); j++)
			{
				Monom m2 = new Monom(p.list.get(j).getCoefficient(), p.list.get(j).getExponential());
				int pos = this.list.get(i).getExponential() + p.list.get(j).getExponential();
				l.set(degree - pos, m1.product(m2).sum(l.get(degree-pos)));
			}
		}
		result.list = l;
		return result;
	}
	
	/**
	 * This method executes the product between a polynomial and a monom
	 * @param m - a monom of type Monom
	 * @return the result as a polynomial
	 */
	private Polinom product(Monom m)
	{
		Polinom result = new Polinom();
		ArrayList<Monom> l = new ArrayList<Monom>();
		int degree = this.list.get(0).getExponential() + m.getExponential();
		l = init(degree);
		for(int i = 0; i < this.list.size(); i++)
		{
			Monom monom = new Monom(this.list.get(i).getCoefficient(), this.list.get(i).getExponential());
			int pos = this.list.get(i).getExponential() + m.getExponential();
			l.set(degree - pos, monom.product(m));
		}
		result.list = l;
		return result;
	}
	
	/**
	 * This method executes the division between 2 polynomials
	 * @param p - a polynomial of type Polinom
	 * @return the result as an array of polynomial of size 2 which has on the position 0 the remainder and on the position 1 the quotient
	 */
	public Polinom[] divide(Polinom p)
	{
		Polinom remainder = new Polinom();
		Polinom quotient = new Polinom();
		Polinom aux = new Polinom();
		aux = this;
		while(aux.list.get(0).getExponential() >= p.list.get(0).getExponential() && aux.list.size() > 0)
		{
			Monom m = new Monom(0,0);
			m = aux.list.get(0).division(p.list.get(0));
			quotient.list.add(m);	
			aux = aux.diff(p.product(m));
			aux = aux.removeZeroCoeff();
		}
		remainder = aux;
		Polinom[] polinom = new Polinom[2];
		polinom[0] = remainder;
		polinom[1] = quotient;
		return polinom;
	}

	/**
	 * This method removes all the monoms from a polynomial which has the coefficient 0
	 * @return a polynomial
	 */
	public Polinom removeZeroCoeff()
	{
		Polinom result = new Polinom();
		for(int i = 0; i < this.list.size(); i++)
		{
			if(this.list.get(i).getCoefficient() != 0)
			{
				result.list.add(this.list.get(i));
			}
		}
		return result;
	}
	
	/**
	 * This method executes the derivation of a polynomial
	 * @return the result as a polynomial
	 */
	public Polinom derivation()
	{
		Polinom result = new Polinom();
		ArrayList<Monom> l = new ArrayList<Monom>();
		int degree = this.list.get(0).getExponential();
		l = init(degree);
		for(int i = 0; i < this.list.size(); i++)
		{
			Monom m = new Monom(this.list.get(i).getCoefficient(), this.list.get(i).getExponential());
			l.set(degree - this.list.get(i).getExponential(), m.derivation());
		}
		result.list = l;
		return result;
	}
	
	/**
	 * This method executes the integration of a polynomial
	 * @return the result as a polynomial
	 */
	public Polinom integration()
	{
		Polinom result = new Polinom();
		ArrayList<Monom> l = new ArrayList<Monom>();
		int degree = this.list.get(0).getExponential() + 1;
		l = init(degree);
		for(int i = 0; i < this.list.size(); i++)
		{
			Monom m = new Monom(this.list.get(i).getCoefficient(), this.list.get(i).getExponential());
			l.set(degree - this.list.get(i).getExponential() - 1, m.integration());
		}
		result.list = l;
		return result;
	}
	
	/**
	 * This method converts a polynomial from type Polinom to type String
	 * @return the polynomial as a String
	 */
	public String polinomToString()
	{
		String result = "";
		for(int i = 0; i < this.list.size(); i++)
		{
			if(this.list.get(i).getCoefficient() > 0)
			{
				result = result + "+" + this.list.get(i).getCoefficient() + "x^" + this.list.get(i).getExponential() + " ";
			}
			else
				if(this.list.get(i).getCoefficient() != 0)
				{
					result = result + this.list.get(i).getCoefficient() + "x^" + this.list.get(i).getExponential() + " ";
				}
		}
		return result;
	}
}
