package TestCase.MyinvoiceAccount.account_controller;

import api.DealResult;
import api.DoApi;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/9/12.
 */
public class WeChatLogin {
    @Test(description = "微信公众号登录的接口",enabled = false)
    @Ignore
    public void weChatLogin(){
        String ret = DoApi.doweChatLogin("013G91TA18XW0h0C1vUA1CKnTA1G91T4");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "0000");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}
