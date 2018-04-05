/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;



import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 *
 * @author kasper
// */
public class ConnectionManagerAttendance 
{
    private final SQLServerDataSource ds = new SQLServerDataSource();
    public ConnectionManagerAttendance() 
    {
        ds.setDatabaseName("CS2017B_13_Attendance");
        ds.setUser("CS2017B_13_java");
        ds.setPassword("javajava");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");
    }
    
    public Connection getConnection() throws SQLServerException
    {
        return ds.getConnection();
    }
}

