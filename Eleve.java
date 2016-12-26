package insa.projet.leboncours;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe qui repr�sente un eleve
 * 
 * @author Claire Guilloteau - Tarek Al-Jijakli - Barthelemy Wade
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class Eleve implements Serializable {

	/** Le nom de l'eleve */
	private String nom;
	
	/** Le prenom de l'eleve*/
	private String prenom;
	
	/** Le sexe de l'eleve */
	private String sexe;
	
	/** L'age de l'eleve */
	private int age;
	
	/** Un entier representant le niveau d'etude de l'eleve :
	 *  Terminale = 0 Premiere = -1 BAC+1 = 1 etc 
	 */
	private int niveau;
	
	/** Code postal de l'adresse de l'eleve */
	private long codePostal;
	
	/** Une ArrayList de R�servation des cours de l'�l�ve */
	private ArrayList<ReservationEleve> cours;
	
	/**
	 * @return le nom de l'eleve
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * @param n le nouveau nom de l'eleve
	 */
	public void setNom(String n) {
		this.nom = n;
	}
	
	/**
	 * @return le prenom de l'eleve
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * @param p le nouveau prenom de l'eleve
	 */
	public void setPrenom(String p) {
		this.prenom = p;
	}
	
	/**
	 * @return le sexe de l'eleve
	 */
	public String getSexe() {
		return sexe;
	}
	
	/**
	 * @param sex le nouveau sexe de l'eleve
	 */
	public void setSexe(String sex) {
		this.sexe = sex;
	}
	
	/**
	 * @return l'age de l'eleve
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * @param age le nouvel age 
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * @return le niveau d'etude de l'eleve
	 */
	public int getNiveau() {
		return niveau;
	}
	
	/**
	 * @param niv le nouveau niveau d'etude de l'eleve
	 */
	public void setNiveau(int niv) {
		this.niveau = niv;
	}
	
	/**
	 * @return le code postal de l'adresse de l'eleve
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
	 * @return la liste de cours de l'eleve
	 */
	public ArrayList<ReservationEleve> getCours() {
		return cours;
	}

	/**
	 * @param cours la nouvelle liste de cours
	 */
	public void setCours(ArrayList<ReservationEleve> cours) {
		this.cours = cours;
	}
	
	/**
	 * Constructeur
	 * 
	 * @param n    Le nom de l'eleve
	 * @param p    Le pr�nom de l'eleve
	 * @param sex  Le sexe de l'eleve
	 * @param age  L'age de l'eleve
	 * @param niv  Le niveau d'etude de l'eleve
	 * @param cp   Le code postal de l'adresse de l'eleve
	 * @param resa La liste des cours reserves par l'eleve
	 */
	public Eleve(String n, String p, String sex, int age, int niv, long cp, ArrayList<ReservationEleve> resa) {
		this.setNom(n);
		this.setPrenom(p);
		this.setSexe(sex);
		this.setAge(age);
		this.setNiveau(niv);
		this.setCodePostal(cp);
		this.setCours(resa);
	}

	/**
	 * Constructeur vide 
	 */
	public Eleve() {
		this.nom = "";
		this.prenom = "";
		this.sexe = "";
		this.age = 0;
		this.niveau = 0;
		this.codePostal = 0;
	}
	
	
}
