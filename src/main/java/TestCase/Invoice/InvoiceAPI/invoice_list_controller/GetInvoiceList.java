package TestCase.Invoice.InvoiceAPI.invoice_list_controller;

import TestCase.Invoice.InvoiceAPI.batch_share_controller.GetBatchShareList;
import api.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;
import utils.StringUtil;

/**
 * Created by cch on 2017/11/29.
 */
public class GetInvoiceList {
    private static Log logger= LogFactory.getLog(GetInvoiceList.class);

    int count;
    String sqlInvoice;
    String sqlInvoice_traffic;
    String sqlInvoice_qrinfo;
    int sqlCount;
    @Test()
    public void getInvoiceList() {
        String ret = DoApi.doInvoiceList(GetRedis.GetWeChatToken());
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
            //判断接口返回状态
            Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
            Assert.assertEquals(DealResult.getResult_Code(ret), "0000");
            String notReadInvoiceFrontList = JsonUtils.getjsondata(ret, "data", "notReadInvoiceFrontList");
            //   System.out.println(notReadInvoiceFrontList);
            String readInvoiceFrontList = JsonUtils.getjsondata(ret, "data", "readInvoiceFrontList");
            //   System.out.println(readInvoiceFrontList);
            //接口返回的已读以及未读的数量
            count = JsonUtils.getJsonArrayCount(notReadInvoiceFrontList) + JsonUtils.getJsonArrayCount(readInvoiceFrontList);
            System.out.println("count is :" + count);
            Reporter.log("接口返回数量："+count);
            sqlInvoice = DoSql.DogetInvoiceData();
            // System.out.println("sqlInvoice is :"+sqlInvoice);
            sqlInvoice_traffic = DoSql.DogetInvoiceData_traffic();
            // System.out.println("sqlInvoice_traffic is :"+sqlInvoice_traffic);
            sqlInvoice_qrinfo = DoSql.DogetInvoiceData_QrInvoice();
            //  System.out.println("sqlInvoice_qrinfo is :"+sqlInvoice_qrinfo);
            //数据库返回的此UID下的所有发票数量
            int sqlCount = JsonUtils.getJsonArrayCount(sqlInvoice) + JsonUtils.getJsonArrayCount(sqlInvoice_traffic) + JsonUtils.getJsonArrayCount(sqlInvoice_qrinfo);
            System.out.println("sqlCount is :" + sqlCount);
            Reporter.log("数据库返回数量："+sqlCount);
            Assert.assertEquals(count, sqlCount);
    }
}
