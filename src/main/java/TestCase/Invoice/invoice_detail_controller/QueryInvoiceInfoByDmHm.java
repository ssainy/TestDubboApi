package TestCase.Invoice.invoice_detail_controller;

import DataProvider.TestDataProvider;
import api.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.JsonUtils;
import utils.JsontoSql;

import java.util.ArrayList;
import java.util.List;

import static api.ExecuteJDBC.ExecuteSqlonMyinvoice;

/**
 * Created by Administrator on 2017/11/13.
 */
public class QueryInvoiceInfoByDmHm {
    String invoiceId;
    String invoicelist;
    String fp_dm;
    String fp_hm;
    List<String> id_list=new ArrayList<>();
    public void init(String id,String invoiceinfo_id,String fpdm,String fphm,String hjje,String kprq,String gmf_mc,String xsf_mc) {
        String ret_sql = DoSql.DoGetInvoiceTagById(id);
        if (!ret_sql.equals("[]")) {
            DoSql.DoInvoiceTagDelete(id);
        } else if (ret_sql.equals("[]")) {
            invoicelist = DoSql.DogetInvoiceData();
            if (invoicelist.equals("[]")) {
                id_list.add(id);
                String json_invoice_tag;
                json_invoice_tag = JsonUtils.SetJsonValue(Model.SqlModel.invoice_tag(), "id", id);
                json_invoice_tag = JsonUtils.SetJsonValue(json_invoice_tag, "invoiceinfo_id", invoiceinfo_id);
                json_invoice_tag = JsonUtils.SetJsonValue(json_invoice_tag, "uid", GetRedis.GetWeChatuid());
                ExecuteSqlonMyinvoice(JsontoSql.JsonToInserSql("invoice_tag", json_invoice_tag));
                String json_invoice_info;
                json_invoice_info = JsonUtils.SetJsonValue(Model.SqlModel.invoice_info(), "id", invoiceinfo_id);
                json_invoice_info = JsonUtils.SetJsonValue(json_invoice_info, "fpdm", fpdm);
                json_invoice_info = JsonUtils.SetJsonValue(json_invoice_info, "fphm", fphm);
                json_invoice_info = JsonUtils.SetJsonValue(json_invoice_info, "hjje", hjje);
                json_invoice_info = JsonUtils.SetJsonValue(json_invoice_info, "kprq", kprq);
                json_invoice_info = JsonUtils.SetJsonValue(json_invoice_info, "gmf_mc", gmf_mc);
                json_invoice_info = JsonUtils.SetJsonValue(json_invoice_info, "xsf_mc", xsf_mc);
                ExecuteSqlonMyinvoice(JsontoSql.JsonToInserSql("invoice_info", json_invoice_info));
                fp_dm = fpdm;
                fp_hm = fphm;
            } else {
                System.out.println(DoSql.DogetInvoiceData(GetRedis.GetWeChatuid()));
                fp_dm = JsonUtils.getJsonArrayData(DoSql.DogetInvoiceData(GetRedis.GetWeChatuid()), "fpdm");
                fp_hm = JsonUtils.getJsonArrayData(DoSql.DogetInvoiceData(GetRedis.GetWeChatuid()), "fphm");
            }
        }
    }
    /**
     *对发票代码和发票号码进行MD5加密处理,返回字符串数组toMD5[0] = fphm, toMD5[1] = fpdm
     */
    public static String[] getMd5FpdmFphm(String token, String fpdm, String fphm){
        String[] toMd5 = new String[2];
        String ret = DoApi.dogetMd5FpdmFphm(token,fpdm,fphm,"","");
//        System.out.println(ret);
        toMd5[0] = JsonUtils.getjsondata(ret,"data","fpdm");
        toMd5[1] = JsonUtils.getjsondata(ret,"data","fphm");
        return toMd5;
    }
    @Test(description = " 通过代码号码获取发票详情（queryInvoiceInfoByDmHm）",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void queryInvoiceInfoByDmHm(String id,String invoiceinfo_id,String fpdm,String fphm,String hjje,String kprq,String gmf_mc,String xsf_mc,String type,String description){
        String token = GetRedis.GetWeChatToken();
        init(id,invoiceinfo_id,fpdm,fphm,hjje,kprq,gmf_mc,xsf_mc);
        String[] toMd5 = getMd5FpdmFphm(token,fp_dm,fp_hm);
        String ret = DoApi.doqueryInvoiceInfoByDmHm(token,toMd5[0],toMd5[1],type);
        System.out.println(ret);
        Reporter.log("接口返回内容："+ret);
        System.out.println("fpdm: "+JsonUtils.getjsondata(ret,"data","invoiceInfo","fpdm"));
        System.out.println("fphm: "+JsonUtils.getjsondata(ret,"data","invoiceInfo","fphm"));
        System.out.println("hjje: "+JsonUtils.getjsondata(ret,"data","invoiceInfo","hjje"));
        System.out.println("kprq: "+JsonUtils.getjsondata(ret,"data","invoiceInfo","kprq"));
        System.out.println("gmf_mc: "+JsonUtils.getjsondata(ret,"data","invoiceInfo","gmf_mc"));
        System.out.println("xsf_mc: "+JsonUtils.getjsondata(ret,"data","invoiceInfo","xsf_mc"));
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");
        Assert.assertEquals(JsonUtils.getjsondata(ret,"data","invoiceInfo","fpdm"),fpdm);
        Assert.assertEquals(JsonUtils.getjsondata(ret,"data","invoiceInfo","fphm"),fphm);
        Assert.assertEquals(JsonUtils.getjsondata(ret,"data","invoiceInfo","hjje"),hjje);
        Assert.assertEquals(JsonUtils.getjsondata(ret,"data","invoiceInfo","kprq"),kprq);
        Assert.assertEquals(JsonUtils.getjsondata(ret,"data","invoiceInfo","gmf_mc"),gmf_mc);
        Assert.assertEquals(JsonUtils.getjsondata(ret,"data","invoiceInfo","xsf_mc"),xsf_mc);
    }
    @AfterClass(description = "清理测试生成的必要数据")
    public void cleardata(){
        if (!id_list.isEmpty()){
            for (int i = 0;i<id_list.size();i++){
                DoSql.DoInvoiceTagDelete(id_list.get(i));
            }
        }else {
            System.out.println("无需要清理的数据");
        }
    }
}
