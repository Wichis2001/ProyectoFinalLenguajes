/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;
import datos.CargaDatos;
import datos.GuardarDatos;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import token.NumeroLinea;
import token.TextoIDE;


/**
 * Ventana Principal que se encargara de desplazarnos a lo largo del programa, la cual sera la primera pestaña al desplegarse al momento de iniciar el programa
 * @author luis
 */
public class VentanaIDE extends javax.swing.JFrame {
    private String archivoALeer;
    ConcurrentHashMap<Integer, TextoIDE> estadoPrevio;
    ConcurrentHashMap<Integer, TextoIDE> estadoActual;
    public String texto="";
    public static File archivo;
    boolean programaGuardado=true;
    boolean programanoenCero=true;
    GuardarDatos guardar = new GuardarDatos();
    /**
     * Creates new form NewJFrame
     */
    public VentanaIDE() {
        initComponents();
        this.setLocationRelativeTo(null);
        NumeroLinea numeroLinea=new NumeroLinea(textoAnalizar);
        //Asignamos un numero de linea al Schroll panel
        jScrollPane1.setRowHeaderView(numeroLinea);
        // Inicializamos los HashMaps
        estadoPrevio = new ConcurrentHashMap<>();
        estadoActual = new ConcurrentHashMap<>();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        salir = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textoAnalizar = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        posicionText = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        copiarText = new javax.swing.JMenuItem();
        pegarTexto = new javax.swing.JMenuItem();
        deshacer = new javax.swing.JMenuItem();
        rehace = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Purisa", 3, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ANALIZADOR LÉXICO SINTÁCTICO");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1040, 70));

        salir.setBackground(new java.awt.Color(255, 153, 51));
        salir.setFont(new java.awt.Font("Ubuntu", 3, 24)); // NOI18N
        salir.setForeground(new java.awt.Color(0, 0, 0));
        salir.setText("SALIR");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        salir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                salirKeyPressed(evt);
            }
        });
        getContentPane().add(salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 680, 250, 100));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pngwing.com (1).png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, -1, -1));

        jScrollPane1.setForeground(new java.awt.Color(255, 153, 51));

        textoAnalizar.setBackground(new java.awt.Color(255, 153, 0));
        textoAnalizar.setColumns(20);
        textoAnalizar.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        textoAnalizar.setRows(5);
        textoAnalizar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                textoAnalizarCaretUpdate(evt);
            }
        });
        textoAnalizar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                textoAnalizarPropertyChange(evt);
            }
        });
        textoAnalizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoAnalizarKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoAnalizarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoAnalizarKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(textoAnalizar);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 1030, 490));

        jTextArea2.setBackground(new java.awt.Color(255, 153, 51));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        jTextArea2.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 800, 1010, 190));

        posicionText.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        posicionText.setForeground(new java.awt.Color(0, 0, 0));
        posicionText.setText("Posicion:");
        getContentPane().add(posicionText, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1000, 710, -1));

        jLabel4.setFont(new java.awt.Font("Ubuntu", 3, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Lista de Errores:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 770, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/24186-naranja.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 1020));

        jMenuBar1.setBackground(new java.awt.Color(255, 153, 51));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pngegg (2).png"))); // NOI18N
        jMenu1.setText("Nuevo");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/carpeta (1).png"))); // NOI18N
        jMenu2.setText("Abrir");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Guardar");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pngwing.com (3).png"))); // NOI18N
        jMenuItem2.setText("Guardar Archivo");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/boton-guardar-archivo.png"))); // NOI18N
        jMenuItem3.setText("Guardar Como");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        jMenu5.setText("Acciones");

        copiarText.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pngegg (1).png"))); // NOI18N
        copiarText.setText("Copiar");
        copiarText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                copiarTextMouseClicked(evt);
            }
        });
        copiarText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copiarTextActionPerformed(evt);
            }
        });
        jMenu5.add(copiarText);

        pegarTexto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/boton-de-pegar-del-portapapeles.png"))); // NOI18N
        pegarTexto.setText("Pegar");
        pegarTexto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pegarTextoMouseClicked(evt);
            }
        });
        pegarTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pegarTextoActionPerformed(evt);
            }
        });
        jMenu5.add(pegarTexto);

        deshacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pngwing.com (2).png"))); // NOI18N
        deshacer.setText("Deshacer");
        deshacer.setEnabled(false);
        deshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deshacerActionPerformed(evt);
            }
        });
        jMenu5.add(deshacer);

        rehace.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rehacer-simbolo-de-flecha.png"))); // NOI18N
        rehace.setText("Rehacer");
        rehace.setEnabled(false);
        rehace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rehaceActionPerformed(evt);
            }
        });
        jMenu5.add(rehace);

        jMenuBar1.add(jMenu5);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/5a381257a07d91.7907713515136241516574 (1).png"))); // NOI18N
        jMenu4.setText("Acerca de");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salirKeyPressed
        
    }//GEN-LAST:event_salirKeyPressed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        int response = JOptionPane.showConfirmDialog(this,"¿Quieres salir del Programa?", "SALIR",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (response==JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(this, "Saliendo...");
            //Salimos del programa
            System.exit(0);
        }
    }//GEN-LAST:event_salirActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        int response2 = JOptionPane.showConfirmDialog(this,"¿Quieres generar una nueva ventana de IDE?", "NUEVO",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        boolean verificar=textoAnalizar.getText().isEmpty();
        if (response2==JOptionPane.YES_OPTION){
            if(programaGuardado==false&&(verificar==false)){
                if(programanoenCero==true){
                    int response = JOptionPane.showConfirmDialog(this,"¿Quieres Guardar los Cambios realizados en el archivo?", "GUARDAR",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if (response==JOptionPane.YES_OPTION){
                        JFileChooser seleccionar = new JFileChooser();
                        if(seleccionar.showDialog(null, "Guardar")==JFileChooser.APPROVE_OPTION){
                            archivo= seleccionar.getSelectedFile();
                            try {
                                String texto= textoAnalizar.getText();
                                String mensaje= guardar.guardaraArchivo(archivo, texto);
                                if(mensaje!=null){
                                    JOptionPane.showMessageDialog(null, mensaje);
                                    deshacer.setEnabled(false);
                                    rehace.setEnabled(false);
                                    estadoActual.clear();
                                    estadoPrevio.clear();
                                    programanoenCero=true;
                                    programaGuardado=true;
                                    textoAnalizar.setText("");
                                    JOptionPane.showMessageDialog(this, "Archivo Nuevo Generado Correctamente", "NUEVO ARCHIVO", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Archivo Incompatible");
                                }

                            } catch (IOException ex) {
                                Logger.getLogger(VentanaIDE.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    } else {
                        deshacer.setEnabled(false);
                        rehace.setEnabled(false);
                        estadoActual.clear();
                        estadoPrevio.clear();
                        programanoenCero=true;
                        programaGuardado=true;
                        textoAnalizar.setText("");
                        JOptionPane.showMessageDialog(this, "Archivo Nuevo Generado Correctamente", "NUEVO ARCHIVO", JOptionPane.INFORMATION_MESSAGE);
                    }         
                } else {
                    int response = JOptionPane.showConfirmDialog(this,"¿Quieres Guardar los Cambios realizados en el archivo?", "GUARDAR",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if (response==JOptionPane.YES_OPTION){
                    try {
                            //Guardamos el archivo del texto de entrada
                            guardar.guardaraArchivoExistente(textoAnalizar.getText(), archivo);
                        } catch (IOException ex) {
                            Logger.getLogger(VentanaIDE.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(this, "Cambios guardados exitosamente :)");
                        deshacer.setEnabled(false);
                        rehace.setEnabled(false);
                        estadoActual.clear();
                        estadoPrevio.clear();
                        programanoenCero=true;
                        programaGuardado=true;
                        textoAnalizar.setText("");
                        JOptionPane.showMessageDialog(this, "Archivo Nuevo Generado Correctamente", "NUEVO ARCHIVO", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        deshacer.setEnabled(false);
                        rehace.setEnabled(false);
                        estadoActual.clear();
                        estadoPrevio.clear();
                        programanoenCero=true;
                        programaGuardado=true;
                        textoAnalizar.setText("");
                    }

                }
            } else{
                    JOptionPane.showMessageDialog(this, "Archivo Nuevo Generado Correctamente", "NUEVO ARCHIVO", JOptionPane.INFORMATION_MESSAGE);
                    deshacer.setEnabled(false);
                    rehace.setEnabled(false);
                    estadoActual.clear();
                    estadoPrevio.clear();
                    programanoenCero=true;
                    programaGuardado=true;
                    textoAnalizar.setText("");
            }    
        }
    }//GEN-LAST:event_jMenu1MouseClicked

    private void copiarTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copiarTextActionPerformed
        textoAnalizar.copy();
    }//GEN-LAST:event_copiarTextActionPerformed

    private void copiarTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_copiarTextMouseClicked

    }//GEN-LAST:event_copiarTextMouseClicked

    private void pegarTextoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pegarTextoMouseClicked

    }//GEN-LAST:event_pegarTextoMouseClicked

    private void pegarTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pegarTextoActionPerformed
        textoAnalizar.paste();
        // Agregamos el registro al Hashmap de previus states
        TextoIDE texo = new TextoIDE(textoAnalizar.getText(), "agregar");
        agregarEstadoPrevio((getEstadoPrevio().size() + 1), texo);
        // Limpiamos el hashmap de eventos posteriores
        limpiarEstadoActual();
    }//GEN-LAST:event_pegarTextoActionPerformed

    private void textoAnalizarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_textoAnalizarPropertyChange
        
    }//GEN-LAST:event_textoAnalizarPropertyChange

    private void textoAnalizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoAnalizarKeyPressed

        
      
    }//GEN-LAST:event_textoAnalizarKeyPressed

    private void rehaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rehaceActionPerformed
        System.out.println("---------REDO----------");
        
        int c = 1;
        for (TextoIDE r:getEstadoActual()) {
            System.out.println(c + " - " + r.getAcccion());
            c++;
        }
        
        if (getEstadoActual().isEmpty())
            return;
        
        TextoIDE lastReg = estadoActual.get(getEstadoActual().size());
        
        if (lastReg == null)
            return;
        
        TextoIDE oldReg;
        switch(lastReg.getAcccion()) {
            case "agregar":
                
                // Enviamos el evento de inserción al UNDO
                oldReg = new TextoIDE(lastReg.getTextoToken(), "agregar");
                agregarEstadoPrevio((getEstadoPrevio().size()+1), oldReg);
                
                // Eliminamos el evento anterior del REDO
                removeNextStates(getEstadoActual().size());
                break;
            case "eliminar":

                // Enviamos el evento de delete al UNDO
                oldReg = new TextoIDE(lastReg.getTextoToken(), "eliminar");
                agregarEstadoPrevio((getEstadoPrevio().size()+1), oldReg);
                
                
                // Eliminamos el evento anterior del REDO
                removeNextStates(getEstadoActual().size());
                break;
            default:
                break;
        }
        
        System.out.println("---------REDO----------");
        
        c = 1;
        for (TextoIDE r:getEstadoActual()) {
            System.out.println(c + " - " + r.getAcccion());
            c++;
        }
        
        // Refrescamos el contenido de la tabla
        textoAnalizar.setText("");
        textoAnalizar.setText(estadoPrevio.get(getEstadoPrevio().size()).getTextoToken());
    }//GEN-LAST:event_rehaceActionPerformed

    private void deshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deshacerActionPerformed
        System.out.println("---------UNDO----------");
        
        int c = 1;
        for (TextoIDE r:getEstadoPrevio()) {
            System.out.println(c + " - " + r.getAcccion());
            c++;
        }
        
        if (getEstadoPrevio().isEmpty())
            return;
        
        TextoIDE lastReg = estadoPrevio.get(getEstadoPrevio().size());
        
        if (lastReg == null)
            return;
        
        TextoIDE oldReg;
        switch(lastReg.getAcccion()) {
            case "agregar":

                // Enviamos el evento de inserción al REDO
                oldReg = new TextoIDE(lastReg.getTextoToken(), "agregar");
                agregarEstadoActual((getEstadoActual().size()+1), oldReg);
                
                // Eliminamos el evento anterior del UNDO
                removePreviusStates(getEstadoPrevio().size());
                break;
            case "eliminar":
                // Enviamos el evento delete al REDO
                oldReg = new TextoIDE(lastReg.getTextoToken(), "eliminar");
                agregarEstadoActual((getEstadoActual().size()+1), oldReg);
                
                // Eliminamos el evento anterior del UNDO
                removePreviusStates(getEstadoPrevio().size());
                break;
            default:
                break;
        }
        
        System.out.println("---------UNDO----------");
        
        c = 1;
        for (TextoIDE r:getEstadoPrevio()) {
            System.out.println(c + " - " + r.getAcccion());
            c++;
        }
        
        // Refrescamos el contenido de la tabla
        textoAnalizar.setText("");
        textoAnalizar.setText(estadoPrevio.get(estadoPrevio.size()).getTextoToken());
    }//GEN-LAST:event_deshacerActionPerformed

    private void textoAnalizarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoAnalizarKeyReleased
        programaGuardado=false;
        if(textoAnalizar.getText()!=""){
            //Analizamos si presionamos la tecla de regreso
            if (evt.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                // Agregamos el registro al Hashmap de previus states
                TextoIDE texo2 = new TextoIDE(textoAnalizar.getText(), "eliminar");
                agregarEstadoPrevio((getEstadoPrevio().size()+1), texo2);
                // Limpiamos el hashmap de eventos posteriores
                limpiarEstadoActual();
            } else {
                // Agregamos el registro al Hashmap de previus states
                TextoIDE texo = new TextoIDE(textoAnalizar.getText(), "agregar");
                agregarEstadoPrevio((getEstadoPrevio().size()+1), texo);
                // Limpiamos el hashmap de eventos posteriores
                limpiarEstadoActual();
            }
        } else {
        }
    }//GEN-LAST:event_textoAnalizarKeyReleased

    private void textoAnalizarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoAnalizarKeyTyped
        
    }//GEN-LAST:event_textoAnalizarKeyTyped

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if(programanoenCero==true){
            int response = JOptionPane.showConfirmDialog(this,"¿Quieres Guardar los Cambios realizados en el archivo?", "GUARDAR",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (response==JOptionPane.YES_OPTION){
                JFileChooser seleccionar = new JFileChooser();
                if(seleccionar.showDialog(null, "Guardar")==JFileChooser.APPROVE_OPTION){
                    archivo= seleccionar.getSelectedFile();
                    try {
                        String texto= textoAnalizar.getText();
                        String mensaje= guardar.guardaraArchivo(archivo, texto);
                        if(mensaje!=null){
                            JOptionPane.showMessageDialog(null, mensaje);
                            deshacer.setEnabled(false);
                            rehace.setEnabled(false);
                            estadoActual.clear();
                            estadoPrevio.clear();
                            programanoenCero=false;
                            programaGuardado=true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Archivo Incompatible");
                        }
             
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaIDE.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }          
        } else {
            int response = JOptionPane.showConfirmDialog(this,"¿Quieres Guardar los Cambios realizados en el archivo?", "GUARDAR",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (response==JOptionPane.YES_OPTION){
            try {
                    //Guardamos el archivo del texto de entrada
                    guardar.guardaraArchivoExistente(textoAnalizar.getText(), archivo);
                } catch (IOException ex) {
                    Logger.getLogger(VentanaIDE.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(this, "Cambios guardados exitosamente :)");
                deshacer.setEnabled(false);
                rehace.setEnabled(false);
                estadoActual.clear();
                estadoPrevio.clear();
                programanoenCero=false;
                programaGuardado=true;
            }
            
        }
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        int response = JOptionPane.showConfirmDialog(this,"¿Quieres Guardar los Cambios realizados en el archivo?", "GUARDAR COMO",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (response==JOptionPane.YES_OPTION){
            JFileChooser seleccionar = new JFileChooser();
            if(seleccionar.showDialog(null, "Guardar")==JFileChooser.APPROVE_OPTION){
                archivo= seleccionar.getSelectedFile();
                try {
                    String texto= textoAnalizar.getText();
                    String mensaje= guardar.guardaraArchivo(archivo, texto);
                    if(mensaje!=null){
                        JOptionPane.showMessageDialog(null, mensaje);
                        deshacer.setEnabled(false);
                        rehace.setEnabled(false);
                        estadoActual.clear();
                        estadoPrevio.clear();
                        programanoenCero=false;
                        programaGuardado=true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Archivo Incompatible");
                    }

                } catch (IOException ex) {
                    Logger.getLogger(VentanaIDE.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }          
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        int response2 = JOptionPane.showConfirmDialog(this,"¿Quieres Abrir un archivo de texto nuevo?", "ABRIR",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        boolean verificar=textoAnalizar.getText().isEmpty();
        if (response2==JOptionPane.YES_OPTION){
            if(programaGuardado==false&&(verificar==false)){
                if(programanoenCero==true){
                    int response = JOptionPane.showConfirmDialog(this,"¿Quieres Guardar los Cambios realizados en el archivo?", "GUARDAR",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if (response==JOptionPane.YES_OPTION){
                        JFileChooser seleccionar = new JFileChooser();
                        if(seleccionar.showDialog(null, "Guardar")==JFileChooser.APPROVE_OPTION){
                            archivo= seleccionar.getSelectedFile();
                            try {
                                String texto= textoAnalizar.getText();
                                String mensaje= guardar.guardaraArchivo(archivo, texto);
                                if(mensaje!=null){
                                    JOptionPane.showMessageDialog(null, mensaje);
                                    deshacer.setEnabled(false);
                                    rehace.setEnabled(false);
                                    estadoActual.clear();
                                    estadoPrevio.clear();
                                    programanoenCero=false;
                                    programaGuardado=true;
                                    textoAnalizar.setText("");
                                    this.cargarArchivo();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Archivo Incompatible");
                                }

                            } catch (IOException ex) {
                                Logger.getLogger(VentanaIDE.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    } else {
                        deshacer.setEnabled(false);
                        rehace.setEnabled(false);
                        estadoActual.clear();
                        estadoPrevio.clear();
                        programanoenCero=false;
                        programaGuardado=true;
                        textoAnalizar.setText("");
                        this.cargarArchivo();
                    }         
                } else {
                    int response = JOptionPane.showConfirmDialog(this,"¿Quieres Guardar los Cambios realizados en el archivo?", "GUARDAR",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if (response==JOptionPane.YES_OPTION){
                    try {
                            //Guardamos el archivo del texto de entrada
                            guardar.guardaraArchivoExistente(textoAnalizar.getText(), archivo);
                        } catch (IOException ex) {
                            Logger.getLogger(VentanaIDE.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(this, "Cambios guardados exitosamente :)");
                        deshacer.setEnabled(false);
                        rehace.setEnabled(false);
                        estadoActual.clear();
                        estadoPrevio.clear();
                        programanoenCero=false;
                        programaGuardado=true;
                        textoAnalizar.setText("");
                        this.cargarArchivo();
                        
                    } else {
                        deshacer.setEnabled(false);
                        rehace.setEnabled(false);
                        estadoActual.clear();
                        estadoPrevio.clear();
                        programanoenCero=false;
                        programaGuardado=true;
                        textoAnalizar.setText("");
                        this.cargarArchivo();
                    }

                }
            } else{
                    deshacer.setEnabled(false);
                    rehace.setEnabled(false);
                    estadoActual.clear();
                    estadoPrevio.clear();
                    programanoenCero=false;
                    programaGuardado=true;
                    textoAnalizar.setText("");
                    this.cargarArchivo();
            }    
        }

    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        this.setVisible(false);
        VentanaInformativa ventana= new VentanaInformativa();
        ventana.setVisible(true);
    }//GEN-LAST:event_jMenu4MouseClicked

    private void textoAnalizarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_textoAnalizarCaretUpdate
        textoAnalizar = (JTextArea)evt.getSource();
        int linea = 1;
        int columna = 1;
        try {
            int caretpos = textoAnalizar.getCaretPosition();
            linea= textoAnalizar.getLineOfOffset(caretpos);
            columna = caretpos - textoAnalizar.getLineStartOffset(linea);

            // Ya que las líneas las cuenta desde la 0
            linea += 1;
        } catch(Exception ex) { }

        // Actualizamos el estado
        actualizarEstado(linea, columna);
    }//GEN-LAST:event_textoAnalizarCaretUpdate

    /**
     * @param args the command line arguments
     */
    
    public Collection<TextoIDE> getEstadoPrevio(){
        return estadoPrevio.values();
    }
    
    public Collection<TextoIDE> getEstadoActual(){
        return estadoActual.values();
    }
    
    public boolean agregarEstadoPrevio(int ID, TextoIDE texto){
       TextoIDE put = estadoPrevio.put(ID, texto);
       deshacer.setEnabled(true);
       return (put!=null);
    }
    
    public boolean agregarEstadoActual(int ID, TextoIDE texto){
       TextoIDE put = estadoActual.put(ID, texto);
       rehace.setEnabled(true);
       return (put!=null);
    }
    
    public void limpiarEstadoActual(){
        estadoActual.clear();
        rehace.setEnabled(false);
    }
    
    public void removePreviusStates(int key) {
        estadoPrevio.remove(key);
        
        if (getEstadoPrevio().isEmpty())
            deshacer.setEnabled(false);
        
    }
    
    
    public void removeNextStates(int key) {
        estadoActual.remove(key);
        
        if (getEstadoActual().isEmpty())
            rehace.setEnabled(false);
    }
    
    public JTextArea getAreaTexto(){
        return this.textoAnalizar;
    }
    
    public void cargarArchivo(){
        //Agregamos un JFileChooser para poeder hacer la respectiva archovo
        JFileChooser buscarArchivo = new JFileChooser();
        int opcion = buscarArchivo.showOpenDialog(this);
        if(opcion == JFileChooser.APPROVE_OPTION){
            //Buscamos el archivo y procedemos a hacer la lectura del mismo
            String archivo2 = buscarArchivo.getSelectedFile().getAbsolutePath();
            this.archivoALeer = archivo2;
            try{
                File archivoCargar = new File(archivoALeer);
                if(archivoCargar.exists()){
                    archivo= buscarArchivo.getSelectedFile();
                    //Hacemos una llamada al Hilo de carga para poder almacenar cada valor en su respectiva Dirección
                    CargaDatos cargaDatos = new CargaDatos(archivoCargar);
                    cargaDatos.anilizarTexto();
                }                    
                //Error de punto nulo
            } catch(NullPointerException e){
                System.err.println(e);
            }
        }    
    }
    
    private void actualizarEstado(int linea, int columna) {
        posicionText.setText("Linea: " + linea + " Columna: " + (columna + 1));
    }
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem copiarText;
    private javax.swing.JMenuItem deshacer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JMenuItem pegarTexto;
    private javax.swing.JLabel posicionText;
    private javax.swing.JMenuItem rehace;
    private javax.swing.JButton salir;
    private javax.swing.JTextArea textoAnalizar;
    // End of variables declaration//GEN-END:variables
}
