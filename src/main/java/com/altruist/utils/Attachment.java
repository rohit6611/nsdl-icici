package com.altruist.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.altruist.nsdl.model.AgentReportModel;

public class Attachment {

	public static void createExcelSheet(List<AgentReportModel> agentReportList) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {

			Integer incompleteApplication = 0,completeApplication = 0;
			Double totalRevenue = 0.00;

			XSSFSheet sheet = workbook.createSheet("Hospi Cash");

			sheet.setColumnWidth(0, 3000);
			sheet.setColumnWidth(1, 5000);
			sheet.setColumnWidth(2, 6000);
			sheet.setColumnWidth(3, 6000);
			sheet.setColumnWidth(4, 5500);
			sheet.setColumnWidth(5, 5000);

			Map<String, Object[]> data = new LinkedHashMap<String, Object[]>();
			data.put("0",
					new Object[] { "Date", "Agent Name", "Incomplete Application", "Complete Application", "Total Application",
							"Total Revenue" });

			for (int i = 0; i < agentReportList.size(); i++) {
				int sno = i + 1;
				AgentReportModel mrb = agentReportList.get(i);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				data.put(Integer.toString(sno), new Object[] { mrb.getDate(), mrb.getAgentUsername(),
						mrb.getApplicationIncomplete(), mrb.getApplicationComplete(), (mrb.getApplicationComplete()+mrb.getApplicationIncomplete()),
						String.valueOf(mrb.getTotalRevenue())});
				
				incompleteApplication = incompleteApplication + mrb.getApplicationIncomplete();
				completeApplication = completeApplication + mrb.getApplicationComplete();
				totalRevenue = totalRevenue + mrb.getTotalRevenue();
			}
			
			if(agentReportList.size()>0) {
				data.put(Integer.toString(agentReportList.size() + 1),
						new Object[] { "Total","", incompleteApplication, completeApplication, (incompleteApplication+completeApplication),
								String.valueOf(Math.round(totalRevenue)) });
			}
			
			fillExcel(sheet, data,workbook);

			FileOutputStream out = new FileOutputStream(new File("HospiCash.xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("Excel file written successfully on disk.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void fillExcel(XSSFSheet sheet, Map<String, Object[]> data,XSSFWorkbook workbook) {
		Set<String> keyset = data.keySet();
		int rownum = 0;
		Row row = null;
		try {
			for (String key : keyset) {
				row = sheet.createRow(rownum++);
				Object[] objArr = data.get(key);
				int cellnum = 0;
				XSSFCellStyle style = null;
				if (key == "0") {
					XSSFFont font = workbook.createFont();
					font.setFontHeightInPoints((short) 12);
					font.setFontName("CALIBRI");
					font.setBold(true);
					

					// Set font into style
					style = workbook.createCellStyle();
					style.setFillForegroundColor(IndexedColors.CORAL.getIndex());
					style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
					style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
					style.setBorderRight(BorderStyle.THIN);
					style.setBorderLeft(BorderStyle.THIN);
					style.setBorderBottom(BorderStyle.THIN);
					style.setBorderTop(BorderStyle.THIN);
					style.setFont(font);
				} else {
					style = workbook.createCellStyle();
					style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
					style.setBorderRight(BorderStyle.THIN);
					style.setBorderLeft(BorderStyle.THIN);
					style.setBorderBottom(BorderStyle.THIN);
					style.setBorderTop(BorderStyle.THIN);
				}
				for (Object obj : objArr) {
					Cell cell = row.createCell(cellnum++);
					if (obj instanceof String) {
						cell.setCellValue((String) obj);
						cell.setCellStyle(style);
					} else if (obj instanceof Integer) {
						cell.setCellValue((Integer) obj);
						cell.setCellValue((Integer) obj);
						cell.setCellStyle(style);
						style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
						style.setBorderRight(BorderStyle.THIN);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
