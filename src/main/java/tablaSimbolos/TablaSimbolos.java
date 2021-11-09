/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablaSimbolos;
import enums.Producciones;
import java.util.ArrayList;
import token.Token;

/**
 * Esta clase me permite almacenar mi tabla de simbolos la cual nos ayuda a identificar el valor o el numero de erorres en un token al movilizarnos a travez de este
 * @author luis
 */
public class TablaSimbolos {
    private ArrayList<Simbolos> simbolos = new ArrayList<>();
    private final ArrayList<Token> tokens = new ArrayList<>();
    private boolean recolector = false;

    /**
     * Este metodo se encarga en recolectar las asignaciones obtenidas a travez de las reglas de producciones
     * @param produccion
     * @param token
     */
    public void recolectorAsignaciones(Producciones produccion, Token token) {
        //Verificamos si viene una produccion de asignacion y seguidamente viene un identificador
        if (produccion.equals(produccion.ASIGNACION) && token.getTipoToken().equalsIgnoreCase("id")) {
            recolector = true;
        }
        //Verificamos si el recolector tiene un valor positivo
        if (recolector) {
            //Verificamos si el lexema obtenida por este tiene un valor de FIN
            if (token.getTipoToken().equalsIgnoreCase("FIN")) {
                //Calculamos el valor de dicho Token y reiniciamos dicho valor
                calcularValorSimbolo();
                recolector = false;
            } else {
                //Capturamos el simbolo obtenido
                this.capturarSimbolo(token);
            }
        }
    }
    
    private ArrayList<String> lexemasdeTokens() {
        int index = 0;
        ArrayList<String> lexemas = new ArrayList<>();
        //Creamos un array de los lexemas que estan almacenados en mi array
        for (Token token : tokens) {
            //Verificamos si el token obtenido no es igual que un signo igual y distinto de un valor neutro como lo es cero
            if (!token.getLexema().equalsIgnoreCase("=") && index != 0) {
                //Agregamos los lexemas a un array
                lexemas.add(token.getLexema());
            }
            index++;
        }
        return lexemas;
    }

    /**
     * Este metodo se encarga de capturar el simbolo establecido establecido por un token
     * @param token
     */
    private void capturarSimbolo(Token token) {
        this.tokens.add(token);
    }

    /**
     * Este metodo se encarga de capturar el valor establecido por un simbolo
     */
    public void calcularValorSimbolo() {
        //Creamos un array de valores para poder almacenar el varlor que tendra cada token
        ArrayList<String> valores = this.lexemasdeTokens();
        valores = this.operacionesconParentesis(valores);    
        Simbolos simbolo = new Simbolos();
        //Asignamos los valores de este simbolo y lo asignamos a un array
        simbolo.setNombre(this.tokens.get(0).getLexema());
        simbolo.setValor(Integer.parseInt(valores.get(0)));
        simbolos.add(simbolo);
        this.tokens.clear();
    }

    private ArrayList<String> operacionesconParentesis(ArrayList<String> valores) {
        //Verificamos si viene un parantesis de aprera
        if (valores.contains("(")) {
            //Analizamos dicha estructura hasta que en el array siga manteniendo el signo de apertura
            while (valores.contains("(")) {
                //Establecemos contadores auxiliares
                int temI = 0;
                int temF = 0;
                //Establecemos un array auxiliar para apoyarnos en la realizacion de dicha operacion
                ArrayList<String> auxiliar = new ArrayList<>();
                //Recorremos el array de valores
                for (int i = 0; i < valores.size(); i++) {
                    //Verificamos si viene un parentesis de apertura
                    if (valores.get(i).equalsIgnoreCase("(")) {
                        //Almacenamos la posicion en la que esta dicho parentesis
                        temI = i;
                    }
                }
                //Volvemos a recorrer el array de valores a partir de la posicion del temporal del signo de apertura
                for (int i = temI; i < valores.size(); i++) {
                    //Verificamos la posicion en la que se encuentra la posicion de cierre del parentesis
                    if (valores.get(i).equalsIgnoreCase(")")) {
                        //Almacenamos la posicion de dicho parentesis
                        temF = i;
                        break;
                    }
                }
                //Guardamos en un array auxiliar la posicoin en la que esta almacenada el parentesis de apertura y de cierra
                for (int i = temI + 1; i < temF; i++) {
                    auxiliar.add(valores.get(i));
                }
                //Llamamos al metodo de las operaciones matemáticas
                auxiliar = operacionLinealMultiplicidad(auxiliar);
                auxiliar = operacionlinealSuma(auxiliar);
                valores.set(temI, auxiliar.get(0));
                valores = this.reasignar(valores, temI, temF);
            }
            //Asignamos los valores obtenidos por la multiplicidad y la suma
            valores = this.operacionLinealMultiplicidad(valores);
            valores = this.operacionlinealSuma(valores);
        } else {
            //Asignamos los valores obtenidos por la multiplicidad y la suma
            valores = this.operacionLinealMultiplicidad(valores);
            valores = this.operacionlinealSuma(valores);
        }
        return valores;
    }

    private ArrayList<String> reasignar(ArrayList<String> valores, int I, int F) {
        ArrayList<String> auxiliar = new ArrayList<>();
        //  Recorremos el array de valores que recorra hasta el inicio de la identificacion de parentesis
        for (int i = 0; i < I + 1; i++) {
            //Asignamos dichos valores al auxiliar
            auxiliar.add(valores.get(i));
        }
        //  Recorremos el array de valores que recorra hasta el final de la identificacion de parentesis
        for (int i = F + 1; i < valores.size(); i++) {
            auxiliar.add(valores.get(i));
        }
        return auxiliar;
    }

    private ArrayList<String> operacionLinealMultiplicidad(ArrayList<String> valores) {
        int producto = 0;
        //REcorremos el arreglo hasta que el valor contenga un signo de multiplicacion
        while (valores.contains("*")) {
            //Recorremos el array de valores obtenidos
            for (int i = 0; i < valores.size(); i++) {
                //Verificamos si viene un signo de multiplicacion
                if (valores.get(i).equalsIgnoreCase("*")) {
                    //Realizamos la multiplicacion a travez de la obtencion de valores de la posicion de atras y la posicion de adelante
                    producto = valorenSimbolo(valores.get(i + 1)) * valorenSimbolo(valores.get(i - 1));
                    valores.set(i, producto + "");
                    //Rmovemos dichas posiciones en el array
                    valores.remove(i + 1);
                    valores.remove(i - 1);
                    break;
                }
            }
        }
       return valores;
    }

    /**
     * Procedemos a realizar la suma que esta almacenada en los valores contenidos entre los parentesis
     * @return valores
     */
    private ArrayList<String> operacionlinealSuma(ArrayList<String> valores) {
        int suma = 0;
        //Recorremos el array dee valores
        for (String valor : valores) {
            //Realizamos un acumulado de suma de dichos valores para obtener el resultado final
            suma += this.valorenSimbolo(valor);
        }
        //Limpiamos el array
        valores.clear();
        valores.add(suma + "");
        return valores;
    }

    /**
     * Este metodo se encarga de devolver el valor en simbolo que posee dicho valor
     * @param token
     * @return
     */
    public int valorenSimbolo(String token) {
        int valor = 0;
        try {
            //Parseamos el valor a entero del resultado que posee el valor
            valor = Integer.parseInt(token);
        //Error númerico en el parseo de dichotoken
        } catch (NumberFormatException e) {
            //Recorremos el array de un simbolo
            for (Simbolos simbolo : simbolos) {
                //Vericamos si el nombre del simbolo es igual que el del token
                if (simbolo.getNombre().equals(token)) {
                    //Asignamos el valor que posee el simbolo
                    valor = simbolo.getValor();
                    break;
                }
            }
        }
        return valor;
    }
}
