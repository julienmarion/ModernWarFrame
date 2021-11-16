
package modele.jeu;

import modele.player.Player;

import java.util.ArrayList;

/**
 * interface du modele du jeu
 */
public interface ModelJeu {
    
    /**
     * indique le contenu de la case de coordonnees (i,j)
     * @param i le numero de la ligne
     * @param j le numero de la colonne
     * @return le contenu de la case de coordonnees (i,j)
     */
    String get(int i, int j);
    
    /**
     * indique le nombre de colonnes du modele
     * @return le nombre de colonnes du modele
     */
    int nbColonnes();
    
    /**
     * indique le nombre de lignes du modele
     * @return le nombre de lignes du modele
     */
    int nbLignes();
    
    /**
     * renvoie une liste de tous les joueurs du modele
     * @return la liste de tous les joueurs du modele
     */
    ArrayList<Player> getAllPlayer();
}
