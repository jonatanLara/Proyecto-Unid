/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Splash;

import com.sun.awt.AWTUtilities;
import java.util.Random;

/**
 *
 * @author jonatanLara
 */
public final class Splash_Ventana extends javax.swing.JFrame {

    /**
     * Creates new form Ventana
     */
    double i =50, j =1;
     ImagenDeFondo modelo;
     ImagenDeFondo modelos = new ImagenDeFondo("Unid_conecta.png");
    Cargar  hilo;
    //= new ImagenDeFondo("Unid_conecta.png");
    public Splash_Ventana() {
        initComponents();
        setContentPane(modelos);
        iniciar();
        //random();
    }
    public void iniciar(){
        setLocationRelativeTo(null);
        hilo = new Cargar(getjProgressBar1());
        hilo.start();
        hilo = null;
    }
    public void random(){
        String arr[]  = new String[]{"Unid_conecta.png","adm.jpg","comunicacion.jpg","lic_sis.jpg"};
        Random img = new Random();
        int x = img.nextInt(arr.length-1);
        String aux = arr[x].toString();
        System.out.println(""+aux);
        modelo = new ImagenDeFondo(aux);
        setContentPane(modelo);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jProgressBar1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jProgressBar1StateChanged(evt);
            }
        });
        getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, -1, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jProgressBar1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jProgressBar1StateChanged
        // TODO add your handling code here:
        if (jProgressBar1.getValue()==i) {
           // System.exit(0);
            if (j !=101) {
                 AWTUtilities.setWindowOpacity(this, Float.valueOf((100-j)/100+"f"));
                 i++;
                 j+=2;
                if (j == 101) {
                    //System.exit(0);
                    
                    new unid.MainForm().setVisible(true);
                    this.dispose();
                   //this.setDefaultCloseOperation(0);
                }
            }
           
        }
        
           
    }//GEN-LAST:event_jProgressBar1StateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Splash_Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Splash_Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Splash_Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Splash_Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Splash_Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the jProgressBar1
     */
    public javax.swing.JProgressBar getjProgressBar1() {
        return jProgressBar1;
    }

    /**
     * @param jProgressBar1 the jProgressBar1 to set
     */
    public void setjProgressBar1(javax.swing.JProgressBar jProgressBar1) {
        this.jProgressBar1 = jProgressBar1;
    }
}
