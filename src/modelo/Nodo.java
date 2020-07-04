package modelo;

public class Nodo {
    String dato;
    Nodo liga; 

    public Nodo(String dato) {
        this.dato = dato;
        this.liga=null;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Nodo getLiga() {
        return liga;
    }

    public void setLiga(Nodo liga) {
        this.liga = liga;
    }
}
