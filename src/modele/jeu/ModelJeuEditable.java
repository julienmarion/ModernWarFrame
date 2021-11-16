
package modele.jeu;

import modele.arme.Bomb;
import modele.arme.Mine;
import modele.player.Player;

/**
 * interface du modele du jeu que l'on peut modifier
 */
public interface ModelJeuEditable extends ModelJeu{
    
    /**
     * definit le contenu de la case de coordonnees (i,j)
     * @param i le numero de la ligne
     * @param j le numero de la colonne
     * @param obj l'objet qu'on met dans la case
     */
    void set(int i, int j,String obj);
    
    /**
     * indique le joueur en train de jouer
     * @return le joueur en train de jouer
     */
    Player getCurrentPlayer();
    
    /**
     * place une bombe
     * @param direction la direction dans laquelle on place la bombe
     */
    void setBomb(String direction);
    
    /**
     * place une mine 
     * @param direction la direction dans laquelle on place la mine
     */
    void setMine(String direction);
    
    /**
     * effectue un tir
     * @param direction la direction dans laquelle on effectue le tir
     */
    void fire (String direction);
    
    /**
     * effectue un deplacement
     * @param direction la direction dans laquelle on effectue le deplacement
     */
    void move(String direction) ;
    
    /**
     * ne fait rien durant ce tour
     */
    void doNothing();
    
    /**
     * actionne un bouclier
     */
    void activeShield();

    /**
     * renvoie la mine presente aux coordonnees (i,j)
     * @param i le numero de la ligne
     * @param j le numero de la colonne
     * @return la mine presente aux coordonnees (i,j)
     */
    Mine getMine(int i, int j);
    
    /**
     * renvoie la bombe presente aux coordonnees (i,j)
     * @param i le numero de la ligne
     * @param j le numero de la colonne
     * @return la bombe presente aux coordonnees (i,j)
     */
    Bomb getBomb(int i, int j);

    /**
     * indique si la partie est terminee
     * @return true si la partie est terminee, false sinon
     */
    boolean isWin();

}
