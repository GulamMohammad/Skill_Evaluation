package com.marlabs.helper;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.marlabs.model.AssociateDetailsMaster;
import com.marlabs.model.BatchMaster;

public class AssociateDetailsExcelHelper {

	// check the file type
	public static boolean checkExcelFormate(MultipartFile file) {

		String fileType = file.getContentType();

		if (fileType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else
			return false;
	}

	// convert excel to List of AssociateMaster

	public static List<AssociateDetailsMaster> ConvertExcel(InputStream is) {

		List<AssociateDetailsMaster> list = new ArrayList<>();

		try {
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheet("data1");
			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cells = row.iterator();
				int cid = 0;

				AssociateDetailsMaster master = new AssociateDetailsMaster();
				while (cells.hasNext()) {
					Cell cell = cells.next();

					switch (cid) {
					case 0: master.setBatchCode(cell.getStringCellValue());
						break;
					case 1:
						master.setAssociateId((int) cell.getNumericCellValue());
						break;
					case 2:
						master.setAssociateName(cell.getStringCellValue());
						break;
					case 3:
						master.setAssessorMark(cell.getNumericCellValue());
						break;
					case 4:
						master.setMentorMark(cell.getNumericCellValue());
						break;
					case 5:
						master.setAssessorRemarks(cell.getStringCellValue());
						break;
					case 6:
						master.setMentorRemarks(cell.getStringCellValue());
						break;

					default:
						break;
					}
					cid++;
				}
				list.add(master);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

}
