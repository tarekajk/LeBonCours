package insa.projet.leboncours;

public class DejaEnregistreeProf extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private Prof leprof;
	
	public DejaEnregistreeProf(Prof leprof) {
		super(leprof.getPrenom() + " " + leprof.getNom() + ", vous etes deja incrit sur notre site.");
	}
	
	public Prof getProf() {
		return leprof;
	}

}
	
