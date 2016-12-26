package insa.projet.leboncours.ihm;


import insa.projet.leboncours.DejaEnregistreeEleve;
import insa.projet.leboncours.Eleve;
import insa.projet.leboncours.Prof;
import insa.projet.leboncours.rmi.RMIServeur;
import insa.projet.leboncours.rmi.RMIServeurImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;



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
	
	RMIServeur LeBonCoursDistant;
	
	public FenetrePrincipale(RMIServeur r) {
		super("Le Bon Cours/Accueil");
		LeBonCoursDistant = r;
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
		connecter.addActionListener(new ControleConnec(this));
	}
	
	
	
	public static void main(String[] args) throws RemoteException, DejaEnregistreeEleve {
		RMIServeurImpl r = new RMIServeurImpl();
		Prof prof = new Prof("AJK","Tarek","Homme",21,4,76100,20,true,null);
		Eleve eleve = new Eleve("Wade","Barth","Homme",18,0,76000,null);
		r.getLeBonCours().getListeProfs().add(prof);
		r.getLeBonCours().ajouterEleve(eleve);
		FenetrePrincipale maFenetre = new FenetrePrincipale(r);
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
		FenetreInscriptionEleve newFenetre;
			newFenetre = new FenetreInscriptionEleve(maFenetre.LeBonCoursDistant);
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
		FenetreInscriptionProf newFenetre = new FenetreInscriptionProf(maFenetre.LeBonCoursDistant);
		newFenetre.setVisible(true);			
	}	
	
}

class ControleConnec implements ActionListener {
	
	String nom, prenom;
	FenetrePrincipale maFenetre;
	JOptionPane jop;
		
	public ControleConnec(FenetrePrincipale uneFenetre){
			maFenetre = uneFenetre;
	}
		
	@Override
	public void actionPerformed(ActionEvent e){
		
		nom = maFenetre.champ_nom.getText();
		prenom = maFenetre.champ_prenom.getText();
		
		try {
			if (maFenetre.LeBonCoursDistant.getLeBonCours().ConnexionEleve(nom, prenom)!=null){
				maFenetre.setVisible(false);
				FenetreMenuEleve newFenetre = new FenetreMenuEleve(maFenetre.LeBonCoursDistant,maFenetre.LeBonCoursDistant.getLeBonCours().ConnexionEleve(nom, prenom));//maFenetre.LeBonCoursDistant,maFenetre.LeBonCoursDistant.getLeBonCours().ConnexionEleve(nom, prenom)
				newFenetre.setVisible(true);
			}
			else if(maFenetre.LeBonCoursDistant.getLeBonCours().ConnexionProf(nom, prenom)!=null) {
				maFenetre.setVisible(false);
				FenetreMenuProf newFenetre = new FenetreMenuProf(maFenetre.LeBonCoursDistant,maFenetre.LeBonCoursDistant.getLeBonCours().ConnexionProf(nom, prenom));
				newFenetre.setVisible(true);
			}
			else {
				jop = new JOptionPane();
				JOptionPane.showMessageDialog(null, "Les informations saisies ne correspondent à aucun compte déjà existant. Veuillez réessayer.", "Attention", JOptionPane.WARNING_MESSAGE);
				maFenetre.setVisible(false);
				FenetrePrincipale newFenetre = new FenetrePrincipale(maFenetre.LeBonCoursDistant); 
				newFenetre.setVisible(true);
			}
		} catch (RemoteException e1) {
				e1.printStackTrace();
		}
					
	}	
	
}
	


