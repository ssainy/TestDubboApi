package TestCase.Invoice.ocr_service_controller;

import api.DealResult;
import api.DoApi;
import api.GetRedis;
import DataProvider.TestDataProvider;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.GetGenImg;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/4.
 */

/**
 *  OCR 识别发票信息
 */
public class InvoiceRecognitionByOcr {
    @Test(description = "OCR识别发票信息",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void invoiceRecognitionByOcr(String type, String picture,String Output_code,String Output_message,String description){
        Map map = new HashMap<String,String>();
        String token = GetRedis.GetWeChatToken();
        String picToBase64Str = GetGenImg.GetImageStr(picture);
        //取出excel表里的图片名，和将其已转化的Base64编码放入map
        map.put("picture",picToBase64Str);
        String ret = null;
        try {
            System.out.println(map);
            ret = DoApi.doinvoiceRecognitionByOcr(token, type, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("返回的信息是："+ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
    }
}
