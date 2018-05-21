package utils;

import jxl.*;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel放在Data文件夹下</p>
 * Excel命名方式：测试类名.xls</p>
 * Excel的sheet命名方式：测试方法名</p>
 * Excel第一行为Map键值</p>
 * 代码参考郑鸿志的Blog
 *
 * @ClassName: ExcelDataProvider
 * @Description: TODO(读取Excel数据)
 */

public class ExcelDataProvider{

    private Workbook book         = null;
    private Sheet    sheet        = null;
    private int      rowNum       = 0;
    private int      currentRowNo = 0;
    private int      columnNum    = 0;
    private String[] columnnName;

    //重构构造函数，将待测试类名和方法名传入函数
    public ExcelDataProvider(String classname, String methodname) {

        try {

//            int dotNum = classname.indexOf(".");
//
//            if (dotNum > 0) {
//                classname = classname.substring(classname.lastIndexOf(".") + 1,
//                        classname.length());
//            }
            //从/data文件夹下读取以类名命名的excel文件
            //String path = "data/" + classname + ".xls";
            //InputStream inputStream = new FileInputStream(path);
            InputStream inputStream = new FileInputStream(classname);
            book = Workbook.getWorkbook(inputStream);
            //取sheet
            sheet = book.getSheet(methodname);
            rowNum = sheet.getRows();
            System.out.println("row .......is " + rowNum);
            Cell[] cell = sheet.getRow(0);
            columnNum = cell.length;
            columnnName = new String[cell.length];

            for (int i = 0; i < cell.length; i++) {
                columnnName[i] = cell[i].getContents().toString();
            }
            this.currentRowNo++;

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("unable to read Excel data");
        }
    }

    //判断Excel表中是否存在下一行数据
    public boolean hasNext() {

        if (this.rowNum == 0 || this.currentRowNo >= this.rowNum) {
            try {
                book.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        } else {
            // sheet下一行内容为空判定结束
            if ((sheet.getRow(currentRowNo))[0].getContents().equals(""))
                return false;
            return true;
        }
    }
    //读取Excel中某一行数据，并为下一行做准备
    public List<String> getdata_list() {

        Cell[] c = sheet.getRow(this.currentRowNo);
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < this.columnNum; i++) {
            String temp = "";
            try {
                temp = c[i].getContents().toString();
            } catch (ArrayIndexOutOfBoundsException ex) {
                temp = "";
            }
            if(temp != null&& !temp.equals(""))
                list.add(temp);
        }

        this.currentRowNo++;
        return list;
    }

    public void remove() {
        throw new UnsupportedOperationException("remove unsupported.");
    }

    public int getCaserowNum(){
        return (this.rowNum-1);
    }
}
