package utils;

import org.apache.commons.codec.binary.Base64;

import java.io.*;

/**
 * Created by Administrator on 2017/11/14.
 */
public class GetGenImg {
    public static String GetImageStr(String picture){
        String relativelyPath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        String imgPath = relativelyPath + separator +"src"+separator+
                "main"+separator+"resources"+separator+"invoices"+separator+picture+".png";
        return GetBase64Encode(imgPath);
    }

    public static String GetBase64Encode(String imgPath)
    {
        InputStream in = null;
        byte[] data = null;
        try
        {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Base64 encoder = new Base64();
        return encoder.encodeAsString(data);

    }

    //base64字符串转化成图片
    public static boolean GenerateImage(String imgStr, String imgFileName)
    {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        try
        {
            //Base64解码
            byte[] b = Base64.decodeBase64(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String relativelyPath = System.getProperty("user.dir");
            String imgFilePath = relativelyPath + "\\src\\main\\resources\\"+imgFileName+".png"; //新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
