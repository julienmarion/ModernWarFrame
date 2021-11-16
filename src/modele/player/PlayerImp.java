package modele.player;

import modele.arme.*;
import utilitaire.Ressources;

import java.util.ArrayList;
import java.util.List;

/**
 * classe implementant un joueur
 */

public class PlayerImp implements Player {

    /**
     * le numero du joueur
     */
    private static int name=0;
    
    /**
     * l'energie du joueur
     */
    private int energy;
    
    /**
     * le canon que possede le joueur
     */
    private Canon canon;
    
    /**
     * la reserve de bombes du joueur
     */
    private ArrayList<Bomb> bombToUse;
    
    /**
     * la reserve de mines du joueur
     */
    private ArrayList<Mine> mineToUse;
    
    /**
     * la liste des bombes posees par le joueur
     */
    private List<Bomb> myBomb=new ArrayList<>();
    
    /**
     * la liste des mines posees par le joueur
     */
    private List<Mine> myMine=new ArrayList<>();
    
    /**
     * la ligne ou se trouve le joueur
     */
    private int posX;
    
    /**
     * la colonne ou se trouve le joueur
     */
    private int posY;
    
    /**
     * indique si le joueur a tire ce tour-ci
     */
    private boolean hasFire=false;
    
    /**
     * le nom du joueur
     */
    private int namePlayer;
    
    /**
     * indique si le joueur est protege par un bouclier ou non
     */
    private boolean hasShield=false;
    
    /**
     * indique si les explosifs du joueur sont visibles ou non
     */
    private boolean visibilityExplosif;
    
    /**
     * le nombre de tours au bout duquel les bombes du joueur explosent
     */
    private int nbTours= Ressources.DEFAULTNBTOURSBOMB;

    /**
     * constructeur
     * @param energy le montant d'energie du joueur
     * @param canon le canon que possede le joueur
     * @param nbBomb le nombre de bombes dont dispose le joueur
     * @param nbMine le nombre de mines dont dispose le joueur
     * @param initialPosX la ligne ou se trouve le joueur
     * @param initialPosY la colonne ou se trouve le joueur
     * @param visibilityExplosif la visibilite des explosifs du joueur
     */
    public PlayerImp(int energy, Canon canon, int nbBomb, int nbMine, int initialPosX, int initialPosY, boolean visibilityExplosif){
        namePlayer=PlayerImp.name;
        PlayerImp.name++;
        this.energy = energy;
        this.canon = canon;
        this.bombToUse = new ArrayList<Bomb>();
        this.visibilityExplosif=visibilityExplosif;
        for(int i=0;i<nbBomb;i++){
            bombToUse.add(new BombImp(30,visibilityExplosif,nbTours));
        }
        this.mineToUse = new ArrayList<Mine>();
        for(int i=0;i<nbMine;i++){
            mineToUse.add(new MineImp(20,visibilityExplosif));
        }
        this.myBomb=new ArrayList<>();
        this.myMine=new ArrayList<>();
        this.posX=initialPosX; //0
        this.posY=initialPosY; //1
    }

    /**
     * effectuve un deplacement du joueur dans la direction indiquee
     * @param mvt direction du deplacement
     */
    @Override
    public void move(String mvt) {
        switch (mvt) {
            case "left":
                this.posY--;
                break;
            case "right":
                this.posY++;
                break;
            case "up":
                this.posX--;
                break;
            case "down":
                this.posX++;
                break;

        }
    }

    /**
     * tire dans la direction indiquee
     * @param direction direction du tir
     */
    @Override
    public void fire(String direction) {
        hasFire=true;
    }

    /**
     * renvoie le montant d'energie du joueur
     * @return le montant d'energie du joueur
     */
    @Override
    public int getEnergy() {
        return this.energy;
    }

    /**
     * renvoie le nombre de bombes dont dispose le joueur
     * @return le nombre de bombes dont dispose le joueur
     */
    @Override
    public int getBomb() {
        return this.bombToUse.size();
    }

    /**
     * renvoie le nombre de mines dont dispose le joueur
     * @return le nombre de mines dont dispose le joueur
     */
    @Override
    public int getMine() {
        return this.mineToUse.size();
    }

    /**
     * ajoute une minde a la liste des mines posees par le joueur
     * @param mine 
     */
    @Override
    public void setMine(Mine mine) {
        this.myMine.add(mine);
    }

    /**
     * ajoute une bombe a la liste des bombes posees par le joueur
     * @param bomb 
     */
    @Override
    public void setBomb(Bomb bomb) {
        this.myBomb.add(bomb);
    }

    /**
     * renvoie le canon que possede le joueur
     * @return 
     */
    @Override
    public Canon getCanon() {
        return this.canon;
    }

    /**
     * indique si une bombe a ete placee par le joueur ou non
     * @param b la bombe
     * @return true si la bombe a ete placee par le joueur, false sinon
     */
    @Override
    public boolean hasPlantedBomb(Bomb b) {
        return this.myBomb.contains(b);
    }
    
    /**
     * indique si une mine a ete placee par le joueur ou non
     * @param m la mine
     * @return true si la mine a ete placee par le joueur, false sinon
     */
    @Override
    public boolean hasPlantedMine(Mine m) { return this.myMine.contains(m); }

    /**
     * indique si le joueur a tire durant ce tour ou non
     * @return true si le joueur a tire durant ce tour, false sinon
     */
    @Override
    public boolean hasFire() {
        if(hasFire) {
            hasFire=false;
            return true;
        }
        return false;

    }

    /**
     * renvoie la ligne ou se trouve le joueur
     * @return la ligne ou se trouve le joueur
     */
    @Override
    public int getPosX() {
        return posX;
    }

    /**
     * renvoie la colonne ou se trouve le joueur
     * @return la colonne ou se trouve le joueur
     */
    @Override
    public int getPosY() {
        return posY;
    }

    /**
     * inflige des degats au joueur et indique s'il est toujours en vie ou non
     * @param damage le montant des degats infliges au joueur
     * @return true si le joueur est toujours en vie, false sinon
     */
    @Override
    public boolean setDamage(int damage) {
        if (!this.hasShield)
            this.energy=this.energy-damage;
        return this.energy > 0;
    }

    /**
     * active ou desactive le bouclier du joueur
     * @param shield true pour activer le bouclier, false pour le desactiver
     */
    @Override
    public void setShield(boolean shield) {
        this.hasShield = shield;
    }

    /**
     * prend une mine dans la reserve de mines du joueur
     * @return null si la reserve etait vide, la mine sinon
     */
    @Override
    public Mine getAMine() {
        if(mineToUse.isEmpty()){
            return null;
        }
        Mine mine=mineToUse.get(0);
        myMine.add(mine);
        mineToUse.remove(0);
        return mine;
    }

    /**
     * prend une bombe dans la reserve de bombes du joueur
     * @return null si la reserve etait vide, la bombe sinon
     */
    @Override
    public Bomb getABomb() {
        if(bombToUse.isEmpty()){
            return null;
        }
        Bomb bomb=bombToUse.get(0);
        bombToUse.remove(0);
        myBomb.add(bomb);
        return bomb;
    }

    /**
     * renvoie le nom du joueur
     */
    @Override
    public String toString(){
        return ""+this.namePlayer;
    }
}
