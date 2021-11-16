
package modele.jeu;

import modele.player.Player;
import utilitaire.Ressources;

import java.util.ArrayList;
import java.util.List;

import modele.player.Wall;

/**
 * classe servant a construire un plateau, en precisant ou non ses caracteristiques
 */
public class PlateauBuilder {
    /**
     * le nombre de lignes du plateau
     */
    private int nbLignes=Ressources.DEFAULTNBLIGNES;
    
    /**
     * le nombre de colonnes du plateau
     */
    private int nbColonnes=Ressources.DEFAULTNBCOLONNES;
    
    /**
     * la presence ou non de murs sur le plateau
     */
    private boolean withWall=Ressources.DEFAULTWITHWALL;
    
    /**
     * la liste des joueurs presents sur le plateau
     */
    private List<Player> playerOnMap;
    
    /**
     * la liste des murs presents sur le plateau
     */
    private List<Wall> listeWall;

    /**
     * renseigne la liste des joueurs et la liste des murs presents sur le plateau
     * @param playerOnMap la liste des joueurs
     * @param listeWall la liste des murs
     * @throws Exception si le nombre de joueurs est inferieur a 2
     */
    public PlateauBuilder(List<Player> playerOnMap, List<Wall> listeWall) throws Exception {
        this.playerOnMap = playerOnMap;
        if(playerOnMap.size()<2){
            throw new Exception("Need more player to play this game(nb>=2)");
        }
        this.listeWall = listeWall;
    }
    
    /**
     * renseigne le nombre de lignes du plateau
     * @param lignes le nombre de lignes du plateau
     * @return le builder du plateau en tenant compte du nombre de lignes fourni
     */
    public PlateauBuilder setNbLignes(int lignes){
        this.nbLignes=lignes;
        return this;
    }
    
    /**
     * renseigne le nombre de colonnes du plateau
     * @param colonnes le nombre de colonnes du plateau
     * @return le builder du plateau en tenant compte du nombre de colonnes fourni
     */
    public PlateauBuilder setNbColonnes(int colonnes){
        this.nbColonnes=colonnes;
        return this;
    }
    
    /**
     * renseigne la presence ou non de murs sur le plateau
     * @param wall la presence ou non de murs sur le plateau
     * @return le builder du plateau en tenant compte de la presence ou non de murs fournie
     */
    public PlateauBuilder setWall(boolean wall){
        this.withWall=wall;
        return this;
    }
    
    /**
     * construit le plateau
     * @return le plateau construit
     */
    public ModelJeuEditable build(){
        ModelJeuEditImp jeu;
        if(this.withWall){
            jeu=new ModelJeuEditImp(this.nbLignes,this.nbColonnes,this.playerOnMap,this.listeWall);
        } else {
            jeu=new ModelJeuEditImp(this.nbLignes,this.nbColonnes,this.playerOnMap);
        }

        return jeu;
    }
}
