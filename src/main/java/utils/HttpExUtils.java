package utils;

import net.sf.json.JSONException;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by thinkpad on 2017/6/24.
 */
public class HttpExUtils {
    public static String  executeHttpDelete(String url,String token){
        org.apache.http.client.HttpClient httpClient = new DefaultHttpClient();
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setHeader("Content-type", "application/json");
        httpDelete.setHeader("Accept", "application/json");
        httpDelete.setHeader("X-Auth-Token", token);
        try {
            HttpResponse response = httpClient.execute(httpDelete);
            String result= EntityUtils.toString(response.getEntity());
            return result;
        } catch (ParseException | JSONException | IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
    public static String  executeHttpPatch(String url,String jsonParam,String token){
        org.apache.http.client.HttpClient httpClient = new DefaultHttpClient();
        HttpPatch httpPatch = new HttpPatch(url);
        httpPatch.setHeader("Content-type", "application/json");
        httpPatch.setHeader("Accept", "application/json");
        httpPatch.setHeader("X-Auth-Token", token);
        try {
            StringEntity entity = new StringEntity(jsonParam, HTTP.UTF_8);
            httpPatch.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPatch);
            String result=EntityUtils.toString(response.getEntity());
            return result;
        } catch (ParseException | JSONException | IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
    public static String executeHttpPut(String url, String jsonParam, String token) {
        org.apache.http.client.HttpClient httpClient = new DefaultHttpClient();
        HttpPut httpPut = new HttpPut(url);
        httpPut.setHeader("Content-type", "application/json");
        httpPut.setHeader("Charset", HTTP.UTF_8);
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Accept-Charset", HTTP.UTF_8);
        httpPut.setHeader("X-Auth-Token", token);
        try {
            if (jsonParam != null) {
                StringEntity entity = new StringEntity(jsonParam.toString(), HTTP.UTF_8);
                httpPut.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPut);
            String result=EntityUtils.toString(response.getEntity());
            return result;
        } catch (ParseException | JSONException | IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
