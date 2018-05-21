package TestCase.MyinvoiceAccount.account_pc_controller;

import api.DoApi;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by cch on 2017/9/14.
 */
public class GenerateUuid {
@Test
    public  void generateUuid(){
    String ret = DoApi.dogenerateUuid();
    System.out.println(ret);
    Reporter.log("接口返回结果："+ret);

}
}
