/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.JsonUtils;
import utils.URLEncodeDecode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/********************************************************
 *处理数据库查询结果
 * @author thinkpad
 ********************************************************/
public class DealSqlResult {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
    /************************************************************************
     * 将数据库返回的数据拼装成modifyCardPage接口结果数据
     * @param sql_json 数据库查询后转换的json串
     * @return 
     ************************************************************************/
    public static String getTitileInfoById(String sql_json) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(JsonUtils.getJsonArrayData(sql_json,"create_time"));
        long ts = date.getTime();
        String creat_time = String.valueOf(ts);
        date = simpleDateFormat.parse(JsonUtils.getJsonArrayData(sql_json,"modify_time"));
        ts = date.getTime();
        String modify_time = String.valueOf(ts);
        return "{\"id\":\""+JsonUtils.getJsonArrayData(sql_json,"id")+"\"," +
                "\"uid\":\""+JsonUtils.getJsonArrayData(sql_json,"uid")+"\"," +
                "\"userType\":\""+JsonUtils.getJsonArrayData(sql_json,"user_type")+"\"," +
                "\"unionId\":\""+JsonUtils.getJsonArrayData(sql_json, "union_id")+"\"," +
                "\"openId\":\""+JsonUtils.getJsonArrayData(sql_json, "open_id")+"\"," +
                "\"title_name\":\""+JsonUtils.getJsonArrayData(sql_json,"title_name")+"\"," +
                "\"taxpayer_id\":\""+JsonUtils.getJsonArrayData(sql_json,"taxpayer_id")+"\"," +
                "\"register_addr\":\""+JsonUtils.getJsonArrayData(sql_json,"register_addr")+"\"," +
                "\"register_phone\":\""+JsonUtils.getJsonArrayData(sql_json,"register_phone")+"\"," +
                "\"bank_name\":\""+JsonUtils.getJsonArrayData(sql_json,"bank_name")+"\"," +
                "\"bank_account\":\""+JsonUtils.getJsonArrayData(sql_json,"bank_account")+"\"," +
                "\"qrCodeVersion\":\""+JsonUtils.getJsonArrayData(sql_json,"qr_code_version")+"\"," +
                "\"createTime\":"+creat_time+"," +
                "\"type\":\""+JsonUtils.getJsonArrayData(sql_json,"type")+"\"," +
                "\"status\":\""+JsonUtils.getJsonArrayData(sql_json,"status")+"\"," +
                "\"modifyTime\":"+modify_time+"," +
                "\"email\":\""+JsonUtils.getJsonArrayData(sql_json,"email")+"\"," +
                "\"telephone\":\""+JsonUtils.getJsonArrayData(sql_json,"telephone")+"\"," +
                "\"mpbs\":\""+JsonUtils.getJsonArrayData(sql_json,"mpbs")+"\"," +
                "\"topFlag\":\""+JsonUtils.getJsonArrayData(sql_json,"top_flag")+"\"}";
    }

    /************************************************************************
     *
     * @param sql_json 数据库查询后获取status值
     * @return
     ************************************************************************/
    public static String GetData_status(String sql_json){
        JSONArray jsonArray = JSONArray.fromObject(sql_json);
        JSONObject jo = jsonArray.getJSONObject(0);
        return  JsonUtils.getjsondata(jo.toString(),"status");
    }

    /************************************************************************
     *
     * @param sql_json 数据库查询后获取top_flag值
     * @return
     ************************************************************************/
    public static String GetData_top_flag(String sql_json){
        return  JsonUtils.getjsondata(sql_json,"top_flag");
    }
    /************************************************************************
     *
     * @param sql_json 数据库查询后将数据库返回的数据拼装成查询用户信息接口结果数据换的json串
     * @return
     ************************************************************************/
    public static String MakeData_getUserInfo(String sql_json){
        return "{\"id\":\""+JsonUtils.getjsondata(sql_json,"id")+"\",\"user_type\":\""+JsonUtils.getjsondata(sql_json,"user_type")+"\",\"open_id\":\""+JsonUtils.getjsondata(sql_json, "user.OpenId")+"\",\"create_time\":\""+JsonUtils.getjsondata(sql_json,"create_time")+"\",\"sex\":\""+JsonUtils.getjsondata(sql_json,"sex")+"\",\"nick_name\":\""+JsonUtils.getjsondata(sql_json,"nick_name")+"\",\"head_img_url\":\""+JsonUtils.getjsondata(sql_json,"head_img_url")+"\",\"city\":\""+JsonUtils.getjsondata(sql_json,"city")+"\",\"country\":\""+JsonUtils.getjsondata(sql_json,"country")+"\",\"province\":\""+JsonUtils.getjsondata(sql_json,"province")+"\",\"language\":\""+JsonUtils.getjsondata(sql_json,"language")+"\",\"address\":\""+JsonUtils.getjsondata(sql_json,"address")+"\",\"lable\":\""+ URLEncodeDecode.decode(JsonUtils.getjsondata(sql_json,"lable"))+"\",\"union_id\":\""+JsonUtils.getjsondata(sql_json, "user.UnionId")+"\",\"currentOpenId\":\""+JsonUtils.getjsondata(sql_json, "user.OpenId")+"\",\"currentType\":\""+JsonUtils.getjsondata(sql_json,"user_type")+"\"}"  ;
    }
    /************************************************************************
     *
     * @param sql_json 数据库查询后将数据库返回的数据拼装成查询APP版本的json串
     * @return
     ************************************************************************/
    public static String MakeData_getAPPVersion(String sql_json){
        return "{" +
                "\"ver\":\""+JsonUtils.getjsondata(sql_json,"version_no")+"\"," +
                "\"url\":\""+JsonUtils.getjsondata(sql_json,"version_url")+"\"," +
                "\"description\":\""+JsonUtils.getjsondata(sql_json,"version_desc")+"\"," +
                "\"type\":"+JsonUtils.getjsondata(sql_json,"version_type")+"" +
                "}"  ;
    }
    public static String MakeData_getCardList(String sql_json){
        return ""  ;
    }
    public static String getCardList(String sql_json) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(JsonUtils.getjsondata(sql_json,"create_time"));
        long ts = date.getTime();
        String creat_time = String.valueOf(ts);
        date = simpleDateFormat.parse(JsonUtils.getjsondata(sql_json,"modify_time"));
        ts = date.getTime();
        String modify_time = String.valueOf(ts);
        return "{\"id\":\""+JsonUtils.getjsondata(sql_json,"id")+"\"," +
                "\"uid\":\""+JsonUtils.getjsondata(sql_json,"uid")+"\"," +
                "\"userType\":\""+JsonUtils.getjsondata(sql_json,"user_type")+"\"," +
                "\"unionId\":\""+JsonUtils.getjsondata(sql_json, "union_id")+"\"," +
                "\"openId\":\""+JsonUtils.getjsondata(sql_json, "open_id")+"\"," +
                "\"title_name\":\""+JsonUtils.getjsondata(sql_json,"title_name")+"\"," +
                "\"taxpayer_id\":\""+JsonUtils.getjsondata(sql_json,"taxpayer_id")+"\"," +
                "\"register_addr\":\""+JsonUtils.getjsondata(sql_json,"register_addr")+"\"," +
                "\"register_phone\":\""+JsonUtils.getjsondata(sql_json,"register_phone")+"\"," +
                "\"bank_name\":\""+JsonUtils.getjsondata(sql_json,"bank_name")+"\"," +
                "\"bank_account\":\""+JsonUtils.getjsondata(sql_json,"bank_account")+"\"," +
                "\"qrCodeVersion\":\""+JsonUtils.getjsondata(sql_json,"qr_code_version")+"\"," +
                "\"createTime\":"+creat_time+"," +
                "\"type\":\""+JsonUtils.getjsondata(sql_json,"type")+"\"," +
                "\"status\":\""+JsonUtils.getjsondata(sql_json,"status")+"\"," +
                "\"modifyTime\":"+modify_time+"," +
                "\"email\":\""+JsonUtils.getjsondata(sql_json,"email")+"\"," +
                "\"telephone\":\""+JsonUtils.getjsondata(sql_json,"telephone")+"\"," +
                "\"mpbs\":\""+JsonUtils.getjsondata(sql_json,"mpbs")+"\"," +
                "\"topFlag\":\""+JsonUtils.getjsondata(sql_json,"top_flag")+"\"}";
    }
}    

