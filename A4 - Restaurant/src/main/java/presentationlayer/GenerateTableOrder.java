package presentationlayer;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GenerateTableOrder {

	public JTable createTable() {
		JTable table = new JTable();
		String[] columns = {"Id", "Products", "Table"};
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		table.setModel(model);
		return table;
	}
}
