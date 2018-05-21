package api;

import org.testng.Reporter;

/**
 * Created by thinkpad on 2018-03-23.
 * c
 */
public class GetUserInfo {

    /*******************************
     * 通过用户类型获取用户token
     * @param UidType
     *         wechat:微信用户
     *         pc:pc端用户
     *         app：app用户
     * @return
     *****************************/
    public static String GetUserToken(String UidType){
        String token;
        switch(UidType){
            case "wechat":
                token = DoRedis.getUserToken(Integer.parseInt(GetRedisProperties.GetUserDB()),"uid:" + GetUserProperties.GetUserUid());
                Reporter.log("通过wechat方式获取到token为：" + token);
                System.out.println(token);
                return token;

            case "pc":
                token = DoRedis.getUserToken(Integer.parseInt(GetRedisProperties.GetUserDB()),"uid_pc:" + GetUserProperties.GetUserUid());
                Reporter.log("通过pc方式获取到token为：" + token);
                return token;

            case "app":
                token = DoRedis.getUserToken(Integer.parseInt(GetRedisProperties.GetUserDB()),"uid_app:" + GetUserProperties.GetUserUid());
                Reporter.log("通过app方式获取到token为：" + token);
                return token;

            default:
                return null;
        }
    }
}
