package TestCase.InvoicePrint.new_print_pdf_controller;

import api.DealResult;
import api.DoApi;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/12/14.
 */
public class GetPrintCode {
    @Test
    public void getPrintCode(){
        String ret = DoApi.dogetPrintCode("111111","1100171320","1","1");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}
