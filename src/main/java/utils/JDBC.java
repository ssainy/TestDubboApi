package utils;

import api.GetJdbcProperties;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.*;


/**
 * Created by cch on 2016/12/26.
 */
public class JDBC {
    public static void delete(String sql, String url, String name, String password) {
        Connection connection = null;
        Statement statement = null;
        try {
            //加载MySQL的驱动类
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + url + "?characterEncoding=utf-8", name, password);
            statement = connection.createStatement();
            try {
                statement.executeUpdate(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static String SqlExe(String sql, String url, String name, String password){
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //加载MySQL的驱动类
            Class.forName("com.mysql.jdbc.Driver");
            connection =  DriverManager.getConnection("jdbc:mysql://" + url + "?characterEncoding=utf-8", name, password);
            statement = connection.createStatement();
          if(sql.toUpperCase().indexOf("SELECT")==0) {
              resultSet = statement.executeQuery(sql);
          }
          else {
              return Boolean.toString(statement.execute(sql));
          }
            return resultSetToJson(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }



    /***************************************************
     * 处理结果集返回JSON格数据
     * @param rs
     * @return
     * @throws SQLException
     * @throws JSONException
     ****************************************************/
    public static String resultSetToJson(ResultSet rs) throws SQLException,JSONException {
        // json数组
        JSONArray array = new JSONArray();

        // 获取列数
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // 遍历ResultSet中的每条数据
         while (rs.next()) {
             JSONObject jsonObj = new JSONObject();

             // 遍历每一列
             for (int i = 1; i <= columnCount; i++) {
                 String columnName =metaData.getColumnLabel(i);
                 String value = rs.getString(columnName);
                 jsonObj.put(columnName, value);
             }
             array.put(jsonObj);
         }

        return array.toString();
     }
}
