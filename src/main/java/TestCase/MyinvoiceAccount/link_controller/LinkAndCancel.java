package TestCase.MyinvoiceAccount.link_controller;

import api.DealResult;
import api.DoApi;
import api.GetRedis;
import DataProvider.TestDataProvider;
import dependence.VerifyCode;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by Administrator on 2017/9/26.
 */
public class LinkAndCancel {
    @Test(priority = 1,description = "账号关联接口",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void link(String linkAccount,String linkType, String verifyCode1, String Output_code,String Output_message,String description) throws InterruptedException {
        //发送短信的类型(0:注册;1:密码修改;2:绑定 必传token  3 关联 )
        VerifyCode verifyCode=new VerifyCode("18335833885","3",GetRedis.GetWeChatToken());
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
        String ret = DoApi.dolink(linkAccount,linkType, verifycode, GetRedis.GetWeChatToken());
        System.out.println(ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);

            }
        }
    }
    @Test(priority = 2,description = "取消账号关联接口",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void cancel(String linkAccount, String linkType, String Output_code,String Output_message,String description){
        String ret = DoApi.dolinkCancel(linkAccount,linkType, GetRedis.GetWeChatToken());
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
    }
}
