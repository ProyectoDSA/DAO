package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.model.Employee;
import edu.upc.eetac.dsa.orm.model.User;

import java.util.List;

//Interfaz donde definimos las operaciones CRUD que queremos realizar al usuario
public interface UserDAO {

    public String addUser(String name, String mail); //Añade un usuario en la tabla User
    public User getUser(int id); //Devuelve un usuario con cierto ID
    public void updateUser(int id, String name, String mail); //Actualiza los datos de un usuario
    public void deleteUser(int userID); //Elimina usuario de la tabla User
    public List<User> getUsers(); //Devuelve una lista con todos los usuarios de la tabla

}
