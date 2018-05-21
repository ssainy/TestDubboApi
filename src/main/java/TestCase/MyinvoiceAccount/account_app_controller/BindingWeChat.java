package TestCase.MyinvoiceAccount.account_app_controller;

import DataProvider.TestDataProvider;
import api.*;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/9/11.
 */
public class BindingWeChat {
    @Test(description = "绑定微信接口",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void bindingWeChat(String user_type,String description){
        String req_str= JsonUtils.jsondata(Data.bindingWeChat(),"user_type",user_type);
        req_str=JsonUtils.jsondata(req_str,"openid",GetProperties.GetOpenId());
        req_str=JsonUtils.jsondata(req_str,"unionid",GetProperties.GetUnionId());
        String ret = DoApi.dobindingWeChat(GetRedis.GetAPPToken(), req_str);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
    }
}
