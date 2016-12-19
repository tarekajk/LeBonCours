package insa.projet.leboncours.ihm;

import insa.projet.leboncours.*;
import insa.projet.leboncours.rmi.RMIServeur;
import insa.projet.leboncours.rmi.RMIServeurImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FenetreEmploiDuTempsModif extends JFrame{
		
		RMIServeur LeBonCoursDistant;
		Prof prof;
	
		private final static Font POLICE_TITRE = new Font("Berlin Sans FB",Font.PLAIN,30);
		protected JComboBox[] tab_choix;
		protected JButton suivant;
		
		public FenetreEmploiDuTempsModif(RMIServeur r, Prof p) {
			super("Le bon cours/Menu Professeur/Emploi du temps");
			
			LeBonCoursDistant=r;
			prof = p;
			
			int[][] dispo = prof.getEdt().getDispo();
			
			//programme se termine quand fenetre fermée
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//CREATION PANEL AVEC TOUTES LES HORAIRES
			JPanel edt= new JPanel(new GridLayout(13,8,1,1));
			tab_choix = new JComboBox[77];
			for (int i=0 ; i<77 ; i++){
				tab_choix[i]= new JComboBox();
				tab_choix[i].addItem("Non Disponible");
				tab_choix[i].addItem("Disponible");
				tab_choix[i].addItem("Réservé");
			}
			//remplissage des ComboBox avec les données du tableau de dispo du prof en entrée
	
			for(int i=0; i<7 ; i++){
				tab_choix[i].setSelectedIndex(dispo[0][i]);
			}
			for(int i=0; i<7 ; i++){
				tab_choix[i+7].setSelectedIndex(dispo[1][i]);
			}
			for(int i=0; i<7 ; i++){
				tab_choix[i+14].setSelectedIndex(dispo[2][i]);
			}
			for(int i=0; i<7 ; i++){
				tab_choix[i+21].setSelectedIndex(dispo[3][i]);
			}
			for(int i=0; i<7 ; i++){
				tab_choix[i+28].setSelectedIndex(dispo[4][i]);
			}
			for(int i=0; i<7 ; i++){
				tab_choix[i+35].setSelectedIndex(dispo[5][i]);
			}
			for(int i=0; i<7 ; i++){
				tab_choix[i+42].setSelectedIndex(dispo[6][i]);
			}
			for(int i=0; i<7 ; i++){
				tab_choix[i+49].setSelectedIndex(dispo[7][i]);
			}
			for(int i=0; i<7 ; i++){
				tab_choix[i+56].setSelectedIndex(dispo[8][i]);
			}
			for(int i=0; i<7 ; i++){
				tab_choix[i+63].setSelectedIndex(dispo[9][i]);
			}
			for(int i=0; i<7 ; i++){
				tab_choix[i+70].setSelectedIndex(dispo[10][i]);
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
			JLabel phrase = new JLabel("Voici votre emploi du temps actuel, vous pouvez modifier vos disponibilités:");
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
			suivant.addActionListener(new ControleSuivantEdtModif(this));
		}
		
		
		
		public static void main(String[] args) throws RemoteException{
			int[][] dispo = new int[11][7];
			for (int i=0;i<11;i++) {
				for (int j=0;j<7;j++) dispo[i][j]=2;
			}
			ArrayList<ReservationProf> resa = new ArrayList<ReservationProf>();
			ArrayList<ReservationProf> demandesResa = new ArrayList<ReservationProf>();
			EmploiDuTemps edt = new EmploiDuTemps(dispo,resa,demandesResa);
			Prof leprof = new Prof("Guilloteau","Claire","Femme", 21, 4, 76000, 20, false, edt);
			RMIServeurImpl r = new RMIServeurImpl();
			FenetreEmploiDuTempsModif maFenetre = new FenetreEmploiDuTempsModif(r,leprof); 
			maFenetre.setVisible(true);
		}
}

class ControleSuivantEdtModif implements ActionListener {
	
	public int[][] new_dispo;
	FenetreEmploiDuTempsModif maFenetre;
	
	public ControleSuivantEdtModif(FenetreEmploiDuTempsModif uneFenetre){
		maFenetre = uneFenetre;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		new_dispo = new int[11][7];
		//on remplit le tableau ligne par ligne
		for(int j=0; j<7 ; j++){
			new_dispo[0][j] = dispoConvert((String) maFenetre.tab_choix[j].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			new_dispo[1][j] = dispoConvert((String) maFenetre.tab_choix[j+7].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			new_dispo[2][j] = dispoConvert((String) maFenetre.tab_choix[j+14].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			new_dispo[3][j] = dispoConvert((String) maFenetre.tab_choix[j+21].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			new_dispo[4][j] = dispoConvert((String) maFenetre.tab_choix[j+28].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			new_dispo[5][j] = dispoConvert((String) maFenetre.tab_choix[j+35].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			new_dispo[6][j] = dispoConvert((String) maFenetre.tab_choix[j+42].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			new_dispo[7][j] = dispoConvert((String) maFenetre.tab_choix[j+49].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			new_dispo[8][j] = dispoConvert((String) maFenetre.tab_choix[j+56].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			new_dispo[9][j] = dispoConvert((String) maFenetre.tab_choix[j+63].getSelectedItem());
		}
		for(int j=0; j<7 ; j++){
			new_dispo[10][j] = dispoConvert((String) maFenetre.tab_choix[j+70].getSelectedItem());
		}
		
		//on associe le tableau new dispo au prof
		try {
			maFenetre.LeBonCoursDistant.getLeBonCours().getListeProfs().get(maFenetre.LeBonCoursDistant.getLeBonCours().getListeProfs().indexOf(maFenetre.prof)).getEdt().setDispo(new_dispo);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		maFenetre.setVisible(false);
		FenetreMenuProf newFenetre = new FenetreMenuProf();
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