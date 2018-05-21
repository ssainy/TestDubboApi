package TestCase.Invoice.invoice_check_API.invoice_qr_check_controller;

import TestCase.Invoice.FileAPI.file_controller.LoadImageByUUID;
import api.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by cch on 2018/3/9.
 */
public class OcrCheckCollection {
    private static Log logger= LogFactory.getLog(OcrCheckCollection.class);
    @Test(description = "二维码校验",enabled = false)
    public void qrcheck(){
        String token = GetRedis.GetWeChatToken();
        String ret = DoApi.doocrcheckcollcetion(token,"0","");
        Reporter.log("接口返回内容："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "");
        Assert.assertEquals(DealResult.getResult_Code(ret), "");

    }
}
