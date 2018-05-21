package TestCase.Invoice.FileAPI.file_controller;

import TestCase.InvoiceCard.GetTitleInfoById;
import api.DealResult;
import api.DoApi;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/11/9.
 */
public class LoadImageByUUID {
    private static Log logger= LogFactory.getLog(LoadImageByUUID.class);
    @Test(description = "根据图片uuid下载",enabled = false)
    public void downLoadFile(){
        String ret = DoApi.doLoadImageByUUID("11111111");
        Reporter.log(ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "名片操作失败，请稍后重试!");
        Assert.assertEquals(DealResult.getResult_Code(ret), "9999");

    }
}
