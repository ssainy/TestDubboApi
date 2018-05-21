package others;

import api.DoRedis;
import api.DoSql;
import api.GetProperties;
import org.testng.annotations.Test;
import utils.JedisUtils_231;
import utils.JedisUtils_232;

/**
 * Created by Administrator on 2017/11/7.
 */
public class ClearUserInfo {
    @Test
    public void clearBindingInfo(){
        DoRedis.doDelUserInfo();
        DoSql.DoDelUserInfoByPhone();
        DoSql.DoDelUSerInfoByUnionId();
        DoSql.DoDelUSerInfoByOpenIdFromUser_base_info();
        DoSql.DoDelUSerInfoByUnionIdFromUser_base_info();
        DoSql.DoDelUSerInfoByPhoneSys_user();
        DoSql.DoDelUSerInfoByOpenId();

    }
    @Test
    public void clearRedis() {
        DoRedis.doDelUserInfo();
    }
    @Test
    public void clearOpenid_Unionid(){
        JedisUtils_231.delMatchedKey(5, GetProperties.GetOpenId());
        JedisUtils_231.delMatchedKey(5, GetProperties.GetUnionId());
        JedisUtils_232.delMatchedKey(5, GetProperties.GetOpenId());
        JedisUtils_232.delMatchedKey(5, GetProperties.GetUnionId());
    }
    @Test
    public void clearUid_token(){
        JedisUtils_231.delMatchedKey(5,"b704118c20b84c358665eeef2c72906e");
        JedisUtils_231.delMatchedKey(5, "1635b5cd-1414-4f25-af4b-e488274aeb59");
        JedisUtils_232.delMatchedKey(5, "b704118c20b84c358665eeef2c72906e");
        JedisUtils_232.delMatchedKey(5, "1635b5cd-1414-4f25-af4b-e488274aeb59");
    }
}
