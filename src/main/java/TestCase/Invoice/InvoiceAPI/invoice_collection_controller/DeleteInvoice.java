package TestCase.Invoice.InvoiceAPI.invoice_collection_controller;

import DataProvider.TestDataProvider;
import api.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;
import utils.JsontoSql;

import static api.DoSql.DogetInvoiceData;
import static api.ExecuteJDBC.ExecuteSqlonMyinvoice;

/**
 * Created by cch on 2017/11/14.
 */
public class DeleteInvoice {
    String invoicelist;
    String invoiceId;
    public void init(String id,String invoiceinfo_id){
        invoicelist=DoSql.DogetInvoiceData();
        if (invoicelist.equals("[]")){
            Reporter.log("账号下没有发票信息！");
            String json_invoice_tag;
            json_invoice_tag = JsonUtils.SetJsonValue(Model.SqlModel.invoice_tag(),"id",id);
            json_invoice_tag = JsonUtils.SetJsonValue(json_invoice_tag,"invoiceinfo_id",invoiceinfo_id);
            json_invoice_tag = JsonUtils.SetJsonValue(json_invoice_tag,"uid", GetRedis.GetWeChatuid());
            ExecuteSqlonMyinvoice(JsontoSql.JsonToInserSql("invoice_tag",json_invoice_tag));
            invoiceId=invoiceinfo_id;
        }else {
            invoiceId = JsonUtils.getRandomJsonArrayData(invoicelist, "invoiceinfo_id");
        }
    }
    @Test(description = "删除发票",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void deleteInvoice(String id,String invoiceinfo_id,String type,String description){
        String token = GetRedis.GetWeChatToken();
        init(id,invoiceinfo_id);
        String sqlInvoiceList = DoSql.DoShowInvoiceIdByUid();
        System.out.println(sqlInvoiceList);
        Reporter.log("数据库返回结果："+sqlInvoiceList);
        Assert.assertTrue(sqlInvoiceList.contains(invoiceId));
        //type判断是否为错票，0为正确，1为错票
        String ret = DoApi.dodeleteInvoice_Invoice(invoiceId, token, type);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");
        Assert.assertFalse(DoApi.dogetInvoiceList(token,"0").contains(invoiceId));
        sqlInvoiceList = DoSql.DoShowInvoiceIdByUid();
        System.out.println(sqlInvoiceList);
        Reporter.log("数据库返回结果："+sqlInvoiceList);
        Assert.assertFalse(sqlInvoiceList.contains(invoiceId));


    }
}
