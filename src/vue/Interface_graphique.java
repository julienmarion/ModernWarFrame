package vue;
import IA.IA;
import modele.jeu.ModelJeu;
import modele.jeu.ModelJeuEditable;
import modele.player.Player;
import utilitaire.EcouteurModele;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;



/**
 *  La classe permet de creer l'interface graphique comportant a gauche la grille du jeu qui est 
 * un JPanel sur lequel on dessine et a droite un tableau pour les stats des joueurs ainsi qu'un bouton "suivant".
 */
public class Interface_graphique extends JFrame implements EcouteurModele ,ActionListener {
    /**
    * JPanel pour positionner la grille de jeu qui se trouve a gauche de l'interface graphique
    */
    private final JPanel left=new JPanel();
    /**
    * JPanel pour positionner nos objets a droite de l'interface graphique
    */
    private final JPanel right=new JPanel();
    /**
    * JPanel pour positionner notre tableau avec stat des joueurs
    */
    private final JPanel right_top=new JPanel();
    /**
    * JPanel pour positionner le bouton "suivant"
    */
    private final JPanel right_bot=new JPanel();
    /**
    * Creation du bouton "suivant" qui permet de passer le tour manuellement
    */
    private final JButton suivant=new JButton("suivant");
    /**
    * Model qui nous sert dans le constructeur
    */
    private ModelJeuEditable model;
    /**
    * Instance de la classe VueInterface_graphique qui nous permet de dessiner sur le JPanel "left" 
    */
    private VueInterface_graphique maVue;
    /**
    * JTable qui permet d'afficher les stats des joueurs que l'on positionne Ã  droite de la fenetre
    */
    private JTable tableau;
    /**
    * Instance de la classe IA que l'on utilise dans le constructeur
    */
    private IA ia;
    /**
    * init de sizeListPlayer pour connaitre le nombre de joueur restant
    */
    private int sizeListPlayer=0;
    /**
    * Un model de Table pour mettre a jour notre table
    */
    private DefaultTableModel defaultModel;
    
    /**
    * Constructeur
    * @param model le modele du jeu en cours
    * @param ia l'IA
    */
    public Interface_graphique(ModelJeuEditable model,IA ia){
        this.sizeListPlayer=model.getAllPlayer().size();
        this.ia=ia;
        this.model=model;
        //init JFrame
        this.setTitle("WarFrame");
        this.setSize(900, 660);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        //ajout et positionnement JFrame left et right
        this.add(left);
        left.setBounds(10,10,600,600);
        this.add(right);
        right.setBounds(620,10,250,600);
        right.setBackground(new Color(251,159,38));

        //tableau

        Object[][] donnees =this.createTable(model);
        String[] entetes = {"Nom", "Energie", "Balle", "bombe","mine"};
        this.tableau = new JTable(donnees, entetes);
        this.defaultModel=new DefaultTableModel(donnees,entetes);
        tableau.setModel(this.defaultModel);
        JScrollPane Scrolltableau= new JScrollPane(tableau);



        //ajout et positionnement dans right
        right.setLayout(null);
        right.add(right_top);
        right_top.setBounds(5,5,240,435);
        right_top.setBackground(new Color(40,183,160));
        right_top.setLayout(null);
        right_top.add(Scrolltableau);
        Scrolltableau.setBounds(0,0,right_top.getWidth(),right_top.getHeight());
        right.add(right_bot);
        right_bot.setBounds(5,445,240,150);
        right_bot.setLayout(null);
        right_bot.add(suivant);
        suivant.setBounds(50,50,140,50);
        suivant.addActionListener(this);
        //appel de la grille de jeu + positionnement de la grille dans left
        this.maVue=new VueInterface_graphique(model.nbColonnes(),model.nbLignes(),model);
        left.setLayout(null);
        maVue.setBounds(0,0,left.getWidth(),left.getHeight());
        left.add(maVue);


        this.setVisible(true);
    }
    
    /**
    * Constructeur
    * @param model le modele du jeu en cours
    */
    public Interface_graphique(ModelJeuEditable model){
        this.model=model;
        //init JFrame
        this.setTitle("WarFrame");
        this.setSize(900, 660);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        //ajout et positionnement JFrame left et right
        this.add(left);
        left.setBounds(10,10,600,600);
        this.add(right);
        right.setBounds(620,10,250,600);
        right.setBackground(new Color(251,159,38));

        //tableau

        Object[][] donnees =this.createTable(model);
        String[] entetes = {"Nom", "Energie", "Balle", "bombe","mine"};
        this.tableau = new JTable(donnees, entetes);
        this.defaultModel=new DefaultTableModel(donnees,entetes);
        tableau.setModel(this.defaultModel);
        JScrollPane Scrolltableau= new JScrollPane(tableau);



        //ajout et positionnement dans right
        right.setLayout(null);
        right.add(right_top);
        right_top.setBounds(5,5,240,435);
        right_top.setBackground(new Color(40,183,160));
        right_top.setLayout(null);
        right_top.add(Scrolltableau);
        Scrolltableau.setBounds(0,0,right_top.getWidth(),right_top.getHeight());
        right.add(right_bot);
        right_bot.setBounds(5,445,240,150);
        right_bot.setLayout(null);

        //appel de la grille de jeu + positionnement de la grille dans left
        this.maVue=new VueInterface_graphique(model.nbColonnes(),model.nbLignes(),model);
        left.setLayout(null);
        maVue.setBounds(0,0,left.getWidth(),left.getHeight());
        left.add(maVue);


        this.setVisible(true);
    }
    
    /**
    * Cette methode nous permet de creer notre tableau qui affiche les stat pour chaque joueurs 
    * @param model le model du jeu
    * @return les stats de chaque joueurs avec le nom , l'energie , le nombre de balle ,nombre de bombe et le nombre de mine
    */
    public Object[][] createTable(ModelJeuEditable model){
        Object[][] data=new Object[model.getAllPlayer().size()][5];
        ArrayList<Player> listjoueur=model.getAllPlayer();
            
            for(int i=0;i<model.getAllPlayer().size();i++){
                data[i][0]=listjoueur.get(i).toString();
                data[i][1]=listjoueur.get(i).getEnergy();
                data[i][2]=listjoueur.get(i).getCanon().getMunition();
                data[i][3]=listjoueur.get(i).getBomb();
                data[i][4]=listjoueur.get(i).getMine();   
        }
        return data;
    }
    
    @Override
    public void MiseAJourModele(Object e) {
        this.maVue = new VueInterface_graphique(((ModelJeu)e).nbColonnes(),((ModelJeu)e).nbLignes(),(ModelJeuEditable)e);
        this.repaint();
        this.updateTable(this.model.getAllPlayer().size());
    }
    /**
    * Methode qui permet de mettre a jour le tableau
    *@param i correspond au nombre de joueur encore en vie
    */
    private void updateTable(int i){	//on modifie la durÃ©e inscrite dans le tableau Ã  l'indice i si elle a Ã©tait dÃ©finie via GetFile()
        if(i==this.sizeListPlayer) {
            for (int j = 0; j < i; j++) {
                this.tableau.getModel().setValueAt(this.model.getAllPlayer().get(j).toString(), j, 0);
                this.tableau.getModel().setValueAt(this.model.getAllPlayer().get(j).getEnergy(), j, 1);
                this.tableau.getModel().setValueAt(this.model.getAllPlayer().get(j).getCanon().getMunition(), j, 2);
                this.tableau.getModel().setValueAt(this.model.getAllPlayer().get(j).getBomb(), j, 3);
                this.tableau.getModel().setValueAt(this.model.getAllPlayer().get(j).getMine(), j, 4);

            }
        }
        else{
            for(int j=this.sizeListPlayer-1;0<=j;j--){
                this.defaultModel.removeRow(j);
            }
            Object[][] data=createTable(model);
            for(int j=0;j<i;j++){
                this.defaultModel.addRow(data[j]);
            }
            this.sizeListPlayer=model.getAllPlayer().size();
        }
            
	}

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(this.ia!=null) {
            if (!model.isWin()) {
                this.ia.nextPlay();
            }
        }
    }
    
    
}
