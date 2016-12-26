package insa.projet.leboncours;

/**
 * Exception levee si l'eleve est deja enregistre sur la liste d'eleves de leboncours
 * 
 * @author Claire Guilloteau - Tarek Al-Jijakli - Barthelemy Wade
 * @version 1.0
 * @since 1.0
 */
public class DejaEnregistreeEleve extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 
	 * L'eleve ayant provoque l'exception
	 */
	private Eleve leleve;
	
	/**
	 * Constructeur
	 * 
	 * @param leleve L'eleve ayant provoqué l'exception
	 */
	public DejaEnregistreeEleve(Eleve leleve) {
		super(leleve.getPrenom() + " " + leleve.getNom() + ", vous etes deja incrit sur notre site.");
	}
	
	/**
	 * Renvoie L'eleve ayant provoqué l'exception
	 * 
	 * @return L'eleve ayant provoqué l'exception
	 */
	public Eleve getEleve() {
		return leleve;
	}

}

