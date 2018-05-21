package api;

import utils.*;

/**
 * Created by Administrator on 2017/11/7.
 */
public class DoRedis {
    /**
     * 删除Redis里的用户信息
     */
    public static void doDelUserInfo(){
        int db=Integer.parseInt(GetRedisProperties.GetUserDB());
        String phone = GetProperties.GetPhone();
        String token = GetRedis.GetWeChatToken();
        String uid = GetRedis.GetWeChatuid();
        String openid = GetProperties.GetOpenId();
        String unionid = GetProperties.GetUnionId();
       /* String appOpenID=GetProperties.GetAPPOpenId();
        if(!StringUtil.isEmpty(appOpenID)){
            JedisUtils_231.delMatchedKey(5,appOpenID);
            JedisUtils_232.delMatchedKey(5,appOpenID);
        }*/
        if(!StringUtil.isEmpty(phone)){
            JedisUtils_231.delMatchedKey(db,phone);
            JedisUtils_232.delMatchedKey(db,phone);
        }
        JedisUtils_231.delMatchedKey(db,token);
        JedisUtils_231.delMatchedKey(db,uid);
        JedisUtils_231.delMatchedKey(db,openid);
        JedisUtils_231.delMatchedKey(db,unionid);

        JedisUtils_232.delMatchedKey(db,token);
        JedisUtils_232.delMatchedKey(db,uid);
        JedisUtils_232.delMatchedKey(db,openid);
        JedisUtils_232.delMatchedKey(db,unionid);

    }


    public static String getWeChatUid(){
        String uid=DoSql.DoGetUidByUnionId();
        if (uid.equals("[]")){
            uid=DoSql.DoGetUidByPhone();
        }
        uid= JsonUtils.getJsonArrayData(uid,"uid");
        System.out.println("uid is :"+uid);
        return uid;
    }
    public static String doGetUidfromRedis(){
        return JedisUtils_231.getUidfromRedis();
    }

    public static String getUserToken(int db, String key){
        String token = null;
        RedisUtils Redis = new RedisUtils();
        Redis.init(GetRedisProperties.GetReidsHost(),Integer.parseInt(GetRedisProperties.GetReidsPort()),GetRedisProperties.GetReidsPassWord());
        token = Redis.get(db,key);
        return token;
    }
}
