package edu.upc.eetac.dsa;

import java.sql.*;

public class DBJDBC2 {

    public static void insert() throws Exception{
        Connection connection = DBUtils.getConnection();

        //SQL INJECTION
        String theQuery = "INSERT INTO User (ID,lastName,firstName,address,city) VALUES (0,?,?,?,?)";

        PreparedStatement statement1 = connection.prepareStatement(theQuery);
        statement1.setString(1, "Vazquez");
        statement1.setString(2, "Carlos");
        statement1.setString(3, "Calle rutlla");
        statement1.setString(4, "BCN");

        statement1.execute();

        connection.close();
    }

    public static void main(String[] args) throws Exception{
        insert();
    }
}
