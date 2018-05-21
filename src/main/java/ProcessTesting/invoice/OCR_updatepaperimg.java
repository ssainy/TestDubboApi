package ProcessTesting.invoice;

import DataProvider.ProcessTesting_DataProvider;
import api.*;
import dependence.AddInvoice;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.GetGenImg;

import java.util.Map;

/**
 * Created by cch on 2018/4/10.
 */
public class OCR_updatepaperimg {
    String invoiceId;
    /**
     * 10.ocr识别发票信息接口（invoiceRecognitionByOcr）+ 添加发票（qrcheck）+加密代码号码（getMd5FpdmFphm）+  获取发票详情（queryInvoiceInfoByDmHm）+OCR识别发票接口（invoiceRecognitionByOcr）+ 添加发票信息（qrcheck）+ 加密代码号码（getMd5FpdmFphm）+更新纸票图片（updatepaperimg）+  获取发票详情（queryInvoiceInfoByDmHm）
     * @param type
     * @param picture
     * @param Output_code
     * @param Output_message
     * @param description
     * 1.进行OCR归集添加发票，确保缓存中存在图片uuid
     * 2.取出发票的代码，号码，uuid，执行更新纸票图片的接口
     */
    @Test(description="10.ocr识别发票信息接口（invoiceRecognitionByOcr）+ 添加发票（qrcheck）+加密代码号码（getMd5FpdmFphm）+  获取发票详情（queryInvoiceInfoByDmHm）+OCR识别发票接口（invoiceRecognitionByOcr）+ 添加发票信息（qrcheck）+ 加密代码号码（getMd5FpdmFphm）+更新纸票图片（updatepaperimg）+  获取发票详情（queryInvoiceInfoByDmHm）",dataProvider ="processTestingDataProvider",dataProviderClass= ProcessTesting_DataProvider.class)
    public void ocr_updatepaperimg(String picture,String type, String Output_code,String Output_message,String description){
        String token = GetRedis.GetWeChatToken();
        String uid = GetRedis.GetWeChatuid();
        AddInvoice ai = new AddInvoice("2","",picture, token, uid, type);
        ai.addInvoice();
        invoiceId = ai.getInvoiceId();
        String info=ai.getInfo();
        String[] Info = info.split(",");
        String fpdm=Info[2];
        String fphm=Info[3];
        String ret = DoApi.doqueryPaperImg_previrewPDF(invoiceId, GetRedis.GetWeChatToken());
        System.out.println("获取纸票图片接口返回结果：" + ret);
        Reporter.log("获取纸票图片接口返回结果：" + ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
        ai.addInvoice();
        String uuid=ai.getPaperImgUuid();
        ret = DoApi.doupdatepaperimg(uuid,fpdm,fphm,token);
        System.out.println("更新发票图片返回结果："+ret);
        Reporter.log("更新发票图片返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
        String fpdm_md5=ai.getMd5FPDM();
        String fphm_md5=ai.getMd5FPHM();
        ret = DoApi.doqueryInvoiceInfoByDmHm(token, fpdm_md5, fphm_md5, type);
        System.out.println("获取发票详情："+ret);
        Reporter.log("获取发票详情："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
    }
}
