
package modele.arme;

/**
 *  classe implementant une bombe
 */
public class BombImp implements Bomb {

    /**
     *   contient les degats que va infliger la bombe lors de son explosion.
     */
    private int damage;
    /**
     *   contient le numero de la ligne ou se situe la bombe
     */
    private int posX;
    /**
     *   contient le numero de la colonne ou se situe la bombe
     */
    private int posY;
    /**
     *   indique si la bombe est visible pour les autres joueurs sous forme d'un booleen
     */
    private boolean visible;
    /**
     *   contient le nombre de tour restant avant l'explosion de la bombe
     */
    private int tours;

    /**
     *   constructeur.
     *
     *   @param damage (requis) degats infliges par l'explosion de la bombe. Doit etre renseigne.
     *   @param visible (requis) booleen indiquant si la bombe est visible par les autres joueurs.
     *   @param tours (requis) nombre de tours que doit prendre la bombe avant d'exploser.
     */
    public BombImp(int damage, boolean visible, int tours) {
        this.damage = damage;
        this.posX = 0;
        this.posY = 0;
        this.visible = visible;
        this.tours = tours;
    }

    /**
     *   retourne le nombre de degats qu'inflige la bombe a l'explosion.
     *   @return les degats infliges par la bombe quand elle explose.
     */
    @Override
    public int getDamage() {
        return damage;
    }

    /**
     * indique si la bombe est visible ou non pour les autres joueurs.
     * @return un booleen qui est a true si la bombe est visible, a false si elle est invisible.
     */
    @Override
    public boolean getVisibility() {
        return visible;
    }

    /**
     * renvoie le nombre de tours restants avant que la bombe explose.
     * @return un entier qui indique le nombre de tours restant avant que la bombe explose.
     */
    @Override
    public int toursRestants(){
        return tours;
    }

    /**
     * reduit de 1 le nombre de tours restants de la bombe.
     */
    @Override
    public void decrement(){
        this.tours-- ;
    }

    /**
     *   regle la ligne sur laquelle on positionne la bombe.
     *   @param i numero de la ligne ou doit etre positionnee la bombe.
     */
    @Override
    public void setPosX(int i) {
        this.posX=i;
    }

    /**
     *   regle la colonne sur laquelle on positionne la bombe.
     *   @param j numero de la colonne ou doit etre positionnee la bombe.
     */
    @Override
    public void setPosY(int j) {
        this.posY=j;
    }

    /**
     *   retourne la ligne sur laquelle est positionnee la bombe.
     *   @return la ligne sur laquelle est positionnee la bombe.
     */
    @Override
    public int getPosX() {
        return posX;
    }

    /**
     *   retourne la colonne sur laquelle est positionnee la bombe.
     *   @return la colonne sur laquelle est positionnee la bombe.
     */
    @Override
    public int getPosY() {
        return posY;
    }
}
