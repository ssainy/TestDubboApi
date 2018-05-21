package TestCase.MyinvoiceAccount.account_pc_controller;

import api.DealResult;
import api.DoApi;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/9/14.
 */
public class GetOpenId {
    @Test(enabled = false)
    public void getOpenId(){
        String ret = DoApi.dogetOpenId("");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
    }
}
