package com.humuson.support;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	private String path = "testExcel.xlsx";
	private String backpath = "testExcel_back.xlsx";
//	private String path = "";

	// public static Workbook xlsWb;
	public static Workbook xlsxWb;

	public static Sheet sheet;
	public static int cellcnt = 0;

	public static ArrayList<CellStyle> cellStyleList;
	public static ArrayList<CellStyle> headerStyleList;
	public static CellStyle cellStyle;
	public static CellStyle headerStyle;
	
	public Excel() {
//		path = currentTime()+".xlsx";
		// Workbook 생성
		// xlsWb = new HSSFWorkbook(); // Excel 2007 이전 버전
		xlsxWb = new XSSFWorkbook(); // Excel 2007 이상

		// Sheet 생성
		sheet = xlsxWb.createSheet("firstSheet");

		// 컬럼 너비 설정
		sheet.setColumnWidth(0, wsize(22));
		sheet.setColumnWidth(1, wsize(22));
		sheet.setColumnWidth(2, wsize(22));
		sheet.setColumnWidth(3, wsize(50));
		sheet.setColumnWidth(4, wsize(5));
		sheet.setColumnWidth(5, wsize(50));

		setHeaderCellStyle();

		addHeader();
		createFile();
	}

	private void createFile() {
		// excel 파일 저장
		try {
//			File xlsxFile = new File(path);
			File xlsxFile = new File(path);
			FileOutputStream fileOut = new FileOutputStream(xlsxFile);
			xlsxWb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {	e.printStackTrace();	}
		  catch (IOException e) {	e.printStackTrace();	}
	}

	private int wsize(int num) {
		return num * 256;
	}

	private void setCellStyle(Workbook wb) {
		cellStyle = wb.createCellStyle();
		// 줄 바꿈
		cellStyle.setWrapText(true);
		
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		
		cellStyle.setFont(fontStyle());
		cellStyle.setBorderTop(BorderStyle.THIN); // ==> 테두리 설정
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	}

	private void setHeaderCellStyle() {
		headerStyle = xlsxWb.createCellStyle();

		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER); // ==> 정렬
		headerStyle.setAlignment(HorizontalAlignment.CENTER);

		headerStyle.setFont(fontStyle(IndexedColors.WHITE.getIndex())); // ==> 폰트
		headerStyle.setBorderTop(BorderStyle.THIN); // ==> 테두리 설정
		headerStyle.setBorderLeft(BorderStyle.THIN);
		headerStyle.setBorderRight(BorderStyle.THIN);
		headerStyle.setBorderBottom(BorderStyle.THIN);
		headerStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	}

	private Font fontStyle() {
		Font font = xlsxWb.createFont();
		
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setFontName("나눔고딕코딩");

		return font;
	}
	
	private Font fontStyle(Short color) {
		Font font = xlsxWb.createFont();
		
		font.setColor(color);
		font.setFontName("나눔고딕코딩");

		return font;
	}

	private static void addHeader() {
		Row row = sheet.createRow(0);
		Cell cell;

		String[] heads = { "대분류", "중분류", "소분류", "시나리오 명", "결과", "실패 내역" };
		for (int i = 0; i < heads.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(heads[i]);
			cell.setCellStyle(headerStyle);
		}

	}

	public static int getCellcnt() {
		return cellcnt;
	}

	public static void setCellcnt(int cellcnt) {
		Excel.cellcnt = cellcnt;
	}

	public void modify(ArrayList<String[]> xlsxContent) {
//		CellStyle cs = cellStyle;
		// TODO 발송 결과에 따라 셀 스타일 다르게 설정할 예정
		
		System.out.println("** 엑셀 파일 수정함");
		try {
			XSSFWorkbook wb = new XSSFWorkbook(new File(path));
			Sheet sheet = wb.getSheetAt(0);
			setCellStyle(wb);	// 이 부분에서 이 workbook에 cellstyle 지정하므로 유의

			for (int i = 0; i < xlsxContent.size(); i++) {
				Row row = sheet.createRow(sheet.getLastRowNum()+1);
				for (int j = 0; j < xlsxContent.get(i).length; j++) {
					Cell cell = row.createCell(j);
					cell.setCellValue(xlsxContent.get(i)[j]);
					cell.setCellStyle(cellStyle);
				}
			}
		
			// important to close InputStream
			// Open FileOutputStream to write updates
			FileOutputStream output_file = new FileOutputStream(new File(backpath));
			
			// write changes
			wb.write(output_file);
			// close the stream
			output_file.close();
			wb.close(); 
			
			Files.copy(Paths.get(backpath), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
		} catch(FileNotFoundException e) { 
			System.out.println("!!!!!!!! 엑셀 파일이 열려있어 수정할 수 없습니다. !!!!!!!!");
		} catch (EncryptedDocumentException | InvalidFormatException | IOException | NullPointerException e) {
			e.printStackTrace();
		}
	}
}

