package datalayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import businesslayer.MenuItem;
import businesslayer.Order;

public class CreateBill {
	private File file = new File("E:/Facultate/Anul II/Sem II/Fundamental Programming Techniques/Homeworks/HW_4/Bill.txt");

	public CreateBill(List<MenuItem> list, Order order) {
		try {
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write("The id of the order: " + order.getOrderId());
			writer.write("\nTable of the order: " + order.getTable());
			writer.write("\n" + order.getDate() + "\n\n");
			int price = 0;
			for(int i = 0; i < list.size(); i++) {
				int p = list.get(i).computePrice();
				writer.write(list.get(i).getName() + " " + p + "\n");
				price = price + p;
			}
			writer.write("\nTotal price: " + price);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
