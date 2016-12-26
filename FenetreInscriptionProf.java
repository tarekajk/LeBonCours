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

public class FenetreInscriptionProf extends JFrame {

	RMIServeur LeBonCoursDistant;
	
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
	
	public FenetreInscriptionProf(RMIServeur r) {
		super("Le bon cours/Inscription Professeur");
		LeBonCoursDistant = r;
		
		//programme se termine quand fenetre ferm�e
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//cr�ation du panel avec toutes les questions
		JPanel panel= new JPanel(new GridLayout(13,3,2,5));
		panel.add(new Canvas());
		panel.add(new Canvas());
		panel.add(new Canvas());
		JLabel nom = new JLabel("Nom:");
		champ_nom = new JTextField(10);
		panel.add(nom);
		panel.add(champ_nom);
		panel.add(new Canvas());
		JLabel prenom = new JLabel("Prenom:");
		champ_prenom = new JTextField(10);
		panel.add(prenom);
		panel.add(champ_prenom);
		panel.add(new Canvas());
		JLabel sexe= new JLabel("Sexe:");
		sexe_choix = new JComboBox();
		sexe_choix.addItem("Homme");
		sexe_choix.addItem("Femme");
		panel.add(sexe);
		panel.add(sexe_choix);
		panel.add(new Canvas());
		JLabel age = new JLabel("Age:");
		champ_age = new JTextField("0",10);
		panel.add(age);
		panel.add(champ_age);
		panel.add(new Canvas());
		JLabel niveau = new JLabel("Niveau d'�tude:");
		niveau_choix = new JComboBox();
		niveau_choix.addItem("BAC");
		niveau_choix.addItem("BAC +1");
		niveau_choix.addItem("BAC +2");
		niveau_choix.addItem("BAC +3");
		niveau_choix.addItem("BAC +4");
		niveau_choix.addItem("BAC +5");
		niveau_choix.addItem("BAC +6");
		niveau_choix.addItem("BAC +7");
		panel.add(niveau);
		panel.add(niveau_choix);
		panel.add(new Canvas());
		JLabel codePost = new JLabel("Code Postal:");
		champ_cp = new JTextField("00000",10);
		panel.add(codePost);
		panel.add(champ_cp);
		panel.add(new Canvas());
		JLabel prix = new JLabel("Prix de l'heure:");
		champ_prix = new JTextField("0",10);
		panel.add(prix);
		panel.add(champ_prix);
		panel.add(new Canvas());
		JLabel vehicule = new JLabel("V�hicul� ?:");
		vehicule_choix = new JComboBox();
		vehicule_choix.addItem("Oui");
		vehicule_choix.addItem("Non");
		panel.add(vehicule);
		panel.add(vehicule_choix);
		panel.add(new Canvas());
		JLabel cours = new JLabel("Mati�res dispens�es:");
		bout_bio = new JRadioButton("Biologie",false);
		bout_maths = new JRadioButton("Math�matiques",false);
		bout_phy = new JRadioButton("Physique",false);
		bout_lang = new JRadioButton("Langues",false);
		bout_litt = new JRadioButton("Litt�rature",false);
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
		
		
		//cr�ation du panel nord, avec nom du site
		JPanel haut = new JPanel();
		haut.setLayout(new BorderLayout());
		
		JLabel titre = new JLabel("leboncours.fr",new ImageIcon("imagecours.png"),SwingConstants.CENTER);
		titre.setFont(POLICE_TITRE);
		haut.add(titre, BorderLayout.NORTH);
		JLabel saut = new JLabel("              ");
		haut.add(saut, BorderLayout.CENTER);
		JLabel phrasee = new JLabel("Veuillez remplir les champs suivants pour vous inscrire:");
		haut.add(phrasee, BorderLayout.SOUTH);
		
		//remplissage du panel principal
		JPanel mainPanel = (JPanel)this.getContentPane();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(panel, BorderLayout.CENTER);
		mainPanel.add(haut,  BorderLayout.NORTH);
		mainPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		this.pack();
		suivant.addActionListener(new ControleeSuivant(this));
	}



	public static void main(String[] args) throws RemoteException {
		RMIServeurImpl r = new RMIServeurImpl();
		FenetreInscriptionProf maFenetre = new FenetreInscriptionProf(r);
		maFenetre.setVisible(true);
	}
}

class ControleeSuivant implements ActionListener {

	FenetreInscriptionProf maFenetre;
	String nom, prenom, age, cp, prix, sexe; 
	int niveau, agee;  //agee sert pour le constructeur de prof, il faut un int, au debut on utilise age pour comparer avec vide
	float prixx;   //pareil pour prixx et cpp ce sont des cast pour construire le prof
	long cpp;
	boolean vehicule, bio, math, phy, lang, litt, eco;
	JOptionPane jop;

	public ControleeSuivant(FenetreInscriptionProf uneFenetre){
		maFenetre = uneFenetre;
	}

	@Override
	public void actionPerformed(ActionEvent e){
		// r�cup�ration des TextField pour voir s'ils sont vides 
		nom = maFenetre.champ_nom.getText();
		prenom = maFenetre.champ_prenom.getText();
		age = maFenetre.champ_age.getText();
		cp = maFenetre.champ_cp.getText();
		prix = maFenetre.champ_prix.getText();
	
		if((nom.equals("")) || (prenom.equals("")) || (age.equals("0")) || (cp.equals("00000")) || (prix.equals("0")) ) {
			jop = new JOptionPane();
			jop.showMessageDialog(null, "Un des champs n'a pas �t� correctement rempli. Veuillez recommencer.", "Attention", JOptionPane.WARNING_MESSAGE);
			maFenetre.setVisible(false);
			FenetreInscriptionProf newFenetre = new FenetreInscriptionProf(maFenetre.LeBonCoursDistant); 
			newFenetre.setVisible(true);
		}
		else {
		// Cast des donn�es qui �taient en String dans leur type qui correspond au constructeur de prof
		agee = Integer.parseInt(age);
		prixx = Float.parseFloat(prix);
		cpp = Long.valueOf(cp).longValue();

		// r�cup�ration des autres infos pour ensuite les associer au profil cr��
		sexe = (String) maFenetre.sexe_choix.getSelectedItem();
		niveau = niveauProf((String) maFenetre.niveau_choix.getSelectedItem());
		vehicule =  vehiculeBoolean((String) maFenetre.vehicule_choix.getSelectedItem());
		
		Prof prof = new Prof(nom, prenom, sexe, agee, niveau, cpp, prixx,vehicule, null);
		
		//r�cup�ration des matieres dispens�es par le prof
		bio = maFenetre.bout_bio.isSelected();
		math = maFenetre.bout_maths.isSelected();
		phy = maFenetre.bout_phy.isSelected();
		lang = maFenetre.bout_lang.isSelected();
		litt = maFenetre.bout_litt.isSelected();
		eco = maFenetre.bout_eco.isSelected();
		
		
		//on ajoute le prof aux arraylist des mati�res qu'il a choisi
		
		try {
		if (bio) {
			maFenetre.LeBonCoursDistant.getLeBonCours().ajouterProf(maFenetre.LeBonCoursDistant.getLeBonCours().bio, prof);
			}
		if (math) {
			maFenetre.LeBonCoursDistant.getLeBonCours().ajouterProf(maFenetre.LeBonCoursDistant.getLeBonCours().maths, prof);
		}
		if (phy) {
			maFenetre.LeBonCoursDistant.getLeBonCours().ajouterProf(maFenetre.LeBonCoursDistant.getLeBonCours().phy, prof);
		}
		if (lang) {
			maFenetre.LeBonCoursDistant.getLeBonCours().ajouterProf(maFenetre.LeBonCoursDistant.getLeBonCours().langues, prof);
		}
		if (litt) {
			maFenetre.LeBonCoursDistant.getLeBonCours().ajouterProf(maFenetre.LeBonCoursDistant.getLeBonCours().litt, prof);
		}
		if (eco) {
			maFenetre.LeBonCoursDistant.getLeBonCours().ajouterProf(maFenetre.LeBonCoursDistant.getLeBonCours().eco, prof);
		}
		} catch (RemoteException | DejaEnregistreeProf e1) {
			e1.printStackTrace();
		}
		
		int i;
		try {
			i = maFenetre.LeBonCoursDistant.getLeBonCours().getListeProfs().indexOf(prof);
			maFenetre.setVisible(false);
			FenetreEmploiDuTempsInit newFenetre = new FenetreEmploiDuTempsInit(maFenetre.LeBonCoursDistant,i);
			newFenetre.setVisible(true);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		}
	}
	
	//fonction qui associe le niveau du prof choisi dans l'interface � un integer pour le code
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
	
	//fonction qui associe la r�ponse � la question v�hicul�? en booleen
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