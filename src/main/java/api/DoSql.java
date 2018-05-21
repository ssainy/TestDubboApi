/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;


import static api.ExecuteJDBC.*;

/**
 *
 * @author thinkpad
 */
public class DoSql {
    /*****************************************************
     * 查询数据库的UID
     * @return
     *******************************************************/
    public static String DoGetUidByUnionId(){
        String sql = "SELECT * FROM user_base_info a WHERE a.union_id='"+GetProperties.GetUnionId()+"' LIMIT 1;";
       // System.out.println(JDBC.ExecuteSqlonMyinvoice_account(sql));
        return ExecuteSqlonMyinvoice_account(sql);
    }
    /*****************************************************
     * 查询数据库的UID
     * @return
     *******************************************************/
    public static String DoGetUidByPhone(){
        String sql = "SELECT * FROM user_account WHERE account='"+GetProperties.GetPhone()+"' AND STATUS='0';";
        System.out.println(ExecuteSqlonMyinvoice_account(sql));
        return ExecuteSqlonMyinvoice_account(sql);
    }


    /************************************************************
     * 查询数据库title_info表，按id查名片状态(0:无效,1:有效)
     * @param
     * @return
     *************************************************************/
    public static String DoQuery_Titel_info_status(String id){
        String sql = "select status from title_info where id = \"" + id + "\"";
        return ExecuteSql(sql);
    }
    
    public static String DoQuery_Titel_info(String id){
        String sql = "select * from title_info where id = \"" + id + "\"";
        return ExecuteSql(sql);
    }
    public static String DoGETCardList(){
        String sql = "SELECT * FROM title_info a WHERE a.uid='"+GetRedis.GetWeChatuid()+"' AND a.status='1';";
        return ExecuteSql(sql);
    }

    /**
     *  在库10.1.1.237上的invoice库里查询
     *  查询数据库title_info表，按id查名片状态(0:无效,1:有效)
     */
    public static String DoQuery_Update_Invoice_Read_Status(String invoiceId){
        String sql = "SELECT * FROM invoice_tag a WHERE a.uid='"+GetRedis.GetWeChatuid()+"' AND a.invoiceinfo_id='"+invoiceId+"';";
        return ExecuteSqlonMyinvoice(sql);
    }
    /**
     *  在库10.1.1.237上的invoice库里查询
     *  invoice_tag_logs，根据UID查询invoice_id
     *  SELECT * FROM invoice_tag_logs WHERE uid='';
     */
    public static String DoQueryInvoiceId(){
        String sql = "SELECT * FROM invoice_info;";
        return ExecuteSqlonMyinvoice(sql);
    }

    /**
     *  在库10.1.1.237上的invoice库里查询
     *  查询数据库title_info表，按id查名片状态(0:无效,1:有效)
     */
    public static String DoQueryPaperInvoice(){
        String sql = "SELECT * FROM paper_img a WHERE a.uid='"+GetRedis.GetWeChatuid()+"' AND a.status='1';";
        return   ExecuteSqlonMyinvoice(sql);
    }
    /**
     *  在库10.1.1.237上的invoice库里查询
     *  paper_img，按id查图片(0:无效,1:有效)
     */
    public static String DoQueryPaperImgStatus_byInvoiceId(String invoiceInfoId){
        String sql = "SELECT * FROM paper_img a WHERE a.invoice_info_id='"+invoiceInfoId+"' and a.uid='"+GetRedis.GetWeChatuid()+"';";
        return   ExecuteSqlonMyinvoice(sql);
    }

    /************************************************************
     * 数据库 myinvoice_account(on 10.1.1.231) 执行脚本 表 user_account p,user_base_info
     * DELETE p.*,pp.* FROM user_account p,user_base_info pp WHERE p.uid = pp.uid AND p.account = '<Phone_Number>';
     *
     *************************************************************/
    public static String DoDelUserInfoByPhone(){
        String sql = "DELETE p.*,pp.* FROM user_account p,user_base_info pp WHERE p.uid = pp.uid AND p.account = \""
                +GetProperties.GetPhone()
                +"\"";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice_account(sql);
    }
    /************************************************************
     * 数据库 myinvoice_account(on 10.1.1.231) 执行脚本 表 user_account
     * DELETE FROM user_account WHERE account ='<union_id>';
     *
     *************************************************************/
    public static String  DoDelUSerInfoByUnionId(){
       String sql = "DELETE FROM user_account WHERE account = \""
               +GetProperties.GetUnionId()
               +"\"";
        System.out.println("sql是:"+sql);
       return   ExecuteSqlonMyinvoice_account(sql);
    }

    /************************************************************
     * 数据库 myinvoice_account(on 10.1.1.231) 执行脚本 表 user_base_info
     * DELETE FROM user_base_info WHERE open_id ='';
     *
     *************************************************************/
    public static String  DoDelUSerInfoByOpenIdFromUser_base_info(){
        String sql = "DELETE FROM user_base_info WHERE open_id =\""
                +GetProperties.GetOpenId()
                +"\"";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice_account(sql);
    }

    /************************************************************
     * 数据库 myinvoice_account(on 10.1.1.231) 执行脚本 表 user_base_info
     * DELETE FROM user_base_info WHERE union_id ='';
     *
     *************************************************************/
    public static String  DoDelUSerInfoByUnionIdFromUser_base_info(){
        String sql = "DELETE FROM user_base_info WHERE union_id =\""
                +GetProperties.GetUnionId()
                +"\"";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice_account(sql);
    }

    /************************************************************
     * 数据库 ele-base-test(on 10.1.1.239) 执行脚本 表 sys_user
     * DELETE FROM sys_user WHERE mobile='<phone_number>'
     *
     *************************************************************/
    public static String  DoDelUSerInfoByPhoneSys_user(){
        String sql = "DELETE FROM sys_user WHERE mobile=\""
                +GetProperties.GetPhone()
                +"\"";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonEle_base_test(sql);
    }
    /************************************************************
     * 数据库 ele-base-test(on 10.1.1.239) 执行脚本 表 user_info
     * DELETE FROM sys_user WHERE mobile='<phone_number>'
     *
     *************************************************************/
    public static String  DoDelUSerInfoByOpenId(){
        String sql = "DELETE FROM user_info WHERE open_id=\""
                +GetProperties.GetOpenId()
                +"\"";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice_account(sql);
    }
    /************************************************************
     * 数据库 ele-base-test(on 10.1.1.239) 执行脚本 表 invoice_tag
     * SELECT * FROM invoice_tag WHERE uid='930629486486093824';
     *
     *************************************************************/
    public static String DoShowInvoiceIdByUid(){
        String sql = "SELECT invoiceinfo_id FROM invoice_tag WHERE uid='"+GetRedis.GetWeChatuid()+"' and status='1'";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice(sql);
    }
    /************************************************************
     * 数据库 ele-base-test(on 10.1.1.239) 执行脚本 表 invoice_tag
     * SELECT * FROM invoice_tag WHERE uid='930629486486093824';
     *
     *************************************************************/
    public static String DoShowInvoiceByUid(String uid){
        String sql = "SELECT * FROM invoice_tag tag,invoice_info info WHERE tag.`invoiceinfo_id`=info.`id` AND tag.`uid`='"+uid+"';";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice(sql);
    }
    /************************************************************
     * 数据库 credits-test(on 10.1.1.231) 执行脚本 表 credits_issue_config
     * SELECT CONCAT(t.issue_name, t.credits,'积分', IFNULL(t.credits_desc,'')) AS credits FROM credits_issue_config t;
     *
     *************************************************************/
    public static String  DogetIntegretionRule(){
        String sql = "SELECT CONCAT(t.issue_name, t.credits,'积分', IFNULL(t.credits_desc,'')) AS credits FROM credits_issue_config t;";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlCredits(sql);
    }
    /************************************************************
     * 数据库 credits-test(on 10.1.1.231) 执行脚本 表 credits_log
     * SELECT a.`id`,a.`uid`,a.`credits`,a.`credits_type`,a.`credits_config_name`,a.`credits_config_id`,a.`source_type`,a.`create_time`,a.`order_no` FROM credits_log a WHERE uid='935042629018714112';
     *
     *************************************************************/
    public static String  DogetIntegretionList(){
        String sql = "SELECT a.`id`,a.`uid`,a.`credits`,a.`credits_type`,a.`credits_config_name`,a.`credits_config_id`,a.`source_type`,a.`create_time`,a.`order_no` FROM credits_log a WHERE uid='"+GetRedis.GetWeChatuid()+"';";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlCredits(sql);
    }

    /************************************************************
     * 数据库 credits-test(on 10.1.1.231) 执行脚本 表 credits
     * SELECT * FROM credits WHERE uid='935042629018714112';
     *
     *************************************************************/
    public static String  DogetIntegretionInfo(){
        String sql = "SELECT * FROM credits WHERE uid='"+GetRedis.GetWeChatuid()+"';";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlCredits(sql);
    }
    /************************************************************
     * 数据库 credits-traffic_invoice_info(on 10.1.1.237) 执行脚本 表 credits
     * SELECT * FROM traffic_invoice_info WHERE uid='938255896717426688';
     *
     *************************************************************/
    public static String  DogetInvoiceData_traffic(){
        String sql = "SELECT * FROM traffic_invoice_info WHERE uid='"+GetRedis.GetWeChatuid()+"' and status='1';";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice(sql);
    }
    /************************************************************
     * 数据库 credits-traffic_invoice_info(on 10.1.1.237) 执行脚本 表 credits
     * SELECT * FROM traffic_invoice_info WHERE uid='938255896717426688';
     *
     *************************************************************/
    public static String  DogetInvoiceData_traffic(String uid,String invoice_type ){
        String sql = "SELECT * FROM traffic_invoice_info WHERE uid='"+uid+"' and invoice_type='"+invoice_type+"' and status='1';";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice(sql);
    }
    /************************************************************
     * 数据库 getUserSetting(on 10.1.1.237) 执行脚本 表 getUserSetting
     * SELECT * FROM user_setting WHERE uid='937593150963318784';
     *
     *************************************************************/
    public static String  DogetUserSetting(){
        String sql = "SELECT * FROM user_setting WHERE uid='"+GetRedis.GetWeChatuid()+"';";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice(sql);
    }
    /************************************************************
     * 数据库 credits-traffic_invoice_info(on 10.1.1.237) 执行脚本 表 credits
     * select * from invoice_tag where uid='938255896717426688';
     *
     *************************************************************/
    public static String  DogetInvoiceData(){
        String sql = "SELECT * FROM invoice_tag WHERE uid='"+GetRedis.GetWeChatuid()+"' and status='1';";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice(sql);
    }
    /************************************************************
     * 两表联查，获取当前用户下的发票信息
     * select * from invoice_tag where uid='938255896717426688';
     *
     *************************************************************/
    public static String  DogetInvoiceData(String uid,String fplb,String check_status){
        String sql = "select * from invoice_tag tag,invoice_info info where tag.`invoiceinfo_id`=info.`id` and tag.uid='"+uid+"' and info.`invoice_type`='"+fplb+"' and info.`check_status`='"+check_status+"' and status='1' ;";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice(sql);
    }
    /************************************************************
     * 两表联查，获取当前用户下的发票信息
     * select * from invoice_tag where uid='938255896717426688';
     *
     *************************************************************/
    public static String  DogetInvoiceData(String uid){
        String sql = "select * from invoice_tag tag,invoice_info info where tag.`invoiceinfo_id`=info.`id` and tag.uid='"+uid+"' and status='1' ;";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice(sql);
    }
    /************************************************************
     * 数据库 credits-traffic_invoice_info(on 10.1.1.237) 执行脚本 表 credits
     * select * from invoice_tag where uid='938255896717426688';
     *
     *************************************************************/
    public static String  DogetInvoiceData(String uid,String fplb){
        String sql = "select * from invoice_tag tag,invoice_info info where tag.`invoiceinfo_id`=info.`id` and tag.uid='"+uid+"' and info.`invoice_type`='"+fplb+"'  and status='1' ;";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice(sql);
    }
    /************************************************************
     * 数据库 credits-traffic_invoice_info(on 10.1.1.237) 执行脚本 表 credits
     * select * from invoice_tag where uid='938255896717426688';
     *
     *************************************************************/
    public static String  DogetInvoiceInfoById(String invoiceinfo_id){
        String sql = "SELECT * FROM invoice_info WHERE id='"+invoiceinfo_id+"' limit 1;";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice(sql);
    }
    /************************************************************
     * 数据库 credits-traffic_invoice_info(on 10.1.1.237) 执行脚本 表 credits
     * select * from qr_invoice_info where uid='938255896717426688';
     *
     *************************************************************/
    public static String  DogetInvoiceData_QrInvoice(){
        String sql = "SELECT * FROM qr_invoice_info WHERE uid='"+GetRedis.GetWeChatuid()+"';";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice(sql);
    }

    /************************************************************
     * 数据库 invoice(on 10.1.1.237) 执行脚本 表 traffic_invoice_info
     * select * from traffic_invoice_info where id='eb2f500a-335f-4042-a7c7-4496c62ac817';
     *
     *************************************************************/
    public static String  DoTrafficInvoiceCollection(String invoiceId){
        String sql = "SELECT * FROM traffic_invoice_info WHERE id='"+invoiceId+"';";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice(sql);
    }
    /************************************************************
     * 数据库 invoice(on 10.1.1.237) 执行脚本 表 invoice_tag
     * select * from traffic_invoice_info where id='eb2f500a-335f-4042-a7c7-4496c62ac817';
     *
     *************************************************************/
    public static String  DoGetInvoiceTagById(String invoiceId){
        String sql = "SELECT * FROM invoice_tag WHERE id='"+invoiceId+"';";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice(sql);
    }
    /************************************************************
     * 数据库 invoice(on 10.1.1.237) 执行脚本 表 traffic_invoice_info
     * 添加表数据
     *
     *************************************************************/
    public static String  DoTrafficInvoiceInsert(String invoiceId){
        String sql = "insert into traffic_invoice_info (" +
                "`id`, `uid`, `invoice_type`, `train_number`, `train_time`, `starting_station`, `terminal_station`, " +
                "`je`, `kprq`, `gjly`, `name`, `img_path`, `status`, `read_status`, `create_time`, `modify_time`) " +
                "values('"+ invoiceId +"','9ffeb23eac2b4f9988183bba7f887d6e','3','','2017-09-15 08:20:30',NULL,NULL,'999.00','2017-09-15 08:20:30','1','打车票','','1','0','2018-03-13 11:00:51','2018-03-13 11:00:51')";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice(sql);
    }

    /************************************************************
     * 数据库 invoice(on 10.1.1.237) 执行脚本 表 traffic_invoice_info
     * 按id删除表中数据
     *
     *************************************************************/
    public static String  DoTrafficInvoiceDelete(String invoiceId){
        String sql = "Delete FROM traffic_invoice_info WHERE id='"+invoiceId+"';";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice(sql);
    }
    /************************************************************
     * 数据库 invoice(on 10.1.1.237) 执行脚本 表 invoice_tag
     * 按id删除表中数据
     *
     *************************************************************/
    public static String  DoInvoiceTagDelete(String invoiceId){
        String sql = "Delete FROM invoice_tag WHERE id='"+invoiceId+"';";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice(sql);
    }

    /************************************************************
     * 数据库 myinvoice_account(on 10.1.1.231) 执行脚本 表 account_info
     * SELECT * FROM account_info WHERE uid = "409b23f1e82a4f889e6979c804fccad1"
     *
     *************************************************************/
    public static String  DoQueryBindUserInfo(String uid){
        String sql = "SELECT * FROM account_info WHERE uid =\""
                +uid
                +"\"";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice_account(sql);
    }

    /************************************************************
     * 数据库 myinvoice_account(on 10.1.1.231) 执行脚本 表 user_base_info
     * SELECT * FROM user_base_info WHERE uid = "409b23f1e82a4f889e6979c804fccad1" AND user_type = "1"
     *
     *************************************************************/
    public static String  DoDoQueryUserInfo(String uid,String userType){
        String sql = "SELECT * FROM user_base_info WHERE uid =\""
                +uid
                +"\""+" AND user_type =\""+userType+"\"";
        System.out.println("sql是:"+sql);
        return   ExecuteSqlonMyinvoice_account(sql);
    }
    /**
     *  在库10.1.1.237上的invoice库里查询
     *  查询数据库invoice_batch_share表，查询分享id
     */
    public static String DoGetBathShareId(){
        String sql = "SELECT * FROM invoice_batch_share a WHERE a.uid='"+GetRedis.GetWeChatuid()+"';";
        return   ExecuteSqlonMyinvoice(sql);
    }
    /**
     *  在库10.1.1.237上的invoice库里查询
     *  查询数据库invoice_batch_share表，查询分享id
     */
    public static String DoDeleteInvoice(){
        String sql = "SELECT * FROM invoice_batch_share a WHERE a.uid='"+GetRedis.GetWeChatuid()+"';";
        return   ExecuteSqlonMyinvoice(sql);
    }
}
