package modele.arme;

import utilitaire.Ressources;
/**
 * classe servant a construire un canon, en precisant ou non ses caracteristiques
 */
public class CanonBuilder {
    /**
     * contient les degats infliges par un tir du canon
     */
    private int damage= Ressources.DEFAULTDAMMAGE;
    /**
     * contient la distance a laquelle tire le canon
     */
    private int distanceFire=Ressources.DEFAULTDISTANCEFIRE;
    /**
     * contient le nombre de munitions dont dispose le canon
     */
    private int munition=Ressources.DEFAULTMUNITION;
    /**
     * renseigne les degats qu'infligent les tirs du canon
     * @param damage les degats qu'infligera le canon a chaque tir
     * @return le builder du canon en tenant compte du montant de degats des tirs fourni
     */
    public CanonBuilder setDamage(int damage) {
        this.damage = damage;
        return this;
    }
    /**
     * renseigne la distance a laquelle le canon tire
     * @param distanceFire le nombre de cases correspondant a la distance a laquelle le canon tirera
     * @return le builder du canon en tenant compte du nombre de la distance de tir fournie
     */
    public CanonBuilder setDistanceFire(int distanceFire) {
        this.distanceFire = distanceFire;
        return this;
    }
    /**
     * renseigne le nombre de munitions dont dispose le canon
     * @param munition le nombre de munitions qu'aura le canon
     * @return le builder du canon en tenant compte du nombre du nombre de munitions fourni
     */
    public CanonBuilder setMunition(int munition) {
        this.munition = munition;
        return this;
    }
    /**
     * construit le canon
     * @return le canon construit
     */
    public Canon build(){
        return new CanonImp(distanceFire,damage,munition);
    }

}
