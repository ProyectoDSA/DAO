package edu.upc.eetac.dsa.orm.model;

public class Objeto {

    public int objectID;
    public String nombre;
    public int precio;
    //public int tipo; La comento porque nose si tendremos solo curas o armas tmbn


    public Objeto(int objectID, String nombre, int precio) {
        this.objectID = objectID;
        this.nombre = nombre;
        this.precio = precio;
    }

    //***GETTERS***
    public int getObjectID() {
        return objectID;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    //***SETTERS***
    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

}
