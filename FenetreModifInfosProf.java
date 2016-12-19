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

public class FenetreModifInfosProf extends JFrame {

	RMIServeur LeBonCoursDistant;
	Prof leprof;
	
	/**
	 * Une police pour le titre du site */
	private final static Font POLICE_TITRE = new Font("Berlin Sans FB",Font.PLAIN,30);
	protected JTextField champ_nom;
	protected JTextField champ_prenom;
	protected JTextField champ_age;
	protected JTextField champ_cp;
	protected JTextField champ_prix;
	protected JComboBox sexe_choix, vehicule_choix, niveau_choix;
	protected JButton suivant;
	protected JRadioButton bout_bio, bout_maths, bout_phy, bout_lang, bout_litt, bout_eco;
	
	public FenetreModifInfosProf(RMIServeur r, Prof p) {
		super("Le bon cours/Menu Professeur/Modifier mes informations");
		LeBonCoursDistant = r;
		leprof = p;
		
		//programme se termine quand fenetre fermée
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// /!\ A FAIRE /!\
		//ICI IL FAUT SUPPRIMER LE PROF DES ARRAYLIST DE MATIERES OU IL EST
		//RAPPEL : on va le réajouter dans les nouvelles arraylist au moment de l'action listener
		// /!\ A FAIRE /!\
		
		//création du panel avec toutes les questions
		JPanel panel= new JPanel(new GridLayout(13,3,2,5));
		panel.add(new Canvas());
		panel.add(new Canvas());
		panel.add(new Canvas());
		JLabel nom = new JLabel("Nom:");
		champ_nom = new JTextField(leprof.getNom(),10);
		panel.add(nom);
		panel.add(champ_nom);
		panel.add(new Canvas());
		JLabel prenom = new JLabel("Prenom:");
		champ_prenom = new JTextField(leprof.getPrenom(),10);
		panel.add(prenom);
		panel.add(champ_prenom);
		panel.add(new Canvas());
		JLabel sexe= new JLabel("Sexe:");
		sexe_choix = new JComboBox();
		sexe_choix.addItem("Homme");
		sexe_choix.addItem("Femme");
		sexe_choix.setSelectedItem(leprof.getSexe());  
		panel.add(sexe);
		panel.add(sexe_choix);
		panel.add(new Canvas());
		JLabel age = new JLabel("Age:");
		champ_age = new JTextField(Integer.toString(leprof.getAge()),10);
		panel.add(age);
		panel.add(champ_age);
		panel.add(new Canvas());
		JLabel niveau = new JLabel("Niveau d'étude:");
		niveau_choix = new JComboBox();
		niveau_choix.addItem("BAC");
		niveau_choix.addItem("BAC +1");
		niveau_choix.addItem("BAC +2");
		niveau_choix.addItem("BAC +3");
		niveau_choix.addItem("BAC +4");
		niveau_choix.addItem("BAC +5");
		niveau_choix.addItem("BAC +6");
		niveau_choix.addItem("BAC +7");
		niveau_choix.setSelectedIndex(leprof.getNiveau());      
		panel.add(niveau);
		panel.add(niveau_choix);
		panel.add(new Canvas());
		JLabel codePost = new JLabel("Code Postal:");
		champ_cp = new JTextField(Long.toString(leprof.getCodePostal()),10);
		panel.add(codePost);
		panel.add(champ_cp);
		panel.add(new Canvas());
		JLabel prix = new JLabel("Prix de l'heure:");
		champ_prix = new JTextField(Float.toString(leprof.getPrix()),10);
		panel.add(prix);
		panel.add(champ_prix);
		panel.add(new Canvas());
		JLabel vehicule = new JLabel("Véhiculé ?:");
		vehicule_choix = new JComboBox();
		vehicule_choix.addItem("Oui");
		vehicule_choix.addItem("Non");
		vehicule_choix.setSelectedItem(IsVoitureToComboBox(leprof.isVoiture()));      //A VERIFIER
		panel.add(vehicule);
		panel.add(vehicule_choix);
		panel.add(new Canvas());
		JLabel cours = new JLabel("Matières dispensées:");
		bout_bio = new JRadioButton("Biologie",false);
		// /!\ A FAIRE /!\
		//LA IL FAUT CHERCHER LE PROF DANS L'ARRAYLIST DE LA MATIERE ET S'IL EST DEDANS FAUT INITIALISER A TRUE! PAREIL POUR CHAQUE MATIERE
		// /!\ A FAIRE /!\
		//bout_bio.setEnabled(boolean b) il faut mettre ça et selon si tu mets true ou false dans les parentheses ca te coche ou non
		 // il faut le faire pour chaque matière
		bout_maths = new JRadioButton("Mathématiques",false);
		bout_phy = new JRadioButton("Physique",false);
		bout_lang = new JRadioButton("Langues",false);
		bout_litt = new JRadioButton("Littérature",false);
		bout_eco = new JRadioButton("Economie",false);
		panel.add(cours);
		panel.add(bout_bio);
		panel.add(bout_maths);
		panel.add(new Canvas()); 
		panel.add(bout_phy);
		panel.add(bout_lang);
		panel.add(new Canvas()); 
		panel.add(bout_litt);
		panel.add(bout_eco);
		
		suivant = new JButton("SUIVANT");
		panel.add(new Canvas());   //ajoute un vide
		panel.add(suivant); 
		
		
		//création du panel nord, avec nom du site
		JPanel haut = new JPanel();
		haut.setLayout(new BorderLayout());
		
		JLabel titre = new JLabel("leboncours.fr",new ImageIcon("imagecours.png"),SwingConstants.CENTER);
		titre.setFont(POLICE_TITRE);
		haut.add(titre, BorderLayout.NORTH);
		JLabel saut = new JLabel("              ");
		haut.add(saut, BorderLayout.CENTER);
		JLabel phrasee = new JLabel("Voici les informations que vous nous avez fourni. Vous pouvez les compléter ou les mettre à jour.");
		haut.add(phrasee, BorderLayout.SOUTH);
		
		//remplissage du panel principal
		JPanel mainPanel = (JPanel)this.getContentPane();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(panel, BorderLayout.CENTER);
		mainPanel.add(haut,  BorderLayout.NORTH);
		mainPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		this.pack();
		suivant.addActionListener(new ControleSuivantModifInfosProf(this));
	}
	
	
	public String IsVoitureToComboBox(boolean vehic){
		String vehicule= "";
		if (vehic == true){
			 vehicule= "Oui";}
		if (vehic == false){
			 vehicule= "Non";}
		return vehicule;
	}



	public static void main(String[] args) throws RemoteException {
		int[][] dispo = new int[11][7];
		for (int i=0;i<11;i++) {
			for (int j=0;j<7;j++) dispo[i][j]=1;
		}
		ArrayList<ReservationProf> resa = new ArrayList<ReservationProf>();
		EmploiDuTemps edt = new EmploiDuTemps(dispo,resa);
		
		Prof leprof = new Prof("Guilloteau","Claire","Femme", 21, 4, 76000, 20, false, edt);
		RMIServeurImpl r = new RMIServeurImpl();
		FenetreModifInfosProf maFenetre = new FenetreModifInfosProf(r,leprof);
		maFenetre.setVisible(true);
	}
}

class ControleSuivantModifInfosProf implements ActionListener {

	FenetreModifInfosProf maFenetre;
	String nom, prenom, age, cp, prix, sexe; 
	int niveau, agee;  //agee sert pour le constructeur de prof, il faut un int, au debut on utilise age pour comparer avec vide
	float prixx;   //pareil pour prixx et cpp ce sont des cast pour construire le prof
	long cpp;
	boolean vehicule, bio, math, phy, lang, litt, eco;
	JOptionPane jop;

	public ControleSuivantModifInfosProf(FenetreModifInfosProf uneFenetre){
		maFenetre = uneFenetre;
	}

	@Override
	public void actionPerformed(ActionEvent e){
		// récupération des TextField pour voir s'ils sont vides 
		nom = maFenetre.champ_nom.getText();
		prenom = maFenetre.champ_prenom.getText();
		age = maFenetre.champ_age.getText();
		cp = maFenetre.champ_cp.getText();
		prix = maFenetre.champ_prix.getText();
	
		if((nom.equals("")) || (prenom.equals("")) || (age.equals("0")) || (cp.equals("00000")) || (prix.equals("0")) ) {
			jop = new JOptionPane();
			jop.showMessageDialog(null, "Un des champs n'a pas été correctement rempli. Vous pourrez le remplir de nouveau dans l'onglet \"Modifier mes informations\" de votre menu.", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		
		// Cast des données qui étaient en String dans leur type qui correspond au constructeur de prof
		agee = Integer.parseInt(age);
		prixx = Float.parseFloat(prix);
		cpp = Long.valueOf(cp).longValue();

		// récupération des autres infos pour ensuite les associer au profil créé
		sexe = (String) maFenetre.sexe_choix.getSelectedItem();
		niveau = niveauProf((String) maFenetre.niveau_choix.getSelectedItem());
		vehicule =  vehiculeBoolean((String) maFenetre.vehicule_choix.getSelectedItem());
		
		// /!\ A FAIRE /!\
		// il faut associer : nom, prenom, sexe, agee, niveau, cpp, prixx,vehicule au prof en entrée de la fenetre
		// /!\ A FAIRE /!\

		
		//récupération des matieres dispensées par le prof
		bio = maFenetre.bout_bio.isSelected();
		math = maFenetre.bout_maths.isSelected();
		phy = maFenetre.bout_phy.isSelected();
		lang = maFenetre.bout_lang.isSelected();
		litt = maFenetre.bout_litt.isSelected();
		eco = maFenetre.bout_eco.isSelected();
		
		// /!\A FAIRE /!\
		// IL FAUT AJOUTER LE PROF AUX ARRAYLIST DES MATIERES OU C'EST TRUE. (se servir de ce qui a été fait dans fenetre inscription prof
		// /!\A FAIRE /!\
		
		maFenetre.setVisible(false);
		FenetreMenuProf newFenetre = new FenetreMenuProf();
		newFenetre.setVisible(true);
	}
	
	//fonction qui associe le niveau du prof choisi dans l'interface à un integer pour le code
	public int niveauProf(String niv){
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
	
	//fonction qui associe la réponse à la question véhiculé? en booleen
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
