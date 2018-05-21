package ProcessTesting.invoice;

import DataProvider.ProcessTesting_DataProvider;
import api.*;
import dependence.AddInvoice;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/11/15.
 */
public class UpdateQrInfo {
    /**
     * 12.添加错票 (qrcheck）+加密代码号码(getMd5FpdmFphm)+获取发票详情(queryInvoiceInfoByDmHm)+ 添加错票（qrcheck）+加密代码号码(getMd5FpdmFphm)+错票覆盖（updateQrInfo）+获取发票详情(queryInvoiceInfoByDmHm)
     * 1.添加一张错票信息，并取出发票id
     * 2.根据此发票id，用正确的发票信息，执行错票覆盖接口
     */
    @Test(description = "12.添加错票 (qrcheck）+加密代码号码(getMd5FpdmFphm)+获取发票详情(queryInvoiceInfoByDmHm)+ 添加错票（qrcheck）+加密代码号码(getMd5FpdmFphm)+错票覆盖（updateQrInfo）+获取发票详情(queryInvoiceInfoByDmHm)",dataProvider ="processTestingDataProvider",dataProviderClass= ProcessTesting_DataProvider.class)
    public void updateQrInfo(String WrongInfo,String RightInfo,String description){
        AddInvoice ai = new AddInvoice("1",WrongInfo,"",GetRedis.GetWeChatToken(),GetRedis.GetWeChatuid(),"1");
        ai.addInvoice();
        String invoiceId = ai.getInvoiceId();
        System.out.println(invoiceId);
        String ret = DoApi.doupdateQrInfo(RightInfo, invoiceId, "1", GetRedis.GetWeChatToken());
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}
