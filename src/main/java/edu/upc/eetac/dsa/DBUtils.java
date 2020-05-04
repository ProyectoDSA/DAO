package edu.upc.eetac.dsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    public static final String DB_NAME = "dbgrupo1";
    public static final String DB_HOST = "localhost";
    public static final String DB_PORT = "3306";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "covid19";

    private static String getDb() {
        return DB_NAME;
    }

    private static String getDbHost() {
        return DB_HOST;
    }

    private static String getDbPort() {
        return DB_PORT;
    }

    private static String getDbUser() {
        return DB_USER;
    }

    private static String getDbPasswd() {
        return DB_PASS;
    }

    public static Connection getConnection() throws SQLException {
        String db = DBUtils.getDb();
        String host = DBUtils.getDbHost();
        String port = DBUtils.getDbPort();
        String user = DBUtils.getDbUser();
        String pass = DBUtils.getDbPasswd();

        Connection connection = DriverManager.getConnection("jdbc:mariadb://"+host+":"+port+"/"
            +db+"?user="+user+"&password="+pass);

        return connection;
    }
}
