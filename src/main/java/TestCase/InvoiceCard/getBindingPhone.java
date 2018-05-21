package TestCase.InvoiceCard;

import api.DealResult;
import api.DoApi;
import api.GetProperties;
import api.GetRedis;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;


/**
 * Created by cch on 2017/8/31.
 */
public class getBindingPhone {

    /**
     * 绑定、未绑定、绑定校验手机号正确性
     */
 /**   @Test(description = "预先绑定")
    public void binding(){
        String phone = GetProperties.GetPhone();
        String newcode = BindingDep.sendMsgAndgetSmsCode(phone,"2","1");
        System.out.println(newcode);
        String ret= DoApi.doBinding(phone,GetProperties.GetWeChatToken(),newcode,"0","0");
        System.out.println("绑定结果是："+ret);
    }
    */
    @Test(description = "获取用户的手机号")
    public void getBindingPhone(){
        String ret = DoApi.doCardGetBindingPhone(GetRedis.GetWeChatToken());
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "名片操作失败，请稍后重试!");
        Assert.assertEquals(DealResult.getResult_Code(ret), "9999");

    }
    @Test(description = "未获取到用户信息")
    public void getBindingPhone1(){
        String ret = DoApi.doCardGetBindingPhone("1111");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "未获取到用户信息!");
        Assert.assertEquals(DealResult.getResult_Code(ret), "9991");

    }
    @Test(description = "token为空")
    public void getBindingPhone2(){
        String ret = DoApi.doCardGetBindingPhone("");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "未获取到用户信息!");
        Assert.assertEquals(DealResult.getResult_Code(ret), "9991");

    }
}
