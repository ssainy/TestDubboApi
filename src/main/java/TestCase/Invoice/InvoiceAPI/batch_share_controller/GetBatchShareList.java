package TestCase.Invoice.InvoiceAPI.batch_share_controller;

import api.DealResult;
import api.DoApi;
import api.GetRedis;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by cch on 2018/3/9.
 */
public class GetBatchShareList {
    private static Log logger= LogFactory.getLog(GetBatchShareList.class);

    /**
     * 获取批量分享历史
     * @throws IOException
     */
    @Test(description="获取批量分享历史")
    public void getBatchShareList() throws IOException {
        String ret = DoApi.dogetBatchShareList(GetRedis.GetWeChatToken(),"001","1","10","0");
        logger.info(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");
    }
}
