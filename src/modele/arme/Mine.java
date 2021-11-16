package modele.arme;

/**
 * interface de mine contenant ses caracteristiques
 */
public interface Mine extends Explosif {


	/**
	 * indique si la mine est visible ou non pour les autres joueurs
	 * @return un booleen qui est a true si la mine est visible, a false si elle est invisible.
	 */
	boolean getVisibility();
}
