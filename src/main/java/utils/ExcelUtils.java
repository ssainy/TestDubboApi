package utils;

import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by thinkpad on 2017/6/24.
 */
public class ExcelUtils {
    public static HashMap<String,String> getDataMap(Workbook workbook) {
        //通过Workbook的getSheet方法选择第一个工作簿（从0开始）
        Sheet sheet = workbook.getSheet(0);

        int rows = sheet.getRows();
        int cols = sheet.getColumns();
        HashMap<String,String> dataMap = new HashMap<String, String>();
        for(int colNum=0;colNum<cols;++colNum){
            for(int rowNum=0;rowNum<rows;++rowNum){
                //获取单元格值
                String zNumStr = sheet.getCell(colNum,rowNum).getContents().trim();
                //double zNum = Double.parseDouble(zNumStr);
                //获取当前z值的x值
                String curRowXStr = sheet.getCell(0,rowNum).getContents();
                //Double curRowX = Double.parseDouble(curRowXStr);
                //获取当前z值的Y值
                String curColYStr = sheet.getCell(colNum,0).getContents();
                // Double curColY = Double.parseDouble(curColYStr);
                String key = "("+curRowXStr+","+curColYStr+")";
                dataMap.put(key, zNumStr);
            }
        }
        return dataMap;
    }

    /**********************************************
     * 获得excel文件的路径
     * @return
     * @throws IOException
     ***********************************************/
    public static String getPath(String fileName) throws IOException {
        File directory = new File(".");
        String sourceFile = directory.getCanonicalPath() + "\\src\\main\\resources\\"
                + fileName + ".xls";
        return sourceFile;
    }
}
