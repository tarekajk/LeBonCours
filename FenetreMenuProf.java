package insa.projet.leboncours.ihm;

import insa.projet.leboncours.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FenetreMenuProf extends JFrame {

	/**
	 * Une police pour le titre du site */
	private final static Font POLICE_TITRE = new Font("Berlin Sans FB",Font.PLAIN,30);
	private final static Font POLICE_SSTITRE = new Font("",Font.BOLD,20);
	protected JButton modif_edt;
	protected JButton voir;
	protected JButton annul;
	protected JButton rep;
	protected JButton quit;
	protected JButton suppr;
	protected JButton modif_infos;
	
	public FenetreMenuProf() {
		super("Le bon cours/Menu Professeur");
		
		//programme se termine quand fenetre fermée
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//création du panel avec toutes les questions
		JPanel panel= new JPanel(new GridLayout(8,0,0,15));
		voir = new JButton("Voir mes cours");
		rep = new JButton("Mes demandes de réservation");
		annul = new JButton("Annuler un cours");
		modif_edt = new JButton("Modifier mon emploi du temps");
		modif_infos = new JButton("Modifier mes informations");
		suppr = new JButton("Supprimer mon compte");
		quit = new JButton("Quitter l'application");
		panel.add(new Canvas());
		panel.add(voir);  
		panel.add(rep);
		panel.add(annul);
		panel.add(modif_edt);
		panel.add(modif_infos);
		panel.add(suppr);
		panel.add(quit);
		
		
		//création du panel nord, avec nom du site
		JPanel haut = new JPanel();
		haut.setLayout(new BorderLayout());
		
		JLabel titre = new JLabel("leboncours.fr",new ImageIcon("imagecours.png"),SwingConstants.CENTER);
		titre.setFont(POLICE_TITRE);
		JLabel phrase = new JLabel("Mon menu professeur", SwingConstants.CENTER);
		phrase.setFont(POLICE_SSTITRE);
		haut.add(titre,  BorderLayout.NORTH);
		JLabel saut = new JLabel("              ");
		haut.add(saut, BorderLayout.CENTER);
		haut.add(phrase,  BorderLayout.SOUTH);
				
		//remplissage du panel principal
		JPanel mainPanel = (JPanel)this.getContentPane();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(panel, BorderLayout.CENTER);
		mainPanel.add(haut,  BorderLayout.NORTH);
		mainPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		this.pack();
		modif_edt.addActionListener(new ControleModifEdt(this));
		modif_infos.addActionListener(new ControleModifInfos(this));
		suppr.addActionListener(new ControleSupprimer(this));
		quit.addActionListener(new ControleQuitter(this));
	}
	
	
	
	public static void main(String[] args) {
		FenetreMenuProf maFenetre = new FenetreMenuProf();
		maFenetre.setVisible(true);
	}
	
}

class ControleModifEdt implements ActionListener {

	FenetreMenuProf maFenetre;
	
	public ControleModifEdt(FenetreMenuProf uneFenetre){
		maFenetre = uneFenetre;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		maFenetre.setVisible(false);
		FenetreEmploiDuTempsModif newFenetre = new FenetreEmploiDuTempsModif();   //rmiserveur et prof a mettre en entrée
		newFenetre.setVisible(true);			
	}	
	
}

class ControleModifInfos implements ActionListener {

	FenetreMenuProf maFenetre;
	
	public ControleModifInfos(FenetreMenuProf uneFenetre){
		maFenetre = uneFenetre;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		maFenetre.setVisible(false);
		FenetreModifInfosProf newFenetre = new FenetreModifInfosProf();   //rmiserveur et prof a mettre en entrée
		newFenetre.setVisible(true);			
	}	
	
}

class ControleSupprimer implements ActionListener {

	FenetreMenuProf maFenetre;
	
	public ControleSupprimer(FenetreMenuProf uneFenetre){
		maFenetre = uneFenetre;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		// /!\ A FAIRE /!\
		//SUPPRIMER LE COMPTE DE L'ELEVE
		// /!\ A FAIRE /!\
		maFenetre.setVisible(false);
		FenetrePrincipale newFenetre = new FenetrePrincipale();   //mettre serveur entrée
		newFenetre.setVisible(true);						//ON REVIENT AU MENU PRINCIPAL APRES SUPPRESSION COMPTE??
	}	
	
}

class ControleQuitter implements ActionListener {

	FenetreMenuProf maFenetre;
	
	public ControleQuitter(FenetreMenuProf uneFenetre){
		maFenetre = uneFenetre;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		System.exit(0);			
	}	
	
}