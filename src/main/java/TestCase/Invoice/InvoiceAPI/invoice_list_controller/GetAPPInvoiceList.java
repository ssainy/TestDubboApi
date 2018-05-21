package TestCase.Invoice.InvoiceAPI.invoice_list_controller;

import api.DealResult;
import api.DoApi;
import api.DoSql;
import api.GetProperties;
import api.GetRedis;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/12/8.
 */
public class GetAPPInvoiceList {
    int count;
    @Test(description = "APP查询发票列表")
    public void getInvoiceList() {
        String ret = DoApi.dogetAPPInvoiceList(GetRedis.GetAPPToken());
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        //判断接口返回状态
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

        String invoiceListFrontLists = JsonUtils.getjsondata(ret, "data", "invoiceListFrontLists");
        //   System.out.println(notReadInvoiceFrontList);
        //接口返回的已读以及未读的数量
        count = JsonUtils.getJsonArrayCount(invoiceListFrontLists);
        System.out.println("count is :"+count);
        Reporter.log("接口返回数量："+count);
        String sqlInvoice= DoSql.DogetInvoiceData();
        // System.out.println("sqlInvoice is :"+sqlInvoice);
        String sqlInvoice_traffic= DoSql.DogetInvoiceData_traffic();
        //  System.out.println("sqlInvoice_traffic is :"+sqlInvoice_traffic);
        String sqlInvoice_qrinfo= DoSql.DogetInvoiceData_QrInvoice();
        //  System.out.println("sqlInvoice_qrinfo is :"+sqlInvoice_qrinfo);
        //数据库返回的此UID下的所有发票数量
        int sqlCount=JsonUtils.getJsonArrayCount(sqlInvoice)+JsonUtils.getJsonArrayCount(sqlInvoice_traffic)+JsonUtils.getJsonArrayCount(sqlInvoice_qrinfo);
        System.out.println("sqlCount is :"+sqlCount);
        Reporter.log("数据库返回数量："+sqlCount);
        Assert.assertEquals(count,sqlCount);
    }
}
