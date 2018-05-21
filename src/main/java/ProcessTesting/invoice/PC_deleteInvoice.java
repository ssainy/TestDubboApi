package ProcessTesting.invoice;

import DataProvider.ProcessTesting_DataProvider;
import api.DealResult;
import api.DoApi;
import api.DoSql;
import api.GetRedis;
import dependence.AddInvoice;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2018/4/10.
 */
public class PC_deleteInvoice {
    String invoicelist;
    String invoiceId;

    /**
     * 11.添加发票（qrcheck）+  获取发票详情（queryInvoiceInfoByDmhmAtPc）+ 删除发票（deleteInvoice）+ 查看列表（getInvoiceList）
     * @param info
     * @param type
     * @param description
     * 1.添加发票信息，qrcheck只能返回发票代码号码
     * 2.根据代码号码获取发票详情，取出发票id
     * 3.根据取出的id执行接口，查询发票详情
     * 2.数据库中当前用户下包含此发票id
     * 3.根据id，执行删除发票接口
     * 4.数据库中当前用户下不包含此发票id
     *
     */
    @Test(description = "11.添加发票（qrcheck）+  获取发票详情（queryInvoiceInfoByDmhmAtPc）+ 删除发票（deleteInvoice）+ 查看列表（getInvoiceList）",dataProvider ="processTestingDataProvider",dataProviderClass= ProcessTesting_DataProvider.class)
    public void pc_deleteInvoice(String flag,String info,String type,String description){
        String token = GetRedis.GetWeChatToken();
        String uid = GetRedis.GetWeChatuid();
        AddInvoice ai = new AddInvoice(flag,info,"",token,uid,type);
        ai.pc_addInvoice();
        invoiceId = ai.getInvoiceId();
        String sqlInvoiceList = DoSql.DoShowInvoiceIdByUid();
        System.out.println(sqlInvoiceList);
        Reporter.log("数据库返回结果："+sqlInvoiceList);
        Assert.assertTrue(sqlInvoiceList.contains(invoiceId));
        //type判断是否为错票，0为正确，1为错票
        String ret = DoApi.dodeleteInvoice_Invoice(invoiceId, token, type);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");
        Assert.assertFalse(DoApi.dogetInvoiceList(token,"0").contains(invoiceId));
        sqlInvoiceList = DoSql.DoShowInvoiceIdByUid();
        System.out.println(sqlInvoiceList);
        Reporter.log("数据库返回结果："+sqlInvoiceList);
        Assert.assertFalse(sqlInvoiceList.contains(invoiceId));
    }
}
