/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 * Este enum me permite poder almacenar los distintos tipos de producciones que estan presentes en mi analizador lexico 
 * @author luis
 */
public enum Producciones {
    
    /**
     * Este enum representa el estado terminal terminal Finalizar
     */
    FINALIZAR("FINALIZAR"),

    /**
     * Este enum representa cualquier inicio de cualquier estructura de producción
     */
    INICIAR("ESCRIBIR, SI, REPETIR, O ID"),

    /**
     * Este enum representa el estado terminal de Escribir
     */
    ESCRITURA("ESCRIBIR"),

    /**
     * Este enum representa el estado terminal de un lexema que puede ser representado por un literal, un identificador o un número
     */
    LEXEMA("LITERAL, ID, O NUMERO"),

    /**
     * Este enum representa el estado terminal de Repetir
     */
    REPETIR("REPETIR"),

    /**
     * Este Enum representa el resultado de una regla de produccón LH la cual puede desencadenar en un número decimal o un identificador
     */
    TERMINALH("NUMERO O ID"),

    /**
     * Este enum representa el estado terminal de una condicional
     */
    CONDICINAL("SI"),

    /**
     * Este enum representa el estado terminal de un valor booleano el cual puede terminar en un estado verdadero o falso
     */
    VALORBOOLEANO("VERDADERO O FALSO"),

    /**
     * Este Enum representa el resultado de una regla de produccón NX la cual puede desencadenar en un número decimal, en un identificador, en un signo +, *, (, ) o el final de dicha producción
     */
    PRODUCCIONX("NUMERO, ID, +, * (, ), O FIN"),

    /**
     Este Enum representa el resultado de una regla de produccón NXP la cual puede desencadenar en un número decimal, en un identificador, en un signo +, *, (, ) o el final de dicha producción
     */
    PRODUCCIONXP("NUMERO, ID, +, * (, ), O FIN"),

    /**
     * Este Enum representa el resultado de una regla de produccón NT la cual puede desencadenar en un número decimal, en un identificador, en un signo +, *, (, ) o el final de dicha producción
     */
    PRODUCCIONT("NUMERO, ID, +, * (, ), FIN"),

    /**
     * Este Enum representa el resultado de una regla de produccón NTP la cual puede desencadenar en un número decimal, en un identificador, en un signo +, *, (, ) o el final de dicha producción
     */
    PRODUCCIONTP("NUMERO, ID, +, * (, ), FIN"),

    /**
     * Este Enum representa el resultado de una regla de produccón NF la cual puede desencadenar en un número decimal, en un identificador, en un signo +, *, (, ) o el final de dicha producción
     */
    PRODUCCIONF("NUMERO, ID, +, * (, ), O FIN"),

    /**
     * Este enum representa el estado terminal de una asignacion el cual es un identificador
     */
    ASIGNACION("ID"),

    /**
     * Este Enum representa el resultado de una regla de produccón que desencadena al obtener epsilone la cual puede desencadenar en un número decimal, en un identificador, en un signo +, *, (, ) o el final de dicha producción
     */
    EPSILON("NUMERO, ID, +, * (, ), O FIN");
   
   private String esperarProducciones;
   
   private Producciones(String esperarProducciones){
       this.esperarProducciones = esperarProducciones;
   }

    /**
     *Este metodo me ayuda a obtener el valor de espera para poder absetenerme a realizar una producción
     * @return
     */
    public String getEsperarProducciones() {
        return esperarProducciones;
    }

    /**
     * Este metodo me permite cambiar el valor de espera de las producciones obtenidas
     * @param esperarProducciones
     */
    public void setEsperarProducciones(String esperarProducciones) {
        this.esperarProducciones = esperarProducciones;
    } 
}
