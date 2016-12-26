package insa.projet.leboncours;

/**
 * Exception levee si le professeur est deja enregistre sur une liste de professeurs de leboncours
 * 
 * @author Claire Guilloteau - Tarek Al-Jijakli - Barthelemy Wade
 * @version 1.0
 * @since 1.0
 */
public class DejaEnregistreeProf extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 
	 * Le professeur ayant provoque l'exception
	 */
	private Prof leprof;
	
	/**
	 * Constructeur
	 * 
	 * @param leprof Le professeur ayant provoqué l'exception
	 */
	public DejaEnregistreeProf(Prof leprof) {
		super(leprof.getPrenom() + " " + leprof.getNom() + ", vous etes deja incrit sur notre site.");
	}
	
	/**
	 * Renvoie Le professeur ayant provoqué l'exception
	 * 
	 * @return Le professeur ayant provoqué l'exception
	 */
	public Prof getProf() {
		return leprof;
	}

}
	
