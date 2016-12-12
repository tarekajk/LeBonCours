package insa.projet.leboncours.ihm;

import insa.projet.leboncours.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.lang.Object;;


public class FenetreInscriptionEleve extends JFrame {

	/**
	 * Une police pour le titre du site */
	private final static Font POLICE_TITRE = new Font("Berlin Sans FB",Font.PLAIN,30);
	protected JTextField champ_nom;
	protected JTextField champ_prenom;
	protected JTextField champ_age;
	protected JTextField champ_cp;
	@SuppressWarnings("rawtypes")
	protected JComboBox sexe_choix, niveau_choix;
	protected JButton suivant;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FenetreInscriptionEleve() {
		super("Le bon cours/Inscription Eleve");
		
		//programme se termine quand fenetre fermée
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//création du panel avec toutes les questions
		JPanel panel= new JPanel(new GridLayout(8,2,0,5));
		panel.add(new Canvas());
		panel.add(new Canvas());
		JLabel nom = new JLabel("Nom:");
		champ_nom = new JTextField(20);
		panel.add(nom);
		panel.add(champ_nom);
		JLabel prenom = new JLabel("Prenom:");
		champ_prenom = new JTextField(20);
		panel.add(prenom);
		panel.add(champ_prenom);
		JLabel sexe= new JLabel("Sexe:");
		sexe_choix = new JComboBox();
		sexe_choix.addItem("Homme");
		sexe_choix.addItem("Femme");
		panel.add(sexe);
		panel.add(sexe_choix);
		JLabel age = new JLabel("Age:");
		champ_age = new JTextField(20);
		panel.add(age);
		panel.add(champ_age);
		JLabel niveau = new JLabel("Niveau d'étude:");
		niveau_choix = new JComboBox();
		niveau_choix.addItem("Primaire");
		niveau_choix.addItem("6ème");
		niveau_choix.addItem("5ème");
		niveau_choix.addItem("4ème");
		niveau_choix.addItem("3ème");
		niveau_choix.addItem("Seconde");
		niveau_choix.addItem("Première");
		niveau_choix.addItem("Terminale");
		panel.add(niveau);
		panel.add(niveau_choix);
		JLabel codePost = new JLabel("Code Postal:");
		champ_cp = new JTextField(20);
		panel.add(codePost);
		panel.add(champ_cp);
		suivant = new JButton("SUIVANT");
		panel.add(new Canvas());
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
		suivant.addActionListener(new ControleSuivant(this));
	}



	public static void main(String[] args) {
		FenetreInscriptionEleve maFenetre = new FenetreInscriptionEleve();
		maFenetre.setVisible(true);
	}
}

class ControleSuivant implements ActionListener {

	FenetreInscriptionEleve maFenetre;
	String nom, prenom, age, cp ;
	JOptionPane jop;

	public ControleSuivant(FenetreInscriptionEleve uneFenetre){
		maFenetre = uneFenetre;
	}

	@Override
	public void actionPerformed(ActionEvent e){
		nom = maFenetre.champ_nom.getText();
		prenom = maFenetre.champ_prenom.getText();
		age = maFenetre.champ_age.getText();
		cp = maFenetre.champ_cp.getText();
	
		if((nom.equals("")) || (prenom.equals("")) || (age.equals("")) || (cp.equals("")) ) {
			jop = new JOptionPane();
			jop.showMessageDialog(null, "Un des champs n'a pas été correctement rempli. Vous pourrez modifier vos informations plus tard.", "Attention", JOptionPane.WARNING_MESSAGE);
		}

		maFenetre.setVisible(false);
		FenetreMenuEleve newFenetre = new FenetreMenuEleve();
		newFenetre.setVisible(true);
	}
}
