/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import ventanas.VentanaPrincipal;


/**
 * Esta clase me permite poder subir el texto que sera empleado a travez de mi analizador lexico.
 * @author luis
 */
public class CargaDatos {

    /**
     * Este metodo me permite establecer un archivo estatico como file el cual sera el valor a retornar cuando se haga una carga de datos
     */
    public static File archivoAProcesar;
       
    /**
     * Este constructor me permite hacer la carga de un archivo y poder aplicarla a la ventana deseada, en este caso a la ventana de carga de datos 
     * @param archivoAProcesar
     */
    public CargaDatos(File archivoAProcesar){
        this.archivoAProcesar = archivoAProcesar;
    }
    
    /**
     * Este metodo me permite leer el archivo de entrada ingresado por el usuairo y que este pueda ser extraido para su analisis
     */
    public void anilizarTexto(){
        try {
            //Llamamos al metodo leer archivo
            leerArchivo();
            //Archivo no encontrado
        } catch (FileNotFoundException e) {
            System.err.println(e);
            //Errores Generales
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.err.println(e);
            //Error en el indice ilegal que posee el arreglo 
        }
    }

    /**
     * Este metodo se encarga de la lectura del archivo de texto y la extraccion de datos para cada objeto respectivamente 
     */
    private void leerArchivo() throws FileNotFoundException, IOException, ArrayIndexOutOfBoundsException{
       //Limpiamos el texto de la ventana
        VentanaPrincipal.ventana.getAreaTexto().setText("");
        //Leemos el texto del archivo       
        BufferedReader lector = new BufferedReader(new FileReader(this.archivoAProcesar));
        //Usamos esta variable para la lectura de linea por linea
        String auxiliar = lector.readLine();
        String temporal="";
        //Usamos esta variable para determinar la poscion en la que esta ubicada la lnea
        while(auxiliar != null){
            //Con la linea leida separamos los campos
            temporal=temporal+auxiliar;
            //Asignamos los datos obtenidos en el Jtextarea
            if(temporal!=""){
               VentanaPrincipal.ventana.getAreaTexto().append(temporal);
               temporal="";
            }
            //Asignamos los saltos de linea identificados en nuestro texto de entrada
            auxiliar = lector.readLine();
            if(auxiliar!=null){
               VentanaPrincipal.ventana.getAreaTexto().append("\n");
               VentanaPrincipal.ventana.getAreaTexto().append("\n"); 
            }        
        }       
        //Establecemos que la carga fue exitosa y cerramos el lector
        JOptionPane.showMessageDialog(null, "Datos cargados con éxito.", "CARGA DE DATOS", JOptionPane.INFORMATION_MESSAGE);
        lector.close();
    }   
}
