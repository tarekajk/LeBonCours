package insa.projet.leboncours;

import java.io.Serializable;

/**
 * Classe qui repr�sente les informations d'un cours pour l'eleve :
 * Le jour et l'heure du cours, ainsi que son professeur
 * 
 * @author Claire Guilloteau - Tarek Al-Jijakli - Barthelemy Wade
 * @version 1.0
 * @since 1.0
 */
public class ReservationEleve implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Le jour du cours representé par un entier*/
	private int jour;
	
	/** L'heure du cours representé par un entier*/
	private int heure;
	
	/** Le professeur qui dispense le cours*/
	private Prof prof;
	
	/**
	 * @return le jour du cours
	 */
	public int getJour() {
		return jour;
	}
	
	/**
	 * Met à jour le jour du cours
     * 
     * @param jour le nouveau jour
	 */
	public void setJour(int jour) {
		this.jour = jour;
	}
	
	/**
	 * @return l'heure du cours
	 */
	public int getHeure() {
		return heure;
	}
	
	/**
	 * Met à jour l'heure du cours
     * 
     * @param heure la nouvelle heure
	 */
	public void setHeure(int heure) {
		this.heure = heure;
	}
	
	/**
	 * @return le professeur qui dispense le cours
	 */
	public Prof getProf() {
		return prof;
	}
	
	/**
	 * Met à jour le professeur qui dispense le cours
     * 
     * @param prof le nouveau professeur
	 */
	public void setProf(Prof prof) {
		this.prof = prof;
	}
	
	/**
	 * Constructeur
	 * 
	 * @param jour  Le jour du cours
	 * @param heure L'heure du cours
	 * @param prof  Le professeur qui dispense le cours
	 */
	public ReservationEleve(int jour, int heure, Prof prof) {
		this.jour = jour;
		this.heure = heure;
		this.prof = prof;
	}	
	
	/**
	 * Constructeur vide
	 */
	public ReservationEleve(){
	}
}

