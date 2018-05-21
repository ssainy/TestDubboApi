package api;

import utils.JDBC;

import static utils.JDBC.SqlExe;

/**
 * Created by cch on 2018/4/4.
 */
public class ExecuteJDBC {
    /**
     * 在数据库myinvoice_test(10.1.1.231配置文件中可配)里执行SQL脚本
     * @param sql
     * @return
     */
    public static String ExecuteSql(String sql){
        return SqlExe(sql,GetJdbcProperties.GetJdbcUrl(),GetJdbcProperties.GetJdbcName(),GetJdbcProperties.GetJdbcPassWord());
    }
    /**
     * 在账号数据库myinvoice_account(10.1.1.231)里执行SQL脚本
     * @param sql
     * @return
     */
    public static String ExecuteSqlonMyinvoice_account(String sql){
        return SqlExe(sql, GetJdbcProperties.GetJdbcMyinvoice_accountUrl(),GetJdbcProperties.GetJdbcMyinvoice_accountName(),GetJdbcProperties.GetJdbcMyinvoice_accountPassWord());
    }
    /**
     * 在数据库ele-base-test10.1.1.239)里执行SQL脚本
     * @param sql
     * @return
     */
    public static String ExecuteSqlonEle_base_test(String sql){
        return SqlExe(sql,GetJdbcProperties.GetJdbcEleBaseUrl(),GetJdbcProperties.GetJdbcEleBaseName(),GetJdbcProperties.GetJdbcEleBasePassWord());
    }
    /**
     * 在数据库myinvoice(10.1.1.237)里执行SQL脚本
     * @param sql
     * @return
     */
    public static String ExecuteSqlonMyinvoice(String sql){
        System.out.println("sql is :"+sql);
        return SqlExe(sql,GetJdbcProperties.GetJdbcMyinvoicUrl(),GetJdbcProperties.GetJdbcMyinvoiceName(),GetJdbcProperties.GetJdbcMyinvoicePassWord());
    }

    /**
     * 在数据库myinvoice(10.1.1.231)里执行SQL脚本
     * @param sql
     * @return
     */
    public static String ExecuteSqlCredits(String sql){
        return SqlExe(sql,GetJdbcProperties.GetJdbcCreditsUrl(),GetJdbcProperties.GetJdbcCreditsName(),GetJdbcProperties.GetJdbcCreditsPassWord());
    }
    /**
     * 在数据库myinvoice(10.1.1.231)里执行SQL脚本
     * @param sql
     * @return
     */
    public static void DeleteInvoice(String sql) {
        JDBC.delete(sql, GetJdbcProperties.GetJdbcMyinvoicUrl(), GetJdbcProperties.GetJdbcMyinvoiceName(), GetJdbcProperties.GetJdbcMyinvoicePassWord());
    }
}
