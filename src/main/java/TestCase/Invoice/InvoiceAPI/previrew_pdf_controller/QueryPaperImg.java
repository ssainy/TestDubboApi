package TestCase.Invoice.InvoiceAPI.previrew_pdf_controller;

import api.DealResult;
import api.DoApi;
import api.GetProperties;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.JsonUtils;

import static api.DoSql.DoQueryPaperInvoice;
import api.GetRedis;

/**
 * Created by cch on 2017/12/12.
 */
public class QueryPaperImg {
    String paperImgList;
    String invoiceInfoId;
    @BeforeMethod
    public void setUp(){
        paperImgList=DoQueryPaperInvoice();
        if (paperImgList.equals("[]")){
            System.out.println("账号下没有图片！");
        }else {
            invoiceInfoId = JsonUtils.getRandomJsonArrayData(paperImgList, "invoice_info_id");
        }
    }
    @Test
    public void previewpdf(){
        String ret = DoApi.doqueryPaperImg_previrewPDF(invoiceInfoId, GetRedis.GetWeChatToken());
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}
