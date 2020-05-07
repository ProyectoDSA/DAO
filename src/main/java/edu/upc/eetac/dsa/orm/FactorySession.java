package edu.upc.eetac.dsa.orm;


import edu.upc.eetac.dsa.orm.util.DBUtils;

import java.sql.Connection;

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
