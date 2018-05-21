package TestCase.InvoiceCheck;

import api.DealResult;
import api.DoApi;
import api.GetRedis;
import DataProvider.TestDataProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/8/29.
 */
public class InvoiceVerify {
     Log logger = LogFactory.getLog(InvoiceVerify.class);
    @Test(description = "发票查验接口",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void invoiceVerify(String id,String kplx,String fplx,String fpdm,String fphm,String bhsje,String kprq,String jym,String crcjym,String Output_code,String Output_message,String reciptType,String cyjg_code,String cyjg_message,String description){
        String ret = DoApi.doinvoiceVerify(kplx+","+fplx+","+fpdm+","+fphm+","+bhsje+","+kprq+","+jym+","+crcjym,GetRedis.GetWeChatToken(), reciptType);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
        Assert.assertEquals(DealResult.getResult_cyjg_message(ret), cyjg_message);
        Assert.assertEquals(DealResult.getResult_cyjg_code(ret), cyjg_code);
        Reporter.log("我在打印日志");
        }
    }