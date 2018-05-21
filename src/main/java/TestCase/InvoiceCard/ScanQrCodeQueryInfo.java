package TestCase.InvoiceCard;

import api.DealResult;
import api.DoApi;
import api.GetRedis;
import DataProvider.TestDataProvider;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
/**
 * Created by cch on 2017/8/31.
 */
public class ScanQrCodeQueryInfo {
    /**
     * 扫描发票信息需分发票类型（普票，专票，电票，红票）
     * 扫描名片二维码，存在，不存在，其他二维码
     * @param qrinfo
     * @param rtCode
     * @param Output_code
     * @param Output_message
     * @param description
     */
    @Test(description="ScanQrCodeQueryInfo - 通过扫描二维码获取名片信息（分享的名片及专票二维码）",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void scanQrCodeQueryInfo(String qrinfo, String rtCode, String Output_code, String Output_message ,String description){
        try {
            qrinfo=URLEncoder.encode(qrinfo,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String ret = DoApi.doscanQrCodeQueryInfo(qrinfo,GetRedis.GetWeChatToken());
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        Assert.assertEquals(DealResult.getResult_Message(ret),Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
        Assert.assertEquals(DealResult.get_rtCode(ret),rtCode);
    }
}
