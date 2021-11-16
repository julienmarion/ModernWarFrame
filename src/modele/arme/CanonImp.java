package modele.arme;

/**
 * classe implementant une arme
 */

public class CanonImp implements Canon {
    /**
     *   contient la valeur de la distance a laquelle peut tirer le canon.
     */
    private int distanceFire;
    /**
     *   contient la valeur des degats infliges par le canon.
     */
    private int damageWeapon;
    /**
     *   contient le nombre de munition dont dispose le canon.
     */
    private int mun=0;

    /**
     *   constructeur.
     *
     *   @param distanceFire (requis) distance a laquelle peut tirer le joueur avec le canon.
     *   @param damageWeapon (requis) degats infliges par le tir du canon.
     *   @param mun (requis) definie le nombre de munition dont dispose le canon au depart.
     */
    public CanonImp(int distanceFire, int damageWeapon, int mun){
        this.distanceFire = distanceFire;
        this.damageWeapon = damageWeapon;
        this.mun=mun;
    }

    /**
     * renvoie la distance a laquelle le canon tire (nombre de cases).
     *  @return la distance a laquelle le conon tire (nombre de cases).
     */
    @Override
    public int getDistance() {
        return this.distanceFire;
    }

    /**
     * renvoie le nombre de degats infliges par le canon.
     *  @return le nombre de degats infliges par le canon.
     */
    @Override
    public int getDamage() {
        return this.damageWeapon;
    }

    /**
     * renvoie le nombre de munitions du canon.
     *  @return le nombre de munition du canon.
     */
    @Override
    public int getMunition() {
        return mun;
    }

    /**
     * decremente les munitions du canon.
     */
    @Override
    public void fire() {
        this.mun--;
    }


}
