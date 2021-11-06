/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package token;

/**
 *
 * @author luis
 */
public class TextoIDE {
    private String acccion;
    private String textoToken;

    public TextoIDE(String textoToken, String accion) {
        this.textoToken = textoToken;
        this.acccion=accion;
    }

    public String getAcccion() {
        return acccion;
    }

    public void setAcccion(String acccion) {
        this.acccion = acccion;
    }

    public String getTextoToken() {
        return textoToken;
    }

    public void setTextoToken(String textoToken) {
        this.textoToken = textoToken;
    }

}
