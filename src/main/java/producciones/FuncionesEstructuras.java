/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producciones;
import enums.Producciones;
import java.util.ArrayList;
import tablaSimbolos.TablaSimbolos;
import token.Token;

/**
 *Esta clase me permite poder establecer los arvhivos de salida tomando como base las estructuras sintácticas que estan relaciondas
 * @author luis
 */
public class FuncionesEstructuras {
    
    private final TablaSimbolos tablaSimbolos = new TablaSimbolos();
    private boolean recolectando = false;
    private String tokenGuia;
    private String cadena;
    private String documentoSalida;
    private boolean error = false;
    private ArrayList<String> cadenas = new ArrayList<>();
    private int ciclo;
    private String iniciar;
    private String tokenSubGuia;
    private String tokenAccion;

    /**
     * Este metodo se encarga de recolectar las producciones obtenidos de los tokens tomando comoguia la tabla de simbolos obtenidas
     * @param tipoToken
     * @param token
     * @param tabla
     */
    public void recolectarProducciones(String tipoToken, Token token, TablaSimbolos tabla) {
        //Vericamos si el tipo de token no es un error
        if (!tipoToken.equals("ERROR")) {
            //Verificamos si el token de guia no tiene un valor previamente asignado
            if (tokenGuia == null) {
                //Verificamos si tenemos un tipo de Token Escribir
                if (tipoToken.equals("ESCRIBIR")) {
                    tokenGuia = tipoToken;
                //Verificamos si tenemos un tipo de Token Repetir   
                } else if (tipoToken.equals("REPETIR")) {
                    tokenGuia = tipoToken;
                //Verificamos si tenemos un tipo de Token SI que indica una condicional    
                } else if (tipoToken.equals("SI")) {
                    tokenGuia = tipoToken;
                }
            } else {
                //Verificamos si el token de guia tiene un valor de ESCRIBIR
                if (tokenGuia.equals("ESCRIBIR")) {
                    //Verificamos si tenemos un tipo de token como fin
                    if (tipoToken.equals("FIN")) {
                        //Agregamos el archivo al documento de salida al rededor de la cadena
                        documentoSalida += "\n" + cadena;
                        //Reiniciamos las variables
                        tokenGuia = null;
                        cadena = null;
                    } else {
                        //En caso contrario quitamos las comillas de la cadena guiandonos por el token y la tabla de simbolos
                        cadena = quitarComillas(token, tabla);
                    }
                //Verificamos si tenemos un token de guia del tipo REPETIR
                } else if (tokenGuia.equals("REPETIR")) {
                    //Verificamos si el el tipo de token es del tipo numerico o es un identificador
                    if (tipoToken.equals("Numero") || tipoToken.equals("id")) {
                        //Asignos al ciclo el valor obtenido por la tablade valores
                        ciclo = tabla.valorenSimbolo(token.getLexema());
                    }
                    //En caso que el tipo de Token venga con una sentancia Iniciar
                    if (tipoToken.equals("INICIAR")) {
                        //Asignamos el valor de iniciar de acuerdo al tipo de token
                        iniciar = "INICIAR";
                    }
                    //Verificamos si tenemos la sentencia iniciar y consecutivamente tenemos un token de escribir
                    if (iniciar!= null && tipoToken.equals("ESCRIBIR")) {
                        //Establecemos el token de accion como una sentencia escribir
                        tokenAccion = "ESCRIBIR";
                    }
                    //Verificamos si el tipo de token es una literal
                    if (tipoToken.equals("Literal")) {
                        //Asignamos en la cadena el valor obtenido por quitarComilas a travez del token y la tabla obtenida
                        cadenas.add(quitarComillas(token, tabla));
                    }
                    //Verificamos si tenemos un tipo de token FIN y el tipo de accion es diferente de un valor nulo
                    if (tipoToken.equals("FIN") && tokenAccion != null) {
                        //Establecemos el token de accion como nulo
                        tokenAccion = null;
                    // Verificamos si el tipo de token viene un FIN y el toke de accion tiene un valor nulo
                    } else if (tipoToken.equals("FIN") && tokenAccion == null) {
                        //imprimimos el ciclo obtenido
                        impirmirCiclo();
                        //Reiniciamos las variables
                        cadenas.clear();
                        cadena = null;
                        tokenGuia = null;
                        tokenAccion = null;
                        tokenGuia = null;
                    }
                //Verificamos si el token de guia contiene un valor de una condicional SI
                } else if (tokenGuia.equals("SI")) {
                    //Verificamos si el tipo de token contiene un valor verdadero
                    if (tipoToken.equals("VERDADERO")) {
                        //Asignamos dicho valor
                        tokenSubGuia = "VERDADERO";
                    }
                    //Verificamos si el tipo de token contiene un valor falso
                    if (tipoToken.equals("FALSO")) {
                        //Asignamos dicho valor
                        tokenSubGuia = "FALSO";
                    }
                    //Verificamos si el tipo de token contiene un valor Escribir
                    if (tipoToken.equals("ESCRIBIR")) {
                        //Establecemos dicho valor al token de accion
                        tokenAccion = "ESCRIBIR";
                    }
                    //Verificamos si el tipo de token contiene un valor númerico o un literal
                    if (tipoToken.equals("Numero") || tipoToken.equals("Literal")) {
                        //Quitamos las comillas de dichas cadenas tomando en cuenta el token y la tabla de simbolos
                        cadena = quitarComillas(token, tabla);
                    }
                    //Verificamos si el tipo de token contiene un valor FIN y el token de accion no contiene valores nulos
                    if (tipoToken.equals("FIN") && tokenAccion != null) {
                        //Asignamos el token accion como nulo
                        tokenAccion = null;
                    //Verificamos si el tipo de token contiene un valor FIN y el token de accion tiene un valor nulo    
                    } else if (tipoToken.equals("FIN") && tokenAccion == null) {
                        //Verificamos si el token de subguia tiene un valor verdadero
                        if (tokenSubGuia.equals("VERDADERO")) {
                            documentoSalida += "\n" + cadena;
                        } 
                        //Reiniciamos las vaiables
                        cadena = null;
                        tokenGuia = null;
                        tokenAccion = null;
                        tokenSubGuia = null;
                    }
                }
            }
        } else {
            //En caso contrario determinamos que si tenemos un error
            error = true;
        }
    }

    /**
     * Este metodo se encarga de imprimir el ciclo obtenido de la recoleccion de producciones
     */
    public void impirmirCiclo() {
        //Recooremos el ciclo
        for (int i = 0; i < ciclo; i++) {
            //Recorremos el array de cadenas
            for (int j = 0; j < cadenas.size(); j++) {
                //Agregamos los elemenos al documento de salida
                documentoSalida += "\n" + cadenas.get(j);
            }
        }
    }
    
    /**
     * Este metodo se encarga de quitar las comillas que estan escritas dentro de las estructuras sintacticas definidas
     * @param token
     * @param tabla
     * @return
     */
    public String quitarComillas(Token token, TablaSimbolos tabla) {
        String salida = "";
        //Obtenemos el valor del tipo de token el cual estamos analizado
        switch (token.getTipoToken()) {
            //Verificamos si tenemos un identifcador
            case "id":
                //Asignamos a la salida el valor del simbolo obtenido por la tabla
                salida += tabla.valorenSimbolo(token.getLexema());
                break;
            //Verificamos si tenemos un Literal    
            case "Literal":
                //Asignamos a la salida el valor del simbolo obtenido por la tabla
                salida += token.getLexema().replace("\"", "");
                break;
            default:
                //En caso contrario asignamos en la salida del tipo de token
                salida += token.getLexema();
                break;
        }

        return salida;
    }

    /**
     * Este metodo me devuelve el metodo que fue redactado por el sistema
     * @return
     */
    public String getDocumento() {
        return documentoSalida;
    }
    
    /**
     * Este metodo se encarga de recoletar las estructuras sintacticas de la terminal ESCRIBIR 
     * @param token
     * @param produccion
     * @param tabla
     * @param repiticiones
     */
    public void recolectarEscribir(Token token, Producciones produccion, TablaSimbolos tabla, Integer repiticiones){
        //Verificamos si ambas producciones tienen una asignatura de ESCRIBIR
        if (produccion.equals(Producciones.ESCRITURA) && token.getTipoToken().equalsIgnoreCase("ESCRIBIR")) {
            recolectando = true;
        }
    }   
    
    /**
     * Este metodo me devuelve la variable si se estan recolectando estructuras sintacticas
     * @return
     */
    public boolean isRecolectando() {
        return recolectando;
    }

    /**
     * Este metodo se encarga de cambiar el valor boolean si estan recolectan estructuras sintacticas
     * @param recolectando
     */
    public void setRecolectando(boolean recolectando) {
        this.recolectando = recolectando;
    }
    
    /**
     * Este metdo se encarga de recoletar las funciones de salidas obtenidas por las estructuras sintacticas
     * @param produccion
     * @param token
     */
    public void recolectorFucionesSalida(Producciones produccion, Token token) {
        tablaSimbolos.recolectorAsignaciones(produccion, token);
        this.recolectarProducciones(token.getTipoToken(), token, tablaSimbolos);
        
    }

    /**
     * Este metodo me devuelve la tabla de simbolos que estoyempleando
     * @return
     */
    public TablaSimbolos getTabla() {
        return tablaSimbolos;
    }
}
