package insa.projet.leboncours.ihm;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;


import insa.projet.leboncours.rmi.*;
import insa.projet.leboncours.ihm.TableModel;
import insa.projet.leboncours.*;

@SuppressWarnings("serial")
public class FenetreResultatRecherche extends JFrame{
	
	/**
	 * L'objet contenant les informations de leboncours.fr
	 */
	public RMIServeur LeBonCoursDistant;
	
	/**
	 * Une police pour le titre du site 
	 */
	private final static Font POLICE_TITRE = new Font("Berlin Sans FB",Font.PLAIN,30);
	
	/**
	 * une table pour afficher les profs disponibles
	 */
	private JTable tableDesProfsDispos;
	

	public FenetreResultatRecherche (RMIServeur leboncours, final int jour, final int heure, ArrayList<Prof> resultat,final Eleve el) {
		super("Resultat de la recherche");
		LeBonCoursDistant = leboncours;
		
		//programme se termine quand fenetre fermée
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//
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
		JLabel phrasee = new JLabel("Voici la liste des professeurs qui correspondent à vos critères:");
		haut.add(phrasee, BorderLayout.SOUTH);		
		
		// Construction du panel avec la liste des profs disponibles 
		tableDesProfsDispos = new JTable(new TableModel(resultat));  // resultat = ArrayList de listeProfDispo
		tableDesProfsDispos.setPreferredScrollableViewportSize(new Dimension(600,600));
		panel.add(new JScrollPane(tableDesProfsDispos), BorderLayout.CENTER);
		panel.add(haut, BorderLayout.NORTH);
		tableDesProfsDispos.setBackground(Color.WHITE);
		
		
		tableDesProfsDispos.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	           
	        	int i=0;
	        	
	        	String nom = (String) tableDesProfsDispos.getValueAt(tableDesProfsDispos.getSelectedRow(), 0);
	        	String prenom = (String) tableDesProfsDispos.getValueAt(tableDesProfsDispos.getSelectedRow(), 1);
	        	int niveau = niveauProfToListener(tableDesProfsDispos.getValueAt(tableDesProfsDispos.getSelectedRow(), 3).toString());
	        	Long cp = Long.parseLong(tableDesProfsDispos.getValueAt(tableDesProfsDispos.getSelectedRow(), 4).toString());
	        	Boolean voiture = IsVoitureToListener(tableDesProfsDispos.getValueAt(tableDesProfsDispos.getSelectedRow(), 5).toString());
	        	Float prix = Float.parseFloat(tableDesProfsDispos.getValueAt(tableDesProfsDispos.getSelectedRow(), 6).toString());
	        	
	        	Prof leprof = new Prof(); 
	        	
	        	try {
					while(i < LeBonCoursDistant.getLeBonCours().getListeProfs().size() ) //recuperer Prof Dispo 
					{
						if (LeBonCoursDistant.getLeBonCours().getListeProfs().get(i).getNom().equals(nom)
								&& LeBonCoursDistant.getLeBonCours().getListeProfs().get(i).getPrenom().equals(prenom)
								&& LeBonCoursDistant.getLeBonCours().getListeProfs().get(i).getNiveau()==niveau
								&& LeBonCoursDistant.getLeBonCours().getListeProfs().get(i).getCodePostal()==cp
								&& LeBonCoursDistant.getLeBonCours().getListeProfs().get(i).isVoiture()==voiture
								&& LeBonCoursDistant.getLeBonCours().getListeProfs().get(i).getPrix()==prix)
						{
							leprof = LeBonCoursDistant.getLeBonCours().getListeProfs().get(i);
							break;
						}
						i++;
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	ReservationEleve resa = new ReservationEleve(jour, heure, leprof); 
	        	FenetreReservationEleve fenResa = new FenetreReservationEleve(LeBonCoursDistant, resa, el); 
	        	fenResa.setVisible(true);
	        	fenResa.pack();
	        	dispose();
	        }
		});
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void refresh() {
		((TableModel)tableDesProfsDispos.getModel()).fireTableDataChanged();
	
	}
	

	public int niveauProfToListener(String niv){
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
	
	public boolean IsVoitureToListener(String vehic){
		boolean vehicule= true;
		if (vehic == "Oui"){
			 vehicule= true;}
		if (vehic == "Non"){
			 vehicule= false;}
		return vehicule;
	}
	
}
