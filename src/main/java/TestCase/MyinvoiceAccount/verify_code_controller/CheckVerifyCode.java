package TestCase.MyinvoiceAccount.verify_code_controller;

import DataProvider.TestDataProvider;
import api.*;
import dependence.BindingDep;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/9/27.
 */
public class CheckVerifyCode {
    String code;
    @Test(description = "校验验证码接口",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void checkVerifyCode(String account,String type,String Output_code,String Output_message,String description) {
        String ret1 = BindingDep.sendMsg(account, "0");
        System.out.println("发送短信返回结果：" + ret1);
        if (JsonUtils.getjsondata(ret1, "message").equals("手机号已存在")) {
            System.out.println("手机号已经注册！");
            String s = BindingDep.sendMsg(account, "1");
            System.out.println("发送短信返回结果：" + s);
            code = BindingDep.getSmsCode(account, "1");
        } else {
            // 0:注册 1:密码修改 2:绑定
            code = BindingDep.getSmsCode(account, "0");
            System.out.println("获取的验证码为：" + code);
            String ret = DoApi.docheckVerifyCode(account, code, type);
            System.out.println(ret);
            Reporter.log("接口返回结果："+ret);
            Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
            Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);

        }
    }
}
