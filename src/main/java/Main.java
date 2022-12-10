import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.DriverManager;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        // load Oracle driver
        Class.forName("oracle.jdbc.driver.OracleDriver");
        // get connection
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "XXX");
        //get statement object
        String sql_statement = "select * from TSTUDENT";
        PreparedStatement pstm = connection.prepareStatement(sql_statement);
        // assign the values to the parameters
//        pstm.setObject(1, 1);
        // execute the query
        ResultSet rs = pstm.executeQuery();
        // result output
        while(rs.next()){
            System.out.println("student id : " + rs.getString("student_id") + ", student name is : " + rs.getString("student_name"));
        }
        // release the resource
        rs.close();
        pstm.close();
        connection.close();
    }
}
