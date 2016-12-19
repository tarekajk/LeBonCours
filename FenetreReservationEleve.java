package insa.projet.leboncours.ihm;

import insa.projet.leboncours.rmi.RMIServeur;
import insa.projet.leboncours.*;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class FenetreReservationEleve extends JFrame{
	
	public RMIServeur LeBonCoursDistant;
	public ReservationEleve resa;
	public Eleve leleve;
	
	/**
	 * Une police pour le titre du site 
	 */
	private final static Font POLICE_TITRE = new Font("Berlin Sans FB",Font.PLAIN,30);
	protected JButton valider, retour;
	
	public FenetreReservationEleve (RMIServeur leboncours, ReservationEleve r, Eleve el) {
		super("Validation de la réservation");
		LeBonCoursDistant = leboncours; 
		String lejour = jourString(r.getJour());
		String lheure = heureString(r.getHeure());
		Prof leprof = r.getProf();
		leleve = el;
		resa = r;
		
		//programme se termine quand fenetre ferm�e
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/////////////////////////////////////////////////////////////////////////////////////////
		// LA FENETRE EST PAS BELLE, IL FAUDRAIT MODIFIER 2 3 TRUCS DANS LA CONSTRUCTION DU PANEL 
		/////////////////////////////////////////////////////////////////////////////////////////
		
		//creation du panel avec toutes les questions
		JPanel panel= new JPanel(new GridLayout(1,2,0,5));
		panel.add(new Canvas());
		panel.add(new Canvas());
		
		valider = new JButton("VALIDER");
		panel.add(new Canvas());
		panel.add(valider); 
		retour = new JButton("RETOUR");
		panel.add(new Canvas());
		panel.add(retour);
		
		
		//cr�ation du panel nord, avec nom du site
		JPanel haut = new JPanel();
		haut.setLayout(new BorderLayout());
						
		JLabel titre = new JLabel("leboncours.fr",new ImageIcon("imagecours.png"),SwingConstants.CENTER);
		titre.setFont(POLICE_TITRE);
		haut.add(titre, BorderLayout.NORTH);
		JLabel saut = new JLabel("              ");
		haut.add(saut, BorderLayout.CENTER);
		JLabel phrasee = new JLabel("Validation du cours du " + lejour + " à " + lheure + " avec " + leprof.getPrenom() + " " + leprof.getNom());
		haut.add(phrasee, BorderLayout.SOUTH);
										
		//remplissage du panel principal
		JPanel mainPanel = (JPanel)this.getContentPane();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(panel, BorderLayout.CENTER);
		mainPanel.add(haut,  BorderLayout.NORTH);
		mainPanel.setBorder(new EmptyBorder(10,10,10,10));
				
		this.pack();
		valider.addActionListener(new ControleValidationRes(this));
		retour.addActionListener(new ControleRetourRecherche(this));
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


class ControleValidationRes implements ActionListener {
	
	FenetreReservationEleve maFenetre;
	
	public ControleValidationRes(FenetreReservationEleve uneFenetre){
		maFenetre = uneFenetre;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		ReservationProf demandeResa = new ReservationProf(maFenetre.resa.getJour(), maFenetre.resa.getHeure(), maFenetre.leleve);
		try {
			int i=0;
			while(i < maFenetre.LeBonCoursDistant.getLeBonCours().getListeProfs().size() )
			{
				if (maFenetre.LeBonCoursDistant.getLeBonCours().getListeProfs().get(i).equals(maFenetre.resa.getProf())) {
					maFenetre.LeBonCoursDistant.getLeBonCours().getListeProfs().get(i).getEdt().demandesCours.add(demandeResa);
					break;
				}
				i++;
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		maFenetre.setVisible(false);
		JOptionPane.showMessageDialog(null, "Votre demande de reservation a bien été pris en compte");
		FenetreMenuEleve retourMenu = new FenetreMenuEleve();
		retourMenu.setVisible(true);
		retourMenu.pack();		
	}
}

class ControleRetourRecherche implements ActionListener {
		
	FenetreReservationEleve maFenetre;
		
	public ControleRetourRecherche(FenetreReservationEleve uneFenetre){
		maFenetre = uneFenetre;
	}
		
		@Override
		public void actionPerformed(ActionEvent e){
			maFenetre.setVisible(false);
			FenetreRechercheProf retourRecherche = new FenetreRechercheProf(maFenetre.LeBonCoursDistant,maFenetre.leleve);
			retourRecherche.setVisible(true);
			retourRecherche.pack();
		}	

}
