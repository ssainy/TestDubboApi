package TestCase.InvoiceCard;

import DataProvider.TestDataProvider;
import api.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.JsonUtils;

import java.text.ParseException;

/**
 * Created by cch on 2017/8/31.
 */

public class GetTitleInfoById {
    static  String RET;
    private static Log logger= LogFactory.getLog(GetTitleInfoById.class);
    static String addCardRet=null;
    /**
     * id 存在，不存在，为空
     */
    @BeforeMethod
    public void addCard(){
        addCardRet=DoApi.doaddCard(GetRedis.GetWeChatToken(),"1", Data.AddCard());
    }
    @Test(description = "显示名片详情" ,dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public static void getTitileInfoById(String id,String Output_code,String Output_message,String description) throws ParseException {
        try {
            id = JsonUtils.getjsondata(addCardRet, "data", "titleInfo", "id");
            String ret = DoApi.dogetTitleInfoById(id);
            System.out.println(ret);
            Reporter.log("接口返回结果："+ret);
            Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
            Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);

            RET = JsonUtils.getjsondata(ret, "data", "titleInfo");
            String sql_ret = DoSql.DoQuery_Titel_info(id);
            logger.info("接口返回内容是：" + RET);
            System.out.println("数据库返回内容是：" + sql_ret);

            Assert.assertEquals(JsonUtils.getjsondata(RET, "id"), JsonUtils.getJsonArrayData(sql_ret, "id"));
            Assert.assertEquals(JsonUtils.getjsondata(RET, "uid"), JsonUtils.getJsonArrayData(sql_ret, "uid"));
            Assert.assertEquals(JsonUtils.getjsondata(RET, "userType"), JsonUtils.getJsonArrayData(sql_ret, "user_type"));
            Assert.assertEquals(JsonUtils.getjsondata(RET, "unionId"), JsonUtils.getJsonArrayData(sql_ret, "union_id"));
            Assert.assertEquals(JsonUtils.getjsondata(RET, "openId"), JsonUtils.getJsonArrayData(sql_ret, "open_id"));
            Assert.assertEquals(JsonUtils.getjsondata(RET, "title_name"), JsonUtils.getJsonArrayData(sql_ret, "title_name"));
            Assert.assertEquals(JsonUtils.getjsondata(RET, "taxpayer_id"), JsonUtils.getJsonArrayData(sql_ret, "taxpayer_id"));
            Assert.assertEquals(JsonUtils.getjsondata(RET, "register_addr"), JsonUtils.getJsonArrayData(sql_ret, "register_addr"));
            Assert.assertEquals(JsonUtils.getjsondata(RET, "register_phone"), JsonUtils.getJsonArrayData(sql_ret, "register_phone"));
            Assert.assertEquals(JsonUtils.getjsondata(RET, "bank_name"), JsonUtils.getJsonArrayData(sql_ret, "bank_name"));
            Assert.assertEquals(JsonUtils.getjsondata(RET, "bank_account"), JsonUtils.getJsonArrayData(sql_ret, "bank_account"));
            Assert.assertEquals(JsonUtils.getjsondata(RET, "qrCodeVersion"), JsonUtils.getJsonArrayData(sql_ret, "qr_code_version"));
            Assert.assertEquals(JsonUtils.getjsondata(RET, "type"), JsonUtils.getJsonArrayData(sql_ret, "type"));
            Assert.assertEquals(JsonUtils.getjsondata(RET, "status"), JsonUtils.getJsonArrayData(sql_ret, "status"));
            Assert.assertEquals(JsonUtils.getjsondata(RET, "email"), JsonUtils.getJsonArrayData(sql_ret, "email"));
            Assert.assertEquals(JsonUtils.getjsondata(RET, "telephone"), JsonUtils.getJsonArrayData(sql_ret, "telephone"));
            Assert.assertEquals(JsonUtils.getjsondata(RET, "mpbs"), JsonUtils.getJsonArrayData(sql_ret, "mpbs"));
            Assert.assertEquals(JsonUtils.getjsondata(RET, "topFlag"), JsonUtils.getJsonArrayData(sql_ret, "top_flag"));
        }catch (Exception  e) {
            // TODO Auto-generated catch block
            System.out.println("异常信息为："+e);
            logger.info(RET);
            System.out.println("接口返回内容是：" + RET);
        }

    }
}