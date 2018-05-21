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
public class SaveChannel {
    @Test(description = "保存用户channelId")
    public void saveChannel(){
        String ret = DoApi.doSaveChannel(GetRedis.GetAPPToken(),"1");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "保存用户设备信息成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}
