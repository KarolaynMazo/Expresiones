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
public class Cola {
    Nodo punta;

    public Cola() {
        this.punta = null;
    }

    public boolean estavacio() {
        if (punta == null) {
            return true;
        } else {
            return false;
        }

    }

    public void encolar(char expresion) {
        Nodo c, r, ult = null;
        c = new Nodo(expresion);
        if (punta == null) {
            punta = c;
        } else {
            r = punta;
            while (r != null) {
                ult = r;
                r = r.getLiga();
            }
            ult.setLiga(c);

        }

    }

    public String desencolar() {
        Nodo e;
        String g = "";
        if (estavacio()) {
            System.out.println("esta vacia");
        }
        else
        {
            e = punta;
            g= Character.toString(e.getExpresion());
            if (e.getLiga() == null) {
                punta = null;
            } else {
                punta = punta.getLiga();
            }
        }
        return g;

    }

    public String mostrar() {
        Nodo t;
        String f = "";
        if (estavacio()) {
            System.out.println("la cola esta vacia");
        } else {
            t = punta;
            while (t != null) {
                f += t.getExpresion() + "-";
                t = t.getLiga();
            }

        }

        return f;

    }

    
    
    
}
