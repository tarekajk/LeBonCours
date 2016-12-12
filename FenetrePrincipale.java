package insa.projet.leboncours.ihm;

import insa.projet.leboncours.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;



@SuppressWarnings("serial")
public class FenetrePrincipale extends JFrame{

	/**
	 * Une police pour le titre du site */
	private final static Font POLICE_TITRE = new Font("Berlin Sans FB",Font.PLAIN,30);
	private final static Font POLICE_SSTITRE = new Font("",Font.BOLD,20);
	
	protected JTextField champ_nom;
	protected JTextField champ_prenom;
	protected JButton prof;
	protected JButton eleve;
	protected JButton connecter;
	
	public FenetrePrincipale() {
		super("Le Bon Cours/Accueil");
		//programme se termine quand fenetre fermée
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//création du panel gauche (Identification avec nom prénom)
		JPanel textPanelg= new JPanel(new GridLayout(4,2,0,5));
		JLabel connexion = new JLabel("Connexion");
		connexion.setFont(POLICE_SSTITRE);
		textPanelg.add(connexion);
		textPanelg.add(new Canvas());
		JLabel nom = new JLabel("Nom:");
		champ_nom = new JTextField(10);
		textPanelg.add(nom);
		textPanelg.add(champ_nom);
		JLabel prenom = new JLabel("Prenom:");
		champ_prenom = new JTextField(10);
		textPanelg.add(prenom);
		textPanelg.add(champ_prenom);
		textPanelg.add(new Canvas());
		connecter = new JButton("Se connecter");
		textPanelg.add(connecter); 
		
		//création du panel droit (Inscription)
		JPanel textPaneld= new JPanel(new GridLayout(3,2,5,5));
		JLabel inscription = new JLabel("Inscription");
		inscription.setFont(POLICE_SSTITRE);
		textPaneld.add(inscription);
		textPaneld.add(new Canvas());
		JLabel question = new JLabel("Etes vous un ?");
		textPaneld.add(question);
		textPaneld.add(new Canvas());
		eleve = new JButton("Elève");
		prof = new JButton("Professeur");
		textPaneld.add(eleve);
		textPaneld.add(prof); 
		
		//création du panel nord, avec nom du site
		JLabel titre = new JLabel("leboncours.fr",new ImageIcon("imagecours.png"),SwingConstants.CENTER);
		titre.setFont(POLICE_TITRE);
		
		//création panel vide, pour esthétique
		JLabel milieu = new JLabel("                  ");
		
				
		//remplissage du panel principal
		JPanel mainPanel = (JPanel)this.getContentPane();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(textPanelg, BorderLayout.WEST);
		mainPanel.add(textPaneld, BorderLayout.EAST);
		mainPanel.add(titre,  BorderLayout.NORTH);
		mainPanel.add(milieu, BorderLayout.CENTER);
		mainPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		this.pack();
		
		eleve.addActionListener(new ControleEleve(this));
		prof.addActionListener(new ControleProf(this));
	}
	
	
	
	public static void main(String[] args) {
		FenetrePrincipale maFenetre = new FenetrePrincipale();
		maFenetre.setVisible(true);
	}
	

}


class ControleEleve implements ActionListener {
	
	FenetrePrincipale maFenetre;
	
	public ControleEleve(FenetrePrincipale uneFenetre){
		maFenetre = uneFenetre;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		maFenetre.setVisible(false);
		FenetreInscriptionEleve newFenetre = new FenetreInscriptionEleve();
		newFenetre.setVisible(true);		
	}
}

class ControleProf implements ActionListener {
		
	FenetrePrincipale maFenetre;
		
	public ControleProf(FenetrePrincipale uneFenetre){
			maFenetre = uneFenetre;
	}
		
	@Override
	public void actionPerformed(ActionEvent e){
			maFenetre.setVisible(false);
			FenetreInscriptionProf newFenetre = new FenetreInscriptionProf();
			newFenetre.setVisible(true);			
	}	
	
}

class ControleConnec implements ActionListener {
	
	String nom, prenom;
	FenetrePrincipale maFenetre;
		
	public ControleConnec(FenetrePrincipale uneFenetre){
			maFenetre = uneFenetre;
	}
		
	@Override
	public void actionPerformed(ActionEvent e){
		
			nom = maFenetre.champ_nom.getText();
			prenom = maFenetre.champ_prenom.getText();
			
		    //la il faut faire la recherche dans la base de données
			
			maFenetre.setVisible(false);
			FenetreInscriptionProf newFenetre = new FenetreInscriptionProf();
			newFenetre.setVisible(true);			
	}	
	
}
	


