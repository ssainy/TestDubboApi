package TestCase.MyinvoiceAccount.account_pc_controller;

import api.DealResult;
import api.DoApi;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/9/14.
 */
public class PollLogin {
    @Test(enabled = false)
    public void pollLogin(){
        String ret = DoApi.doPollLogin("");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}
