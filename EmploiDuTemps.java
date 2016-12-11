package insa.projet.leboncours;

import java.util.ArrayList;

public class EmploiDuTemps {
	
	public int[][] dispo;
	public ArrayList<Reservation> listeCours;
	
	
	public int[][] getDispo() {
		return dispo;
	}
	public void setDispo(int[][] dispo) {
		this.dispo = dispo;
	}
	public ArrayList<Reservation> getListeCours() {
		return listeCours;
	}
	public void setListeCours(ArrayList<Reservation> listeCours) {
		this.listeCours = listeCours;
	}
	
	public EmploiDuTemps(int[][] dispo, ArrayList<Reservation> listeCours) {
		this.dispo = dispo;
		this.listeCours = listeCours;
	}
		
	public int[][] Modifier(int[][] dispo, int jour, int heure, int etat){
		dispo[jour][heure]=etat;
		return dispo;
	}
	
	public void Validation(int[][] dispo, ArrayList<Reservation> listeCours, Reservation resa){
		this.Modifier(dispo,resa.getJour(),resa.getHeure(),2); // Rev�rifier les num�ros pour les �tats
		listeCours.add(resa);
	}
	
	public void SupprCours (int[][] dispo, ArrayList<Reservation> listeCours, Reservation resa){
		this.Modifier(dispo,resa.getJour(),resa.getHeure(),1); 
		listeCours.remove(resa);
	}
	
}
