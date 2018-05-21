package TestCase.MyinvoiceAccount.verify_code_controller;

import api.DealResult;
import api.DoApi;
import api.GetRedis;
import DataProvider.TestDataProvider;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/9/27.
 */
public class Checkverifycodesend {
    @Test(description = "校验验证码-关联接口",dataProvider ="testDataProvider",dataProviderClass= TestDataProvider.class)
    public void checkverifycodesend(String account,String type,String Output_code,String Output_message,String description){
        String ret = DoApi.docheckverifycodesend(account, GetRedis.GetWeChatToken(),type);
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        if (DealResult.getResult_Code(ret).equals("1007")){
            System.out.println("手机号已经存在");
        }else {
            Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
            Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);

        }
    }
}
