package insa.projet.leboncours.ihm;

import insa.projet.leboncours.rmi.RMIServeur;
import insa.projet.leboncours.*;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class FenetreAnnulationEleve extends JFrame{
	
	public RMIServeur LeBonCoursDistant;
	public ReservationEleve resa;
	public Eleve leleve;
	
	/**
	 * Une police pour le titre du site 
	 */
	private final static Font POLICE_TITRE = new Font("Berlin Sans FB",Font.PLAIN,30);
	protected JButton valider, retour;
	
	public FenetreAnnulationEleve (RMIServeur leboncours, ReservationEleve r, Eleve el) {
		super("Annulation de la réservation");
		LeBonCoursDistant = leboncours; 
		String lejour = jourString(r.getJour());
		String lheure = heureString(r.getHeure());
		Prof leprof = r.getProf();
		leleve = el;
		resa = r;
		
		//programme se termine quand fenetre fermée
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel= new JPanel(new GridLayout(2,2,30,15));
		panel.add(new Canvas());
		panel.add(new Canvas());
		
		valider = new JButton("VALIDER");
		panel.add(valider); 
		retour = new JButton("RETOUR");
		panel.add(retour);
		
		
		//création du panel nord, avec nom du site
		JPanel haut = new JPanel();
		haut.setLayout(new BorderLayout());
						
		JLabel titre = new JLabel("leboncours.fr",new ImageIcon("imagecours.png"),SwingConstants.CENTER);
		titre.setFont(POLICE_TITRE);
		haut.add(titre, BorderLayout.NORTH);
		JLabel saut = new JLabel("              ");
		haut.add(saut, BorderLayout.CENTER);
		JLabel phrasee = new JLabel("Voulez-vous confirmer l'annulation du cours du " + lejour + " à " + lheure + " avec " + leprof.getPrenom() + " " + leprof.getNom()+" ?");
		haut.add(phrasee, BorderLayout.SOUTH);
										
		//remplissage du panel principal
		JPanel mainPanel = (JPanel)this.getContentPane();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(panel, BorderLayout.CENTER);
		mainPanel.add(haut,  BorderLayout.NORTH);
		mainPanel.setBorder(new EmptyBorder(10,10,10,10));
				
		this.pack();
		valider.addActionListener(new ControleAnnulationResEl(this));
		retour.addActionListener(new ControleRetourRechercheAnnulEl(this));
		}
	
	//fonction qui associe l'heure de la reservation en chaine de caractere pour l'affichage de la réservation
	private String heureString(int h) {
		String heure = "";
		
		switch (h)
		{
		case 0:
			heure = "9h";
			break;
		case 1:
			heure = "10h";
			break;
		case 2:
			heure = "11h";
			break;
		case 3:
			heure = "12h";
			break;
		case 4:
			heure = "13h";
			break;
		case 5:
			heure = "14h";
			break;
		case 6:
			heure = "15h";
			break; 
		case 7:
			heure = "16h";
			break;
		case 8:
			heure = "17h";
			break;
		case 9:
			heure = "18h";
			break;
		case 10:
			heure = "19h";
			break; 	
		}
		return heure;	
	}

	//fonction qui associe le jour de la reservation en chaine de caractère pour l'affichage de la réservation
	private String jourString(int j) {
		String jour = "";
		
		switch (j)
		{
		case 0:
			jour = "Lundi";
			break;
		case 1:
			jour = "Mardi";
			break;
		case 2:
			jour = "Mercredi";
			break;
		case 3:
			jour = "Jeudi";
			break;
		case 4:
			jour = "Vendredi";
			break;
		case 5:
			jour = "Samedi";
			break;
		case 6:
			jour = "Dimanche";
			break; 
		}
		return jour;
	}


}	


class ControleAnnulationResEl implements ActionListener {
	
	FenetreAnnulationEleve maFenetre;
	
	public ControleAnnulationResEl(FenetreAnnulationEleve uneFenetre){
		maFenetre = uneFenetre;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		ReservationEleve resaAnnul = new ReservationEleve(maFenetre.resa.getJour(), maFenetre.resa.getHeure(), maFenetre.resa.getProf());
		//suppression du cours chez l'eleve
		int i=0;
		while(i < maFenetre.leleve.getCours().size() )
		{
			if (maFenetre.leleve.getCours().get(i).equals(resaAnnul)) {
				maFenetre.leleve.getCours().remove(i);
				break;
			}
			i++;
		}
		//suppression du cours chez le prof
		ReservationProf resaAnnull = new ReservationProf(maFenetre.resa.getJour(), maFenetre.resa.getHeure(), maFenetre.leleve);
		int j=0;
		while(j < maFenetre.resa.getProf().getEdt().getListeCours().size() )
		{
			if (maFenetre.resa.getProf().getEdt().getListeCours().get(j).equals(resaAnnull)) {
				maFenetre.resa.getProf().getEdt().getListeCours().remove(j);
				break;
			}
			j++;
		}
		maFenetre.setVisible(false);
		JOptionPane.showMessageDialog(null, "Votre demande d'annulation a bien été prise en compte");
		FenetreMenuEleve retourMenu = new FenetreMenuEleve(maFenetre.LeBonCoursDistant,maFenetre.leleve);
		retourMenu.setVisible(true);
		retourMenu.pack();		
	}
}

class ControleRetourRechercheAnnulEl implements ActionListener {
		
	FenetreAnnulationEleve maFenetre;
		
	public ControleRetourRechercheAnnulEl(FenetreAnnulationEleve uneFenetre){
		maFenetre = uneFenetre;
	}
		
		@Override
		public void actionPerformed(ActionEvent e){
			maFenetre.setVisible(false);
			FenetreAnnuleCoursEleve retourAnnul = new FenetreAnnuleCoursEleve(maFenetre.LeBonCoursDistant,maFenetre.leleve);
			retourAnnul.setVisible(true);
			retourAnnul.pack();
		}	

}