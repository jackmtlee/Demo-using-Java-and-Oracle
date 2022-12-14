import oracle.jdbc.OracleTypes;

import java.sql.*;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        javaCallOracleStoreProcedure();
    }

    static void javaCallOracleStoreProcedure() throws Exception
    {
        // load Oracle driver
        Class.forName("oracle.jdbc.driver.OracleDriver");
        // get connection
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "XXXX");
        //get statement object
        String sql = "{call get_student_name(?,?)}";
        // call store procedure
        CallableStatement call = connection.prepareCall(sql);
        // set the input parameter
        call.setString(1,"001");
        // register the output parameter
        call.registerOutParameter(2, OracleTypes.VARCHAR);
        // execute the store procedure
        call.execute();
        // get the value for the output parameter
        String studentName = call.getString(2);
        System.out.println("student name is " + studentName);
        call.close();
        connection.close();
    }

    static void javaConnectOracle() throws Exception
    {
        // load Oracle driver
        Class.forName("oracle.jdbc.driver.OracleDriver");
        // get connection
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "XXXX");
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
