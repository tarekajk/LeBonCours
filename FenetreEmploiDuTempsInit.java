package insa.projet.leboncours.ihm;

import insa.projet.leboncours.*;
import insa.projet.leboncours.rmi.RMIServeur;
import insa.projet.leboncours.rmi.RMIServeurImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FenetreEmploiDuTempsInit extends JFrame{
		
		RMIServeur LeBonCoursDistant;
		int indice_prof;
	
		private final static Font POLICE_TITRE = new Font("Berlin Sans FB",Font.PLAIN,30);
		protected JComboBox[] tab_choix;
		protected JButton suivant;
		
		public FenetreEmploiDuTempsInit(RMIServeur r, int ip) {
			super("Le bon cours/Inscription Professeur/Emploi du temps");
			LeBonCoursDistant = r;
			indice_prof = ip;
			
			//programme se termine quand fenetre fermée
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//création du panel avec toutes les horaires
			JPanel edt= new JPanel(new GridLayout(13,8,1,1));
			tab_choix = new JComboBox[77];
			for (int i=0 ; i<77 ; i++){
				tab_choix[i]= new JComboBox();
				tab_choix[i].addItem("Non Disponible");
				tab_choix[i].addItem("Disponible");
				tab_choix[i].addItem("Réservé");
			}
			//pour sauter une ligne avant l'emploi du temps
			edt.add(new Canvas());
			edt.add(new Canvas());
			edt.add(new Canvas());
			edt.add(new Canvas());
			edt.add(new Canvas());
			edt.add(new Canvas());
			edt.add(new Canvas());
			edt.add(new Canvas());
			
			edt.add(new Canvas());
			edt.add(new JLabel("Lundi",SwingConstants.CENTER));
			edt.add(new JLabel("Mardi",SwingConstants.CENTER));
			edt.add(new JLabel("Mercredi",SwingConstants.CENTER));
			edt.add(new JLabel("Jeudi",SwingConstants.CENTER));
			edt.add(new JLabel("Vendredi",SwingConstants.CENTER));
			edt.add(new JLabel("Samedi",SwingConstants.CENTER));
			edt.add(new JLabel("Dimanche",SwingConstants.CENTER));
			
			edt.add(new JLabel("9h - 10h"));
			for (int i=0; i<7 ; i++){
			edt.add(tab_choix[i]);
			}
			edt.add(new JLabel("10h - 11h"));
			for (int i=0; i<7 ; i++){
				edt.add(tab_choix[i+7]);
				}
			edt.add(new JLabel("11h - 12h"));
			for (int i=0; i<7 ; i++){
				edt.add(tab_choix[i+14]);
				}
			edt.add(new JLabel("12h - 13h"));
			for (int i=0; i<7 ; i++){
				edt.add(tab_choix[i+21]);
				}
			edt.add(new JLabel("13h - 14h"));
			for (int i=0; i<7 ; i++){
				edt.add(tab_choix[i+28]);
				}
			edt.add(new JLabel("14h - 15h"));
			for (int i=0; i<7 ; i++){
				edt.add(tab_choix[i+35]);
				}
			edt.add(new JLabel("15h - 16h"));
			for (int i=0; i<7 ; i++){
				edt.add(tab_choix[i+42]);
				}
			edt.add(new JLabel("16h - 17h"));
			for (int i=0; i<7 ; i++){
				edt.add(tab_choix[i+49]);
				}
			edt.add(new JLabel("17h - 18h"));
			for (int i=0; i<7 ; i++){
				edt.add(tab_choix[i+56]);
				}
			edt.add(new JLabel("18h - 19h"));
			for (int i=0; i<7 ; i++){
				edt.add(tab_choix[i+63]);
				}
			edt.add(new JLabel("19h - 20h"));
			for (int i=0; i<7 ; i++){
				edt.add(tab_choix[i+70]);
				}
			
			//création panel sud, avec bouton "Suivant"
			JPanel bas = new JPanel();
			suivant = new JButton("SUIVANT"); //ajoute un vide
			bas.add(suivant); 
			
			//création du panel nord, avec nom du site et phrase (test)
			JPanel haut = new JPanel();
			haut.setLayout(new BorderLayout());
			
			JLabel titre = new JLabel("leboncours.fr",new ImageIcon("imagecours.png"),SwingConstants.CENTER);
			titre.setFont(POLICE_TITRE);
			JLabel phrase = new JLabel("Veuillez saisir vos disponibilités pour achever votre inscription:");
			haut.add(titre,  BorderLayout.NORTH);
			JLabel saut = new JLabel("              ");
			haut.add(saut, BorderLayout.CENTER);
			haut.add(phrase,  BorderLayout.SOUTH);
			
			//remplissage du panel principal
			JPanel mainPanel = (JPanel)this.getContentPane();
			//JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(edt, BorderLayout.CENTER);
			mainPanel.add(haut,  BorderLayout.NORTH);
			mainPanel.add(bas, BorderLayout.SOUTH);
			
			mainPanel.setBorder(new EmptyBorder(10,10,10,10));
			
			this.pack();
			suivant.addActionListener(new ControleSuivantEdtInit(this));
		}
		
		public static void main(String[] args) throws RemoteException {
			RMIServeurImpl rmi = new RMIServeurImpl();
			FenetreEmploiDuTempsInit maFenetre = new FenetreEmploiDuTempsInit(rmi,1);
			maFenetre.setVisible(true);
		}
}

class ControleSuivantEdtInit implements ActionListener {
	
	public int[][] dispo;
	FenetreEmploiDuTempsInit maFenetre;
	
	public ControleSuivantEdtInit(FenetreEmploiDuTempsInit uneFenetre){
		maFenetre = uneFenetre;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		dispo = new int[11][7];
		//on remplit le tableau ligne par ligne
		for(int j=0; j<7 ; j++){
			dispo[0][j] = dispoConvert((String) maFenetre.tab_choix[j].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			dispo[1][j] = dispoConvert((String) maFenetre.tab_choix[j+7].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			dispo[2][j] = dispoConvert((String) maFenetre.tab_choix[j+14].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			dispo[3][j] = dispoConvert((String) maFenetre.tab_choix[j+21].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			dispo[4][j] = dispoConvert((String) maFenetre.tab_choix[j+28].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			dispo[5][j] = dispoConvert((String) maFenetre.tab_choix[j+35].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			dispo[6][j] = dispoConvert((String) maFenetre.tab_choix[j+42].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			dispo[7][j] = dispoConvert((String) maFenetre.tab_choix[j+49].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			dispo[8][j] = dispoConvert((String) maFenetre.tab_choix[j+56].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			dispo[9][j] = dispoConvert((String) maFenetre.tab_choix[j+63].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			dispo[10][j] = dispoConvert((String) maFenetre.tab_choix[j+70].getSelectedItem());
		}
		
		try {
			maFenetre.LeBonCoursDistant.getLeBonCours().getListeProfs().get(maFenetre.indice_prof).getEdt().setDispo(dispo);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		maFenetre.setVisible(false);
		FenetreMenuProf newFenetre = new FenetreMenuProf(); //maFenetre.LeBonCoursDistant.getLeBonCours().getListeProfs().get(maFenetre.indice_prof)
		newFenetre.setVisible(true);		
	}
	
	//fonction qui associe la case dispo cochée à l'integer correspondant
			public int dispoConvert(String disp){
				int dispo = 0;
				
				switch (disp)
				{
				  case "Non Disponible":
				    dispo = 0;
				    break;
				  case "Disponible":
					    dispo = 1;
					    break;
				  case "Réservé":
					    dispo = 2;
					    break;  
				}
				return dispo;
			}
	
}