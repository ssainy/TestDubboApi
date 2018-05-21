package TestCase.Invoice.ocr_service_controller;

import DataProvider.TestDataProvider;
import api.*;
import dependence.TrafficInvoice;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by Administrator on 2017/12/13.
 */
public class TrafficInvoiceCollection {
   String uuid;
   String req_str;
   String ret;
   @Test(description = "交通发票归集", dataProvider = "testDataProvider",dataProviderClass = TestDataProvider.class)
    public void trafficInvoiceCollection(String flag, String type, String info,String Output_code, String Output_message,String description){
       String token = GetRedis.GetWeChatToken();
      if (flag.equals("0")) {
        //新建交通票实体类并在类初始化过程中调用OCR识别发票
         TrafficInvoice trafficInvoice = new TrafficInvoice(token,"",info,type,flag);
       //  trafficInvoice.TrafficInvoice(token,info,type,flag);
         //取得识别到的发票信息
          req_str = trafficInvoice.trafficInvoiceInstance();
         //取得发票的uuid
          uuid = trafficInvoice.getUuid();
          ret=DoApi.dotrafficInvoiceCollection(token,uuid,flag,req_str);
      }else {
         ret=DoApi.dotrafficInvoiceCollection(token,"",flag,info);
      }

       //交通票归集
       System.out.println("返回的数据是: "+ret);
       Reporter.log("接口返回结果："+ret);
       String invoiceId = JsonUtils.getjsondata(ret,"data","invoiceId");
       String sql_ret= DoSql.DoTrafficInvoiceCollection(invoiceId);
       System.out.println(sql_ret);
       Reporter.log("数据库返回结果："+sql_ret);
       Assert.assertTrue(sql_ret.contains(invoiceId));
      Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
       Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);




   }
}
