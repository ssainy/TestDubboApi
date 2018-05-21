package TestCase.MyinvoiceAccount.account_user_controller;

import DataProvider.TestDataProvider;
import api.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtils;

/**
 * Created by Administrator on 2017/9/27.
 */
public class GetBindingListInfo {
    @Test(description = "获取用户绑定信息列表", dataProvider = "testDataProvider", dataProviderClass = TestDataProvider.class)//接口描述信息
    public void getBindingListInfo(String Output_code, String Output_message,String description) { //用方法名作为sheet页名，设置Sheet里传入的参数列表
        String ret = DoApi.dogetBindingListInfo(GetRedis.GetWeChatToken());//从DoApi中调用do方法，把请求的参数按顺序定义出来
        System.out.println(ret);
        Reporter.log("接口返回结果："+ret);
        String retData = JsonUtils.getjsondata(ret,"data");
        int retDataSize = JsonUtils.getJsonArrayCount(retData);
        String[] retSubData = new String[retDataSize];
        String tmpUid;
        String tmpUserType;
        String tmpOpenId;
        String tmpUserName;
        String sql_ret;
        String openId;
        System.out.println("\n#######################################################################################################");
        for(int i=0; i<retDataSize; i++){
            retSubData[i] = JsonUtils.getJsonArrayData(retData,i);
            tmpUid = JsonUtils.getjsondata(retSubData[i],"uid");
            tmpUserType = JsonUtils.getjsondata(retSubData[i],"user_type");
            tmpOpenId = JsonUtils.getjsondata(retSubData[i], "open_id");
            tmpUserName = JsonUtils.getjsondata(retSubData[i],"user_name");
            System.out.println("uid: "+tmpUid+", "+"user_type: "+tmpUserType+", "+"open_id: "+tmpOpenId+", "+"user_name: "+tmpUserName+";");
            if("0".equals(tmpUserType)){
                sql_ret = DoSql.DoQueryBindUserInfo(tmpUid);
                openId = JsonUtils.getJsonArrayData(sql_ret,"account");
            }else{
                sql_ret = DoSql.DoDoQueryUserInfo(tmpUid,tmpUserType);
                openId = JsonUtils.getJsonArrayData(sql_ret, "open_id");
            }
            Assert.assertEquals(openId,tmpOpenId);
        }
        System.out.println("#######################################################################################################");
        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);

    }
}
