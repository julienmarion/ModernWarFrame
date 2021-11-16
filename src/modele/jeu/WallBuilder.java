
package modele.jeu;

import java.util.ArrayList;
import modele.player.Player;
import modele.player.Wall;

/**
 * classe servant a construire une liste de murs
 */

public class WallBuilder {
    
    /**
     * le nombre de lignes du plateau sur lequel on place des murs
     */
    private int nbLignes;
    
    /**
     * le nombre de colonnes du plateau sur lequel on place des murs
     */
    private int nbColonnes;
    
    /**
     * la probabilite d'apparition d'un mur
     */
    private double probaWall;
    
    /**
     * constructeur
     * @param nbLignes le nombre de lignes du plateau sur lequel on place des murs
     * @param nbColonnes le nombre de colonnes du plateau sur lequel on place des murs
     * @param probaWall la probabilite d'apparition d'un mur
     */
    public WallBuilder(int nbLignes, int nbColonnes, double probaWall) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.probaWall = probaWall;
    }
    
    /**
     * construit une liste de murs
     * @return la liste des murs
     */
    public ArrayList<Wall> build(){
        ArrayList<Wall> listeWall = new ArrayList<>();
        for (int i = 1 ; i<nbLignes-1 ; i=i+2) {
            for (int j=1 ; j<nbColonnes-1 ; j=j+2) {
                if (Math.random()<probaWall) {
                    Wall w = new Wall(i,j);
                    listeWall.add(w);
                }
            }
        }
        return listeWall;
    }
    
}
