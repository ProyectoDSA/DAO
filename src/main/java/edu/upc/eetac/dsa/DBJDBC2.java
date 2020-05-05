package edu.upc.eetac.dsa;

import java.sql.*;

public class DBJDBC2 {


    public static void insert() throws Exception{
        Connection connection = DBUtils.getConnection();

        //SQL INJECTION
        String theQuery = "INSERT INTO User (ID,nombre,mail) VALUES (0,?,?)";

        PreparedStatement statement1 = connection.prepareStatement(theQuery);
        statement1.setString(1, "Ivan");
        statement1.setString(2, "ivan@yahoo.es");

        statement1.execute();

        connection.close();
    }

    public static void main(String[] args) throws Exception{

        insert();
    }
}
