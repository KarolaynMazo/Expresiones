package modelo;

public class Nodo {
    Nodo siguiente; 
    String dato;

    public Nodo(String dato) {
        this.dato = dato;
        this.siguiente=null;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo liga) {
        this.siguiente = liga;
    }
}
