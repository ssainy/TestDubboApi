package api;

import utils.ReadProper;

import java.util.Properties;

/**
 * Created by thinkpad on 2018-03-23.
 */
public class GetRedisProperties {
    public static Properties properties= ReadProper.readproper("/redis.properties");

    /*****************************
     * 获取redis主机地址
     *
     * @return
     ******************************/
    public static String GetReidsHost(){
        return(properties.getProperty("redis.host.231"));
    }

    /*****************************
     * 获取redis端口
     *
     * @return
     ******************************/
    public static String GetReidsPort(){
        return(properties.getProperty("redis.port.231"));
    }

    /*****************************
     * 获取redis密码
     *
     * @return
     ******************************/
    public static String GetReidsPassWord(){
        return(properties.getProperty("redis.password.231"));
    }

    /*****************************
     * 获取用户redis存储db
     *
     * @return
     ******************************/
    public static String GetUserDB(){
        return(properties.getProperty("redis.user.db"));
    }

}
