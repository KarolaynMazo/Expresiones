package modelo;

public class Pila {

    Nodo cabeza = null;

    public Nodo getCabeza() {
        return cabeza;
    }
    
    public void apilar(String dato) {
        Nodo nodo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nodo;
        } else {
            nodo.setSiguiente(cabeza);
            cabeza = nodo;
        }
    }

    public Nodo desapilar(){
        Nodo nodoDesapilado = null;
        
        if(cabeza != null){
            nodoDesapilado = cabeza;
            cabeza = nodoDesapilado.getSiguiente();
        }
        
        return nodoDesapilado;
    }

    public String pilaTexto(){
        Nodo dato = cabeza;
        String pilaTexto = "";
        while(dato != null){
            pilaTexto = pilaTexto + dato.getDato();
            dato = dato.getSiguiente();
        }
        
        return pilaTexto;
    }
}
