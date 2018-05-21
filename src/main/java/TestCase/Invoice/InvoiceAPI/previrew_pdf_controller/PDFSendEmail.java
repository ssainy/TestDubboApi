package TestCase.Invoice.InvoiceAPI.previrew_pdf_controller;

import api.DealResult;
import api.DoApi;
import api.DoSql;
import api.GetProperties;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/12/12.
 */
public class PDFSendEmail {
    //从invoice_info表随便取出id。发送指定邮箱
    String invoicelist;
    @BeforeClass
    public void getInvoiceList(){
        invoicelist = DoSql.DoQueryInvoiceId();
        System.out.println("发票列表为："+invoicelist);
        Reporter.log("发票列表："+invoicelist);
    }

    @Test
    public void pdfSendEmail(){
        if(!invoicelist.equals("[]")) {
        String id = JsonUtils.getRandomJsonArrayData(invoicelist, "id");
        String ret = DoApi.dosendEmail_previrewPDF(id,"507939109@qq.com");
        System.out.println(ret);
            Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}
}
