package modele.player;

import modele.arme.Canon;
import utilitaire.Ressources;

/**
 * classe servant a construire un joueur, en precisant ou non ses caracteristiques
 */
public class PlayerBuilder {
    /**
     * la quantite d'energie de depart du joueur
     */
    private int energy= Ressources.DEFAULTENERGY;
    
    /**
     * le canon que possede le joueur
     */
    private Canon canon;
    
    /**
     * le nombre de bombes dont dispose le joueur
     */
    private int nbBomb=Ressources.DEFAULTNBBOMB;
    
    /**
     * le nombre de mines dont dispose le joueur
     */
    private int nbMine=Ressources.DEFAULTNBMINE;
    
    /**
     * la ligne de depart du joueur
     */
    private int initialPosX;
    
    /**
     * la colonne de depart du joueur
     */
    private int initialPosY;
    
    /**
     * la visibilite des explosifs du joueur
     */
    private boolean visibilityExplosif=Ressources.VISIBILITYEXPLOSIF;


    /**
     * renseigne le montant d'energie de depart du joueur
     * @param energy le montant d'energie de depart du joueur
     * @return le builder du joueur en tenant compte du montant d'energie fourni
     */
    public PlayerBuilder setEnergy(int energy) {
        this.energy = energy;
        return this;
    }

    /**
     * renseigne le canon que possede le joueur
     * @param canon le canon que possede le joueur
     * @return le builder du joueur en tenant compte du canon fourni
     */
    public PlayerBuilder setCanon(Canon canon) {
        this.canon = canon;
        return this;
    }

    /**
     * renseigne le nombre de bombes dont dispose le joueur
     * @param nbBomb le nombre de bombes dont dispose le joueur
     * @return le builder du joueur en tenant compte du nombre de bombes fourni
     */
    public PlayerBuilder setNbBomb(int nbBomb) {
        this.nbBomb = nbBomb;
        return this;
    }

    /**
     * renseigne le nombre de mines dont dispose le joueur
     * @param nbMine le nombre de mines dont dispose le joueur
     * @return le builder du joueur en tenant compte du nombre de mines fourni
     */
    public PlayerBuilder setNbMine(int nbMine) {
        this.nbMine = nbMine;
        return this;
    }

    /**
     * renseigne la visibilite des explosifs du joueur
     * @param visibilityExplosif la visibilite des explosifs du joueur
     * @return le builder du joueur en tenant compte de la visibilite fournie
     */
    public PlayerBuilder setVisibilityExplosif(boolean visibilityExplosif) {
        this.visibilityExplosif = visibilityExplosif;
        return this;
    }

    /**
     * construit le joueur
     * @return le joueur construit
     */
    public Player build() {
        return new PlayerImp(energy,canon,nbBomb,nbMine,initialPosX,initialPosY,visibilityExplosif);
    }

    /**
     * renseigne la ligne de depart du joueur
     * @param x la ligne de depart du joueur
     * @return le builder du joueur en tenant compte de la ligne fournie
     */
    public PlayerBuilder setPosX(int x){
        initialPosX=x;
        return this;
    }

    /**
     * renseigne la colonne de depart du joueur
     * @param y la colonne de depart du joueur
     * @return le builder du joueur en tenant compte de la colonne fournie
     */
    public PlayerBuilder setPosY(int y){
        initialPosY=y;
        return this;
    }

}
