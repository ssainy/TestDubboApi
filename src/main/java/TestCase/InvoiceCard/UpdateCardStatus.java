package TestCase.InvoiceCard;

import DataProvider.TestDataProvider;
import api.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/8/31.
 */
public class UpdateCardStatus {
    String addCardRet=null;
    @BeforeMethod
    public void addCard(){
        addCardRet=DoApi.doaddCard(GetRedis.GetWeChatToken(),"1", Data.AddCard());
    }
    @Test(description = "根据名片id修改名片状态(status)--删除",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void updateCardStatus(String id,String Output_code,String Output_message,String description){
        id = JsonUtils.getjsondata(addCardRet,"data","titleInfo","id");
        Assert.assertEquals(DealSqlResult.GetData_status(DoSql.DoQuery_Titel_info(id)), "1");
        String ret = DoApi.doupdateCardStatus(id);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret),Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret),Output_code);
        System.out.println(DoSql.DoQuery_Titel_info(id));
        Assert.assertEquals(DealSqlResult.GetData_status(DoSql.DoQuery_Titel_info(id)), "0");
    }
}
