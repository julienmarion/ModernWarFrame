/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modernwarframe;

import IA.IA;
import IA.Strategy;
import IA.Strategy1;
import modele.arme.Bomb;
import modele.arme.CanonImp;
import modele.jeu.*;
import modele.player.Player;
import modele.player.PlayerImp;
import utilitaire.AbstractModeleEcoutable;
import utilitaire.Ressources;
import utilitaire.XmlReader;
import vue.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import modele.player.Wall;
import utilitaire.ExtractPlayerFromXml;

/**
 *
 * @author 21609103
 */
public class ModernWarframe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here




        HashMap<List<Integer>,String> test1=new HashMap<>();
        test1.put(Arrays.asList(0,0),"Saboteur");
        test1.put(Arrays.asList(0,9),"Blinde");
        test1.put(Arrays.asList(9,0),"Blinde");
        test1.put(Arrays.asList(9,9),"Blinde");

        ExtractPlayerFromXml xmlPlayer=new ExtractPlayerFromXml(test1);

       List<Player> playerOnMap=xmlPlayer.getList();

       ArrayList<Wall> listeWall;
        if(Ressources.DEFAULTWITHWALL) {
            WallBuilder wb = new WallBuilder(10, 10, 0.5);
            listeWall = wb.build();
        }
        try {
            ModelJeuEditImp model= (ModelJeuEditImp) new PlateauBuilder(playerOnMap,listeWall).build();
            Strategy strategy=new Strategy1();
            IA ia=new IA(strategy, model);
            Interface_graphique vueGen=new Interface_graphique(model,ia);
            model.ajoutEcouteur(vueGen);
            ArrayList<Interface_graphique> vueJoueur=new ArrayList<>();
            for(Player p:playerOnMap){
                vueJoueur.add(new Interface_graphique(new ProxyModele(model,p)));
            }

            //Vue vu1=new Vue((ModelJeuEditable) model);                // Vue en ligne de Commande
            //((AbstractModeleEcoutable)model).ajoutEcouteur(vu1);      // Affichant la vue général

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
