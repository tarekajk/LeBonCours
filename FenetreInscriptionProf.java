package insa.projet.leboncours.ihm;

import insa.projet.leboncours.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FenetreInscriptionProf extends JFrame {

	/**
	 * Une police pour le titre du site */
	private final static Font POLICE_TITRE = new Font("Berlin Sans FB",Font.PLAIN,30);
	protected JTextField champ;
	protected JTextField champp;
	protected JComboBox sexe_choix, vehicule_choix, niveau_choix;
	protected JButton suivant;
	
	public FenetreInscriptionProf() {
		super("Le bon cours/Inscription Professeur");
		
		//programme se termine quand fenetre fermée
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//création du panel avec toutes les questions
		JPanel panel= new JPanel(new GridLayout(10,2,0,5));
		panel.add(new Canvas());
		panel.add(new Canvas());
		JLabel nom = new JLabel("Nom:");
		champ = new JTextField(20);
		panel.add(nom);
		panel.add(champ);
		JLabel prenom = new JLabel("Prenom:");
		champp = new JTextField(20);
		panel.add(prenom);
		panel.add(champp);
		JLabel sexe= new JLabel("Sexe:");
		sexe_choix = new JComboBox();
		sexe_choix.addItem("Homme");
		sexe_choix.addItem("Femme");
		panel.add(sexe);
		panel.add(sexe_choix);
		JLabel age = new JLabel("Age:");
		champp = new JTextField(20);
		panel.add(age);
		panel.add(champp);
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
		panel.add(niveau);
		panel.add(niveau_choix);
		JLabel codePost = new JLabel("Code Postal:");
		champp = new JTextField(20);
		panel.add(codePost);
		panel.add(champp);
		JLabel prix = new JLabel("Prix de l'heure:");
		champp = new JTextField(20);
		panel.add(prix);
		panel.add(champp);
		JLabel vehicule = new JLabel("Véhiculé ?:");
		vehicule_choix = new JComboBox();
		vehicule_choix.addItem("Oui");
		vehicule_choix.addItem("Non");
		panel.add(vehicule);
		panel.add(vehicule_choix);
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
		JLabel phrasee = new JLabel("Veuillez remplir les champs suivants pour vous inscrire:");
		haut.add(phrasee, BorderLayout.SOUTH);
		
		//remplissage du panel principal
		JPanel mainPanel = (JPanel)this.getContentPane();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(panel, BorderLayout.CENTER);
		mainPanel.add(haut,  BorderLayout.NORTH);
		mainPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		this.pack();
	}
	
	
	
	public static void main(String[] args) {
		FenetreInscriptionProf maFenetre = new FenetreInscriptionProf();
		maFenetre.setVisible(true);
	}
	
}
