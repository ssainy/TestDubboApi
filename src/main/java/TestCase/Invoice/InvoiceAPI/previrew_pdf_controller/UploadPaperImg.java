package TestCase.Invoice.InvoiceAPI.previrew_pdf_controller;
import api.Data;
import api.DealResult;
import api.DoApi;
import api.GetProperties;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.JsonUtils;

import static api.DoSql.DoQueryPaperInvoice;
import api.GetRedis;

/**
 * Created by cch on 2017/12/12.
 */
public class UploadPaperImg {
    String paperImgList;
    String invoiceInfoId;
    @BeforeMethod
    public void setUp(){
         paperImgList=DoQueryPaperInvoice();
        if (paperImgList.equals("[]")){
            System.out.println("账号下没有图片！");
        }else {
            invoiceInfoId = JsonUtils.getRandomJsonArrayData(paperImgList, "invoice_info_id");
        }
    }
    @Test
    public void uploadPaperImg(){
            String req_str = Data.uploadPaperImg(invoiceInfoId,"","","");
            req_str = JsonUtils.jsondata(req_str, "invoice_info_id", invoiceInfoId);
            req_str = JsonUtils.jsondata(req_str, "uid", GetRedis.GetWeChatuid());
            System.out.println(req_str);
            String ret = DoApi.douploadPaperImg_previrewPDF(GetRedis.GetWeChatToken(),req_str);
            System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
            Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

        }

    @Test
    public void getImgs(){
        //根据id获取发票代码号码以及校验码
        String ret1=DoApi.dogetInvoiceListById(GetRedis.GetWeChatToken(),invoiceInfoId);
        System.out.println(ret1);
        String invoiceInfos=JsonUtils.getjsondata(ret1,"data","invoiceInfos");
        String fpdm=JsonUtils.getJsonArrayData(invoiceInfos,"fpdm");
        String fphm=JsonUtils.getJsonArrayData(invoiceInfos,"fphm");
        String jym=JsonUtils.getJsonArrayData(invoiceInfos,"jym");
        String ret = DoApi.dogetImgs(fpdm,fphm,jym);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret), "处理成功");
        Assert.assertEquals(DealResult.getResult_Code(ret), "0000");

    }
}
