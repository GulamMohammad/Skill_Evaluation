package com.marlabs.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import com.marlabs.model.TechnologyMaster;

public class MyExcelHelper {

	public static boolean checkExcelFormate(MultipartFile file) {

		String contentType = file.getContentType();

		// check that file is of excel type or not
		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
			return true;
		else
			return false;
	}

	// convert excel to list of Technology master
	public static List<TechnologyMaster> convertExcelToListOfTechnologyMaster(InputStream is) {

		List<TechnologyMaster> list = new ArrayList();

		try {

			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheet("data");

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

				TechnologyMaster t = new TechnologyMaster();

				while (cells.hasNext()) {
					Cell cell = cells.next();

					switch (cid) {

					case 0:
						t.setTechId(cell.getStringCellValue());
						break;
					case 1:
						t.setTechName(cell.getStringCellValue());
						break;
					default:
						break;

					}
					cid++;
				}
				list.add(t);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
