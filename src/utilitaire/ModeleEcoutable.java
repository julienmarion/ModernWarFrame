package utilitaire;

/**
 * interface de modele ecoutable pour le pattern mvc
 */
public interface ModeleEcoutable {
    
	/**
	* ajoute un ecouteur au modele
	* @param e un ecouteur
	*/
    public void ajoutEcouteur(EcouteurModele e);
    
	/**
	* retire un ecouteur du modele
	* @param e un ecouteur
	*/
    public void retraitEcouteur(EcouteurModele e);
    
    /**
	* previent le modele qu'il y a eu un changement pour qu'il soit mis a jour
	* @param e le modele a prevenir
	*/
    void fireChangement(Object e);
}
