package insa.projet.leboncours;

import java.util.ArrayList;
import java.util.Scanner;

import insa.projet.leboncours.rmi.ProfConsoleDistante;

public class LeBonCours {

	public ArrayList<Prof> bio;
	public ArrayList<Prof> maths;
	public ArrayList<Prof> phy;
	public ArrayList<Prof> langues;
	public ArrayList<Prof> litt;
	public ArrayList<Prof> eco;
	public ArrayList<Prof> listeProfs;
	public ArrayList<Eleve> listeEleves;
	

	public ArrayList<Prof> getBio() {
		return bio;
	}
	public void setBio(ArrayList<Prof> bio) {
		this.bio = bio;
	}
	public ArrayList<Prof> getMaths() {
		return maths;
	}
	public void setMaths(ArrayList<Prof> maths) {
		this.maths = maths;
	}
	public ArrayList<Prof> getPhy() {
		return phy;
	}
	public void setPhy(ArrayList<Prof> phy) {
		this.phy = phy;
	}
	public ArrayList<Prof> getLangues() {
		return langues;
	}
	public void setLangues(ArrayList<Prof> langues) {
		this.langues = langues;
	}
	public ArrayList<Prof> getLitt() {
		return litt;
	}
	public void setLitt(ArrayList<Prof> litt) {
		this.litt = litt;
	}
	public ArrayList<Prof> getEco() {
		return eco;
	}
	public void setEco(ArrayList<Prof> eco) {
		this.eco = eco;
	}
	public ArrayList<Prof> getListeProfs() {
		return listeProfs;
	}
	public void setListeProfs(ArrayList<Prof> listeProfs) {
		this.listeProfs = listeProfs;
	}
	public ArrayList<Eleve> getListeEleve() {
		return listeEleves;
	}
	public void setListeEleve(ArrayList<Eleve> listeEleve) {
		this.listeEleves = listeEleve;
	}
	
	public LeBonCours() {
		int[][] dispo = new int[7][11];
		ArrayList<Eleve> listeEleves = new ArrayList<Eleve>();
		ArrayList<Prof> listeProfs = new ArrayList<Prof>();
		ArrayList<Prof> bio = new ArrayList<Prof>();
		ArrayList<Prof> maths = new ArrayList<Prof>();
		ArrayList<Prof> phy = new ArrayList<Prof>();
		ArrayList<Prof> litt = new ArrayList<Prof>();
		ArrayList<Prof> langues = new ArrayList<Prof>();
		ArrayList<Prof> eco = new ArrayList<Prof>();
 		for (int i=0; i<7; i++) {
 			for(int j=0; j<11;j++){
 				dispo[i][j]=1;
 			}
 		}
 		ArrayList<Reservation> resa = new ArrayList<Reservation>();
 		EmploiDuTemps edt = new EmploiDuTemps(dispo,resa);
 		Prof p1 = new Prof("Aljijakli","Tarek","M",21,4,76100,20,true,edt);
 		Prof p2 = new Prof("Wade","Barthelemy","M",22,4,76000,20,true,edt);
 		Prof p3 = new Prof("Guilloteau","Claire","F",21,4,76100,25,true,edt);
 		Prof p4 = new Prof("Montanier","Marion","F",19,1,76830,15,true,edt);
 		Prof p5 = new Prof("Lambert","Cedrik","M",20,2,76100,18,true,edt);
 		Eleve e1 = new Eleve("Ngaba","Fabrice","M",16,-2,76000);
 		Eleve e2 = new Eleve("LeFeuvre","Louis","M",15,-3,76550);
 		Eleve e3 = new Eleve("Jallais","Maeva","F",18,0,76100);
 		this.listeProfs = listeProfs;
 		this.bio = bio;
 		this.eco = eco;
 		this.phy = phy;
 		this.maths = maths;
 		this.langues = langues;
 		this.litt = litt;
 		listeEleves.add(e1);
 		listeEleves.add(e2);
 		listeEleves.add(e3);
		try {
			ajouterProf(maths, listeProfs, p1);
			ajouterProf(phy, listeProfs, p2);
	 		ajouterProf(bio, listeProfs, p3);
			ajouterProf(litt, listeProfs, p4);
			ajouterProf(eco, listeProfs, p5);
			ajouterProf(langues, listeProfs, p1);
			ajouterProf(maths, listeProfs, p2);
		} catch (DejaEnregistreeProf e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.listeEleves = listeEleves;
	}
	
	public void ajouterProf(ArrayList<Prof> matiere,ArrayList<Prof> listeProfs,Prof leprof) throws DejaEnregistreeProf{
		if (matiere.contains(leprof)) throw new DejaEnregistreeProf(leprof);
		matiere.add(leprof);
		if (!listeProfs.contains(leprof)) {
		listeProfs.add(leprof); };	
	}
	
	public void ajouterEleve(ArrayList<Eleve> listeEleve, Eleve leleve) throws DejaEnregistreeEleve{
		if (listeEleve.contains(leleve)) throw new DejaEnregistreeEleve(leleve);
		listeEleve.add(leleve);
	}
	
	public ArrayList<Prof> RechercheProf(ArrayList<Prof> matiere, int jour, int heure) {
		ArrayList<Prof> profdispo = new ArrayList<Prof>();
		for(int i = 0; i < matiere.size(); i++) {
			if (matiere.get(i).getEdt().dispo[jour][heure]==1) {
				profdispo.add(matiere.get(i));
			}
		}
		return profdispo;
	}
	
	public Prof TrouveUnProf(ArrayList<Prof> listeProfs, String nom, String prenom){
		Prof leprof = null;
		for(int i = 0; i < listeProfs.size(); i++) {
			if (listeProfs.get(i).getNom()==nom && listeProfs.get(i).getPrenom()==prenom){
				leprof = listeProfs.get(i);
				}
		}
		return leprof;
	}
	
	public Eleve TrouveUnEleve(ArrayList<Eleve> listeEleve, String nom, String prenom){
		Eleve leprof = null;
		for(int i = 0; i < listeProfs.size(); i++) {
			if (listeEleve.get(i).getNom()==nom && listeEleve.get(i).getPrenom()==prenom){
				leprof = listeEleve.get(i);
				}
		}
		return leprof;
	}
 
 
	public static void main(String[] args){
		System.out.println("Bienvenue sur leboncours.fr");
		LeBonCours leboncours = new LeBonCours();
		// Chargement de donnees initiales
		System.out.println("Etes vous un eleve ou un prof ?");
		System.out.println(" 0 : Prof");
		System.out.println(" 1 : Eleve");
		@SuppressWarnings("resource")
		Scanner lecture = new Scanner(System.in);
		int rep = Integer.parseInt(lecture.nextLine());
		if (rep==1) {
			EleveConsole eleve = new EleveConsole(leboncours.getListeEleve().get(1),leboncours);
			eleve.run();
		}
		else if (rep==0) {
			ProfConsole console = new ProfConsole(leboncours.getListeProfs().get(1),leboncours);
			console.run();
		}
		
 		
		
		
	}
	
}
