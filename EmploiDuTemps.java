package insa.projet.leboncours;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe qui repr�sente l'emploi du temps d'un professeur
 * 
 * @author Claire Guilloteau - Tarek Al-Jijakli - Barthelemy Wade
 * @version 1.0
 * @since 1.0
 */
public class EmploiDuTemps implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Un tableau d'entier representant la disponibilite du professeur
	 * 0 = Non Disponible 1 = Disponible 2 = Reserve
	 */ 
	public int[][] dispo;
	
	/** Une ArrayList de Reservation des cours du professeur */
	public ArrayList<ReservationProf> listeCours;
	
	/** Une ArrayList de Reservation des demandes de cours du professeur */
	public ArrayList<ReservationProf> demandesCours;
	
	/**
	 * @return le tableau des disponibilites du professeur
	 */
	public int[][] getDispo() {
		return dispo;
	}
	
	/**
	 * Met à jour le tableau des disponibilites du professeur
     * 
     * @param dispo le nouveau tableau des disponibilites du professeur
	 */
	public void setDispo(int[][] dispo) {
		this.dispo = dispo;
	}
	
	/**
	 * @return la liste des cours du professeur
	 */
	public ArrayList<ReservationProf> getListeCours() {
		return listeCours;
	}
	
	/**
	 * Met à jour la liste des cours du professeur
     * 
     * @param listeCours la nouvelle liste des cours du professeur
	 */
	public void setListeCours(ArrayList<ReservationProf> listeCours) {
		this.listeCours = listeCours;
	}
	
	/**
	 * @return la liste de demandes de cours du professeur 
	 */
	public ArrayList<ReservationProf> getDemandesCours() {
		return demandesCours;
	}
	
	/**
	 * Met à jour la liste des demandes de cours du professeur
     * 
     * @param demandesCours la nouvelle liste des demandes de cours du professeur
	 */
	public void setDemandesCours(ArrayList<ReservationProf> demandesCours) {
		this.demandesCours = demandesCours;
	}
	
	/**
	 * Constructeur
	 * 
	 * @param dispo     	Le tableau des disponibilites du professeur
	 * @param listeCours    La liste des cours du professeur
	 * @param demandesCours La liste des demandes de cours du professeur
	 */
	public EmploiDuTemps(int[][] dispo, ArrayList<ReservationProf> listeCours, ArrayList<ReservationProf> demandesCours) {
		this.dispo = dispo;
		this.listeCours = listeCours;
		this.demandesCours = demandesCours;
	}
	
	/** 
	 * Modifie le tableau des disponibilites du professeur
	 * 
	 * @param dispo le tableau des disponibilites du professeur
	 * @param jour  le jour pour lequel on modifie la disponibilite du professeur
	 * @param heure l'heure pour laquelle on modifie la disponibilite du professeur
	 * @param etat  la nouvelle disponibilite du professeur pour le jour et l'heure choisie
	 * @return une instance d'ordre
	 */
	public int[][] Modifier(int[][] dispo, int jour, int heure, int etat){
		dispo[jour][heure]=etat;
		return dispo;
	}
	
	/**
	 * Modifier les parametres de l'emploi du temps du professeur lorsque qu'un cours est validé par le professeur
	 * 
	 * @param resa la reservation contenant le jour l'heure du cours et l'eleve ayant effectué la reservation
	 */
	public void Validation (ReservationProf resa){
		this.Modifier(dispo,resa.getJour(),resa.getHeure(),2); // Rev�rifier les num�ros pour les �tats
		listeCours.add(resa);
		demandesCours.remove(resa);
	}
	
	/**
	 * Modifier les parametres de l'emploi du temps du professeur lorsque qu'il annule un de ces cours
	 * 
	 * @param resa la reservation contenant le jour l'heure du cours et l'eleve ayant effectué la reservation
	 */
	public void SupprCours (ReservationProf resa){
		this.Modifier(dispo,resa.getJour(),resa.getHeure(),1); 
		listeCours.remove(resa);
	}
	
}
