/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package token;

/**
 * Esta clase en carga de manejar la acciones de hacer y rehacer tomando en cuenta la accion de escritura o elimanacion que se llega a realizar en el transcurso del programa
 * @author luis
 */
public class TextoIDE {
    private String acccion;
    private String textoToken;

    /**
     * Este constructor me permite poder construir un nuevo objeto TextoIDE con los parametros del texto del token y la accion que se realiza
     * @param textoToken
     * @param accion
     */
    public TextoIDE(String textoToken, String accion) {
        this.textoToken = textoToken;
        this.acccion=accion;
    }

    /**
     * Este metodo me devuelve la accion que se realiza en el area de texto
     * @return
     */
    public String getAcccion() {
        return acccion;
    }

    /** 
     * Este metodo me permite cambiar las acciones que se realizan en el araa de texto
     * @param acccion
     */
    public void setAcccion(String acccion) {
        this.acccion = acccion;
    }

    /**
     * Este metodo me devuelve el texto del area de tokens
     * @return
     */
    public String getTextoToken() {
        return textoToken;
    }

    /**
     * Este metodo se encarga de devolverme el texto que esta establecido en el area de tokens
     * @param textoToken
     */
    public void setTextoToken(String textoToken) {
        this.textoToken = textoToken;
    }

}
