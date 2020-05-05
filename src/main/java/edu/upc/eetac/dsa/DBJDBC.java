package edu.upc.eetac.dsa;

import java.sql.*; //JDBC

//CLASE PARA TEST SIN SQL INJECTION (METODO PRIMITIVO, USAR DBJDBC2)
public class DBJDBC {

    //Metodo para insertar un Usuario en la tabla User
    public static void insert() throws SQLException{
        //Nos conectamos a la BBDD y lanzamos una sentencia para insertar un usuario
        Connection connection = DBUtils.getConnection();
        Statement statement1 = connection.createStatement();
        statement1.execute("INSERT INTO User (ID, nombre, mail) VALUES (0, 'Miguel', '198.163.2.1')");
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
            String lastName, address;
            while(rs.next()){
                id = (Integer) rs.getObject(1);
                lastName = (String) rs.getObject(2);
                address = (String) rs.getObject(3);


                System.out.println(id+" "+lastName+" "+address+" ");
            }
        }
        catch (Exception e){
            //log.d("",e)
            e.printStackTrace();
        } finally {
            connection.close(); //CERRAR CONEXION SIEMPRE EN EL FINALLY!!
        }
    }

    public static void main(String[] args) throws Exception{
        insert();
        findAll();
    }
}
