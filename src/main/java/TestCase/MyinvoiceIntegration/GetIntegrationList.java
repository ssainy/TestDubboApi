package TestCase.MyinvoiceIntegration;

import api.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/9/28.
 */
public class GetIntegrationList {
    @Test(description = "积分明细")
    public void getIntegrationList(){
        String ret = DoApi.dogetIntegrationList(GetRedis.GetWeChatToken(), "1","100");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "积分信息获取成功！");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

        String result= JsonUtils.getjsondata(ret,"data","integrationlist");
        System.out.println("接口返回的积分列表："+result);
        String sql= DoSql.DogetIntegretionList();
        System.out.println("数据库取出的积分列表为："+sql);
        //对比接口和数据库返回的列表条数是否一致
        Assert.assertEquals(JsonUtils.getJsonArrayCount(result),JsonUtils.getJsonArrayCount(sql));

    }
}
