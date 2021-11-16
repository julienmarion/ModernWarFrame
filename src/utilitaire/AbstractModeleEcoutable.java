package utilitaire;

import java.util.ArrayList;

/**
 * classe implementant un modele ecoutable pour le pattern mvc
 */
public abstract class AbstractModeleEcoutable implements ModeleEcoutable{
	
	/**
	* variable contenant une liste d'ecouteurs
	*/
    private ArrayList<EcouteurModele> e=new ArrayList<>();
	
	/**
	* ajoute un ecouteur a la liste des ecouteurs
	* @param ecout l'ecouteur a ajouter
	*/
    @Override
    public void ajoutEcouteur(EcouteurModele ecout) {
        e.add(ecout);
    }
	
	/**
	* retire un ecouteur de la liste des ecouteurs
	* @param ecout l'ecouteur a retirer
	*/
    @Override
    public void retraitEcouteur(EcouteurModele ecout) {
        e.remove(ecout);
    }
	
	/**
	* previent le modele qu'il y a eu un changement pour qu'il soit mis a jour
	* @param model le modele a prevenir
	*/
    @Override
    public void fireChangement(Object model){
        for(EcouteurModele ei:e){
            ei.MiseAJourModele(model);
        }
    }
}
