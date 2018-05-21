package TestCase.MyinvoiceAccount.verify_code_controller;

import api.DealResult;
import api.DoApi;
import DataProvider.TestDataProvider;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/9/28.
 */
public class SendEmail {
    @Test(description = "发送邮箱验证码",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void sendEmail(String account,String type,String Output_code,String Output_message,String description){
        String ret = DoApi.dosendEmail(account,type);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);

    }
}
