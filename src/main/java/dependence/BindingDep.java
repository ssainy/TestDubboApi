package dependence;

import api.DoApi;
import api.GetProperties;
import api.GetRedis;
import org.testng.annotations.Test;
import utils.JedisUtils_232;

/**
 * Created by Administrator on 2017/9/29.
 */

public class BindingDep {
    /**
     * 发短信
     */
    public static void sendMsg(String account, String type, String bindType){
        //type: 发送短信的类型(0:注册;1:密码修改;2:绑定,3：关联)
        //bindType: 绑定类型（1：新绑定手机号；2：重新绑定手机号）默认为1
        String ret = DoApi.dosendMsg(account, GetRedis.GetWeChatToken(),type,bindType);
        System.out.println(ret);
    }

    public static String sendMsg(String account, String type){
        String ret = DoApi.dosendMsg(account, type);
        System.out.println(ret);
        return ret;
    }

    /**
     * 获取存在redis中的短信验证码
     * (0:注册;1:密码修改;2:绑定,3：关联)
     */
    public static String getSmsCode(String account,String type) {
        String typeStr=null;
        int typeValue = Integer.parseInt(type);
        switch(typeValue) {
            case 0:
                typeStr = "regist";
                break;
            case 1:
                typeStr = "modify";
                break;
            case 2:
                typeStr = "binding";
                break;
            case 3:
                typeStr = "associate";
                break;
        }
        String code = JedisUtils_232.get(5,"verifycode:"+account+":"+typeStr);
        return code;
    }
    /**
     * 发送短新和得到短信校验码
     */
    public static String sendMsgAndgetSmsCode(String account,String type,String bindType){
        sendMsg(account,type,bindType);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (getSmsCode(account,type));
    }

    /**
     * 发送短新和得到短信校验码 无bindType参数
     */
    public static String sendMsgAndgetSmsCode(String account,String type){
        sendMsg(account,type);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (getSmsCode(account,type));
    }

        @Test(description = "测试")
        public void testBindingDep(){
        System.out.println(sendMsgAndgetSmsCode("15611726431","2","1"));
        System.out.println(getSmsCode("15611726431","2"));

  }

        @Test
        public void testBindingDep_withoutBindType(){
            System.out.println(sendMsgAndgetSmsCode("15611726431","2"));
        //    System.out.println(getSmsCode("18335834703","0"));
        }
}
