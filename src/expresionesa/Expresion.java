/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expresionesa;

import modelo.Nodo;
import modelo.Pila;

public class Expresion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String prefijo = NotacionAritmetica.convertirInfijaAPrefija("3.7+5*2");
        System.out.println(prefijo);
        System.out.println(NotacionAritmetica.evaluarPrefija(prefijo));
        
        String prefijo2 = NotacionAritmetica.convertirInfijaAPrefija("2^3+4*5.1");
        System.out.println(prefijo2);
        System.out.println(NotacionAritmetica.evaluarPrefija(prefijo2));
        
        String prefijo4 = NotacionAritmetica.convertirInfijaAPrefija("-4 + ---3*   (2+ +5)");
        System.out.println(prefijo4);
        System.out.println(NotacionAritmetica.evaluarPrefija(prefijo4));
        
        String prefijo5 = NotacionAritmetica.convertirInfijaAPrefija("(2 -   -8 + 3 / 2)");
        System.out.println(prefijo5);
        System.out.println(NotacionAritmetica.evaluarPrefija(prefijo5));
        
        try{
            String op = NotacionAritmetica.convertirInfijaAPrefija("$%++345");
            Double op2 = NotacionAritmetica.evaluarPrefija(op);
            System.out.println(op2);
        }catch(Exception e){
            System.out.println("La operación no está escrita correcta");
        }
    }
    
}
