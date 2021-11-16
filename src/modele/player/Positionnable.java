package modele.player;

/**
 * interface d'objet positionnable servant a renvoyer ses coordonnees
 */
public interface Positionnable {
    
    /**
    * renvoie le numero de la ligne ou est situe l'objet
    */
    int getPosX();
    
    /**
    * renvoie le numero de la colonne ou est situe l'objet
    */
    int getPosY();
}
