package utils;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by cch on 2017/12/15.
 */
public class DownLoadExcel {
    public static void  exportExcel(String file,String filePath) throws Exception{
        InputStream is=new FileInputStream(file);
        OutputStream os=null;
        InputStreamReader isr=new InputStreamReader(is);
        BufferedReader br=new BufferedReader(isr);
        String line="";
        int i=0;
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        Sheet sheet = wb.createSheet();
        while((line=br.readLine())!=null){
            Row row = sheet.createRow(i);
            String[] split = line.split(",");
            for(int j=0;j<split.length;j++){
                String value=split[j];
                value=value.replaceAll("\"", "");
                Cell cell = row.createCell(j);
                cell.setCellValue(Long.valueOf(value));
            }
            i++;
        }
        os=new FileOutputStream(new File(filePath));
        wb.write(os);
        os.close();
        isr.close();
        br.close();
        is.close();
    }
    public static void download(HttpServletRequest request,
                                HttpServletResponse response, String downLoadPath) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;


        File downFile = new File(downLoadPath);

        long fileLength = downFile.length();

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment; filename="
                + new String(downFile.getName().getBytes("utf-8"), "ISO8859-1"));
        response.setHeader("Content-Length", String.valueOf(fileLength));

        bis = new BufferedInputStream(new FileInputStream(downLoadPath));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
    }
}
