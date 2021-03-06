package TestCase.Ranking.ranking_controller;

import api.DealResult;
import api.DoApi;
import api.GetRedis;
import DataProvider.TestDataProvider;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/9/28.
 */
public class GetTopThree {
    @Test(description = "排行列表和排行前三", dataProvider = "testDataProvider", dataProviderClass = TestDataProvider.class)
//接口描述信息
    public void getTopThree(String binkingCount, String Output_code, String Output_message,String description) { //用方法名作为sheet页名，设置Sheet里传入的参数列表
        String ret = DoApi.dogetTopThree(GetRedis.GetWeChatToken(),binkingCount);//从DoApi中调用do方法，把请求的参数按顺序定义出来
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
    }
}
