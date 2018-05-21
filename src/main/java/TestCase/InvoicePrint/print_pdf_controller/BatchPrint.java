package TestCase.InvoicePrint.print_pdf_controller;

import api.DealResult;
import api.DoApi;
import api.GetProperties;
import api.GetRedis;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/12/15.
 */
public class BatchPrint {
    @Test
    public void batchPrint(){
        String ret = DoApi.dobatchPrint(GetRedis.GetWeChatToken(),"[\"940138022022807552,0\",\"201712131833323675361076747264,0\",\"201712131833323675361075125248,0\",\"894ce6c7ddb8455aad0311ddb0333f38,0\"]");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "批量获取pdf成功!");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}
