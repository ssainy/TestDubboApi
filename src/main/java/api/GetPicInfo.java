package api;

import org.testng.Reporter;
import utils.GetGenImg;
import utils.JsonUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import api.GetStringFromMap;
/**
 * Created by Administrator on 2017/12/11.
 */
public class GetPicInfo {
    /**
     *
     * @param picture: 发票图片文件名（不带扩展名.png）
     * @param type: 请求端类型(0-公众号,1-PC,2-APP)
     * @return
     */
    public static Map<String,String> getPicInfo(String picture,String type,String token){
        Map info = new HashMap<String,String>();//返回信息
        Map map = new HashMap<String,String>();
        String picToBase64Str = GetGenImg.GetImageStr(picture);
        map.put("picture",picToBase64Str);
        String ret = null;
        try {
            ret = DoApi.doinvoiceRecognitionByOcr(token, type, map);
            System.out.println("ocr识别发票返回结果："+ret);
            Reporter.log("ocr识别发票返回结果："+ret);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String infoStr = InfoStrGen.getInfoStr(ret);
        info.put("infoStr",InfoStrGen.getInfoStr(ret));
        info.put("uuid",JsonUtils.getjsondata(ret,"data","uuid"));
        info.put("fpdm",JsonUtils.getjsondata(ret,"data","data","fpdm"));
        info.put("fphm",JsonUtils.getjsondata(ret,"data","data","fphm"));
        return info;
    }
}
