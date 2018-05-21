package TestCase.Invoice.traffic_invoice_controller;

import api.*;
import DataProvider.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JDBC;
import utils.JsonUtils;
import utils.JsontoSql;

import static api.ExecuteJDBC.ExecuteSqlonMyinvoice;


/**
 * Created by thinkpad on 2018-03-26.
 */
public class DeleteTrafficInvoice {
    public void init(String type,String id){
        String ret_sql = DoSql.DoTrafficInvoiceCollection(id);
        if (type.equals("0")&&!ret_sql.equals("[]")){
            DoSql.DoTrafficInvoiceDelete(id);
        }else if (type.equals("1")&&ret_sql.equals("[]")){
            //DoSql.DoTrafficInvoiceInsert(id);
            String json_traffic;
            json_traffic = JsonUtils.SetJsonValue(Model.SqlModel.traffic_invoice_info(),"id",id);
            json_traffic = JsonUtils.SetJsonValue(json_traffic,"uid",GetUserProperties.GetUserUid());
            ExecuteSqlonMyinvoice(JsontoSql.JsonToInserSql("traffic_invoice_info",json_traffic));
        }else{
            Reporter.log("无需前置处理，或数据库类型错误！");
        }
    }
    @Test(description = "根据id删除对应交通票,根据DBTpye判断库中否存在：0无、1有",dataProvider ="InvoiceDataProvider",dataProviderClass= MyInvoice_InvoiceDataProvider.class)
    public void deleteTrafficInvoice(String DBType, String Traffic_id,String Output_code, String Output_message,String description){

        String token;
        String ret="";
        token = GetRedis.GetWeChatToken();
        init(DBType,Traffic_id);

        ret = DoApi.dodeleteTrafficInvoice(token,Traffic_id);

        Assert.assertEquals(DealResult.getResult_Message(ret), Output_message);
        Assert.assertEquals(DealResult.getResult_Code(ret), Output_code);
    }
}
