package TestCase.InvoiceCard;

import DataProvider.TestDataProvider;
import api.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

import java.text.ParseException;

/**
 * Created by cch on 2017/8/31.
 */
public class ShowCardList {
    /**
     * 企业标识类型，type值
     * @param qybs
     * @param type
     * @param Output_code
     * @param Output_message
     * @param description
     */
    @Test(description = "ShowCardList - 测试展示发票名片列表",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void ShowCardList(String qybs,String type,String Output_code,String Output_message,String description) throws ParseException {

        String ret = DoApi.doshowCardList(qybs, type, GetRedis.GetWeChatToken());
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals( DealResult.getResult_Message(ret),Output_message);
        Assert.assertEquals( DealResult.getResult_Code(ret), Output_code);

        String SQLCardList=DoSql.DoGETCardList();
        System.out.println("数据库执行结果为："+SQLCardList);//通过openID查询数据库中的名片列表
        String retCardList=JsonUtils.getjsondata(ret,"data","cardList");
        //对比返回的名片数量是否和数据库匹配
        Assert.assertEquals(JsonUtils.getJsonArrayCount(SQLCardList),JsonUtils.getJsonArrayCount(JsonUtils.getjsondata(ret,"data","cardList")));
        for (int i=0;i<JsonUtils.getJsonArrayCount(SQLCardList);i++) {
            //取数据库返回数据的第n个数据
            String ArrayData=JsonUtils.getJsonArrayData((SQLCardList),i);
            //将数据库返回数据的字段顺序替换为接口返回顺序
            System.out.println(DealSqlResult.getCardList(ArrayData));
            System.out.println(ret);
            //判断数据库的字段是否包含在接口返回数据中
            Assert.assertTrue(ret.contains(DealSqlResult.getCardList(ArrayData)));

        }

    }
}
