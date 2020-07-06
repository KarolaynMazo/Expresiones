package modelo;

public class Pila {

    Nodo cabeza = null;

    public Nodo getTope() {
        return cabeza;
    }
    
    public boolean estaVacia(){
        return cabeza == null;
    }

    public void apilar(String dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (estaVacia()) {
            cabeza = nuevoNodo;
        } else {
            nuevoNodo.setLiga(cabeza);
            cabeza = nuevoNodo;
        }
    }

    public Nodo desapilar(){
        Nodo Desapilado = null;
        
        if(!estaVacia()){
            Desapilado = cabeza;
            cabeza = Desapilado.getLiga();
        }
        
        return Desapilado;
    }

    public void imprimir(){
        System.out.println(this.toString());
    }
    
    @Override
    public String toString(){
        String sb = new String();
        Nodo dato = cabeza;
        while(dato != null){
            sb += dato.getDato();
            dato = dato.getLiga();
        }
        
        return sb;
    }
}
