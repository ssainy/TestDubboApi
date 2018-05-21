package TestCase.Invoice.invoice_detail_controller;

import api.DealResult;
import api.DoApi;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.JsonUtils;

import static api.DoSql.DoQueryPaperInvoice;
import api.GetRedis;

/**
 * Created by cch on 2018/2/28.
 */
public class GetInvoiceListById {
    String paperImgList;
    String invoiceInfoId;
    @BeforeMethod
    public void setUp(){
        paperImgList=DoQueryPaperInvoice();
        if (paperImgList.equals("[]")){
            Reporter.log("账号下没有图片！");
        }else {
            invoiceInfoId = JsonUtils.getRandomJsonArrayData(paperImgList, "invoice_info_id");
        }
    }
    @Test
    public void getDmHmByInvoiceId(){
        String ret= DoApi.dogetDmHmByInvoiceId(GetRedis.GetWeChatToken(),invoiceInfoId);
        Reporter.log("接口返回内容："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}
