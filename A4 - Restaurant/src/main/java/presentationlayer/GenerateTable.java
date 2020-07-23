package presentationlayer;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GenerateTable {
	
	public JTable createTable() {
		JTable table = new JTable();
		String[] columns = {"Name", "Price"};
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		table.setModel(model);
		return table;
	}
}
