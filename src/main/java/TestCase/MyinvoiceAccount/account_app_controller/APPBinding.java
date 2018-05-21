package TestCase.MyinvoiceAccount.account_app_controller;

import DataProvider.TestDataProvider;
import api.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/9/29.
 */
public class APPBinding {
    @Test(description = "绑定手机接口",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void APPBinding(String account, String code,String platform,String source,String Output_code,String Output_message,String description){
        String ret = DoApi.doAPPBinding(account, GetRedis.GetAPPToken(),code,platform,source, Data.appBinding());
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);

    }
}
