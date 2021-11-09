/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejadores;
import enums.PalabrasReservadas;
import java.util.ArrayList;
import javax.swing.JTextArea;
import token.Token;
import ventanas.VentanaPrincipal;
import token.ErroresSintacticos;
/**
 *
 * @author luis
 */
public class ManejadorAnalizadorLexico {
    
    ErroresSintacticos errores= new ErroresSintacticos();
    private String texto;
    private Token token= new Token();
    private final String SALTO = "\n";
    private final String TABULADOR = "\t";
    private final char SIGNOIGUAL = '=';
    private final char DIAGONAL = '/';
    private final char GUIONMEDIO = '-';
    private final char GUIONBAJO = '_';
    private final char COMILLASDOBLES = '"';
    private int fila = 1;
    private int columna = 0;
    private int posicion = 0;
    private int estadoActual;
    private String lexemasReporte = "";
    public static ArrayList<Token> tokenRecopilado = new ArrayList<>();
    private ArrayList<String> lexema = new ArrayList<>();
    private ArrayList<String> nombreToken = new ArrayList<>();
    private int estadoAnterior = 0;
    private int contador=0;
    
    // filas s0 --> 0, s1 -> 1, s2 -> 2, , s3 -> 3, s4 -> 4, s5 -> 5, s6 -> 6, s7 -> 7, s8 -> 8, s9 -> 9, s10 -> 10, s11 -> 11
        // \Letra --> 0
        // \Digito --> 1
        // \Diagonal "/" --> 2
        // \Signos de Puntuacio --> 3
        // \Signos de Agrupacion --> 4
        // \Operador Matemático --> 5
        // \Guion Medio --> 6
        // \Comillas --> 8
        // \Caracteres especiales --> 9
        // \Guion Bajo --> 10
        // \Signo Igual --> 11
        // error --> -1, -2, -3, -4
        // no pertenece a mi alfabeto -1
    int matrizTransicion[][] = new int[12][12];
    {
        matrizTransicion[0][0] = 3; //Traslado al estado 3 ya que viene una letra 
        matrizTransicion[0][1] = 7; //Traslado al estado 7 ya que viene un digito 
        matrizTransicion[0][2] = 1; //Traslado al estado 2 ya que viene una diagonal 
        matrizTransicion[0][3] = -1; //Error ya que dentro de un seguimiento de letras no deberia de haber un signo de puntuacion 
        matrizTransicion[0][4] = 10; //Traslado al estado 10 ya que viene una signo de agrupación 
        matrizTransicion[0][5] = 11; //Traslado al estado 11 ya que viene un signo igual 
        matrizTransicion[0][6] = 7; //Traslado al estado 7 ya que viene un guion medio 
        matrizTransicion[0][7] = 5; //Traslado al estado 5 ya que viene un guion medio 
        matrizTransicion[0][8] = 4; //Traslado al estado 4 ya que vienen comillas dobles
        matrizTransicion[0][9] = 3; //Traslado al estado 3 ya que viene un signo de puntuacion
        matrizTransicion[0][10] = 3; //Traslado al estado 3 ya que viene un guion bajo
        matrizTransicion[0][11] = 9; //Traslado al estado 9 ya que viene un signo igual
        
        matrizTransicion[1][0] = -1; //Error ya que dentro de un decimal no deberia de haber una letra
        matrizTransicion[1][1] = -1; //Error ya que dentro de un decimal de letras no deberia de haber una letra
        matrizTransicion[1][2] = 2; //Error ya que dentro de un decimal de letras no deberia de haber una letra
        matrizTransicion[1][3] = -1; //Error ya que dentro de un decimal de letras no deberia de haber un signo de puntuacion
        matrizTransicion[1][4] = -1; //Error ya que dentro de un decimal de letras no deberia de haber un signo de agrupacion
        matrizTransicion[1][5] = -1; //Error ya que dentro de un decimal de letras no deberia de haber un operador matematico
        matrizTransicion[1][6] = -1; //Error ya que dentro de un decimal de letras no deberia de haber un guion medio
        matrizTransicion[1][7] = -1; //Error ya que dentro de un decimal de letras no deberia de haber una letra
        matrizTransicion[1][8] = -1; //Error ya que dentro de un decimal de letras no deberian de venir comillas dobles
        matrizTransicion[1][9] = 8; // Traslado al estado 8 ya que viene un signo de puntuacion
        matrizTransicion[1][10] = -1; //Error ya que dentro de un decimal de letras no deberia de haber un guion bajo dentro del numero
        matrizTransicion[1][11] = -1; //Error ya que dentro de un decimal de letras no deberia de haber un signo igual
        
        matrizTransicion[2][0] = 2; //Permanecemos en el estado 2 ya que viene una letra 
        matrizTransicion[2][1] = 2; //Permanecemos en el estado 2 ya que viene un digito 
        matrizTransicion[2][2] = 2; ///Permanecemos en el estado 2 ya que viene una diagonal   
        matrizTransicion[2][3] = 2; //Permanecemos en el estado 2 ya que viene un signo de agrupacion
        matrizTransicion[2][4] = 2; //Permanecemos en el estado 2 ya que viene un signo de puntuacion
        matrizTransicion[2][5] = 2; ///Permanecemos en el estado 2 ya que viene un operador matematico
        matrizTransicion[2][6] = 2; //Permanecemos en el estado 2 ya que viene un guion medio
        matrizTransicion[2][7] = 2; //Permanecemos en el estado 2 ya que vienen letras
        matrizTransicion[2][8] = 2; //Permanecemos en el estado 2 ya que vienen comillas dobles
        matrizTransicion[2][9] = 2; //Permanecemos en el estado 2 ya que viene signos de puntuacion
        matrizTransicion[2][10] = 2; //Permanecemos en el estado 2 ya que viene un guion bajo
        matrizTransicion[2][11] = 2; //Permanecemos en el estado 2 ya que viene un signo igual
        
        matrizTransicion[3][0] = 3; //Permanecemos en el estado 3 ya que viene una letra
        matrizTransicion[3][1] = 3; //Permanecemos en el estado 3 ya que viene un digito
        matrizTransicion[3][2] = -1; //Error ya que dentro de un signo de puntuacion no deberia de haber una diagonal
        matrizTransicion[3][3] = -1; //Error ya que dentro de un signo de puntuacion no deberia de haber un signo de puntuacion
        matrizTransicion[3][4] = -4; //Error ya que dentro de un signo de puntuacion no deberia de haber una diagonal
        matrizTransicion[3][5] = -1; //Error ya que dentro de un signo de puntuacion no deberia de haber un operador matematico
        matrizTransicion[3][6] = 3; //Permanecemos en el estado 3 ya que viene un guion medio
        matrizTransicion[3][7] = 3; //Permanecemos en el estado 3 ya que viene un digito
        matrizTransicion[3][8] = -1; //Error ya que dentro de un signo de puntuacion no deberia de venir comillas dobles 
        matrizTransicion[3][9] = -1; //Error ya que dentro de un signo de puntuacion no deberia de haber mas signos de puntuacion
        matrizTransicion[3][10] = 3; //Permanecemos en el estado 3 ya que viene un guion bajo
        matrizTransicion[3][11] = -1; //Error ya que dentro de un signo de puntuacion no deberia de haber un signo igual
        
        matrizTransicion[4][0] = 4; //Permanecemos en el estado 4 ya que viene una letra
        matrizTransicion[4][1] = 4; //Permanecemos en el estado 4 ya que viene un digito
        matrizTransicion[4][2] = 4; //Permanecemos en el estado 4 ya que viene una diagonal
        matrizTransicion[4][3] = 4; //Permanecemos en el estado 4 ya que viene un signo de puntuacion
        matrizTransicion[4][4] = 4; //Permanecemos en el 4 ya que viene un signo de agrupacion
        matrizTransicion[4][5] = 4; //Permanecemos en el estado 4 ya que viene un operador matemático
        matrizTransicion[4][6] = 4; //Permanecemos en el estado 4 ya que viene un guion medio
        matrizTransicion[4][7] = 4; //Permanecemos en el estado 4 ya que viene una diagonal
        matrizTransicion[4][8] = 6; //Traslado al estaddo 4 ya que vienen comillas dobles
        matrizTransicion[4][9] = 4; //Permanecemos en el estado 4 ya que viene un signo de puntuacion
        matrizTransicion[4][10] = 4; //Permanecemos en el estado 4 ya que viene un guion bajo
        matrizTransicion[4][11] = 4; //Permanecemos en el estado 4 ya que viene un signo igual
        
        matrizTransicion[7][1] = 7; //Permanecemos en el estado ya que viene una diagonal
        matrizTransicion[7][4] = -4; //Traslado al estaddo -4 ya que viene un signo de agrupacion
    }
    

    public ManejadorAnalizadorLexico() {
        this.asignacionErroresSintacticos();
        this.tipoToken();
        this.analisisSintactico();
    }
    
    private void asignacionErroresSintacticos(){
         for (int i = 0; i < matrizTransicion.length; i++) {
            for (int j = 0; j < matrizTransicion[i].length; j++) {
                if (matrizTransicion[i][j] == 0) {
                    matrizTransicion[i][j] = -1;
                }
            }
            
        }
    }
    
    public int transicionesMatriz(int estadoActual, char caracter) {
        //Establacemos la posicion inicial como un error sintactico
        int posicion = -1;
        //Verificamos si obtenemos primeramente un caracter especial para analizar a donde nos desplazaremos
        if (token.getCaracteresEspeciales().contains(caracter)) {
            //Si estamos en un estado 3 procedemos a devolver un reinicio de la matriz
            if (estadoActual == 3) {
                posicion = 0;
            //En caso contrario devuelvo el valor de 9
            } else {
                posicion = 9;
            }
        //En caso que vega un digito devuelvo el valor de 1 
        } else if (Character.isDigit(caracter)) {
            posicion = 1;
        //En caso que vega una diagonal devuelvo el valor de 2    
        } else if (Character.compare(caracter, DIAGONAL) == 0) {
            posicion = 2;
        //En caso que vega un signo de agrupacion devuelvo el valor de 4     
        } else if (token.getSignosAgrupacion().contains(caracter)) {
            posicion = 4;
        //En caso que vega un operador matematico devuelvo el valor de 5 
        } else if (token.getOperadoresMatematicos().contains(caracter)) {
            posicion = 5;
        //En caso que vega un signo de puntuacion devuelvo el valor de 1 
        } else if (token.getSignosPuntuacion().contains(caracter)) {
            posicion = 3;
        //Verificamos si en dado caso obtenemos un espacio de cadenas o espacio de tabulador
        } else if (Character.isSpaceChar(caracter) || Character.compare(caracter, TABULADOR.charAt(0)) == 0) {
            //Verificamos el estado actual en el que se encuentra atraves del token para verificar hacia donde nos desplazamos
            switch (estadoActual) {
                case 4:
                    posicion = 4;
                    break;
                case 2:
                    posicion = 2;
                    break;
                default:
                    posicion = -2;
                    break;
            }
        //En caso que venga un salto de linea procedemos a trasladarnos a un estado -3 para marcar un fin de cadena     
        } else if (Character.compare(caracter, SALTO.charAt(0)) == 0) {
            posicion = -3;
        //En caso que venga un guion medio devuelvo el valor de 6
        } else if (Character.compare(caracter, GUIONMEDIO) == 0) {
            posicion = 6;
        //En caso que vengan comillas dobles devuelvo el valor de 8    
        } else if (Character.compare(caracter, COMILLASDOBLES) == 0) {
            posicion = 8;
        //En caso que venga una Letra decuelvo el valor de 0    
        } else if (Character.isLetter(caracter)) {
            posicion = 0;
        //En caso que venga un guion bajo devuelvo el valor de 10    
        } else if (Character.compare(caracter, GUIONBAJO) == 0) {
            posicion = 10;
        //En caso que venga un signo igual devuelvo el valor de 11
        } else if (Character.compare(caracter, SIGNOIGUAL) == 0) {
            posicion = 11;
        }
        return posicion;
    }
    
    private int getSiguienteEstado(int estadoActual, int caracter) {
        int resultado = -1;
        //Verificamos si la matriz en la que se encuentra el token es valida para poder trasladarnos a una de dicha posicion
        if (caracter >= 0 && caracter <= 11) {
            resultado= matrizTransicion[estadoActual][caracter];
        //Si obtenemos un valor de error devolvemos los valores de error que devuelve dicha matriz de transicion 
        } else if (caracter == -2) {
            resultado = -2;
        } else if (caracter == -3) {
            resultado = -3;
        } else if (caracter == -4) {
            resultado = -4;
        }
        return resultado;
    }

  
    
    private boolean getNextToken(int estadoActual, char caracter) {
        boolean seguiLeyendo = true;
        //Verificamos si viene un espacio vacio con la finalidad de verificar si hay fin de kon
        if (Character.isSpaceChar(caracter)) {
            //Verificamos si el estado en el que se encuentra esta en estos rangos
            seguiLeyendo = estadoActual == 4 || estadoActual == 2;
        //Verificamos si hay un salto de linea para poder marcar fin de token
        } else if (Character.compare(caracter, SALTO.charAt(0)) == 0) {
            seguiLeyendo = false;
        }
        //Verificamos si nos encontramos en un estado 10 o un estado -4 para poder finalizar la lectura
        if (estadoActual == 10 || estadoActual == -4) {
            seguiLeyendo = false;
        }
        return seguiLeyendo;
    }
    
    private boolean reiniciar(int estadoActual) {
        boolean reiniciar = false;
        //Verificamos si nos encontramos en un estado de error para poder evitar que el programa se corrompa
        if (estadoActual == -1 || estadoActual == -2 || estadoActual == -3) {
            reiniciar = true;
        }
        return reiniciar;
    }
    
   
    public void construccionReporteTokens(char caracter, int estado, int lenght) {
        
        //Verificamos la posicion en la que se encuentra el token para poder construir dicho token conforme a la posicion en la que este se encuentra
        switch (estado) {
            case -3:
                //Verificamos que no venga un token vacio y que este no sea un estado de no aceptacion
                if (!"".equals(lexemasReporte) && !this.esNoAceptcion(caracter, this.estadoAnterior)) {
                    //Guardamos la posicion en la que se encuentra el token
                    Token tokens = new Token(nombreToken(this.estadoAnterior, lexemasReporte), lexemasReporte, fila, columna);
                    tokenRecopilado.add(tokens);
                }
                //Aumentamos la posicion de filas y columnas de donde nos encontramos
                this.fila++;
                this.columna = 0;
                this.lexemasReporte = "";
                break;
            case -2:
                //Verificamos que no venga un token vacio y que este no sea un estado de no aceptacion
                if (!"".equals(lexemasReporte) && !this.esNoAceptcion(caracter, this.estadoAnterior)) {
                    //Guardamos la posicion en la que se encuentra el token
                    Token tokens = new Token(nombreToken(this.estadoAnterior, lexemasReporte), lexemasReporte, fila, columna);
                    tokenRecopilado.add(tokens);
                }
                //Aumentamos la posicion de la columna de donde nos encontramos para poder hacer la transicion de cambio de linia
                this.columna++;
                this.lexemasReporte = "";
                break;
            case -4:
                //Verificamos que no venga un token vacio y que este no sea un estado de no aceptacion
                if (!"".equals(lexemasReporte) && !this.esNoAceptcion(caracter, this.estadoAnterior)) {
                    //Guardamos la posicion en la que se encuentra el token
                    Token tokens = new Token(nombreToken(this.estadoAnterior, lexemasReporte), lexemasReporte, fila, columna);
                    tokenRecopilado.add(tokens);
                    //Aumentamos la posicion de la columna en la que nos encontramos para demsotrar la transicion en la que nos encontramos
                    this.columna++;
                    this.lexemasReporte = "";
                    //Guardamos la posicion en la que se encuentra el token
                    Token token1 = new Token(nombreToken(10, caracter + ""), caracter + "", fila, columna);
                    tokenRecopilado.add(token1);
                    //Aumentamos la posicion de la columna en la que nos encontramos para demsotrar la transicion en la que nos encontramos
                    this.columna++;
                    this.lexemasReporte = "";
                }
                break;
            case 10:
                // Verificamos que a continuacion no venta un estado de no aceptacion 
                if (!this.esNoAceptcion(caracter, this.estadoAnterior)) {
                    //Guardamos la posicion en la que se encuentra el token
                    Token tokens = new Token(nombreToken(10, caracter + ""), caracter + "", fila, columna);
                    tokenRecopilado.add(tokens);
                    //Aumentamos la posicion de la columna en la que nos encontramos para demsotrar la transicion en la que nos encontramos
                    this.columna++;
                    this.lexemasReporte = "";
                }
                break;
            default:
                //En caso contraio unicamente aumentamos la posicion de la columna donde nos encontramos
                this.columna++;
                this.lexemasReporte += "" + caracter;
                //Verificamos que nos encontemos en un estado de no aceptacion y que el largo de la cadena
                if (this.contador == lenght - 1 && !this.esNoAceptcion(caracter, estado)) {
                    //Guardamos la posicion en la que se encuentra el token
                    Token tokens = new Token(nombreToken(estado, lexemasReporte), lexemasReporte, fila, columna);
                    tokenRecopilado.add(tokens);
                }

                break;
        }
        this.contador++;
        this.estadoAnterior = estado;
    }

    private boolean esNoAceptcion(char caracter, int estado) {
        boolean esNoAceptacion = false;
        //Verificamos si nos encontramos en un estadp 4 o 1
        if (estado == 4 || estado == 1) {
            //Agregamos el error a la lista de tokens
            errores.recopilarErroesAnalizador(caracter, -1);
            //Devolvemos que nos encontramos en un estado de no aceptación
            esNoAceptacion = true;
        }
        return esNoAceptacion;
    }

    /**
     * Este metodo se encarga de asignar el nombre de cada token conforme a la posicion en la que este se encuentre
     * @param estado
     * @return
     */
    private String nombreToken(int estado, String lexemaEncontrado) {
        String token = "";
        switch (estado) {
            //Devolvemos que se trata de un comentario
            case 2:
                token = nombreToken.get(4);
                break;
            case 3:
                //Devolvemos que se trata de un identificador
                token = nombreToken.get(0);
                break;
            case 6:
                //Devolvemos que se trata de una literal
                token = nombreToken.get(5);
                break;
            case 7:
                //Devolvemos que se trata de un numero decimal
                token = nombreToken.get(1);
                break;
            case 8:
                //Devolvemos que se trata de un caracter especial
                token = nombreToken.get(6);
                break;
            case 9:
                //Devolvemos el lexema encontrado por la cadena
                token = lexemaEncontrado;
                break;
            case 10:
                //Devolvemos el lexema encontrado por la cadena
                token = lexemaEncontrado;
                break;
            case 11:
                //Devolvemos el lexema encontrado por la cadena
                token = lexemaEncontrado;
                break;
            default:
                //En caso contrario devolvemos que se trata de un error
                token = nombreToken.get(10);
                break;
        }

        return token;
    }

    /**
     * Este metodo se encarga de asignar el nombre correspondiente de cada Token conforme en la posicion en la que esta se encuentre
     */
    private void tipoToken() {
        this.nombreToken.add("id");
        this.nombreToken.add("NÚMERO DECIMAL");
        this.nombreToken.add("Reservada");
        this.nombreToken.add("Literal");
        this.nombreToken.add("COMENTARIO");
        this.nombreToken.add("LITERAL");
        this.nombreToken.add("SIGNO ESPECIAL");
        this.nombreToken.add("Igual");
        this.nombreToken.add("Agrupacion");
        this.nombreToken.add("Operador");
        this.nombreToken.add("Error");

    }

    public void pabrasReservadas() {
        //Recorremos la lista de tokens obtenidos
        for (Token token : tokenRecopilado) {
            //Verificamos la lista de tipos de tokens
            for (PalabrasReservadas value : PalabrasReservadas.values()) {
                //Verificamos si el nombre del lexema es igual
                if (token.getLexema().equals(value.name())) {
                    //Cambiamos el tipo de token que obtuvimos
                    token.setTipoToken(value.name());
                }
            }
        }
    }

    public String nombreParaListado(String tipoToken) {
        //Obtenemos el tipo de token, y le asignamos el tipo de token conforme al resultado que obtuvimos
        switch (tipoToken) {
            case "id":
                tipoToken = "IDENTIFICADOR";
                break;
            case "ESCRIBIR":
                tipoToken = "PALABRA RESERVADA";
                break;
            case "FIN":
                tipoToken = "PALABRA RESERVADA";
                break;
            case "REPETIR":
                tipoToken = "PALABRA RESERVADA";
                break;
            case "INICIAR":
                tipoToken = "PALABRA RESERVADA";
                break;
            case "SI":
                tipoToken = "PALABRA RESERVADA";
                break;
            case "VERDADERO":
                tipoToken = "PALABRA RESERVADA";
                break;
            case "FALSO":
                tipoToken = "PALABRA RESERVADA";
                break;
            case "ENTONCES":
                tipoToken = "PALABRA RESERVADA";
                break;
            case "=":
                tipoToken = "SIGNO IGUAL";
                break;
            case "+":
                tipoToken = "OPERADOR MATEMÁTICO";
                break;
            case "*":
                tipoToken = "OPERADOR MATEMÁTICO";
                break;
            case "(":
                tipoToken = "AGRUPACION MATEMÁTICO";
                break;
            case ")":
                tipoToken = "AGRUPACION MATEMÁTICO";
                break;
        }

        return tipoToken;
    }

    public void analisisSintactico() {
        this.lexema.clear();
        texto = VentanaPrincipal.ventana.getAreaTexto().getText();
        this.estadoActual = 0;
        char temporal;
        while (posicion < texto.length()) {
            temporal = texto.charAt(posicion);
            int estadoTemporal = getSiguienteEstado(estadoActual, this.transicionesMatriz(estadoActual, temporal));
            errores.recopilarErroesAnalizador(temporal, estadoTemporal);
            this.construccionReporteTokens(temporal, estadoTemporal, texto.length());
            if (estadoTemporal == 10) {
                estadoTemporal = 0;
            }
            this.estadoActual = estadoTemporal;
            if (!getNextToken(estadoActual, temporal) || reiniciar(estadoActual)) {
                estadoActual = 0;
            }
            posicion++;
        }
        this.pabrasReservadas();
        Token tokens = new Token("FINALIZAR", "FINALIZAR", 0, 0);
        tokenRecopilado.add(tokens);
        VentanaPrincipal.ventana.getAreaTexto().setText(VentanaPrincipal.ventana.getAreaTexto().getText());
    }
}
