package ProcessTesting.traffic;

import DataProvider.ProcessTesting_DataProvider;
import api.DealResult;
import api.DoApi;
import api.DoSql;
import api.GetRedis;
import dependence.TrafficInvoice;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.GetGenImg;
import utils.JsonUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cch on 2018/4/10.
 */
public class OCR_DeleteTrafficImg {
    /**
     * 18.ocr识别发票信息接口（invoiceRecognitionByOcr）+交通票归集（trafficInvoiceCollection）+getMd5FpdmFphm+交通票详情（queryTrafficInfo）+获取交通票图片（previewTrafficInfo）+下载交通票图片接口（downloadTrafficImg）
     */
    String uuid;
    String req_str;
    String ret;
    String invoiceId;
    @Test(description = "18.ocr识别发票信息接口（invoiceRecognitionByOcr）+交通票归集（trafficInvoiceCollection）+getMd5FpdmFphm+交通票详情（queryTrafficInfo）+获取交通票图片（previewTrafficInfo）+下载交通票图片接口（downloadTrafficImg）",dataProvider ="processTestingDataProvider",dataProviderClass= ProcessTesting_DataProvider.class)
    public void ocr_deleteTrafficImg(String type, String picture,String Output_code, String Output_message,String description) throws IOException {
        Map map = new HashMap<String,String>();
        String token = GetRedis.GetWeChatToken();
        TrafficInvoice trafficInvoice = new TrafficInvoice(token,picture,"",type,"0");
        trafficInvoice.addInvoice();
        String invoiceId=trafficInvoice.getInvoiceId();
        String picToBase64Str = GetGenImg.GetImageStr(picture);
        map.put("image",picToBase64Str);
        String sql_ret= DoSql.DoTrafficInvoiceCollection(invoiceId);
        String img_path = JsonUtils.getJsonArrayData(sql_ret,"img_path");
        System.out.println(sql_ret);
        System.out.println("img_path 是："+img_path);
        //下载交通票图片
        String imagePath = JsonUtils.getJsonArrayData(sql_ret,"img_path");
        String suffix = "png";
        byte[] pngbyte = DoApi.dodownloadTrafficImg(token,imagePath,suffix);
        FileOutputStream fos=null;
        try {
            fos = new FileOutputStream(new File("交通票下载.png"));
            fos.write(pngbyte);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //删除图片
        System.out.println("执行删除图片操作！");
        String ret = DoApi.dodeleteTrafficImg(token,invoiceId);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        sql_ret= DoSql.DoTrafficInvoiceCollection(invoiceId);
        System.out.println(sql_ret);
        Assert.assertTrue(!sql_ret.contains(img_path));
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);


    }
}
