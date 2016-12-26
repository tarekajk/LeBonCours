package insa.projet.leboncours.ihm;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import insa.projet.leboncours.Eleve;
import insa.projet.leboncours.Prof;
import insa.projet.leboncours.ReservationEleve;
import insa.projet.leboncours.ReservationProf;

@SuppressWarnings("serial")
public class TableModelCoursEleve extends AbstractTableModel{

	public Eleve leleve;
	
	public TableModelCoursEleve(Eleve uneleve) {
		this.leleve = uneleve;
	}

	public int getRowCount() {
		return leleve.getCours().size();
	}

	public int getColumnCount() {
		return 8;
	}

	public String getColumnName(int column) {
		switch (column) {
		case 0 : return "Jour";
		case 1 : return "Heure";
		case 2 : return "Nom du professeur";
		case 3 : return "Prenom du professeur";
		case 4 : return "Niveau d'étude";
		case 5 : return "Code postal" ;
		case 6 : return "Prix" ;
		case 7 : return "Véhiculé?" ;
		default : return "Il n'y a que 8 colonnes";
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		ReservationEleve resa =  null;
		try {
		resa = leleve.getCours().get(rowIndex);
		switch (columnIndex) {
		case 0 : return jourEntierToString(resa.getJour());
		case 1 : return heureEntierToString(resa.getHeure());
		case 2 : return resa.getProf().getNom();
		case 3 : return resa.getProf().getPrenom();
		case 4 : return niveauProfToTableModel(resa.getProf().getNiveau());
		case 5 : return resa.getProf().getCodePostal();
		case 6 : return resa.getProf().getPrix();
		case 7 : return vehiculeToTableModel(resa.getProf().isVoiture());
		default : return "Il n'y a que 8 colonnes";
		}
		} catch (Exception ex) {
			System.err.println("size="+leleve.getCours().size()+" row="+rowIndex+" col="+columnIndex+" " + resa);
			System.err.println(ex.getMessage());
			return "????";
		}
	}
	
	public String niveauProfToTableModel(int niv){
		String niveau = "";
		
		switch (niv)
			{
			  case 0:
			    niveau = "BAC";
			    break;
			  case 1:
				niveau = "BAC +1";
			    break;
			  case 2:
				niveau = "BAC +2";
			    break;
			  case 3:
				niveau = "BAC +3";
		  	    break;
			  case 4:
				niveau = "BAC +4";
				break;
			  case 5:
				niveau = "BAC +5";
				break;
			  case 6:
				niveau = "BAC +6";
				break;
			  case 7:
				niveau = "BAC +7";
				break;  
			}
			return niveau;
		}
	
	public String vehiculeToTableModel(boolean vehic){
		String vehicule= "";
		if (vehic == true)
			 vehicule= "Oui";
		else if (vehic == false)
			 vehicule= "Non";
		return vehicule;
	}
	
	public String jourEntierToString(int j){
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
	
	public String heureEntierToString(int h){
		String heure = "";
		
		switch (h)
		{
		  case 0:
		    heure = "9h";
		    break;
		  case 1:
			heure = "10";
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