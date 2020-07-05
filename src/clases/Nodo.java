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
public class Nodo {
    char expresion;
    Nodo liga; 

    public Nodo(char expresion) {
        this.expresion = expresion;
        this.liga=null;
    }

    public char getExpresion() {
        return expresion;
    }

    public void setExpresion(char expresion) {
        this.expresion = expresion;
    }

    public Nodo getLiga() {
        return liga;
    }

    public void setLiga(Nodo liga) {
        this.liga = liga;
    }
    
    
    
    
    
    
    
    
}
