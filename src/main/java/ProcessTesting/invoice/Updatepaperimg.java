package ProcessTesting.invoice;

import DataProvider.ProcessTesting_DataProvider;
import api.*;
import dependence.AddInvoice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.GetGenImg;

import java.util.Map;


/**
 * Created by Administrator on 2017/11/15.
 */
public class Updatepaperimg {
    String invoiceId;
    /**
     * 6.添加发票（qrcheck）+ 加密代码号码（getMd5FpdmFphm）+  获取发票详情（queryInvoiceInfoByDmHm）+获取纸质发票图片（queryPaperImg）+上传图片（uploadPaperImg）+OCR识别发票接口（invoiceRecognitionByOcr）+ 添加发票信息（qrcheck）+ 加密代码号码（getMd5FpdmFphm）+更新纸票图片（updatepaperimg）+  获取发票详情（queryInvoiceInfoByDmHm）
     * @param type
     * @param picture
     * @param Output_code
     * @param Output_message
     * @param description
     * 1.进行OCR归集添加发票，确保缓存中存在图片uuid
     * 2.取出发票的代码，号码，uuid，执行更新纸票图片的接口
     */
        @Test(description="6.添加发票（qrcheck）+ 加密代码号码（getMd5FpdmFphm）+  获取发票详情（queryInvoiceInfoByDmHm）+获取纸质发票图片（queryPaperImg）+上传图片（uploadPaperImg）+OCR识别发票接口（invoiceRecognitionByOcr）+ 添加发票信息（qrcheck）+ 加密代码号码（getMd5FpdmFphm）+更新纸票图片（updatepaperimg）+  获取发票详情（queryInvoiceInfoByDmHm）",dataProvider ="processTestingDataProvider",dataProviderClass= ProcessTesting_DataProvider.class)
        public void updatepaperimg(String info,String fpdm ,String fphm,String type, String picture,String Output_code,String Output_message,String description){
            String token = GetRedis.GetWeChatToken();
            String uid = GetRedis.GetWeChatuid();
            AddInvoice ai = new AddInvoice("1",info,"", token, uid, type);
            ai.addInvoice();
            invoiceId = ai.getInvoiceId();
            String ret = DoApi.doqueryPaperImg_previrewPDF(invoiceId, GetRedis.GetWeChatToken());
            System.out.println("获取纸票图片接口返回结果：" + ret);
            Reporter.log("获取纸票图片接口返回结果：" + ret);
            if (ret.contains("没有获取到纸质发票图片")) {
                Assert.assertEquals(DealResult.getResult_Message(ret), "没有获取到纸质发票图片");
                Assert.assertEquals(DealResult.getResult_Code(ret), "9999");
                String picToBase64Str = GetGenImg.GetImageStr("普票");
                String req_str = Data.uploadPaperImg(invoiceId, fpdm, fphm, picToBase64Str);
                System.out.println(req_str);
                ret = DoApi.douploadPaperImg_previrewPDF(token, req_str);
                System.out.println("上传纸票图片接口返回结果：" + ret);
                Reporter.log("上传纸票图片接口返回结果：" + ret);
                Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
                Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
            }
            Map picInfo = GetPicInfo.getPicInfo(picture,"0",token);
            String infoStr = GetStringFromMap.getString(picInfo,"infoStr");
            String uuid = GetStringFromMap.getString(picInfo,"uuid");
            ret = DoApi.doqrcheck("2",infoStr,token,uuid,"","0");
            Reporter.log("OCR识别添加发票返回结果："+ret);
            System.out.println(uuid+" : "+fpdm+" : "+fphm+" : "+token);
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
