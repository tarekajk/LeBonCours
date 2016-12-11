package insa.projet.leboncours.ihm;

import insa.projet.leboncours.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FenetreEmploiDuTemps extends JFrame{
		
		private final static Font POLICE_TITRE = new Font("Berlin Sans FB",Font.PLAIN,30);
		protected JComboBox[] tab_choix;
		protected JButton suivant;
		
		public FenetreEmploiDuTemps() {
			super("Le bon cours/Inscription Professeur/Emploi du temps");
			
			//programme se termine quand fenetre fermée
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//création du panel avec toutes les horaires
			JPanel edt= new JPanel(new GridLayout(12,8,1,1));
			tab_choix = new JComboBox[78];
			for (int i=0 ; i<78 ; i++){
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
			
			edt.add(new JLabel("10h - 11h"));
			for (int i=0; i<7 ; i++){
			edt.add(tab_choix[i]);
			}
			edt.add(new JLabel("11h - 12h"));
			for (int i=0; i<7 ; i++){
				edt.add(tab_choix[i+7]);
				}
			edt.add(new JLabel("12h - 13h"));
			for (int i=0; i<7 ; i++){
				edt.add(tab_choix[i+14]);
				}
			edt.add(new JLabel("14h - 15h"));
			for (int i=0; i<7 ; i++){
				edt.add(tab_choix[i+21]);
				}
			edt.add(new JLabel("15h - 16h"));
			for (int i=0; i<7 ; i++){
				edt.add(tab_choix[i+28]);
				}
			edt.add(new JLabel("16h - 17h"));
			for (int i=0; i<7 ; i++){
				edt.add(tab_choix[i+35]);
				}
			edt.add(new JLabel("17h - 18h"));
			for (int i=0; i<7 ; i++){
				edt.add(tab_choix[i+42]);
				}
			edt.add(new JLabel("18h - 19h"));
			for (int i=0; i<7 ; i++){
				edt.add(tab_choix[i+49]);
				}
			edt.add(new JLabel("19h - 20h"));
			for (int i=0; i<7 ; i++){
				edt.add(tab_choix[i+56]);
				}
			edt.add(new JLabel("20h - 21h"));
			for (int i=0; i<7 ; i++){
				edt.add(tab_choix[i+63]);
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
			JLabel phrase = new JLabel("Veuillez saisir vos disponibilités pour achever votre inscription");
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
			
		}
		
		public static void main(String[] args) {
			FenetreEmploiDuTemps maFenetre = new FenetreEmploiDuTemps();
			maFenetre.setVisible(true);
		}
}
