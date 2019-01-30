package ExcelToSql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelToSql {
	private static Logger log=Logger.getLogger(ExcelToSql.class);
	/**
	 * 从excel获取数据到List�?
	 * @param filePath  excel文件位置
	 * @return
	 */
	public static List<Product> getProducts(FileInputStream fis){
		List<Product> products=new ArrayList<Product>();
		Product product=null;
		try {
			HSSFWorkbook hssfworkbook=new HSSFWorkbook(fis);
			Workbook wb=null;
			XSSFWorkbook xssfworkbook=new XSSFWorkbook(fis);
			
			//循环工作表Sheet
			for(int numSheet=0;numSheet<hssfworkbook.getNumberOfSheets();numSheet++){
				HSSFSheet hssfSheet= hssfworkbook.getSheetAt(numSheet);
				//如表无内容则不查询此�?
				if(hssfSheet==null){
					continue;
				}
				//循环行Row
				for(int rowNum=2;rowNum<=hssfSheet.getLastRowNum();rowNum++){	//屏蔽�?2行：rowNum=2
					product=new Product();
					HSSFRow hssfRow=hssfSheet.getRow(rowNum);
					//如表行无内容则不查询此行
					if(hssfRow==null){
						continue;
					}
					//循环列cell
					for(int cellNum=0;cellNum<hssfRow.getLastCellNum();cellNum++){
						HSSFCell cell=hssfRow.getCell(cellNum);
						switch(cellNum){
						case 0:
							product.setPwd(ExcelToSql.getValue(cell).equals("0")?null:ExcelToSql.getValue(cell));
							break;
						case 1:
							product.setPwdQuantity(Integer.parseInt(ExcelToSql.getValue(cell)));
							break;
						case 2:
							product.setBox(ExcelToSql.getValue(cell).equals("0")?null:ExcelToSql.getValue(cell));
							break;
						case 3:
							product.setBoxQuantity(Integer.parseInt(ExcelToSql.getValue(cell)));
							break;
						case 4:
							product.setGasket(ExcelToSql.getValue(cell).equals("0")?null:ExcelToSql.getValue(cell));
							break;
						case 5:
							product.setGasketQuantity(Integer.parseInt(ExcelToSql.getValue(cell)));
							break;
						case 6:
							product.setSpongiaOne(ExcelToSql.getValue(cell).equals("0")?null:ExcelToSql.getValue(cell));
							break;
						case 7:
							product.setSpongiaOneQuantity(Integer.parseInt(ExcelToSql.getValue(cell)));
							break;
						case 8:
							product.setSpongiaTwo(ExcelToSql.getValue(cell).equals("0")?null:ExcelToSql.getValue(cell));
							break;
						case 9:
							product.setSpongiaTwoQuantity(Integer.parseInt(ExcelToSql.getValue(cell)));
							break;
						case 10:
							product.setEsdBag(ExcelToSql.getValue(cell).equals("0")?null:ExcelToSql.getValue(cell));
							break;
						case 11:
							product.setEsdBagQuantity(Integer.parseInt(ExcelToSql.getValue(cell)));
							break;
						case 12:
							product.setGeDang(ExcelToSql.getValue(cell).equals("0")?null:ExcelToSql.getValue(cell));
							break;
						case 13:
							product.setGeDangQuantity(Integer.parseInt(ExcelToSql.getValue(cell)));
							break;
						case 14:
							product.setEsdTable(ExcelToSql.getValue(cell).equals("0")?null:ExcelToSql.getValue(cell));
							break;
						case 15:
							product.setEsdTableQuantity(Integer.parseInt(ExcelToSql.getValue(cell)));
							break;
						case 16:
							product.setEsdBubbleBag(ExcelToSql.getValue(cell).equals("0")?null:ExcelToSql.getValue(cell));
							break;
						case 17:
							product.setEsdBubbleBagQuantity(Integer.parseInt(ExcelToSql.getValue(cell)));
							break;
						case 18:
							product.setRemark(ExcelToSql.getValue(cell).equals("0")?null:ExcelToSql.getValue(cell));
							break;
						case 19:
							product.setProductCategoryId(Integer.parseInt(ExcelToSql.getValue(cell)));
							break;
						}
					}
					products.add(product);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	//excel内容类型转换 
	//CELL_TYPE_NUMERIC 数�?�型 0
	//CELL_TYPE_STRING 字符串型 1
	//CELL_TYPE_FORMULA 公式�? 2
	//CELL_TYPE_BLANK 空�?? 3
	//CELL_TYPE_BOOLEAN 布尔�? 4
	//CELL_TYPE_ERROR 错误 5
	private static String getValue(HSSFCell cell){
		String cellValue=null;
		if(cell.getCellType()==0){
			cellValue=String.valueOf((int)cell.getNumericCellValue());
		}else if(cell.getCellType()==1){
			cellValue=cell.getStringCellValue();
		}else if(cell.getCellType()==2){
			cellValue=cell.getNumericCellValue()+"";
		}else if(cell.getCellType()==3){
			cellValue="0";
		}else if(cell.getCellType()==4){
			cellValue=String.valueOf(cell.getBooleanCellValue());
		}else if(cell.getCellType()==5){
			cellValue=String.valueOf(cell.getErrorCellValue());
		}
		return cellValue;
	}
}
