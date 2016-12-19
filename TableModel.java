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
		return 6;
	}

	public String getColumnName(int column) {
		switch (column) {
		case 0 : return "Nom";
		case 1 : return "Prenom";
		case 2 : return "Niveau d'étude : BAC +";
		case 3 : return "Code postal";
		case 4 : return "Motorisé" ;
		case 5 : return "Prix/heure";
		default : return "Il n'y a que 6 colonnes";
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Prof leprof =  null;
		try {
		leprof = profDispos.get(rowIndex);
		switch (columnIndex) {
		case 0 : return leprof.getNom();
		case 1 : return leprof.getPrenom();
		case 2 : return leprof.getNiveau();
		case 3 : return leprof.getCodePostal();
		case 4 : return leprof.isVoiture();
		case 5 : return leprof.getPrix();
		default : return "Il n'y a que 6 colonnes";
		}
		} catch (Exception ex) {
			System.err.println("size="+profDispos.size()+" row="+rowIndex+" col="+columnIndex+" " + leprof);
			System.err.println(ex.getMessage());
			return "????";
		}
	}

}
