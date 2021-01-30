package pl;

import java.sql.*;

public class DbUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "coderslab";
    private static final String DB_PARAMS = "?characterEncoding=utf8";

    public static Connection connect(String database) throws SQLException {
        String url = DB_URL + (database != null ? "/" + database : "") + DB_PARAMS;
        Connection connection = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
        return connection;
    }

}
