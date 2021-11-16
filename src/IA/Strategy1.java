package IA;

import utilitaire.Ressources;

import java.util.HashMap;
import java.util.Map;

/**
 * classe implementant une strategie pour l'IA
 */
public class Strategy1 implements Strategy {
    
    /**
     * constructeur
     */
    public Strategy1(){
    }

    /**
     * renvoie une action a effectuer
     * @return l'action a effectuer
     */
    @Override
    public Action getAction() {
        Map<String,String> actionArg=new HashMap<>();
        String actString= Ressources.LISTOFACTION.get((int)(Math.random()*Ressources.LISTOFACTION.size())); //On Recupere aléatoirement une action
        if(!actionNeedArg(actString))
            actionArg.put(actString,"");
        else{
            String arg=Ressources.ACTIONARGUMENT.get(actString).get((int)Math.random()*Ressources.ACTIONARGUMENT.get(actString).size());
            actionArg.put(actString,arg);
        }
        return new Action(actionArg);
    }
    
    /**
     * indique si l'action requiert une direction
     * @param actString l'action
     * @return true si l'action requiert une direction, false sinon
     */
    private boolean actionNeedArg(String actString){
        return !(actString.equals("doNothing") || actString.equals("activeShield"));
    }
}
