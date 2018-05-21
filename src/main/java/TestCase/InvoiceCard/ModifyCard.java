package TestCase.InvoiceCard;

import api.DealResult;
import api.DoApi;
import DataProvider.TestDataProvider;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/8/31.
 */
public class ModifyCard {
    /**
     * 修改入参为字段
     * 确定内层code
     * @param newTitleInfo
     * @param Output_code
     * @param Output_message
     * @param description
     */
    @Test(description = "ModifyCard - 根据名片id修改名片信息",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void modifyCard(String newTitleInfo, String Output_code, String Output_message ,String description){
        String ret = DoApi.doModifyCard(newTitleInfo);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret),Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);

    }
}
