
package modele.player;

/**
 * classe de mur
 */
public class Wall implements Positionnable {
    
    /**
     * le numero de la ligne du mur
     */
    private int posX ;
    
    /**
     * le numero de la colonne du mur
     */
    private int posY ;
    
    /**
     * constructeur
     * @param posX le numero de la ligne du mur
     * @param posY le numero de la colonne du mur
     */
    public Wall(int posX, int posY){
        this.posX = posX ;
        this.posY = posY ;
    }

    /**
     * renvoie la ligne ou est situe le mur
     * @return la ligne ou est situe le mur
     */
    @Override
    public int getPosX() {
        return this.posX;
    }

    /**
     * renvoie la colonne ou est situe le mur
     * @return la colonne ou est situe le mur
     */
    @Override
    public int getPosY() {
        return this.posY;
    }
    
}
