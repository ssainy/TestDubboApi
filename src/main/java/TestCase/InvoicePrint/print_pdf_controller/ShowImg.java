package TestCase.InvoicePrint.print_pdf_controller;

import api.DealResult;
import api.DoApi;
import api.GetProperties;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DownLoadExcel;
import utils.GenPDF;
import utils.GetGenImg;

import java.io.File;

/**
 * Created by cch on 2017/12/15.
 */
public class ShowImg {
    @Test
    public void showImg() throws Exception {
        String path = System.getProperty("user.dir");
        File pathFile = new File(path+"\\src\\main\\resources\\a.png");
        if (pathFile.exists())
            pathFile.delete();
        Assert.assertFalse(pathFile.exists());
        String ret = DoApi.doshowImg("4a881268-6c1c-42b5-9580-ea7b9c19f361");
        GetGenImg.GenerateImage(ret,"a");
        Assert.assertTrue(pathFile.exists());
    }
}
