package insa.projet.leboncours.ihm;

import javax.swing.table.AbstractTableModel;

import insa.projet.leboncours.Prof;
import insa.projet.leboncours.ReservationProf;

@SuppressWarnings("serial")
public class TableModelReservation extends AbstractTableModel{

	public Prof leprof;
	
	public TableModelReservation(Prof unprof) {
		this.leprof = unprof;
	}

	public int getRowCount() {
		return leprof.getEdt().getDemandesCours().size();
	}

	public int getColumnCount() {
		return 6;
	}

	public String getColumnName(int column) {
		switch (column) {
		case 0 : return "Jour";
		case 1 : return "Heure";
		case 2 : return "Nom de l'eleve";
		case 3 : return "Prenom de l'eleve";
		case 4 : return "Niveau d'etude";
		case 5 : return "Code postal" ;
		default : return "Il n'y a que 6 colonnes";
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		ReservationProf resa =  null;
		try {
		resa = leprof.getEdt().getDemandesCours().get(rowIndex);
		switch (columnIndex) {
		case 0 : return jourEntierToString(resa.getJour());
		case 1 : return heureEntierToString(resa.getHeure());
		case 2 : return resa.getEleve().getNom();
		case 3 : return resa.getEleve().getPrenom();
		case 4 : return niveauEleveToTableModel(resa.getEleve().getNiveau());
		case 5 : return resa.getEleve().getCodePostal();
		default : return "Il n'y a que 6 colonnes";
		}
		} catch (Exception ex) {
			System.err.println("size="+leprof.getEdt().getListeCours().size()+" row="+rowIndex+" col="+columnIndex+" " + resa);
			System.err.println(ex.getMessage());
			return "????";
		}
	}
	
	public String niveauEleveToTableModel(int niv){
		String niveau = "";
		
		switch (niv)
		{
		  case -7:
		    niveau = "Primaire";
		    break;
		  case -6:
			 niveau = "6eme";
		    break;
		  case -5:
			 niveau = "5eme";
		    break;
		  case -4:
				 niveau = "4eme";
			    break;
		  case -3:
				 niveau = "3eme";
			    break;
		  case -2:
				 niveau = "Seconde";
			    break;
		  case -1:
				 niveau = "Premiere";
			    break;
		  case 0:
				 niveau = "Terminale";
			    break;  
		}
		return niveau;
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


