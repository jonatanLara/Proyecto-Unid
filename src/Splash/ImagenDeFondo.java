/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Splash;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 *
 * @author jonatan
 */

public class ImagenDeFondo extends JPanel {

    private Image imagen;
    //creo mi constructor
    public ImagenDeFondo() {
    //mi imagen sera null para retomarla despues
        imagen =null;
       
      // this.setSize(795, 600);
      } 
    
     public ImagenDeFondo(String nombre){
          if(nombre != null)
              imagen = new ImageIcon(getClass().getResource(nombre)).getImage();
      }
        public ImagenDeFondo(Image img) {
        //si image es diferente asignaremos la variable imagen un image
        if (img != null) {
            imagen = img;
        }
     }
         public void setImage (String nombre) {
        //si es el nombre de la imagen que estamos resiviendo trae algun contenido
        if (nombre != null) {
            //
            imagen = new ImageIcon(getClass().getResource(nombre)).getImage();
        } else {
            imagen = null;
        }
        repaint();
    }
         @Override
    public void paint(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
        } else {
            setOpaque(true);
        }
        super.paint(g);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    }
