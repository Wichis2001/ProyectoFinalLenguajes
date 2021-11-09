/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablaSimbolos;

/**
 * Esta clase me permite poder crear un simbolo para poder asignarle un valor numerico
 * @author luis
 */
public class Simbolos {
    
    private String nombre;
    private int valor;
    
    /**
     * Este metodo me devuelve el valor que posee un simbolo
     * @return
     */
    public int getValor() {
        return valor;
    }

    /**
     * Este metodo me permite cambiar el valor de un simbolo
     * @param valor
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Este metodo me devuelve el nombre que tiene un simbolo
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Este metodo me permite cambiar el nombre de un simbolo
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Este metodo me permite convertir un objeto simbolo a un String
     * @return
     */
    @Override
    public String toString() {
        return "Simbolo{" + "valor=" + valor + ", nombre=" + nombre + '}';
    }
}
