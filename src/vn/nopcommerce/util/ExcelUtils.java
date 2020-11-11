package vn.nopcommerce.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import vn.nopcommerce.setting.Setting;
import vn.nopcommerce.util.CommonUtils;
import vn.nopcommerce.util.ExcelUtils;

public class ExcelUtils {
	/* CONSTANT: BLANK */
	private static final String BLANK = "";
	
	/* CONSTANT: FORMAT_DATE_DDMMYYYY */
	private static final String FORMAT_DATE_DDMMYYYY = "dd/MM/yyyy";
	
	/* FLAG: Convert idxSheet to sheetName */
	private static boolean isConvertToSheetNameFlg = false;
	
	/**
	 * <p><b>Main method (Using to test)</b></p>
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String relativePath = "TestData/InputData/Data_Add_Cusstomer.xlsx";
		String sheetName = "Sheet1";
		String cell = "A1";
		ExcelUtils.readData(relativePath, sheetName, cell);
	}

	/**
	 * <p><b>Read data in excel file</b></p>
	 * 
	 * @param relativePath
	 * @param idxSheet
	 * @param cellAddress
	 * @return stringValue
	 * @throws Exception
	 */
	public static String readData(String relativePath, int idxSheet, String cellAddress) throws Exception {
		
		CellReference cellReference = null;

		try {
			cellReference = new CellReference(cellAddress);
			isConvertToSheetNameFlg = true;

		} catch (IllegalArgumentException iae) {
			// Write log
			if (Setting.isWriteLogConsole()) {
				System.out.println("ERROR!!!");
				System.out.println("Message: Cell Address = \"" + cellAddress + "\" is invalid");
			}
			throw iae;
		}

		return readDataCommon(relativePath, String.valueOf(idxSheet), cellReference.getCol(), cellReference.getRow());
	}

	/**
	 * <p><b>Read data in excel file</b></p>
	 * 
	 * @param relativePath
	 * @param sheetName
	 * @param cellAddress
	 * @return stringValue
	 * @throws Exception
	 */
	public static String readData(String relativePath, String sheetName, String cellAddress) throws Exception {
		
		CellReference cellReference = null;

		try {
			cellReference = new CellReference(cellAddress);

		} catch (IllegalArgumentException iae) {
			// Write log
			if (Setting.isWriteLogConsole()) {
				System.out.println("ERROR!!!");
				System.out.println("Message: Cell Address = \"" + cellAddress + "\" is invalid");
			}
			throw iae;
		}
		
		return readDataCommon(relativePath, sheetName, cellReference.getCol(), cellReference.getRow());
	}

	/**
	 * <p><b>Read data in excel file</b></p>
	 * 
	 * @param relativePath
	 * @param sheetName
	 * @param idxColumn
	 * @param idxRow
	 * @return stringValue
	 * @throws Exception
	 */
	public static String readData(String relativePath, String sheetName, int idxColumn, int idxRow) throws Exception {

		return readDataCommon(relativePath, sheetName, idxColumn, idxRow);
	}

	/**
	 * <p><b>Read data in excel file</b></p>
	 * 
	 * @param relativePath
	 * @param idxSheet
	 * @param idxColumn
	 * @param idxRow
	 * @return stringValue
	 * @throws Exception
	 */
	public static String readData(String relativePath, int idxSheet, int idxColumn, int idxRow) throws Exception {

		isConvertToSheetNameFlg = true;

		return readDataCommon(relativePath, String.valueOf(idxSheet), idxColumn, idxRow);
	}

	/**
	 * <p><b>Read data in excel file (Common)</b></p>
	 * 
	 * @param relativePath
	 * @param sheetName
	 * @param idxColumn
	 * @param idxRow
	 * @return stringValue
	 * @throws Exception
	 */
	private static String readDataCommon(String relativePath, String sheetIdentify, int idxColumn, int idxRow) throws Exception {
	
		Workbook wb = null;
		Sheet sheet = null;
		String sheetName = null;
		Row row = null;
		Cell cell = null;
		String data = null;

		try {
			wb = readExcelFile(relativePath);

			// Check sheetIdentify (if sheetIdentify = idxSheet then convert to sheetName)
			if (isConvertToSheetNameFlg) {
				sheetName = wb.getSheetName(Integer.parseInt(sheetIdentify));
				isConvertToSheetNameFlg = false;
			} else {
				sheetName = sheetIdentify;
			}

			// Write log
			if (Setting.isWriteLogConsole()) {
				System.out.println("File = \"" + relativePath + "\", SheetName = \"" + sheetName + "\"");
			}

			sheet = wb.getSheet(sheetName);
			if (sheet == null) {
		    	throw new Exception("Not found sheet \"" + sheetName + "\" in file \"" + relativePath + "\"");
			}

			row = sheet.getRow(idxRow);
			if (row == null) {
				// Write log
				if (Setting.isWriteLogConsole()) {
					System.out.println("No data in cell: [column = "+ idxColumn +", row = " + idxRow + "]");
					System.out.println("By default, the value of cell will be: BLANK");
				}
				return BLANK;
			}

			cell = row.getCell(idxColumn);
			if (cell == null) {
				// Write log
				if (Setting.isWriteLogConsole()) {
					System.out.println("No data in cell: [column = "+ idxColumn +", row = " + idxRow + "]");
					System.out.println("By default, the value of cell will be: BLANK");
				}
				return BLANK;
			}
			
			FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
			CellValue cellValue = evaluator.evaluate(cell);

			switch (cellValue.getCellType()) {
				case BOOLEAN:
					data = Boolean.toString(cellValue.getBooleanValue());
					
					// Write log
					if (Setting.isWriteLogConsole()) {
						System.out.println("Detecting the data type as a boolean in cell: [column = "
									+ idxColumn +", row = " + idxRow + "]. Data = \"" + data + "\"");
					}
					return data;

			    case NUMERIC:
			    	if (DateUtil.isCellDateFormatted(cell)) {
			    		Format f = new SimpleDateFormat(FORMAT_DATE_DDMMYYYY);
			    		data = f.format(cell.getDateCellValue()).toString();

						// Write log
						if (Setting.isWriteLogConsole()) {
							System.out.println("Detecting the data type as a date in cell: [column = "+ idxColumn +", row = " + idxRow + "]");
				    		System.out.println("By default, the value of the cell will be converted to format: \"dd/MM/yyyy\"");
				    		System.out.println("Data before convert: \"" + cell.getDateCellValue() + "\"");
				    		System.out.println("Data after convert: \"" + data + "\"");
						}

			    	} else {
						data = Double.toString(cellValue.getNumberValue());

						// Write log
						if (Setting.isWriteLogConsole()) {
							System.out.println("Detecting the data type as a numeric in cell: [column = "
									+ idxColumn +", row = " + idxRow + "]. Data = \"" + data + "\"");
						}
			    	}
			    	return data;

			    case STRING:
					data = cellValue.getStringValue();

					// Write log
					if (Setting.isWriteLogConsole()) {
						System.out.println("Detecting the data type as a string in cell: [column = "
								+ idxColumn +", row = " + idxRow + "]. Data = \"" + data + "\"");
					}
					return data;

			    case BLANK:
					// Write log
					if (Setting.isWriteLogConsole()) {
						System.out.println("No data in cell: column = ["+ idxColumn +", row = " + idxRow + "]");
						System.out.println("By default, the value of cell will be: BLANK");
					}
			    	return BLANK;

			    case ERROR:
			    default:
			    	throw new Exception("Excel data is corrupted. Please check data in excel file!");
			}

		} catch (IOException ioe) {
			// Write log
			if (Setting.isWriteLogConsole()) {
				System.out.println("ERROR!!!");
				System.out.println("Message: File not found in path \"" + CommonUtils.getFullPath(relativePath) + "\"");
			}
			throw ioe;

		} catch (IllegalArgumentException iae) {
			// Reset isConvertToSheetNameFlg
			isConvertToSheetNameFlg = false;

			// Write log
			if (Setting.isWriteLogConsole()) {
				System.out.println("ERROR!!!");
				System.out.println("Message: Sheet index = " + sheetIdentify + " in file \"" + relativePath + "\" is out of range");
			}
			throw iae;

		} catch(Exception e) {
			// Write log
			if (Setting.isWriteLogConsole()) {
				System.out.println("ERROR!!!");
				if (e.getMessage() != null) {
					System.out.println("Message: " + e.getMessage());
				}
			}
			throw e;
		} finally {
			wb.close();
		}
	}
	
	/**
	 * <p><b>Read excel file</b></p>
	 * Convert excel file to object in Java<br>
	 * <br>
	 * @param relativePath
	 * @return Workbook
	 */
	public static Workbook readExcelFile(String relativePath) throws Exception {

		FileInputStream fis = null;
		Workbook wb = null;
		String fullPath = null;

		try {
			// Check exists file name in relativePath
			if(!CommonUtils.isExistsFileName(relativePath)) {
				throw new Exception("Path \"" + relativePath + "\" is not contain file name");
			}
			
			fullPath = new StringBuilder(Setting.TEST_DATA_PATH).append(relativePath).toString();
			fis = new FileInputStream(fullPath);

			switch (CommonUtils.getExtensionName(relativePath).toLowerCase()) {
				case "xlsx":
					wb = new XSSFWorkbook(fis);
					break;
				case "xls":
					wb = new HSSFWorkbook(fis);
					break;
				default:
					throw new Exception(CommonUtils.getFileName(relativePath) + " is not an excel file.");
			}
		} catch (IOException ioe) {
			throw ioe;
		} catch(Exception e) {
			throw e;
		} finally {
			if (fis != null) {
				fis.close();
			}
		}

		return wb;		
	}
	
	/**
	 * <p><b>Write data in excel file</b></p>
	 * 
	 * @param relativePath
	 * @param idxSheet
	 * @param cellAddress
	 * @return stringValue
	 * @throws Exception
	 */
	
	public static String writeData(String relativePath, int idxSheet, String cellAddress) throws Exception {
		
		CellReference cellReference = null;
		try {
			cellReference = new CellReference(cellAddress);
			isConvertToSheetNameFlg = true;

		} catch (IllegalArgumentException iae) {
			// Write log
			if (Setting.isWriteLogConsole()) {
				System.out.println("ERROR!!!");
				System.out.println("Message: Cell Address = \"" + cellAddress + "\" is invalid");
			}
			throw iae;
		}
		return writeDataCommon(relativePath, String.valueOf(idxSheet), cellReference.getCol(), cellReference.getRow());
	}
	
	/**
	 * <p><b>Write data in excel file (Common)</b></p>
	 * 
	 * @param relativePath
	 * @param sheetName
	 * @param idxColumn
	 * @param idxRow
	 * @return stringValue
	 * @throws Exception
	 */
	private static String writeDataCommon(String relativePath, String sheetIdentify, int idxColumn, int idxRow) {
		
		Workbook wb = null;
		Sheet sheet = null;
		String sheetName = null;
		Row row = null;
		Cell cell = null;
		String data = null;
		try {
			wb=readExcelFile(relativePath);
			
			// Check sheetIdentify (if sheetIdentify = idxSheet then convert to sheetName)
			if (isConvertToSheetNameFlg) {
				sheetName = wb.getSheetName(Integer.parseInt(sheetIdentify));
				isConvertToSheetNameFlg = false;
			} else {
				sheetName = sheetIdentify;
			}
			// Write log
			if (Setting.isWriteLogConsole()) {
				System.out.println("File = \"" + relativePath + "\", SheetName = \"" + sheetName + "\"");
			}

			sheet = wb.getSheet(sheetName);
			if (sheet == null) {
				throw new Exception("Not found sheet \"" + sheetName + "\" in file \"" + relativePath + "\"");
			}
			
			FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
			CellValue cellValue = evaluator.evaluate(cell);
			
			
			

			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
}
