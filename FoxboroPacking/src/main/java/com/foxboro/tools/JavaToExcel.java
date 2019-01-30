package com.foxboro.tools;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

public class JavaToExcel {
	
	/**
	 * <包材历史数据>导出为Excel
	 * @param sheetName sheet名称
	 * @param title 单元格标题
	 * @param values 内容
	 * @param wb HSSFWorkbook对象
	 * @return
	 */
	public static HSSFWorkbook getHSSFWorkbookForMaterialsHistory(String sheetName,String[] title,String[][] values,HSSFWorkbook wb){
		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
		if(wb == null){
			wb = new HSSFWorkbook();
		}

		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);

		// 第三步，在sheet中添加表头第0行（可以是0～65535之间的任何一个）,注意老版本poi对Excel的行数列数有限制
		HSSFRow row = sheet.createRow(0);

		// 第四步，创建单元格，并设置值表头 设置表头居中
		sheet.setColumnWidth((short) 0, (short) 1200);// 设置列宽
		sheet.setColumnWidth((short) 1, (short) 4000);
		sheet.setColumnWidth((short) 2, (short) 3000);
		sheet.setColumnWidth((short) 3, (short) 3000);
		sheet.setColumnWidth((short) 4, (short) 4000);
		sheet.setColumnWidth((short) 5, (short) 4000);
		sheet.setColumnWidth((short) 6, (short) 3000);
		sheet.setColumnWidth((short) 7, (short) 5500);
		sheet.setColumnWidth((short) 8, (short) 2000);
		sheet.setColumnWidth((short) 9, (short) 7000);
		
		HSSFCellStyle headerStyle = wb.createCellStyle();	// 创建标题样式
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直对齐居中
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		HSSFFont headerFont = wb.createFont();	// 创建标题字体样式
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
		headerFont.setFontName("Times New Roman");
		headerFont.setFontHeightInPoints((short) 10);
		headerStyle.setFont(headerFont);	// 创建标题字体样式
		headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		
		HSSFCellStyle cellStyle = wb.createCellStyle();// 设置正文样式
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直对齐居中
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		//cellStyle.setWrapText(true); // 设置为自动换行
		HSSFFont cellFont = wb.createFont();
		cellFont.setFontName("Times New Roman");
		cellFont.setFontHeightInPoints((short) 10);
		cellStyle.setFont(cellFont);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		
		//声明列对象
		HSSFCell cell = null;

		//创建单元格（每列）标题
		for(int i=0;i<title.length;i++){
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(headerStyle);
		}

		//创建内容
		for(int i=0;i<values.length;i++){//有i行
			row = sheet.createRow(i + 1);//从第二行开始创建内容
			for(int j=0;j<values[i].length;j++){
				//将内容按顺序赋给对应的列对象
				row.createCell(j).setCellValue(values[i][j]);
				row.getCell(j).setCellStyle(cellStyle);
			}
		}
		
		return wb;
	}
	
	/**
	 * <包材订购>数据导出为Excel
	 * @param sheetName sheet名称
	 * @param title 单元格标题
	 * @param values 内容
	 * @param wb HSSFWorkbook对象
	 * @return
	 */
	public static HSSFWorkbook getHSSFWorkbookForMaterialsOrder(String sheetName,String[] title,String[][] values,HSSFWorkbook wb){
		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
		if(wb == null){
			wb = new HSSFWorkbook();
		}

		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);

		// 第三步，在sheet中添加表头第0行（可以是0～65535之间的任何一个）,注意老版本poi对Excel的行数列数有限制
		HSSFRow row = sheet.createRow(0);

		// 第四步，创建单元格，并设置值表头 设置表头居中
		sheet.setColumnWidth((short) 0, (short) 4000);// 设置列宽
		sheet.setColumnWidth((short) 1, (short) 4000);
		sheet.setColumnWidth((short) 2, (short) 4000);
		sheet.setColumnWidth((short) 3, (short) 4000);
		sheet.setColumnWidth((short) 4, (short) 4000);
		
		HSSFCellStyle headerStyle = wb.createCellStyle();	// 创建标题样式
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直对齐居中
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		HSSFFont headerFont = wb.createFont();	// 创建标题字体样式
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
		headerFont.setFontName("Calibri");
		headerFont.setFontHeightInPoints((short) 11);
		headerStyle.setFont(headerFont);	// 创建标题字体样式
		headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		
		HSSFCellStyle cellStyle = wb.createCellStyle();// 设置正文样式
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直对齐居中
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		//cellStyle.setWrapText(true); // 设置为自动换行
		HSSFFont cellFont = wb.createFont();
		cellFont.setFontName("Calibri");
		cellFont.setFontHeightInPoints((short) 11);
		cellStyle.setFont(cellFont);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		
		//声明列对象
		HSSFCell cell = null;

		//创建单元格（每列）标题
		for(int i=0;i<title.length;i++){
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(headerStyle);
		}

		//创建内容
		for(int i=0;i<values.length;i++){//有i行
			row = sheet.createRow(i + 1);//从第二行开始创建内容
			for(int j=0;j<values[i].length;j++){
				//将内容按顺序赋给对应的列对象
				row.createCell(j).setCellValue(values[i][j]);
				row.getCell(j).setCellStyle(cellStyle);
			}
		}
		
		//合并单元格
		Region region1 = new Region((short)1,(short)0,(short)values.length,(short)0);	
		sheet.addMergedRegion(region1);
		Region region2 = new Region((short)1,(short)1,(short)values.length,(short)1);
		sheet.addMergedRegion(region2);
		
		return wb;
	}
}
