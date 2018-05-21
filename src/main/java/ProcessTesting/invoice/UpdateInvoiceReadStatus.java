package ProcessTesting.invoice;

import DataProvider.ProcessTesting_DataProvider;
import api.*;
import dependence.AddInvoice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by Administrator on 2017/11/13.
 */
public class UpdateInvoiceReadStatus {
    /**
     * 7.8.添加发票（qrcheck）+ 加密代码号码（getMd5FpdmFphm）+  获取发票详情（queryInvoiceInfoByDmHm）+查看列表（getInvoiceList）+加密代码号码（getMd5FpdmFphm）+获取发票详情（queryInvoiceInfoByDmHm）+ 更新读取状态（updateInvoiceReadStatus）+查看列表（getInvoiceList）
     * 1.添加发票信息，并取出发票的id
     * 2.根据发票id更新读取状态
     * 3.比较接口返回以及数据库中的读取状态都为1
     */
    @Test(description="7.8.添加发票（qrcheck）+ 加密代码号码（getMd5FpdmFphm）+  获取发票详情（queryInvoiceInfoByDmHm）+查看列表（getInvoiceList）+加密代码号码（getMd5FpdmFphm）+获取发票详情（queryInvoiceInfoByDmHm）+ 更新读取状态（updateInvoiceReadStatus）+查看列表（getInvoiceList）",dataProvider ="processTestingDataProvider",dataProviderClass= ProcessTesting_DataProvider.class)
    public static void updateInvoiceReadStatus(String flag,String fpdm,String fphm,String type, String info,String picture,String Output_code,String Output_message,String description){
        String token = GetRedis.GetWeChatToken();
        String uid = GetRedis.GetWeChatuid();
        AddInvoice ai = new AddInvoice(flag,info,picture,token,uid,type);
        ai.addInvoice();
        String invoiceId = ai.getInvoiceId();
        if (flag.equals("2")){
        info=ai.getInfo();
        String[] Info = info.split(",");
        fpdm=Info[2];
        fphm=Info[3];
        }
        String ret = DoApi.doupdateInvoiceReadStatus(token,fpdm,fphm);
        Reporter.log(ret);
        String sql_ret= DoSql.DoQuery_Update_Invoice_Read_Status(invoiceId);
        Reporter.log("数据库返回返回："+sql_ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
        Assert.assertEquals(DealResult.getResult_Data(ret),"1");
        Assert.assertEquals(JsonUtils.getJsonArrayData(sql_ret,"status"),"1");
        ret=DoApi.dogetInvoiceList(token,"0");
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
    }

}
