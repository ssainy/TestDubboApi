import Model.SqlModel;
import api.GetUserInfo;
import org.testng.annotations.Test;
import utils.JsontoSql;
import utils.RedisUtils;
import utils.JedisUtils_231;

/**
 * Created by thinkpad on 2018-03-23.
 */
public class test {
    @Test
    public void test(){
        String s="{id:\"11111\",uid:\"uuuuid\",uuid:\"\"}";
        s=JsontoSql.JsonToInserSql("sss", SqlModel.traffic_invoice_info());
        System.out.println(s);
    }

}
