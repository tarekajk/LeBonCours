package insa.projet.leboncours.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;


import insa.projet.leboncours.rmi.*;
import insa.projet.leboncours.*;

@SuppressWarnings("serial")
public class FenetreAnnuleCoursEleve extends JFrame {
	RMIServeur LeBonCoursDistant;
	Eleve leleve;
	
	private JTable tableDesResa;
	protected JButton retour;
	/**
	 * Une police pour le titre du site */
	private final static Font POLICE_TITRE = new Font("Berlin Sans FB",Font.PLAIN,30);
	@SuppressWarnings("unused")
	private final static Font POLICE_SSTITRE = new Font("",Font.BOLD,20);
	
	
	public FenetreAnnuleCoursEleve(RMIServeur r, Eleve el) {
		super("Le bon cours/Menu Eleve/Annuler un cours");
		LeBonCoursDistant =r;
		leleve = el;
		
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
		JLabel phrasee = new JLabel("Voici la liste des cours que vous allez recevoir. Cliquez sur l'un d'eux pour l'annuler");
		haut.add(phrasee, BorderLayout.SOUTH);		
		
		// Construction du panel avec la liste des profs disponibles 
		tableDesResa = new JTable(new TableModelCoursEleve(leleve));  
		tableDesResa.setPreferredScrollableViewportSize(new Dimension(600,300));
		panel.add(new JScrollPane(tableDesResa), BorderLayout.CENTER);
		panel.add(haut, BorderLayout.NORTH);
		tableDesResa.setBackground(Color.WHITE);
		
		//Construction panel sud avec bouton retour
		retour = new JButton("Retour");
		panel.add(retour, BorderLayout.SOUTH);
		
		this.pack();
		
		retour.addActionListener(new ControleRetourAnnuleEl(this));
		
		tableDesResa.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	           
	        	int i=0;
	        	
	        	int jour = jourEntier(tableDesResa.getValueAt(tableDesResa.getSelectedRow(), 0).toString());
	        	int heure = heureEntier(tableDesResa.getValueAt(tableDesResa.getSelectedRow(), 1).toString());
	        	String nom = (String) tableDesResa.getValueAt(tableDesResa.getSelectedRow(), 2);
	        	String prenom = (String) tableDesResa.getValueAt(tableDesResa.getSelectedRow(), 3);
	        	int niveau = niveauProfEntier(tableDesResa.getValueAt(tableDesResa.getSelectedRow(), 4).toString());
	        	Long cp = Long.parseLong(tableDesResa.getValueAt(tableDesResa.getSelectedRow(), 5).toString());
	        	float prix = Float.parseFloat(tableDesResa.getValueAt(tableDesResa.getSelectedRow(), 6).toString());
	        	@SuppressWarnings("unused")
				boolean vehic = vehiculeBoolean(tableDesResa.getValueAt(tableDesResa.getSelectedRow(), 7).toString());
	        	
	        	
	        	ReservationEleve laresa = new ReservationEleve(); 
	        	
	        	while(i < leleve.getCours().size() ) //recuperer liste cours de l'eleve 
				{
					if (leleve.getCours().get(i).getJour()==jour
							&& leleve.getCours().get(i).getHeure()==heure
							&& leleve.getCours().get(i).getProf().getNom().equals(nom)
							&& leleve.getCours().get(i).getProf().getPrenom().equals(prenom)
							&& leleve.getCours().get(i).getProf().getNiveau()==niveau
							&& leleve.getCours().get(i).getProf().getCodePostal()==cp
							&& leleve.getCours().get(i).getProf().getPrix()==prix)
					{
						laresa = leleve.getCours().get(i);
						break;
					}
					i++;
				} 
	        	//eclipse m'a dit d'enlever le catch..
	        	 
	        	FenetreAnnulationEleve fenResa = new FenetreAnnulationEleve(LeBonCoursDistant, laresa, leleve); 
	        	fenResa.setVisible(true);
	        	fenResa.pack();
	        	dispose();
	        }
		});
	}
	
	
	public void refresh() {
		((TableModelCoursEleve)tableDesResa.getModel()).fireTableDataChanged();
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
	
	public int niveauProfEntier(String niv){
		int niveau = 0;
		
		switch (niv)
		{
		  case "BAC":
		    niveau = 0;
		    break;
		  case "BAC +1":
			 niveau = 1;
		    break;
		  case "BAC +2":
			 niveau = 2;
		    break;
		  case "BAC +3":
				 niveau = 3;
			    break;
		  case "BAC +4":
				 niveau = 4;
			    break;
		  case "BAC +5":
				 niveau = 5;
			    break;
		  case "BAC +6":
				 niveau = 6;
			    break;
		  case "BAC +7":
				 niveau = 7;
			    break;  
		}
		return niveau;
	}
	
	public boolean vehiculeBoolean(String vehic){
		boolean vehicule= true;
		switch (vehic)
		{
		  case "Oui":
			 vehicule= true;
		    break;
		  case "Non":
			 vehicule= false;
		    break;
		}
		return vehicule;
	}
	
}

class ControleRetourAnnuleEl implements ActionListener {

	FenetreAnnuleCoursEleve maFenetre;
	
	public ControleRetourAnnuleEl(FenetreAnnuleCoursEleve uneFenetre){
		maFenetre = uneFenetre;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		maFenetre.setVisible(false);
		FenetreMenuEleve newFenetre = new FenetreMenuEleve(maFenetre.LeBonCoursDistant,maFenetre.leleve);
		newFenetre.setVisible(true);			
	}	
	
}