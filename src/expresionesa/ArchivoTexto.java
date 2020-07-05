/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expresionesa;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
/**
 *
 * @author Andrés Laverde
 */
public class ArchivoTexto {
    public static String leerTexto(String rutaArchivo){
      StringBuilder sb = new StringBuilder();
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (rutaArchivo);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null)
            sb.append(linea + "\n");
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      
      return sb.toString();
    }
    
    public static boolean guardarTexto(String texto, String rutaArchivo){
        FileWriter fichero = null;
        PrintWriter pw = null;
        boolean resultado = false;
        try
        {
            fichero = new FileWriter(rutaArchivo, true);
            pw = new PrintWriter(fichero);
            pw.println(texto);
            
            resultado = true;

        } catch (Exception e) {
            resultado = false;
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              resultado = false;
              e2.printStackTrace();
              
           }
        }
        
        return resultado;
    }
}
