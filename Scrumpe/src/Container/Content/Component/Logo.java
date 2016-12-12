/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Container.Content.Component;

import Utils.AssHandler;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @deprecated  
 * not used ATM
 * @author MJ. Verhoeven
 */
public class Logo extends JPanel {

    public enum Location {
        LEFT, CENTER,
        RIGHT
    };
    private static BufferedImage image;
    private int w, h;
    private int location = 0;

    /**
     *
     * @return
     */
    public int getW() {
        return w;
    }

    /**
     *
     * @return
     */
    public int getH() {
        return h;
    }

    /**
     *
     */
    public Logo() {
        this(Location.LEFT);
    }

    /**
     *
     * @param location
     */
    public Logo(Location location) {
        createLogo();
        w = image.getWidth();
        h = image.getHeight();
        setPreferredSize(new Dimension(w, h));
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        if (location == Location.CENTER) {
            this.location = (width / 2) - (w / 2);
        } else if (location == Location.RIGHT) {
            this.location = width - (w + 20);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, location, 0, this);
    }

    /**
     *
     * @return
     */
    public static BufferedImage createLogo() {
        try {
            System.out.println(AssHandler.getRootDir() + AssHandler.ASSET_DIR);
            image = ImageIO.read(new File(AssHandler.getRootDir() + AssHandler.ASSET_DIR + "squerist.png"));
            return image;
        } catch (IOException ex) {
            ex.printStackTrace();
            return (BufferedImage) BufferedImage.UndefinedProperty;
        }
    }

}
