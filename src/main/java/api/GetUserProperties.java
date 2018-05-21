package api;

import utils.ReadProper;

import java.util.Properties;

/**
 * Created by thinkpad on 2018-03-23.
 */
public class GetUserProperties {
    public static Properties properties= ReadProper.readproper("/user.properties");

    /*****************************
     * 获取用户UID配置
     *
     * @return
     ******************************/
    public static String GetUserUid(){
        return(properties.getProperty("user.uid"));
    }
}
