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
public class exactQuery {
    @Test(description = "精确查询",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void exactQuery(String nsrmc,String ptcode,String Output_code,String Output_message,String description){
        String ret = DoApi.doexactQuery(nsrmc,ptcode);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);

        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals((JsonUtils.getjsondata(ret,"data","nsrmc")),nsrmc);
    }

    @Test(description = "精确查询，不存在的纳税人名称,查询编码默认：01")
    public void exactQueryNotExistDefault(){
        String ret = DoApi.doexactQuery("是是是","01");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "名片操作成功!");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

        Assert.assertEquals((JsonUtils.getjsondata(ret,"data")),"null");
    }

    @Test(description = "精确查询，不存在的纳税人名称,查询编码其它：02")
    public void exactQueryNotExistOthers(){
        String ret = DoApi.doexactQuery("不不不","02");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "名片操作成功!");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

        Assert.assertEquals((JsonUtils.getjsondata(ret,"data")),"null");
    }
}
