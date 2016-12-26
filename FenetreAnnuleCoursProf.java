package insa.projet.leboncours.ihm;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;


import insa.projet.leboncours.rmi.*;
import insa.projet.leboncours.ihm.TableModel;
import insa.projet.leboncours.*;

public class FenetreAnnuleCoursProf extends JFrame {
	RMIServeur LeBonCoursDistant;
	Prof prof;
	
	private JTable tableDesResa;
	protected JButton retour;
	/**
	 * Une police pour le titre du site */
	private final static Font POLICE_TITRE = new Font("Berlin Sans FB",Font.PLAIN,30);
	private final static Font POLICE_SSTITRE = new Font("",Font.BOLD,20);
	
	
	public FenetreAnnuleCoursProf(RMIServeur r, Prof p) {
		super("Le bon cours/Menu Professeur/Annuler un cours");
		LeBonCoursDistant =r;
		prof =p;
		
		//programme se termine quand fenetre fermée
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = (JPanel)this.getContentPane();
		panel.setLayout(new BorderLayout(15,15));
		
		//création du panel nord, avec nom du site
		JPanel haut = new JPanel();
		haut.setLayout(new BorderLayout());
		
		JLabel titre = new JLabel("leboncours.fr",new ImageIcon("imagecours.png"),SwingConstants.CENTER);
		titre.setFont(POLICE_TITRE);
		haut.add(titre, BorderLayout.NORTH);
		JLabel saut = new JLabel("              ");
		haut.add(saut, BorderLayout.CENTER);
		JLabel phrasee = new JLabel("Voici la liste des cours que vous devez dispenser. Cliquez sur l'un d'eux pour l'annuler");
		haut.add(phrasee, BorderLayout.SOUTH);		
		
		// Construction du panel avec la liste des profs disponibles 
		tableDesResa = new JTable(new TableModelCoursProf(prof));  
		tableDesResa.setPreferredScrollableViewportSize(new Dimension(600,300));
		panel.add(new JScrollPane(tableDesResa), BorderLayout.CENTER);
		panel.add(haut, BorderLayout.NORTH);
		tableDesResa.setBackground(Color.WHITE);
		
		//Construction panel sud avec bouton retour
		retour = new JButton("Retour");
		panel.add(retour, BorderLayout.SOUTH);
		
		this.pack();
		
		retour.addActionListener(new ControleRetourAnnule(this));
		
		tableDesResa.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	           
	        	int i=0;
	        	
	        	int jour = jourEntier(tableDesResa.getValueAt(tableDesResa.getSelectedRow(), 0).toString());
	        	int heure = heureEntier(tableDesResa.getValueAt(tableDesResa.getSelectedRow(), 1).toString());
	        	String nom = (String) tableDesResa.getValueAt(tableDesResa.getSelectedRow(), 2);
	        	String prenom = (String) tableDesResa.getValueAt(tableDesResa.getSelectedRow(), 3);
	        	int niveau = niveauEleve(tableDesResa.getValueAt(tableDesResa.getSelectedRow(), 4).toString());
	        	Long cp = Long.parseLong(tableDesResa.getValueAt(tableDesResa.getSelectedRow(), 5).toString());
	        	
	        	
	        	ReservationProf laresa = new ReservationProf(); 
	        	
	        	while(i < prof.getEdt().getListeCours().size() ) //recuperer liste cours du prof 
				{
					if (prof.getEdt().getListeCours().get(i).getJour()==jour
							&& prof.getEdt().getListeCours().get(i).getHeure()==heure
							&& prof.getEdt().getListeCours().get(i).getEleve().getNom().equals(nom)
							&& prof.getEdt().getListeCours().get(i).getEleve().getPrenom().equals(prenom)
							&& prof.getEdt().getListeCours().get(i).getEleve().getNiveau()==niveau
							&& prof.getEdt().getListeCours().get(i).getEleve().getCodePostal()==cp)
					{
						laresa = prof.getEdt().getListeCours().get(i);
						break;
					}
					i++;
				} 
	        	//eclipse m'a dit d'enlever le catch..
	        	 
	        	FenetreAnnulationProf fenResa = new FenetreAnnulationProf(LeBonCoursDistant, laresa, prof); 
	        	fenResa.setVisible(true);
	        	fenResa.pack();
	        	dispose();
	        }
		});
	}
	
	
	public void refresh() {
		((TableModelCoursProf)tableDesResa.getModel()).fireTableDataChanged();
	}
	
	
	public int jourEntier(String j){
		int jour = -1;
		
		switch (j)
		{
		  case "Lundi":
		    jour = 0;
		    break;
		  case "Mardi":
			jour = 1;
		    break;
		  case "Mercredi":
			jour = 2;
		    break;
		  case "Jeudi":
			jour = 3;
			break;
		  case "Vendredi":
			jour = 4;
			break;
		  case "Samedi":
			jour = 5;
			break;
		  case "Dimanche":
			jour = 6;
			break; 
		}
		return jour;
	}
	
	
	public int heureEntier(String h){
		int heure = -1;
		
		switch (h)
		{
		  case "9h":
		    heure = 0;
		    break;
		  case "10":
			heure = 1;
		    break;
		  case "11h":
			heure = 2;
		    break;
		  case "12h":
			heure = 3;
			break;
		  case "13h":
			heure = 4;
			break;
		  case "14h":
			heure = 5;
			break;
		  case "15h":
			heure = 6;
			break; 
		  case "16h":
			heure = 7;
			break;
		  case "17h":
			heure = 8;
			break;
		  case "18h":
			heure = 9;
			break;
		  case "19h":
			heure = 10;
			break; 	
		}
		return heure;
	}
	
	public int niveauEleve(String niv){
		int niveau = 0;
		
		switch (niv)
		{
		  case "Primaire":
		    niveau = -7;
		    break;
		  case "6ème":
			 niveau = -6;
		    break;
		  case "5ème":
			 niveau = -5;
		    break;
		  case "4ème":
				 niveau = -4;
			    break;
		  case "3ème":
				 niveau = -3;
			    break;
		  case "Seconde":
				 niveau = -2;
			    break;
		  case "Première":
				 niveau = -1;
			    break;
		  case "Terminale":
				 niveau = 0;
			    break;  
		}
		return niveau;
	}
}

class ControleRetourAnnule implements ActionListener {

	FenetreAnnuleCoursProf maFenetre;
	
	public ControleRetourAnnule(FenetreAnnuleCoursProf uneFenetre){
		maFenetre = uneFenetre;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		maFenetre.setVisible(false);
		FenetreMenuProf newFenetre = new FenetreMenuProf(maFenetre.LeBonCoursDistant,maFenetre.prof);
		newFenetre.setVisible(true);			
	}	
	
}