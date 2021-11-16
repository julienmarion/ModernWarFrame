/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modele.arme.CanonBuilder;
import modele.player.Player;
import modele.player.PlayerBuilder;

/**
 * Classe permettant d'extraire les infos recuperer par XmlReader
 */
public class ExtractPlayerFromXml extends XmlReader {
    /**
     * Liste des joueurs avec leurs stats
     */
    private ArrayList<Player> playerOnMap=new ArrayList<>();
    
    /**
     * Constructeur
     * @param map la HashMap que l'on a cree avec XmlReader
     */
    public ExtractPlayerFromXml(HashMap<List<Integer>,String> map){
        XmlReader xmlreader=new XmlReader();
        List<HashMap<String, String>> listOfConfig =xmlreader.getBalisInfo();
        PlayerBuilder playerBuild=new PlayerBuilder();
        CanonBuilder canonBuild=new CanonBuilder();

        for (Map.Entry< List<Integer>,String> entry : map.entrySet()) {
            String type=entry.getValue();
            List<Integer> coor=entry.getKey();

            playerBuild.setPosX(coor.get(0));       //x
            playerBuild.setPosY(coor.get(1));       //y

            for(HashMap<String,String> x:listOfConfig){
                if(x.get("PlayerTypeid").equals(type)){
                    for(String configInfo: x.keySet()){
                        switch(configInfo){
                            case "Minevisible":
                                if(x.get(configInfo).equals("false"))
                                    playerBuild=playerBuild.setVisibilityExplosif(false);
                                break;
                            case "Weapondamage":
                                canonBuild=canonBuild.setDamage(Integer.valueOf(x.get(configInfo)));
                                break;
                            case "Weaponmunition":
                                canonBuild=canonBuild.setMunition(Integer.valueOf(x.get(configInfo)));
                                break;
                            case "Bombevisible":
                                if(x.get(configInfo).equals("false"))
                                    playerBuild=playerBuild.setVisibilityExplosif(false);
                                break;
                            case "Weapondist":
                                canonBuild=canonBuild.setDistanceFire(Integer.valueOf(x.get(configInfo)));
                                break;
                            case "Energyquantity":
                                playerBuild=playerBuild.setEnergy(Integer.valueOf(x.get(configInfo)));
                                break;
                            case "Minequantity":
                                playerBuild=playerBuild.setNbMine(Integer.valueOf(x.get(configInfo)));
                                break;
                            case "Bombequantity":
                                playerBuild= playerBuild.setNbBomb((Integer.valueOf(x.get(configInfo))));
                                break;
                        }
                    }
                    playerBuild.setCanon(canonBuild.build());
                    playerOnMap.add(playerBuild.build());
                    playerBuild=new PlayerBuilder();
                    canonBuild=new CanonBuilder();
                }
            }
        }
    }
    /**
     * @return la list des joueurs avec leurs stats
     */
    public List<Player> getList(){
     return playerOnMap;
    }
    
}
