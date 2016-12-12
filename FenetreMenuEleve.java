package insa.projet.leboncours.ihm;

import insa.projet.leboncours.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FenetreMenuEleve extends JFrame {

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
	
	public FenetreMenuEleve() {
		super("Le bon cours/Menu Eleve");
		
		//programme se termine quand fenetre ferm�e
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//cr�ation du panel avec toutes les questions
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
		
		
		//cr�ation du panel nord, avec nom du site
		JPanel haut = new JPanel();
		haut.setLayout(new BorderLayout());
		
		JLabel titre = new JLabel("leboncours.fr",new ImageIcon("imagecours.png"),SwingConstants.CENTER);
		titre.setFont(POLICE_TITRE);
		JLabel phrase = new JLabel("Mon menu �l�ve", SwingConstants.CENTER);
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
	}
	
	
	
	public static void main(String[] args) {
		FenetreMenuEleve maFenetre = new FenetreMenuEleve();
		maFenetre.setVisible(true);
	}
	
}