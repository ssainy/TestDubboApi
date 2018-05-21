package TestCase.MyinvoiceAccount.account_pc_controller;

import api.DoApi;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/9/15.
 */
public class WeChatLoginSM2 {
    @Test(enabled = false)
    public void weChatLoginSM2(){
        String ret = DoApi.doPCWeChatLoginSM2("uuid","code");
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
    }
}
