package api;
import java.util.Map;
/**
 * Created by Administrator on 2017/12/11.
 * Map取值方法,其中取得多种值,避免null值转换
 */
public class GetStringFromMap {

    /**
     * <p>
     * 根据Key返回一个String
     * </p>
     * @param key
     * @return String
     */
    public static String getString(Map map, String key){
        if(map.get(key)!=null){
            if(map.get(key) instanceof String){
                return (String)map.get(key);
            }else{
                return null;
            }
        }else{
            return "";
        }
    }
}