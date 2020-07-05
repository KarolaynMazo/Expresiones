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
    char cab;
    
     public Pila() {
        cabeza = null;

    }
    public Nodo getPunta() {
        return cabeza;
    }

    public void setPunta(Nodo punta) {
        this.cabeza = punta;
    }

    public void apilar(char expresion) {
       
        Nodo q = new Nodo(expresion);
        if (cabeza == null) {
            q.setLiga(null);
            cabeza = q;
            cab= q.getExpresion();
        } else {
           

            q.setLiga(cabeza);
            cabeza = q;
            cab=q.expresion;
        }

    }

    public char desapilar() {
        char c;
        Nodo D; 
       // char d = cabeza.getExpresion();
        char e = 0; 
        if (cabeza == null) {
            System.out.println("La pila esta vacia");
            return e;
        } else {
             c=cabeza.getExpresion();
              D= new Nodo(c);
            cabeza = cabeza.getLiga();
          //  c=cabeza.getExpresion();

        }
        return D.getExpresion();
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
