package TestCase.MyinvoiceAccount.verify_code_controller;

import api.DealResult;
import api.DoApi;
import api.GetRedis;
import DataProvider.TestDataProvider;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/9/28.
 */
public class SendMsg {
    @Test(description = "发送短信验证码",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void sendMsg(String account,String type,String bindType,String Output_code,String Output_message,String description){
        String ret = DoApi.dosendMsg(account, GetRedis.GetWeChatToken(),type,bindType);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);

    }
}
