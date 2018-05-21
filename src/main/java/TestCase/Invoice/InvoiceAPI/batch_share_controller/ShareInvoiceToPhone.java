package TestCase.Invoice.InvoiceAPI.batch_share_controller;

import api.DealResult;
import api.DoApi;
import api.GetProperties;
import api.GetRedis;
import DataProvider.TestDataProvider;
import dependence.AddInvoice;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

import java.io.IOException;

/**
 * Created by cch on 2018/3/9.
 */
public class ShareInvoiceToPhone {

    @Test(description="通过手机号分享发票-交通票",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void shareInvoice_traffic(String flag, String info,String Output_code, String Output_message,String description) throws IOException {
        String token = GetRedis.GetWeChatToken();
        //新建交通票实体类并在类初始化过程中调用OCR识别发票
        String  ret=DoApi.dotrafficInvoiceCollection(token,"",flag,info);
        String invoiceId=JsonUtils.getjsondata(ret,"data","invoiceId");
         ret = DoApi.doshareInvoiceToPhone_traffic(token,"001",GetProperties.GetPhone(),invoiceId,"0");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);

    }
    @Test(description="通过手机号分享发票-普通票",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void shareInvoice_invoice( String info,String type,String Output_code,String Output_message,String description) throws IOException {
        String token = GetRedis.GetWeChatToken();
        String uid = GetRedis.GetWeChatuid();
        AddInvoice ai = new AddInvoice("1",info,"",token,uid,type);
        ai.addInvoice();
        String invoiceId = ai.getInvoiceId();
        String ret = DoApi.doshareInvoiceToPhone_invoice(token,"002",GetProperties.GetPhone(),invoiceId,"");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);

    }
}
