package insa.projet.leboncours.ihm;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;


import insa.projet.leboncours.rmi.*;
import insa.projet.leboncours.ihm.TableModel;
import insa.projet.leboncours.*;

public class FenetreVoirCoursEleve extends JFrame {
	RMIServeur LeBonCoursDistant;
	Eleve leleve;
	
	private JTable tableDesResa;
	protected JButton retour;
	/**
	 * Une police pour le titre du site */
	private final static Font POLICE_TITRE = new Font("Berlin Sans FB",Font.PLAIN,30);
	private final static Font POLICE_SSTITRE = new Font("",Font.BOLD,20);
	
	
	public FenetreVoirCoursEleve(RMIServeur r, Eleve el) {
		super("Le bon cours/Menu Eleve/Voir mes cours");
		LeBonCoursDistant =r;
		leleve =el;
		
		//programme se termine quand fenetre ferm�e
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = (JPanel)this.getContentPane();
		panel.setLayout(new BorderLayout(15,15));
		
		//cr�ation du panel nord, avec nom du site
		JPanel haut = new JPanel();
		haut.setLayout(new BorderLayout());
		
		JLabel titre = new JLabel("leboncours.fr",new ImageIcon("imagecours.png"),SwingConstants.CENTER);
		titre.setFont(POLICE_TITRE);
		haut.add(titre, BorderLayout.NORTH);
		JLabel saut = new JLabel("              ");
		haut.add(saut, BorderLayout.CENTER);
		JLabel phrasee = new JLabel("Voici la liste des cours que vous allez recevoir:");
		haut.add(phrasee, BorderLayout.SOUTH);		
		
		// Construction du panel avec la liste des profs disponibles 
		tableDesResa = new JTable(new TableModelCoursEleve(leleve));  
		tableDesResa.setPreferredScrollableViewportSize(new Dimension(600,300));
		panel.add(new JScrollPane(tableDesResa), BorderLayout.CENTER);
		panel.add(haut, BorderLayout.NORTH);
		tableDesResa.setBackground(Color.WHITE);
		
		//Construction panel sud avec bouton retour
		retour = new JButton("Retour");
		panel.add(retour, BorderLayout.SOUTH);
		
		this.pack();
		
		retour.addActionListener(new ControleRetourEleve(this));
	}
	
	
	public void refresh() {
		((TableModelCoursProf)tableDesResa.getModel()).fireTableDataChanged();
	}
}

class ControleRetourEleve implements ActionListener {

	FenetreVoirCoursEleve maFenetre;
	
	public ControleRetourEleve(FenetreVoirCoursEleve uneFenetre){
		maFenetre = uneFenetre;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		maFenetre.setVisible(false);
		FenetreMenuEleve newFenetre = new FenetreMenuEleve(maFenetre.LeBonCoursDistant,maFenetre.leleve);
		newFenetre.setVisible(true);			
	}	
	
}