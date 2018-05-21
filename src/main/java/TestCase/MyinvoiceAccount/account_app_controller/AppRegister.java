package TestCase.MyinvoiceAccount.account_app_controller;

import api.Data;
import api.DealResult;
import api.DoApi;
import DataProvider.TestDataProvider;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/9/11.
 */
public class AppRegister {
    @Test(description = "注册接口",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void register(String account,String password,String repassWord,String repassword,String userType,String verifyCode,String Output_code,String Output_message,String description){
        String req_str= Data.register();
        req_str= JsonUtils.jsondata(req_str,"account",account);
        req_str= JsonUtils.jsondata(req_str,"password",password);
        req_str= JsonUtils.jsondata(req_str,"repassWord",repassWord);
        req_str= JsonUtils.jsondata(req_str,"repassword",repassword);
        req_str= JsonUtils.jsondata(req_str,"userType",userType);
        req_str= JsonUtils.jsondata(req_str,"verifyCode",verifyCode);
        String ret = DoApi.doAppRegister(req_str);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);

    }
}
