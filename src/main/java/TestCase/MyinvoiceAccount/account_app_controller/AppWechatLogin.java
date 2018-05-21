package TestCase.MyinvoiceAccount.account_app_controller;

import api.Data;
import api.DealResult;
import api.DoApi;
import api.GetProperties;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/9/11.
 */
public class AppWechatLogin {
    @Test(description = "微信登录接口")
    public void appWechatLogin(){
        String req_str=Data.appWechatLogin();
        req_str= JsonUtils.jsondata(req_str,"user_type","4");
        req_str= JsonUtils.jsondata(req_str,"openid",GetProperties.GetAPPOpenId());
        req_str= JsonUtils.jsondata(req_str,"unionid",GetProperties.GetUnionId());
        String ret = DoApi.doAppWechatLogin(req_str);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}