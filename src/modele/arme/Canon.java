package modele.arme;

/**
 * interface d'arme contenant ses caracteristiques
 */

public interface Canon {
    
    /**
     * renvoie la distance a laquelle l'arme tire (nombre de cases)
     * @return un entier conteant la distance jusqu'a laquelle un canon peut toucher
     */
    int getDistance();
    
    /**
     * renvoie le nombre de degats infliges par l'arme
     * @return un entier contenant les dommage qu'inflige le canon
     */
    int getDamage();
    
    /**
     * renvoie le nombre de munitions de l'arme
     * @return un entier contenant le nombre de munition restant
     */
    int getMunition();

    /**
     * decremente les munitions du canon.
     */
    void fire();
}
