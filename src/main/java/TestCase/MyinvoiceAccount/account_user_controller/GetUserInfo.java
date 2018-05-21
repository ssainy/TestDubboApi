package TestCase.MyinvoiceAccount.account_user_controller;

import api.DealResult;
import api.DoApi;
import api.GetRedis;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by Administrator on 2017/9/27.
 */
public class GetUserInfo {
    @Test(description = "根据token和uid获取用户基本信息")
//接口描述信息
    public void getUserInfo() { //用方法名作为sheet页名，设置Sheet里传入的参数列表
        String ret = DoApi.dogetUserInfo(GetRedis.GetWeChatuid(), GetRedis.GetWeChatToken());//从DoApi中调用do方法，把请求的参数按顺序定义出来
        String userInfo = JsonUtils.getjsondata(ret, "data","userInfo");
        System.out.println(userInfo);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}
