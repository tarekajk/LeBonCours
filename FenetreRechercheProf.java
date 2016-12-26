package insa.projet.leboncours.ihm;

import insa.projet.leboncours.*;
import insa.projet.leboncours.rmi.*;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;



@SuppressWarnings("serial")
public class FenetreRechercheProf extends JFrame {
	
	/**
	 * L'objet contenant les informations de leboncours.fr
	 */
	public RMIServeur LeBonCoursDistant;
	public Eleve leleve;
		
	/**
	 * Une police pour le titre du site 
	 */
	private final static Font POLICE_TITRE = new Font("Berlin Sans FB",Font.PLAIN,30);


	@SuppressWarnings("rawtypes")
	protected JComboBox<String> matiere_choix, jour_choix, heure_choix;
	protected JButton chercher, retour;
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	public FenetreRechercheProf(RMIServeur s, Eleve el) { // Garder seulement le serveur et récupérer leboncours
		super("Recherche d'un prof");
		LeBonCoursDistant = s;
		leleve = el;
			
		//programme se termine quand fenetre fermée
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//creation du panel avec toutes les questions
		JPanel panel= new JPanel(new GridLayout(5,2,10,5));
		panel.add(new Canvas());
		panel.add(new Canvas());
		
		JLabel matiere = new JLabel("Matiere:");
		matiere_choix = new JComboBox();
		matiere_choix.addItem("Bio");
		matiere_choix.addItem("Maths");
		matiere_choix.addItem("Physique");
		matiere_choix.addItem("Langues");
		matiere_choix.addItem("Litterature");
		matiere_choix.addItem("Eco");
		panel.add(matiere);
		panel.add(matiere_choix);
		
		JLabel jour = new JLabel("Jour : ");
		jour_choix = new JComboBox();
		jour_choix.addItem("Lundi");
		jour_choix.addItem("Mardi");
		jour_choix.addItem("Mercredi");
		jour_choix.addItem("Jeudi");
		jour_choix.addItem("Vendredi");
		jour_choix.addItem("Samedi");
		jour_choix.addItem("Dimanche");
		panel.add(jour);
		panel.add(jour_choix);
		
		JLabel heure = new JLabel("Heure : ");
		heure_choix = new JComboBox();
		heure_choix.addItem("9h");
		heure_choix.addItem("10h");
		heure_choix.addItem("11h");
		heure_choix.addItem("12h");
		heure_choix.addItem("13h");
		heure_choix.addItem("14h");
		heure_choix.addItem("15h");
		heure_choix.addItem("16h");
		heure_choix.addItem("17h");
		heure_choix.addItem("18h");
		heure_choix.addItem("19h");
		panel.add(heure);
		panel.add(heure_choix);
		
		chercher = new JButton("CHERCHER");
		retour = new JButton("RETOUR");
		panel.add(retour);
		panel.add(chercher); 
		
		//création du panel nord, avec nom du site
		JPanel haut = new JPanel();
		haut.setLayout(new BorderLayout());
				
		JLabel titre = new JLabel("leboncours.fr",new ImageIcon("imagecours.png"),SwingConstants.CENTER);
		titre.setFont(POLICE_TITRE);
		haut.add(titre, BorderLayout.NORTH);
		JLabel saut = new JLabel("              ");
		haut.add(saut, BorderLayout.CENTER);
		JLabel phrasee = new JLabel("Selectionnez les caracteristiques du cours que vous recherchez:");
		haut.add(phrasee, BorderLayout.SOUTH);
								
		//remplissage du panel principal
		JPanel mainPanel = (JPanel)this.getContentPane();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(panel, BorderLayout.CENTER);
		mainPanel.add(haut,  BorderLayout.NORTH);
		mainPanel.setBorder(new EmptyBorder(10,10,10,10));
				
		this.pack();
		chercher.addActionListener(new ControleRecherche(this));
		retour.addActionListener(new ControleRetourMenu(this));
		}
	
	public static void main(String[] args) throws RemoteException, DejaEnregistreeProf { // Problème, je ne sais pas quoi mettre dans le main...
		RMIServeurImpl s = new RMIServeurImpl();
		ArrayList<ReservationEleve> resael = new ArrayList<ReservationEleve>();
		Eleve el = new Eleve("Lambert", "Zoe", "Femme",21, -2, 76000,resael);
		int[][] dispo = new int[11][7];
		for (int i=0;i<11;i++) {
			for (int j=0;j<7;j++) dispo[i][j]=1;
		}
		ArrayList<ReservationProf> resa = new ArrayList<ReservationProf>();
		ArrayList<ReservationProf> demandesResa = new ArrayList<ReservationProf>();
		EmploiDuTemps edt = new EmploiDuTemps(dispo,resa,demandesResa);
		Prof leprof2 = new Prof("Wade", "Barth", "Homme", 22, 4, 76000, 25, true, edt);
		Prof leprof = new Prof("Guilloteau","Claire","Femme", 21, 4, 76000, 20, true, edt);
		s.getLeBonCours().ajouterProf(s.getLeBonCours().getBio(), leprof);
		s.getLeBonCours().ajouterProf(s.getLeBonCours().getBio(), leprof2);
		FenetreRechercheProf maFenetre = new FenetreRechercheProf(s,el); 
		maFenetre.setVisible(true);
	}
}

class ControleRecherche implements ActionListener {

	FenetreRechercheProf maFenetre;
	String matiere; 
	int jour, heure;
	JOptionPane jop;

	public ControleRecherche(FenetreRechercheProf uneFenetre){
		maFenetre = uneFenetre;
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e){
		
		// recuperation des caracteristiques du cours recherché 
		matiere = (String) maFenetre.matiere_choix.getSelectedItem();
		jour = jourEntier ((String) maFenetre.jour_choix.getSelectedItem());
		heure = heureEntier ((String) maFenetre.heure_choix.getSelectedItem());
		ArrayList<Prof> m = null;
		
		// On récupère la liste de profs correspondant à la matière recherchée
		if (matiere.equals("Bio")) {
			try {
				m = maFenetre.LeBonCoursDistant.getLeBonCours().getBio();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (matiere.equals("Maths")) {
			try {
				m = maFenetre.LeBonCoursDistant.getLeBonCours().getMaths();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (matiere.equals("Physique")) {
			try {
				m = maFenetre.LeBonCoursDistant.getLeBonCours().getPhy();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (matiere.equals("Langues")) {
			try {
				m = maFenetre.LeBonCoursDistant.getLeBonCours().getLangues();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (matiere.equals("Litterature")) {
			try {
				m = maFenetre.LeBonCoursDistant.getLeBonCours().getLitt();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (matiere.equals("Eco")) {
			try {
				m = maFenetre.LeBonCoursDistant.getLeBonCours().getEco();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		ArrayList<Prof> resultat=null;
		try {
			resultat = maFenetre.LeBonCoursDistant.getLeBonCours().RechercheProf(m, jour, heure);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
		// Si la liste des profs dispos est vide, on demande à l'eleve de refaire une recherche
		if (resultat.size()==0) {
			jop = new JOptionPane();
			jop.showMessageDialog(null, "Il n'y a pas de professeur disponible, veuillez ré-effectuer votre recherche en changeant le jour ou l'heure.", "Attention", JOptionPane.WARNING_MESSAGE);
			FenetreRechercheProf fenetreRecherche2 = new FenetreRechercheProf(maFenetre.LeBonCoursDistant, maFenetre.leleve);
			fenetreRecherche2.setVisible(true);
			fenetreRecherche2.pack();
    		
    	}
    	else {
    		maFenetre.setVisible(false);
    		FenetreResultatRecherche fenetreResultat = new FenetreResultatRecherche(maFenetre.LeBonCoursDistant, jour, heure, resultat, maFenetre.leleve);
        	fenetreResultat.setVisible(true);
        	fenetreResultat.pack();
    	}
		
	}
	
	//fonction qui associe le jour choisi par l'eleve en un entier pour faire la recherche dans l'emploi du temps des profs
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
		
	//fonction qui associe l'heure choisie par l'eleve en un entier pour faire la recherche dans l'emploi du temps des profs
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

class ControleRetourMenu implements ActionListener {

	FenetreRechercheProf maFenetre;
	

	public ControleRetourMenu(FenetreRechercheProf uneFenetre){
		maFenetre = uneFenetre;
	}

	@Override
	public void actionPerformed(ActionEvent e){
		maFenetre.setVisible(false);
		FenetreMenuEleve newFenetre = new FenetreMenuEleve(maFenetre.LeBonCoursDistant,maFenetre.leleve);
		newFenetre.setVisible(true);
	}
}