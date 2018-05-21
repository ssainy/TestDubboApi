package TestCase.Invoice.InvoiceAPI.invoice_list_controller;

import DataProvider.TestDataProvider;
import api.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/12/8.
 */
public class GetAndSetUserSetting {
    @Test(description = "设置用户设置信息",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public  void setUserSetting(String settingType,String settingValue){
        String ret = DoApi.dosetUserSetting(GetRedis.GetWeChatToken(),settingType,settingValue);
        System.out.println("setUserSetting ret is :"+ret);
        Reporter.log("设置用户信息接口返回结果："+ret);
        //判断接口返回状态
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        ret = DoApi.dogetUserSetting(GetRedis.GetWeChatToken());
        System.out.println("getUserSetting ret is :"+ret);
        Reporter.log("获取用户信息接口返回结果："+ret);
        //判断接口返回状态
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

        String data = JsonUtils.getjsondata(ret, "data");
        System.out.println("data is :"+data);
        Reporter.log("接口返回结果："+data);
        String sqlData= DoSql.DogetUserSetting();
        System.out.println("sqlData is :"+sqlData);
        Reporter.log("数据库返回结果："+sqlData);
        Assert.assertEquals(JsonUtils.getjsondata(data,"value"),JsonUtils.getJsonArrayData(sqlData,"value"));
        Assert.assertEquals(JsonUtils.getjsondata(data,"value"),settingValue);
    }

}
