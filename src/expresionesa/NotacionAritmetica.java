/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expresionesa;

import modelo.Pila;

/**
 *
 * @author kamaida
 */
public class NotacionAritmetica {
    
    /**
     * Punto 1. Literal a y b
     * El método recibe una expresión aritmética en notación infija y devuelve
     * su equivalente en notación prefija.
     * 
     * @param expresionInfija Expresión aritmética en notación infija.
     * @return Expresión aritmética en notación prefija.
     */
    public static String convertirInfijaAPrefija(String expresionInfija){
        Pila pilaInfija = obtenerPilaDeExpresion(expresionInfija, false);

        Pila pilaPrefija = new Pila();
        Pila pilaTemporal = new Pila();
        String dato = "";
        while(pilaInfija.estaVacia() == false){
            dato = pilaInfija.desapilar().getDato();
            
            if(esDatoNumerico(dato)){
                pilaPrefija.apilar(dato);
            }else{
                if(pilaTemporal.estaVacia()){
                    pilaTemporal.apilar(dato);
                    continue;
                }
                
                int precedenciaDato = obtenerPrecedencia(dato.charAt(0));
                int precedenciaTopePilaTemporal = obtenerPrecedencia(pilaTemporal.getTope().getDato().charAt(0));
                
                if(dato.equals(")") || dato.equals("(")){
                    if(dato.equals(")")){
                        pilaTemporal.apilar(dato);
                    }else{
                        boolean parar = false;
                        
                        while(pilaTemporal.estaVacia() == false && parar == false){
                            if(pilaTemporal.getTope().getDato().equals(")")){
                                pilaTemporal.desapilar();
                                parar = true;
                            }else{
                                pilaPrefija.apilar(pilaTemporal.desapilar().getDato());
                            }
                        }
                    }
                }else{
                    if(pilaTemporal.getTope().getDato().equals(")")){
                        pilaTemporal.apilar(dato);
                    }else{
                        if(precedenciaDato >= precedenciaTopePilaTemporal){
                            pilaTemporal.apilar(dato);
                        }else{
                            boolean parar = false;
                            while(pilaTemporal.estaVacia() == false && parar == false){
                                precedenciaTopePilaTemporal = obtenerPrecedencia(pilaTemporal.getTope().getDato().charAt(0));
                                
                                if(precedenciaTopePilaTemporal > precedenciaDato){
                                    pilaPrefija.apilar(pilaTemporal.desapilar().getDato());
                                    
                                    if(pilaTemporal.estaVacia()){
                                        pilaTemporal.apilar(dato);
                                        parar = true;
                                    }
                                }else{
                                    pilaTemporal.apilar(dato);
                                    parar = true;
                                }
                            }
                        }
                    }
                }
                
            }
        }
        
        while(pilaTemporal.estaVacia() == false){
            pilaPrefija.apilar(pilaTemporal.desapilar().getDato());
        }
        
        return pilaPrefija.toString().replace(")", "");
    }
    
    /**
     * Punto 2.
     * El método recibe una expresión aritmética en notación prefija y devuelve
     * el resultado númerico de evaluar dicha expresión.
     * 
     * @param expresionPrefija Expresión aritmética en notación prefija.
     * @return Resultado de evaluar la expresión en notación prefija.
     */
    public static double evaluarPrefija(String expresionPrefija){
        Pila pilaPrefija = obtenerPilaDeExpresion(expresionPrefija, true);
        Pila pila = new Pila();
        
        while(pilaPrefija.estaVacia() == false){
            String dato = pilaPrefija.desapilar().getDato();
            
            if(esDatoNumerico(dato)){
                pila.apilar(dato);
            }else{
                double numero1 = Double.parseDouble(pila.desapilar().getDato());
                double numero2 = Double.parseDouble(pila.desapilar().getDato());
                
                switch (dato.charAt(0)) { 
                case '+': 
                    pila.apilar(String.valueOf(numero1 + numero2)); 
                    break; 
                case '-': 
                    pila.apilar(String.valueOf(numero1 - numero2)); 
                    break; 
                case '*': 
                    pila.apilar(String.valueOf(numero1 * numero2)); 
                    break; 
                case '/': 
                    pila.apilar(String.valueOf(numero1 / numero2)); 
                    break; 
                case '%': 
                    pila.apilar(String.valueOf(numero1 % numero2)); 
                    break; 
                case '^': 
                    pila.apilar(String.valueOf(Math.pow(numero1, numero2))); 
                    break;
                } 
            }
        }
        
        return Double.parseDouble(pila.getTope().getDato());
    }
    
    /*Metodos privados*/
    
    private static Pila obtenerPilaDeExpresion(String expresionInfija, boolean evaluarExpresion){
        if(evaluarExpresion == false){
            expresionInfija = ResolverOperadores(expresionInfija);
        }
        Pila pilaInfija = new Pila();
        
        String dato = "";
        
        for (int i = 0; i < expresionInfija.length(); i++) {
            char digito = expresionInfija.charAt(i);
            
            if(i == 1){
                if(esOperador(String.valueOf(digito)) && 
                    expresionInfija.charAt(i) == expresionInfija.charAt(i-1) &&
                    digito == '-'){
                    dato = dato + expresionInfija.charAt(i);
                    continue;
                }
            }
            
            if(esCaracterNumero(digito)){
                
                if(i < expresionInfija.length() - 1){
                    if(expresionInfija.charAt(i + 1) == '.'){
                        dato = dato + expresionInfija.charAt(i);
                        dato = dato + expresionInfija.charAt(i + 1);
                        dato = dato + expresionInfija.charAt(i + 2);
                        
                        pilaInfija.apilar(dato);
                        dato = "";
                        i = i+2;
                        continue;
                    }
                }
            }
            
            if(dato.equals("")){
                pilaInfija.apilar(String.valueOf(digito));
            }else{
                pilaInfija.apilar(dato + digito);
                dato = "";
            }
        }
        
        if(dato.equals("") == false){
            pilaInfija.apilar(dato);
        }
        
        return pilaInfija;
    }
    
    private static String ResolverOperadores(String expresionInfija){
        expresionInfija = expresionInfija.replace(" ", "");
        boolean operadoresResueltos = false;
        boolean operadoresAdyacentes = false;
        String sb = "";
        
        while(operadoresResueltos == false){
            sb = "";
            operadoresAdyacentes = false;
            
            for (int i = 0; i < expresionInfija.length(); i++) {
                if(i < expresionInfija.length()- 1){
                    String dato1 = String.valueOf(expresionInfija.charAt(i));
                    String dato2 = String.valueOf(expresionInfija.charAt(i+1));
                    
                    if(esOperador(dato1) && esOperador(dato2)){
                        operadoresAdyacentes = true;
            
                        if(dato1.equals("-") && dato2.equals("+")){
                            sb += "-";
                        }

                        if(dato1.equals("+") && dato2.equals("-")){
                            sb += "-";
                        }

                        if(dato1.equals("+") && dato2.equals("+")){
                            sb += "+";
                        }

                        if(dato1.equals("-") && dato2.equals("-")){
                            sb += "+";
                        }

                        i = i + 1;
                    }else{
                        sb += expresionInfija.charAt(i);
                    }
                }else{
                    sb += expresionInfija.charAt(i);
                }
            }
            
            if(operadoresAdyacentes){
                expresionInfija = sb;
            }else{
                operadoresResueltos = true;
            }
        }
        
        return sb;
    }
    
    private static int obtenerPrecedencia(char elemento) {
        int res = 0;
        switch (elemento) {
        case ')': case '(':
            res = 4; break;
        case '^':
            res = 3; break;
        case '*': case '/': case '%':
            res = 2; break;
        case '+': case '-':
            res = 1; break;

        }
        return res;
    }
    
    private static boolean esCaracterNumero(char dato){
        if(dato >= 48 && dato <=57){
            return true;
        }
        
        return false;
    }
    
    private static boolean esDatoNumerico(String dato){
        try{
            double numero = Double.parseDouble(dato);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    private static boolean esOperador(String dato){
        if(dato.equals("+") || dato.equals("-") 
                || dato.equals("*") || dato.equals("/")
                || dato.equals("%") || dato.equals("^")){
            return true;
        }
        
        return false;
    }
}
