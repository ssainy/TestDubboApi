package api;

import utils.ReadProper;

import java.util.Properties;

/**
 * Created by thinkpad on 2018-03-27.
 */
public class GetMongodbProperties {
    public static Properties properties= ReadProper.readproper("/mongodb.properties");

    /*****************************
     * 获取mongodb主机地址
     *
     * @return
     ******************************/
    public static String GetReidsHost(){
        return(properties.getProperty("mongodb.host"));
    }

    /*****************************
     * 获取mongodb端口
     *
     * @return
     ******************************/
    public static String GetReidsPort(){
        return(properties.getProperty("mongodb.port"));
    }
}
