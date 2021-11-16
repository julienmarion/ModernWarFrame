/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaire;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe permettant de recuperer les donnees d'un fichier XML en le parsant
 */
public class XmlReader extends DefaultHandler{
    
    /**
     * List pour recuperer les balises avec leur contenu dans le XML
     */
    private List<HashMap<String, String>> balisInfo=new ArrayList<>();
    /**
     * HashMap pour recuperer les infos contenu dans une balise 
     */
    private HashMap<String,String> innerMainbalise;
    
    /**
     * Contructeur
     */
    public XmlReader(){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try
        {
// puis on obtient une instance de parseur à partir de cette usine.
            SAXParser saxParser = factory.newSAXParser();
// Enfin, on lance le parsing :
            saxParser.parse("src/utilitaire/Players.xml", this);
        } catch(Exception e){e.printStackTrace();}

    }
    
    /**
     * @return la list des infos de chaque balise 
     */
    public List<HashMap<String, String>> getBalisInfo() {
        return balisInfo;
    }
    @Override
    public void endDocument(){
        for(HashMap<String,String> x:balisInfo)
        for (Map.Entry<String,String> entry : x.entrySet()) {
            //System.out.println(entry.getKey()+" = "+entry.getValue());
        }
    }
    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts){
        if(qName=="PlayerType"){
            innerMainbalise=new HashMap<>();
        }
        for (int i = 0; i < atts.getLength(); i++) {
            innerMainbalise.put(qName+atts.getQName(i),atts.getValue(i));
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName){
        if(qName=="PlayerType"){
            this.balisInfo.add(innerMainbalise);
            innerMainbalise=new HashMap<>();
        }
    }

    @Override
    public void characters(char[] ch, int indiceDebut, int longueur){
    }
    
}
