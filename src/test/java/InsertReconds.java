import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by cch on 2018/3/20.
 */
public class InsertReconds {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://103.237.1.71/dxtax_jkpt?characterEncoding=utf-8";

    //  Database credentials
    static final String USER = "dxhy";
    static final String PASS = "dxhy";

    public static void insert(String sql) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end JDBCExample
