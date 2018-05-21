package TestCase.InvoicePrint.print_pdf_controller;

import api.DealResult;
import api.DoApi;
import api.GetProperties;
import api.GetRedis;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.DownLoadExcel;

/**
 * Created by cch on 2017/12/15.
 */
public class ExportExcel {
    @Test
    public void exportExcel() throws Exception {
        String path=System.getProperty("user.dir");
        String ret = DoApi.doexportExcel(GetRedis.GetWeChatToken(),"[\"940138022022807552,0\",\"201712131833323675361076747264,0\",\"201712131833323675361075125248,0\",\"894ce6c7ddb8455aad0311ddb0333f38,0\"]");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
       // Assert.assertEquals(DealResult.getResult_Code(ret), "0000");
       // Assert.assertEquals(DealResult.getResult_Message(ret), "批量获取pdf成功");
       // DownLoadExcel.downloadFile(ret,path+"\\src\\main\\java\\images\\a.xls");
       // DownLoadExcel.download(request,response,path+"\\src\\main\\resources\\a.xls");
    }
}
