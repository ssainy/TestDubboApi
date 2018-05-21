package TestCase.MyinvoiceAccount.account_controller;

import api.DealResult;
import api.DoApi;
import api.GetProperties;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/9/12.
 */
public class GetWxInfo {
    @Test(description = "获取微信信息")
    public void getWxInfo(){
        String ret = DoApi.dogetWxInfo(GetProperties.GetOpenId());
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}
