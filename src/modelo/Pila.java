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
        Nodo nodoDesapilado = null;
        
        if(!estaVacia()){
            nodoDesapilado = cabeza;
            cabeza = nodoDesapilado.getLiga();
        }
        
        return nodoDesapilado;
    }

    public void imprimir(){
        System.out.println(this.toString());
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Nodo dato = cabeza;
        while(dato != null){
            sb.append(dato.getDato());
            dato = dato.getLiga();
        }
        
        return sb.toString();
    }
}
