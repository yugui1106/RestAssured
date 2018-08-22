package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by chengzw on 2018/6/8.
 */
public class ExcelUtil{
    private List<List<String>> readXls(String filePath) throws Exception{
        InputStream is = new FileInputStream(filePath);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<List<String>> result = new ArrayList<List<String>>();
        int size = hssfWorkbook.getNumberOfSheets();
        for(int numSheet = 0;numSheet<size;numSheet++){
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if(hssfSheet==null){
                continue;
            }
            for(int rowNum=1;rowNum<= hssfSheet.getLastRowNum();rowNum++){
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                int minColIx = hssfRow.getFirstCellNum();
                int maxColIx = hssfRow.getLastCellNum();
                List<String> rowList = new ArrayList<String>();
                for(int colIx = minColIx;colIx<maxColIx;colIx++){
                    HSSFCell cell = hssfRow.getCell(colIx);
                    if(cell==null){
                        continue;
                    }
                    rowList.add(cell.toString());
                }
                result.add(rowList);
            }
        }
        return result;
    }

    public List<List<String>> readXlsx(String filePath) throws Exception{
        InputStream is = new FileInputStream(filePath);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<List<String>> result = new ArrayList<List<String>>();
        for(Sheet sheet : xssfWorkbook){
            if(sheet==null){
                continue;
            }
            for(int rowNum=1;rowNum<=sheet.getLastRowNum();rowNum++){
                Row xssfRow = sheet.getRow(rowNum);
                int minColIx = xssfRow.getFirstCellNum();
                int maxColIx = xssfRow.getLastCellNum();
                List<String> rowList = new ArrayList<String>();
                for(int colIx=minColIx;colIx<maxColIx;colIx++){
                    Cell cell = xssfRow.getCell(colIx);
                    if(cell==null){
                        continue;
                    }
                    rowList.add(cell.toString());
                }
                result.add(rowList);
            }
        }
        return result;
    }
//    public static String getStringVal(HSSFCell cell){
//        switch (cell.getCellType()) {
//            case Cell.CELL_TYPE_BOOLEAN:
//                return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
//            case Cell.CELL_TYPE_FORMULA:
//                return cell.getCellFormula();
//            case Cell.CELL_TYPE_NUMERIC:
//                cell.setCellType(Cell.CELL_TYPE_STRING);
//                return cell.getStringCellValue();
//            case Cell.CELL_TYPE_STRING:
//                return cell.getStringCellValue();
//            default:
//                return "";
//        }
//    }

}
