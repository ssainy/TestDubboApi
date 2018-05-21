package TestCase.MyinvoiceAccount.binding_controller;

import api.DealResult;
import api.DoApi;
import api.GetRedis;
import DataProvider.TestDataProvider;
import dependence.BindingDep;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/9/26.
 */
public class Binding {
    String actualCode;
    @Test
    public void getCode(){
        //type: 发送短信的类型(0:注册;1:密码修改;2:绑定,3：关联)
        //bindType: 绑定类型（1：新绑定手机号；2：重新绑定手机号）默认为1
        this.actualCode=BindingDep.sendMsgAndgetSmsCode("15611726431","1","1");
        System.out.println("得到的Code为："+actualCode);
    }
    @Test(description = "用户绑定相关接口",dependsOnMethods = { "getCode" },dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void binding(String account, String code,String platform,String source,String Output_code,String Output_message,String description){
        if("actualCode".equals(code)){
            code = actualCode;
            System.out.println("真实code为："+actualCode);
        }
        String ret = DoApi.doBinding(account,GetRedis.GetWeChatToken(),code,platform,source);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);

    }
}
