package TestCase.MyinvoiceAccount.account_controller;

import api.DealResult;
import api.DoApi;
import api.GetProperties;
import api.GetRedis;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/9/12.
 */
public class GetWechickStatus {
    @Test(description = "判断是否关注公众号")
    public void getWechickStatus(){
        String ret = DoApi.dogetwechickstatus(GetRedis.GetWeChatToken());
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "null");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}
