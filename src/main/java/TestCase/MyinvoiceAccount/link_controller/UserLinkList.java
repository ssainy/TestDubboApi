package TestCase.MyinvoiceAccount.link_controller;

import api.DealResult;
import api.DoApi;
import api.GetRedis;
import DataProvider.TestDataProvider;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/9/26.
 */
public class UserLinkList {
    @Test(description = "获取关联账号列表",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void userLinkList(String linkType,String Output_code,String Output_message,String description){
        String ret = DoApi.douserLinkList(linkType, GetRedis.GetWeChatToken());
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);

    }
}
