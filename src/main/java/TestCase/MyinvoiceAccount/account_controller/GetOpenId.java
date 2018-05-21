package TestCase.MyinvoiceAccount.account_controller;

import api.DealResult;
import api.DoApi;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/9/12.
 */
public class GetOpenId {
    @Test(enabled = false)
    public void getOpenId(){
        String ret = DoApi.dogetOpenId("1111");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);

        Assert.assertEquals(DealResult.getResult_Message(ret), "");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");
        Reporter.log("我在打印日志");
    }
}
