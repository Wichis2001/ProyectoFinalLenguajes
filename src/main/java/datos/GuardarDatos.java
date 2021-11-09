/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Esta clase me permite guardar los datos realizados a mi archivo de entrada, con la finalidad de no perder los datos almacenados
 * @author luis
 */
public class GuardarDatos {
    FileOutputStream salida;
    /**
     * Este metodo me permite guardar los datos realizados a mi archivo de entrada, con la finalidad de no perder los datos almacenado a travez del parametro del texto ingresado
     * @param archivo
     * @param texto
     * @return 
     * @throws IOException
     */
    public String guardaraArchivo(File archivo, String texto) throws IOException{
        String mensaje=null;
        salida= new FileOutputStream(archivo);
        byte[] bytxt=texto.getBytes();
        salida.write(bytxt);
        mensaje="Archivo Guardado";
        return mensaje;
    }
    
    /**
     * Este metodo me permite poder guardar un archivo que ya ha sido creado con antelacion para poder mantener los datos que han sido guardados previamente
     * @param texto
     * @param archivo
     * @throws IOException
     */
    public void guardaraArchivoExistente(String texto, File archivo) throws IOException{
        try (FileWriter fw = new FileWriter(archivo)) {
            fw.write(texto);
        }
    }
}
