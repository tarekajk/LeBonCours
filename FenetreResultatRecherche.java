package insa.projet.leboncours.ihm;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import insa.projet.leboncours.rmi.*;
import insa.projet.leboncours.ihm.TableModel;
import insa.projet.leboncours.*;

@SuppressWarnings("serial")
public class FenetreResultatRecherche extends JFrame{
	
	/**
	 * L'objet contenant les informations de leboncours.fr
	 */
	public RMIServeur LeBonCoursDistant;
	
	/**
	 * Une police pour le titre du site 
	 */
	private final static Font POLICE_TITRE = new Font("Berlin Sans FB",Font.PLAIN,30);
	
	/**
	 * une table pour afficher les profs disponibles
	 */
	private JTable tableDesProfsDispos;
	

	public FenetreResultatRecherche (RMIServeur leboncours, final int jour, final int heure, ArrayList<Prof> resultat,final Eleve el) {
		super("Resultat de la recherche");
		LeBonCoursDistant = leboncours;
		
		//programme se termine quand fenetre ferm�e
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//
		JPanel panel = (JPanel)this.getContentPane();
		
		panel.setLayout(new BorderLayout(10,10));
		panel.setBorder(new MatteBorder(3,3,3,3,Color.WHITE));
		panel.setBackground(Color.WHITE);
		
		JPanel leboncoursPanel = new JPanel(new BorderLayout(5,5));
		
		panel.add(leboncoursPanel, BorderLayout.CENTER);
		leboncoursPanel.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED),new EmptyBorder(5,5,5,5)));
		JPanel profPanel = new JPanel(new BorderLayout(5,5));
		leboncoursPanel.add(profPanel,BorderLayout.CENTER);
		leboncoursPanel.setBackground(Color.WHITE);

		/** Construction du panel avec la liste des profs disponibles */
		tableDesProfsDispos = new JTable(new TableModel(resultat));  // resultat = ArrayList de voitures pour nous listeProfDispo
		tableDesProfsDispos.setPreferredScrollableViewportSize(new Dimension(200,200));
		panel.add(new JScrollPane(tableDesProfsDispos), BorderLayout.CENTER);
		panel.setBackground(Color.WHITE);
		tableDesProfsDispos.setBackground(Color.WHITE); // JUSQU'A LA
		
		
		tableDesProfsDispos.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	           
	        	int i=0;
	        	
	        	String nom = (String) tableDesProfsDispos.getValueAt(tableDesProfsDispos.getSelectedRow(), 0);
	        	String prenom = (String) tableDesProfsDispos.getValueAt(tableDesProfsDispos.getSelectedRow(), 1);
	        	int niveau = Integer.parseInt(tableDesProfsDispos.getValueAt(tableDesProfsDispos.getSelectedRow(), 2).toString());
	        	Long cp = Long.parseLong(tableDesProfsDispos.getValueAt(tableDesProfsDispos.getSelectedRow(), 3).toString());
	        	Boolean voiture = Boolean.parseBoolean(tableDesProfsDispos.getValueAt(tableDesProfsDispos.getSelectedRow(), 4).toString());
	        	Float prix = Float.parseFloat(tableDesProfsDispos.getValueAt(tableDesProfsDispos.getSelectedRow(), 5).toString());
	        	
	        	Prof leprof = new Prof(); 
	        		
	        	try {
					while(i < LeBonCoursDistant.getLeBonCours().getListeProfs().size() ) //recuperer Prof Dispo 
					{
						if (LeBonCoursDistant.getLeBonCours().getListeProfs().get(i).getNom().equals(nom)
								&& LeBonCoursDistant.getLeBonCours().getListeProfs().get(i).getPrenom().equals(prenom)
								&& LeBonCoursDistant.getLeBonCours().getListeProfs().get(i).getNiveau()==niveau
								&& LeBonCoursDistant.getLeBonCours().getListeProfs().get(i).getCodePostal()==cp
								&& LeBonCoursDistant.getLeBonCours().getListeProfs().get(i).isVoiture()==voiture
								&& LeBonCoursDistant.getLeBonCours().getListeProfs().get(i).getPrix()==prix)
						{
							leprof = LeBonCoursDistant.getLeBonCours().getListeProfs().get(i);
							break;
						}
						i++;
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        	ReservationEleve resa = new ReservationEleve(jour, heure, leprof); 
	        	FenetreReservationEleve fenResa = new FenetreReservationEleve(LeBonCoursDistant, resa, el); 
	        	fenResa.setVisible(true);
	        	fenResa.pack();
	        }});
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}
	public void refresh() {
		((TableModel)tableDesProfsDispos.getModel()).fireTableDataChanged();
	
	}
	

}

