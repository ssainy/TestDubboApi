package ProcessTesting.traffic;

import DataProvider.ProcessTesting_DataProvider;
import api.*;
import dependence.TrafficInvoice;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by Administrator on 2017/12/18.
 */
public class UpdateTrafficInvoiceReadStatus {
    String uuid;
    String req_str;
    String ret;

    /**
     * 15.16.ocr识别发票信息接口（invoiceRecognitionByOcr）+交通票归集（trafficInvoiceCollection）+getMd5FpdmFphm+交通票详情（queryTrafficInfo）+查看列表（getInvoiceList）+加密代码号码（getMd5FpdmFphm）+交通票详情（queryTrafficInfo）+更新交通票读取状态（updateTrafficInvoiceReadStatus）+查看列表（getInvoiceList）
     */
    @Test(description = "15.16.ocr识别发票信息接口（invoiceRecognitionByOcr）+交通票归集（trafficInvoiceCollection）+getMd5FpdmFphm+交通票详情（queryTrafficInfo）+查看列表（getInvoiceList）+加密代码号码（getMd5FpdmFphm）+交通票详情（queryTrafficInfo）+更新交通票读取状态（updateTrafficInvoiceReadStatus）+查看列表（getInvoiceList）",dataProvider ="processTestingDataProvider",dataProviderClass= ProcessTesting_DataProvider.class)
    public void updateTrafficInvoiceReadStatus(String flag, String type, String info,String picture,String Output_code, String Output_message,String description) {
        String token = GetRedis.GetWeChatToken();
        TrafficInvoice trafficInvoice = new TrafficInvoice(token,picture,info,type,flag);
        trafficInvoice.addInvoice();
        String invoiceId=trafficInvoice.getInvoiceId();
        String sql_ret= DoSql.DoTrafficInvoiceCollection(invoiceId);
        System.out.println(sql_ret);
        Assert.assertEquals(JsonUtils.getJsonArrayData(sql_ret,"read_status"),"0");
        String ret = DoApi.doupdateTrafficInvoiceReadStatus(token,invoiceId);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        sql_ret= DoSql.DoTrafficInvoiceCollection(invoiceId);
        System.out.println(sql_ret);
        Assert.assertEquals(JsonUtils.getJsonArrayData(sql_ret,"read_status"),"1");
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);



    }
}
