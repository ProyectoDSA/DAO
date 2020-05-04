package edu.upc.eetac.dsa.orm.model;

public class User {

    public int ID;
    public String nombre;
    public String mail;

    public User(int ID, String nombre, String mail) {
        this.ID = ID;
        this.nombre = nombre;
        this.mail = mail;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
