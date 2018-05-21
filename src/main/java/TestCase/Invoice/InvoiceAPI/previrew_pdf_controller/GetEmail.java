package TestCase.Invoice.InvoiceAPI.previrew_pdf_controller;

import api.DealResult;
import api.DoApi;
import api.GetProperties;
import api.GetRedis;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/12/12.
 */
public class GetEmail {
    @Test
    public void getEmail(){
        String ret = DoApi.dogetEmail_previrewPDF(GetRedis.GetWeChatToken());
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}
