package utils;

import api.GetProperties;
import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by thinkpad on 2017/6/27.
 */
public class JedisUtils_232 {

    //@Override
    public static String get(int db, String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.select(db);
            if (jedis.exists(key)) {
                value = jedis.get(key);
                value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
            }
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return value;
    }
    public static void delMatchedKey(int db, String key) {
        Jedis jedis = null;
        Set matchedKeys = new HashSet();
        String matchRule = "*"+key+"*";
        String strTemp=null;
        try{
            jedis = getResource();
            jedis.select(db);
            matchedKeys = jedis.keys(matchRule);
            if(matchedKeys.isEmpty()){
                System.out.println("未找到匹配的Key!");
            }
            Iterator<String> it = matchedKeys.iterator();
            while (it.hasNext()){
                strTemp = it.next();
                jedis.del(strTemp);
                System.out.println("Key："+strTemp+"已被删除！");
            }
        }catch(Exception e){

        }finally {
            returnBrokenResource(jedis);
        }
    }

    public static String getTokenfromRedis(){
        /**
         * 1) 遍历Redis中的所有包含Token 的key,每一步执行一次get，并判断返回的字符串里面是否包含传入的open_id 如果包含则返回此key名。
         * 2) 去掉token:，取出token并返回。
         */
        String token = null;
        String openId = GetProperties.GetOpenId();
        String unionId = GetProperties.GetUnionId();
        Jedis jedis = null;
        Set matchedKeys = new HashSet();
        String matchRule = "*"+"token"+"*";
        String strTemp=null;
        String value=null;
        Set<String> tokenSet = new HashSet();
        try{
            jedis = getResource();
            matchedKeys = jedis.keys(matchRule);
            if(matchedKeys.isEmpty()){
                System.out.println("未找到匹配的Key!");
            }
            Iterator<String> it = matchedKeys.iterator();
            while (it.hasNext()){
                strTemp = it.next();
                //处理过程
                value=jedis.get(strTemp);
                if(value.contains(openId) || value.contains(unionId)){
                    tokenSet.add(strTemp);
                    //                  return strTemp.substring(6);
                }
            }
            //判断取到的TokenKey是否大于1条
            if(tokenSet.size()>1){
                for(String tempVal : tokenSet){
                    //判断取得的token Key里面的open_id是否为手机号，如果是返回该条TokenKey对应的Token
                    if(checkMobileNumber(JsonUtils.getjsondata(jedis.get(tempVal), "user.OpenId"))){
                        return tempVal.substring(6);
                    }
                }
            }
            //取出tokenSet里的唯一tokenKey对应的Token
            for(String tempVal : tokenSet){
                return tempVal.substring(6);
            }

        }catch(Exception e){

        }finally {
            returnBrokenResource(jedis);
        }

        return null;
    }
    public static boolean checkMobileNumber(String mobileNumber){
        boolean flag = false;
        try{
            Pattern regex = Pattern.compile("^(((1[3-9][0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }
    public static String getUidfromRedis(){
        String uid=null;
        String token = getTokenfromRedis();
        Jedis jedis = null;
        String value=null;
        try{
            jedis = getResource();
            value = jedis.get("token:"+token);
            if(value==null || value.equals("")){
                return uid;
            }else{
                uid = JsonUtils.getjsondata(value, "uid");
                return uid;
            }
        }catch(Exception e){

        }finally {
            returnBrokenResource(jedis);
        }
        return null;
    }
//    /**
//     * 获取资源
//     * @return
//     * @throws JedisException
//     */
    public static  Jedis getResource() throws JedisException {
        Jedis jedis = null;
        try {
            jedis = JedisClientPool_232.jedisPool.getResource();
//			logger.debug("getResource.", jedis);
        } catch (JedisException e) {
            //logger.warn("getResource.", e);
            returnBrokenResource(jedis);
            throw e;
        }
        return jedis;
    }
//
//    /**
//     * 归还资源
//     * @param jedis
//     * @param isBroken
//     */
    public static  void returnBrokenResource(Jedis jedis) {
        if (jedis != null) {
            JedisClientPool_232.jedisPool.returnBrokenResource(jedis);
        }
    }
//
//    /**
//     * 释放资源
//     * @param jedis
//     * @param isBroken
//     */
    public static  void returnResource(Jedis jedis) {
        if (jedis != null) {
            JedisClientPool_232.jedisPool.returnResource(jedis);
        }
    }
}
