package edu.upc.eetac.dsa;

import java.sql.*; //JDBC

public class DBJDBC {

    //Metodo para insertar un Usuario en la tabla User
    public static void insert() throws SQLException{
        //Nos conectamos a la BBDD y lanzamos una sentencia para insertar un usuario
        Connection connection = DBUtils.getConnection();
        Statement statement1 = connection.createStatement();
        statement1.execute("INSERT INTO User (ID, lastName, firstName, address, city) VALUES (0, 'Requena', 'Ivan', 'Calle falsa 123', 'SantBoi')");
        connection.close();
    }

    //Metodo para ver todos los usuarios de la tabla User
    public static void findAll() throws Exception{
        Connection connection = null;
        try{
            connection = DBUtils.getConnection();
            //Creamos una sentencia que ejecutaremos
            Statement statement2 = connection.createStatement();
            //Recogemos el resultado de ejecutar esa secuencia
            ResultSet rs = statement2.executeQuery("SELECT * FROM User WHERE 1=1");

            //Cogemos el resultado, lo iteramos y lo printamos en pantalla
            int id;
            String lastName, address, city;
            while(rs.next()){
                id = (Integer) rs.getObject(1);
                lastName = (String) rs.getObject(2);
                address = (String) rs.getObject(3);
                city = (String) rs.getObject(4);

                System.out.println(id+" "+lastName+" "+address+" "+city);
            }
        }
        catch (Exception e){
            //log.d("",e)
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception{
        insert();
        findAll();
    }
}
