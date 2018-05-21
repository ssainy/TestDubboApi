package api;

import utils.HttpUtils;
import utils.ReadProper;
import utils.URLEncodeDecode;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;


/**
 * Created by cch on 2017/6/20.
 */
public class DoApi {
    public static Properties properties = ReadProper.readproper("/properties.properties");

    /*****************************************************************
     * 执行发票查验
     * @param info
     * @param token
     * @return
     ******************************************************************/
    public static String doinvoiceVerify(String info, String token, String reciptType) {
        String url = properties.getProperty("InvoiceCheckUrl") + "/secured/invoiceCheck/invoiceVerify" + "?info=" + info + "&token=" + token + "&reciptType=" + reciptType;
        return HttpUtils.executeHttpPost(url, "");
    }

    /*****************************************************************
     * 执行添加名片
     * @param type
     * @param token
     * @return
     ******************************************************************/
    public static String doaddCard(String token,String type,String req_str){
        String url=properties.getProperty("InvoiceCardUrl") + "/card/addCard"+"?token="+token+"&type="+type;
        return HttpUtils.executeHttpPost(url,req_str);
    }
    /*****************************************************************
     * 执行精确查询操作
     * @param nsrmc
     * @param ptcode
     * @return
     ******************************************************************/
    public static String doexactQuery(String nsrmc,String ptcode){
        nsrmc=URLEncodeDecode.encode(nsrmc);
        String url=properties.getProperty("InvoiceCardUrl") + "/card/exactQuery"+"?nsrmc="+nsrmc+"&ptcode="+ptcode;
        return HttpUtils.executeHttpPost(url,"");
    }
    /*****************************************************************
     * 执行模糊查询操作
     * @param nsrmc
     * @param ptcode
     * @return
     ******************************************************************/
    public static String dofuzzyQuery(String nsrmc,String ptcode){
        nsrmc=URLEncodeDecode.encode(nsrmc);
        String url=properties.getProperty("InvoiceCardUrl") + "/card/fuzzyQuery"+"?nsrmc="+nsrmc+"&ptcode="+ptcode;
        return HttpUtils.executeHttpPost(url,"");
    }
    /*****************************************************************
     * 执行精确查询操作
     * @param token
     * @return
     ******************************************************************/
    public static String doCardGetBindingPhone(String token){
        String url=properties.getProperty("InvoiceCardUrl") + "/card/getBindingPhone"+"?token="+token;
        return HttpUtils.executeHttpPost(url,"");
    }
    /*****************************************************************
     * 执行PC、公众号发票查验接口,只查验发票和保存查验信息
     * @param info
     * @param token
     * @param type
     * @param reciptType
     * @return
     ******************************************************************/
    public static String doinvoiceVerifyAndCollection(String info, String token, String type, String reciptType) {
        String url = properties.getProperty("InvoiceVerifyAndCollectionUrl") + "/secured/invoice/invoiceVerifyAndCollection"
                + "?info=" + info + "&token=" + token + "&type=" + type + "&reciptType=" + reciptType;
        return HttpUtils.executeHttpPost(url, "");

    }

    /*****************************************************************
     * 根据名片id查询出发票名片详情
     * @param id
     * @return
     ******************************************************************/
    public static String dogetTitleInfoById(String id) {
        String url = properties.getProperty("InvoiceCardUrl") + "/card/getTitleInfoById" + "?id=" + id;
        return HttpUtils.executeHttpGet(url);
    }

    /*****************************************************************
     * 根据名片id修改名片信息
     * @param newTitleInfo
     * @return
     ******************************************************************/
    public static String doModifyCard(String newTitleInfo) {
        String url = properties.getProperty("InvoiceCardUrl") + "/card/modifyCard";
        return HttpUtils.executeHttpPost(url, newTitleInfo);
    }

    /*****************************************************************
     * 通过扫描二维码获取名片信息（分享的名片及专票二维码）
     * @param qrinfo
     * @return
     ******************************************************************/
    public static String doscanQrCodeQueryInfo(String qrinfo, String token) {
        String url = properties.getProperty("InvoiceCardUrl") + "/card/scanQrCodeQueryInfo"+"?qrinfo="+qrinfo+"&token="+token;
        return HttpUtils.executeHttpPost(url, "");

    }

    /*****************************************************************
     * 展示用户所有发票名片
     * @param qybs
     * @param type
     * @return
     ******************************************************************/
    public static String doshowCardList(String qybs, String type, String token) {
        String url = properties.getProperty("InvoiceCardUrl") + "/card/showCardList"+"?qybs="+qybs+"&type="+type+"&token="+token;
        return HttpUtils.executeHttpPost(url, "");

    }

    /*****************************************************************
     * 展示用户所有发票名片
     * @param id
     * @return
     ******************************************************************/
    public static String doupdateCardStatus(String id) {
        String url = properties.getProperty("InvoiceCardUrl") + "/card/updateCardStatus"+"?id="+id;
        return HttpUtils.executeHttpPost(url, "");
    }
/**
 *account-app-controller
 *绑定微信接口
 */
public static String dobindingWeChat(String token,String req_str) {
    String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/app/bindingWeChat"+"?token="+token;
    return HttpUtils.executeHttpPost(url, req_str);
}
    /**
     *account-app-controller
     *登录接口
     */
    public static String doAppLogin(String account,String password) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/app/login"+"?account="+account+"&password="+password;
        return HttpUtils.executeHttpPost(url, "");
    }
    /**
     *account-app-controller
     *登出接口
     */
    public static String doAppLoginOut(String token) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/app/loginOut"+"?token="+token;
        return HttpUtils.executeHttpPost(url, "");
    }
    /**
     *account-app-controller
     *注册接口
     */
    public static String doAppRegister(String req_str) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/app/register";
        return HttpUtils.executeHttpPost(url, req_str);
    }
    /**
     *account-app-controller
     *保存用户channelId
     */
    public static String doSaveChannel(String token,String channelId) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/app/saveChannel"+"?token="+token+"&channelId="+channelId;
        return HttpUtils.executeHttpPost(url, "");
    }
    /**
     *account-app-controller
     *修改密码
     */
    public static String doAppUpdatePassword(String type,String token,String req_str) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/app/updatePassword"+"?type="+type+"&token="+token;
        return HttpUtils.executeHttpPost(url, req_str);
    }
    /**
     *account-app-controller
     *微信登录
     */
    public static String doAppWechatLogin(String req_str) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/app/weChatLogin";
        return HttpUtils.executeHttpPost(url, req_str);
    }
    /**
     *account-app-controller
     *获取微信openID
     */
    public static String dogetOpenId(String code) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/wx/getOpenId"+"?code="+code;
        return HttpUtils.executeHttpPost(url, "");
    }
    /**
     *account-app-controller
     *获取token
     */
    public static String dogetToken(String uid,String token,String type) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/wx/getToken"+"?uid="+uid+"&token"+token+"&type="+type;
        return HttpUtils.executeHttpPost(url, "");
    }
    /**
     *account-app-controller
     *获取微信账户信息
     */
    public static String dogetWxInfo(String openId) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/wx/getWxInfo"+"?openId="+openId;
        return HttpUtils.executeHttpGet(url);
    }
    /**
     *account-app-controller
     *获取绑定的手机号
     */
    public static String dogetbindingphone(String token) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/wx/getbindingphone"+"?token="+token;
        return HttpUtils.executeHttpGet(url);
    }
    /**
     *account-app-controller
     *判断是否关注公众号
     */
    public static String dogetwechickstatus(String token) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/wx/getwechickstatus"+"?token="+token;
        return HttpUtils.executeHttpPost(url, "");
    }
    /**
     *account-app-controller
     *登录
     */
    public static String dologin(String account,String password) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/wx/login"+"?account="+account+"&password="+password;
        return HttpUtils.executeHttpPost(url, "");
    }
    /**
     *account-app-controller
     *登录
     */
    public static String dotoAbout() {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/wx/toAbout";
        return HttpUtils.executeHttpGet(url);
    }
    /**
     *account-app-controller
     *微信登录
     */
    public static String doweChatLogin(String code) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/wx/weChatLogin?code="+code;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     *account-app-controller
     *生成UUID
     */
    public static String dogenerateUuid() {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/pc/generateUuid";
        return HttpUtils.executeHttpGet(url);
    }
    /**
     *account-app-controller
     *PC 端获取openID
     */
    public static String doPCGetOpenId(String code) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/pc/getOpenId?code="+code;
        return HttpUtils.executeHttpGet(url);
    }
    /**
     *account-app-controller
     *PC 端轮询登录接口
     */
    public static String doPollLogin(String uuid) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/pc/pollLogin?uuid="+uuid;
        return HttpUtils.executeHttpGet(url);
    }
    /**
     *account-app-controller
     *PC 端注册
     */
    public static String doPCRegister(String req_str) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/pc/register";
        return HttpUtils.executeHttpPost(url,req_str);
    }
    /**
     *account-app-controller
     *PC 端修改密码
     */
    public static String doPCUpdatePassword(String type,String token,String req_str) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/pc/updatePassword?type="+type+"&token="+token;
        return HttpUtils.executeHttpPost(url,req_str);
    }
    /**
     *account-app-controller
     *PC 端微信登录
     */
    public static String doPCWeChatLoginJQ(String uuid) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/pc/weChatLoginJQ?uuid="+uuid;
        return HttpUtils.executeHttpGet(url);
    }
    /**
     *account-app-controller
     *PC 端扫码登录
     */
    public static String doPCWeChatLoginSM2(String uuid,String code) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/pc/weChatLoginSM2?uuid="+uuid+"&code="+code;
        return HttpUtils.executeHttpGet(url);
    }
    /**
     *account-app-controller
     *第三方（微信）绑定手机接口
     */
    public static String doAPPBinding(String account,String token,String code,String platform,String source,String weChatInfo) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/app/binding?account="+account+"&token="+token+"&code="+code+"&platform="+platform+"&source="+source;
        return HttpUtils.executeHttpPost(url,weChatInfo);
    }
    /**
     *binding-controller
     *第三方（微信）绑定手机接口
     */
    public static String doBinding(String account,String token,String code,String platform,String source) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/binding?account="+account+"&token="+token+"&code="+code+"&platform="+platform+"&source="+source;
        System.out.println("发送的请求时:"+url);
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     *link-controller
     *账号关联接口
     */
    public static String dolink(String linkAccount,String linkType,String verifyCode,String token) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/link?linkAccount="+linkAccount+"&linkType="+linkType+"&verifyCode="+verifyCode+"&token="+token;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     *link-controller
     *账号取消关联接口
     */
    public static String dolinkCancel(String linkAccount,String linkType,String token) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/link/cancel?linkAccount="+linkAccount+"&linkType="+linkType+"&token="+token;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     *link-controller
     *获取关联账号列表
     */
    public static String douserLinkList(String token,String linkType) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/link/userLinkList?token="+token+"&linkType="+linkType;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * my-invoice-sign-controller : 签到相关接口
     *获取7天连续签到信息
     */
    public static String dogetContinuitySign(String token) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/signin/getContinuitySign?token="+token;
        return HttpUtils.executeHttpGet(url);
    }
    /**
     * my-invoice-sign-controller : 签到相关接口
     *获取当天是否签到
     */
    public static String dogetNowSignInfo(String token) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/signin/getNowSignInfo?token="+token;
        return HttpUtils.executeHttpGet(url);
    }
    /**
     * my-invoice-sign-controller : 签到相关接口
     * 新增签到
     */
    public static String dosaveSignInfo(String token,String source_type) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/signin/saveSignInfo?token="+token+"&source_type="+source_type;
        return HttpUtils.executeHttpGet(url);
    }
    /**
     * account-user-controller : 用户账号信息接口 1、修改个人信息的接口 2、获取用户当前个人信息的接口 3、下载图片的接口
     * 下载图片的接口
     */
    public static byte[] dodownloadImg(String uuid) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/user/downloadImg/"+uuid;
        return HttpUtils.executeHttpGet2(url);
    }
    /**
     * account-user-controller : 用户账号信息接口 1、修改个人信息的接口 2、获取用户当前个人信息的接口 3、下载图片的接口
     * 获取用户绑定信息列表的接口
     */
    public static String dogetBindingListInfo(String token) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/user/getBindingListInfo?token="+token;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * account-user-controller : 用户账号信息接口 1、修改个人信息的接口 2、获取用户当前个人信息的接口 3、下载图片的接口
     * 获取用户当前个人信息
     */
    public static String dogetUserInfo(String uid,String token) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/user/getUserInfo?uid="+uid+"&token="+token;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * account-user-controller : 用户账号信息接口 1、修改个人信息的接口 2、获取用户当前个人信息的接口 3、下载图片的接口
     * 修改个人信息接口
     */
    public static String doupdateUserInfo(String token,String req_str) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/user/updateUserInfo?token="+token;
        return HttpUtils.executeHttpPost(url,req_str);
    }
    /**
     * verify-code-controller : 验证码相关接口
     * 验证码校验接口
     */
    public static String docheckVerifyCode(String account,String code,String type) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/verifycode/checkVerifyCode?account="+account+"&code="+code+"&type="+type;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * verify-code-controller : 验证码相关接口
     * 验证码校验接口
     */
    public static String docheckverifycodesend(String account,String token,String type) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/verifycode/checkverifycodesend?account="+account+"&token="+token+"&type="+type;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * verify-code-controller : 验证码相关接口
     * 发送邮箱验证码
     */
    public static String dosendEmail(String account,String type) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/verifycode/sendEmail?account="+account+"&type="+type;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * verify-code-controller : 验证码相关接口
     * 发送短信验证码
     */
    public static String dosendMsg(String account,String token,String type,String bindType,String imgVerifyCode) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/verifycode/sendMsg?account="+account+"&token="+token+"&type="+type+"&bindType="+bindType+"&imgVerifyCode="+imgVerifyCode;
        return HttpUtils.executeHttpPost(url,"");
    }

    /**
     * 重载DosendMsg方法去掉图片验证码
     */
    public static String dosendMsg(String account,String token,String type,String bindType) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/verifycode/sendMsg?account="+account+"&token="+token+"&type="+type+"&bindType="+bindType;
        return HttpUtils.executeHttpPost(url,"");
    }

    /**
     * 重载DosendMsg方法去掉图片验证码和绑定类型
     */
    public static String dosendMsg(String account,String type) {
        String url = properties.getProperty("MyinvoiceAccountUrl") + "/account/verifycode/sendMsg?account="+account+"&type="+type+"&token="+GetRedis.GetWeChatToken();
        System.out.println("url:"+url);
        return HttpUtils.executeHttpPost(url,"");
    }

    /**
     *my-integration-controller : 积分相关接口
     * 积分明细
     */
    public static String dogetIntegrationList(String token,String page,String limit) {
        String url = properties.getProperty("MyinvoiceIntegrationUrl") + "/integration/getIntegrationList?token="+token+"&page="+page+"&limit="+limit;
        return HttpUtils.executeHttpGet(url);
    }
    /**
     *my-integration-controller : 积分相关接口
     * 获取总积分排名和当天积分信息
     */
    public static String dogetIntegretionInfo(String token) {
        String url = properties.getProperty("MyinvoiceIntegrationUrl") + "/integration/getIntegretionInfo?token="+token;
        return HttpUtils.executeHttpGet(url);
    }
    /**
     *my-integration-controller : 积分相关接口
     * 获取积分规则列表
     */
    public static String dogetIntegretionRule(String token) {
        String url = properties.getProperty("MyinvoiceIntegrationUrl") + "/integration/getIntegretionRule?token="+token;
        return HttpUtils.executeHttpPost(url,"");
    }

    /**
     * my-invoice-ranking-controller : 排行榜相关接口
     * 获取个人排行及本周发票信息
     */
    public static String dogetRankingInfo(String token,String pageSize,String page,String binkingCount) {
        String url = properties.getProperty("MyInvoiceRankingUrl") + "/ranking/getRinkingInfo"+"?token="+token+"&pageSize="+pageSize+"&page="+page+"&binkingCount="+binkingCount;
        return HttpUtils.executeHttpPost(url,"");
    }

    /**
     * my-invoice-ranking-controller : 排行榜相关接口
     * 获取个人排行及本周发票信息
     */
    public static String dogetTopThree(String token,String binkingCount) {
        String url = properties.getProperty("MyInvoiceRankingUrl") + "/ranking/getTopThree"+"?token="+token+"&binkingCount="+binkingCount;
        return HttpUtils.executeHttpPost(url,"");
    }

    /**
     *memo-randum-controller : 备忘录(增删改查)
     * 添加记一记
     */
    public static String doaddMemo(String req_str) {
        String url = properties.getProperty("MemoUrl") + "/invoiceDetail/memorandum/addMemo";
        return HttpUtils.executeHttpPost(url,req_str);
    }
    /**
     *memo-randum-controller : 备忘录(增删改查)
     * 删除备忘录
     */
    public static String dodelMemo(String token,String memo_id) {
        String url = properties.getProperty("MemoUrl") + "/invoiceDetail/memorandum/delMemo?token="+token+"&memo_id="+memo_id;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     *memo-randum-controller : 备忘录(增删改查)
     * 修改备忘录信息
     */
    public static String domodifyMemo(String req_str) {
        String url = properties.getProperty("MemoUrl") + "/invoiceDetail/memorandum/modifyMemo";
        return HttpUtils.executeHttpPost(url,req_str);
    }

    /**
     *memo-randum-controller : 备忘录(增删改查)
     * 根据id查询数据
     */
    public static String doqueryMemo(String token,String memo_id) {
        String url = properties.getProperty("MemoUrl") + "/invoiceDetail/memorandum/queryMemo?token="+token+"&memo_id="+memo_id;
        return HttpUtils.executeHttpPost(url,"");
    }

    /**
     *memo-randum-controller : 备忘录(增删改查)
     * 通过发票id,uid获取记一记信息
     */
    public static String dogetMemoList(String token,String invoiceInfoId,String invoiceType) {
        String url = properties.getProperty("MemoUrl") + "/invoiceDetail/memorandum/getMemoList?invoiceInfoId="+invoiceInfoId+"&token="+token+"&invoiceType="+invoiceType;
        return HttpUtils.executeHttpPost(url,"");
    }

    /**
     *memo-randum-controller : 备忘录(增删改查)
     * 根据id更新提醒时间
     */
    public static String doupdateByRemindTime(String remindTime,String memo_id) {
        String url = properties.getProperty("MemoUrl") + "/invoiceDetail/memorandum/updateByRemindTime?remindTime="+remindTime+"&memo_id="+memo_id;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     *file-controller : 下载图片
     *下载文件获取文件数据字符串
     */
    public static String dodownLoadFile(String uuid){
        String url = properties.getProperty("InvoiceUrl") + "/file/download/local/"+uuid;
        return HttpUtils.executeHttpGet(url);
    }
    /**
     * MyInvoice-Invoice Api
     *file-controller : 下载图片
     *下载纸票图片
     */
    public static String dodownLoadImage(String suffix,String uuid){
        String url = properties.getProperty("InvoiceUrl") + "/file/download/local/"+suffix+"/"+uuid;
        return HttpUtils.executeHttpGet(url);
    }
    /**
     * MyInvoice-Invoice Api
     *file-controller : 下载图片
     *下载PDF
     */
    public static String dodownLoadPDF(String suffix,String uuid){
        String url = properties.getProperty("InvoiceUrl") + "/file/download/local/"+uuid;
        return HttpUtils.executeHttpGet(url);
    }
    /**
     * MyInvoice-Invoice Api
     * file-controller : 下载图片
     * 根据uuid预览图片
     */
    public static String doLoadImageByUUID(String uuid){
        String url = properties.getProperty("InvoiceUrl") + "/file/load/"+uuid;
        return HttpUtils.executeHttpGet(url);
    }
    /**
     * MyInvoice-Invoice Api
     * invoice-check-controller : 查验并归集
     * PC、公众号发票查验接口,只查验发票和保存查验信息
     */
    public static String doinvoiceVerifyAndCollection_Invoice(String info,String token,String type,String reciptType){
        String url = properties.getProperty("InvoiceUrl") + "/secured/invoice/invoiceVerifyAndCollection?info="+info+"&token="+token+"&type="+type+"&reciptType="+reciptType;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * invoice-collection-controller : 删除发票;修改（覆盖）错票信息
     * 根据id进行删除发票
     */
    public static String dodeleteInvoice_Invoice(String invoiceId,String token,String type){
        String url = properties.getProperty("InvoiceUrl") + "/secured/invoice/deleteInvoice?invoiceId="+invoiceId+"&token="+token+"&type="+type;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * invoice-collection-controller : 覆盖qrinfo
     * 修改（覆盖错票信息）
     */
    public static String doupdateQrInfo(String info,String qrId,String imgKey,String token ){
        String url = properties.getProperty("InvoiceUrl") + "/secured/invoice/updateQrInfo?info="+info+"&qrId="+qrId+"&imgKey="+imgKey+"&token="+token;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * invoice-collection-controller : 删除发票;修改（覆盖）错票信息
     * 覆盖qrinfo
     */
    public static String doupdateQrInfo(String info,String qrId){
        String url = properties.getProperty("InvoiceUrl") + "/secured/invoice/updateQrInfo?info="+info+"&qrId="+qrId;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * invoice-list-controller : 发票列表;APP端发票列表
     * 票夹查询发票列表(新增获取发票列表返回接口增加已读和未读发票列表)
     */
    public static String dogetInvoiceList(String token,String userSettingType){
        String url = properties.getProperty("InvoiceUrl") + "/secured/getInvoiceList?token="+token+"&userSettingType="+userSettingType;
        return HttpUtils.executeHttpPost(url,"");
    }

    /**
     * MyInvoice-Invoice Api
     * invoice-detail-controller : 加密发票代码、发票号码、分享者uid、交通票id
     */
    public static String dogetMd5FpdmFphm( String token, String fpdm, String fphm, String shareUid,String trafficId){
        String url = properties.getProperty("InvoiceUrl")+"/invoice/detail/getMd5FpdmFphm?token="+token+"&fpdm="+fpdm+
                "&fphm="+fphm+"&shareUid="+shareUid+"&trafficId="+trafficId;
        return HttpUtils.executeHttpPost(url, "");
    }
    /**
     * MyInvoice-Invoice Api
     * invoice-detail-controller : 加密发票代码、发票号码、分享者uid、交通票id
     */
    public static String dogetDmHmByInvoiceId( String token, String invoiceId){
        String url = properties.getProperty("InvoiceUrl")+"/invoice/detail/getDmHmByInvoiceId?token="+token+"&invoiceId="+invoiceId;
        return HttpUtils.executeHttpPost(url, "");
    }
    /**
     * MyInvoice-Invoice Api
     * invoice-detail-controller : 加密发票代码、发票号码、分享者uid、交通票id
     */
    public static String dogetInvoiceListById( String token, String invoiceId){
        String url = properties.getProperty("InvoiceUrl")+"/invoice/detail/getInvoiceListById?token="+token+"&invoiceId="+invoiceId;
        return HttpUtils.executeHttpPost(url, "");
    }

    /**
     * MyInvoice-Invoice Api
     * invoice-detail-controller : 通过代码号码获取发票详情
     */
    public static String doqueryInvoiceInfoByDmHm( String token, String fpdm, String fphm,String type){
        String url = properties.getProperty("InvoiceUrl")+"/invoice/detail/queryInvoiceInfoByDmHm?token="+token+"&fpdm="+fpdm+
                "&fphm="+fphm+"&type="+type;
        System.out.println("发送的请求是:"+url);
        return HttpUtils.executeHttpPost(url, "");
    }

    /**
     * MyInvoice-Invoice Api
     * invoice-detail-controller : 在PC端通过代码号码获取发票详情
     */
    public static String doqueryInvoiceInfoByDmhmAtPc( String token, String fpdm, String fphm,String type){
        String url = properties.getProperty("InvoiceUrl")+"/invoice/detail/queryInvoiceInfoByDmhmAtPc?fpdm="+fpdm+
                "&fphm="+fphm+"&type="+type+"&token="+token;
        System.out.println("发送的请求是:"+url);
        return HttpUtils.executeHttpPost(url, "");
    }

    /**
     * MyInvoice-Invoice Api
     * invoice-detail-controller : 根据发票ID查询发票详情
     */
    public static String doqueryInvoiceInfoById(String token, String invoiceId, String invoiceType){
        String url = properties.getProperty("InvoiceUrl")+"/invoice/detail/queryInvoiceInfoById?token="+token+"&invoiceId="+invoiceId+"&invoiceType="+invoiceType;
        System.out.println("发送的请求是:"+url);
        return HttpUtils.executeHttpPost(url, "");
    }

    /**
     * MyInvoice-Invoice Api
     * invoice-detail-controller : 更新发票读取状态,data为更新成功数量
     */
    public static String doupdateInvoiceReadStatus( String token, String fpdm, String fphm){
        String url = properties.getProperty("InvoiceUrl")+"/invoice/detail/updateInvoiceReadStatus?token="+token+"&fpdm="+fpdm+"&fphm="+fphm;
        System.out.println("发送的请求是:"+url);
        return HttpUtils.executeHttpPost(url, "");
    }

    /**
     * MyInvoice-Invoice Api
     * invoice-qr-check-controller : 二维码校验
     */
    public static String doqrcheck( String flag, String infoStr, String token, String uuid){
        String url = properties.getProperty("InvoiceUrl")+"/invoice/check/qrcheck?flag="+flag+"&infoStr="+infoStr+"&token="+token+"&uuid="+uuid;
        System.out.println("发送的请求是:"+url);
        return HttpUtils.executeHttpPost(url, "");
    }

    /**
     * MyInvoice-Invoice Api
     * uploadPaperImg : 上传纸质发票图片
     */
    public static String douploadPaperImg(String token, String req_str){
        String url = properties.getProperty("InvoiceUrl")+"/secured/previrewDownload/uploadPaperImg?token="+token;
        return HttpUtils.executeHttpPost(url, req_str);
    }

    /**
     * MyInvoice-Invoice Api
     * updateInvoiceReadStatus: 更新发票读取状态,data为更新成功数量
     */
     public static String doupdateInvoiceStatus(String token,String fpdm, String fphm){
         String url = properties.getProperty("InvoiceUrl")+"/invoice/detail/updateInvoiceReadStatus?token="+token+"&fpdm="+fpdm+
                 "&fphm="+fphm;
         return HttpUtils.executeHttpPost(url, "");
     }
    /**
     * invoice-list-controller : 发票列表;发票列表
     * 票夹查询发票列表(新增获取发票列表返回接口增加已读和未读发票列表)
     */
    public static String dogetInvoiceList(String token,String cxrq, String fplb,String checkStatus,String todo,String xfmc,String userSettingType,String fromShareUid,String invoiceIds,String trafficIds,String flag){
        String url = properties.getProperty("InvoiceUrl")+"/secured/getInvoiceList?token="+token+"&cxrq="+cxrq+
                "&fplb="+fplb+
                "&checkStatus="+checkStatus+
                "&todo="+todo+
                "&xfmc="+xfmc+
                "&userSettingType="+userSettingType+
                "&fromShareUid="+fromShareUid +
                "&invoiceIds="+invoiceIds +
                "&trafficIds="+trafficIds +
                "&flag="+flag;
        return HttpUtils.executeHttpPost(url, "");
    }
    /**
     * invoice-list-controller : 发票列表;APP端发票列表
     * 票夹查询发票列表(新增获取发票列表返回接口增加已读和未读发票列表)
     */
    public static String dogetAPPInvoiceList(String token, String fplb,String num,String xfmc){
        String url = properties.getProperty("InvoiceUrl")+"/secured/appInvoiceList?token="+token+
                "&fplb="+fplb+
                "&xfmc="+xfmc+
                "&num="+num;
        return HttpUtils.executeHttpPost(url, "");
    }
    /**
     * invoice-list-controller : 发票列表;APP端发票列表
     * 票夹查询发票列表(新增获取发票列表返回接口增加已读和未读发票列表)
     */
    public static String dogetAPPInvoiceList(String token){
        String url = properties.getProperty("InvoiceUrl")+"/secured/appInvoiceList?token="+token;
        return HttpUtils.executeHttpPost(url, "");
    }
    /**
     * invoice-list-controller : 发票列表;APP端发票列表
     * 票夹查询发票列表(新增获取发票列表返回接口增加已读和未读发票列表)
     */
    public static String doInvoiceList(String token, String cxrq,String fplb,String checkStatus,String todo){
        String url = properties.getProperty("InvoiceUrl")+"/secured/invoiceList?token="+token+
                "&cxrq="+cxrq+
                "&fplb="+fplb+
                "&checkStatus="+checkStatus+
                "&todo="+todo;
        return HttpUtils.executeHttpPost(url, "");
    }
    /**
     * invoice-list-controller : 发票列表;APP端发票列表
     * 票夹查询发票列表(新增获取发票列表返回接口增加已读和未读发票列表)
     */
    public static String doInvoiceList(String token){
        String url = properties.getProperty("InvoiceUrl")+"/secured/getInvoiceList?token="+token;
        return HttpUtils.executeHttpPost(url, "");
    }
    /**
     * invoice-list-controller : 发票列表;APP端发票列表
     * 获取用户之前设置
     */
    public static String dosetUserSetting(String token,String settingType,String settingValue){
        String url = properties.getProperty("InvoiceUrl")+"/secured/invoice/setUserSetting?token="+token+"&settingType="+settingType+"&settingValue="+settingValue;
        return HttpUtils.executeHttpPost(url, "");
    }
    /**
     * invoice-list-controller : 发票列表;APP端发票列表
     * 获取用户之前设置
     */
    public static String dogetUserSetting(String token){
        String url = properties.getProperty("InvoiceUrl")+"/secured/invoice/getUserSetting?token="+token;
        return HttpUtils.executeHttpPost(url, "");
    }
    /**
     * MyInvoice-Invoice Api
     * /invoice/detail/updatepaperimg:  根据缓存的图片更新纸票图片，用于ocr查验/新增 发票覆盖附件
     */
    public static String doupdatepaperimg(String uuid,String fpdm, String fphm, String token){
        String url = properties.getProperty("InvoiceUrl")+"/invoice/detail/updatepaperimg?token="+token+"&fpdm="+fpdm+"&fphm="+fphm+"&uuid="+uuid;
        return HttpUtils.executeHttpPost(url, "");
    }

    /**
     * MyInvoice-Invoice Api
     * /invoice/ocr/invoiceRecognitionByOcr  OCR识别发票信息
     */
    public static String doinvoiceRecognitionByOcr(String token, String type, Map<String,String> map) throws IOException {
        String url = properties.getProperty("InvoiceUrl")+"/invoice/ocr/invoiceRecognitionByOcr?token="+token+"&type="+type;
        return HttpUtils.executeHttpPost2(url, map);
    }
    public static String doinvoiceRecognitionByOcr(Map<String,String> map) throws IOException {
        String url = "http://10.1.1.98:8867/bigData/ocr";
        return HttpUtils.executeHttpPost2(url, map);
    }

    /**
     * MyInvoice-Invoice Api
     * invoice-qr-check-controller : 二维码校验
     * /invoice/check/ocrcheckcollcetion?token=
     */
    public static String doocrcheckcollcetion(String token,String platform,String img){
        String url = properties.getProperty("InvoiceUrl")+"/invoice/check/ocrcheckcollcetion?token="+token+"&platform="+platform;
        return HttpUtils.executeHttpPost(url, img);
    }
    /**
     * MyInvoice-Invoice Api
     * invoice-qr-check-controller : 二维码校验
     * /invoice/check/qrcheck
     */
    public static String doqrcheck(String flag,String infoStr, String token,String uuid,String epdf,String clientType){
        String url = properties.getProperty("InvoiceUrl")+"/invoice/check/qrcheck?flag="+flag+"&infoStr="+infoStr+"&token="+token+"&uuid="+uuid+"&clientType="+clientType;
        return HttpUtils.executeHttpPost(url, "");
    }
    /**
     * MyInvoice-Invoice Api
     * previrew-pdf-controller : 发票预览(pdf/png);发送邮件;上传纸质图片(pdf/png);删除纸质发票
     * /secured/previrewDownload/card/sendEmail
     */
    public static String dosendEmail_previrewPDF(String invoiceId,String emailAddress){
        String url = properties.getProperty("InvoiceUrl")+"/secured/previrewDownload/card/sendEmail?invoiceId="+invoiceId+"&emailAddress="+emailAddress;
        return HttpUtils.executeHttpPost(url, "");
    }
    /**
     * MyInvoice-Invoice Api
     * previrew-pdf-controller : 发票预览(pdf/png);发送邮件;上传纸质图片(pdf/png);删除纸质发票
     * 删除纸质发票图片
     */
    public static String dodeletePaperImg_previrewPDF(String token,String invoiceInfoId){
        String url = properties.getProperty("InvoiceUrl")+"/secured/previrewDownload/deletePaperImg?token="+token+"&invoiceInfoId="+invoiceInfoId;
        return HttpUtils.executeHttpGet(url);
    }
    /**
     * MyInvoice-Invoice Api
     * previrew-pdf-controller : 发票预览(pdf/png);发送邮件;上传纸质图片(pdf/png);删除纸质发票
     * 获取用户邮箱
     */
    public static String dogetEmail_previrewPDF(String token){
        String url = properties.getProperty("InvoiceUrl")+"/secured/previrewDownload/getEmail?token="+token;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * previrew-pdf-controller : 发票预览(pdf/png);发送邮件;上传纸质图片(pdf/png);删除纸质发票
     * 调用大数据接口获取pdf
     */
    public static String dopreviewpdf_previrewPDF(String fpdm,String fphm,String token){
        String url = properties.getProperty("InvoiceUrl")+"/secured/previrewDownload/previewpdf?fpdm="+fpdm+"&fphm="+fphm+"&token="+token;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * previrew-pdf-controller : 发票预览(pdf/png);发送邮件;上传纸质图片(pdf/png);删除纸质发票
     * 根据id获取纸质发票图片
     */
    public static String doqueryPaperImg_previrewPDF(String invoice_id,String token){
        String url = properties.getProperty("InvoiceUrl")+"/secured/previrewDownload/queryPaperImg?invoice_id="+invoice_id+"&token="+token;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * previrew-pdf-controller : 发票预览(pdf/png);发送邮件;上传纸质图片(pdf/png);删除纸质发票
     * 上传纸质发票图片
     */
    public static String douploadPaperImg_previrewPDF(String token,String req_str){
        String url = properties.getProperty("InvoiceUrl")+"/secured/previrewDownload/uploadPaperImg?token="+token;
        return HttpUtils.executeHttpPost(url,req_str);
    }
    /**
     * MyInvoice-Invoice Api
     * previrew-pdf-controller : 发票预览(pdf/png);发送邮件;上传纸质图片(pdf/png);删除纸质发票
     * 上传pdf的接口
     */
    public static String douploadPdfForPc(String fpdm,String fphm,String token){
        String url = properties.getProperty("InvoiceUrl")+"/secured/previrewDownload/uploadPdfForPc?fphm="+fpdm+"&fphm="+fphm+"&token="+token;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * share-invoice-controller : 分享发票
     * (积分)分享增票
     */
    public static String dogetIntegration(String token,String fpdm,String fphm,String clientType){
        String url = properties.getProperty("InvoiceUrl")+"/secured/invoice/getIntegration?token="+token+"&fpdm="+fpdm+"&fphm="+fphm+"&clientType="+clientType;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * share-invoice-controller : 分享发票
     分享给我的用户列表
     */
    public static String dogetShareAccountForme(String token){
        String url = properties.getProperty("InvoiceUrl")+"/secured/invoice/getShareAccountForme?token="+token;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * share-invoice-controller : 分享发票
     * 发票分享这列表
     */
    public static String dogetShareList(String token){
        String url = properties.getProperty("InvoiceUrl")+"/secured/invoice/getShareList?token="+token;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * share-invoice-controller : 分享发票
     * 电子发票分享至公众号
     */
    public static String doinvoiceShareReal(String token,String fpdm,String fphm,String trafficId,String shareUid,String invoiceType,String clientType){
        String url = properties.getProperty("InvoiceUrl")+"/secured/invoice/invoiceShareReal?token="+token+"&fpdm="+fpdm+"&fphm="+fphm+"&trafficId="+trafficId+"&shareUid="+shareUid+"&invoiceType="+invoiceType+"&clientType="+clientType;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * new-print-pdf-controller : 根据打印码获取pdf/png;显示图片/pdf;批量打印;导出 excel
     * 根据mongoDB中PDF的uuid下载PDF
     * /newInvoicePrint/downloadPdfCheck
     */
    public static String dodownloadPdfCheck(String fpdm,String fphm,String jym){
        String url = properties.getProperty("InvoiceUrl")+"/newInvoicePrint/downloadPdfCheck?fpdm="+fpdm+"&fphm="+fphm+"&jym="+jym;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * new-print-pdf-controller : 根据打印码获取pdf/png;显示图片/pdf;批量打印;导出 excel
     * 根据mongoDB中PDF的uuid下载PDF
     * /newInvoicePrint/getImg
     */
    public static String dogetImg(String printCode){
        String url = properties.getProperty("InvoiceUrl")+"/newInvoicePrint/getImg";
        return HttpUtils.executeHttpPost(url,printCode);
    }
    /**
     * MyInvoice-Invoice Api
     * new-print-pdf-controller : 根据打印码获取pdf/png;显示图片/pdf;批量打印;导出 excel
     * 根据mongoDB中图片的uuid显示图片
     * /newInvoicePrint/getImgs
     */
    public static String dogetImgs(String fpdm,String fphm,String jym){
        String url = properties.getProperty("InvoiceUrl")+"/newInvoicePrint/getImgs?fpdm="+fpdm+"&fphm="+fphm+"&jym="+jym;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * new-print-pdf-controller : 根据打印码获取pdf/png;显示图片/pdf;批量打印;导出 excel
     * 获取打印码
     * /newInvoicePrint/getPrintCode
     */
    public static String dogetPrintCode(String type,String fpdm,String fphm,String jym){
        String url = properties.getProperty("InvoiceUrl")+"/newInvoicePrint/getPrintCode?type="+type+"&fpdm="+fpdm+"&fphm="+fphm+"&jym="+jym;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * print-pdf-controller : 根据打印码获取pdf/png;显示图片/pdf;批量打印;导出 excel
     * 批量获取pdf_uuid和png_uuid
     * /invoicePrint/batchPrint
     */
    public static String dobatchPrint(String token,String invoiceList){
        invoiceList=URLEncodeDecode.encode(invoiceList);
        String url = properties.getProperty("InvoiceUrl")+"/invoicePrint/batchPrint?token="+token+"&invoiceList="+invoiceList;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * print-pdf-controller : 根据打印码获取pdf/png;显示图片/pdf;批量打印;导出 excel
     * 批量获取pdf_uuid和png_uuid
     * /invoicePrint/exportExcel
     */
    public static String doexportExcel(String token,String invoiceList){
        invoiceList=URLEncodeDecode.encode(invoiceList);
        String url = properties.getProperty("InvoiceUrl")+"/invoicePrint/exportExcel?token="+token+"&invoiceList="+invoiceList;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * print-pdf-controller : 根据打印码获取pdf/png;显示图片/pdf;批量打印;导出 excel
     * 根据mongoDB中图片的uuid显示图片
     * /invoicePrint/showImg
     */
    public static String doshowImg(String uuid){
        String url = properties.getProperty("InvoiceUrl")+"/invoicePrint/showImg?uuid="+uuid;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * print-pdf-controller : 根据打印码获取pdf/png;显示图片/pdf;批量打印;导出 excel
     * 获取打印码
     * /invoicePrint/showPrint?type=
     */
    public static String doshowPrint(String type,String pdfUuid,String pngUuid){
        String url = properties.getProperty("InvoiceUrl")+"/invoicePrint/showPrint?type="+type+"&pdfUuid="+pdfUuid+"&pngUuid="+pngUuid;
        return HttpUtils.executeHttpPost(url,"");
    }

    /**
     * MyInvoice-Invoice Api
     * ocr-service-controller : OCR识别发票信息
     * /invoice/ocr/trafficInvoiceCollection
     * 交通发票归集
     */
    public static String dotrafficInvoiceCollection(String token,String uuid, String flag, String req_str) {
        String url = properties.getProperty("InvoiceUrl") + "/invoice/ocr/trafficInvoiceCollection?token="+token+"&uuid="+uuid+"&flag="+flag;
        return HttpUtils.executeHttpPost(url,req_str);
    }

    /**
     * MyInvoice-Invoice Api
     * traffic-invoice-controller : 交通票相关接口
     * /traffic/deleteTrafficImg
     * 删除交通票图片
     */
    public static String dodeleteTrafficImg(String token,String id) {
        String url = properties.getProperty("InvoiceUrl") + "/traffic/deleteTrafficImg?id="+id+"&token="+token;
        return HttpUtils.executeHttpPost(url,"");
    }

    /**
     * MyInvoice-Invoice Api
     * traffic-invoice-controller : 交通票相关接口
     * /traffic/deleteTrafficInvoice
     * 根据id删除对应交通票
     */
    public static String dodeleteTrafficInvoice(String token,String id) {
        String url = properties.getProperty("InvoiceUrl") + "/traffic/deleteTrafficInvoice?id="+id+"&token="+token;
        return HttpUtils.executeHttpPost(url,"");
    }

    /**
     * MyInvoice-Invoice Api
     * traffic-invoice-controller : 交通票相关接口
     * /traffic/downloadTrafficImg
     * 下载交通票图片
     */
    public static byte[] dodownloadTrafficImg(String token,String imagePath,String suffix) {
        String url = properties.getProperty("InvoiceUrl") + "/traffic/downloadTrafficImg/"+suffix+"/"+imagePath+"?token="+token;
        return HttpUtils.executeHttpGet2(url);
    }

    /**
     * MyInvoice-Invoice Api
     * traffic-invoice-controller : 交通票相关接口
     * /traffic/queryTrafficInfo
     * 通过交通票表的主键id获取车票信息
     */
    public static String doqueryTrafficInfo(String token,String id) {
        String url = properties.getProperty("InvoiceUrl") + "/traffic/queryTrafficInfo?token="+token+"&id="+id;
        return HttpUtils.executeHttpPost(url,"");
    }

    /**
     * MyInvoice-Invoice Api
     * traffic-invoice-controller : 交通票相关接口
     * /traffic/updateTrafficInvoiceReadStatus
     * 更新交通票读取状态
     */
    public static String doupdateTrafficInvoiceReadStatus(String token,String id) {
        String url = properties.getProperty("InvoiceUrl") + "/traffic/updateTrafficInvoiceReadStatus?token="+token+"&id="+id;
        return HttpUtils.executeHttpPost(url,"");
    }

    /**
     * MyInvoice-Invoice Api
     * traffic-invoice-controller : 交通票相关接口
     * /traffic/uploadpicture
     * 上传交通票图片
     */
    public static String douploadpicture(String token, String id, Map<String,String> map) throws IOException {
        String url = properties.getProperty("InvoiceUrl") + "/traffic/uploadpicture?id="+id+"&token="+token;
        return HttpUtils.executeHttpPost2(url,map);
    }
    /**
     * MyInvoice-Invoice Api
     * batch-share-controller : 批量分享发票
     * /secured/batchShareInvoice/ShareInvoiceToPhone?token=
     * 通过手机号分享发票_分享交通票
     */
    public static String doshareInvoiceToPhone_traffic(String token, String random,String phone,String trafficIds,String clientType) throws IOException {
        String url = properties.getProperty("InvoiceUrl") + "/secured/batchShareInvoice/ShareInvoiceToPhone?token="+token+"&random="+random+"&phone="+phone+"&trafficIds="+trafficIds+"&clientType="+clientType;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * batch-share-controller : 批量分享发票
     * /secured/batchShareInvoice/ShareInvoiceToPhone?token=
     * 通过手机号分享发票——分享增值税发票
     */
    public static String doshareInvoiceToPhone_invoice(String token, String random,String phone,String invoiceIds,String clientType) throws IOException {
        String url = properties.getProperty("InvoiceUrl") + "/secured/batchShareInvoice/ShareInvoiceToPhone?token="+token+"&random="+random+"&phone="+phone+"&invoiceIds="+invoiceIds+"&clientType="+clientType;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * batch-share-controller : 批量分享发票
     * /secured/batchShareInvoice/getBatchShareList?token=
     * 获取批量分享历史
     */
    public static String dogetBatchShareList(String token, String random,String page,String limit,String clientType) throws IOException {
        String url = properties.getProperty("InvoiceUrl") + "/secured/batchShareInvoice/getBatchShareList?token="+token+"&random="+random+"&page="+page+"&limit="+limit+"&clientType="+clientType;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * batch-share-controller : 批量分享发票
     * /secured/batchShareInvoice/getShareRedisKey?token=
     * 通过微信分享发票_分享交通票
     */
    public static String dogetShareRedisKey_traffic(String token, String random,String phone,String trafficIds,String clientType) throws IOException {
        String url = properties.getProperty("InvoiceUrl") + "/secured/batchShareInvoice/getShareRedisKey?token="+token+"&random="+random+"&phone="+phone+"&trafficIds="+trafficIds+"&clientType="+clientType;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * batch-share-controller : 批量分享发票
     * /secured/batchShareInvoice/getShareRedisKey?token=
     * 通过微信分享发票——分享增值税发票
     */
    public static String dogetShareRedisKey_invoice(String token, String random,String phone,String invoiceIds,String clientType) throws IOException {
        String url = properties.getProperty("InvoiceUrl") + "/secured/batchShareInvoice/getShareRedisKey?token="+token+"&random="+random+"&phone="+phone+"&invoiceIds="+invoiceIds+"&clientType="+clientType;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * batch-share-controller : 批量分享发票
     * /secured/batchShareInvoice/nextShareInvoice?token=
     * 再次分享_分享交通票
     */
    public static String donextShareInvoice_traffic(String token, String random,String bathShareId,String trafficIds,String clientType) throws IOException {
        String url = properties.getProperty("InvoiceUrl") + "/secured/batchShareInvoice/nextShareInvoice?token="+token+"&random="+random+"&bathShareId="+bathShareId+"&trafficIds="+trafficIds+"&clientType="+clientType;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * batch-share-controller : 批量分享发票
     * /secured/batchShareInvoice/nextShareInvoice?token=
     * 再次分享——分享增值税发票
     */
    public static String donextShareInvoice_invoice(String token, String random,String bathShareId,String invoiceIds,String clientType) throws IOException {
        String url = properties.getProperty("InvoiceUrl") + "/secured/batchShareInvoice/nextShareInvoice?token="+token+"&random="+random+"&bathShareId="+bathShareId+"&invoiceIds="+invoiceIds+"&clientType="+clientType;
        return HttpUtils.executeHttpPost(url,"");
    }
    /**
     * MyInvoice-Invoice Api
     * batch-share-controller : 批量分享发票
     * /secured/batchShareInvoice/saveShareInvoice?token=
     * 分享保存数据
     */
    public static String dosaveShareInvoice(String token, String random,String saveKey,String clientType) throws IOException {
        String url = properties.getProperty("InvoiceUrl") + "/secured/batchShareInvoice/saveShareInvoice?token="+token+"&random="+random+"&saveKey="+saveKey+"&clientType="+clientType;
        return HttpUtils.executeHttpPost(url,"");
    }
}
