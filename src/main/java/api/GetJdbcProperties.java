/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.util.Properties;
import utils.ReadProper;

/**
 *
 * @author thinkpad
 */
public class GetJdbcProperties {
    public static Properties properties= ReadProper.readproper("/jdbc.properties");

    /*****************************
     * 获取ele-base数据库配置
     * 获取Url连接地址
     * @return
     ******************************/
    public static String GetJdbcEleBaseUrl(){
        return(properties.getProperty("JdbcEle_base_test"));
    }
    /***********************************
     * 获取ele-base数据库用户名
     * @return
     ***********************************/
    public static String GetJdbcEleBaseName(){
        return(properties.getProperty("NameEle_base_test"));
    }
    /*************************************
     * 获取ele-base数据库密码
     * @return
     ************************************/
    public static String GetJdbcEleBasePassWord(){
        return(properties.getProperty("PasswordEle_base_test"));
    }

    /*****************************
     * 获取默认（旧数据库）配置
     * 获取Url连接地址
     * @return 
     ******************************/
    public static String GetJdbcUrl(){
        return(properties.getProperty("Jdbc"));        
    }
    /***********************************
     * 获取数据库用户名
     * @return 
     ***********************************/
    public static String GetJdbcName(){
        return(properties.getProperty("Name"));
    }
    /*************************************
     * 获取数据库密码
     * @return 
     ************************************/
    public static String GetJdbcPassWord(){
        return(properties.getProperty("Password"));
    }


    /*****************************
     * 获取账号数据库配置
     * 获取Url连接地址
     * @return
     ******************************/
    public static String GetJdbcMyinvoice_accountUrl(){
        return(properties.getProperty("JdbcMyinvoice_account"));
    }
    /***********************************
     * 获取账号数据库用户名
     * @return
     ***********************************/
    public static String GetJdbcMyinvoice_accountName(){
        return(properties.getProperty("NameMyinvoice_account"));
    }
    /*************************************
     * 获取账号数据库密码
     * @return
     ************************************/
    public static String GetJdbcMyinvoice_accountPassWord(){
        return(properties.getProperty("PasswordMyinvoice_account"));
    }

    /*****************************
     * 获取发票数据库配置
     * 获取Url连接地址
     * @return
     ******************************/
    public static String GetJdbcMyinvoicUrl(){
        return(properties.getProperty("JdbcMyinvoice"));
    }
    /***********************************
     * 获取发票数据库用户名
     * @return
     ***********************************/
    public static String GetJdbcMyinvoiceName(){
        return(properties.getProperty("NameMyinvoice"));
    }
    /*************************************
     * 获取发票数据库密码
     * @return
     ************************************/
    public static String GetJdbcMyinvoicePassWord(){
        return(properties.getProperty("PasswordMyinvoice"));
    }

    /*****************************
     * 获取积分数据库配置
     * 获取Url连接地址
     * @return
     ******************************/
    public static String GetJdbcCreditsUrl(){
        return(properties.getProperty("JdbcCredits"));
    }
    /***********************************
     * 获取积分数据库用户名
     * @return
     ***********************************/
    public static String GetJdbcCreditsName(){
        return(properties.getProperty("NameCredits"));
    }
    /*************************************
     * 获取积分数据库密码
     * @return
     ************************************/
    public static String GetJdbcCreditsPassWord(){
        return(properties.getProperty("PasswordCredits"));
    }

}
