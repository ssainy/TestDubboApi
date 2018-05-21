package dependence;

import api.*;
import org.testng.Reporter;
import utils.GetGenImg;
import utils.JsonUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/14.
 */
public class TrafficInvoice {
    String ret;
    String picture;
    String token;
    String type;
    String flag;
    String info;
    String uuid;
    String trafficId;
    String trafficId_md5;
    String ImagePath;



  public  TrafficInvoice(String token, String picture,String info,String type, String flag){
        this.token = token;
        this.picture = picture;
        this.type = type;
        this.flag = flag;
        this.info=info;
    }
    public void  recognitionByOcr(){
        Map map = new HashMap<String,String>();
        int retryTimes = 0;
        Boolean flag = true; //true 代表识别发票成功
        String picToBase64Str = GetGenImg.GetImageStr(picture);
        map.put("picture",picToBase64Str);
        try {
            //需要重试多次
            do{
                retryTimes++;
                System.out.println("尝试第 "+retryTimes+" 次识别!");
                ret = DoApi.doinvoiceRecognitionByOcr(token, type, map);
               System.out.println("ocr识别返回接口："+ret);
                Reporter.log("ocr识别返回接口："+ret);
                if(retryTimes>=10){
                    System.out.println("重试了10次，依然没有识别出来！终断!");
                    flag=false;
                    break;
                }
            }while(!JsonUtils.getjsondata(ret,"message").equals("处理成功"));
            if(flag==true) {
             //   trafficId = JsonUtils.getjsondata(ret, "data", "data", "id");
                uuid = JsonUtils.getjsondata(ret, "data", "uuid");
            }else{
                System.out.println("未能识别出发票,返回的信息是："+ret);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String trafficInvoiceInstance(){
        getRet();
        String id = JsonUtils.getjsondata(ret,"data","data","id");
        String uid = JsonUtils.getjsondata(ret,"data","data","uid");
        String create_time = JsonUtils.getjsondata(ret,"data","data","create_time");
        String existPicture = JsonUtils.getjsondata(ret,"data","data","existPicture");
        String gjly = JsonUtils.getjsondata(ret,"data","data","gjly");
        String img_path = JsonUtils.getjsondata(ret,"data","data","img_path");
        String je =  JsonUtils.getjsondata(ret,"data","data","je");
        String kprq = JsonUtils.getjsondata(ret,"data","data","kprq");
        String modify_time = JsonUtils.getjsondata(ret,"data","data","modify_time");
        String name = JsonUtils.getjsondata(ret,"data","data","name");
        String read_status = JsonUtils.getjsondata(ret,"data","data","read_status");
        String starting_station = JsonUtils.getjsondata(ret,"data","data","starting_station");
        String status = JsonUtils.getjsondata(ret,"data","data","status");
        String terminal_station = JsonUtils.getjsondata(ret,"data","data","terminal_station");
        String train_number = JsonUtils.getjsondata(ret,"data","data","train_number");
        String train_time = JsonUtils.getjsondata(ret,"data","data","train_time");
        String invoice_type = JsonUtils.getjsondata(ret,"data","invoiceType");
        String uuid = JsonUtils.getjsondata(ret,"data","uuid");

        SimpleDateFormat sdfOld =   new SimpleDateFormat( "yyyyMMdd" );
        SimpleDateFormat sdfNew =   new SimpleDateFormat( "yyyy-MM-dd" );

        if(train_time!=null && train_time.length()!=0) {
            try {
                Date date = sdfOld.parse(train_time);
                train_time = sdfNew.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        /**
         * 分别判断kprq或train_time是否为空，如果为空设置为系统当前时间
         */
        Date now = new Date();

        if("".equals(kprq) || kprq==null || "null".equals(kprq)) {
            kprq = sdfNew.format(now);
        }
        if("".equals(train_time) || train_time==null ||"null".equals(train_time)){
            train_time = sdfNew.format(now);
        }

        String req_str= Data.trafficInvoiceCollection();
        req_str= JsonUtils.jsondata(req_str,"id",id);
        req_str= JsonUtils.jsondata(req_str,"uid",uid);
        req_str= JsonUtils.jsondata(req_str,"create_time",create_time);
        req_str= JsonUtils.jsondata(req_str,"existPicture",existPicture);
        req_str= JsonUtils.jsondata(req_str,"gjly",gjly);
        req_str= JsonUtils.jsondata(req_str,"img_path",img_path);
        req_str= JsonUtils.jsondata(req_str,"je",je);
        req_str= JsonUtils.jsondata(req_str,"kprq",kprq);
        req_str= JsonUtils.jsondata(req_str,"modify_time",modify_time);
        req_str= JsonUtils.jsondata(req_str,"name",name);
        req_str= JsonUtils.jsondata(req_str,"read_status",read_status);
        req_str= JsonUtils.jsondata(req_str,"starting_station",starting_station);
        req_str= JsonUtils.jsondata(req_str,"status",status);
        req_str= JsonUtils.jsondata(req_str,"terminal_station",terminal_station);
        req_str= JsonUtils.jsondata(req_str,"train_number",train_number);
        req_str= JsonUtils.jsondata(req_str,"train_time",train_time);
        req_str= JsonUtils.jsondata(req_str,"invoice_type",invoice_type);
        req_str= JsonUtils.jsondata(req_str,"uuid",uuid);
        System.out.println("开票日期为："+ kprq+"\n"+"发车日期为："+train_time);
        return req_str;

    }
    /**
     * 加密代码号码
     * @param token
     */
    public  void getMd5FpdmFphm(String token, String trafficId){
        String ret = DoApi.dogetMd5FpdmFphm(token,"","","",trafficId);
        this.trafficId_md5 = JsonUtils.getjsondata(ret,"data","trafficId");
    }
    public void queryTrafficInfo(String token,String trafficId){
        String ret = DoApi.doqueryTrafficInfo(token,trafficId);
        this.ImagePath=JsonUtils.getjsondata(ret,"data","img_path");
        System.out.println("请求发票详情返回："+ret);
        Reporter.log("请求发票详情返回："+ret);
    }
    public void addInvoice(){
        if (this.flag.equals("0")){
            //0为OCR归集，1为手动归集
            recognitionByOcr();
            trafficInvoiceCollection(this.token,this.uuid,this.flag,trafficInvoiceInstance());
        }else {
            trafficInvoiceCollection(this.token,"",this.flag,this.info);
        }
        getMd5FpdmFphm(this.token, this.trafficId);
        if (type.equals("1")||type.equals("4")){
            type="3";
        }
        queryTrafficInfo(this.token, this.trafficId_md5);
    }
    public String trafficInvoiceCollection(String token,String uuid,String flag,String info){
        String ret=DoApi.dotrafficInvoiceCollection(token,uuid,flag,info);
        this.trafficId=JsonUtils.getjsondata(ret,"data","invoiceId");
        return  this.trafficId;
    }
    public String getInvoiceId(){
        return this.trafficId;
    }
    public String getRet(){
        return ret;
    }
    public String getImagePath(){
        return this.ImagePath;
    }

    public String getUuid(){
        return JsonUtils.getjsondata(trafficInvoiceInstance(),"uuid");
    }

}
