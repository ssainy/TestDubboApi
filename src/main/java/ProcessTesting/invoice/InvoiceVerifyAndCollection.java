package ProcessTesting.invoice;

import DataProvider.ProcessTesting_DataProvider;
import api.DealResult;
import api.DoApi;
import api.GetRedis;
import dependence.AddInvoice;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by cch on 2018/4/8.
 */
public class InvoiceVerifyAndCollection {
    String invoiceId;
    /**
     * 3.4.（ocr归集发票）+添加发票（qrcheck）+ 加密代码号码（getMd5FpdmFphm）+  获取发票详情（queryInvoiceInfoByDmHm）+查验并归集(invoiceVerifyAndCollection)
     * @param info
     * @param type
     * @param description
     */
    @Test(description = "3.4.（ocr归集发票）+添加发票（qrcheck）+ 加密代码号码（getMd5FpdmFphm）+  获取发票详情（queryInvoiceInfoByDmHm）+查验并归集(invoiceVerifyAndCollection)",dataProvider ="processTestingDataProvider",dataProviderClass= ProcessTesting_DataProvider.class)
    public void invoiceVerifyAndCollection(String flag,String reciptType,String type,String info,String picture,String Output_code,String Output_message,String cyjg_code,String message,String description) {
        String token = GetRedis.GetWeChatToken();
        String uid = GetRedis.GetWeChatuid();
        AddInvoice ai = new AddInvoice(flag,info,picture, token, uid, type);
        ai.addInvoice();
        invoiceId = ai.getInvoiceId();
        if (flag.equals("2")){
            info=ai.getInfo();
        }
        String ret = DoApi.doinvoiceVerifyAndCollection(info, GetRedis.GetWeChatToken(),type,reciptType);
        Reporter.log("查验并归集接口返回结果："+ret);
        System.out.println("查验并归集接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
        Assert.assertEquals(JsonUtils.getjsondata(ret,"data","cyjg_code"), cyjg_code);
        Assert.assertEquals(JsonUtils.getjsondata(ret,"data","message"), message);

    }
}
