package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

import bll.validators.*;
import dao.ProductDAO;
import model.Product;

/**
 * This class contains a method for each query for the product table. Each of them calls the specific method from the data access layer and returns the result.
 * @author Alina Somcutean
 *
 */
public class ProductBLL {
	private List<Validator<Product>> validators;
	private ProductDAO productDAO;
	
	public ProductBLL() {
		validators = new ArrayList<Validator<Product>>();
		validators.add(new PriceValidator());
		validators.add(new StockValidator());
		productDAO = new ProductDAO();
	}
	
	public Product findProductById(int id) {
		Product product = productDAO.findById(id);
		return product;
	}
	
	public List<Product> findAllProducts(){
		List<Product> list = productDAO.findALL();
		if(list == null) {
			throw new NoSuchElementException("Product table is empty!");
		}
		return list;
	}
	
	public Product updateProduct(Product p, int id) {
		Product product = productDAO.update(p, id);
		if(product == null) {
			throw new NoSuchElementException("The product with the id " + id + " cannot be updated!");
		}
		return product;
	}
	
	public Product insertProduct(Product p) {
		Product product = productDAO.insert(p);
		if(product == null) {
			throw new NoSuchElementException("There is no product to insert!");
		}
		return product;
	}
	
	public void deleteProductById(int id) {
		productDAO.deleteById(id);
	}
}
