package TestCase.Invoice.InvoiceAPI.invoice_list_controller;

import api.DealResult;
import api.DoApi;
import api.DoSql;
import api.GetProperties;
import api.GetRedis;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2017/12/12.
 */
public class InvoiceList {
    private static Log logger= LogFactory.getLog(InvoiceList.class);
    int count;
    @Test(description = "票夹查询发票列表(新增获取发票列表返回接口增加已读和未读发票列表信息")
    public void getInvoiceList() {
        String ret = DoApi.doInvoiceList(GetRedis.GetWeChatToken());
        Reporter.log("接口返回结果："+ret);
        System.out.println("ret is :"+ret);
        //判断接口返回状态
        try {
            Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

        String invoiceListFrontLists = JsonUtils.getjsondata(ret, "data", "readInvoiceFrontList");
        //接口返回的已读以及未读的数量
        count = JsonUtils.getJsonArrayCount(invoiceListFrontLists);
        logger.info("count is :"+count);
        Reporter.log("接口返回数量："+count);
        String sqlInvoice= DoSql.DogetInvoiceData();
        String sqlInvoice_traffic= DoSql.DogetInvoiceData_traffic();
        String sqlInvoice_qrinfo= DoSql.DogetInvoiceData_QrInvoice();
        //数据库返回的此UID下的所有发票数量
        int sqlCount=JsonUtils.getJsonArrayCount(sqlInvoice)+JsonUtils.getJsonArrayCount(sqlInvoice_traffic)+JsonUtils.getJsonArrayCount(sqlInvoice_qrinfo);
        System.out.println("sqlCount is :"+sqlCount);
        Reporter.log("sqlCount is :"+sqlCount);
        Assert.assertEquals(count,sqlCount);
        }catch (Exception e){
            logger.info("报错信息："+ret);
        }
    }
}
