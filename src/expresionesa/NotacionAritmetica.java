/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expresionesa;

import clases.Pila;
import java.util.Arrays;

/**
 *
 * @author kamaida
 */
public class NotacionAritmetica {

    /* El metodo recibe una expresion aritmetica en notacion infija y devulve el 
    equivalente en notacion prefija
     */
    public static String convertirInfijaAPrefija(String expresionInfija) {
        int n = expresionInfija.length();
        String vec1[] = new String[n];
        String vec2[] = new String[n];
        String car = "";
        String expresionfinal="";
        char d= 0; 
        char ex=0;
        Pila p = new Pila();
        int i=n-1;
        int j=0;
        
        while (i > 0) {
            if (expresionInfija.charAt(i) == ')'|| expresionInfija.charAt(i) == '+' || expresionInfija.charAt(i) == '-'
                    || expresionInfija.charAt(i) == '*' ||expresionInfija.charAt(i) == '/' || expresionInfija.charAt(i) == '^'
                    || expresionInfija.charAt(i) == '%' || expresionInfija.charAt(i) == ']'
                    ||expresionInfija.charAt(i) == '(' || expresionInfija.charAt(i) == '[') {

                p.apilar(expresionInfija.charAt(i));
                  i=i-1;
                if(expresionInfija.charAt(i) == p.getPunta().getExpresion()){
                  d= p.desapilar();
                 System.out.println(d);
                }
                 
           
               // convertirInfijaAPrefija(expresionInfija.split(car));
                
            } else if (expresionInfija.charAt(i) != ')' || expresionInfija.charAt(i) != '+' || expresionInfija.charAt(i) != '-'
                    || expresionInfija.charAt(i) != '*' || expresionInfija.charAt(i) != '/' || expresionInfija.charAt(i) != '^'
                    || expresionInfija.charAt(i) != '%' || expresionInfija.charAt(i) != ']'
                    ||expresionInfija.charAt(i) != '(' || expresionInfija.charAt(i) != '[') {
                car = Character.toString(expresionInfija.charAt(i));
                vec1[i] = car;
                System.out.println(Arrays.toString(vec1));
                 i=i-1;
//                 ex=p.getPunta().getExpresion();
                if(expresionInfija.charAt(i) != ')' || expresionInfija.charAt(i) != '+' || expresionInfija.charAt(i) != '-'
                    || expresionInfija.charAt(i) != '*' || expresionInfija.charAt(i) != '/' || expresionInfija.charAt(i) != '^'
                    || expresionInfija.charAt(i) != '%' || expresionInfija.charAt(i) != ']'
                    ||expresionInfija.charAt(i) != '(' || expresionInfija.charAt(i) != '['){
                    d = p.desapilar();
                    car = Character.toString(d);
                    vec1[i]= car;
                    System.out.println(Arrays.toString(vec1));
                }
                
            }//
            
           // if(p.getPunta().getExpresion()==expresionInfija.charAt(i)){
                
            //}
            
            
            
           /* if (expresionInfija.charAt(i) == '(' || expresionInfija.charAt(i) == '['
                    ||expresionInfija.charAt(i) == ')' || expresionInfija.charAt(i) == '+' || expresionInfija.charAt(i) == '-'
                    || expresionInfija.charAt(i) == '*' || expresionInfija.charAt(i) == '/' || expresionInfija.charAt(i) == '^'
                    || expresionInfija.charAt(i) == '%' || expresionInfija.charAt(i) == ']') {
                
                 d= p.desapilar();
                 System.out.println(d);
            } else if(expresionInfija.charAt(i) != '(' || expresionInfija.charAt(i) != '['
                    ||expresionInfija.charAt(i) != ')' || expresionInfija.charAt(i) != '+' || expresionInfija.charAt(i) != '-'
                    || expresionInfija.charAt(i) != '*' || expresionInfija.charAt(i) != '/' || expresionInfija.charAt(i) != '^'
                    || expresionInfija.charAt(i) != '%' || expresionInfija.charAt(i) != ']'){
                d = p.desapilar();
                car = Character.toString(d);
                vec1[i]= car;
                System.out.println(Arrays.toString(vec1));
            }
           i=i-1;
            */
        }
         
        while(i>0){
            for(j=n-1; j<vec1[j].length();j++){
                vec2[j]=vec1[i];
                i-=i;
            }
            
        }
        while(i>0){
            expresionfinal+=vec2[i];
            
    }
        System.out.println(expresionfinal);
        return expresionfinal;
       
    }

    /* El metodo recibe una expresion aritmetica en    notacion prefija y devulve
    el resultado numerico de evaluar dicha expresion
     */

    public static double evaluarPrefija() {

        return 0;
    }

}
