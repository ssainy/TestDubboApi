package TestCase.Invoice.InvoiceAPI.batch_share_controller;

import api.DealResult;
import api.DoApi;
import api.DoSql;
import api.GetProperties;
import api.GetRedis;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

import java.io.IOException;

/**
 * Created by cch on 2018/3/9.
 */
public class SaveShareInvoice {
    @Test(description="分享保存数据")
    /**
     * 1.获得数据库中所有已分享id
     * 2.根据id执行分享保存数据接口
     */
    public void getBatchShareList() throws IOException {
        String bathShareIdList = DoSql.DoGetBathShareId();
        String bathShareId= JsonUtils.getRandomJsonArrayData(bathShareIdList,"id");
        String ret = DoApi.dosaveShareInvoice(GetRedis.GetWeChatToken(),"001",bathShareId,"0");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}
