package insa.projet.leboncours.ihm;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import insa.projet.leboncours.Prof;

@SuppressWarnings("serial")
public class TableModel extends AbstractTableModel{

	/** la liste de profs disponibles */
	public ArrayList<Prof> profDispos;

	/**
	 * Constructeur
	 * 
	 * @param res La liste des profs disponibles
	 */
	public TableModel(ArrayList<Prof> res) {
		this.profDispos = res;
	}

	public int getRowCount() {
		return profDispos.size();
	}

	public int getColumnCount() {
		return 7;
	}

	public String getColumnName(int column) {
		switch (column) {
		case 0 : return "Nom";
		case 1 : return "Prenom";
		case 2 : return "Age";
		case 3 : return "Niveau d'étude";
		case 4 : return "Code postal";
		case 5 : return "Véhiculé" ;
		case 6 : return "Prix/heure";
		default : return "Il n'y a que 7 colonnes";
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Prof leprof =  null;
		try {
		leprof = profDispos.get(rowIndex);
		switch (columnIndex) {
		case 0 : return leprof.getNom();
		case 1 : return leprof.getPrenom();
		case 2 : return leprof.getAge();
		case 3 : return niveauToTableModel(leprof.getNiveau());
		case 4 : return leprof.getCodePostal();
		case 5 : return IsVoitureToTableModel(leprof.isVoiture());
		case 6 : return leprof.getPrix();
		default : return "Il n'y a que 7 colonnes";
		}
		} catch (Exception ex) {
			System.err.println("size="+profDispos.size()+" row="+rowIndex+" col="+columnIndex+" " + leprof);
			System.err.println(ex.getMessage());
			return "????";
		}
	}

	public String IsVoitureToTableModel(boolean vehic){
		String vehicule= "";
		if (vehic == true){
			 vehicule= "Oui";}
		if (vehic == false){
			 vehicule= "Non";}
		return vehicule;
	}
	
	public String niveauToTableModel(int niv){
		String niveau = "BAC";
		
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
	
}
