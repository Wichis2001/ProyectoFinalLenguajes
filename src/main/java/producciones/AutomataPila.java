/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producciones;
import enums.Producciones;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 * Esta clase me ayuda a poder crear un automata de pila la cual se encargargara de poder verificar que las estructuras sintacticas pueden llegar a ser cumplidas al momento de vaciar
 * @author luis
 */
public class AutomataPila {
    private Stack automataPila = new Stack();

    /**
     * Este constructor me permite poder almacenar las producciones de inicio y fin con la finalidad de poder empezar la lectura dentro de mi automata
     */
    public AutomataPila() {
        this.automataPila.push(Producciones.FINALIZAR.name());
        this.automataPila.push(Producciones.INICIAR);
    }
    
    /**
     * Este metodo me ayuda a poder comprobar la existencia de un Enum de comprobar la existencia de una produccion 
     * @param object
     * @return
     */
    public boolean comprobarExistenciaProduccion(Object object) {
        boolean includidoEnumProduccion = false;
        Producciones[] produccion = Producciones.values();
        //Recooremos las producciones obtenidas desde el array para verificar la existencia de dicha produccion
        for (Producciones producciones : produccion) {
            includidoEnumProduccion = producciones.equals(object);
            //Verificamos la existencia de dicha produccion para poder garantizarla
            if (includidoEnumProduccion) {
                break;
            }
        }
        return includidoEnumProduccion;
    }

    private boolean terminalExistenteEnPila(String tokenEvaluando) {
        //Verificamos si el automata contiene el token evaluado
        return this.automataPila.contains(tokenEvaluando);
    }

    /**
     * Este metodo se encarga de llenar una pila a travez de una palabra y el token que se empleara para poder verificar que se cumplan las estructuras sintacticas
     * @param palabra
     * @param token
     */
    public void llenarPila(String palabra, String token) {
        //Verificamos si la palabra a analizar no tiene un valor nulo
        if (palabra != null) {
            //Verificamos que la pila no viene un estado de epsilon, es decir una cadena vacia
            if (!palabra.equals("Ɛ")) {
                //Eliminamos el ultimo elemento introducido e la pila
                automataPila.pop();
                switch (palabra) {
                    //Verificamos si viene una estructura Escribir
                    case "ES":
                        //Introducimos a la pila las producciones iniciar y escribir
                        automataPila.push(Producciones.INICIAR);
                        automataPila.push(Producciones.ESCRITURA);
                        break;
                    //Verificamos si viene una estructura asignacion 
                    case "AS":
                        //Introducimos a la pila las producciones iniciar y asignacion
                        automataPila.push(Producciones.INICIAR);
                        automataPila.push(Producciones.ASIGNACION);
                        break;
                    //Verificamos si viene una estructura repetecion
                    case "RS":
                        //Introducimos a la pila las producciones iniciar y repetir
                        automataPila.push(Producciones.INICIAR);
                        automataPila.push(Producciones.REPETIR);
                        break;
                    //Verificamos si viene una estructura asignación
                    case "CS":
                        //Introducimos a la pila las producciones iniciar y repetir
                        automataPila.push(Producciones.INICIAR);
                        automataPila.push(Producciones.CONDICINAL);
                        break;
                    //Verificamos si vienen un signo de $ para marcar el fin de la lectura de una estructura sintactica
                    case "$":
                        automataPila.push(Producciones.EPSILON);
                        break;
                    //Verificamos si viene una estructura de produccion ESCRIBIR L FIN E
                    case "ESCRIBIR L FIN E":
                        //Agregamos una escritura, un fin que marque fin de producción, agregamos un lexema y una estructura escribir
                        automataPila.push(Producciones.ESCRITURA);
                        automataPila.push("FIN");
                        automataPila.push(Producciones.LEXEMA);
                        automataPila.push("ESCRIBIR");
                        break;
                    //Verificamos si viene una literal    
                    case "Literal":
                        //Agregamos a la pila una literal
                        automataPila.push("Literal");
                        break;
                    //Verificamos si viene un número decimal
                    case "Numero":
                        //Agregamos un número decimal a la pila
                        automataPila.push("Numero");
                        break;
                    //Verificamos si viene un identificador
                    case "id":
                        //Agregamos un número decimal a la pila
                        automataPila.push("id");
                        break;
                    //Verificamos si viene una estructura REPETIR H INICIAR E FIN R
                    case "REPETIR H INICIAR E FIN R":
                        //Asignamos una estructura repetir, un fin de produccion, una estructura escribir, un inicio de produccion, un desencadenimiento de produccion TERMINALH, y una estructura repetir
                        automataPila.push(Producciones.REPETIR);
                        automataPila.push("FIN");
                        automataPila.push(Producciones.ESCRITURA);
                        automataPila.push("INICIAR");
                        automataPila.push(Producciones.TERMINALH);
                        automataPila.push("REPETIR");
                        break;
                    //Verificamos si viene una estructura SI B ENTONCES E FIN C    
                    case "Si B ENTONCES E FIN C":
                        //Asignamos una estructura CONDICIONAL seguido de un FIN de produccion, una regla de producción ESCRITURA, seguido de un ENTONES, seguido del valor booleano de produccion seguido de SI
                        automataPila.push(Producciones.CONDICINAL);
                        automataPila.push("FIN");
                        automataPila.push(Producciones.ESCRITURA);
                        automataPila.push("ENTONCES");
                        automataPila.push(Producciones.VALORBOOLEANO);
                        automataPila.push("SI");
                        break;
                    //Verificamos si viene un valor verdadero en la producción
                    case "VERDADERO":
                        //Asignamos en la pila un valor verdadero
                        automataPila.push("VERDADERO");
                        break;
                    //Verificamos si viene un valor falso en la producción
                    case "FALSO":
                        //Asignamos en la pila un valor falso
                        automataPila.push("FALSO");
                        break;
                    //Verificamos si viene una regla de producción TX'
                    case "TX’":
                        //Metemos en la pila una regla de produccion NXP y NT
                        automataPila.push(Producciones.PRODUCCIONXP);
                        automataPila.push(Producciones.PRODUCCIONT);
                        break;
                    //Verificamos si viene una regla de producción +TX'
                    case "+TX’":
                        //Asignamos en la pila las reglas de prouccion desencadenadas NXP y NT
                        automataPila.push(Producciones.PRODUCCIONXP);
                        automataPila.push(Producciones.PRODUCCIONT);
                        automataPila.push("+");
                        break;
                    //Verificamos si viene una regla de producción FT'    
                    case "FT’":
                        //Asignamos en la pila las reglas de prouccion desencadenadas NTP y NF
                        automataPila.push(Producciones.PRODUCCIONTP);
                        automataPila.push(Producciones.PRODUCCIONF);
                        break;
                    //Verificamos si viene una regla de producción *FT'    
                    case "*FT’":
                        //Asignamos en la pila las reglas de prouccion desencadenadas *, NTP y NF
                        automataPila.push(Producciones.PRODUCCIONTP);
                        automataPila.push(Producciones.PRODUCCIONF);
                        automataPila.push("*");
                        break;
                    //Verificamos si vienen los teerminales (X)
                    case "(X)":
                        //Asignamos en la pila las reglas de prouccion desencadenadas ), ( y X
                        automataPila.push(")");
                        automataPila.push(Producciones.PRODUCCIONX);
                        automataPila.push("(");
                        break;
                   //Verificamos si viene una regla de producion ID= X FIN A     
                    case "id = X FIN A":
                        //Asignamos en la pila una producción de asignacion, seguido de un fin de lectura, seguido de X, seguido de un seguido igual, conseguido de un ID
                        automataPila.push(Producciones.ASIGNACION);
                        automataPila.push("FIN");
                        automataPila.push(Producciones.PRODUCCIONX);
                        automataPila.push("=");
                        automataPila.push("id");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Estructuras Semanticas no reconocidas por el automata de pila", "ERROR", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            } else {
               //Verificamos si esta terminal ya existe en la pila
                if (terminalExistenteEnPila(token)) {
                    //Verificamos la existencia dedicha produccion obteniendo el ultimo valor de dicha pila para poder vaciarla
                    while (comprobarExistenciaProduccion(this.automataPila.peek())) {
                        this.automataPila.pop();
                    }
                } else {
                    //Verificamos si el automata de pila contiene una estructura de producción NXP
                    if (this.automataPila.contains(Producciones.PRODUCCIONXP)) {
                        //Vaciamos la pila hasta poder obtener la producción producida por NXP
                        while (!Producciones.PRODUCCIONXP.equals(this.automataPila.peek())) {
                            this.automataPila.pop();
                        }
                    } else {
                        //Vaciamos la pila con la finalidad de poder obtener una produccion INICIAR
                        while (!Producciones.INICIAR.equals(this.automataPila.peek())) {
                            this.automataPila.pop();
                        }
                    }
                }
            }
        } 
    }

    /**
     * Este metodo me devuelve la pila establecida por dicha clase
     * @return
     */
    public Stack getPila() {
        return automataPila;
    }

    /**
     * Este metodo me permite cambiar la pila establecida por dicha clase
     * @param automataPila
     */
    public void setPila(Stack automataPila) {
        this.automataPila = automataPila;
    }
}
