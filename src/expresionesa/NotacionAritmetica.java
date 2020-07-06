/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expresionesa;

import modelo.Pila;
public class NotacionAritmetica {
    
    /**
     * Primero: a y b
     * El método recibe una expresión aritmética en notación infija y devuelve
     * su equivalente en notación prefija.
     * 
     * @param expresionInfija Expresión aritmética en notación infija.
     * @return Expresión aritmética en notación prefija.
     */
    public static String convertirInfijaAPrefija(String expresionInfija){
        String dato = "";
        Pila pInfija = obtPilaExpresion(expresionInfija, false);
        Pila pPrefija = new Pila();
        Pila pilaAuxiliar = new Pila();
      
        while(pInfija.estaVacia() == false){
            dato = pInfija.desapilar().getDato();
            
            if(esOperandoNumerico(dato)){
                pPrefija.apilar(dato);
            }else{
                if(pilaAuxiliar.estaVacia()){
                    pilaAuxiliar.apilar(dato);
                    continue;
                }
                
                int origenDato = obtenerOrigen(dato.charAt(0));
                int origenCabezaAuxiliar = obtenerOrigen(pilaAuxiliar.getTope().getDato().charAt(0));
                
                if(dato.equals(")") || dato.equals("(")){
                    if(dato.equals(")")){
                        pilaAuxiliar.apilar(dato);
                    }else{
                        boolean parar = false;
                        
                        while(pilaAuxiliar.estaVacia() == false && parar == false){
                            if(pilaAuxiliar.getTope().getDato().equals(")")){
                                pilaAuxiliar.desapilar();
                                parar = true;
                            }else{
                                pPrefija.apilar(pilaAuxiliar.desapilar().getDato());
                            }
                        }
                    }
                }else{
                    if(pilaAuxiliar.getTope().getDato().equals(")")){
                        pilaAuxiliar.apilar(dato);
                    }else{
                        if(origenDato >= origenCabezaAuxiliar){
                            pilaAuxiliar.apilar(dato);
                        }else{
                            boolean parar = false;
                            while(pilaAuxiliar.estaVacia() == false && parar == false){
                                origenCabezaAuxiliar = obtenerOrigen(pilaAuxiliar.getTope().getDato().charAt(0));
                                
                                if(origenCabezaAuxiliar > origenDato){
                                    pPrefija.apilar(pilaAuxiliar.desapilar().getDato());
                                    
                                    if(pilaAuxiliar.estaVacia()){
                                        pilaAuxiliar.apilar(dato);
                                        parar = true;
                                    }
                                }else{
                                    pilaAuxiliar.apilar(dato);
                                    parar = true;
                                }
                            }
                        }
                    }
                }
                
            }
        }
        
        while(pilaAuxiliar.estaVacia() == false){
            pPrefija.apilar(pilaAuxiliar.desapilar().getDato());
        }
        
        return pPrefija.toString().replace(")", "");
    }
    
    /**
     * Segundo:
     * El método recibe una expresión aritmética en notación prefija y devuelve
     * el resultado númerico de evaluar dicha expresión.
     * 
     * @param expresionPrefija Expresión aritmética en notación prefija.
     * @return Resultado de evaluar la expresión en notación prefija.
     */
    public static double evaluarPrefija(String expresionPrefija){
        Pila p = new Pila();
        Pila pPrefijo = obtPilaExpresion(expresionPrefija, true);
       
        
        while(pPrefijo.estaVacia() == false){
            String dato = pPrefijo.desapilar().getDato();
            
            if(esOperandoNumerico(dato)){
                p.apilar(dato);
            }else{
                double num1 = Double.parseDouble(p.desapilar().getDato());
                double num2 = Double.parseDouble(p.desapilar().getDato());
                // el segun caso, esta diseñado con base a la prioridad de simbolos
                
                switch (dato.charAt(0)) { 
                case '^': 
                    p.apilar(String.valueOf(Math.pow(num1, num2))); 
                    break;
                case '*': 
                    p.apilar(String.valueOf(num1 * num2)); 
                    break; 
                case '/': 
                    p.apilar(String.valueOf(num1 / num2)); 
                    break; 
                case '%': 
                    p.apilar(String.valueOf(num1 % num2)); 
                    break;
                case '+': 
                    p.apilar(String.valueOf(num1 + num2)); 
                    break; 
                case '-': 
                    p.apilar(String.valueOf(num1 - num2)); 
                    break; 
                
                } 
            }
        }
        
        return Double.parseDouble(p.getTope().getDato());
    }
    
    /*Metodos privados*/
    
    private static Pila obtPilaExpresion(String expresionInfija, boolean valoraExpresion){
        if(valoraExpresion == false){
            expresionInfija = ejecucionOperadores(expresionInfija);
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
            
            if(esCharNumero(digito)){
                
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
    
    private static String ejecucionOperadores(String expresionInfija){
        expresionInfija = expresionInfija.replace(" ", "");
        String concatenador = "";
        boolean operadorSolucionado = false;
        boolean operadorSiguiente = false;
        
        while(operadorSolucionado == false){
            concatenador = "";
            operadorSiguiente = false;
            
            for (int i = 0; i < expresionInfija.length(); i++) {
                if(i < expresionInfija.length()- 1){
                    String operando1 = String.valueOf(expresionInfija.charAt(i));
                    String operando2 = String.valueOf(expresionInfija.charAt(i+1));
                    
                    if(esOperador(operando1) && esOperador(operando2)){
                        operadorSiguiente = true;
            
                        if(operando1.equals("-") && operando2.equals("+")){
                            concatenador += "-";
                        }

                        if(operando1.equals("+") && operando2.equals("-")){
                            concatenador += "-";
                        }

                        if(operando1.equals("+") && operando2.equals("+")){
                            concatenador += "+";
                        }

                        if(operando1.equals("-") && operando2.equals("-")){
                            concatenador += "+";
                        }

                        i = i + 1;
                    }else{
                        concatenador += expresionInfija.charAt(i);
                    }
                }else{
                    concatenador += expresionInfija.charAt(i);
                }
            }
            
            if(operadorSiguiente){
                expresionInfija = concatenador;
            }else{
                operadorSolucionado = true;
            }
        }
        
        return concatenador;
    }
    
    private static int obtenerOrigen(char elemento) {
        int respuesta = 0;
        switch (elemento) {
        case ')': case '(':
            respuesta = 4; break;
        case '^':
            respuesta = 3; break;
        case '*': case '/': case '%':
            respuesta = 2; break;
        case '+': case '-':
            respuesta = 1; break;

        }
        return respuesta;
    }
    
    private static boolean esCharNumero(char dat){
        if(dat >= 48 && dat <=57){
            return true;
        }
        
        return false;
    }
    
    private static boolean esOperandoNumerico(String dat){
        try{
            double numero = Double.parseDouble(dat);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    private static boolean esOperador(String dat){
        if(dat.equals("+") || dat.equals("-") 
                || dat.equals("*") || dat.equals("/")
                || dat.equals("%") || dat.equals("^")){
            return true;
        }
        
        return false;
    }
}
