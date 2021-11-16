package modele.arme;

/**
 * interface de bombe contenant ses caractéristiques
 */

public interface Bomb extends Explosif {

    /**
     * indique si la bombe est visible ou non pour les autres joueurs.
     * @return un booleen qui est a true si la bombe est visible, a false si elle est invisible.
     */
    boolean getVisibility();
    
    /**
     * renvoie le nombre de tours restants avant que la bombe explose.
     * @return un entier qui indique le nombre de tours restant avant que la bombe explose.
     */
    int toursRestants();
    
    /**
     * reduit de 1 le nombre de tours restants de la bombe
     */
    void decrement();

}
