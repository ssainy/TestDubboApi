package TestCase.Invoice.traffic_invoice_controller;

import DataProvider.MyInvoice_InvoiceDataProvider;
import api.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import utils.JDBC;
import utils.JsonUtils;
import utils.JsontoSql;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static api.ExecuteJDBC.ExecuteSqlonMyinvoice;

/**
 * Created by thinkpad on 2018-03-27.
 */
public class DeleteTrafficImg {

    List<String> id_list=new ArrayList<>();

    public void init(String type,String id){
        String ret_sql = DoSql.DoTrafficInvoiceCollection(id);
        if (type.equals("0")&&!ret_sql.equals("[]")){
            DoSql.DoTrafficInvoiceDelete(id);
        }else if (type.equals("1")&&ret_sql.equals("[]")){
            //DoSql.DoTrafficInvoiceInsert(id);
            id_list.add(id);
            String json_traffic;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String date = df.format(new Date());
            json_traffic = JsonUtils.SetJsonValue(Model.SqlModel.traffic_invoice_info(),"id",id);
            json_traffic = JsonUtils.SetJsonValue(json_traffic,"uid", GetUserProperties.GetUserUid());
            //json_traffic = JsonUtils.SetJsonValue(json_traffic,"invoice_type","3");
            json_traffic = JsonUtils.SetJsonValue(json_traffic,"train_time",date);
            //json_traffic = JsonUtils.SetJsonValue(json_traffic,"je","123");
            json_traffic = JsonUtils.SetJsonValue(json_traffic,"kprq",date);
            //json_traffic = JsonUtils.SetJsonValue(json_traffic,"gjly","1");
            json_traffic = JsonUtils.SetJsonValue(json_traffic,"status","1");
            //json_traffic = JsonUtils.SetJsonValue(json_traffic,"read_status","0");
            json_traffic = JsonUtils.SetJsonValue(json_traffic,"create_time",date);
            json_traffic = JsonUtils.SetJsonValue(json_traffic,"modify_time",date);
            json_traffic = JsonUtils.SetJsonValue(json_traffic,"img_path","testimg_path");
            ExecuteSqlonMyinvoice(JsontoSql.JsonToInserSql("traffic_invoice_info",json_traffic));
        }else{
            Reporter.log("无需前置处理，或数据库类型错误！");
        }
    }

    @Test(description = "根据id删除对应交通票,根据DBTpye判断库中否存在：0无、1有",dataProvider ="InvoiceDataProvider",dataProviderClass= MyInvoice_InvoiceDataProvider.class)
    public void deleteTrafficImg(String DBType, String Traffic_id,String Output_code, String Output_message,String description){

        String token;
        String ret="";
        token = GetRedis.GetWeChatToken();
        init(DBType,Traffic_id);

        ret = DoApi.dodeleteTrafficImg(token,Traffic_id);

        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
    }

    @AfterClass(description = "清理测试生成的必要数据")
    public void cleardata(){
        if (!id_list.isEmpty()){
            for (int i = 0;i<id_list.size();i++){
                DoSql.DoTrafficInvoiceDelete(id_list.get(i));
            }
        }else {
            System.out.println("无需要清理的数据");
        }
    }
}
