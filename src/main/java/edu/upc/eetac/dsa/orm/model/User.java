package edu.upc.eetac.dsa.orm.model;

import edu.upc.eetac.dsa.RandomUtils;

public class User {

    public String ID;
    public String nombre;
    public String mail;

    public User(String nombre, String mail) {
        this.ID = RandomUtils.generateID(6);
        this.nombre = nombre;
        this.mail = mail;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getID(){
        return this.ID;
    }
}
