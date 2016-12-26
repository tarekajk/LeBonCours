package insa.projet.leboncours.ihm;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import insa.projet.leboncours.Eleve;
import insa.projet.leboncours.Prof;
import insa.projet.leboncours.ReservationEleve;
import insa.projet.leboncours.ReservationProf;
import insa.projet.leboncours.rmi.RMIServeur;

@SuppressWarnings("serial")
public class FenetreValidationReservation extends JFrame{

	public RMIServeur LeBonCoursDistant;
	public ReservationProf resa;
	public Prof leprof;
	
	/**
	 * Une police pour le titre du site 
	 */
	private final static Font POLICE_TITRE = new Font("Berlin Sans FB",Font.PLAIN,30);
	protected JButton accepter, refuser;
	
	public FenetreValidationReservation (RMIServeur leboncours, ReservationProf r, Prof p) {
		super("Le Bon Cours/Mes demandes de reservation/ Validation Cours");
		LeBonCoursDistant = leboncours; 
		String lejour = jourString(r.getJour());
		String lheure = heureString(r.getHeure());
		Eleve leleve = r.getEleve();
		leprof = p;
		resa = r;
		
		//programme se termine quand fenetre ferm�e
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel= new JPanel(new GridLayout(2,2,30,15));
		panel.add(new Canvas());
		panel.add(new Canvas());
		
		accepter = new JButton("ACCEPTER");
		panel.add(accepter); 
		refuser = new JButton("REFUSER");
		panel.add(refuser);
				
		//cr�ation du panel nord, avec nom du site
		JPanel haut = new JPanel();
		haut.setLayout(new BorderLayout());
								
		JLabel titre = new JLabel("leboncours.fr",new ImageIcon("imagecours.png"),SwingConstants.CENTER);
		titre.setFont(POLICE_TITRE);
		haut.add(titre, BorderLayout.NORTH);
		JLabel saut = new JLabel("              ");
		haut.add(saut, BorderLayout.CENTER);
		JLabel phrasee = new JLabel("Demande de cours de " + leleve.getPrenom() + " " + leleve.getNom() + " " + lejour + " a " + lheure);
		haut.add(phrasee, BorderLayout.SOUTH);
				
		//remplissage du panel principal
		JPanel mainPanel = (JPanel)this.getContentPane();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(panel, BorderLayout.CENTER);
		mainPanel.add(haut,  BorderLayout.NORTH);
		mainPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		this.pack();
		accepter.addActionListener(new ControleAccepter(this));
		refuser.addActionListener(new ControleRefuser(this));

	}
	
	//fonction qui associe le jour de la reservation en chaine de caract�re pour l'affichage de la r�servation
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
	

}

class ControleAccepter implements ActionListener {
	
	FenetreValidationReservation maFenetre;
	
	public ControleAccepter(FenetreValidationReservation uneFenetre) {
		maFenetre = uneFenetre;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		ReservationEleve resaEl = new ReservationEleve(maFenetre.resa.getJour(), maFenetre.resa.getHeure(), maFenetre.leprof);
		try {
			int i=0;
			while(i< maFenetre.LeBonCoursDistant.getLeBonCours().getListeEleve().size() )
			{
				if (maFenetre.LeBonCoursDistant.getLeBonCours().getListeEleve().get(i).equals(maFenetre.resa.getEleve())) {
					maFenetre.LeBonCoursDistant.getLeBonCours().getListeEleve().get(i).getCours().add(resaEl);
					break;
				}
				i++;
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			int j=0;
			while(j< maFenetre.LeBonCoursDistant.getLeBonCours().getListeProfs().size() )
			{   // PROBLEME LE REMOVE NE SE FAIT PAS DANS LA LISTE DES RESAS
				if (maFenetre.LeBonCoursDistant.getLeBonCours().getListeProfs().get(j).equals(maFenetre.leprof)) {
					maFenetre.LeBonCoursDistant.getLeBonCours().getListeProfs().get(j).getEdt().getDemandesCours().remove(maFenetre.resa); //Laisser demandesCours ou utiliser getDemandesCours()
					maFenetre.LeBonCoursDistant.getLeBonCours().getListeProfs().get(j).getEdt().getListeCours().add(maFenetre.resa); 
					maFenetre.LeBonCoursDistant.getLeBonCours().getListeProfs().get(j).getEdt().Modifier(maFenetre.leprof.getEdt().getDispo(), maFenetre.resa.getJour(), maFenetre.resa.getHeure(), 2);
					break;
				}
				j++;
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		maFenetre.setVisible(false);
		FenetreMenuProf retourMenu = new FenetreMenuProf(maFenetre.LeBonCoursDistant,maFenetre.leprof);
		// Pour tester que ca rempli bien l'attribut listeCours de prof : OK
		//FenetreVoirCoursProf retourMenu = new FenetreVoirCoursProf(maFenetre.LeBonCoursDistant,maFenetre.leprof);	
		// Pour tester que ca supprime bien la resa dans demandesResa de prof : MAIS CA MARCHE PAS 
		//FenetreDemandesReservation retourMenu = new FenetreDemandesReservation(maFenetre.LeBonCoursDistant,maFenetre.leprof);
		// Pour tester si ca modifie bien l'emploi du temps : MESSAGES D'ERREUR, IMPOSSIBLE DE TESTER
		//FenetreEmploiDuTempsModif retourMenu = new FenetreEmploiDuTempsModif(maFenetre.LeBonCoursDistant,maFenetre.leprof);
		retourMenu.setVisible(true);
		retourMenu.pack();
	}
}

class ControleRefuser implements ActionListener {
	
	FenetreValidationReservation maFenetre;
	
	public ControleRefuser(FenetreValidationReservation uneFenetre){
		maFenetre = uneFenetre;
	}
		
	@Override
	public void actionPerformed(ActionEvent e){
	try {
		int i=0;
		while(i< maFenetre.LeBonCoursDistant.getLeBonCours().getListeProfs().size() )
		{   //VOIR LES QUESTIONS LIGNE 2 DANS LE IF
			if (maFenetre.LeBonCoursDistant.getLeBonCours().getListeProfs().get(i).equals(maFenetre.leprof)) {
				maFenetre.LeBonCoursDistant.getLeBonCours().getListeProfs().get(i).getEdt().getDemandesCours().remove(maFenetre.resa); //Laisser demandesCours ou utiliser getDemandesCours()
				break;
			}
			i++;
		}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		maFenetre.setVisible(false);
		FenetreMenuProf retourMenu= new FenetreMenuProf(maFenetre.LeBonCoursDistant,maFenetre.leprof);
		retourMenu.setVisible(true);
		retourMenu.pack();
	}	

}