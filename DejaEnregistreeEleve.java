package insa.projet.leboncours;

public class DejaEnregistreeEleve extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private Eleve leleve;
	
	public DejaEnregistreeEleve(Eleve leleve) {
		super(leleve.getPrenom() + " " + leleve.getNom() + ", vous etes deja incrit sur notre site.");
	}
	
	public Eleve getEleve() {
		return leleve;
	}

}

