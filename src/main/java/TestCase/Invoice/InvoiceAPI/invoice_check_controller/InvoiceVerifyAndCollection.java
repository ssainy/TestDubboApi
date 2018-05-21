package TestCase.Invoice.InvoiceAPI.invoice_check_controller;

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
 * Created by Administrator on 2017/8/31.
 */
public class InvoiceVerifyAndCollection  {
    private static Log logger= LogFactory.getLog(InvoiceVerifyAndCollection.class);
    @Test(description="InvoiceVerifyAndCollection - 正常PC、公众号发票查验接口,只查验发票和保存查验信息",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void invoiceVerifyAndCollection(String info,String type,String reciptType,String Output_code,String Output_message,String description ){
        String ret = DoApi.doinvoiceVerifyAndCollection(info, GetRedis.GetWeChatToken(),type,reciptType);
        logger.info(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "发票信息查验成功!");
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);

    }
}
