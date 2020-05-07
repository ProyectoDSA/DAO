package edu.upc.eetac.dsa.orm;


import edu.upc.eetac.dsa.DBUtils;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FactorySession {

    public static Session openSession() {

        Connection conn = getConnection();
        Session session = new SessionImpl(conn);
        return session;
    }



    private static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
        } catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
