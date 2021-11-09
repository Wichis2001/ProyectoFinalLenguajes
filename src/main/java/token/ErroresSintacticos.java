/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package token;

import java.util.ArrayList;
import javax.swing.JTable;


/**
 *
 * @author luis
 */
public class ErroresSintacticos {
    
    public static ArrayList<String> cadena = new ArrayList<>();
    public static ArrayList<Character> caracter = new ArrayList<>();
    public static ArrayList<Integer> fila = new ArrayList<>();
    public static ArrayList<Integer> columna = new ArrayList<>();
    private int filaActual = 1;
    private int columnaActual = 0;
    private String cadenaActual = "";
    public static boolean existenciaErrores = false;

    /**
     * Este metodo se encarga de poder recopilar y almacenar en un array los errores que el analizador sintactico a lagrado almacenar
     * @param Caracter
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
                this.columnaActual++;
                if (estado == -2) {
                    this.cadenaActual = "";
                } else {
                    this.cadenaActual += caracter;
                }

            }
        }
        
    }

    public boolean existenciaErrores() {
        return existenciaErrores;
    }
}
