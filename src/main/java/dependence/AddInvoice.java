package dependence;

import api.*;
import org.apache.regexp.RE;
import org.testng.Reporter;
import utils.JsonUtils;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/15.
 */
public class AddInvoice {
    private String info;        //发票信息，格式为:01,10,012001700111,04523861,13.59,20170829,11467753127216379282,F6B5
    private String flag;
    private String token;       //用户的token
    private String uid;         //用户uid
    private String invoiceId;   //发票ID
    private String pic;         //发票的图片文件流
    private String type;         //错票标识，3为错票
    private String fpdm;        //发票代码
    private String fphm;        //发票号码
    private String invoiceType; //发票类型
    private String imgUuid;     //图片缓存的uuid
    private String picture;
    /**
     * 构造方法
     * @param info  发票信息，格式为:01,10,012001700111,04523861,13.59,20170829,11467753127216379282,F6B5
     * @param token  用户的token
     */
    //type....1,3,4为错票
    public AddInvoice(String flag,String info,String picture,String token,String uid,String type){
        this.flag=flag;
        this.info = info;
        this.token = token;
        this.uid = uid;
        this.type=type;
        this.picture=picture;
    }
    /**
     *  调用/invoice/check/qrcheck接口获得fpdm、fphm
     */
    public void qrcheck(String flag,String info, String token,String uuid,String type){
        String ret = DoApi.doqrcheck(flag,info,token,uuid);
        System.out.println("使用qrcheck添加发票返回结果："+ret);
        Reporter.log("使用qrcheck添加发票返回结果："+ret);
        if (type.equals("1")||type.equals("3")||type.equals("4")){
            this.fpdm = JsonUtils.getjsondata(ret, "data", "invoiceInfo", "fpdm");
            this.fphm = JsonUtils.getjsondata(ret, "data", "invoiceInfo", "fphm");
        }else {
            this.fpdm = JsonUtils.getjsondata(ret, "data", "paramMap", "fpdm");
            this.fphm = JsonUtils.getjsondata(ret, "data", "paramMap", "fphm");
        }

    }

    /**
     * 加密代码号码
     * @param token
     * @param fpdm
     * @param fphm
     */
    public  void getMd5FpdmFphm(String token, String fpdm, String fphm){
        String ret = DoApi.dogetMd5FpdmFphm(token,fpdm,fphm,"","");
        this.fpdm = JsonUtils.getjsondata(ret,"data","fpdm");
        this.fphm = JsonUtils.getjsondata(ret,"data","fphm");
    }
    /**
     * 根据获得的fpdm、fphm调用/invoice/detail/queryInvoiceInfoByDmhmAtPc接口获得invoiceId并且获得发票图片的Base64编码
     * type(1=错票,其余为真票)
     */
    public void getInvoiceIdPic(String token, String fpdm, String fphm, String type){
        if (type.equals("3")||type.equals("4")){
            type="1";
        }
        String ret =  DoApi.doqueryInvoiceInfoByDmhmAtPc(token,fpdm,fphm,type);
        this.invoiceId = JsonUtils.getjsondata(ret,"data","invoiceInfo","id");
        this.invoiceType = JsonUtils.getjsondata(ret,"data","invoiceInfo","invoice_type");
        String pngFileContent = JsonUtils.getjsondata(ret,"data","pngFile");
        this.pic = pngFileContent.substring(2,pngFileContent.length()-2);
        Reporter.log("根据代码号码获取invoiceid以及发票图片base64结果为："+ret);
    }

    /**
     * 根据代码号码获取发票详情
     * @param fpdm
     * @param fphm
     * @param type
     */
    public void queryInvoiceInfoByDmHm(String token,String fpdm,String fphm,String type) {
        String ret = DoApi.doqueryInvoiceInfoByDmHm(token, fpdm, fphm, type);
        System.out.println(ret);
        invoiceId=JsonUtils.getjsondata(ret,"data","invoiceInfo","id");
        Reporter.log("接口返回内容：" + ret);
    }
    /**
     * 调用/secured/previrewDownload/uploadPaperImg 接口 上传纸票图片
     */
    public void uploadPaperImg(String token, String uid, String id,String fpdm,String fphm,String pic){
        String req_str = Data.uploadPaperImg(invoiceId,fpdm,fphm,pic);
        req_str= JsonUtils.jsondata(req_str,"create_time","2018-12-31 20:20:20");
        req_str= JsonUtils.jsondata(req_str,"file","文件");
        req_str= JsonUtils.jsondata(req_str,"fpdm",fpdm);
        req_str= JsonUtils.jsondata(req_str,"fphm",fphm);
        req_str= JsonUtils.jsondata(req_str,"id",id);
        req_str= JsonUtils.jsondata(req_str,"invoice_info_id","");
        req_str= JsonUtils.jsondata(req_str,"paper_img","");
        req_str= JsonUtils.jsondata(req_str,"pic",pic);
        req_str= JsonUtils.jsondata(req_str,"status","1");
        req_str= JsonUtils.jsondata(req_str,"type","0");
        req_str= JsonUtils.jsondata(req_str,"uid",uid);
        String ret = DoApi.douploadPaperImg(token,req_str);
        imgUuid = JsonUtils.getjsondata(ret,"data","paper_img");
        System.out.println(req_str);
        Reporter.log("上传纸票图片返回结果为："+ret);
    }
    /**
     * 1)查验
     * 2)得到发票Id和Pic
     * 3)上传图片
     * 4)删除图片
     */
    public void runAddInvoice(){
        qrcheck(this.flag,this.info,"",this.token,this.type);
        getInvoiceIdPic(this.token, this.fpdm, this.fphm, "0");
        String invoiceType = getInvoiceType();
        if(invoiceType.equals("1"))
        uploadPaperImg(this.token,this.uid,this.invoiceId,this.fpdm,this.fphm,this.pic);
    }
    public void addInvoice(){
        if (this.flag.equals("2")){
            //若为OCR归集，先走OCR识别发票信息接口，返回发票的信息，再进行qrcheck
            Map picInfo = GetPicInfo.getPicInfo(this.picture,"0",this.token);
            String uuid = GetStringFromMap.getString(picInfo,"uuid");
            String infoStr = GetStringFromMap.getString(picInfo,"infoStr");
           // fphm=GetStringFromMap.getString(picInfo,"fphm");
            qrcheck(this.flag,infoStr,this.token,uuid,this.type);
        }else {
            qrcheck(this.flag, this.info, this.token, "",this.type);
        }
        getMd5FpdmFphm(this.token, this.fpdm, this.fphm);
        if (type.equals("1")||type.equals("4")){
            type="3";
        }
        queryInvoiceInfoByDmHm(this.token, this.fpdm, this.fphm, this.type);
    }
    public void pc_addInvoice(){
        qrcheck(this.flag, this.info, this.token, "",this.type);
        getInvoiceIdPic(this.token, this.fpdm, this.fphm, "0");
    }
    public void ocr_addInvoice(){
        qrcheck(this.flag,this.info,"",this.token,this.type);
        getMd5FpdmFphm(this.token, this.fpdm, this.fphm);
        if (type.equals("1")||type.equals("4")){
            type="3";
        }
        queryInvoiceInfoByDmHm(this.token, this.fpdm, this.fphm, this.type);
    }
    public void queryInvoiceInfo(){
        getMd5FpdmFphm(this.token, this.fpdm, this.fphm);
        if (type.equals("1")||type.equals("4")){
            type="3";
        }
        queryInvoiceInfoByDmHm(this.token, this.fpdm, this.fphm, this.type);
    }
    public void runAddInvoice_wrong(){
        qrcheck(this.flag,this.info,this.token,"",this.type);
        getInvoiceIdPic(this.token, this.fpdm, this.fphm, "1");
        String invoiceType = getInvoiceType();
        if(invoiceType.equals("1"))
            uploadPaperImg(this.token,this.uid,this.invoiceId,this.fpdm,this.fphm,this.pic);
    }
    public String getInvoiceId(){
        return this.invoiceId;
    }
    public String getInvoiceType(){ return this.invoiceType; }
    public String getMd5FPDM(){
        getMd5FpdmFphm(this.token, this.fpdm, this.fphm);
        return this.fpdm;
    }
    public String getMd5FPHM(){
        getMd5FpdmFphm(this.token, this.fpdm, this.fphm);
        return this.fphm;
    }
    public String getPaperImgUuid() { return this.imgUuid; }
    public String getInfo() {
        Map picInfo = GetPicInfo.getPicInfo(this.picture,"0",this.token);
        String infoStr = GetStringFromMap.getString(picInfo,"infoStr");
        return infoStr; }
}

