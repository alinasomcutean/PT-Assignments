package presentation;

import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.Client;
import model.Product;

/**
 * Class that creates a pdf file with the bill of a new order.
 * @author Alina Somcutean
 *
 */
public class CreatePDF {
	private static String file = "E:/Facultate/Anul II/Sem II/Fundamental Programming Techniques/Homeworks/HW_3/Bill";
	
	/**
	 * Constructor which creates the pdf file for a specific client, who order a list of products, with a specific quantity for each product.
	 * @param client client that made the order
	 * @param list list of the products ordered
	 * @param quantity list of quantities for each product
	 */
	public CreatePDF(Client client, List<Product> list, List<Integer> quantity, int id) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file + id + ".pdf"));
			document.open();
			addContent(document, client, list, quantity);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method which add text to the document
	 * @param document document in which the text is added
	 * @param client client who placed the order
	 * @param list list of the ordered products
	 * @param quantity list of the quantities for each product
	 * @throws DocumentException
	 */
	private static void addContent(Document document, Client client, List<Product> list, List<Integer> quantity) throws DocumentException{
		document.add(new Paragraph("Client " + client.getName() + " ordered the following products:"));
		Paragraph paragraph = new Paragraph();
		addEmptyLine(paragraph, 2);
		document.add(paragraph);
		int total = createTable(document, list, quantity);
		document.add(paragraph);
		document.add(new Paragraph("Total: " + total));
	}
	
	/**
	 * Method which creates a table in a document and finds the total price of the order.
	 * @param document document in which the table is added
	 * @param list list of the ordered products
	 * @param quantity list of the quantities for each product
	 * @return the total price of the order
	 * @throws DocumentException
	 */
	private static int createTable(Document document, List<Product> list, List<Integer> quantity) throws DocumentException{
		PdfPTable table = new PdfPTable(4);
		
		PdfPCell c1 = new PdfPCell(new Phrase("Id"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Product Name"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Quantity"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Total price per product"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		table.setHeaderRows(1);
		int count = 1;
		int total = 0;
		
		for(int i = 0; i < list.size(); i++) {
			table.addCell(String.valueOf(count));
			table.addCell(list.get(i).getName());
			table.addCell(String.valueOf(quantity.get(i)));
			total = total + quantity.get(i) * list.get(i).getPrice();
			table.addCell(String.valueOf(quantity.get(i) * list.get(i).getPrice()));
			count++;
		}
		
		document.add(table);
		return total;
	}
	
	/**
	 * Method which adds empty lines
	 * @param paragraph 
	 * @param nr number of empty lines
	 */
	private static void addEmptyLine(Paragraph paragraph, int nr) {
		for(int i = 0; i < nr; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}
