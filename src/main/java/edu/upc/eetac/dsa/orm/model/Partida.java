package edu.upc.eetac.dsa.orm.model;

public class Partida {

    public int partidaID;
    public int playerID;
    public int puntuacion;
    public float tiempo;

    public Partida(int partidaID, int playerID, int puntuacion, float tiempo) {
        this.partidaID = partidaID;
        this.playerID = playerID;
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
    }

    //***GETTERS***

    public int getPartidaID() {
        return partidaID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public float getTiempo() {
        return tiempo;
    }

    //***SETTERS***
    public void setPartidaID(int partidaID) {
        this.partidaID = partidaID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }
}
