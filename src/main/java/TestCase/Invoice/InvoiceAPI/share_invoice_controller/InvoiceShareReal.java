package TestCase.Invoice.InvoiceAPI.share_invoice_controller;

import api.DealResult;
import api.DoApi;
import api.GetProperties;
import api.GetRedis;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/12/13.
 */
public class InvoiceShareReal {
    String fpdm;
    String fphm;
    String trafficId;
    String shareUid;
    String invoiceType;
    String clientType;
    //电子发票分享至公众号
    @BeforeMethod
    public void SetUp(){
        String ret = DoApi.dogetInvoiceList(GetRedis.GetWeChatToken(), "", "", "", "", "", "", "", "","","");
       /* System.out.println(ret);
        String invoiceInfo= JsonUtils.getRandomJsonArrayData(ret,"");*/
    }
    //发票类型 0 电子发票 1 纸质发票 2 火车票 3出租车票
   // clientType 客户端类型(0公众号 1PC 2app 3京东 4小程序)
    @Test(enabled = false)
    public void invoiceShareReal(){
        String ret = DoApi.doinvoiceShareReal(GetRedis.GetWeChatToken(),fpdm,fphm,trafficId,shareUid,invoiceType,clientType);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}
