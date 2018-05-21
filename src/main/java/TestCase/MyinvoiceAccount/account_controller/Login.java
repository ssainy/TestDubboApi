package TestCase.MyinvoiceAccount.account_controller;

import api.Data;
import api.DealResult;
import api.DoApi;
import api.GetProperties;
import api.GetRedis;
import dependence.VerifyCode;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/9/12.
 */
public class Login {
    @BeforeMethod
    public static void setUp() throws InterruptedException{
        String req_str= Data.register();
        req_str= JsonUtils.jsondata(req_str,"account",GetProperties.GetPhone());
        req_str= JsonUtils.jsondata(req_str,"password",GetProperties.GetPwd());
        req_str= JsonUtils.jsondata(req_str,"repassWord",GetProperties.GetPwd());
        req_str= JsonUtils.jsondata(req_str,"repassword",GetProperties.GetPwd());
        //用户类型0手机号,1微信,2 qq , 3微博,4邮箱
        req_str= JsonUtils.jsondata(req_str,"userType","0");
        //发送短信的类型(0:注册;1:密码修改;2:绑定 必传token  3 关联 )
        VerifyCode verifyCode=new VerifyCode(GetProperties.GetPhone(),"0",GetRedis.GetWeChatToken());
        String ret1=verifyCode.sendMsg();
        System.out.println("发送短信返回结果："+ret1);
        if (JsonUtils.getjsondata(ret1,"message").equals("手机号已存在")){
            System.out.println("手机号已经注册！");
        }else {
            String verifycode=verifyCode.getSmsCode();
            //发送短信的类型(0:注册;1:密码修改;2:绑定 必传token  3 关联 )
            //String verifyCode = BindingDep.getSmsCode(account, "0");
            System.out.println("短信验证码为："+verifycode);
            if (verifyCode.equals(null)){
                System.out.println("短信验证码没有发送成功！");
            }else {
                req_str = JsonUtils.jsondata(req_str, "verifyCode", verifycode);
                String ret = DoApi.doAppRegister(req_str);
                System.out.println(ret);
            }
        }
    }
    @Test(description = "登录接口")
    public void login() throws InterruptedException {
        String ret = DoApi.dologin(GetProperties.GetPhone(),GetProperties.GetPwd());
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);

        if (DealResult.getResult_Code(ret).equals("1004")){
            Login.setUp();
        }
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}
