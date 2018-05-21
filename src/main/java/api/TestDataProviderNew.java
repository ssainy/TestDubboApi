package api;

import org.testng.annotations.DataProvider;
import utils.GetSearchDataNew;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/9/18.
 */
public class TestDataProviderNew {
    @DataProvider()
    public static Object[][] testDataProviderNew(Method method) throws IOException {
        System.out.println(method.getName());
        return GetSearchDataNew.getSearchData(System.getProperty("user.dir") + "/src/main/resources/test.xls", method.getName());//获取Excel文件的测试数据
    }
}
