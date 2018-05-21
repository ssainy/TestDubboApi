package TestCase.Invoice.InvoiceAPI.previrew_pdf_controller;

import DataProvider.TestDataProvider;
import api.*;
import dependence.AddInvoice;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/12/12.
 */
public class DeletePaperImg {
    @Test(description = "删除纸票图片",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void deletePaperImg(String info,String type,String Output_code,String Output_message,String description){
        AddInvoice ai = new AddInvoice("1",info,"",GetRedis.GetWeChatToken(),GetRedis.GetWeChatuid(),type);
        ai.addInvoice();
        String invoiceId = ai.getInvoiceId();
        String paperImgList=DoSql.DoQueryPaperImgStatus_byInvoiceId(invoiceId);
            String invoiceInfoId = JsonUtils.getRandomJsonArrayData(paperImgList, "invoice_info_id");
            String ret = DoApi.dodeletePaperImg_previrewPDF(GetRedis.GetWeChatToken(), invoiceInfoId);
            System.out.println(ret);
            Reporter.log("接口返回结果："+ret);
            Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
            Assert.assertEquals(DealResult.getResult_Code(ret), "0000");
            String status=DoSql.DoQueryPaperImgStatus_byInvoiceId(invoiceInfoId);
            Assert.assertEquals(JsonUtils.getRandomJsonArrayData(status,"status"),"0");
    }
}
