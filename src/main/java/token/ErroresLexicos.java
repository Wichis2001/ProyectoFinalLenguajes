/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package token;
import java.util.ArrayList;

/**
 * Esta clase se encarga de poder almacenar los distintos erroes lexicos que se presentaron al momento de desarrollar el programa
 * @author luis
 */
public class ErroresLexicos {
    
    /**
     * Este array estatico se encarga de almacenar la cadena que contiene el error 
     */
    public static ArrayList<String> cadena = new ArrayList<>();

    /**
     * Este array estatico se encarga de poder almacenar el caracter que contiene el error para poder almacenar dicho errror
     */
    public static ArrayList<Character> caracter = new ArrayList<>();

    /**
     * Este array se encarga de poder almacenar la posicion de la fila en la cual esta ubicado el error
     */
    public static ArrayList<Integer> fila = new ArrayList<>();

    /**
     * Este array se encarga de almacenar la columna en la cual esta ubicada el error
     */
    public static ArrayList<Integer> columna = new ArrayList<>();
    private int filaActual = 1;
    private int columnaActual = 0;
    private String cadenaActual = "";

    /**
     * Esta variable estatica me indica la existencia o no de errores lexicos al momento de leer el programa
     */
    public static boolean existenciaErrores = false;

    /**
     * Este metodo se encarga de poder recopilar y almacenar en un array los errores que el analizador sintactico a lagrado almacenar
     * @param caracter
     * @param estado 
     */
    public void recopilarErroesAnalizador(char caracter, int estado) {
        //Verificamos si encontramos un estado de error -3
        if (estado == -3) {
            //Realizamos un aumento de filas y reiniciamos la columna
            this.filaActual++;
            this.columnaActual = 0;
            this.cadenaActual = "";
        } else {
            //Verificamos si nos encontramos en un estado de error -1 para definir que se encontro un error
            if (estado == -1) {
                this.existenciaErrores = true;
                this.columnaActual++;
                this.cadenaActual += caracter;
                this.caracter.add(caracter);
                this.cadena.add(cadenaActual);
                this.fila.add(this.filaActual);
                this.columna.add(this.columnaActual);
                this.cadenaActual = "";
            } else {
                //En caso contrario aumentamos el valor de la columna
                this.columnaActual++;
                //Verificamos si el estado es igual a -2
                if (estado == -2) {
                    this.cadenaActual = "";
                } else {
                    this.cadenaActual += caracter;
                }
            }
        }       
    }

    /**
     * Este metodo me devuelve el valor booleano que contiene la variable de existencia de errores
     * @return
     */
    public boolean existenciaErrores() {
        return existenciaErrores;
    }
}
