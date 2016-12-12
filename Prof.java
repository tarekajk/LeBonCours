package insa.projet.leboncours;

import java.io.Serializable;

/**
 * Classe qui représente un professeur particulier
 * 
 * @author Claire Guilloteau - Tarek Al-Jijakli - Barthelemy Wade
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class Prof implements Serializable{
	
	/** Le nom du professeur */
	private String nom;
	
	/** Le prenom du professeur */
	private String prenom;
	
	/** Le sexe du professeur */
	private String sexe;
	
	/** L'age du professeur */
	private int age;
	
	/** Un entier representant le niveau d'etude du professeur :
	 *  Terminale = 0 Premiere = -1 BAC+1 = 1 etc
	 */
	private int niveau;
	
	/** Le code postale de l'adresse du professeur */
	private long codePostal;
	
	/** Le salaire horaire du professeur */
	private float prix;
	
	/** Indique si le professeur est motorisé ou non 
	 * si oui voiture = true sinon false
	 * */
	private boolean voiture;
	
	/** L'emploi du temps du professeur */
	private EmploiDuTemps edt;
	
	/**
	 * @return le nom du professeur
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * @param n le nouveau nom du professeur
	 */
	public void setNom(String n) {
		this.nom = n;
	}
	
	/**
	 * @return le prenom du professeur
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * @param p le nouveau prenom du professeur
	 */
	public void setPrenom(String p) {
		this.prenom = p;
	}
	
	/**
	 * @return le sexe du professeur
	 */
	public String getSexe() {
		return sexe;
	}
	
	/**
	 * @param sex le nouveau sexe du professeur
	 */
	public void setSexe(String sex) {
		this.sexe = sex;
	}
	
	/**
	 * @return l'age du professeur
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * @param age le nouvel age du professeur
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * @return le niveau d'etude du professeur
	 */
	public int getNiveau() {
		return niveau;
	}
	
	/**
	 * @param niv le nouveau niveau d'etude du professeur
	 */
	public void setNiveau(int niv) {
		this.niveau = niv;
	}
	
	/**
	 * @return le code postal de l'adresse du professeur
	 */
	public long getCodePostal() {
		return codePostal;
	}
	
	/**
	 * @param cp le nouveau code postal
	 */
	public void setCodePostal(long cp) {
		this.codePostal = cp;
	}
	
	/**
	 * @return le salaire horaire du professeur
	 */
	public float getPrix() {
		return prix;
	}
	
	/**
	 * @param p le nouveau prix horaire
	 */
	public void setPrix(float p) {
		this.prix = p;
	}
	
	/**
	 * @return La motorisation du professeur
	 */
	public boolean isVoiture() {
		return voiture;
	}
	
	/**
	 * @param v La nouvelle motorisation du professeur
	 */
	public void setVoiture(boolean v) {
		this.voiture = v;
	}
	
	/**
	 * @return l'emploi du temps du professeur
	 */
	public EmploiDuTemps getEdt() {
		return edt;
	}
	
	/**
	 * @param EDT le nouvel emploi du temps du professeur
	 */
	public void setEdt(EmploiDuTemps EDT) {
		this.edt = EDT;
	}
	
	/**
	 * Constructeur
	 * 
	 * @param n   Le nom du prof
	 * @param p   Le prénom du prof
	 * @param sex Le sexe du prof
	 * @param age L'age du prof
	 * @param niv Le niveau d'etude du prof
	 * @param cp  Le code postal de l'adresse du prof
	 * @param pr  Le prix horaire du prof
	 * @param v   La motorisation du prof
	 * @param edt L'emploi du temps du prof
	 */
	public Prof(String n, String p, String sex, int age, int niv, long cp, float pr,
			boolean v, EmploiDuTemps edt) {
		this.nom = n;
		this.prenom = p;
		this.sexe = sex;
		this.age = age;
		this.niveau = niv;
		this.codePostal = cp;
		this.prix = pr;
		this.voiture = v;
		this.edt = edt;
	}
	
	
	
	

}
