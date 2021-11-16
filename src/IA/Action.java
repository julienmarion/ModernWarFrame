package IA;

import java.util.Map;
import java.util.Set;

/**
 * classe contenant les actions possibles pour un joueur
 */

public class Action {
    
    /**
     * contient une map des actions et de leurs differents arguments
     */
    private Map<String,String> action;
    
    public Action(Map<String,String> action){
        this.action=action;
    }
    
    /**
     * indique si l'action est un deplacement
     * @return true si l'action est un deplacement, false sinon
     */
    public boolean actionIsMove(){
        return action.containsKey("move");
    }
    
    /**
     * indique si l'action est un posage de mine
     * @return true si l'action est un posage de mine, false sinon
     */
    public boolean actionIsMine(){
        return action.containsKey("setMine");
    }
    
    /**
     * indique si l'action est un posage de bombe
     * @return true si l'action est un posage de bombe, false sinon
     */
    public boolean actionIsBomb(){
        return action.containsKey("setBomb");
    }
    
    /**
     * indique si l'action est un tir
     * @return true si l'action est un tir, false sinon
     */
    public boolean actionIsFire(){
        return action.containsKey("fire");
    }
    
    /**
     * indique si l'action est de ne rien faire
     * @return true si l'action est de ne rien faire, false sinon
     */
    public boolean actionIsNothing(){
        return action.containsKey("doNothing");
    }
    
    /**
     * indique si l'action est une activation de bouclier
     * @return true si l'action est une activation de bouclier, false sinon
     */
    public boolean actionIsShield(){
        return action.containsKey("activeShield");
    }
    
    /**
     * renvoie une direction dans laquelle l'IA va effectuer l'action
     * @return la direction
     */
    public String getArg(){
        return action.get(action.keySet().iterator().next());
    }

}
