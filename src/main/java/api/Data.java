package api;

/**
 * Created by cch on 2017/9/1.
 */
public class Data {
    public static String AddCard(){
        return "{\n" +
                "  \"bank_account\": \"\",\n" +
                "  \"bank_name\": \"\",\n" +
                "  \"createTime\": \"\",\n" +
                "  \"email\": \"\",\n" +
                "  \"id\": \"\",\n" +
                "  \"modifyTime\": \"\",\n" +
                "  \"mpbs\": \"\",\n" +
                "  \"openId\": \"\",\n" +
                "  \"qrCodeVersion\": \"\",\n" +
                "  \"register_addr\": \"\",\n" +
                "  \"register_phone\": \"\",\n" +
                "  \"status\": \"\",\n" +
                "  \"taxpayer_id\": \"\",\n" +
                "  \"telephone\": \"\",\n" +
                "  \"title_name\": \"\",\n" +
                "  \"topFlag\": \"\",\n" +
                "  \"type\": \"\",\n" +
                "  \"uid\": \"\",\n" +
                "  \"unionId\": \"\",\n" +
                "  \"userType\": \"\"\n" +
                "}";
    }
    public static String bindingWeChat(){
        return "{\n" +
                "  \"city\": \"\",\n" +
                "  \"country\": \"\",\n" +
                "  \"headimgurl\": \"\",\n" +
                "  \"language\": \"\",\n" +
                "  \"nickname\": \"\",\n" +
                "  \"openid\": \"\",\n" +
                "  \"province\": \"\",\n" +
                "  \"sex\": \"\",\n" +
                "  \"unionid\": \"\",\n" +
                "  \"user_type\": \"\"\n" +
                "}";

    }
    public static String register(){
        return "{\n" +
                "  \"account\": \"\",\n" +
                "  \"password\": \"\",\n" +
                "  \"repassWord\": \"\",\n" +
                "  \"repassword\": \"\",\n" +
                "  \"userType\": \"\",\n" +
                "  \"verifyCode\": \"\"\n" +
                "}";
    }
    public static String appWechatLogin(){
        return "{\n" +
                "  \"city\": \"\",\n" +
                "  \"country\": \"\",\n" +
                "  \"headimgurl\": \"\",\n" +
                "  \"language\": \"\",\n" +
                "  \"nickname\": \"\",\n" +
                "  \"openid\": \"\",\n" +
                "  \"province\": \"\",\n" +
                "  \"sex\": \"\",\n" +
                "  \"unionid\": \"\",\n" +
                "  \"user_type\": \"\"\n" +
                "}";
    }
    public static String updatePassword(){
        return "{\n" +
                "  \"account\": \"\",\n" +
                "  \"password\": \"\",\n" +
                "  \"repassWord\": \"\",\n" +
                "  \"repassword\": \"\",\n" +
                "  \"userType\": \"\",\n" +
                "  \"verifyCode\": \"\"\n" +
                "}";
    }
    public static String appBinding(){
        return "{\n" +
                "  \"city\": \"string\",\n" +
                "  \"country\": \"string\",\n" +
                "  \"headimgurl\": \"string\",\n" +
                "  \"language\": \"string\",\n" +
                "  \"nickname\": \"string\",\n" +
                "  \"openid\": \"string\",\n" +
                "  \"province\": \"string\",\n" +
                "  \"sex\": \"string\",\n" +
                "  \"unionid\": \"string\",\n" +
                "  \"user_type\": \"string\"\n" +
                "}";
    }
    public static String addMemo(){
        return "{\n" +
                "  \"address\": \"\",\n" +
                "  \"category_label\": \"\",\n" +
                "  \"create_time\": \"\",\n" +
                "  \"id\": \"\",\n" +
                "  \"invoiceinfo_id\": \"\",\n" +
                "  \"memo_name\": \"\",\n" +
                "  \"modify_time\": \"\",\n" +
                "  \"open_id\": \"\",\n" +
                "  \"remindTime\": \"\",\n" +
                "  \"remind_status\": \"\",\n" +
                "  \"remind_time\": \"\",\n" +
                "  \"selectTime\": \"\",\n" +
                "  \"select_time\": \"\",\n" +
                "  \"status\": \"\",\n" +
                "  \"todo_label\": \"\",\n" +
                "  \"uid\": \"\"\n" +
                "}";
    }
    public static String modifyMemo(){
        return "{\n" +
                "  \"address\": \"\",\n" +
                "  \"category_label\": \"\",\n" +
                "  \"create_time\": \"\",\n" +
                "  \"id\": \"\",\n" +
                "  \"invoiceinfo_id\": \"\",\n" +
                "  \"memo_name\": \"\",\n" +
                "  \"modify_time\": \"\",\n" +
                "  \"open_id\": \"\",\n" +
                "  \"remindTime\": \"\",\n" +
                "  \"remind_status\": \"\",\n" +
                "  \"remind_time\": \"\",\n" +
                "  \"selectTime\": \"\",\n" +
                "  \"select_time\": \"\",\n" +
                "  \"status\": \"\",\n" +
                "  \"todo_label\": \"\",\n" +
                "  \"uid\": \"\"\n" +
                "}";
    }
    public static String uploadPaperImg(String invoiceId,String fpdm,String fphm,String picture){
        return "{\"id\":\""+invoiceId+"\",\"fpdm\":\""+fpdm+"\",\"fphm\":\""+fphm+"\",\"file\":\"data:image/jpeg;base64,"+picture+"\",\"type\":\"0\"}";
    }

    public static String trafficInvoiceCollection(){
        return "{\n" +
                "  \"create_time\": \"\",\n" +
                "  \"existPicture\": \"\",\n" +
                "  \"gjly\": \"\",\n" +
                "  \"id\": \"\",\n" +
                "  \"img_path\": \"\",\n" +
                "  \"invoice_type\": \"\",\n" +
                "  \"je\": \"\",\n" +
                "  \"kprq\": \"\",\n" +
                "  \"name\": \"\",\n" +
                "  \"read_status\": \"\",\n" +
                "  \"starting_station\": \"\",\n" +
                "  \"status\": \"\",\n" +
                "  \"terminal_station\": \"\",\n" +
                "  \"train_number\": \"\",\n" +
                "  \"train_time\": \"\",\n" +
                "  \"uid\": \"\"\n" +
                "}";
    }
}
