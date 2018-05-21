package TestCase.MyinvoiceAccount.account_app_controller;

import api.DealResult;
import api.DoApi;
import api.GetProperties;
import api.GetRedis;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/9/11.
 */
public class AppLoginOut {
    @Test(description = "登出接口")
    public void appLogin(){
        String ret = DoApi.doAppLoginOut(GetRedis.GetAPPToken());
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "登出成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}
