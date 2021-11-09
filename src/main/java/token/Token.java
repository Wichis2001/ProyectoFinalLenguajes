/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package token;

import java.util.ArrayList;

/**
 * Dicha clase se encarga de establecer los distintos tipos de Tokens que se manejaran al rededor de mi programa y seran empleados por el analizador lexico y el analisador sintactico
 * @author luis
 */
public class Token {

    private ArrayList<Character> signosAgrupacion;
    private ArrayList<Character> operadoresMatematicos;
    private ArrayList<Character> caracteresEspeciales;
    private ArrayList<Character> signosPuntuacion;
    private String lexema;
    private String tipoToken;
    private int fila;
    private int columna;
    
    /**
     * Dicho metodo me permite que al momento de crear un nuevo objeto de Token pueda poner hacer un llamado al metodo de agrupacion de Tokens
     */
    public Token() {
        this.agrupacionTokens();
    }

    /**
     * Este constuctor me permite poder crear un token a travez de la obtencia de su lexema, del tipo de token, y la posicion en la que este se encuentra
     * @param lexema
     * @param tipoToken
     * @param fila
     * @param columna
     */
    public Token(String lexema, String tipoToken, int fila, int columna) {
        this.lexema = lexema;
        this.tipoToken = tipoToken;
        this.fila = fila;
        this.columna = columna;
    }
    

    /**
     * Este metodo me permite llenar mis distinitos tipos de arraylist con la finalidad de que podamos utilizarlo para poder inferir los distintos tipos de caracteres que puede que nos vengan
     */
    private void agrupacionTokens() {
        tiposSignosAgrupacion();
        tiposOperadoresMatematicos();
        tiposSignosPuntuacion();
        tiposCaractereesEspeciales();
    }
    
    /**
     * Este metedo me permite llenar un array de caracteres especiales para saber que tipos de caracteres obtendre de dicha sentencia
     */
    public void tiposCaractereesEspeciales() {
        this.caracteresEspeciales = new ArrayList<>();
        this.caracteresEspeciales.add('t');
        this.caracteresEspeciales.add('f');
        this.caracteresEspeciales.add('n');
        this.caracteresEspeciales.add('r');    
    }
    
    /**
     * Este metodo me permite llenar mi array de tipos de asignacion con la finalidad de conocer los distintios tipos de signos de agrupacion que obrendre de dicha sentencia
     */
    private void tiposSignosAgrupacion() {
        signosAgrupacion = new ArrayList<>();
        this.signosAgrupacion.add(')');
        this.signosAgrupacion.add('(');       
    }

    /**
     * Este metodo me permite llenar mi array de tipos de puntuacion con la finalidad de conocer los distintios tipos de signos de puntuacion que obrendre de dicha sentencia
     */
    private void tiposSignosPuntuacion() {
        signosPuntuacion = new ArrayList<>();
        this.signosPuntuacion.add('.');
        this.signosPuntuacion.add(':');
        this.signosPuntuacion.add(';');
        this.signosPuntuacion.add(',');
        this.signosPuntuacion.add('<');
        this.signosPuntuacion.add('<');
        this.signosPuntuacion.add('‘');
        this.signosPuntuacion.add('\'');
        this.signosPuntuacion.add('>');
    }

    /**
     * Este metodo me permite llenar mi array de tipos de operadores matematicos con la finalidad de conocer los distintios tipos de signos de agrupacion que obrendre de dicha sentencia
     */
    private void tiposOperadoresMatematicos() {
        operadoresMatematicos = new ArrayList<>();
        this.operadoresMatematicos.add('*');
        this.operadoresMatematicos.add('%');
        this.operadoresMatematicos.add('+');
    }

    /**
     * Este metodo me devuelve el array que contiene mis signos de agrupacion
     * @return
     */
    public ArrayList<Character> getSignosAgrupacion() {
        return signosAgrupacion;
    }

    /**
     * Este metodo me permite cambiar los elementos que contiene mi array de signos de agrupación
     * @param signosAgrupacion
     */
    public void setSignosAgrupacion(ArrayList<Character> signosAgrupacion) {
        this.signosAgrupacion = signosAgrupacion;
    }

    /**
     * Este metodo me devuelve los elementos que contiene mi array con los operadores matemáticos
     * @return
     */
    public ArrayList<Character> getOperadoresMatematicos() {
        return operadoresMatematicos;
    }

    /**
     * Este metodo me permite cambiar el array que contiene mis operadors matemáticos
     * @param operadoresMatematicos
     */
    public void setOperadoresMatematicos(ArrayList<Character> operadoresMatematicos) {
        this.operadoresMatematicos = operadoresMatematicos;
    }

    /**
     * Este metodo me devuelve un array con los elemenots que contienen mis caracteres especiales
     * @return
     */
    public ArrayList<Character> getCaracteresEspeciales() {
        return caracteresEspeciales;
    }

    /**
     * Este metodo me permite cambiar la cantiddad de caracteres especiales que esta contenida en mi array
     * @param caracteresEspeciales
     */
    public void setCaracteresEspeciales(ArrayList<Character> caracteresEspeciales) {
        this.caracteresEspeciales = caracteresEspeciales;
    }

    /**
     * Este metodo me devuelve los disitintos tipos de signos de puntuacion contenidos en mi array
     * @return
     */
    public ArrayList<Character> getSignosPuntuacion() {
        return signosPuntuacion;
    }

    /**
     * Este metodo me permite cambiar los distintos tipos de signos de puntuacion que estan contenidos en mi array
     * @param signosPuntuacion
     */
    public void setSignosPuntuacion(ArrayList<Character> signosPuntuacion) {
        this.signosPuntuacion = signosPuntuacion;
    }

    /**
     * Este metodo me devuelve un lexema de un token
     * @return
     */
    public String getLexema() {
        return lexema;
    }

    /**
     * Este metodo me devuelve el tipo de token de un objeto
     * @return
     */
    public String getTipoToken() {
        return tipoToken;
    }

    /**
     * Este metodo me devuelve la fila en la que se encuentra un token
     * @return
     */
    public int getFila() {
        return fila;
    }

    /* 
     * Este metodo me devuelve la columna en la que se encuentra un token
     * @return
     */
   public int getColumna() {
        return columna;
    }

    /**
     * Este metodo me permite cambiar el tipo de token 
     * @param tipoToken
     */
    public void setTipoToken(String tipoToken) {
        this.tipoToken = tipoToken;
    }
}
