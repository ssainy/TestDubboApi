package TestCase.Invoice.invoice_check_API.invoice_qr_check_controller;

import DataProvider.MyInvoice_InvoiceDataProvider;
import DataProvider.TestDataProvider;
import api.DealResult;
import api.DoApi;
import api.DoSql;
import api.GetRedis;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2018/4/12.
 */
public class QrCheck {
    @Test(description = "添加发票",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void qrcheck(String flag,String info,String fphm,String Output_code,String  Output_message,String status,String description){
        String token = GetRedis.GetWeChatToken();
        String uid = GetRedis.GetWeChatuid();
        String ret = DoApi.doqrcheck(flag,info,token,"");
        System.out.println("使用qrcheck添加发票返回结果："+ret);
        Reporter.log("使用qrcheck添加发票返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
        String sqlInvoiceList = DoSql.DoShowInvoiceByUid(uid);
        Assert.assertEquals(sqlInvoiceList.contains(fphm),status.equals("true"));
    }
}
