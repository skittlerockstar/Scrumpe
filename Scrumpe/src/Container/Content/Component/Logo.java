/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Container.Content.Component;

import Utils.AssHandler;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author MJ. Verhoeven
 */
public class Logo extends JPanel{

    private BufferedImage image;
    private int w,h;

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
    public Logo() {
       try {
           System.out.println(AssHandler.getRootDir()+AssHandler.ASSET_DIR);
           image = ImageIO.read(new File(AssHandler.getRootDir()+AssHandler.ASSET_DIR+"squerist.jpg"));
           w = image.getWidth();
           h = image.getHeight();
       } catch (IOException ex) {
         ex.printStackTrace();
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
    }
    
}
