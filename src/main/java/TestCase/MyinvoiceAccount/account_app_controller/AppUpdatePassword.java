package TestCase.MyinvoiceAccount.account_app_controller;

import api.Data;
import api.DoApi;
import api.GetRedis;
import DataProvider.TestDataProvider;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/9/11.
 */
public class AppUpdatePassword {
    @Test(description = "修改密码接口",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void updatePassword(String account, String password, String repassWord, String repassword, String userType, String verifyCode,String description) {
        String req_str = Data.updatePassword();
        req_str = JsonUtils.jsondata(req_str, "account", account);
        req_str = JsonUtils.jsondata(req_str, "password", password);
        req_str = JsonUtils.jsondata(req_str, "repassWord", repassWord);
        req_str = JsonUtils.jsondata(req_str, "repassword", repassword);
        req_str = JsonUtils.jsondata(req_str, "userType", userType);
        req_str = JsonUtils.jsondata(req_str, "verifyCode", verifyCode);
        String ret = DoApi.doAppUpdatePassword("1", GetRedis.GetAPPToken(), req_str);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
    }
}
