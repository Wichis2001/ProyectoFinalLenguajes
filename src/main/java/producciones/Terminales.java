/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producciones;
import enums.Producciones;

/**
 * Este metodo se encarga de determinar la poscion en la cual estara determinada mi matriz de transiciones a partir de la posicion en la que esta se encuentre
 * @author luis
 */
public class Terminales {

    /**
     * Este metodo me devuelve el estado en la que se encuentra un eestado
     * @param producciones
     * @return
     */
    public int getEstado(Producciones producciones) {
        int valor = 0;
        //Verificamos la producción de la que provenimos
        switch (producciones) {
            //Verificamos si tenemos una producción INICIAR
            case INICIAR:
                valor = 0;
                break;
            //Verificamos si tenemos una producción ESCRITURA
            case ESCRITURA:
                valor = 1;
                break;
            //Verificamos si tenemos una producción LEXEMA
            case LEXEMA:
                valor = 2;
                break;
            //Verificamos si tenemos una producción REPETIR
            case REPETIR:
                valor = 3;
                break;
            //Verificamos si tenemos una producción TERMINALH    
            case TERMINALH:
                valor = 4;
                break;
            //Verificamos si tenemos una producción CONDICIONAL    
            case CONDICINAL:
                valor = 5;
                break;
            //Verificamos si tenemos una producción de un valor BOOLEANO    
            case VALORBOOLEANO:
                valor = 6;
                break;
            //Verificamos si tenemos una producción X
            case PRODUCCIONX:
                valor = 7;
                break;
            //Verificamos si tenemos una producción XP
            case PRODUCCIONXP:
                valor = 8;
                break;
            //Verificamos si tenemos una producción T    
            case PRODUCCIONT:
                valor = 9;
                break;
            //Verificamos si tenemos una producción TP    
            case PRODUCCIONTP:
                valor = 10;
                break;
            //Verificamos si tenemos una producción F    
            case PRODUCCIONF:
                valor = 11;
                break;
            //Verificamos si tenemos una producción ASIGNACIÓN
            case ASIGNACION:
                valor = 12;
                break;
        }
        return valor;
    }
    
    /**
     * Este metodo se encarga de devolver el valor terminarl de acorde a las estructuras sintacticas definidas por el programa
     * @param tipoToken
     * @return posision del terminal en la matriz
     */
    public int getValorTerminal(String tipoToken) {
        int valor = 0;
        //Verificamos el tipo de token en el que estamos ubicados
        switch (tipoToken) {
            //Verificamos si tenemos un tipo de token ESCRIBIR
            case "ESCRIBIR":
                valor = 0;
                break;
            //Verificamos si tenemos un tipo de token FIN
            case "FIN":
                valor = 1;
                break;
            //Verificamos si tenemos un tipo de token REPETIR    
            case "REPETIR":
                valor = 5;
                break;
            //Verificamos si tenemos un tipo de token INICIAR
            case "INICIAR":
                valor = 6;
                break;
            //Verificamos si tenemos un tipo de token SI    
            case "SI":
                valor = 7;
                break;
            //Verificamos si tenemos un tipo de token VERDADERO
            case "VERDADERO":
                valor = 9;
                break;
            //Verificamos si tenemos un tipo de token FALSO
            case "FALSO":
                valor = 10;
                break;
            //Verificamos si tenemos un tipo de token ENTONCES    
            case "ENTONCES":
                valor = 8;
                break;
            //Verificamos si tenemos un tipo de token de un identificador    
            case "id":
                valor = 4;
                break;
            //Verificamos si tenemos un tipo de token LITERAL    
            case "Literal":
                valor = 2;
                break;
            //Verificamos si tenemos un tipo de token de un número decimal    
            case "Numero":
                valor = 3;
                break;
            //Verificamos si tenemos un tipo de token de un signo +    
            case "+":
                valor = 11;
                break;
            //Verificamos si tenemos un tipo de token de un signo *    
            case "*":
                valor = 12;
                break;
            //Verificamos si tenemos un tipo de token de un signo de parentesis de apertura    
            case "(":
                valor = 13;
                break;
            //Verificamos si tenemos un tipo de token de un parentesis de cierre    
            case ")":
                valor = 14;
                break;
            //Verificamos si tenemos un tipo de token de FINALIZAR    
            case "FINALIZAR":
                valor = 15;
                break;          
        }        
        return valor;
    }
}
