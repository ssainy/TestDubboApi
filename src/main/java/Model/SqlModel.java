package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by thinkpad on 2018-03-27.
 */
public class SqlModel {
    static Date now = new Date();
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
    static String time = dateFormat.format( now );

    public static String traffic_invoice_info(){
        return "{\n" +
                "    \"id\": \"\",\n" +
                "    \"uid\": \"\",\n" +
                "    \"invoice_type\": \"\",\n" +
                "    \"train_number\": \"\",\n" +
                "    \"train_time\": \""+time+"\",\n" +
                "    \"starting_station\": \"\",\n" +
                "    \"terminal_station\": \"\",\n" +
                "    \"je\": \"\",\n" +
                "    \"kprq\": \"\",\n" +
                "    \"gjly\": \"\",\n" +
                "    \"name\": \"\",\n" +
                "    \"img_path\": \"\",\n" +
                "    \"status\": \"\",\n" +
                "    \"read_status\": \"\",\n" +
                "    \"create_time\": \""+time+"\",\n" +
                "    \"modify_time\": \""+time+"\"\n" +
                "}";
    }
    public static String invoice_tag(){
        return "{\n" +
                "    \"id\": \"\",\n" +
                "    \"uid\": \"\",\n" +
                "    \"invoiceinfo_id\": \"\",\n" +
                "    \"create_time\": \""+time+"\",\n" +
                "    \"fpbq\": \"\",\n" +
                "    \"gjly\": \"\",\n" +
                "    \"status\": \"1\",\n" +
                "    \"read_status\": \"\",\n" +
                "    \"modify_time\": \""+time+"\"\n" +
                "}";
    }
    public static String invoice_info(){
        return "{\n" +
                "    \"id\": \"\",\n" +
                "    \"fpdm\": \"\",\n" +
                "    \"fphm\": \"\",\n" +
                "    \"kprq\": \""+time+"\",\n" +
                "    \"hjje\": \"\",\n" +
                "    \"invoice_type\": \"\",\n" +
                "    \"gmf_mc\": \"\",\n" +
                "    \"kpxm\": \"\",\n" +
                "    \"xsf_mc\": \"\",\n" +
                "    \"bxzt\": \"\",\n" +
                "    \"cjjg\": \"\",\n" +
                "    \"paper_pic_path\": \"\",\n" +
                "    \"ele_pdf_path\": \"\",\n" +
                "    \"ele_pic_path\": \"\",\n" +
                "    \"create_time\": \""+time+"\",\n" +
                "    \"jym\": \"\",\n" +
                "    \"kplx\": \"\",\n" +
                "    \"ddh\": \"\",\n" +
                "    \"xsf_nsrsbh\": \"\",\n" +
                "    \"xsf_dzdh\": \"\",\n" +
                "    \"xsf_yhzh\": \"\",\n" +
                "    \"gmf_nsrsbh\": \"\",\n" +
                "    \"gmf_dzdh\": \"\",\n" +
                "    \"gmf_yhzh\": \"\",\n" +
                "    \"yfp_dm\": \"\",\n" +
                "    \"yfp_hm\": \"\",\n" +
                "    \"hjse\": \"\",\n" +
                "    \"jshj\": \"\",\n" +
                "    \"bz\": \"\",\n" +
                "    \"jqbh\": \"\",\n" +
                "    \"kpxmsl\": \"\",\n" +
                "    \"ch_bz\": \"\",\n" +
                "    \"dsptbm\": \"\",\n" +
                "    \"bmb_bbh\": \"\",\n" +
                "    \"fpzt\": \"\",\n" +
                "    \"fpzl\": \"\",\n" +
                "    \"gjly\": \"\",\n" +
                "    \"fpbq\": \"\",\n" +
                "    \"check_status\": \"\",\n" +
                "    \"gmf_sf\": \"\",\n" +
                "    \"fjh\": \"\",\n" +
                "    \"version\": \"\",\n" +
                "    \"swjg_dm\": \"\",\n" +
                "    \"dkbz\": \"\",\n" +
                "    \"sgbz\": \"\"\n" +
                "}";
    }
}
