package PT2019.Polinom.PolinomProject;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import model.Monom;
import model.Polinom;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	private Polinom p1;
	private Polinom p2;
	private Monom m1;
	private Monom m2;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
        this.p1 = new Polinom();
        this.p2 = new Polinom();
        this.m1 = new Monom(4,3);
        this.m2 = new Monom(2,3);
        this.p1.createMonoms("3x^2+5x^1+2x^0");
    	this.p2.createMonoms("2x^1+1x^0");
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    
    public void testMonomSum()
    {
    	Monom m3 = m1.sum(m2);
    	assertEquals(6.0, m3.getCoefficient());
    	assertEquals(3, m3.getExponential());
    }
    
    public void testMonomDiff()
    {
    	Monom m3 = m1.diff(m2);
    	assertEquals(2.0, m3.getCoefficient());
    	assertEquals(3, m3.getExponential());
    }
    
    public void testMonomProduct()
    {
    	Monom m3 = m1.product(m2);
    	assertEquals(8.0, m3.getCoefficient());
    	assertEquals(6, m3.getExponential());
    }
    
    public void testMonomDivision()
    {
    	Monom m3 = m1.division(m2);
    	assertEquals(2.0, m3.getCoefficient());
    	assertEquals(0, m3.getExponential());
    }
    
    public void testMonomDerivation()
    {
    	Monom m3 = m1.derivation();
    	assertEquals(12.0, m3.getCoefficient());
    	assertEquals(2, m3.getExponential());
    }

    public void testMonomIntegration()
    {
    	Monom m3 = m1.integration();
    	double x = 4/3;
    	assertEquals(x, m3.getCoefficient());
    	assertEquals(4, m3.getExponential());
    }
    
    public void testPolinomSum()
    {  
    	assertEquals("+3.0x^2 +7.0x^1 +3.0x^0 ", p1.sum(p2).polinomToString());
    }
    
    public void testPolinomDiff()
    {  
    	assertEquals("+3.0x^2 +3.0x^1 +1.0x^0 ", p1.diff(p2).polinomToString());
    }
    
    public void testPolinomProduct()
    {  
    	assertEquals("+6.0x^3 +13.0x^2 +9.0x^1 +2.0x^0 ", p1.product(p2).polinomToString());
    }
    
    public void testPolinomDivide()
    {  
    	assertEquals("+1.5x^1 +1.75x^0 ", p1.divide(p2)[1].polinomToString());
    	assertEquals("+0.25x^0 ", p1.divide(p2)[0].polinomToString());
    }
    
    public void testPolinomDerivation()
    {
    	assertEquals("+6.0x^1 +5.0x^0 ", p1.derivation().polinomToString());
    }
    
    public void testPolinomIntegration()
    {
    	assertEquals("+1.0x^3 +2.5x^2 +2.0x^1 ", p1.integration().polinomToString());
    }
}
