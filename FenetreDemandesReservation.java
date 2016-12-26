package insa.projet.leboncours.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import insa.projet.leboncours.ihm.TableModelReservation;
import insa.projet.leboncours.*;

@SuppressWarnings("serial")
public class FenetreDemandesReservation extends JFrame{
	
	/**
	 * L'objet contenant les informations de leboncours.fr
	 */
	public RMIServeur LeBonCoursDistant;
	public Prof prof;
	
	/**
	 * Une police pour le titre du site 
	 */
	private final static Font POLICE_TITRE = new Font("Berlin Sans FB",Font.PLAIN,30);
	private final static Font POLICE_SSTITRE = new Font("",Font.BOLD,20);
	
	/**
	 * une table pour afficher les profs disponibles
	 */
	private JTable tableDesResas;

	public FenetreDemandesReservation (RMIServeur leboncours, Prof p) {
		super("Le bon cours/Menu Professeur/Mes demandes de reservation");
		LeBonCoursDistant = leboncours;
		prof = p;
		
		//programme se termine quand fenetre ferm�e
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//
		JPanel panel = (JPanel)this.getContentPane();
		panel.setLayout(new BorderLayout(15,15));
		
		//cr�ation du panel nord, avec nom du site
		JPanel haut = new JPanel();
		haut.setLayout(new BorderLayout());
		
		JLabel titre = new JLabel("leboncours.fr",new ImageIcon("imagecours.png"),SwingConstants.CENTER);
		titre.setFont(POLICE_TITRE);
		haut.add(titre, BorderLayout.NORTH);
		JLabel saut = new JLabel("              ");
		haut.add(saut, BorderLayout.CENTER);
		JLabel phrasee = new JLabel("Voici la liste des demandes de reservation de cours:");
		haut.add(phrasee, BorderLayout.SOUTH);		
		
		// Construction du panel avec la liste des profs disponibles 
		tableDesResas = new JTable(new TableModelReservation(prof));  // resultat = ArrayList de listeProfDispo
		tableDesResas.setPreferredScrollableViewportSize(new Dimension(600,600));
		panel.add(new JScrollPane(tableDesResas), BorderLayout.CENTER);
		panel.add(haut, BorderLayout.NORTH);
		tableDesResas.setBackground(Color.WHITE);
		
		this.pack();
		
		tableDesResas.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	           
	        	int i=0;
	        	
	        	int jour = jourEntier(tableDesResas.getValueAt(tableDesResas.getSelectedRow(), 0).toString());
	        	int heure = heureEntier(tableDesResas.getValueAt(tableDesResas.getSelectedRow(), 1).toString());
	        	String nom = (String) tableDesResas.getValueAt(tableDesResas.getSelectedRow(), 2);
	        	String prenom = (String) tableDesResas.getValueAt(tableDesResas.getSelectedRow(), 3);
	        	int niveau = niveauEleveToListener(tableDesResas.getValueAt(tableDesResas.getSelectedRow(), 4).toString());
	        	Long cp = Long.parseLong(tableDesResas.getValueAt(tableDesResas.getSelectedRow(), 5).toString());
	        	
	        	Eleve leleve = new Eleve(); 
	        	
	        	try {
					while(i < LeBonCoursDistant.getLeBonCours().getListeEleve().size() ) //recuperer Prof Dispo 
					{
						if (LeBonCoursDistant.getLeBonCours().getListeEleve().get(i).getNom().equals(nom)
								&& LeBonCoursDistant.getLeBonCours().getListeEleve().get(i).getPrenom().equals(prenom)
								&& LeBonCoursDistant.getLeBonCours().getListeEleve().get(i).getNiveau()==niveau
								&& LeBonCoursDistant.getLeBonCours().getListeEleve().get(i).getCodePostal()==cp)
						{
							leleve = LeBonCoursDistant.getLeBonCours().getListeEleve().get(i);
							break;
						}
						i++;
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	ReservationProf resa = new ReservationProf(jour, heure, leleve); 
	        	FenetreValidationReservation fenValidation = new FenetreValidationReservation(LeBonCoursDistant, resa, prof); 
	        	fenValidation.setVisible(true);
	        	fenValidation.pack();
	        	dispose();
	        }
		});
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void refresh() {
		((TableModelReservation)tableDesResas.getModel()).fireTableDataChanged();
	
	}
	
	public int niveauEleveToListener(String niv){
		int niveau = 0;
		
		switch (niv)
		{
		  case "Terminale":
		    niveau = 0;
		    break;
		  case "Premiere":
			niveau = -1;
		    break;
		  case "Seconde":
			niveau = -2;
		    break;
		  case "3eme":
		    niveau = -3;
		    break;
		  case "4eme":
			niveau = -4;
		    break;
		  case "5eme":
			niveau = -5;
		    break;
		  case "6eme":
			niveau = -6;
		    break;
		  case "Primaire":
			niveau = -7;
		    break;  
		}
		return niveau;
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
	
}
