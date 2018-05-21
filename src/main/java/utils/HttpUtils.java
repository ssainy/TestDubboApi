package utils;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.util.Map;


/**
 * Created by cch on 2017/6/15.
 */
public class HttpUtils {
    public static String executeHttpPost(String url,String  str_send,String token){
        PostMethod postMethod = null;
        HttpClient httpClient = new HttpClient();

        postMethod  = new PostMethod(url);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(6000000);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(2000000);
        postMethod.setRequestHeader("accept", "*/*");
        postMethod.setRequestHeader("Content-Type","application/json");
        postMethod.setRequestHeader("X-Auth-Token",token);
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        try{

            httpClient.getParams().setContentCharset("UTF-8");
            RequestEntity request = new StringRequestEntity(str_send,"textml","UTF-8");
            System.out.println("str_send"+str_send);
            //System.out.println(request);
            postMethod.setRequestEntity(request);

            int statusCode = httpClient.executeMethod(postMethod);
            if (200 == statusCode) {
                byte[] responseBody = postMethod.getResponseBody();

                // 处理内容
                String respXML = new String(responseBody, "UTF-8");
                return respXML;

            }else{
                byte[] responseBody = postMethod.getResponseBody();
                return (new String(responseBody, "UTF-8"));
                //return("POST请求失败，返回状态："+ statusCode);
            }
        }
        catch(Exception e){
            return("请求出错：" + e.toString());
        }
    }
    public static String executeHttpPost2(String url,Map<String, String> params) throws IOException{

        PostMethod postMethod = null;
        HttpClient httpClient = new HttpClient();

        postMethod  = new PostMethod(url);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(6000000);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(2000000);
        postMethod.setRequestHeader("accept", "*/*");
        postMethod.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
        if(params !=null && params.size()>0){
            int paramsSize = params.size();   //获取传入的Map的元素数量
            int i = 0; //设定数组的角标
            NameValuePair[] param = new NameValuePair[paramsSize]; //创建NameValuePair类型的数组，大小为paramsSize
            for(Map.Entry<String,String> e : params.entrySet()){
                param[i] = new NameValuePair(e.getKey(),e.getValue());
                i++;
            }
            postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            postMethod.setRequestBody(param);
            try{;
                int statusCode = httpClient.executeMethod(postMethod);
                if (200 == statusCode) {
                    byte[] responseBody = postMethod.getResponseBody();

                    // 处理内容
                    String respXML = new String(responseBody, "UTF-8");
                    return respXML;

                }else{
                    System.out.println("返回状态非200" + statusCode);
                    byte[] responseBody = postMethod.getResponseBody();
                    postMethod.releaseConnection();
                    return (new String(responseBody, "UTF-8"));
                    //return("POST请求失败，返回状态："+ statusCode);
                }
            }
            catch(Exception e){
                return("请求出错：" + e.toString());
            }
        }

       return("传入的Map参数为空！");
    }

    public static String executeHttpPost(String url,String  str_send){
        PostMethod postMethod = null;
        HttpClient httpClient = new HttpClient();

        postMethod  = new PostMethod(url);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(6000000);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(2000000);
        postMethod.setRequestHeader("accept", "*/*");
        postMethod.setRequestHeader("Content-Type","application/json");
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        try{

            httpClient.getParams().setContentCharset("UTF-8");
            RequestEntity request = new StringRequestEntity(str_send,"application/json","UTF-8");
            System.out.println("发送内容为："+str_send);
            //System.out.println(request);
            postMethod.setRequestEntity(request);

            int statusCode = httpClient.executeMethod(postMethod);
            if (200 == statusCode) {
                byte[] responseBody = postMethod.getResponseBody();

                // 处理内容
                String respXML = new String(responseBody, "UTF-8");
                return respXML;

            }else{
                System.out.println("返回状态非200" + statusCode);
                byte[] responseBody = postMethod.getResponseBody();
                return (new String(responseBody, "UTF-8"));
                //return("POST请求失败，返回状态："+ statusCode);
            }
        }
        catch(Exception e){
            return("请求出错：" + e.toString());
        }
    }

    public static String executeHttpGet(String url) {
        GetMethod getMethod = null;
        HttpClient httpClient = new HttpClient();

        getMethod  = new GetMethod(url);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(60000);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(60000);

        try{
            int statusCode = httpClient.executeMethod(getMethod);
            if (200 == statusCode) {
                byte[] responseBody = getMethod.getResponseBody();

                // 处理内容
                String respXML = new String(responseBody, "UTF-8");
                return respXML;
            }else{
                byte[] responseBody = getMethod.getResponseBody();
                return (new String(responseBody, "UTF-8"));
            }
        }
        catch(Exception e){
            return("请求出错：" + e.toString());
        }
    }

    public static byte[] executeHttpGet2(String url) {
        GetMethod getMethod = null;
        HttpClient httpClient = new HttpClient();

        getMethod  = new GetMethod(url);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(60000);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(60000);

        try{
            int statusCode = httpClient.executeMethod(getMethod);
            if (200 == statusCode) {
                byte[] responseBody = getMethod.getResponseBody();

                // 处理内容
              //  String respXML = new String(responseBody, "UTF-8");
                return responseBody;
            }else{
                byte[] responseBody = getMethod.getResponseBody();
                return responseBody;
            }
        }
        catch(Exception e){
            //return("请求出错：" + e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public static String executeHttpGetforResCode(String url){
        GetMethod getMethod = null;
        HttpClient httpClient = new HttpClient();

        getMethod  = new GetMethod(url);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(6000000);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(2000000);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        try{
            int statusCode = httpClient.executeMethod(getMethod);
            return Integer.toString(statusCode);
            }
            catch(Exception e){
            return("请求出错：" + e.toString());
        }
    }

}
