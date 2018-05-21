package utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

/**
 * Created by cch on 2017/9/5.
 */
public class GetSearchDataNew {
    public static Object[][] getSearchData(String FileName,String sheetName) throws IOException {
        //根据参数传入的数据文件路径和文件名称，组合出Excel数据文件的绝对路径，声明一个File文件对象
        File file = new File(FileName);
        //创建FileInputStream对象用于读取Excel文件
        FileInputStream inputStream = new FileInputStream(file);
        Workbook Workbook = null;
        //获取文件名参数的扩展名，判断是.xlsx文件还是.xls文件
        String fileExtensionName = FileName.substring(FileName
                .indexOf("."));
        ArrayList<String> resultKey = new ArrayList<String>();

        if (fileExtensionName.equals(".xlsx")) {
            Workbook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            Workbook = new HSSFWorkbook(inputStream);
        }
        //通过sheetName参数，声称Sheet对象
        Sheet Sheet = Workbook.getSheet(sheetName);
        //获取Excel数据文件Sheet1中数据的行数，getLastRowNum()方法获取数据的最后一行行号
        //getFirstRowNum()方法获取数据的第一行行号，相减之后得出数据的行数，Excel文件的行号和列号都是从0开始
        int rowCount = Sheet.getLastRowNum()+1;
        //得到Excel的行
        int colCount = Sheet.getRow(0).getLastCellNum();

        //为了返回值是Object[][],定义一个多行单列的二维数组
        HashMap<String,String>[][] results = new HashMap[rowCount - 1][1];
        //对数组中所有的元素hashmap进行初始化
        if(rowCount >1){
            for (int i =0; i< rowCount -1;i++) {
                results[i][0] = new HashMap<>();
            }
        }else {
            System.out.println("excel中没有数据");
        }

        //获得首行的列名，作为hashmap的key值
        for (int c = 0; c< colCount; c++) {
            Sheet.getRow(0).getCell(c).setCellType(CELL_TYPE_STRING);
            String cellvalue =  Sheet.getRow(0).getCell(c).getStringCellValue();
            resultKey.add(cellvalue);
        }

        // 遍历所有的单元格的值添加到hashmap中
        for (int r = 1; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                Sheet.getRow(r).getCell(c).setCellType(CELL_TYPE_STRING);
                String cellvalue =  Sheet.getRow(r).getCell(c).getStringCellValue();
                results[r-1][0].put(resultKey.get(c), cellvalue);
            }
        }
        return results;
    }
}
