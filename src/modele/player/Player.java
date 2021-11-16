package modele.player;

import modele.arme.Bomb;
import modele.arme.Mine;
import modele.arme.Canon;

/**
* interface de joueur contenant ses caracteristiques et actions possibles
*/

public interface Player extends Positionnable {

    /**
     * effectue un deplacement dans la direction indiquee
     *
     * @param mvt la direction du deplacement
     */
    void move(String mvt);

    /**
     * tire dans une direction
     * @param direction la direction dans laquelle le joueur tire
     */
    void fire(String direction);

    /**
     * renvoie le montant d'energie dont dispose le joueur
     * @return le montant d'energie dont dispose le joueur
     */
    int getEnergy();

    /**
     * renvoie le nombre de bombes dont dispose le joueur
     * @return le nombre de bombes dont dispose le joueur
     */
    int getBomb();

    /**
     * renvoie le nombre de mines dont dispose le joueur
     * @return le nombre de mines dont dispose le joueur
     */
    int getMine();

    /**
     * pose une mine dans la case de coordonnees (i,j)
     * @param mine la mine qui doit être posée
     */
    void setMine(Mine mine);

    /**
     * pose une bombe dans la case de coordonnees (i,j)
     * @param bomb la bombe qui doit être posée
     */
    void setBomb(Bomb bomb);

    /**
     * renvoie le canon dont est equipe le joueur
     * @return le canon dont est equipe le joueur
     */
    Canon getCanon();
    
    /**
     * indique si une bombe du joueur se situe dans la case (i,j)
     * @param bomb la bombe dont on veut savoir si elle appartient au joueur ou non
     * @return true si le joueur a mis une bombe dans la case (i,j), false sinon
     */
    boolean hasPlantedBomb(Bomb bomb);
    
    /**
     * indique si une mine du joueur se situe dans la case (i,j)
     * @param mine la mine dont on veut savoir si elle appartient au joueur ou non
     * @return true si le joueura mis une mine dans la case (i,j), false sinon
     */
    boolean hasPlantedMine(Mine mine);

    /**
     * indique si le joueur a tire ce tour-ci
     * @return true si le joueur a tire ce tour-ci, false sinon
     */
    boolean hasFire();

    /**
     * inflige des degats au joueur
     * @param damage le montant de degats inflige au joueur
     * @return true si le joueur est toujours vivant, false sinon
     */
    boolean setDamage(int damage);

    /**
     * prend une mine dans le stock du joueur
     * @return la mine prise dans le stock
     */
    Mine getAMine();

    /**
     * prend une bombe dans le stock du joueur
     * @return la bombe prise dans le stock
     */
    Bomb getABomb();

    /**
     * active ou desactive le bouclier du joueur
     * @param b true pour que le joueur ait un bouclier, false sinon
     */
    void setShield(boolean b);
}
