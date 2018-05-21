package dependence;

import api.DoApi;
import api.GetProperties;
import api.GetRedis;
import utils.GetGenImg;
import utils.JsonUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/9/29.
 */

public class Account {
     String id;
     String account_type;
     String account;
     String phone;
     String realname;
     String sex;
     String nick_name;
     String head_img_url;
     String city;
     String country;
     String province;
     String language;
     String lable;
     String uid;
     String create_time;
     String modify_time;
     String login_time;
     String token;
     String currentOpenId;
     String currentType;
     String currentWxOpenId;
     String img;
     String loginType;
     String userInfo;

     public Account(){
         this.account = GetProperties.GetPhone();
         this.token = GetRedis.GetWeChatToken();
         this.uid = GetRedis.GetWeChatuid();
         getUserInfo();
     }

     public String getUserInfo(){
         String ret = DoApi.dogetUserInfo(uid,token);
         return ret;
     }

    /**
     * 绑定微信公众号
     */
     public void bindingWeChat() throws InterruptedException {
         String account=GetProperties.GetPhone();
         String token=this.token;
         String oldAccount = JsonUtils.getjsondata(getUserInfo(),"data","userInfo","account");
         if(this.account.equals(oldAccount)){
             System.out.println("该手机号"+this.account+"已经与登录的微信号绑定过了！");
             return;
         }
         VerifyCode vCode = new VerifyCode(account,token,"2","1");
         String code=vCode.sendMsgAndgetSmsCode();
         String ret =DoApi.doBinding(account,token,code,"0","0");
         System.out.println(ret);
     }

    /**
     * 判断用户是否自定义头像
     */
     public boolean headImgUrl(){
         Boolean flag= false;
         setToken();
         setUid();
         System.out.println("绑定成功，更新后的用户信息为："+getUserInfo());
         String testStr=JsonUtils.getjsondata(getUserInfo(),"data","userInfo","head_img_url");
         String regex = "^http://[\\s\\S]*";
         Pattern pattern= Pattern.compile(regex);
         Matcher matcher = pattern.matcher(testStr);
         flag = matcher.matches();
         return flag;
     }

    /**
     * 更新头像图片
     */
    public String updateHeadImg(String imgPath){
        String imgBase64 = GetGenImg.GetBase64Encode(imgPath);
        String req_str = "{\"img\" : \""+imgBase64+"\"}";
        String ret = DoApi.doupdateUserInfo(this.token,req_str);
        System.out.println("返回的结果是："+ret);
        return JsonUtils.getjsondata(ret,"data","head_img_url");
    }

    /**
     * 根据是否存在uuid决定是否上传头像
     *  @param imgPath 图片文件存放路径
     */
    public String getHeadImgUuid(String imgPath){
        Boolean flag = headImgUrl();
        String uuid = null;
        if(flag==false){
            String str = getUserInfo();
            System.out.println(str);
            uuid = JsonUtils.getjsondata(getUserInfo(),"data","userInfo","head_img_url");
            System.out.println("在此之前，图片已经被更新过！");
            return uuid;
        }else {
            uuid = updateHeadImg(imgPath);
            return uuid;
        }
     }

     public void setUid(){this.uid=GetRedis.GetWeChatuid();}

     public void setToken(){this.token = GetRedis.GetWeChatToken();}
}
