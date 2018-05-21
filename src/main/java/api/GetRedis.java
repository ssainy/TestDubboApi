/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import utils.JedisUtils_231;

/**
 *
 * @author thinkpad
 */
public class GetRedis {
    public static String GetWeChatToken(){
        //   return properties.getProperty("token");
        return  JedisUtils_231.getTokenByUid_wechat();
    }
    public static String GetPcToken(){
        //   return properties.getProperty("token");
        return  JedisUtils_231.getTokenByUid_pc();
    }
    public static String GetAPPToken(){
        //   return properties.getProperty("token");
        return  JedisUtils_231.getTokenByUid_app();
    }
    public static String GetWeChatuid(){
        return DoRedis.getWeChatUid();
    }
    
}
