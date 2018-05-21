package TestCase.InvoiceCard;

import api.DealResult;
import api.DoApi;
import DataProvider.TestDataProvider;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/8/31.
 */
public class fuzzyQuery {

    @Test(description = "模糊查询",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void fuzzyQuery(String nsrmc,String ptcode,String Output_code,String Output_message,String description){
        String ret = DoApi.dofuzzyQuery(nsrmc,ptcode);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);

       /* System.out.println(JsonUtils.getjsondata(ret,"data"));
        String ss=JsonUtils.getjsondata(ret,"data");
        System.out.println(JsonUtils.getJsonArrayData(ss,"nsrmc"));*/
        /**
         * 此处需要继续校验返回的内容是否正确
         */
    }

    @Test(description = "查询并校验查询结果里包含输入内容")
    public void fuzzyQueryVerify(){
        String ret = DoApi.dofuzzyQuery("大象","01");
        Reporter.log("接口返回："+ret);
        String data=JsonUtils.getjsondata(ret,"data").replace("[\"","").replace("\"]","").replace("\\","");
        Assert.assertTrue(JsonUtils.getjsondata(data,"nsrmc").contains("大"));
        Assert.assertTrue(JsonUtils.getjsondata(data,"nsrmc").contains("象"));
    }

}
