package presentationlayer;

import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ChefGUI extends JFrame implements Observer{
	private JTextArea text = new JTextArea();

	public ChefGUI() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(new Rectangle(500, 400));
		this.setTitle("Chef Window");
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		text.setBounds(100, 50, 300, 200);
		text.setText("A new order with composite product was added!");
		text.setEditable(false);
		getContentPane().add(text);
	}
}
