package TestCase.MyinvoiceAccount.account_signin_controller;

import DataProvider.TestDataProvider;
import api.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/9/26.
 */
public class SaveSignInfo {
    @Test(description = "新增签到",dataProvider = "testDataProvider",dataProviderClass = TestDataProvider.class)
    public void saveSignInfo(String source_type, String Output_code,String Output_message,String description){
        String ret = DoApi.dosaveSignInfo(GetRedis.GetWeChatToken(), source_type);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
//        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
    }
}
