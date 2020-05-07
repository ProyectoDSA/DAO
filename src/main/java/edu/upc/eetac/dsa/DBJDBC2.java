package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.orm.dao.UserDAO;
import edu.upc.eetac.dsa.orm.dao.UserDAOImpl;
import edu.upc.eetac.dsa.orm.util.DBUtils;
import edu.upc.eetac.dsa.orm.util.RandomUtils;

import java.sql.*;

//CLASE PARA TEST
public class DBJDBC2 {

    //Inserta un usuario en la tabla User
    public static void insert() throws Exception{
        //Obtenemos conexion con la BBDD
        Connection connection = DBUtils.getConnection();

        //SQL INJECTION
        //Preparamos consulta INSERT para usuario con parametros
        String theQuery = "INSERT INTO User (ID,nombre,mail) VALUES (?,?,?)";

        //Asignamos el valor a los parametros de la Query
        PreparedStatement statement1 = connection.prepareStatement(theQuery);
        statement1.setString(1, RandomUtils.generateID(6));
        statement1.setString(2, "Carlos");
        statement1.setString(3, "ivdjjj@yahoo.es");

        //Ejecutamos la consulta
        statement1.execute();
        System.out.println(statement1);

        //Cerramos conexion SIEMPRE!!
        connection.close();
    }

    public static void main(String[] args) throws Exception{
        //insert();
        UserDAO userDao = new UserDAOImpl();
        //userDao.addUser("Manu", "manu@outlook.com");
        userDao.getAllUsers();
        userDao.updateUser("RAGVAL", "micky", "jj");
        userDao.getUser("RAGVAL");
        userDao.deleteUser("RAGVAL");
    }
}
