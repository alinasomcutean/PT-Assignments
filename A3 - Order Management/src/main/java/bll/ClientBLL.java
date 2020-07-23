package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

import bll.validators.*;
import dao.ClientDAO;
import model.Client;

/**
 * This class contains a method for each query for the Client table. Each of them calls the specific method from the data access layer and returns the result.
 * @author Alina Somcutean
 *
 */
public class ClientBLL {
	private List<Validator<Client>> validators;
	private ClientDAO clientDAO;
	
	public ClientBLL() {
		validators = new ArrayList<Validator<Client>>();
		validators.add(new EmailValidator());
		validators.add(new AgeValidator());
		clientDAO = new ClientDAO();
	}
	
	public Client findClientById(int id) {
		Client client = clientDAO.findById(id);
		return client;
	}
	
	public List<Client> findAllClients(){
		List<Client> list = clientDAO.findALL();
		if(list == null) {
			throw new NoSuchElementException("Client table is empty!");
		}
		return list;
	}
	
	public Client updateClient(Client c, int id) {
		Client client = clientDAO.update(c, id);
		if(client == null) {
			throw new NoSuchElementException("The client with the id " + id + " cannot be updated!");
		}
		return client;
	}
	
	public Client insertClient(Client c) {
		Client client = clientDAO.insert(c);
		if(client == null) {
			throw new NoSuchElementException("There is no client to insert!");
		}
		return client;
	}
	
	public void deleteClientById(int id) {
		clientDAO.deleteById(id);
	}
}
