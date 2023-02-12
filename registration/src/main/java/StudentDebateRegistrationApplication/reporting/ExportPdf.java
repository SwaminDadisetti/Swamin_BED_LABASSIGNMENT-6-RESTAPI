package StudentDebateRegistrationApplication.reporting;


import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.greatlearning.registration.model.Student;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
public class ExportPdf {
	public static ByteArrayInputStream printTable(List<Student> students) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(90);
			table.setWidths(new int[] { 3, 5, 5, 4, 4 });

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;
			
			hcell = new PdfPCell(new Phrase("Student Id", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setPadding(5);
			table.addCell(hcell);
			

			hcell = new PdfPCell(new Phrase("First Name", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setPadding(5);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Last Name", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setPadding(5);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Course", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setPadding(5);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Country", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setPadding(5);
			table.addCell(hcell);
			
			for (Student student : students) {

				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(student.getId())));
				cell.setPadding(10);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(student.getFirstName()));
				cell.setPadding(10);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(student.getLastName()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(10);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(student.getCourse()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(10);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(student.getCountry()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(10);
				table.addCell(cell);
			}

			PdfWriter.getInstance(document, out);
			document.open();
			document.add(table);

			document.close();

		} catch (DocumentException ex) {

		}

		return new ByteArrayInputStream(out.toByteArray());
	}


}
