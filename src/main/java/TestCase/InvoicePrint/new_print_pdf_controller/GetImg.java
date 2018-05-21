package TestCase.InvoicePrint.new_print_pdf_controller;

import api.DealResult;
import api.DoApi;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/12/14.
 */
public class GetImg {
    String fpdm="11111";
    String fphm="11111";
    String jym="11111";
    String printCode=null;
    @BeforeMethod
    public void getPrintCode(){
        String ret = DoApi.dogetPrintCode("1",fpdm,fphm,jym);
        printCode= JsonUtils.getjsondata(ret,"data");
    }
    @Test
    public void getImg(){
        String ret = DoApi.dogetImg(printCode);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(JsonUtils.getjsondata(ret,"data","fpdm"),fpdm);
        Assert.assertEquals(JsonUtils.getjsondata(ret,"data","fphm"),fphm);
        Assert.assertEquals(JsonUtils.getjsondata(ret,"data","jym"),jym);
        Assert.assertEquals(JsonUtils.getjsondata(ret,"data","type"),"print_invoice");
    }
}
