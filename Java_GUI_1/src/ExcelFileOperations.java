import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.collections4.SetUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileOperations {
	static XSSFWorkbook workbook = new XSSFWorkbook(); // Blank workbook 
	static XSSFSheet sheet = workbook.createSheet("student Details"); // Create a blank sheet 
	static CellStyle cellStyle = workbook.createCellStyle();
	static CellStyle cellStyleTopLineBorder = workbook.createCellStyle();
	static CellStyle cellStyleBottomLineBorder = workbook.createCellStyle();
	static CellStyle cellStyleLRLineBorder = workbook.createCellStyle();

	static CellStyle cellStyleLeftTopCorner = workbook.createCellStyle();
	static CellStyle cellStyleLeftBottomCorner = workbook.createCellStyle();
	static CellStyle cellStyleRightTopCorner = workbook.createCellStyle();
	static CellStyle cellStyleRightBottomCorner = workbook.createCellStyle();
	static Row row;
	static Cell cell;
	static ArrayList rowArrayList=new ArrayList();
	static int invoiceStartColumnPos=0;
	static int invoiceStartRowPos=2;
	static Font textFont = workbook.createFont(); 
	private static void createSquare(int rowNo,int cellNo,int length,int height) {

		cellStyle = workbook.createCellStyle();
		cellStyleTopLineBorder = workbook.createCellStyle();
		cellStyleBottomLineBorder = workbook.createCellStyle();
		cellStyleLRLineBorder = workbook.createCellStyle();
		cellStyleLeftTopCorner = workbook.createCellStyle();
		cellStyleLeftBottomCorner = workbook.createCellStyle();
		cellStyleRightTopCorner = workbook.createCellStyle();
		cellStyleRightBottomCorner = workbook.createCellStyle();
		if((height==1)&&(length>1)) {					// This block of code will create a square with height of 1 cell
			row = (Row) rowArrayList.get(rowNo);		// Access the previously stored rows from rowArrayList
			for(int m=0;m<length;m++) {
				cell=row.createCell(cellNo+m);
				cell=row.createCell(cellNo+m);
				if(m==0) {
					cellStyle.setBorderLeft(BorderStyle.THIN);
					cellStyle.setBorderTop(BorderStyle.THIN);
					cellStyle.setBorderBottom(BorderStyle.THIN);
					cell.setCellStyle(cellStyle);
				}else if(m==length-1) {
					cellStyle = workbook.createCellStyle();
					cellStyle.setBorderRight(BorderStyle.THIN);
					cellStyle.setBorderTop(BorderStyle.THIN);
					cellStyle.setBorderBottom(BorderStyle.THIN);
					cell.setCellStyle(cellStyle);
				}else {
					cellStyle = workbook.createCellStyle();
					cellStyle.setBorderTop(BorderStyle.THIN);
					cellStyle.setBorderBottom(BorderStyle.THIN);
					cell.setCellStyle(cellStyle);					
				}
			}
		}else if((height==1)&&(length==1)) {					// This block of code will create a square of 1x1 cell
			row = (Row) rowArrayList.get(rowNo);		// Access the previously stored rows from rowArrayList
			cell=row.createCell(cellNo);
			cellStyle.setBorderLeft(BorderStyle.THIN);
			cellStyle.setBorderRight(BorderStyle.THIN);
			cellStyle.setBorderTop(BorderStyle.THIN);
			cellStyle.setBorderBottom(BorderStyle.THIN);
			cell.setCellStyle(cellStyle);
		}
		else {					// This block of code will create a square of height more than 1 cell
			/********* Top Line of Square  ****************/
			row = (Row) rowArrayList.get(rowNo);		// Access the previously stored rows from rowArrayList
			for(int i=0;i<length;i++) {
				cell=row.createCell(cellNo+i);
				if(i==0) {					// Left Top Corner of a Square
					cellStyleLeftTopCorner.setBorderLeft(BorderStyle.THIN);
					cellStyleLeftTopCorner.setBorderTop(BorderStyle.THIN);
					cell.setCellStyle(cellStyleLeftTopCorner);
				}else if(i==length-1) {		// Right Top Corner of a Square
					cellStyleRightTopCorner.setBorderTop(BorderStyle.THIN);
					cellStyleRightTopCorner.setBorderRight(BorderStyle.THIN);
					cell.setCellStyle(cellStyleRightTopCorner);
				}
				else {
					cellStyleTopLineBorder.setBorderTop(BorderStyle.THIN);
					cell.setCellStyle(cellStyleTopLineBorder);
				}
			}
			/********* Bottom Line of Square  ****************/
			row = (Row) rowArrayList.get(rowNo+(height-1));		// Access the previously stored rows from rowArrayList
			for(int j=0;j<length;j++) {
				cell=row.createCell(cellNo+j);
				if(j==0) {					// Left Top Corner of a Square
					cellStyleLeftBottomCorner.setBorderLeft(BorderStyle.THIN);
					cellStyleLeftBottomCorner.setBorderBottom(BorderStyle.THIN);
					cell.setCellStyle(cellStyleLeftBottomCorner);
				}else if(j==length-1) {		// Right Top Corner of a Square
					cellStyleRightBottomCorner.setBorderBottom(BorderStyle.THIN);
					cellStyleRightBottomCorner.setBorderRight(BorderStyle.THIN);
					cell.setCellStyle(cellStyleRightBottomCorner);

				}
				else {
					cellStyleBottomLineBorder.setBorderBottom(BorderStyle.THIN);
					cell.setCellStyle(cellStyleBottomLineBorder);
				}
			}
			if(height>2) {			// don't execute below code for a square with height less than 2
				/********* Left and Right Line of Square  ****************/
				row = (Row) rowArrayList.get(rowNo+1);
				for(int k=1;k<height-1;k++) {
					/******* Below Code is to draw Left Border Line ************/
					cellStyleLRLineBorder = workbook.createCellStyle();
					row = (Row) rowArrayList.get(rowNo+k);
					cell=row.createCell(cellNo);
					cellStyleLRLineBorder.setBorderLeft(BorderStyle.THIN);
					cell.setCellStyle(cellStyleLRLineBorder);
					/******* Below Code is to draw Right Border Line ************/
					if(length==1) {									// this will create a square of length 1 and multiple row
						cell=row.createCell(cellNo+(length-1));
						cellStyleLRLineBorder.setBorderRight(BorderStyle.THIN);
						cell.setCellStyle(cellStyleLRLineBorder);
					}else {
						cellStyleLRLineBorder = workbook.createCellStyle();
						cell=row.createCell(cellNo+(length-1));
						cellStyleLRLineBorder.setBorderRight(BorderStyle.THIN);
						cell.setCellStyle(cellStyleLRLineBorder);
					}
				}
				if(length==1) {		              					// this will create a square of length 1 and multiple row
					row = (Row) rowArrayList.get(rowNo);
					cell=row.getCell(cellNo);
					cellStyleLRLineBorder=cell.getCellStyle();
					cellStyleLRLineBorder.setBorderRight(BorderStyle.THIN);
					cell.setCellStyle(cellStyleLRLineBorder);

					row = (Row) rowArrayList.get(rowNo+(height-1));
					cell=row.getCell(cellNo);
					cellStyleLRLineBorder=cell.getCellStyle();
					cellStyleLRLineBorder.setBorderRight(BorderStyle.THIN);
					cell.setCellStyle(cellStyleLRLineBorder);
				}
			}
		}
	}

	private static void writeDataToExcel(int row,int column,String data,int font,int bold) {

		cell=sheet.getRow(row).getCell(column);		// get the desired cell value
		if(cell==null) {							// if cell value is null then create cell else continue with the value
			cell=((Row) rowArrayList.get(row)).createCell(column);
		}
		cellStyle = cell.getCellStyle();			/* get the previously designed cell style which includes cell borders this will keep 
													   cell border while changing one format like font*/
		cell.setCellValue(data);					// write data to excel file
		textFont = workbook.createFont();
		textFont.setFontHeightInPoints((short)font);  
		textFont.setFontName("Times New Roman"); 
		if(bold==1) {
			textFont.setBold(true);
		}else {
			textFont.setBold(false);
		}
		cellStyle.setFont(textFont); 
		cell.setCellStyle(cellStyle);
	}

	public static void generateInvoice() 
	{ 
		for(int i=0;i<=50;i++) {
			rowArrayList.add(sheet.createRow(i));		//create and store rows in ArrayList to avoid problem of not getting two square in one row
		}
		createSquare(invoiceStartRowPos,invoiceStartColumnPos,10,31);		// main outer square
		writeDataToExcel(invoiceStartRowPos,invoiceStartColumnPos,"SAMARTH PRODUCTIONS",11,1);
		writeDataToExcel(invoiceStartRowPos+1,invoiceStartColumnPos,"Sinhagad Road, Pune-411051",10,0);
		writeDataToExcel(invoiceStartRowPos+2,invoiceStartColumnPos,"Contact"+"  "+":1234567890",10,0);
		createSquare(invoiceStartRowPos,invoiceStartColumnPos+6,2,1);
		writeDataToExcel(invoiceStartRowPos,invoiceStartColumnPos+6,"Invoice No.",10,0);
		createSquare(invoiceStartRowPos+1,invoiceStartColumnPos+6,2,2);
		createSquare(invoiceStartRowPos,invoiceStartColumnPos+8,2,1);
		writeDataToExcel(invoiceStartRowPos,invoiceStartColumnPos+8,"Dated",10,0);
		createSquare(invoiceStartRowPos+1,invoiceStartColumnPos+8,2,2);
		createSquare(invoiceStartRowPos+3,invoiceStartColumnPos,6,7);
		writeDataToExcel(invoiceStartRowPos+3,invoiceStartColumnPos,"Buyer",10,1);
		if(Frame1.newCustomerFlag==1) {
			writeDataToExcel(invoiceStartRowPos+4,invoiceStartColumnPos,NumberToWords.capitalizeWord(Frame2.custName),10,1);
			writeDataToExcel(invoiceStartRowPos+5,invoiceStartColumnPos,NumberToWords.capitalizeWord(Frame2.address1),10,0);
			writeDataToExcel(invoiceStartRowPos+6,invoiceStartColumnPos,NumberToWords.capitalizeWord(Frame2.address2),10,0);
			writeDataToExcel(invoiceStartRowPos+7,invoiceStartColumnPos,"State Name"+"   : "+NumberToWords.capitalizeWord(Frame2.stateName),10,0);
			writeDataToExcel(invoiceStartRowPos+8,invoiceStartColumnPos,"Contact"+"  :  "+Frame2.contact,10,0);
			writeDataToExcel(invoiceStartRowPos+9,invoiceStartColumnPos,"PAN"+"    :  "+Frame2.PAN.toUpperCase(),10,0);
		}
		else if(Frame1.oldCustomerFlag==1) {		// if the user is selected old customer then get customer data from resultset 
			String custName = null;
			String address1 = null;
			String address2 = null;
			String stateName = null;
			String contact = null;
			String PAN = null;
			System.out.println("Old Customer");
			try {
				DataBaseOperations.res.first();
				for(int i=0;i<Frame3.clickedRow;i++) {
					DataBaseOperations.res.next();
				}
				custName=DataBaseOperations.res.getString(2);
				address1=DataBaseOperations.res.getString(3);
				address2=DataBaseOperations.res.getString(4);
				stateName=DataBaseOperations.res.getString(5);
				contact=DataBaseOperations.res.getString(6);
				PAN=DataBaseOperations.res.getString(7);
				System.out.println(custName+"  "+address1+"  "+address2+"  "+stateName+"  "+contact+"  "+PAN);

			} catch (SQLException e) {
				e.printStackTrace();
			}
			writeDataToExcel(invoiceStartRowPos+4,invoiceStartColumnPos,NumberToWords.capitalizeWord(custName),10,1);
			writeDataToExcel(invoiceStartRowPos+5,invoiceStartColumnPos,NumberToWords.capitalizeWord(address1),10,0);
			writeDataToExcel(invoiceStartRowPos+6,invoiceStartColumnPos,NumberToWords.capitalizeWord(address2),10,0);
			writeDataToExcel(invoiceStartRowPos+7,invoiceStartColumnPos,"State Name"+"   : "+NumberToWords.capitalizeWord(stateName),10,0);
			writeDataToExcel(invoiceStartRowPos+8,invoiceStartColumnPos,"Contact"+"  :  "+contact,10,0);
			writeDataToExcel(invoiceStartRowPos+9,invoiceStartColumnPos,"PAN"+"    :  "+PAN.toUpperCase(),10,0);
		}
		createSquare(invoiceStartRowPos+3,invoiceStartColumnPos+6,2,2);
		writeDataToExcel(invoiceStartRowPos+3,invoiceStartColumnPos+6,"Supplier's Ref.",10,0);
		createSquare(invoiceStartRowPos+5,invoiceStartColumnPos+6,2,5);
		createSquare(invoiceStartRowPos+3,invoiceStartColumnPos+8,2,2);
		writeDataToExcel(invoiceStartRowPos+3,invoiceStartColumnPos+8,"Other Reference(s)",10,0);
		createSquare(invoiceStartRowPos+5,invoiceStartColumnPos+8,2,5);
		createSquare(invoiceStartRowPos+10,invoiceStartColumnPos,1,1);
		writeDataToExcel(invoiceStartRowPos+10,invoiceStartColumnPos,"Sr No.",10,0);
		createSquare(invoiceStartRowPos+10,invoiceStartColumnPos+1,4,1);
		writeDataToExcel(invoiceStartRowPos+10,invoiceStartColumnPos+1," Description of Goods",10,0);
		createSquare(invoiceStartRowPos+10,invoiceStartColumnPos+5,1,1);
		writeDataToExcel(invoiceStartRowPos+10,invoiceStartColumnPos+5,"Quantity",10,0);
		createSquare(invoiceStartRowPos+10,invoiceStartColumnPos+6,1,1);
		writeDataToExcel(invoiceStartRowPos+10,invoiceStartColumnPos+6,"Rate",10,0);
		createSquare(invoiceStartRowPos+10,invoiceStartColumnPos+7,1,1);
		writeDataToExcel(invoiceStartRowPos+10,invoiceStartColumnPos+7,"Per",10,0);
		createSquare(invoiceStartRowPos+10,invoiceStartColumnPos+8,2,1);
		writeDataToExcel(invoiceStartRowPos+10,invoiceStartColumnPos+8,"Amount",10,0);

		createSquare(invoiceStartRowPos+11,invoiceStartColumnPos,1,9);
		createSquare(invoiceStartRowPos+11,invoiceStartColumnPos+5,1,9);
		createSquare(invoiceStartRowPos+11,invoiceStartColumnPos+6,1,9);
		createSquare(invoiceStartRowPos+11,invoiceStartColumnPos+7,1,9);
		createSquare(invoiceStartRowPos+11,invoiceStartColumnPos+8,2,9);
		createSquare(invoiceStartRowPos+20,invoiceStartColumnPos,1,1);
		createSquare(invoiceStartRowPos+20,invoiceStartColumnPos+1,4,1);
		writeDataToExcel(invoiceStartRowPos+20,invoiceStartColumnPos+1," TOTAL",10,1);
		createSquare(invoiceStartRowPos+20,invoiceStartColumnPos+5,1,1);
		createSquare(invoiceStartRowPos+20,invoiceStartColumnPos+6,1,1);
		createSquare(invoiceStartRowPos+20,invoiceStartColumnPos+7,1,1);
		createSquare(invoiceStartRowPos+20,invoiceStartColumnPos+8,2,1);
		createSquare(invoiceStartRowPos+21,invoiceStartColumnPos,10,2);
		writeDataToExcel(invoiceStartRowPos+21,invoiceStartColumnPos," Amount Chargeable(in words)",10,0);
		writeDataToExcel(invoiceStartRowPos+21,invoiceStartColumnPos+9," E.&O.E ",10,1);
		createSquare(invoiceStartRowPos+23,invoiceStartColumnPos,10,8);
		writeDataToExcel(invoiceStartRowPos+23,invoiceStartColumnPos," Declaration: ",10,0);
		writeDataToExcel(invoiceStartRowPos+24,invoiceStartColumnPos," We declare that this invoice shows the actual price of the ",10,1);
		writeDataToExcel(invoiceStartRowPos+25,invoiceStartColumnPos," Goods described and that all particulars are true and correct ",10,1);
		writeDataToExcel(invoiceStartRowPos+26,invoiceStartColumnPos," Company Bank Details:- ",10,0);
		writeDataToExcel(invoiceStartRowPos+27,invoiceStartColumnPos," Bank Name- XYZ Bank LTD.,Pune ",10,0);
		writeDataToExcel(invoiceStartRowPos+28,invoiceStartColumnPos," A/C No.= 123 ",10,0);
		writeDataToExcel(invoiceStartRowPos+29,invoiceStartColumnPos," IFSC Code & Branch= IFSC000 ",10,0);
		createSquare(invoiceStartRowPos+25,invoiceStartColumnPos+6,4,5);
		writeDataToExcel(invoiceStartRowPos+25,invoiceStartColumnPos+7," For Samarth Production ",10,1);
		writeDataToExcel(invoiceStartRowPos+29,invoiceStartColumnPos+7," Authorized Signature ",10,0);
		writeDataToExcel(invoiceStartRowPos-2,invoiceStartColumnPos+4," INVOICE ",11,1);
		writeDataToExcel(invoiceStartRowPos+31,invoiceStartColumnPos+3," This is Computer Generated Invoice ",10,0);

		if((Frame4.MRP30CheckboxFlag==1)&&(Frame4.MRP60CheckboxFlag==0)) {
			writeDataToExcel(invoiceStartRowPos+11,invoiceStartColumnPos," 1.",10,0);
			writeDataToExcel(invoiceStartRowPos+11,invoiceStartColumnPos+1," M.R.P. 30",10,0);
			writeDataToExcel(invoiceStartRowPos+11,invoiceStartColumnPos+5,String.format("%.2f", (double)Frame4.MRP30Quanty),10,0);
			writeDataToExcel(invoiceStartRowPos+11,invoiceStartColumnPos+6,String.format("%.2f", (double)Frame4.MRP30Rate),10,0);
			writeDataToExcel(invoiceStartRowPos+11,invoiceStartColumnPos+7,"Dz",10,0);
			writeDataToExcel(invoiceStartRowPos+11,invoiceStartColumnPos+8,String.format("%.2f",((double)Frame4.MRP30Rate)*((double)Frame4.MRP30Quanty)),10,0);
		}else if((Frame4.MRP30CheckboxFlag==0)&&(Frame4.MRP60CheckboxFlag==1)) {
			writeDataToExcel(invoiceStartRowPos+11,invoiceStartColumnPos," 1.",10,0);
			writeDataToExcel(invoiceStartRowPos+11,invoiceStartColumnPos+1," M.R.P. 60",10,0);
			writeDataToExcel(invoiceStartRowPos+11,invoiceStartColumnPos+5,String.format("%.2f", (double)Frame4.MRP60Quanty),10,0);
			writeDataToExcel(invoiceStartRowPos+11,invoiceStartColumnPos+6,String.format("%.2f", (double)Frame4.MRP60Rate),10,0);
			writeDataToExcel(invoiceStartRowPos+11,invoiceStartColumnPos+7,"Dz",10,0);
			writeDataToExcel(invoiceStartRowPos+11,invoiceStartColumnPos+8,String.format("%.2f",((double)Frame4.MRP60Rate)*((double)Frame4.MRP60Quanty)),10,0);
		}else if((Frame4.MRP30CheckboxFlag==1)&&(Frame4.MRP60CheckboxFlag==1)) {
			writeDataToExcel(invoiceStartRowPos+11,invoiceStartColumnPos," 1.",10,0);
			writeDataToExcel(invoiceStartRowPos+12,invoiceStartColumnPos," 2.",10,0);
			writeDataToExcel(invoiceStartRowPos+11,invoiceStartColumnPos+1," M.R.P. 30",10,0);
			writeDataToExcel(invoiceStartRowPos+12,invoiceStartColumnPos+1," M.R.P. 60",10,0);
			writeDataToExcel(invoiceStartRowPos+11,invoiceStartColumnPos+5,String.format("%.2f", (double)Frame4.MRP30Quanty),10,0);
			writeDataToExcel(invoiceStartRowPos+12,invoiceStartColumnPos+5,String.format("%.2f", (double)Frame4.MRP60Quanty),10,0);
			writeDataToExcel(invoiceStartRowPos+11,invoiceStartColumnPos+6,String.format("%.2f", (double)Frame4.MRP30Rate),10,0);
			writeDataToExcel(invoiceStartRowPos+12,invoiceStartColumnPos+6,String.format("%.2f", (double)Frame4.MRP60Rate),10,0);
			writeDataToExcel(invoiceStartRowPos+11,invoiceStartColumnPos+7,"Dz",10,0);
			writeDataToExcel(invoiceStartRowPos+12,invoiceStartColumnPos+7,"Dz",10,0);
			writeDataToExcel(invoiceStartRowPos+11,invoiceStartColumnPos+8,String.format("%.2f",((double)Frame4.MRP30Rate)*((double)Frame4.MRP30Quanty)),10,0);
			writeDataToExcel(invoiceStartRowPos+12,invoiceStartColumnPos+8,String.format("%.2f",((double)Frame4.MRP60Rate)*((double)Frame4.MRP60Quanty)),10,0);
		}
		writeDataToExcel(invoiceStartRowPos+20,invoiceStartColumnPos+8,String.format("%.2f", (double)Frame4.total),10,1);
		writeDataToExcel(invoiceStartRowPos+22,invoiceStartColumnPos,"INR "+NumberToWords.convert(Frame4.total)+" Only",10,1);

		Date date = new Date();  
		SimpleDateFormat  formatter = new SimpleDateFormat("dd MMMM yyyy");  
		String  strDate = formatter.format(date);  
		writeDataToExcel(invoiceStartRowPos+1,invoiceStartColumnPos+8,strDate,10,1);
		
		
		
		
		
		
		
		
		//		   System.out.println(dtf.format(now));  
		//		createSquare(1,2,2,1);								
		//		Cell cell = sheet.getRow(1).getCell(2);			// This will not disturb or destroy cell properties like cell border etc but we have to create a square first.
		//		cell.setCellValue("Hello");
		//		sheet.autoSizeColumn(7);
		//		sheet.addMergedRegion(new CellRangeAddress(1,1,7,8));
		try { 
			FileOutputStream out = new FileOutputStream(new File("ExcelFilebyMe.xlsx")); // this Writes the workbook ExcelFilebyMe 
			workbook.write(out); 
			out.close(); 
			workbook.close();
			System.out.println("ExcelFilebyMe.xlsx written successfully on disk."); 
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	} 
}

