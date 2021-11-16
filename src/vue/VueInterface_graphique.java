
package vue;

import modele.jeu.ModelJeuEditable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Cette classe permet de dessiner sur le JPanel a l'aide de la methode paint()
 */
public class VueInterface_graphique extends JPanel{
    /**
     * le nombre de colonne
     */
    private int nbcolcase;
    /**
     * le nombre de ligne
     */
    private int nblignecase;
    /**
     * le model du jeu en cours
     */
    private ModelJeuEditable model;
    /**
     * image du joueur
     */
    private BufferedImage imagejoueur;
    /**
     * image de la bombe
     */
    private BufferedImage imagebombe;
    /**
     * image de la mine
     */
    private BufferedImage imagemine;
    /**
     * image du mur
     */
    private BufferedImage imagewall;
    
    /**
     * permet de redimensionner chaque image 
     * @param img l'imae que l'on veut redimensionner
     * @param newW largueur de l'image souhaite
     * @param newH hauteur de l'image souhaite
     * @return notre image avec les dimension souhaite
     */
    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
} 
    
    /**
     * Constructeur
     * @param nbcolcase le nombre de colonne de la grille
     * @param nblignecase le nombre de ligne de la grille
     * @param model le model du jeu en cours de la grille
     */
    public VueInterface_graphique(int nbcolcase, int nblignecase, ModelJeuEditable model){
        this.nbcolcase=nbcolcase;
        this.nblignecase=nblignecase;
        this.model=model;
        
        URL resourcejoueur = getClass().getResource("ressource/pion.png");
        URL resourcebombe = getClass().getResource("ressource/bombe.png");
        URL resourcemine = getClass().getResource("ressource/mine.jpg");
        URL resourcewall = getClass().getResource("ressource/wall.jpg");
        try {
            imagejoueur = ImageIO.read(resourcejoueur);
            imagebombe = ImageIO.read(resourcebombe);
            imagemine = ImageIO.read(resourcemine);
            imagewall = ImageIO.read(resourcewall);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void paint(Graphics g){
        //super.paintComponent(g);
        g.setColor(Color.black);
        float Xcase = (getWidth()/nbcolcase);
        float Ycase = (getHeight()/nblignecase);
        imagejoueur=resize(imagejoueur,(int)Xcase-10,(int)Ycase-10);
        imagemine=resize(imagemine,(int)Xcase-10,(int)Ycase-10);
        imagebombe=resize(imagebombe,(int)Xcase-10,(int)Ycase-10);
        imagewall=resize(imagewall,(int)Xcase-10,(int)Ycase-10);
        
        for(float i=0; i<nbcolcase+1; i++){
            float x= ((Xcase*i)-(i/nbcolcase));
            g.drawLine(Math.round(x), 0, Math.round(x), getHeight());
            
        }
        for(float j=0;j<nblignecase+1;j++){
            float y=(Ycase*j)-(j/nblignecase);
            g.drawLine(0, Math.round(y), getWidth(), Math.round(y));
        }
        
        for(int i = 0; i<model.nbLignes(); i++){
            for(int j = 0; j<model.nbColonnes(); j++){
                if(model.get(j,i)=="m"){ //mine
                    g.drawImage(imagemine, i*(int)Xcase+5, j*(int)Ycase+5, this);
                    
                }
                else if(model.get(j,i)=="b"){ //bombe
                    g.drawImage(imagebombe, i*(int)Xcase+5, j*(int)Ycase+5, this);
                    
                }
                else if(model.get(j,i)=="v"){
                  
                    
                }
                else if(model.get(j,i)=="w"){
                    g.drawImage(imagewall, i*(int)Xcase+5, j*(int)Ycase+5, this);
                    
                }
                else{
                    g.drawImage(imagejoueur,i*(int)Xcase+5,j*(int)Ycase+5,this);
                   
                    g.drawString(model.get(j,i).toString(), i*(int)Xcase+10 ,j*(int)Ycase+20);
                }
            }
                
        }
        
    }

    

}
