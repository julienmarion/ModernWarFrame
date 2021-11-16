package modele.arme;

import modele.player.Positionnable;

public interface Explosif extends Positionnable {

    /**
     * retourne le nombre de degats qu'inflige l'explosif a l'explosion.
     * @return un entier indiquant les dommage infligée par l'objet implémentant explosif
     */
    int getDamage();

    /**
     *   regle la ligne sur laquelle on positionne l'explosif.
     *	@param x numero de la ligne ou doit etre positionne l'explosif
     */
    void setPosX(int x);

    /**
     *   regle la colonne sur laquelle on positionne l'explosif.
     *	@param y numero de la colonne ou doit etre positionne l'explosif
     */
    void setPosY(int y);
}
