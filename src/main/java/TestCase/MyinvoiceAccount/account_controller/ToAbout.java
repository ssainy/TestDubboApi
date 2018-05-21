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
public class ToAbout {
    @Test(description = "关于接口")
    public void toAbout(){
        String ret = DoApi.dotoAbout();
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "0000");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}
