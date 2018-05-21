package TestCase.InvoicePrint.new_print_pdf_controller;

import api.DealResult;
import api.DoApi;
import api.GetProperties;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.GenPDF;

import java.io.File;

/**
 * Created by cch on 2017/12/14.
 */
public class DownloadpdfCheck {
    @Test
    public void downloadpdfCheck(){
        String path=System.getProperty("user.dir");
        File pathFile = new File(path+"\\src\\main\\java\\images\\a.pdf");
        if (pathFile.exists())
            pathFile.delete();
        Assert.assertFalse(pathFile.exists());
        String ret = DoApi.dodownloadPdfCheck("011001600211","80098347","982842");
        GenPDF.baseStringToPDF(ret,path+"\\src\\main\\java\\images\\a.pdf");
        Assert.assertTrue(pathFile.exists());
    }
}
