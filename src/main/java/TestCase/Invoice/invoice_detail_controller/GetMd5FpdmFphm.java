package TestCase.Invoice.invoice_detail_controller;

import api.DealResult;
import api.DoApi;
import api.GetRedis;
import DataProvider.TestDataProvider;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;
import utils.MD5Util;

/**
 * 加密发票代码和发票号码,注意此接口不校验token是否正确
 */

public class GetMd5FpdmFphm {
    /**
     * 加密发票代码和发票号码
     */
    @Test(description = "加密发票代码和发票号码",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void getMd5FpdmFphm(String fpdm,String fphm,String Output_code,String Output_message,String fpdmMd5,String fphmMd5,String description){
        String ret = DoApi.dogetMd5FpdmFphm(GetRedis.GetWeChatToken(),fpdm,fphm,"","");
        Reporter.log("接口返回内容："+ret);
        System.out.println("接口返回内容："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
        Assert.assertEquals(JsonUtils.getjsondata(ret,"data","fpdm"),fpdmMd5);
        Assert.assertEquals(JsonUtils.getjsondata(ret,"data","fphm"),fphmMd5);
    }

    /**
    * 校验fpdm fphm 为空
    * */
    @Test(description = "发票代码、发票号码为空")
    public void getMd5FpdmFphmIsNull(){
        String ret = DoApi.dogetMd5FpdmFphm(GetRedis.GetWeChatToken(),"","","","");
        Reporter.log("接口返回内容："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");
        Assert.assertEquals(JsonUtils.getjsondata(ret,"data"),"{}");
    }
}
