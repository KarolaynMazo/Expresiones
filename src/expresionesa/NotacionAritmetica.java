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
        Pila pPrefija = new Pila();
        Pila pilaAuxiliar = new Pila();
        Pila pInfija = obtPila(expresionInfija, false);
      
        while(pInfija.getCabeza() != null){
            dato = pInfija.desapilar().getDato();
            
            if(esOperandoNumerico(dato) == false){
                if(pilaAuxiliar.getCabeza() == null){
                    pilaAuxiliar.apilar(dato);
                    continue;
                }
                
                int origenDato = jerarquia(dato.charAt(0));
                int origenCabezaAuxiliar = jerarquia(pilaAuxiliar.getCabeza().getDato().charAt(0));
                
                if(dato.equals(")") || dato.equals("(")){
                    if(dato.equals(")")){
                        pilaAuxiliar.apilar(dato);
                    }else{
                        boolean parar = false;
                        
                        while(pilaAuxiliar.getCabeza() != null && parar == false){
                            if(pilaAuxiliar.getCabeza().getDato().equals(")")){
                                pilaAuxiliar.desapilar();
                                parar = true;
                            }else{
                                pPrefija.apilar(pilaAuxiliar.desapilar().getDato());
                            }
                        }
                    }
                }else{
                    if(pilaAuxiliar.getCabeza().getDato().equals(")")){
                        pilaAuxiliar.apilar(dato);
                    }else{
                        if(origenDato >= origenCabezaAuxiliar){
                            pilaAuxiliar.apilar(dato);
                        }else{
                            boolean parar = false;
                            while(pilaAuxiliar.getCabeza() != null && parar == false){
                                origenCabezaAuxiliar = jerarquia(pilaAuxiliar.getCabeza().getDato().charAt(0));
                                
                                if(origenCabezaAuxiliar > origenDato){
                                    pPrefija.apilar(pilaAuxiliar.desapilar().getDato());
                                    
                                    if(pilaAuxiliar.getCabeza() == null){
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
                
            }else{
                pPrefija.apilar(dato);
            }
        }
        
        while(pilaAuxiliar.getCabeza() != null){
            pPrefija.apilar(pilaAuxiliar.desapilar().getDato());
        }
        
        String expresionPrefija = pPrefija.pilaTexto();
        expresionPrefija = expresionPrefija.replace(")", "");
        
        return expresionPrefija;
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
        Pila pPrefijo = obtPila(expresionPrefija, true);
       
        
        while(pPrefijo.getCabeza() != null){
            String token = pPrefijo.desapilar().getDato();
            
            if(esOperandoNumerico(token) == false){
                double num1 = Double.parseDouble(p.desapilar().getDato());
                double num2 = Double.parseDouble(p.desapilar().getDato());
                
                p.apilar(calcular(num1, num2, ""+token.charAt(0)));
            }else{
                p.apilar(token);
            }
        }
        
        return Double.parseDouble(p.getCabeza().getDato());
    }
    
    private static String calcular(double numero1, double numero2, String operador){
        double resultado = 0;
        
        if(operador.equals("+"))
            resultado =  numero1 + numero2;
        
        if(operador.equals("-"))
            resultado = numero1 - numero2;
        
        if(operador.equals("*"))
            resultado = numero1 * numero2;
        
        if(operador.equals("/"))
            resultado = numero1 / numero2;
        
        if(operador.equals("^"))
            resultado = Math.pow(numero1, numero2);
        
        if(operador.equals("%"))
            resultado = numero1 % numero2;

        return resultado + "";
    }
    
    private static Pila obtPila(String expresionInfija, boolean valoraExpresion){
        if(valoraExpresion == false){
            boolean operadorSiguiente = false;
            boolean signosOperados = false;
            expresionInfija = expresionInfija.replace(" ", "");
            String concatenador = "";

            while(signosOperados == false){
                concatenador = "";
                operadorSiguiente = false;

                for (int i = 0; i < expresionInfija.length(); i++) {
                    if(i < expresionInfija.length()- 1){
                        String operando1 = String.valueOf(expresionInfija.charAt(i));
                        String operando2 = String.valueOf(expresionInfija.charAt(i+1));

                        if(esOperador(operando1) && esOperador(operando2)){
                            concatenador += leySignos(operando1, operando2);
                            operadorSiguiente = true;

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
                    signosOperados = true;
                }
            }
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
            
            if(esOperandoNumerico(""+digito)){
                
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
    
    private static String leySignos(String operador1, String operador2){
        String concatenador = "";
        if(operador1.equals("-") && operador2.equals("+")){
            concatenador += "-";
        }

        if(operador1.equals("+") && operador2.equals("-")){
            concatenador += "-";
        }

        if(operador1.equals("+") && operador2.equals("+")){
            concatenador += "+";
        }

        if(operador1.equals("-") && operador2.equals("-")){
            concatenador += "+";
        }
        
        return concatenador;
    }
    
    private static int jerarquia(char elemento) {
        switch (elemento) {
            case '+': 
                return 1; 
            case '-':
                return 1;
            case '*': 
                return 2; 
            case '/': 
                return 2;
            case '%':
                return 2;
            case '^':
                return 3; 
            case ')': 
                return 4; 
            case '(':
                return 4;
            default:
                return 0;
        }
    }
    
    private static boolean esOperador(String dat){
        switch(dat){
            case "+":
                return true;
            case "-":
                return true;
            case "*":
                return true;
            case "/":
                return true;
            case "^":
                return true;
            case "%":
                return true;
            default:
                return false;
        }
    }
    
    public static boolean esOperandoNumerico(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
