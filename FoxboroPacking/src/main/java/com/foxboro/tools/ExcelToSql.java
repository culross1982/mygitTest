package com.foxboro.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.foxboro.entity.MaterialsOrder;
import com.foxboro.entity.Product;
import com.foxboro.entity.ProductCategory;


public class ExcelToSql {
	private static Logger log=Logger.getLogger(ExcelToSql.class);
	
	/**
	 * 从excel获取数据到List<Product>中（包材基础数据批量导入）
	 * @param filePath  excel文件位置
	 * @return	List<Product>
	 */
	public static List<Product> getProducts(MultipartFile excel,List<ProductCategory> productCategory){
		List<Product> products=new ArrayList<Product>();
		Product product=null;
		Workbook hssfworkbook=null;

		try {
			String url=excel.getOriginalFilename();
			String suffix=url.substring(url.lastIndexOf("."));//获取文件后缀
			//FileInputStream fis=(FileInputStream) excel.getInputStream();//转化为输入流
			InputStream fis=excel.getInputStream();//转化为输入流
			if(suffix.equals(".xls")){
				hssfworkbook=new HSSFWorkbook(fis);//旧版本的excel使用HSSFWorkbook
			}else if(suffix.equals(".xlsx")){
				hssfworkbook=new XSSFWorkbook(fis);//新版本的excel使用XSSFWorkbook
			}
			//循环工作表Sheet
			for(int numSheet=0;numSheet<hssfworkbook.getNumberOfSheets();numSheet++){
				Sheet sheet= hssfworkbook.getSheetAt(numSheet);
				//如表无内容则不查询此表
				if(sheet==null){
					continue;
				}
				//循环行Row
				for(int rowNum=2;rowNum<=sheet.getLastRowNum();rowNum++){	//屏蔽前2行：rowNum=2
					product=new Product();
					Row row=sheet.getRow(rowNum);
					//如表行无内容则不查询此行
					if(row==null){
						continue;
					}
					//循环列cell
					for(int cellNum=0;cellNum<row.getLastCellNum();cellNum++){
						Cell cell=row.getCell(cellNum);
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
							int productCategoryId=0;
							//把excel类别的String转换为数据库中对应的int类型 
							for(int i=0;i<productCategory.size();i++){
								if(ExcelToSql.getValue(cell).equals(productCategory.get(i).getProductCategoryName())){
									productCategoryId=productCategory.get(i).getId();
								}
							}
							product.setProductCategoryId(productCategoryId);
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
		}catch(Exception e){
			e.printStackTrace();
		}
		return products;
	}
	
	/**
	 * 从excel获取数据到List<MaterialsOrder>中（包材订购数据批量导入）
	 * @param filePath  excel文件位置
	 * @return
	 */
	public static List<MaterialsOrder> getMaterialsOrder(MultipartFile excel){
		List<MaterialsOrder> materialsOrders=new ArrayList<MaterialsOrder>();
		MaterialsOrder materialsOrder=null;
		Workbook hssfworkbook=null;

		try {
			String url=excel.getOriginalFilename();
			String suffix=url.substring(url.lastIndexOf("."));//获取文件后缀
			InputStream fis=excel.getInputStream();//转化为输入流
			if(suffix.equals(".xls")){
				hssfworkbook=new HSSFWorkbook(fis);//旧版本的excel使用HSSFWorkbook
			}else if(suffix.equals(".xlsx")){
				hssfworkbook=new XSSFWorkbook(fis);//新版本的excel使用XSSFWorkbook
			}
			//循环工作表Sheet
			for(int numSheet=0;numSheet<hssfworkbook.getNumberOfSheets();numSheet++){
				Sheet sheet= hssfworkbook.getSheetAt(numSheet);
				//如表无内容则不查询此表
				if(sheet==null){
					continue;
				}
				//循环行Row
				for(int rowNum=1;rowNum<=sheet.getLastRowNum();rowNum++){	//屏蔽前1行：rowNum=1
					materialsOrder=new MaterialsOrder();
					Row row=sheet.getRow(rowNum);
					//如表行无内容则不查询此行
					if(row==null){
						continue;
					}
					//循环列cell
					for(int cellNum=0;cellNum<row.getLastCellNum();cellNum++){
						Cell cell=row.getCell(cellNum);
						switch(cellNum){
						case 0:
							materialsOrder.setPwd(ExcelToSql.getValue(cell).equals("0")?null:ExcelToSql.getValue(cell));
							break;
						case 1:
							materialsOrder.setPwdQuantity(Integer.parseInt(ExcelToSql.getValue(cell)));
							break;
						case 2:
							materialsOrder.setMargin(Integer.parseInt(ExcelToSql.getValue(cell)));
							break;
						}
					}
					materialsOrders.add(materialsOrder);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return materialsOrders;
	}

	//excel内容类型转换 
	//CELL_TYPE_NUMERIC 数值型 0
	//CELL_TYPE_STRING 字符串型 1
	//CELL_TYPE_FORMULA 公式型 2
	//CELL_TYPE_BLANK 空值 3
	//CELL_TYPE_BOOLEAN 布尔型 4
	//CELL_TYPE_ERROR 错误 5
	private static String getValue(Cell cell){
		String cellValue;
		cellValue = null;
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
