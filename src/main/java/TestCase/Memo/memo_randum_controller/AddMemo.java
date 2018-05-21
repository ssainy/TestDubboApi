package TestCase.Memo.memo_randum_controller;

import api.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.JsonUtils;
/**
 * Created by cch on 2017/10/18.
 */
public class AddMemo {
    String invoicelist;
    String AddResult;
    String invoice_id;
    @BeforeClass
    public void getInvoiceList(){
        invoicelist = DoApi.dogetInvoiceList(GetRedis.GetWeChatToken(),"0");
        System.out.println("发票列表为："+invoicelist);
        String readInvoiceFrontList=JsonUtils.getjsondata(invoicelist,"data","readInvoiceFrontList");
        if(!readInvoiceFrontList.equals("[]")) {
            invoice_id = JsonUtils.getRandomJsonArrayData(readInvoiceFrontList, "invoiceinfo_id");
            System.out.println("添加记一记的发票id为：" + invoice_id);
        }
    }
    @Test(priority = 1,description = "添加记一记")
    public void addMemo(){
            String data = Data.addMemo();
            data = JsonUtils.jsondata(data, "token", GetRedis.GetWeChatToken());
            data = JsonUtils.jsondata(data, "invoiceinfo_id", invoice_id);
            AddResult = DoApi.doaddMemo(data);
            System.out.println(AddResult);
        Reporter.log("接口返回结果："+AddResult);
            Assert.assertEquals(DealResult.getResult_Message(AddResult), "处理成功");
            Assert.assertEquals(DealResult.getResult_Code(AddResult), "0000");

        }
    @Test(priority = 2,description = "获取记一记列表")
    public void getMemoList(){
        //0 增值税发票，1 交通票
       String ret=DoApi.dogetMemoList(GetRedis.GetWeChatToken(),invoice_id,"0");
       System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

        Assert.assertTrue(ret.contains(invoice_id));
    }
}
