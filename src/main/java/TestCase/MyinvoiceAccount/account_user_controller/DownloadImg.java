package TestCase.MyinvoiceAccount.account_user_controller;

import api.DoApi;
import DataProvider.TestDataProvider;
import dependence.Account;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/9/27.
 */
public class DownloadImg {
    @Test(description = "根据uuid获取其对应的文件,返回文件流", dataProvider = "testDataProvider", dataProviderClass = TestDataProvider.class)
    public void downloadImg(String uuid, String Output_code, String Output_message,String description) throws IOException, InterruptedException {
        Account account  = new Account();
        account.bindingWeChat();
        String imgPath = System.getProperty("user.dir") + System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+
                "main"+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+"headimg"+System.getProperty("file.separator");
        String srcImgFullName = "圣诞老人.jpg";
        String destImgFullName = "下载的头像.jpg";

        String uuid1 = account.getHeadImgUuid(imgPath+srcImgFullName);
        byte[] filebyte= DoApi.dodownloadImg(uuid1);
        OutputStream fos = new FileOutputStream(imgPath+destImgFullName);
        fos.write(filebyte);
        fos.close();
        System.out.println("头像下载成功！存放路径为："+imgPath+destImgFullName);

    }
}
