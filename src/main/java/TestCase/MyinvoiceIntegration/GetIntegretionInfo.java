package TestCase.MyinvoiceIntegration;

import api.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/9/28.
 */
public class GetIntegretionInfo {
    @Test(description = "获取总积分排名和当天积分信息")
    public void getIntegretionInfo(){
        String ret = DoApi.dogetIntegretionInfo(GetRedis.GetWeChatToken());
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "积分信息获取成功！");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

        String result= JsonUtils.getjsondata(ret,"data","integration");
        System.out.println("接口返回的积分："+result);
        String sql= DoSql.DogetIntegretionInfo();
        System.out.println("数据库取出的积分："+sql);
        String resultCredits=JsonUtils.getjsondata(result,"total_credits");
        String sqlCredits=JsonUtils.getJsonArrayData(sql,"credits");
        if (sql.equals("[]")){
            System.out.println("数据库没有相关积分数据!");
            sqlCredits="0";
        }
        Assert.assertEquals(resultCredits,sqlCredits);
    }
}
