package TestCase.Invoice.InvoiceAPI.share_invoice_controller;

import api.DealResult;
import api.DoApi;
import api.DoSql;
import api.GetProperties;
import api.GetRedis;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/12/13.
 */
public class GetIntegration {
    String fpdm;
    String fphm;
    @BeforeMethod
    public void SetUp(){
        String sqlInvoice= DoSql.DogetInvoiceData();
        String invoiceInfo=JsonUtils.getJsonArrayData(sqlInvoice,1);
        System.out.println(invoiceInfo);
        String invoiceinfo_id=JsonUtils.getjsondata(invoiceInfo,"invoiceinfo_id");
        String invoice=DoSql.DogetInvoiceInfoById(invoiceinfo_id);
        System.out.println(invoice);

        fphm=JsonUtils.getJsonArrayData(invoice,"fphm");
        fpdm=JsonUtils.getJsonArrayData(invoice,"fpdm");
    }
    @Test
    public void getIntegration(){
        String ret = DoApi.dogetIntegration(GetRedis.GetWeChatToken(),fpdm,fphm,"0");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}