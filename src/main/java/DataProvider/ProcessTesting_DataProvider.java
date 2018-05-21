package DataProvider;

import org.testng.annotations.DataProvider;
import utils.GetSearchData;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by thinkpad on 2018-03-26.
 */
public class ProcessTesting_DataProvider {
    @DataProvider()
    public static Object[][] processTestingDataProvider(Method method) throws IOException {

        String path =System.getProperty("user.dir") + System.getProperty("file.separator")+"src" +
                System.getProperty("file.separator")+"main"+System.getProperty("file.separator") +
                "resources"+System.getProperty("file.separator")+"ProcessTesting.xls";
        return GetSearchData.getSearchData(path, method.getName());//获取Excel文件的测试数据
    }
}
