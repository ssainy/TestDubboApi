package TestCase.Invoice.InvoiceAPI.batch_share_controller;

import DataProvider.TestDataProvider;
import api.*;
import dependence.AddInvoice;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

import java.io.IOException;

/**
 * Created by cch on 2018/3/9.
 */
public class NextShareInvoice {
    String uuid;
    String req_str;
    String ret;
    String bathShareIdList =DoSql.DoGetBathShareId();
    @Test(description="再次分享-交通票",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void shareInvoice_traffic(String flag, String info,String Output_code, String Output_message,String description) throws IOException {
        String bathShareId=JsonUtils.getRandomJsonArrayData(bathShareIdList,"id");
        String token = GetRedis.GetWeChatToken();
        String ret=DoApi.dotrafficInvoiceCollection(token,"",flag,info);
        String invoiceId=JsonUtils.getjsondata(ret,"data","invoiceId");
         ret = DoApi.donextShareInvoice_traffic(token,"001",bathShareId,invoiceId,"0");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);

    }
    @Test(description="再次分享-普通票",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void shareInvoice_invoice( String info,String type,String Output_code,String Output_message,String description) throws IOException {
        String bathShareId=JsonUtils.getRandomJsonArrayData(bathShareIdList,"id");
        String token = GetRedis.GetWeChatToken();
        String uid = GetRedis.GetWeChatuid();
        AddInvoice ai = new AddInvoice("",info,"",token,uid,type);
        ai.addInvoice();
        String invoiceId = ai.getInvoiceId();
        String ret = DoApi.donextShareInvoice_invoice(token,"002",bathShareId,invoiceId,"");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);

    }
}
