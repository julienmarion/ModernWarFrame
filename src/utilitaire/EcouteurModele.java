package utilitaire;

/**
 * Interface permettant de recuperer un lien sur un modele pour le pattern mvc
 */
public interface EcouteurModele {
	/**
	* met a jour un ecouteur
	* @param e un ecouteur
	*/
    public void MiseAJourModele(Object e);
}
