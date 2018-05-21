package TestCase.MyinvoiceIntegration;

import api.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/9/28.
 */
public class GetIntegretionRule {
    @Test(description = "获取积分规则列表")
    public void getIntegretionRule(){
        String ret = DoApi.dogetIntegretionRule(GetRedis.GetWeChatToken());
        System.out.println("接口返回的积分规则："+ret);
        Reporter.log("接口返回结果："+ret);
        String result=JsonUtils.getjsondata(ret,"data","ruleList");
        System.out.println("接口返回的积分规则："+result);
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");
        Assert.assertEquals(DealResult.getResult_Message(ret), "积分规则列表获取成功！");
        String sql= DoSql.DogetIntegretionRule();
        System.out.println("数据库取出的积分规则："+sql);
        Assert.assertEquals(JsonUtils.getJsonArrayCount(JsonUtils.getjsondata(ret,"data","ruleList")),JsonUtils.getJsonArrayCount(sql));
        for (int i=0;i<6;i++){
            String str=JsonUtils.getjsondata(JsonUtils.getJsonArrayData(sql,i),"credits");
            Assert.assertTrue(result.contains(str));
        }
    }
}
