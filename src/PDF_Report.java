import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class PDF_Report {
	
	private Document d;
	private Paragraph parag ;
	private PdfWriter writer;
	private PdfPTable tab ;
	
	public PDF_Report(String id) {

		 d = new Document();
		try {
			writer = PdfWriter.getInstance(d, new FileOutputStream("report.pdf"));
			writer.setSpaceCharRatio(PdfWriter.NO_SPACE_CHAR_RATIO);
			d.open();

			d.add(new Paragraph("Policies Details Report",
					FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.BLUE)));

			d.add(new Paragraph(new java.util.Date().toString()));

			d.add(new Paragraph(
					"----------------------------------------------------------------------------------------------------------------"));

			d.add(new Phrase("\n"));

		    parag = new Paragraph("", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, null));
			parag.add(new Phrase("Client Info: ",FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.BOLD, BaseColor.RED)));
			parag.add(new Phrase("\n\n"));

			ArrayList<String> list = DB_operation.client_info(id);
			parag.add(new Phrase("Client id :" + list.get(0)));
			parag.add(new Phrase("\n"));
			parag.add(new Phrase("Name :" + list.get(1) + "                                                          "));
			parag.add(new Phrase("Age :" + list.get(4)));
			parag.add(new Phrase("\n"));
			parag.add(new Phrase(
					"Address :" + list.get(2) + "                                                         "));
			parag.add(new Phrase("Phone :" + list.get(3)));
			parag.add(new Phrase("\n"));
			parag.add(new Phrase(
					"----------------------------------------------------------------------------------------------------------------"));
			parag.add(new Phrase("\n"));
			d.add(parag);

			d.add(new Phrase("\n\n\n"));
			
			
			Insurances("Pictures//life.jpg"," Life Insurance: ", "1", id);
			
			d.add(new Phrase("\n\n\n"));
			
			Insurances("Pictures//hosp.png"," Hospitalization Insurance: " ,"2", id);
			
			d.add(new Phrase("\n\n\n"));
			
			Insurances("Pictures//icon_car.png","Car Insurance: " ,"3", id);
			
			d.add(new Phrase("\n\n\n"));
			
			
			


			d.close();
			
			/** Show the report Directly after Creation**/
			if (Desktop.isDesktopSupported()) {
			    try {
			        File myFile = new File("report.pdf");
			        Desktop.getDesktop().open(myFile);
			    } catch (IOException ex) {
			        
			    }
			}

		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public void Insurances(String path,String Title,String type,String id){
		
		
		 tab = new PdfPTable(1);
		PdfPCell cell = new PdfPCell();
		Paragraph p = new Paragraph();
		
		
			Image img;
			try { 
				img = Image.getInstance(path);
		
			
		p.add(new Chunk(img, 0, 0));
		p.add(new Phrase(Title,FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.BOLD, BaseColor.RED)));
		cell.addElement(p);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setMinimumHeight(60);
		tab.addCell(cell);
		tab.setHorizontalAlignment(100);
		d.add(tab);
		} catch (DocumentException|IOException e) {
			e.printStackTrace();
		}
		
		ResultSet rs = DB_operation.ALL(type, id);
		
		int size=0;
		try {
			while(rs.next()){
				
				size++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		int i = 1;
		try {
			if(size!=0){
				rs.beforeFirst();
			while (rs.next()) {
				
				d.add(new Phrase("Insurance " + i + " :",FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.PLAIN, BaseColor.GRAY)));

				PdfPTable table = new PdfPTable(5);
				table.addCell("Policy ID");
				table.addCell("Policy Type");
				table.addCell("Policy Date");
				table.addCell("Policy Amount");
				table.addCell("Agent Name");

				table.addCell("" + rs.getInt(1));
				table.addCell("" + rs.getString(17));
				table.addCell("" + rs.getString(7));
				table.addCell("" + rs.getString(5));
				table.addCell(rs.getString(19) + " " + rs.getString(20));

				d.add(table);

				parag = new Paragraph("\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.PLAIN, null));
				parag.add(new Phrase("Details : ",
						FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.PLAIN, BaseColor.GRAY)));
				parag.add(new Phrase("\n\n"));
				d.add(parag);

				tab = new PdfPTable(2);
				tab.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				tab.setHorizontalAlignment(150);
				tab.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				tab.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
				tab.getDefaultCell().setFixedHeight(30);
				
				if(type.equals("1")){
				tab.addCell(new Phrase("Term: " + rs.getString(6)));
				tab.addCell(new Phrase(""));

				tab.addCell(new Phrase("First Beneficiary: " + rs.getString(8)));
				tab.addCell(new Phrase("Second Beneficiary: " + rs.getString(9)));

				
				}
				
				if(type.equals("2")){
					
					tab.addCell(new Phrase("Hospitalization Type: " + rs.getString(12)));
					tab.addCell(new Phrase(""));
				}
				
				if(type.equals("3")){
					
					
					tab.addCell(new Phrase("Company: "+rs.getString(24)));
					tab.addCell(new Phrase("Car Model: "+rs.getString(28)+" ("+rs.getString(26)+")"));
					tab.addCell(new Phrase("Accident: "+rs.getString(30)));
					
					
				}
				
				tab.addCell(new Phrase("Applicant Name: " + rs.getString(10)));
				tab.addCell(new Phrase("Applicant Phone: " + rs.getString(11)));

				tab.addCell(new Phrase("Agent Name: " + rs.getString(16) + " " + rs.getString(17)));
				tab.addCell(new Phrase("Agent mail: " + rs.getString(18)));
				
				d.add(tab);

				i++;
				d.add(new Phrase("\n"));
				//d.add(new Phrase("\n"));
				
			}
			
			
			}
			else {
				
				d.add(new Phrase("You Don't have this insurance yet ! "));
			}
			
			d.add(new Phrase("\n"));
			d.add(new Phrase("--------------------------------------------------------------------------------------------------------------"));
			d.add(new Phrase("\n"));
			
		} catch (SQLException |DocumentException e) {
			System.out.println("error!!");
		}
		
		
	
		
		
		
		
	}
	
	

public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				new PDF_Report("22");
			}
		});

	}

}
