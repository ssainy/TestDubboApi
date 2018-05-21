package TestCase.Invoice.invoice_detail_controller;

import DataProvider.MyInvoice_InvoiceDataProvider;
import api.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JDBC;
import utils.JsonUtils;
import utils.JsontoSql;

import static api.ExecuteJDBC.ExecuteSqlonMyinvoice;

/**
 * Created by cch on 2018/2/28.
 */
public class GetDmHmByInvoiceId {
    String invoiceList;
    String invoiceInfoId;
    public void init(String id,String invoiceinfo_id,String status,String fpdm,String fphm){
        invoiceList=DoSql.DogetInvoiceData();
        if (invoiceList.equals("[]")){
            System.out.println("账号下没有发票信息！");
            Reporter.log("账号下没有发票信息！");
            String json_invoice_tag;
            json_invoice_tag = JsonUtils.SetJsonValue(Model.SqlModel.invoice_tag(),"id",id);
            json_invoice_tag = JsonUtils.SetJsonValue(json_invoice_tag,"invoiceinfo_id",invoiceinfo_id);
            json_invoice_tag = JsonUtils.SetJsonValue(json_invoice_tag,"status",status);
            json_invoice_tag = JsonUtils.SetJsonValue(json_invoice_tag,"uid", GetUserProperties.GetUserUid());
            ExecuteSqlonMyinvoice(JsontoSql.JsonToInserSql("invoice_tag",json_invoice_tag));
            String json_invoice_info;
            json_invoice_info = JsonUtils.SetJsonValue(Model.SqlModel.invoice_info(),"id",invoiceinfo_id);
            json_invoice_info = JsonUtils.SetJsonValue(json_invoice_info,"fpdm",fpdm);
            json_invoice_info = JsonUtils.SetJsonValue(json_invoice_info,"fphm", fphm);
            ExecuteSqlonMyinvoice(JsontoSql.JsonToInserSql("invoice_info",json_invoice_info));
            Reporter.log("插入数据库发票信息，执行成功！");
            invoiceInfoId=invoiceinfo_id;
        }else {
            invoiceInfoId = JsonUtils.getRandomJsonArrayData(invoiceList, "invoiceinfo_id");
        }
    }
    @Test(description = "根据id获取发票代码号码",dataProvider ="InvoiceDataProvider",dataProviderClass= MyInvoice_InvoiceDataProvider.class)
    public void getDmHmByInvoiceId(String id,String invoiceinfo_id,String status,String fpdm,String fphm){
        init( id, invoiceinfo_id, status,fpdm,fphm);
        String ret=DoApi.dogetDmHmByInvoiceId(GetRedis.GetWeChatToken(),invoiceInfoId);
        Reporter.log("接口返回内容："+ret);
        System.out.println("接口返回内容："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");
    }
}
