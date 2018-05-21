package dependence;

import api.DoApi;
import utils.JedisUtils_231;
import utils.JedisUtils_232;

/**
 * Created by Administrator on 2017/12/20.
 */
public class VerifyCode {
    String account;
    String token;
    String type;
    String bindType;
    String typeDes;
    String bindTypeDes;
    String imgVerifyCode;
    public VerifyCode(String account, String token, String type, String bindType,String imgVerifyCode){
        this.account = account;
        this.token = token;
        this.type = type;
        this.bindType = bindType;
        this.imgVerifyCode = imgVerifyCode;
        setTypeDes();
        setBindTypeDes();
    }
    /**
     *
     * @param account : 手机号码
     * @param token : Token
     * @param type : 发送短信的类型(0:注册;1:密码修改;2:绑定,3：关联)
     * @param bindType : 绑定类型（1：新绑定手机号；2：重新绑定手机号）默认为1
     */
    public VerifyCode(String account, String token, String type, String bindType){
        this.account = account;
        this.token = token;
        this.type = type;
        this.bindType = bindType;
        setTypeDes();
        setBindTypeDes();
    }
    public VerifyCode(String account, String type,String token){
        this.account = account;
        this.token = token;
        this.type = type;
        setTypeDes();
    }
    /**
     * 得到类型并设置TypeDes
     * type: 发送短信的类型(0:注册;1:密码修改;2:绑定,3：关联)
     */
     public void setTypeDes(){
         int typeVal = Integer.parseInt(this.type);
         switch (typeVal) {
             case 0:
                 this.typeDes = "注册";
                 break;
             case 1:
                 this.typeDes = "密码修改";
                 break;
             case 2:
                 this.typeDes = "绑定";
                 break;
             case 3:
                 this.typeDes = "关联";
                 break;
         }
     }
    /**
     * bindType: 绑定类型（1：新绑定手机号；2：重新绑定手机号）默认为1
     */
    public void setBindTypeDes(){
         int bindTypeVal = Integer.parseInt(this.bindType);
         switch (bindTypeVal) {
             case 1:
                 bindTypeDes = "新绑定手机号";
                 break;
             case 2:
                 bindTypeDes = "重新绑定手机号";
                 break;
         }
     }
    /**
     * 发短信
     * 若无token和bindType发短信
     * bindType： 默认为1，新绑定手机号
     * token： 当前登录账号token,绑定账号验证时需要传当前登录账号的token，以判断该手机号是否绑定其他账号
     */
    public String sendMsg(){
        String ret=null;
       if("".equals(this.bindType) || this.bindType == null){
           ret = DoApi.dosendMsg(this.account,this.type);
           System.out.println("指令为："+this.typeDes+"，"+"新绑定手机号（默认）");
           return ret;
       }
       ret = DoApi.dosendMsg(this.account,this.token,this.type,this.bindType);
       System.out.println("指令为："+this.typeDes+"，"+this.bindTypeDes);
       return ret;
    }
    /**
     * 获取存在redis中的短信验证码*
     */
    public String getSmsCode() throws InterruptedException {
        int typeValue = Integer.parseInt(this.type);
        String typeStr = null;
        switch(typeValue) {
            case 0:
                typeStr = "regist";
                break;
            case 1:
                typeStr = "modify";
                break;
            case 2:
                typeStr = "binding";
                break;
            case 3:
                typeStr = "link";
                break;
        }
        Thread.sleep(10000);
        String code = JedisUtils_231.get(5,"verifycode:"+this.account+":"+typeStr);
        if("".equals(code) || code==null){
            System.out.println("从Redis里获取的校验码为空！");
            return code;
        }
        System.out.println("成功的从Redis里获取了校验码: "+ code);
        return code;
    }
    /**
     * 发送短信和得到短信校验码
     */
    public String sendMsgAndgetSmsCode() throws InterruptedException {
        String ret = sendMsg();
        System.out.println(ret);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (getSmsCode());
    }
}
