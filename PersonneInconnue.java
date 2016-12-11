package insa.projet.leboncours;


public class PersonneInconnue extends Exception {

	private static final long serialVersionUID = 1L;
	

	private String nom;
	private String prenom;
	
	public PersonneInconnue(String nom, String prenom) {
		super(prenom + " " + nom + ", vous n'etes pas encore incrit sur notre site.");
	}
	
	public String getPersonne() {
		return (nom + " " + prenom);
	}

}
