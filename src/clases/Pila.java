/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author kamaida
 */
public class Pila {

    Nodo cabeza;

    public Nodo getPunta() {
        return cabeza;
    }

    public void setPunta(Nodo punta) {
        this.cabeza = punta;
    }

    public void insertar(String expresion) {
        Nodo p;
        Nodo q = new Nodo(expresion);
        if (cabeza == null) {
            q.setLiga(null);
            cabeza = q;
        } else {
            p = cabeza;

            q.setLiga(cabeza);
            cabeza = q;
        }

    }

    public String extraer() {
        String d = cabeza.getExpresion();
        if (cabeza == null) {
            System.out.println("La lista esta vacia");
            return " ";
        } else {

            cabeza = cabeza.getLiga();

        }
        return d;
    }

    public void mostrar() {
        Nodo reco = cabeza;
        System.out.println("listado de todos los elementos de la pila");
        while (reco != null) {
            System.out.println(reco.getExpresion() + "-");
            reco = reco.getLiga();
        }

    }

    public String recorrer() {
        Nodo p;
        p = cabeza;
        String s = " ";

        while (p != null) {
            s += p.getExpresion();
            p = p.getLiga();
        }
        return s;
    }

    public int contador() {
        Nodo c;
        int r = 0;
        c = cabeza;
        while (c != null) {
            r++;
            c = c.getLiga();

        }
        return r;
    }
}
