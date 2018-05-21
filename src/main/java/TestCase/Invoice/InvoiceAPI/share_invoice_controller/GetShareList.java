package TestCase.Invoice.InvoiceAPI.share_invoice_controller;

import api.DealResult;
import api.DoApi;
import api.GetProperties;
import api.GetRedis;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/12/13.
 */
public class GetShareList {
    @Test
    public void getShareList(){
        String ret = DoApi.dogetShareList(GetRedis.GetWeChatToken());
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        if (DealResult.getResult_Code(ret).equals("9999")){
            Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功,数据为空");
        }else{
            Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
            Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

        }

    }
}
