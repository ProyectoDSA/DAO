package edu.upc.eetac.dsa.orm;


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
            Properties p = new Properties(); //Crea fichero de propiedades
            //Seleccionamos nuestro fichero donde guardamos las propiedades
            File dbPathPropertiesFile = new File("src/main/resources/db.properties");
            FileReader r = new FileReader(dbPathPropertiesFile);
            //Insertamos nuestro fichero en las propiedades
            p.load(r);
            //Asignamos los distintos parametros
            String host = p.getProperty("db.host");
            String port = p.getProperty("db.port");
            String user = p.getProperty("db.username");
            String db = p.getProperty("db.database");
            String pswd = p.getProperty("db.password");

            //Obtenemos la conexion
            connection = DriverManager.getConnection("jdbc:mariadb://"+host+":"+port+"/"
                    +db+"?user="+user+"&password="+pswd);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
