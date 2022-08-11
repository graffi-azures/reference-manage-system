package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection()
    {
        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/my-data-manage?useUnicode=true&characterEncoding=utf8";
        String userName = "root";
        String password = "root";
        Connection con  = null;
        try {
            Class.forName(driverName);
            String sql = null;
            con = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}
