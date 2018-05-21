package utils;

import net.sf.json.JSONObject;
import org.testng.annotations.Test;

import java.util.Iterator;

/**
 * Created by thinkpad on 2018-03-27.
 */
public class JsontoSql {

    /********************************************
     * 传入表名及json串，将传入内容转换成insert sql语句
     * json只支持一级结构
     * @param TableName
     * @param Json
     * @return
     ********************************************/
    public static String JsonToInserSql(String TableName, String Json){
        String sql;
        StringBuilder SqlColumn = new StringBuilder("INSERT INTO " + TableName + "(");
        StringBuilder SqlValue = new StringBuilder(") values (");
        String key;
        String value;
        JSONObject jsonObject = JSONObject.fromObject(Json);
        Iterator iterator = jsonObject.keys();
        int i = 0;
        while (iterator.hasNext()) {
            if (i==0){
                key = (String) iterator.next();
                SqlColumn.append( key );
                value = jsonObject.getString(key);
                SqlValue.append("'" + value + "'" );
                i++;
            }else {
                key = (String) iterator.next();
                SqlColumn.append(", " +  key );
                value = jsonObject.getString(key);
                SqlValue.append(", '" + value + "'" );
            }
        }
        sql = SqlColumn.toString()+SqlValue.toString() + ");";
        return sql;
    }
}
