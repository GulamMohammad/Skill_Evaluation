package com.marlabs.helper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.marlabs.model.AssociateDetailsMaster;
import com.sun.jdi.InternalException;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssociatePDFExporter {

	private List<AssociateDetailsMaster> associateList;

	public void export(HttpServletResponse response) throws InternalException, IOException {
		// Document document = new Document(PageSize.A4);

		// String path = "associates.pdf";
		PdfWriter writer = new PdfWriter(response.getOutputStream());
		PdfDocument pdfdocument = new PdfDocument(writer);
		pdfdocument.setDefaultPageSize(PageSize.A4);

		Document document = new Document(pdfdocument);


		 String imgPath = "src/main/resources/img/Marlabs_Logo.jpg";
		 ImageData imageData = ImageDataFactory.create(imgPath);
		 Image image = new Image(imageData);
		 image.setFixedPosition(pdfdocument.getDefaultPageSize().getWidth()/2-320, pdfdocument.getDefaultPageSize().getWidth()/2-160);
		 image.setOpacity(0.3f);

		DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		float col1 = 285f;
		float col150 = col1 + 150f;
		float twoColWidth[] = { col150, col1 };
		float rowWidth[] = { 4.5f, 4.5f, 4.5f, 4.5f, 4.5f, 4.5f, 4.5f, };

		Table table = new Table(twoColWidth);
		table.addCell(new Cell().add("Associate Details Master").setBorder(Border.NO_BORDER).setBold().setFontSize(18)
				.setFontColor(com.itextpdf.kernel.color.Color.GREEN));
		table.addCell(new Cell().add("Date :" + currentDateTime).setBorder(Border.NO_BORDER).setBold());
		document.add(table);

		// Table header
		Table table1 = new Table(1);

		// table for HeaderRow
		Table table2 = new Table(rowWidth);

		writeTableHeader(table1);
		writeTableHeaderRow(table2);
		wirteTableData(table2);
		document.add(image);
		document.add(table1);
		document.add(table2);
		document.close();
	}

	private void writeTableHeaderRow(Table table) {

		// table.setMaxWidth();

		Cell cell = new Cell();
		cell.setWidth(6.5f);
		cell.setBackgroundColor(com.itextpdf.kernel.color.Color.BLUE);
		cell.setPadding(3);

		
		table.addCell(new Cell().add("Batch Code").setBackgroundColor(com.itextpdf.kernel.color.Color.YELLOW));

		table.addCell(new Cell().add("Associate Id").setBackgroundColor(com.itextpdf.kernel.color.Color.YELLOW));

		table.addCell(new Cell().add("Associate Name").setBackgroundColor(com.itextpdf.kernel.color.Color.YELLOW));

		table.addCell(new Cell().add("Assessor Mark").setBackgroundColor(com.itextpdf.kernel.color.Color.YELLOW));

		table.addCell(new Cell().add("Mantor Mark").setBackgroundColor(com.itextpdf.kernel.color.Color.YELLOW));

		table.addCell(new Cell().add("Assessor Ramarks").setBackgroundColor(com.itextpdf.kernel.color.Color.YELLOW));

		table.addCell(new Cell().add("Mantor Remarks").setBackgroundColor(com.itextpdf.kernel.color.Color.YELLOW));
	}

	private void wirteTableData(Table table) {
		
		float totalAssessorMarks = 0f;
		int totalAsssessor = 1;
		float totalMentorMarks = 0f;
		int totalMentor = 1;
		for (AssociateDetailsMaster master : associateList) {
			totalAssessorMarks = (float) (totalAssessorMarks + master.getAssessorMark());
			totalAsssessor++;
			totalMentorMarks = (float) (totalMentorMarks + master.getMentorMark()); 
			totalMentor++;
			table.addCell(String.valueOf(master.getBatchCode()));
			table.addCell(String.valueOf(master.getAssociateId()));
			table.addCell(String.valueOf(master.getAssociateName()));
			table.addCell(String.valueOf(master.getAssessorMark()));
			table.addCell(String.valueOf(master.getMentorMark()));
			table.addCell(String.valueOf(master.getAssessorRemarks()));
			table.addCell(String.valueOf(master.getMentorRemarks()));
		}
		table.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add("Total Average").setBackgroundColor(Color.CYAN).setFontSize(15).setBold());
		table.addCell(new Cell().add(String.valueOf(totalAssessorMarks/totalAsssessor)).setBackgroundColor(Color.CYAN));
		table.addCell(new Cell().add(String.valueOf(totalMentorMarks/totalMentor)).setBackgroundColor(Color.CYAN));

	}

	private void writeTableHeader(Table table1) {
		String header = "Associate Deatails Master Table";
		Cell cell = new Cell();
		cell.setBackgroundColor(com.itextpdf.kernel.color.Color.RED);
		cell.setWidth(1000);
		cell.setFontSize(20);
		cell.add(header).setTextAlignment(TextAlignment.CENTER).setBold();
		// cell.setTextAlignment(alignment)
		table1.addCell(cell);

	}

}