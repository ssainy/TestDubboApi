package TestCase.MyinvoiceAccount.account_user_controller;
import DataProvider.TestDataProvider;
import api.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by Administrator on 2017/9/27.
 */
public class UpdateUserInfo {
    private final String curToken = GetRedis.GetWeChatToken();
    private final String curUid = GetRedis.GetWeChatuid();
    private final String userInfoOld = JsonUtils.getjsondata(DoApi.dogetUserInfo(curUid,curToken),"data","userInfo");

    @Test(description = "更新用户信息", dataProvider = "testDataProvider", dataProviderClass = TestDataProvider.class)
    public void updateUserInfo(String Output_code, String Output_message,String description) {
        //执行更新用户信息
        String userInfoNew=JsonUtils.jsondata(userInfoOld,"sex","2");
        String ret = DoApi.doupdateUserInfo(curToken,userInfoNew);
        Reporter.log("接口返回结果："+ret);
        System.out.println("响应数据："+ret);
        String userinfo=JsonUtils.getjsondata(DoApi.dogetUserInfo(curUid,curToken),"data","userInfo");
        System.out.println("更新后的用户信息为:  \n"+userinfo);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
        Assert.assertEquals(JsonUtils.getjsondata(userinfo,"sex"),"2");
    }

    @AfterMethod
    public void revertUserinfo(){
        //执行复原用户信息
        DoApi.doupdateUserInfo(curToken,userInfoOld);
        System.out.println("复原后的用户信息为:  \n"+JsonUtils.getjsondata(DoApi.dogetUserInfo(curUid,curToken),"data","userInfo"));
    }
}
