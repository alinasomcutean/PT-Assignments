package presentation;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Client;

/**
 * Class that creates the graphical user interface specific for the client table.
 * @author Alina Somcutean
 *
 */
public class ClientView extends JFrame{
	public JTable table = new JTable();
	private GenerateTable<Client> generate = new GenerateTable<Client>();
	private Client client = new Client();
	
	private JTextField insertId = new JTextField();
	private JTextField insertName = new JTextField();
	private JTextField insertAge = new JTextField();
	private JTextField insertMail = new JTextField();
	private JTextField insertAddress = new JTextField();
	
	private JButton insertButton = new JButton("Insert");
	private JButton updateButton = new JButton("Update");
	private JButton findAllButton = new JButton("Find All");
	private JButton findByIdButton = new JButton("Find By Id");
	private JButton deleteButton = new JButton("Delete");
	
	/**
	 * Constructor in which are set all the labels, text fields, buttons and table.
	 */
	public ClientView() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setBounds(new Rectangle(1400, 600));
		this.setTitle("Client");
		table = generate.createTable(client);
		getContentPane().add(table);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0, 0, 1000, 600);
		getContentPane().add(scroll);
		
		JLabel id = new JLabel("Id: ");
		id.setBounds(1050, 50, 70, 30);
		id.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(id);
		
		insertId.setBounds(1130, 57, 200, 20);
		getContentPane().add(insertId);
		
		JLabel name = new JLabel("Name: ");
		name.setBounds(1050, 90, 70, 30);
		name.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(name);
		
		insertName.setBounds(1130, 97, 200, 20);
		getContentPane().add(insertName);
		
		JLabel age = new JLabel("Age: ");
		age.setBounds(1050, 130, 70, 30);
		age.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(age);
		
		insertAge.setBounds(1130, 137, 200, 20);
		getContentPane().add(insertAge);
		
		JLabel mail = new JLabel("Mail: ");
		mail.setBounds(1050, 170, 70, 30);
		mail.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(mail);
		
		insertMail.setBounds(1130, 177, 200, 20);
		getContentPane().add(insertMail);
		
		JLabel address = new JLabel("Address: ");
		address.setBounds(1050, 210, 70, 30);
		address.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		getContentPane().add(address);
		
		insertAddress.setBounds(1130, 217, 200, 20);
		getContentPane().add(insertAddress);
		
		insertButton.setBounds(1050, 280, 100, 40);
		getContentPane().add(insertButton);
		
		updateButton.setBounds(1250, 280, 100, 40);
		getContentPane().add(updateButton);
		
		findAllButton.setBounds(1050, 370, 100, 40);
		getContentPane().add(findAllButton);
		
		findByIdButton.setBounds(1250, 370, 100, 40);
		getContentPane().add(findByIdButton);
		
		deleteButton.setBounds(1150, 460, 100, 40);
		getContentPane().add(deleteButton);
	}
	
	public int getInsertedId() {
		return Integer.parseInt(insertId.getText());
	}
	
	/**
	 * Header of a method for the client button
	 * @return
	 */
	public String getInsertedName() {
		return insertName.getText();
	}
	
	public int getInsertedAge() {
		return Integer.parseInt(insertAge.getText());
	}
	
	public String getInsertedMail() {
		return insertMail.getText();
	}
	
	public String getInsertedAddress() {
		return insertAddress.getText();
	}
	
	/**
	 * Header of a method for the insert button
	 * @param actionListener
	 */
	public void insertActionListener(final ActionListener actionListener) {
		insertButton.addActionListener(actionListener);
	}
	
	/**
	 * Header of a method for the update button
	 * @param actionListener
	 */
	public void updateActionListener(final ActionListener actionListener) {
		updateButton.addActionListener(actionListener);
	}
	
	/**
	 * Header of a method for the find all button
	 * @param actionListener
	 */
	public void findAllActionListener(final ActionListener actionListener) {
		findAllButton.addActionListener(actionListener);
	}
	
	/**
	 * Header of a method for the find by id button
	 * @param actionListener
	 */
	public void findByIdActionListener(final ActionListener actionListener) {
		findByIdButton.addActionListener(actionListener);
	}
	
	/**
	 * Header of a method for the delete button
	 * @param actionListener
	 */
	public void deleteActionListener(final ActionListener actionListener) {
		deleteButton.addActionListener(actionListener);
	}
}
