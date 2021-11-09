/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejadores;
import enums.Producciones;
import java.util.ArrayList;
import javax.swing.JTextArea;
import producciones.AutomataPila;
import producciones.FuncionesEstructuras;
import producciones.Terminales;
import token.Token;

/**
 * Este metodo se encarga de manejar un analizador sintactico con la finalidad de poder devolver un archivo de salida en razon al lenguaje obtenido
 * @author luis
 */
public class ManejadoAnalizadorSintactico {
    private AutomataPila automataPila = new AutomataPila();
    private Producciones produccion;
    private final Terminales alfabeto = new Terminales();
    private Token token;
    private ArrayList<Token> tokens = new ArrayList<>();
    private FuncionesEstructuras funciones = new FuncionesEstructuras();
    private final ArrayList<Token> tokensError = new ArrayList<>();
    private String matrizProduccionesSintacticas[][] = new String[13][16];
    {
        matrizProduccionesSintacticas[0][0] = "ES";
        matrizProduccionesSintacticas[0][4] = "AS";
        matrizProduccionesSintacticas[0][5] = "RS";
        matrizProduccionesSintacticas[0][7] = "CS";
        matrizProduccionesSintacticas[0][15] = "Ɛ";
        
        matrizProduccionesSintacticas[1][0] = "ESCRIBIR L FIN E";
        matrizProduccionesSintacticas[1][1] = "Ɛ";
        matrizProduccionesSintacticas[1][4] = "Ɛ";
        matrizProduccionesSintacticas[1][5] = "Ɛ";
        matrizProduccionesSintacticas[1][7] = "Ɛ";
        matrizProduccionesSintacticas[1][15] = "Ɛ";
        
        matrizProduccionesSintacticas[2][2] = "Literal";
        matrizProduccionesSintacticas[2][3] = "Numero";
        matrizProduccionesSintacticas[2][4] = "id";
        
        matrizProduccionesSintacticas[3][0] = "Ɛ";
        matrizProduccionesSintacticas[3][4] = "Ɛ";
        matrizProduccionesSintacticas[3][5] = "REPETIR H INICIAR E FIN R";
        matrizProduccionesSintacticas[3][7] = "Ɛ";
        matrizProduccionesSintacticas[3][15] = "Ɛ";

        matrizProduccionesSintacticas[4][3] = "Numero";
        matrizProduccionesSintacticas[4][4] = "id";
        
        matrizProduccionesSintacticas[5][0] = "Ɛ";
        matrizProduccionesSintacticas[5][4] = "Ɛ";
        matrizProduccionesSintacticas[5][5] = "Ɛ";
        matrizProduccionesSintacticas[5][7] = "Si B ENTONCES E FIN C";
        matrizProduccionesSintacticas[5][15] = "Ɛ";
        
        matrizProduccionesSintacticas[6][9] = "VERDADERO";
        matrizProduccionesSintacticas[6][10] = "FALSO";
        
        matrizProduccionesSintacticas[7][3] = "TX’";
        matrizProduccionesSintacticas[7][4] = "TX’";
        matrizProduccionesSintacticas[7][13] = "TX’";
        
        matrizProduccionesSintacticas[8][1] = "Ɛ";
        matrizProduccionesSintacticas[8][11] = "+TX’";
        matrizProduccionesSintacticas[8][14] = "Ɛ";
        matrizProduccionesSintacticas[8][15] = "Ɛ";
        matrizProduccionesSintacticas[9][3] = "FT’";
        
        matrizProduccionesSintacticas[9][4] = "FT’";
        matrizProduccionesSintacticas[9][13] = "FT’";
        
        matrizProduccionesSintacticas[10][1] = "Ɛ";
        matrizProduccionesSintacticas[10][11] = "Ɛ";
        matrizProduccionesSintacticas[10][12] = "*FT’";
        matrizProduccionesSintacticas[10][14] = "Ɛ";
        matrizProduccionesSintacticas[10][15] = "Ɛ";
        
        matrizProduccionesSintacticas[11][3] = "Numero";
        matrizProduccionesSintacticas[11][4] = "id";
        matrizProduccionesSintacticas[11][13] = "(X)";
        
        matrizProduccionesSintacticas[12][0] = "Ɛ";
        matrizProduccionesSintacticas[12][4] = "id = X FIN A";
        matrizProduccionesSintacticas[12][5] = "Ɛ";
        matrizProduccionesSintacticas[12][6] = "Ɛ";
        matrizProduccionesSintacticas[12][15] = "Ɛ";
    }

    /**
     * Este metodo se encarga de limpiar los tokens obtenidos con la finalidad de omitir los tokens que contengan simbolos especiales y un comentario
     * @param tokens
     * @return
     */
    private ArrayList<Token> limpiarTokens(ArrayList<Token> tokens) {
        ArrayList<Token> tokenporAnallizar = new ArrayList<>();
        //Recorremos el array de tokens obtenidos
        for (Token token : tokens) {
            //Verificamos si no tenemos un tipo de token como un comentario o un simbolo especial
            if (!token.getTipoToken().equalsIgnoreCase("Comentario") && !token.getTipoToken().equalsIgnoreCase("Especial")) {
                //Analizamos el token obtenido
                tokenporAnallizar.add(token);
            }
        }
        return tokenporAnallizar;
    }

    /**
     * Este metodo me devuelve un arreglo de tokens obtenido por el analizador lexico
     * @return 
     */
    public ArrayList<Token> getTokens() {
        return tokens;
    }

    /**
     * Este metodo se encarga de cambiar los tokens que estan asignadas en el array de tokens
     * @param tokens
     */
    public void setTokens(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    /**
     * Este metodo se encarga de corroborar si estamos inmeros en un enum de las producciones plausibles que pueda tomar mi automata
     * @param object
     * @return
     */
    private boolean comprobarEnum(Object object) {
        boolean esEnum = false;
        Producciones[] produccion = Producciones.values();
        //Recooremos el array de producciones 
        for (Producciones producciones : produccion) {
            //Verificamos si la produccion ingresada es igual a una de las producciones almacenadas en el array
            esEnum = producciones.equals(object);
            if (esEnum) {
                break;
            }
        }
        return esEnum;
    }

    /**
     * Este metodo se encarga de capturar los errores sintacticos obtenidos al realizar dicho analisis
     * @param token
     * @param descripcion
     */
    public void capturaErrorSintactico(Token token, String descripcion){
        token.setDescripcion(descripcion);
        this.tokensError.add(token);
        
    }
    
    /**
     * Este metodo se encarga de enlistar los errores semanticos que fueron encontrados al momento de realizar un analisis sintactico
     * @param area
     */
    public void enlistarErrores(JTextArea area){
        area.setText("");
        //Recorremos el array en busca de posibles erros sintacticos que podamos encontrar
        for (Token token : tokensError) {
            area.append("Error sintactico en el lexema: "+ token.getLexema()+ " .El cual esta ubicado en la posicion: Fila "+ token.getFila() +" Columna: "+ token.getColumna()+"\n");
            area.append("Descripcion Error: "+ token.getDescripcion()+"\n");
        }
    }

    /**
     * Este metodo me ayuda verificar si en caso hay errores sintacticos al momento de hacer dicha evaluacion
     * @return
     */
    public boolean existenciaError() {
        return !this.tokensError.isEmpty();
    }

    /**
     * Este metodo me devuelve las distintas tipos de funciones de estructura que puedo emplear en el transcurso del programa
     * @return
     */
    public FuncionesEstructuras getFunciones() {
        return funciones;
    }
    
    /**
     *Este metodo me ayuda a hacer un analisis sintactico a partir de un array de tokens
     * @param Tokens
     */
    public void analisisSintactico(ArrayList<Token> tokens) {
        this.tokens = this.limpiarTokens(tokens);
        int index = 0;
        //Verificamos si el automata de pila se encuentra vacio y el largo del token
        while (!this.automataPila.getPila().empty() && index < this.tokens.size()) {
            //Verificamos si el token se encuentra nulo
            if (this.token == null) {
                //Asignamos un tokenes a travez del index de los token
                this.token = this.tokens.get(index);
                index++;
            }
            //Verificamos si el estado del automata de pila es valido
            while (comprobarEnum(this.automataPila.getPila().peek())) {
                //Asignamos la produccion a travez de las producciones de dicha pila
                this.produccion = (Producciones) this.automataPila.getPila().peek();
                //Asignamos el valor de la matriz
                String valorMatriz = matrizProduccionesSintacticas[this.alfabeto.getEstado(produccion)][this.alfabeto.getValorTerminal(this.token.getTipoToken())];
                //Verificamos si el valor de la matriz es igual nulo
                if (valorMatriz == null) {
                    String descriondeError = "El analizador sintactico esperaba un tipo detoken " + produccion.getEsperarProducciones();
                    capturaErrorSintactico(token, descriondeError);
                    token = null;
                    break;
                } else {
                    this.automataPila.llenarPila(valorMatriz, this.token.getTipoToken());
                }
            }
            //Comprobamos si la pila tiene un estado de ENUM valido y el valor del token no es nulo
            if (!comprobarEnum(this.automataPila.getPila().peek()) && this.token != null) {
                if (token.getTipoToken().equalsIgnoreCase((String) this.automataPila.getPila().peek())) {
                    this.funciones.recolectorFucionesSalida(produccion, token);
                    this.automataPila.getPila().pop();
                    this.token = null;
                } else {
                    String descriondeError = "El analizador sintactico esperaba un tipo detoken " + this.automataPila.getPila().peek();
                    capturaErrorSintactico(token, descriondeError);
                    this.token = null;
                }
            }
        }
    }
}
