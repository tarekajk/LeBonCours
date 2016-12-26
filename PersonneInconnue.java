package insa.projet.leboncours;

/**
 * Exception levee si l'utilisateur n'est pas inscrit sur leboncours
 * 
 * @author Claire Guilloteau - Tarek Al-Jijakli - Barthelemy Wade
 * @version 1.0
 * @since 1.0
 */
public class PersonneInconnue extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 
	 * Le nom et le prenom de l'utilisateur ayant provoque l'exception
	 */
	private String nom;
	private String prenom;
	
	/**
	 * Constructeur
	 * 
	 * @param nom    le nom de l'utilisateur ayant provoqué l'exception
	 * @param prenom le prenom de l'utilisateur ayant provoqué l'exception
	 */
	public PersonneInconnue(String nom, String prenom) {
		super(prenom + " " + nom + ", vous n'etes pas encore incrit sur notre site.");
	}
	
	/**
	 * Renvoie Le nom et le prenom de l'utilisateur ayant provoqué l'exception
	 * 
	 * @return Le nom et le prenom de l'utilisateur ayant provoqué l'exception
	 */
	public String getPersonne() {
		return (nom + " " + prenom);
	}

}
