import org.testng.annotations.Test;
import utils.JDBC;
import utils.JsonUtils;
import utils.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by cch on 2018/3/20.
 */
public class SubString {
    String invoiceList="";
    String str;
    @Test
    public void STR(){
        String invoice="{\"RZRQ\": \"20180112\",\"RZLX\": \"1\",\"SFDBTS\": \"0\",\"SFYGX\": \"0\",\"FPHM\": \"03201006\",\"SKSSQ\": \"201801\",\"FPDM\": \"110022334455\",\"FPJE\": \"9974.57\",\"XFMC\": \"深圳市立顺祥商贸有限公司\",\"GFSBH\": \"914403001922012959\",\"RZFS\": \"2\",\"KPRQ\": \"20180104\",\"FPLX\": \"01\",\"FPSE\": \"1695.67\",\"FPZT\": \"0\",\"XFSBH\": \"914403000504925330\",\"SFRZ\": \"1\"}";
        invoiceList=invoice;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
       // System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        for (int i=1000;i<1200;i++){
            String invoiceList1= JsonUtils.jsondata(invoice,"FPHM","0320"+i);
            if(!StringUtil.isEmpty(invoiceList)){
                invoiceList=invoiceList+","+invoiceList1;
            }else {
                invoiceList=invoiceList1;
            }
        }
        str  ="{\"CODE\":\"0\",\"MESSAGE\":\"执行成功\",\"DATA\":{\"INVLIST\":["+invoiceList+"],\"DQYF\":\"201803\",\"SKSSQ\":\"201803\",\"GXJZR\":\"20180418\",\"ISNEXT\":false,\"TAXNO\":\"914403006188547107\",\"GXFW\":\"20170701-20180331\",\"TOTAL\":\"200\",\"TASKNO\":\"8a9b71c36212c93b016236adf0b77810\"}}";
        System.out.println(str);
        String sql="insert  into `dx_http_request`(`id`,`type`,`req_json`,`res_text`,`create_date`,`status`,`taxno`) values('"+ UUID.randomUUID().toString().replace("-", "").toLowerCase()+"','0','http://sd.taxservices.cn/fpcj/client/sync.do?AccessKeyID=daxiang&SignatureNonce=da92d73ddd2a47ccb5b184a03ef279de&TaxNo=914403001922012959&TimeStamp=2018-03-18T09:14:01Z&Version=1.0&Signature=5D8697C22B5931426D874A79634F2E7A','"+str+"','"+df.format(new Date())+"','0','914403001922012959');";
        System.out.println(sql);
        InsertReconds.insert(sql);
    }
}
