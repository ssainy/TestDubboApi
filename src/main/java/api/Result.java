package api;

import utils.URLEncodeDecode;
import java.util.*;

/**
 * Created by Administrator on 2017/9/15.
 */
public class Result {
    String url="";
    Map requestArg;//请求参数
    Map expectRs;//预期结果

    public Result(HashMap<String, String> data) {
        requestArg = new HashMap<String,String>();
        expectRs = new HashMap();
        /**遍历data,将requestArg 和 expectArg填充*/
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if('$'==(entry.getKey().charAt(0))){
                expectRs.put(entry.getKey().substring(1),entry.getValue());
            }else{
                requestArg.put(entry.getKey(),entry.getValue());
            }
        }
    }

    public Map getRequestArg(){
        return this.requestArg;
    } //返回请求参数Map

    public Map getExpectRs(){
        return this.expectRs;
    }     //返回结果参数Map

    public String getExValue(String key){
        return (String)getExpectRs().get(key);
    }

    public String getUrl(){                             //返回不带token请求字符串String
        StringBuilder sb = new StringBuilder();
        Map reqMap =this.getRequestArg();
        Iterator<Map.Entry<String,String>> it = reqMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,String> entry =it.next();
            if(entry.getKey().equals("info")) {
                entry.setValue(URLEncodeDecode.encode(entry.getValue()));
            }

            sb.append("&"+entry.getKey()+"="+entry.getValue());
        }
        String returnUrl = "?"+sb.substring(1);
        return returnUrl;
    }

    public String getUrl(String token){                         //返回带token的请求字符串
        return this.getUrl()+"&token="+token;
    }
}