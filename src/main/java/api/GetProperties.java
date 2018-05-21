package api;
import utils.ReadProper;
import java.util.Properties;

/**
 * Created by thinkpad on 2017/6/26.
 */
public class GetProperties {
    public static Properties properties= ReadProper.readproper("/user.properties");

    public static String GetPwd(){
        return properties.getProperty("user.pwd");
    }

    public static String GetUnionId(){
        return properties.getProperty("user.UnionId");
    }
    public static String GetOpenId(){
        return properties.getProperty("user.OpenId");
    }
    public static String GetAPPOpenId(){
        return properties.getProperty("user.appOpenId");
    }

    public static String GetPhone(){
        return properties.getProperty("user.phone");
    }


}
