package IA;

import modele.jeu.ModelJeuEditable;

/**
 * classe contenant une intelligence artificielle remplaçant un joueur
 */
public class IA {
    
    /**
     * contient la strategie utilisee par l'IA
     */
    private final Strategy strat;
    
    /**
     * contient le modele sur lequel l'IA agit
     */
    private ModelJeuEditable mdl;
    
    /**
     * constructeur
     * @param strat la strategie utilisee par l'IA
     * @param mdl le modele sur lequel l'IA agit
     */
    public IA(Strategy strat, ModelJeuEditable mdl){
        this.strat=strat;
        this.mdl=mdl;
    }
    
    /**
     * effectue une action
     */
    public void nextPlay(){
        Action act=this.strat.getAction();
        if(act.actionIsBomb()){
            this.mdl.setBomb(act.getArg());
        }
        else if(act.actionIsFire()){
            this.mdl.fire(act.getArg());
        }
        else if(act.actionIsMine()){
            this.mdl.setMine(act.getArg());
        }
        else if(act.actionIsMove()){
            this.mdl.move(act.getArg());
        }
        else if(act.actionIsNothing()){
            this.mdl.doNothing();
        }
        else if(act.actionIsShield()){
            this.mdl.activeShield();
        }
    }
}
