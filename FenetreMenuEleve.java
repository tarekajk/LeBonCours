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

public class FenetreMenuEleve extends JFrame {

	RMIServeur LeBonCoursDistant;
	Eleve eleve;
	
	/**
	 * Une police pour le titre du site */
	private final static Font POLICE_TITRE = new Font("Berlin Sans FB",Font.PLAIN,30);
	private final static Font POLICE_SSTITRE = new Font("",Font.BOLD,20);
	protected JButton rech;
	protected JButton voir;
	protected JButton suppr;
	protected JButton quit;
	protected JButton annul;
	protected JButton modif_infos;
	
	public FenetreMenuEleve(RMIServeur r, Eleve e) {
		super("Le bon cours/Menu Eleve");
		LeBonCoursDistant =r;
		eleve =e;
		
		//programme se termine quand fenetre fermée
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//création du panel avec toutes les questions
		JPanel panel= new JPanel(new GridLayout(7,0,0,15));
		rech = new JButton("Rechercher un cours");
		voir = new JButton("Voir mes cours");
		annul = new JButton("Annuler un cours");
		modif_infos = new JButton("Modifier mes informations");
		suppr = new JButton("Supprimer mon compte");
		quit = new JButton("Quitter l'application");
		
		panel.add(new Canvas());
		panel.add(rech);  
		panel.add(voir);
		panel.add(annul);
		panel.add(modif_infos);
		panel.add(suppr);
		panel.add(quit);
		
		
		//création du panel nord, avec nom du site
		JPanel haut = new JPanel();
		haut.setLayout(new BorderLayout());
		
		JLabel titre = new JLabel("leboncours.fr",new ImageIcon("imagecours.png"),SwingConstants.CENTER);
		titre.setFont(POLICE_TITRE);
		JLabel phrase = new JLabel("Mon menu élève", SwingConstants.CENTER);
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
		rech.addActionListener(new ControleRechercheEleve(this));
		voir.addActionListener(new ControleVoirEleve(this));
		annul.addActionListener(new ControleAnnuleEleve(this));
		rech.addActionListener(new ControleRechercheEleve(this));
		modif_infos.addActionListener(new ControleModifInfosEleve(this));
		suppr.addActionListener(new ControleSupprimerEleve(this));
		quit.addActionListener(new ControleQuitterEleve(this));
	}
	
	
	
	public static void main(String[] args) throws RemoteException {
		RMIServeurImpl r = new RMIServeurImpl();
		Prof leprof = new Prof("Ajk","Tarek","Homme", 21, 4, 76000, 25, true, null);
		ArrayList<ReservationEleve> resael = new ArrayList<ReservationEleve>();
		ReservationEleve uneresa = new ReservationEleve(1,10,leprof);
		resael.add(uneresa);
		Eleve leleve = new Eleve("Guilloteau","Claire","Femme", 16, -4, 76000, resael);
		FenetreMenuEleve maFenetre = new FenetreMenuEleve(r, leleve);
		maFenetre.setVisible(true);
	}
	
}

class ControleRechercheEleve implements ActionListener {

	FenetreMenuEleve maFenetre;
	
	public ControleRechercheEleve(FenetreMenuEleve uneFenetre){
		maFenetre = uneFenetre;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		maFenetre.setVisible(false);
		FenetreRechercheProf newFenetre;
		newFenetre = new FenetreRechercheProf(maFenetre.LeBonCoursDistant,maFenetre.eleve);
		newFenetre.setVisible(true);				
	}	
}

class ControleVoirEleve implements ActionListener {

	FenetreMenuEleve maFenetre;
	
	public ControleVoirEleve(FenetreMenuEleve uneFenetre){
		maFenetre = uneFenetre;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		maFenetre.setVisible(false);
		FenetreVoirCoursEleve newFenetre;
		newFenetre = new FenetreVoirCoursEleve(maFenetre.LeBonCoursDistant,maFenetre.eleve);
		newFenetre.setVisible(true);
	}	
}

class ControleAnnuleEleve implements ActionListener {

	FenetreMenuEleve maFenetre;
	
	public ControleAnnuleEleve(FenetreMenuEleve uneFenetre){
		maFenetre = uneFenetre;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		maFenetre.setVisible(false);
		FenetreAnnuleCoursEleve newFenetre;
		newFenetre = new FenetreAnnuleCoursEleve(maFenetre.LeBonCoursDistant,maFenetre.eleve);
		newFenetre.setVisible(true);
	}	
}

class ControleModifInfosEleve implements ActionListener {

	FenetreMenuEleve maFenetre;
	
	public ControleModifInfosEleve(FenetreMenuEleve uneFenetre){
		maFenetre = uneFenetre;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		maFenetre.setVisible(false);
		FenetreModifInfosEleve newFenetre;
		newFenetre = new FenetreModifInfosEleve(maFenetre.LeBonCoursDistant,maFenetre.eleve);
		newFenetre.setVisible(true);				
	}	
}

class ControleSupprimerEleve implements ActionListener {

	FenetreMenuEleve maFenetre;
	
	public ControleSupprimerEleve(FenetreMenuEleve uneFenetre){
		maFenetre = uneFenetre;
	}


@Override
	public void actionPerformed(ActionEvent e){
		try {
			maFenetre.LeBonCoursDistant.getLeBonCours().supprimerEleve(maFenetre.eleve);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		maFenetre.setVisible(false);
		FenetrePrincipale newFenetre = new FenetrePrincipale(maFenetre.LeBonCoursDistant); 
		newFenetre.setVisible(true);				//ON REVIENT AU MENU PRINCIPAL APRES SUPPRESSION COMPTE?? OUI
	}	
}

class ControleQuitterEleve implements ActionListener {

	FenetreMenuEleve maFenetre;

	public ControleQuitterEleve(FenetreMenuEleve uneFenetre){
	maFenetre = uneFenetre;
	}


	@Override
	public void actionPerformed(ActionEvent e){
	System.exit(0);			
	}	

}
