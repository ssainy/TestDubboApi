package DataProvider;

import org.testng.annotations.DataProvider;
import utils.GetSearchData;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/9/6.
 */
public class TestDataProvider {

    @DataProvider()
    public static Object[][] testDataProvider(Method method) throws IOException {
        System.out.println(method.getName());
        System.out.println(System.getProperty("user.dir") + System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+
                "main"+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+"test.xls");
        return GetSearchData.getSearchData(System.getProperty("user.dir") + System.getProperty("file.separator")+"src"+
                System.getProperty("file.separator")+"main"+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+"test.xls", method.getName());//获取Excel文件的测试数据
    }
}