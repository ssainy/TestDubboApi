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
public class DeleteTrafficInvoice {
    /**
     * 13.14.ocr识别发票信息接口（invoiceRecognitionByOcr）+交通票归集（trafficInvoiceCollection）+getMd5FpdmFphm+交通票详情（queryTrafficInfo）+ 删除交通票（deleteTrafficInvoice）+ 查看列表（getInvoiceList）
     */
    String uuid;
    String req_str;
    String ret;
    @Test(description = "13.14.ocr识别发票信息接口（invoiceRecognitionByOcr）+交通票归集（trafficInvoiceCollection）+getMd5FpdmFphm+交通票详情（queryTrafficInfo）+ 删除交通票（deleteTrafficInvoice）+ 查看列表（getInvoiceList)",dataProvider ="processTestingDataProvider",dataProviderClass= ProcessTesting_DataProvider.class)
    public void deleteTrafficInvoice(String flag, String type, String picture,String info,String Output_code, String Output_message,String description){
        String token = GetRedis.GetWeChatToken();
        //归集标识(0-OCR归集,1-手动录入)
            TrafficInvoice trafficInvoice = new TrafficInvoice(token,picture,info,type,flag);
            trafficInvoice.addInvoice();
        String invoiceId=trafficInvoice.getInvoiceId();
        System.out.println("即将删除的发票id为："+invoiceId);
        Reporter.log("即将删除的发票id为："+invoiceId);
        String ret = DoApi.dodeleteTrafficInvoice(token,invoiceId);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        String sql_ret= DoSql.DoTrafficInvoiceCollection(invoiceId);
        System.out.println(sql_ret);
        Assert.assertEquals(JsonUtils.getJsonArrayData(sql_ret,"status"),"0");
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
        Assert.assertFalse(DoApi.dogetInvoiceList(token,"0").contains(invoiceId));
    }
}
