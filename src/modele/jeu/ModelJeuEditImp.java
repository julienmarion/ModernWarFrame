/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.jeu;

import modele.player.*;
import modele.arme.*;
import java.util.*;


import utilitaire.AbstractModeleEcoutable;

/**
 * classe implementant un modele editable du jeu
 */
public class ModelJeuEditImp extends AbstractModeleEcoutable implements ModelJeuEditable {
    
    /**
     * contient le plateau du jeu dont les cases sont representees par des caracteres
     * v pour une case vide
     * un numero pour un joueur
     * m pour une mine
     * b pour une bombe
     * w pour un mur
     */
    private String[][] plateau;
    
    /**
     * contient le numero du joueur dont c'est le tour de jouer
     */
    public int indexCurrentPlayer=0;
    
    /**
     * contient la liste des joueurs presents sur le plateau
     */
    private List<Player> playerOnMap;
    
    /**
     * contient une map des mines presentes sur le plateau
     */
    private HashMap<List<Integer>,Mine> mineOnBoard=new HashMap<>();
    
    /**
     * contient une map des bombes presentes sur le plateau
     */
    private HashMap<List<Integer>,Bomb> bombOnBoard=new HashMap<>();
    
    /**
     * variable indiquant si le jeu est fini
     */
    private boolean win=false;


    /**
     * constructeur (sans murs)
     * @param lignes le nombre de lignes du plateau
     * @param colonnes le nombre de colonnes du plateau
     * @param playerOnMap la liste des joueurs presents sur le plateau
     */
    public ModelJeuEditImp(int lignes, int colonnes, List<Player> playerOnMap){
        this.plateau=new String[lignes][colonnes];
        for(int x=0;x<lignes;x++)
            for(int y=0;y<colonnes;y++)
                this.plateau[x][y]="v";
        this.playerOnMap=playerOnMap;
        for(Player p:playerOnMap)
            set(p.getPosX(),p.getPosY(),""+p);
    }

    /**
     * constructeur (avec des murs)
     * @param lignes le nombre de lignes du plateau
     * @param colonnes le nombre de colonnes du plateau
     * @param playerOnMap la liste des joueurs presents sur le plateau
     * @param listeWall la liste des murs presents sur le plateau
     */
    public ModelJeuEditImp(int lignes, int colonnes, List<Player> playerOnMap, List<Wall> listeWall){
        this.plateau=new String[lignes][colonnes];
        for(int x=0;x<lignes;x++)
            for(int y=0;y<colonnes;y++)
                this.plateau[x][y]="v";
        this.playerOnMap=playerOnMap;

        for(Wall w:listeWall)
            set(w.getPosX(),w.getPosY(),"w");

        for(Player p:playerOnMap)
            set(p.getPosX(),p.getPosY(),""+p);
    }
    
    /**
     * place un objet dans la case de coordonnees (x,y)
     * @param x le numero de la ligne
     * @param y le numero de la colonne
     * @param obj l'objet a placer
     */
    @Override
    public void set(int x, int y,String obj) {
        this.plateau[x][y]=obj;
    }

    /**
     * renvoie le contenu de la case de coordonnees (i,j)
     * @param x le numero de la ligne
     * @param y le numero de la colonne
     * @return le contenu de la case de coordonnees (i,j)
     */
    @Override
    public String get(int x, int y) {
        return this.plateau[x][y];
    }

    /**
     * renvoie le nombre de colonnes du plateau
     * @return le nombre de colonnes du plateau
     */
    @Override
    public int nbColonnes() {
        return this.plateau.length;
    }

    /**
     * renvoie le nombre de lignes du plateau
     * @return le nombre de lignes du plateau
     */
    @Override
    public int nbLignes() {
        return this.plateau[0].length;
    }

    /**
     * effectue un deplacement du joueur courant dans une direction
     * @param direction la direction du deplacement
     */
    @Override
    public void move(String direction) {
        int x=getCurrentPlayer().getPosX(),y=getCurrentPlayer().getPosY();
        boolean isAlive;
        isAlive=getCurrentPlayer().setDamage(5);
        if(!isAlive){
            set(x,y,"v");
            playerOnMap.remove(getCurrentPlayer());
            this.indexCurrentPlayer--;
            nextIndex();
            return;
        }
        switch(direction){
            case "left":
                if(0 < y) {
                    switch (get(x, y-1)) {
                        case "v":
                            set(x,y,"v");
                            this.getCurrentPlayer().move("left");
                            set(x,y-1,"" + getCurrentPlayer());
                            this.fireChangement(this);
                            nextIndex();
                            break;
                        case "b":
                            this.getCurrentPlayer().move("left");
                            Bomb b=bombOnBoard.get(Arrays.asList(x-1,y));
                            isAlive=this.explose(b);
                            set(x, y, "v");
                            if(!isAlive){
                                set(x,y,"v");
                                playerOnMap.remove(getCurrentPlayer());
                            }
                            else
                                set(x, y - 1, "" + getCurrentPlayer());

                            this.fireChangement(this);
                            nextIndex();
                            break;
                        case "m":
                            this.getCurrentPlayer().move("left");
                            Mine m=mineOnBoard.get(Arrays.asList(x-1,y));
                            isAlive=this.explose(m);
                            set(x, y, "v");
                            if(!isAlive){
                                playerOnMap.remove(getCurrentPlayer());
                                indexCurrentPlayer--;
                            }
                            else
                                set(x, y - 1, "" + getCurrentPlayer());

                            this.fireChangement(this);
                            nextIndex();
                            break;
                        default :
                            // Si le joueur tente d'avancer contre un mur ("w") ou contre un autre joueur ("0","1","2",...),
                            // alors il ne se passe rien
                            break;
                    }
                }
                break;
            case "right":
                if(y < nbColonnes()) {
                    switch (get(x, y+1)) {
                        case "v":
                            set(x,y,"v");
                            this.getCurrentPlayer().move("right");
                            set(x,y+1,""+getCurrentPlayer());
                            this.fireChangement(this);
                            nextIndex();
                            break;
                        case "b":
                            this.getCurrentPlayer().move("right");
                            Bomb b=bombOnBoard.get(Arrays.asList(x,y+1));
                            isAlive =this.explose(b);
                            set(x, y, "v");
                            if(!isAlive){
                                playerOnMap.remove(getCurrentPlayer());
                            }
                            else
                                set(x, y + 1, "" + getCurrentPlayer());

                            this.fireChangement(this);
                            nextIndex();
                            break;
                        case "m":
                            this.getCurrentPlayer().move("right");
                            Mine m=mineOnBoard.get(Arrays.asList(x,y+1));
                            isAlive=this.explose(m);
                            set(x, y, "v");
                            if(!isAlive){
                                playerOnMap.remove(getCurrentPlayer());
                                indexCurrentPlayer--;
                            }
                            else
                                set(x, y + 1, "" + getCurrentPlayer());
                            this.fireChangement(this);
                            nextIndex();
                            break;
                        default:
                            
                            break;
                    }

                }
                break;
            case "up":
                if(x>0) {
                    switch (get(x-1, y)) {
                        case "v":
                            set(x,y,"v");
                            this.getCurrentPlayer().move("up");
                            set(x-1,y,""+getCurrentPlayer());
                            this.fireChangement(this);
                            nextIndex();
                            break;
                        case "b":
                            Bomb b=bombOnBoard.get(Arrays.asList(x-1,y));
                            this.getCurrentPlayer().move("up");
                            isAlive=this.explose(b);
                            set(x,y,"v");
                            if(!isAlive){
                                playerOnMap.remove(getCurrentPlayer());
                            }
                            else
                                set(x - 1, y, "" + getCurrentPlayer());

                            this.fireChangement(this);
                            nextIndex();
                            break;
                        case "m":
                            this.getCurrentPlayer().move("up");
                            Mine m=mineOnBoard.get(Arrays.asList(x-1,y));
                            isAlive=this.explose(m);
                            set(x,y,"v");
                            if(!isAlive){
                                playerOnMap.remove(getCurrentPlayer());
                                indexCurrentPlayer--;
                            }
                            else
                                set(x-1,y,""+getCurrentPlayer());
                            this.fireChangement(this);
                            nextIndex();
                            break;
                    }
                }
                break;
            case "down":
                if(x < nbLignes()) {
                    switch (get(x+1, y)) {
                        case "v":
                            set(x,y,"v");
                            this.getCurrentPlayer().move("down");
                            set(x+1,y,""+getCurrentPlayer());
                            this.fireChangement(this);
                            nextIndex();
                            break;
                        case "b":
                            Bomb b=bombOnBoard.get(Arrays.asList(x+1,y));
                            set(x,y,"v");
                            this.getCurrentPlayer().move("down");
                            isAlive=this.explose(b);
                            if(!isAlive){
                                playerOnMap.remove(getCurrentPlayer());
                            }
                            else
                                set(x+1,y,""+getCurrentPlayer());
                            this.fireChangement(this);
                            nextIndex();
                            break;
                        case "m":
                            Mine m=mineOnBoard.get(Arrays.asList(x+1,y));
                            set(x,y,"v");
                            this.getCurrentPlayer().move("down");
                            isAlive=this.explose(m);
                            if(!isAlive){
                                playerOnMap.remove(getCurrentPlayer());
                                indexCurrentPlayer--;
                            }
                            else
                                set(x+1,y,""+getCurrentPlayer());
                            this.fireChangement(this);
                            nextIndex();
                            break;
                        default:

                            break;
                    }
                }
                break;
                default:
                //throw new Exception("Error wrong direction specified");
                    break;
        }
    }

    /**
     * renvoie la liste des joueurs
     * @return la liste des joueurs
     */
    @Override
    public ArrayList<Player> getAllPlayer() {
        return new ArrayList<>(playerOnMap);
    }

    /**
     * renvoie le joueur dont c'est le tour de jouer
     * @return le joueur dont c'est le tour de jouer
     */
    public Player getCurrentPlayer(){
        return this.playerOnMap.get(indexCurrentPlayer);
    }

    /**
     * pose une mine (du joueur courant)
     * @param direction la direction dans laquelle le joueur courant pose sa mine
     */
    @Override
    public void setMine(String direction){
        int i=0,j=0;
        boolean succes=false;
        switch(direction){
            case "n":
                if(getCurrentPlayer().getPosX() > 0) {
                    i = getCurrentPlayer().getPosX() - 1;
                    j = getCurrentPlayer().getPosY();
                    succes=true;
                }
                break;
            case "s":
                if(getCurrentPlayer().getPosX() < nbLignes()){
                    i = getCurrentPlayer().getPosX() + 1;
                    j = getCurrentPlayer().getPosY();
                    succes=true;
                }
                break;
            case "w":
                if(getCurrentPlayer().getPosY() > 0){
                    i = getCurrentPlayer().getPosX();
                    j = getCurrentPlayer().getPosY() - 1;
                    succes=true;
                }
                break;
            case "e":
                if(getCurrentPlayer().getPosY() < nbColonnes()){
                    i = getCurrentPlayer().getPosX();
                    j = getCurrentPlayer().getPosY() + 1;
                    succes=true;
                }
                break;
            case "nw":
                if(getCurrentPlayer().getPosX() > 0 && getCurrentPlayer().getPosY() > 0) {
                    i = getCurrentPlayer().getPosX() - 1;
                    j = getCurrentPlayer().getPosY() - 1;
                    succes=true;
                }
                break;
            case "ne":
                if(getCurrentPlayer().getPosX() > 0 && getCurrentPlayer().getPosY() < nbColonnes()) {
                    i = getCurrentPlayer().getPosX() - 1;
                    j = getCurrentPlayer().getPosY() + 1;
                    succes=true;
                }
                break;
            case "sw":
                if(getCurrentPlayer().getPosX() < nbLignes() && getCurrentPlayer().getPosY() > 0) {
                    i = getCurrentPlayer().getPosX() + 1;
                    j = getCurrentPlayer().getPosY() - 1;
                    succes=true;
                }
                break;
            case "se":
                if(getCurrentPlayer().getPosX() < nbLignes() && getCurrentPlayer().getPosY() < nbColonnes()) {
                    i = getCurrentPlayer().getPosX() + 1;
                    j = getCurrentPlayer().getPosY() + 1;
                    succes=true;
                }
                break;
            default: return;
        }
        if(succes) {
            Mine mine =null;
            if (get(i,j).equals("v")) {
                mine=this.getCurrentPlayer().getAMine();
                this.set(i, j, "m");
                mine.setPosX(i);
                mine.setPosY(j);
                this.mineOnBoard.put(Arrays.asList(i,j),mine);
                nextIndex();
            }
            else if (!get(i,j).equals("w") && !get(i,j).equals("m") && !get(i,j).equals("b")) {
                mine=this.getCurrentPlayer().getAMine();
                mine.setPosX(i);
                mine.setPosY(j);
                boolean isAlive = getPlayerAtPosition(i,j).setDamage(mine.getDamage());
                if (!isAlive) {
                    set(i,j,"v");
                    playerOnMap.remove(getPlayerAtPosition(i,j));
                    indexCurrentPlayer--;
                }
                nextIndex();
            }
            this.fireChangement(this);
        }
    }
    
    /**
     * pose une bombe (du joueur courant)
     * @param direction la direction dans laquelle le joueur courant pose sa bombe
     */
    @Override
    public void setBomb(String direction){
        int i=0,j=0;
        boolean succes=false;
        switch(direction){
            case "n":
                if(getCurrentPlayer().getPosX() > 0) {
                    i = getCurrentPlayer().getPosX() - 1;
                    j = getCurrentPlayer().getPosY();
                    succes=true;
                }
                break;
            case "s":
                if(getCurrentPlayer().getPosX() < nbLignes()){
                    i = getCurrentPlayer().getPosX() + 1;
                    j = getCurrentPlayer().getPosY();
                    succes=true;
                }
                break;
            case "w":
                if(getCurrentPlayer().getPosY() > 0){
                    i = getCurrentPlayer().getPosX();
                    j = getCurrentPlayer().getPosY() - 1;
                    succes=true;
                }
                break;
            case "e":
                if(getCurrentPlayer().getPosY() < nbColonnes()){
                    i = getCurrentPlayer().getPosX();
                    j = getCurrentPlayer().getPosY() + 1;
                    succes=true;
                }
                break;
            case "nw":
                if(getCurrentPlayer().getPosX() > 0 && getCurrentPlayer().getPosY() > 0) {
                    i = getCurrentPlayer().getPosX() - 1;
                    j = getCurrentPlayer().getPosY() - 1;
                    succes=true;
                }
                break;
            case "ne":
                if(getCurrentPlayer().getPosX() > 0 && getCurrentPlayer().getPosY() < nbColonnes()) {
                    i = getCurrentPlayer().getPosX() - 1;
                    j = getCurrentPlayer().getPosY() + 1;
                    succes=true;
                }
                break;
            case "sw":
                if(getCurrentPlayer().getPosX() < nbLignes() && getCurrentPlayer().getPosY() > 0) {
                    i = getCurrentPlayer().getPosX() + 1;
                    j = getCurrentPlayer().getPosY() - 1;
                    succes=true;
                }
                break;
            case "se":
                if(getCurrentPlayer().getPosX() < nbLignes() && getCurrentPlayer().getPosY() < nbColonnes()) {
                    i = getCurrentPlayer().getPosX() + 1;
                    j = getCurrentPlayer().getPosY() + 1;
                    succes=true;
                }
                break;
            default: return;
        }
        if(succes) {
            Bomb bomb =null;
            if(get(i, j).equals("v")) {
                bomb=this.getCurrentPlayer().getABomb();
                this.set(i, j, "b");
                bomb.setPosX(i);
                bomb.setPosY(j);
                this.bombOnBoard.put(Arrays.asList(i,j),bomb);
                nextIndex();
            }
            else if (!get(i,j).equals("w") && !get(i,j).equals("m") && !get(i,j).equals("b")) {
                bomb=this.getCurrentPlayer().getABomb();
                bomb.setPosX(i);
                bomb.setPosY(j);
                boolean isAlive = this.explose(bomb);
                if(!isAlive) {
                    playerOnMap.remove(getPlayerAtPosition(i, j));
                    set(i,j,"v");
                    this.indexCurrentPlayer--;
                }
                nextIndex();
            }
            this.fireChangement(this);
        }
    }

    /**
     * passe au joueur suivant
     */
    private void nextIndex() {
        if(playerOnMap.size()<=1)
            this.win=true;

        if (indexCurrentPlayer < playerOnMap.size() - 1)
            indexCurrentPlayer++;
        else {
            this.playBomb();
            indexCurrentPlayer = 0;
        }
        getCurrentPlayer().setShield(false);
    }

    /**
     * effectue un tir (du joueur courant)
     * @param direction la direction dans laquelle le joueur courant tire
     */
    @Override
    public void fire(String direction){
        Canon weaponCurrentPlayer =getCurrentPlayer().getCanon();
        int x=getCurrentPlayer().getPosX(),y=getCurrentPlayer().getPosY();
        int dist;
        boolean wall;
        switch (direction) {
            case "vertical"://fonctionne
                wall=false;
                dist = weaponCurrentPlayer.getDistance();
                weaponCurrentPlayer.fire();
                for (int i = 1; i <= dist;i++){
                    if (0 <= x - i) {
                        if(!wall && !get(x-i,y).equals("v") && !get(x-i,y).equals("m") && !get(x-i,y).equals("b") &&!get(x-i,y).equals("w")) {
                            boolean isAlive=getPlayerAtPosition(x-i,y).setDamage(weaponCurrentPlayer.getDamage());
                            if(!isAlive) {
                                playerOnMap.remove(getPlayerAtPosition(x-i, y));
                                set(x-i,y,"v");
                                if(playerOnMap.indexOf(getPlayerAtPosition(x-i,y))<=playerOnMap.indexOf(getCurrentPlayer()))
                                    this.indexCurrentPlayer--;
                            }

                        }
                        else if(get(x-i,y).equals("w")){
                            wall=true;
                        }
                    }
                }
                wall=false;
                for (int i = 1; i <= dist;i++) {
                    if (getCurrentPlayer().getPosX() + i <= nbColonnes() - 1) {
                        if (!wall && !get(x + i, y).equals("v") && !get(x + i, y).equals("m") && !get(x + i, y).equals("b") &&!get(x + i, y).equals("w")) {
                            boolean isAlive = getPlayerAtPosition(x + i, y).setDamage(weaponCurrentPlayer.getDamage());
                            if (!isAlive) {
                                playerOnMap.remove(getPlayerAtPosition(x + i, y));
                                set(x + i, y, "v");
                                if(playerOnMap.indexOf(getPlayerAtPosition(x+i,y))<=playerOnMap.indexOf(getCurrentPlayer()))
                                    this.indexCurrentPlayer--;
                            }

                        }
                        else if(get(x + i, y).equals("w"))
                            wall=true;
                    }
                }
                nextIndex();
                break;
            case "horizontal":
                wall=false;
                dist = weaponCurrentPlayer.getDistance();
                weaponCurrentPlayer.fire();
                for (int i = 1; i <= dist;i++){
                    if (0 <= getCurrentPlayer().getPosY() - i) {
                        if(!wall && !get(x,y-i).equals("v") && !get(x,y-i).equals("m") && !get(x,y-i).equals("b") && !get(x,y-i).equals("w")) {
                            boolean isAlive=getPlayerAtPosition(x,y-i).setDamage(weaponCurrentPlayer.getDamage());
                            if(!isAlive){
                                playerOnMap.remove(getPlayerAtPosition(x, y-i));
                                set(x,y-i,"v");
                                if(playerOnMap.indexOf(getPlayerAtPosition(x,y-i))<=playerOnMap.indexOf(getCurrentPlayer()))
                                    this.indexCurrentPlayer--;
                            }

                        }
                        else if(get(x,y-i).equals("w"))
                            wall=true;
                    }
                }
                wall=false;
                for (int i = 1; i <= dist;i++) {
                    if (getCurrentPlayer().getPosY() + i <= nbLignes() - 1) {
                        if (!wall && !get(x, y + i).equals("v") && !get(x, y + i).equals("m") && !get(x, y + i).equals("b") && !get(x, y + i).equals("w")) {
                            boolean isAlive = getPlayerAtPosition(x, y + i).setDamage(weaponCurrentPlayer.getDamage());
                            if (!isAlive) {
                                playerOnMap.remove(getPlayerAtPosition(x, y + i));
                                set(x, y + i, "v");
                                if(playerOnMap.indexOf(getPlayerAtPosition(x,y+i))<=playerOnMap.indexOf(getCurrentPlayer()))
                                    this.indexCurrentPlayer--;
                            }

                        }
                        else if(get(x, y + i).equals("w"))
                            wall=true;
                    }
                }
                nextIndex();
                break;
        }
        this.fireChangement(this);
    }

    /**
     * renvoie le joueur present dans la case de coordonnees (i,j)
     * @param i le numero de la ligne
     * @param j le numero de la colonne
     * @return le joueur present dans la case de coordonnees (i,j)
     */
    private Player getPlayerAtPosition(int i,int j){
        for(Player p:playerOnMap){
            if(p.getPosY()==j && p.getPosX()==i)
                return  p;
        }
        return null;
    }

    /**
     * decremente le nombre de tours restants avant que les bombes explosent
     */
    private void playBomb() {

        for (Map.Entry<List<Integer>, Bomb> entry : bombOnBoard.entrySet()) {
            List<Integer> key = entry.getKey();
            Bomb bomb = entry.getValue();
            bomb.decrement();
            if (bomb.toursRestants() == 0) {
                explose(bomb);
                set(bomb.getPosX(), bomb.getPosY(), "v");
                this.bombOnBoard.remove(Arrays.asList(key));
            }
        }
    }

    /**
     * le joueur courant ne fait rien durant ce tour
     */
    @Override
    public void doNothing(){
        nextIndex();
    }

    /**
     * Fait exploser une bombe.
     * (dans le cas ou un joueur se trouve sur la meme case que la bombe,
     *  la methode indique si celui-ci est toujours vivant ou non)
     * @param bomb la bombe qui explose
     * @return false si un joueur qui se trouvait dans la case de la bombe a ete tue, true sinon
     */
    public boolean explose(Bomb bomb) {
        if (bomb.getPosX() - 1 > 0) {
            Player p = getPlayerAtPosition(bomb.getPosX() - 1, bomb.getPosY());
            if (p != null) {
                boolean isAlive = p.setDamage(bomb.getDamage());
                if (!isAlive) {
                    set(bomb.getPosX() - 1, bomb.getPosY(), "v");
                    if(playerOnMap.indexOf(getPlayerAtPosition(bomb.getPosX() - 1,bomb.getPosY()))<=playerOnMap.indexOf(getCurrentPlayer()))
                        this.indexCurrentPlayer--;
                    playerOnMap.remove(p);
                }
            }
        } else if (bomb.getPosY() + 1 < nbColonnes()) {
            Player p = getPlayerAtPosition(bomb.getPosX(), bomb.getPosY() + 1);
            if (p != null) {
                boolean isAlive = p.setDamage(bomb.getDamage());
                if (!isAlive) {
                    set(bomb.getPosX(), bomb.getPosY() + 1, "v");
                    if(playerOnMap.indexOf(getPlayerAtPosition(bomb.getPosX() ,bomb.getPosY()+1))<=playerOnMap.indexOf(getCurrentPlayer()))
                        this.indexCurrentPlayer--;
                    playerOnMap.remove(p);
                }
            }
        } else if (bomb.getPosX() + 1 < nbLignes()) {
            Player p = getPlayerAtPosition(bomb.getPosX() + 1, bomb.getPosY());
            if (p != null) {
                boolean isAlive = p.setDamage(bomb.getDamage());
                if (!isAlive) {
                    set(bomb.getPosX() + 1, bomb.getPosY(), "v");
                    if(playerOnMap.indexOf(getPlayerAtPosition(bomb.getPosX() + 1,bomb.getPosY()))<=playerOnMap.indexOf(getCurrentPlayer()))
                        this.indexCurrentPlayer--;
                    playerOnMap.remove(p);
                }
            }
        } else if (bomb.getPosY() - 1 > 0) {
            Player p = getPlayerAtPosition(bomb.getPosX(), bomb.getPosY() - 1);
            if (p != null) {
                boolean isAlive = p.setDamage(bomb.getDamage());
                if (!isAlive) {
                    set(bomb.getPosX(), bomb.getPosY() - 1, "v");
                    if(playerOnMap.indexOf(getPlayerAtPosition(bomb.getPosX() ,bomb.getPosY()-1))<=playerOnMap.indexOf(getCurrentPlayer()))
                        this.indexCurrentPlayer--;
                    playerOnMap.remove(p);
                }
            }
        } else if (bomb.getPosX() - 1 > 0 && bomb.getPosY() + 1 < nbColonnes()) {
            Player p = getPlayerAtPosition(bomb.getPosX() - 1, bomb.getPosY() + 1);
            if (p != null) {
                boolean isAlive = p.setDamage(bomb.getDamage());
                if (!isAlive) {
                    set(bomb.getPosX() + 1, bomb.getPosY(), "v");
                    if(playerOnMap.indexOf(getPlayerAtPosition(bomb.getPosX() + 1,bomb.getPosY()))<=playerOnMap.indexOf(getCurrentPlayer()))
                        this.indexCurrentPlayer--;
                    playerOnMap.remove(p);
                }
            }
        } else if (bomb.getPosY() + 1 < nbColonnes() && bomb.getPosX() + 1 < nbLignes()) {
            Player p = getPlayerAtPosition(bomb.getPosX() + 1, bomb.getPosY() + 1);
            if (p != null) {
                boolean isAlive = p.setDamage(bomb.getDamage());
                if (!isAlive) {
                    set(bomb.getPosX() + 1, bomb.getPosY() + 1, "v");
                    if(playerOnMap.indexOf(getPlayerAtPosition(bomb.getPosX() + 1,bomb.getPosY()+1))<=playerOnMap.indexOf(getCurrentPlayer()))
                        this.indexCurrentPlayer--;
                    playerOnMap.remove(p);
                }
            }
        } else if (bomb.getPosX() + 1 < nbLignes() && bomb.getPosY() - 1 > 0) {
            Player p = getPlayerAtPosition(bomb.getPosX() + 1, bomb.getPosY() - 1);
            if (p != null) {
                boolean isAlive = p.setDamage(bomb.getDamage());
                if (!isAlive) {
                    set(bomb.getPosX() + 1, bomb.getPosY() - 1, "v");
                    if(playerOnMap.indexOf(getPlayerAtPosition(bomb.getPosX() + 1,bomb.getPosY()-1))<=playerOnMap.indexOf(getCurrentPlayer()))
                        this.indexCurrentPlayer--;
                    playerOnMap.remove(p);
                }
            }
        } else if (bomb.getPosY() - 1 > 0 && bomb.getPosX() - 1 > 0) {
            Player p = getPlayerAtPosition(bomb.getPosX() + 1, bomb.getPosY());
            if (p != null) {
                boolean isAlive = p.setDamage(bomb.getDamage());
                if (!isAlive) {
                    set(bomb.getPosX() - 1, bomb.getPosY() - 1, "v");
                    if(playerOnMap.indexOf(getPlayerAtPosition(bomb.getPosX() - 1,bomb.getPosY()-1))<=playerOnMap.indexOf(getCurrentPlayer()))
                        this.indexCurrentPlayer--;
                    playerOnMap.remove(p);
                }
            }
        }
        Player p=getPlayerAtPosition(bomb.getPosX(),bomb.getPosY());
        if(p!=null){
            boolean isAlive=p.setDamage(bomb.getDamage());
            if(!isAlive){
                set(bomb.getPosX(),bomb.getPosY(),"v");
                if(playerOnMap.indexOf(getPlayerAtPosition(bomb.getPosX(),bomb.getPosY()))<=playerOnMap.indexOf(getCurrentPlayer()))
                    this.indexCurrentPlayer--;
                playerOnMap.remove(p);
            }
            return isAlive;
        }
        return true;
    }

    /**
     * fait exploser une mine
     * @param m la mine qui explose
     * @return true si le joueur touche par la mine est toujours en vie, false sinon
     */
    public boolean explose(Mine m){
        set(m.getPosX(),m.getPosY(),"v");
        return this.getCurrentPlayer().setDamage(m.getDamage());
    }

    /**
     * active le bouclier sur le joueur courant
     */
    @Override
    public void activeShield(){
        boolean isAlive=getCurrentPlayer().setDamage(10);
        getCurrentPlayer().setShield(true);
        if(!isAlive){
            set(getCurrentPlayer().getPosX(),getCurrentPlayer().getPosY(),"v");
            playerOnMap.remove(getCurrentPlayer());
        }
    }

    /**
     * renvoie la mine presente dans la case de coordonnees (i,j)
     * @param i le numero de la ligne
     * @param j le numero de la colonne
     * @return la mine presente dans la case de coordonnees (i,j)
     */
    @Override
    public Mine getMine(int i, int j) {
        return mineOnBoard.get(Arrays.asList(i,j));
    }

    /**
     * renvoie la bombe presente dans la case de coordonnees (i,j)
     * @param i le numero de la ligne
     * @param j le numero de la case
     * @return la bombe presente dans la case de coordonnees (i,j)
     */
    @Override
    public Bomb getBomb(int i, int j) {
        return bombOnBoard.get(Arrays.asList(i,j));
    }

    /**
     * indique si le jeu est termine
     * @return true si le jeu est fini, false sinon
     */
    @Override
    public boolean isWin() {
        return this.win;
    }

}
