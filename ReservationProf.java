package insa.projet.leboncours;

import java.io.Serializable;

/**
 * Classe qui repr�sente les informations d'un cours pour le professeur :
 * Le jour et l'heure du cours, ainsi que son eleve
 * 
 * @author Claire Guilloteau - Tarek Al-Jijakli - Barthelemy Wade
 * @version 1.0
 * @since 1.0
 */
public class ReservationProf implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Le jour du cours representé par un entier*/
	private int jour;
	
	/** L'heure du cours representé par un entier*/
	private int heure;
	
	/** L'eleve qui assiste au cours*/
	private Eleve eleve;
	
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
	 * @return l'eleve qui assiste au cours
	 */
	public Eleve getEleve() {
		return eleve;
	}
	
	/**
	 * Met à jour l'eleve qui assiste au cours
     * 
     * @param prof le nouvel eleve
	 */
	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}
	
	/**
	 * Constructeur
	 * 
	 * @param jour  Le jour du cours
	 * @param heure L'heure du cours
	 * @param prof  L'eleve qui assiste au cours
	 */
	public ReservationProf(int jour, int heure, Eleve eleve) {
		this.jour = jour;
		this.heure = heure;
		this.eleve = eleve;
	}
	
	/**
	 * Constructeur vide
	 */
	public ReservationProf() {
		// TODO Auto-generated constructor stub
	}

	
	
}
