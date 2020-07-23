package presentation;

import java.lang.reflect.Field;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Class that creates the header of a table
 * @author Alina Somcutean
 *
 * @param <T> a generic class
 */
public class GenerateTable<T> {

	/**
	 * Method which creates the header of a table using reflecion technique.
	 * @param t object for which the header should be generated
	 * @return created table
	 */
	public JTable createTable(T t) {
		JTable table = new JTable();
		Field[] fields = t.getClass().getDeclaredFields();
		String[] vect = new String[fields.length];
		int i = 0;
		for(Field f : fields) {
			vect[i] = f.getName();
			i++;
		}
		DefaultTableModel model = new DefaultTableModel(vect, 0);
		table.setModel(model);
		return table;
	}
}
