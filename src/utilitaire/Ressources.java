package utilitaire;

import java.util.*;

/**
 * classe contenant les variables globales servant a definir les parametres par defaut du plateau
 */
public class Ressources {
    /**
     * nombre le ligne par defaut
     */
    public final static int DEFAULTNBLIGNES = 10;
    /**
     * nombre de colonne par defaut
     */
    public final static int DEFAULTNBCOLONNES = 10;
    /**
     * boolean pour dire si on utilise des mur ou non par defaut
     */
    public final static boolean DEFAULTWITHWALL=true;
    /**
     * le nombre de tour avant que le bomb explose par defaut
     */
    public static final int DEFAULTNBTOURSBOMB = 3;
    /**
     * distance par defaut pour un tire avec une arme
     */
    public static final int DEFAULTDISTANCEFIRE=3;
    /**
     * degats d'une arme par defaut
     */
    public static final int DEFAULTDAMMAGE=10;
    /**
     * le nombre de munition par defaut
     */
    public static final int DEFAULTMUNITION=20;
    /**
     * boolean pour savoir si les explosifs sont visible ou non par defaut
     */
    public static final boolean VISIBILITYEXPLOSIF=true;
    
    /**
     * energie des joueurs par defaut
     */
    public static final int DEFAULTENERGY=100;
    /**
     * nombre de mine par defaut
     */
    public static final int DEFAULTNBMINE=2;
    /**
     * nombre de bombe par defaut
     */
    public static final int DEFAULTNBBOMB=2;


    /**
     * ArrayList sur laquelle on ajoute les actions possibles
     */
    public final static List<String> LISTOFACTION=new ArrayList<>();
    static{
        LISTOFACTION.add("move");
        LISTOFACTION.add("setMine");
        LISTOFACTION.add("setBomb");
        LISTOFACTION.add("fire");
        LISTOFACTION.add("doNothing");
        LISTOFACTION.add("activeShield");
    }
    
    /**
     * HashMap qui contient les arguments possible pour chaque actions
     */
    public final static Map<String,List<String>> ACTIONARGUMENT = new HashMap<>();
    static {
        ACTIONARGUMENT.put("move", Arrays.asList("up","down","left","right"));
        ACTIONARGUMENT.put("fire", Arrays.asList("horizontal","vertical"));
        ACTIONARGUMENT.put("setMine", Arrays.asList("n","s","w","e","ne","nw","se","sw"));
        ACTIONARGUMENT.put("setBomb", Arrays.asList("n","s","w","e","ne","nw","se","sw"));
    }
}
