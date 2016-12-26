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

public class FenetreMenuProf extends JFrame {
	
	RMIServeur LeBonCoursDistant;
	Prof prof;

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
	
	public FenetreMenuProf(RMIServeur r, Prof p) {
		super("Le bon cours/Menu Professeur");
		LeBonCoursDistant =r;
		prof =p;
		
		//programme se termine quand fenetre fermée
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//création du panel avec tous les choix
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
		voir.addActionListener(new ControleVoirProf(this));
		rep.addActionListener(new ControleRepondreProf(this));
		annul.addActionListener(new ControleAnnuleProf(this));
		modif_edt.addActionListener(new ControleModifEdt(this));
		modif_infos.addActionListener(new ControleModifInfos(this));
		suppr.addActionListener(new ControleSupprimer(this));
		quit.addActionListener(new ControleQuitter(this));
		
		// Affiche une fenêtre pour indiquer qu'il y a de nouvelles demandes de résa en attente
		if (!prof.getEdt().getDemandesCours().isEmpty()) {
			JOptionPane jop = new JOptionPane();
			JOptionPane.showMessageDialog(null, "Vous avez de nouvelles demandes de réservation en attente.", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	
	
	public static void main(String[] args) throws RemoteException {
		RMIServeurImpl r = new RMIServeurImpl();
		int[][] dispo = new int[11][7];
		for (int i=0;i<11;i++) {
			for (int j=0;j<7;j++) dispo[i][j]=1;
		}
		Eleve leleve = new Eleve("Lambert","Zoe","Femme", 16, -4, 76000, null);
		ArrayList<ReservationProf> resa = new ArrayList<ReservationProf>();
		ReservationProf uneresa = new ReservationProf(1,10,leleve);
		resa.add(uneresa);
		ArrayList<ReservationProf> demandes = new ArrayList<ReservationProf>();
		ReservationProf unedem = new ReservationProf(1,9,leleve);
		demandes.add(unedem);
		EmploiDuTemps edt = new EmploiDuTemps(dispo,resa,demandes);
		Prof leprof = new Prof("Guilloteau","Claire","Femme", 21, 4, 76000, 20, true, edt);
		FenetreMenuProf maFenetre = new FenetreMenuProf(r,leprof);
		maFenetre.setVisible(true);
	}
	
}

class ControleVoirProf implements ActionListener {

	FenetreMenuProf maFenetre;
	
	public ControleVoirProf(FenetreMenuProf uneFenetre){
		maFenetre = uneFenetre;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		maFenetre.setVisible(false);
		FenetreVoirCoursProf newFenetre = new FenetreVoirCoursProf(maFenetre.LeBonCoursDistant,maFenetre.prof);
		newFenetre.setVisible(true);
	}	
}

class ControleRepondreProf implements ActionListener {

	FenetreMenuProf maFenetre;
	
	public ControleRepondreProf(FenetreMenuProf uneFenetre){
		maFenetre = uneFenetre;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		// /!\ A FAIRE /!\
		// Compléter une fois que la fenetre a été créée
		// /!\ A FAIRE /!\
	}	
}

class ControleAnnuleProf implements ActionListener {

	FenetreMenuProf maFenetre;
	
	public ControleAnnuleProf(FenetreMenuProf uneFenetre){
		maFenetre = uneFenetre;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		maFenetre.setVisible(false);
		FenetreAnnuleCoursProf newFenetre = new FenetreAnnuleCoursProf(maFenetre.LeBonCoursDistant,maFenetre.prof);
		newFenetre.setVisible(true);
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
		FenetreEmploiDuTempsModif newFenetre = new FenetreEmploiDuTempsModif(maFenetre.LeBonCoursDistant,maFenetre.prof);
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
		FenetreModifInfosProf newFenetre;
		try {
			newFenetre = new FenetreModifInfosProf(maFenetre.LeBonCoursDistant,maFenetre.prof);
			newFenetre.setVisible(true);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
					
	}	
	
}

class ControleSupprimer implements ActionListener {

	FenetreMenuProf maFenetre;
	
	public ControleSupprimer(FenetreMenuProf uneFenetre){
		maFenetre = uneFenetre;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		try {
			maFenetre.LeBonCoursDistant.getLeBonCours().supprimerProf(maFenetre.prof);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		maFenetre.setVisible(false);
		FenetrePrincipale newFenetre = new FenetrePrincipale(maFenetre.LeBonCoursDistant); 
		newFenetre.setVisible(true);				//ON REVIENT AU MENU PRINCIPAL APRES SUPPRESSION COMPTE?? OUI
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