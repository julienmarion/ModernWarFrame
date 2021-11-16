/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.jeu;

import modele.arme.Bomb;
import modele.arme.Mine;
import modele.player.Player;
import utilitaire.AbstractModeleEcoutable;

import java.util.ArrayList;
import java.util.Collections;

/**
 * classe implementant un modele du jeu que l'on peut modifier,
 * mais en filtrant certaines actions interdites ou certaines informations invisibles en fonction des joueurs
 */

public class ProxyModele extends AbstractModeleEcoutable implements ModelJeuEditable {
    
    /**
     * le joueur qui accede a ce proxy
     */
    private Player p;
    
    /**
     * le modele que le proxy va filtrer
     */
    private ModelJeuEditable m;
    
    /**
     * constructeur
     * @param m le modele que le proxy va filtrer
     * @param p le joueur qui accede a ce proxy
     */
    public ProxyModele(ModelJeuEditable m,Player p){
        this.m = m;
        this.p=p;
    }
    
    /**
     * place un objet dans la case de coordonnees (i,j)
     * @param i le numero de la ligne
     * @param j le numero de la colonne
     * @param obj l'objet a placer
     */
    @Override
    public void set(int i, int j, String obj) {
        this.m.set(i, j, obj);
    }

    /**
     * renvoie le joueur dont c'est le tour de jouer
     * @return le joueur dont c'est le tour de jouer
     */
    @Override
    public Player getCurrentPlayer() {
        return this.m.getCurrentPlayer();
    }

    /**
     * place une bombe (du joueur courant)
     * @param direction la direction dans laquelle le joueur courant place sa bombe
     */
    @Override
    public void setBomb(String direction) {
        this.m.setBomb(direction);
    }

    /**
     * place une mine (du joueur courant)
     * @param direction la direction dans laquelle le joueur courant place sa mine
     */
    @Override
    public void setMine(String direction) {
        this.m.setMine(direction);
    }

    /**
     * effectue un tir (du joueur courant)
     * @param direction la direction dans laquelle le joueur courant tire
     */
    @Override
    public void fire(String direction) {
        this.m.fire(direction);
        this.fireChangement(this);
    }

    /**
     * effectue un deplacement (du joueur courant)
     * @param direction la direction dans laquelle le joueur courant se deplace
     */
    @Override
    public void move(String direction){ //throws Exception {
        this.m.move(direction);
    }

    /**
     * le joueur courant ne fait rien durant son tour
     */
    @Override
    public void doNothing() {
        this.m.doNothing();
    }

    /**
     * active un bouclier sur le joueur courant
     */
    @Override
    public void activeShield() {
        this.m.activeShield();
    }

    /**
     * renvoie la mine presente dans la case de coordonnees (i,j)
     * @param i le numero de la ligne
     * @param j le numero de la colonne
     * @return la mine presente dans la case de coordonnees (i,j)
     */
    @Override
    public Mine getMine(int i, int j) {
        return this.m.getMine(i,j);
    }

    /**
     * renvoie la bombe presente dans la case de coordonnees (i,j)
     * @param i le numero de la ligne
     * @param j le numero de la colonne
     * @return la bombe presente dans la case de coordonnees (i,j)
     */
    @Override
    public Bomb getBomb(int i, int j) {
        return this.m.getBomb(i,j);
    }

    /**
     * indique si le jeu est termine
     * @return true si le jeu est fini, false sinon
     */
    @Override
    public boolean isWin() {
        return this.m.isWin();
    }

    /**
     * renvoie le contenu de la case de coordonnees (i,j),
     * a condition que le joueur puisse la voir
     * @param i le numero de la ligne
     * @param j le numero de la colonne
     * @return l'objet contenu dans la case (i,j) si celui-ci est visible pour le joueur
     */
    @Override
    public String get(int i, int j) {
     String obj=this.m.get(i, j);
        switch (obj){
            case "m":
                Mine m=this.m.getMine(i,j);
                if(m.getVisibility()){
                    return obj;
                }
                else {
                    if (p.hasPlantedMine(m)) {
                        return obj;
                    }
                }
                return "v";
            case "b":
                Bomb b=this.m.getBomb(i,j);
                if(b.getVisibility()){
                    return obj;
                }
                else {
                    if (p.hasPlantedBomb(b)) {
                        return obj;
                    }
                }
                return "v";
            default:
                return obj;

        }
    }

    /**
     * renvoie le nombre de colonnes du plateau
     * @return le nombre de colonnes du plateau
     */
    @Override
    public int nbColonnes() {
        return m.nbColonnes();
    }

    /**
     * renvoie le nombre de lignes du plateau
     * @return le nombre de lignes du plateau
     */
    @Override
    public int nbLignes() {
        return m.nbLignes();
    }

    /**
     * renvoie la liste des joueurs
     * @return la liste des joueurs
     */
    @Override
    public ArrayList<Player> getAllPlayer() {
        return new ArrayList<>(Collections.singletonList(p));
    }

}
