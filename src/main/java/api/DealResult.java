package api;

import utils.JsonUtils;

/**
 * Created by cch on 2017/6/20.
 * 处理接口返回数据获取所需数据
 */
public class DealResult {

    public static String getResult_Code(String result){
        return(JsonUtils.getjsondata(result,"code"));
    }

    public static String getResult_Data(String result){
        return(JsonUtils.getjsondata(result,"data"));        
    }

    public static String getResult_Message(String result){
        return(JsonUtils.getjsondata(result,"message"));
    }

    public static String getResult_rtMessage(String result){
        return(JsonUtils.getjsondata(JsonUtils.getjsondata(result,"data"),"rtMessage"));
    }
    public static String getResult_rtCode(String result){
        return(JsonUtils.getjsondata(JsonUtils.getjsondata(result,"data"),"rtCode"));
    }

    public static String getResult_cyjg_code(String result){
        return(JsonUtils.getjsondata(JsonUtils.getjsondata(result,"data"),"cyjg_code"));
    }

    public static String getResult_cyjg_message(String result){
        return(JsonUtils.getjsondata(JsonUtils.getjsondata(result,"data"),"message"));
    }

    public static String get_getBindingPhone_Phone(String result){
        return (JsonUtils.getjsondata(result,"data","phone"));
    }
    public static String get_rtCode(String result){
        return (JsonUtils.getjsondata(result, "data","rtCode"));
    }
}
