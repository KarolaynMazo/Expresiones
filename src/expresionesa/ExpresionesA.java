/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expresionesa;

import modelo.Nodo;
import modelo.Pila;

/**
 *
 * @author kamaida
 */
public class ExpresionesA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //String prefija = NotacionAritmetica.convertirInfijaAPrefija("3.7+5*2");
        String prefija = NotacionAritmetica.convertirInfijaAPrefija("3.7+5*2");
        System.out.println(prefija);
       // System.out.println(NotacionAritmetica.evaluarPrefija("-+7*45+20"));
        System.out.println(NotacionAritmetica.evaluarPrefija(prefija));
        
        String prefija2 = NotacionAritmetica.convertirInfijaAPrefija("2^3+4*5.1");
        System.out.println(prefija2);
       // System.out.println(NotacionAritmetica.evaluarPrefija("-+7*45+20"));
        System.out.println(NotacionAritmetica.evaluarPrefija(prefija2));
    }
    
}
