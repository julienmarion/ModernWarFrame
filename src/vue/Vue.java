
package vue;

import modele.jeu.ModelJeu;
import modele.jeu.ModelJeuEditable;
import modele.player.Player;
import utilitaire.EcouteurModele;

/**
 *C'est une classe qui a le role d'écouteur pour visualiser le jeu en ligne de commande.
 */
public class Vue implements EcouteurModele{
    /**
    * model du jeu ecoute
    */
    private ModelJeuEditable mdl;
    
    /**
    * Constructeur
    * @param model le modele du jeu ecoute
    */
    public Vue(ModelJeuEditable mdl){
        affichePlateau(mdl);
        this.mdl=mdl;
    }
    
    @Override
    public void MiseAJourModele(Object e) {
        this.mdl= (ModelJeuEditable) e;
        affichePlateau((ModelJeu) e);
    }
    /**
    * affiche le plateau en ligne de commande
    * @param mdlJeu le model du jeu
    */
    public void affichePlateau(ModelJeu mdlJeu){
        for(int i = 0; i<mdlJeu.nbLignes(); i++){
            for(int j = 0; j<mdlJeu.nbColonnes(); j++){
                System.out.print(mdlJeu.get(i,j));
            }
            System.out.println();
        }

        System.out.println("\nStatistique joueur");
        System.out.println("Joueur |   pts de vie  |  nb bombes   |   nbMines  |   mun    |");
        System.out.println("-------|---------------|--------------|------------|----------|");
        for(Player p:mdlJeu.getAllPlayer()){
            System.out.print(p+"      |   ");
            System.out.print(p.getEnergy()+"         |      ");
            System.out.print(p.getBomb()+"       |       ");
            System.out.print(p.getMine()+"    |     ");
            System.out.print(p.getCanon().getMunition()+"   |");
            System.out.println("\n-------|---------------|--------------|------------|----------|");
        }
        System.out.println("end of turn");
        System.out.println("-------------------------------------------------------------------------------" +
                "------------------------------------------------------------------------------------------" +
                "-------------------------------------------------------------------------------------------");
    }
}
