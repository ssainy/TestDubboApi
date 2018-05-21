package ProcessTesting.invoice;

import DataProvider.ProcessTesting_DataProvider;
import api.*;
import dependence.AddInvoice;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.GetGenImg;
import utils.JsonUtils;

/**
 * Created by cch on 2018/4/8.
 */
public class QueryPaperImg {
    String invoiceId;
    /**
     * 5.添加发票（qrcheck）+ 加密代码号码（getMd5FpdmFphm）+  获取发票详情（queryInvoiceInfoByDmHm）+获取纸质发票图片（queryPaperImg）+上传图片（uploadPaperImg）+删除图片（deletePaperImg）
     * @param info
     * @param type
     * @param description
     */
    @Test(description = "5.添加发票（qrcheck）+ 加密代码号码（getMd5FpdmFphm）+  获取发票详情（queryInvoiceInfoByDmHm）+获取纸质发票图片（queryPaperImg）+上传图片（uploadPaperImg）+删除图片（deletePaperImg）",dataProvider ="processTestingDataProvider",dataProviderClass= ProcessTesting_DataProvider.class)
    public void queryPaperImg(String type,String info,String fpdm,String fphm,String Output_code,String Output_message,String description) {
        String token = GetRedis.GetWeChatToken();
        String uid = GetRedis.GetWeChatuid();
        AddInvoice ai = new AddInvoice("1",info, "",token, uid, type);
        ai.addInvoice();
        invoiceId = ai.getInvoiceId();
        String ret = DoApi.doqueryPaperImg_previrewPDF(invoiceId, GetRedis.GetWeChatToken());
        System.out.println("请求纸票图片接口返回结果：" + ret);
        Reporter.log("请求纸票图片接口返回结果：" + ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "没有获取到纸质发票图片");
        Assert.assertEquals(DealResult.getResult_Code(ret), "9999");
        String picToBase64Str = GetGenImg.GetImageStr("普票");
        String req_str = Data.uploadPaperImg(invoiceId,fpdm,fphm,picToBase64Str);
        System.out.println(req_str);
         ret = DoApi.douploadPaperImg_previrewPDF(token,req_str);
        System.out.println("上传纸票图片接口返回结果："+ret);
        Reporter.log("上传纸票图片接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
         ret = DoApi.dodeletePaperImg_previrewPDF(GetRedis.GetWeChatToken(), invoiceId);
        System.out.println("删除纸票图片接口返回结果："+ret);
        Reporter.log("删除纸票图片接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
        String status= DoSql.DoQueryPaperImgStatus_byInvoiceId(invoiceId);
        Assert.assertEquals(JsonUtils.getRandomJsonArrayData(status,"status"),"0");
    }
}
