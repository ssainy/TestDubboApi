package api;

import utils.JsonUtils;

/**
 * Created by Administrator on 2017/12/6.
 */
public class InfoStrGen {
    public static String getInfoStr(String OcrRecognitionRs){
        String infoStr="";
        String version="";
        String fplx="";
        String fpdm="";
        String fphm="";
        String hjje="";
        String kprq="";
        String jym="";
        String crc="";

        version = "01";
        System.out.println(OcrRecognitionRs);
        fplx = JsonUtils.getjsondata(OcrRecognitionRs,"data","data","fpzl");
        fpdm = JsonUtils.getjsondata(OcrRecognitionRs,"data","data","fpdm");
        fphm = JsonUtils.getjsondata(OcrRecognitionRs,"data","data","fphm");
        hjje = JsonUtils.getjsondata(OcrRecognitionRs,"data","data","hjje");
        kprq = JsonUtils.getjsondata(OcrRecognitionRs,"data","data","kprq");
        jym =  JsonUtils.getjsondata(OcrRecognitionRs,"data","data","jym");
        crc = "0116";
        return "("+version+","+fplx+","+fpdm+","+fphm+","+hjje+","+kprq+","+jym+","+crc+")";
    }
}
