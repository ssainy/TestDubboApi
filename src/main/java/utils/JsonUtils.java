package utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.testng.Reporter;

import java.util.Map;

/**
 * Created by cch on 2017/5/17.
 */
public class JsonUtils {
    public static String jsondata(String json,String key1,String value){
        try{
        JSONObject s1 = JSONObject.fromObject(json);
        s1.put(key1,value);
        String injson = s1.toString();
        return injson;
        }catch (Exception e){
            System.out.println("取值出错了：" + e.toString());
            Reporter.log("修改"+json+"中的"+key1+"过程中出错");
            return null;
        }
    }
    public static String getjsondata(String json,String key1){
        try{
            JSONObject s1 = JSONObject.fromObject(json);
            String value=s1.getString(key1).toString();
            return value;
        }catch (Exception e){
        System.out.println("取值出错了：" + e.toString());
        Reporter.log("从"+json+"取"+key1+"过程中出错，错误原因是："+e.toString());
            return null;
        }
    }
    public static String getjsondata(String json,String key1,String key2){
        try{
        JSONObject s1 = JSONObject.fromObject(json);
        JSONObject s2 = JSONObject.fromObject(s1.get(key1));
        String value=s2.getString(key2).toString();
        return value;
        }catch (Exception e){
            System.out.println("取值出错了：" + e.toString());
            Reporter.log("从"+json+"取"+key1+","+key2+"过程中出错，错误原因是："+e.toString());
            return null;
        }
    }
    public static String getjsondata(String json,String key1,String key2,String key3){
        try{
            JSONObject s1 = JSONObject.fromObject(json);
            JSONObject s2 = JSONObject.fromObject(s1.get(key1));
            JSONObject s3 = JSONObject.fromObject(s2.get(key2));
            String value=s3.getString(key3).toString();
            return value;
        }catch (Exception e){
            System.out.println("取值出错了：" + e.toString());
            System.out.println("从"+json+"取"+key1+","+key2+","+key3+"过程中出错，错误原因是："+e.toString());
            Reporter.log("从"+json+"取"+key1+","+key2+","+key3+"过程中出错，错误原因是："+e.toString());
        return null;
        }
    }

    /******************************************
     * 对json串key赋值，只支持一级结构
     * @param json
     * @param key
     * @param value
     * @return
     ******************************************/
    public static String SetJsonValue(String json,String key,String value){
        try{
            JSONObject s1 = JSONObject.fromObject(json);
            s1.put(key,value);
            String injson = s1.toString();
            return injson;
        }catch (Exception e){
            System.out.println("取值出错了：" + e.toString());
            Reporter.log("修改" + json + "中的" + key + "过程中出错");
            return null;
        }
    }

    public static String jsondata(String json,String key1,String key2,String value){
        try{
        JSONObject s1 = JSONObject.fromObject(json);
        JSONObject s2 = JSONObject.fromObject(s1.get(key1));
        s2.put(key2,value);
        String send_request=s2.toString();
        s1.put(key1,send_request);
        String injson = s1.toString();
        return injson;
    }catch (Exception e){
        System.out.println("取值出错了：" + e.toString());
            Reporter.log("修改"+json+"中的"+key1+","+key2+"过程中出错");
        return null;
        }
    }
    public static String jsonArrayData(String json,String arraykey,String key,String value){
        JSONObject s1 = JSONObject.fromObject(json);
        JSONArray jsonArray =s1.getJSONArray(arraykey);
        String injson=null;
        for (int i=0;i<jsonArray.size();i++){
            JSONObject jo = jsonArray.getJSONObject(i);
            jo.put(key,value);
            System.out.println("*************"+jo);
            jsonArray.set(0,jo);
            String ss=jsonArray.toString();
            s1.put(arraykey,ss);
            injson = s1.toString();
            System.out.println("发送报文：" + injson);

        }
        return injson;
    }
    public static String getJsonArrayData(String json,String key){
        try{
        JSONArray jsonArray =JSONArray.fromObject(json);
        String value=null;
        for (int i=0;i<jsonArray.size();i++){
           // System.out.println(jsonArray.getJSONObject(i));
            JSONObject jo = jsonArray.getJSONObject(i);

            value=jo.getString(key);
        }
        return value;
        }catch (Exception e){
        System.out.println("取值出错了：" + e.toString());
        Reporter.log("从"+json+"取"+key+"过程中出错，错误原因是："+e.toString());
        return null;
        }
    }
    public static String getJsonArrayData(String json,int i){
        JSONArray jsonArray =JSONArray.fromObject(json);
            String value=jsonArray.getJSONObject(i).toString();
        return value;
    }
    public static int getJsonArrayCount(String json){
        try{
        int count=0;
        if (StringUtil.isEmpty(json)){
        }else{
        JSONArray jsonArray =JSONArray.fromObject(json);
        count=jsonArray.size();
    }
        return count;
        }catch (Exception e){
        System.out.println("取值出错了：" + e.toString());
        Reporter.log("获取列表个数出错:"+json);
        return 0;
        }
    }
    public static String getRandomJsonArrayData(String json,String key){
        try{
        JSONArray jsonArray =JSONArray.fromObject(json);
        String value=null;
        int index = (int) (Math.random() * jsonArray.size());
            JSONObject jo = jsonArray.getJSONObject(index);
            value=jo.getString(key);
        return value;
        }catch (Exception e){
        System.out.println("取值出错了：" + e.toString());
            Reporter.log("从"+json+"取"+key+"过程中出错，错误原因是："+e.toString());
        return null;
        }
    }

    /**
     * json string 转换为 map 对象
     * @param jsonObj
     * @return
     */
    public static Map<Object, Object> jsonToMap(Object jsonObj) {
        JSONObject jsonObject = JSONObject.fromObject(jsonObj);
        Map<Object, Object> map = (Map)jsonObject;
        return map;
    }

    /**json string 转换为 对象
     * @param jsonObj
     * @param type
     * @return
     */
    public  static <T>  T jsonToBean(Object jsonObj, Class<T> type) {
        JSONObject jsonObject = JSONObject.fromObject(jsonObj);
        T obj =(T)JSONObject.toBean(jsonObject, type);
        return obj;
    }
}
