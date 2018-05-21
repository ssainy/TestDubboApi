package utils;

import api.GetProperties;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;

/**
 * Created by cch on 2017/9/5.
 */
public class GetSearchData {
    public static Object[][] getSearchData(String FileName,String sheetName) throws IOException {
        //根据参数传入的数据文件路径和文件名称，组合出Excel数据文件的绝对路径，声明一个File文件对象
        File file = new File(FileName);
        //创建FileInputStream对象用于读取Excel文件
        FileInputStream inputStream = new FileInputStream(file);
        org.apache.poi.ss.usermodel.Workbook Workbook = null;
        //获取文件名参数的扩展名，判断是.xlsx文件还是.xls文件
        String fileExtensionName = FileName.substring(FileName.indexOf("."));
        if (fileExtensionName.equals(".xlsx")) {
            Workbook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            Workbook = new HSSFWorkbook(inputStream);
        }
        //通过sheetName参数，声称Sheet对象
        org.apache.poi.ss.usermodel.Sheet Sheet = Workbook.getSheet(sheetName);
        //获取Excel数据文件Sheet1中数据的行数，getLastRowNum()方法获取数据的最后一行行号
        //getFirstRowNum()方法获取数据的第一行行号，相减之后得出数据的行数，Excel文件的行号和列号都是从0开始
        int rowCount = Sheet.getLastRowNum() - Sheet.getFirstRowNum();
        //创建list对象存储从Excel数据文件读取的数据
        List<Object[]> records = new ArrayList<Object[]>();
        //循环遍历Excel数据文件的所有数据，除了第一行，第一行是数据列名称
        for (int i = 1; i < rowCount + 1; i++) {
            //使用getShow方法获取行对象
            Row row = Sheet.getRow(i);
            //声明一个数组，存储Excel数据文件每行中的3个数据，数组的大小用getLastCellNum()方法进行动态声明，实现测试数据个数和数组大小一致
            String fields[] = new String[row.getLastCellNum()];
            for (int j = 0; j < row.getLastCellNum(); j++) {
//                System.out.println("列数："+row.getLastCellNum());
                //使用getCell()和getStringCellValue()方法获取Excel文件中的单元格数据
                //fields[j] =row.getCell(j).getStringCellValue();
                if(row.getCell(j)==null){
                    fields[j] = "";
                }else {
                    row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                    if("".equals(row.getCell(j).getStringCellValue())){
                        fields[j] = "";
                    }else {
                        fields[j] = row.getCell(j).getStringCellValue();
                    }
                }

            }
            //将fields的数据对象存入records的list中
            records.add(fields);
        }
        // 将存储测试数据的List转换为一个Object的二维数组
        Object[][] results = new Object[records.size()][];
        // 设置二位数组每行的值，每行是一个Object对象
        for (int i = 0; i < records.size(); i++) {
            results[i] = records.get(i);
        }
        return results;
    }
public static void main(String args[]) throws IOException {
}
}
