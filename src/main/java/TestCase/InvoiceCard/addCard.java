package TestCase.InvoiceCard;

import DataProvider.TestDataProvider;
import api.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/8/31.
 */
public class addCard {
    @Test(description = "添加名片",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    /**
     * 具体添加的名片的内容为Data.AddCard()的内容，此处的内容不做校验
     */
    public void addCard(String mpbs,String type,String Output_code,String Output_message,String rtCode,String description){
        String data= JsonUtils.jsondata(Data.AddCard(),"mpbs",mpbs);
        String ret = DoApi.doaddCard(GetRedis.GetWeChatToken(),type, data);
        System.out.println(ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);

        Assert.assertEquals(DealResult.getResult_rtCode(ret),rtCode);
    }
    @Test(description = "未获取到用户信息",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void addCard_tokenError(String token,String type,String Output_code,String Output_message,String rtCode,String rtMessage,String description){
        String ret = DoApi.doaddCard(token,type, Data.AddCard());
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
        Assert.assertEquals(DealResult.getResult_rtMessage(ret),rtMessage);
        Assert.assertEquals(DealResult.getResult_rtCode(ret),rtCode);

    }

}
