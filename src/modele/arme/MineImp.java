package modele.arme;

/**
 * classe implementant une mine
 */

public class MineImp implements Mine {
    /**
     *   contient les degats que va infliger la mine lors de son explosion.
     */
    private int damage;
    /**
     *   contient le numero de la ligne ou se situe la mine
     */
    private int posX;
    /**
     *   contient le numero de la colonne ou se situe la mine
     */
    private int posY;
    /**
     *   indique si la mine est visible pour les autres joueurs sous forme d'un booleen.
     */
    private boolean visible;

    /**
     *   constructeur.
     *
     *   @param damage (requis) degats infliges par l'explosion de la bombe.
     *   @param visible (requis) booleen indiquant si la mine est visible par les autres joueurs.
     */
    public MineImp(int damage, boolean visible) {
        this.damage = damage;
        this.posX = 0;
        this.posY = 0;
        this.visible = visible;
    }

    /**
     *   retourne le nombre de degats qu'inflige la mine a l'explosion.
     *   @return les degats infliges par la mine quand elle explose.
     */
    @Override
    public int getDamage() {
        return damage;
    }

    /**
     * indique si la mine est visible ou non pour les autres joueurs.
     * @return un booleen qui est a true si la mine est visible, a false si elle est invisible.
     */
    @Override
    public boolean getVisibility() {
        return visible;
    }

    /**
     *   regle la ligne sur laquelle on positionne la mine.
     *   @param x numero de la ligne ou doit etre positionnee la mine.
     */
    @Override
    public void setPosX(int x) {
        this.posX=x;
    }

    /**
     *   regle la colonne sur laquelle on positionne la mine.
     *   @param y numero de la colonne ou doit etre positionnee la mine.
     */
    @Override
    public void setPosY(int y) {
        this.posY=y;
    }

    /**
     *   retourne la ligne sur laquelle est positionnee la mine.
     *   @return la ligne sur laquelle est positionnee la mine.
     */
    @Override
    public int getPosX() {
        return posX;
    }

    /**
     *   retourne la colonne sur laquelle est positionnee la mine.
     *   @return la colonne sur laquelle est positionnee la mine.
     */
    @Override
    public int getPosY() {
        return posY;
    }


}
