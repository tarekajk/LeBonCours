package insa.projet.leboncours;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * Classe qui représente le site leboncours qui met en relation des eleves à la 
 * recherche de cours particuliers avec des professeurs/etudiants
 * 
 * @author Claire Guilloteau - Tarek Al-Jijakli - Barthelemy Wade
 * @version 1.0
 * @since 1.0
 */
public class LeBonCours {

	/** Une ArrayList de Prof donnant des cours de biologie */
	public ArrayList<Prof> bio;
	
	/** Une ArrayList de Prof donnant des cours de mathématiques */
	public ArrayList<Prof> maths;
	
	/** Une ArrayList de Prof donnant des cours de physique */
	public ArrayList<Prof> phy;
	
	/** Une ArrayList de Prof donnant des cours de langues */
	public ArrayList<Prof> langues;
	
	/** Une ArrayList de Prof donnant des cours de litterature */
	public ArrayList<Prof> litt;
	
	/** Une ArrayList de Prof donnant des cours d'economie */
	public ArrayList<Prof> eco;
	
	/** Une ArrayList de Prof contenant l'ensemble des professeurs inscrits sur leboncours*/
	public ArrayList<Prof> listeProfs;
	
	/** Une ArrayList d'Eleve contenant l'ensemble des eleves inscrits sur leboncours*/
	public ArrayList<Eleve> listeEleves;
	
	/**
	 * @return la liste des professeurs de biologie
	 */
	public ArrayList<Prof> getBio() {
		return bio;
	}
	
	/**
	 * Met à jour la liste de professeurs de biologie
     * 
     * @param bio la nouvelle liste de professeurs de biologie
	 */
	public void setBio(ArrayList<Prof> bio) {
		this.bio = bio;
	}
	
	/**
	 * @return la liste des professeurs de mathématiques
	 */
	public ArrayList<Prof> getMaths() {
		return maths;
	}
	
	/**
	 * Met à jour la liste de professeurs de mathématiques
     * 
     * @param maths la nouvelle liste de professeurs de mathematiques
	 */
	public void setMaths(ArrayList<Prof> maths) {
		this.maths = maths;
	}
	
	/**
	 * @return la liste des professeurs de physique
	 */
	public ArrayList<Prof> getPhy() {
		return phy;
	}
	
	/**
	 * Met à jour la liste de professeurs de physique
     * 
     * @param phy la nouvelle liste de professeurs de physique
	 */
	public void setPhy(ArrayList<Prof> phy) {
		this.phy = phy;
	}
	
	/**
	 * @return la liste des professeurs de langues
	 */
	public ArrayList<Prof> getLangues() {
		return langues;
	}
	
	/**
	 * Met à jour la liste de professeurs de langues
     * 
     * @param langues la nouvelle liste de professeurs de langues
	 */
	public void setLangues(ArrayList<Prof> langues) {
		this.langues = langues;
	}
	
	/**
	 * @return la liste des professeurs de litterature
	 */
	public ArrayList<Prof> getLitt() {
		return litt;
	}
	
	/**
	 * Met à jour la liste de professeurs de litterature
     * 
     * @param litt la nouvelle liste de professeurs de litterature
	 */
	public void setLitt(ArrayList<Prof> litt) {
		this.litt = litt;
	}
	
	/**
	 * @return la liste des professeurs d'economie
	 */
	public ArrayList<Prof> getEco() {
		return eco;
	}
	
	/**
	 * Met à jour la liste de professeurs d'economie
     * 
     * @param eco la nouvelle liste de professeurs d'economie
	 */
	public void setEco(ArrayList<Prof> eco) {
		this.eco = eco;
	}
	
	/**
	 * @return la liste des professeurs de leboncours
	 */
	public ArrayList<Prof> getListeProfs() {
		return listeProfs;
	}
	
	/**
	 * Met à jour la liste de professeurs de leboncours
     * 
     * @param listeProfs la nouvelle liste de professeurs de leboncours
	 */
	public void setListeProfs(ArrayList<Prof> listeProfs) {
		this.listeProfs = listeProfs;
	}
	
	/**
	 * @return la liste des eleves de leboncours
	 */
	public ArrayList<Eleve> getListeEleve() {
		return listeEleves;
	}
	
	/**
	 * Met à jour la liste d'eleves de leboncours
     * 
     * @param listeEleve la nouvelle liste d'eleves de leboncours
	 */
	public void setListeEleve(ArrayList<Eleve> listeEleve) {
		this.listeEleves = listeEleve;
	}
	
	/**
	 * Constructeur vide
	 */
	public LeBonCours() {
		this.listeEleves = new ArrayList<Eleve>();
		this.listeProfs = new ArrayList<Prof>();
		this.bio = new ArrayList<Prof>();
		this.maths = new ArrayList<Prof>();
		this.phy = new ArrayList<Prof>();
		this.litt = new ArrayList<Prof>();
		this.langues = new ArrayList<Prof>();
		this.eco = new ArrayList<Prof>();
	}
	
	/** 
	 * Ajoute un prof dans une matiere (apres inscription)
	 * 
	 * @param matiere la liste de professeurs de la matiere choisie
	 * @param leprof  le professeur que l'on ajoute à la liste des professeurs de la matiere
	 * @throws DejaEnregistreeProf si le prof est déjà ajouté à la matiere correspondante
	 */
	public void ajouterProf(ArrayList<Prof> matiere,Prof leprof) throws DejaEnregistreeProf{
		if (matiere.contains(leprof)) throw new DejaEnregistreeProf(leprof);
		matiere.add(leprof);
		if (!listeProfs.contains(leprof)) {
		listeProfs.add(leprof); };	
	}
	
	/** 
	 * Ajoute un eleve à la liste d'eleves de leboncours (apres inscription)
	 * 
	 * @param leleve l'eleve que l'on ajoute à la liste des eleves de leboncours
	 * @throws DejaEnregistreeEleve si l'eleve est déjà ajouté à la liste des eleves de leboncours
	 */
	public void ajouterEleve(Eleve leleve) throws DejaEnregistreeEleve{
		if (listeEleves.contains(leleve)) throw new DejaEnregistreeEleve(leleve);
		listeEleves.add(leleve);
	}
	
	/** 
	 * Supprime un prof de leboncours
	 * 
	 * @param leprof  le professeur que l'on supprime de leboncours
	 */
	public void supprimerProf(Prof leprof) {
		// On Supprime le prof dans la liste des profs
		this.listeProfs.remove(leprof);
		// Mais aussi dans toutes les matières qu'il enseigne
		if (this.bio.contains(leprof)){
			this.bio.remove(leprof);
		}
		if (this.maths.contains(leprof)){
			this.maths.remove(leprof);
		}
		if (this.phy.contains(leprof)){
			this.phy.remove(leprof);
		}
		if (this.langues.contains(leprof)){
			this.langues.remove(leprof);
		}
		if (this.litt.contains(leprof)){
			this.litt.remove(leprof);
		}
		if (this.eco.contains(leprof)){
			this.eco.remove(leprof);
		}
	}

	/** 
	 * Supprime un eleve de leboncours
	 * 
	 * @param leleve l'eleve que l'on supprime de leboncours
	 */
	public void supprimerEleve(Eleve leleve){
		this.listeEleves.remove(leleve);
		}
	
	/** 
	 * Recherche les professeurs disponibles pour un horaire spécifique dans une matière choisie par l'eleve
	 * 
	 * @param matiere    la liste de professeurs de la matiere choisie par l'eleve
	 * @param jour       le jour voulu par l'eleve
	 * @param heure      l'heure voulue par l'eleve
	 * @return profdispo la liste des professeurs disponibles dans la matière demandée à l'horaire demandé
	 */
	public ArrayList<Prof> RechercheProf(ArrayList<Prof> matiere, int jour, int heure) {
		ArrayList<Prof> profdispo = new ArrayList<Prof>();
		for(int i = 0; i < matiere.size(); i++) {
			if (matiere.get(i).getEdt().dispo[jour][heure]==1) {
				profdispo.add(matiere.get(i));
			}
		}
		return profdispo;
	}
	
	/** 
	 * Recherche le professeur dans la base de données pour qu'il puisse se connecter
	 * 
	 * @param nom     le nom du professeur
	 * @param prenom  le prenom du professeur
	 * @return leprof le professeur 
	 */
	public Prof ConnexionProf(String nom, String prenom){
		Prof leprof = null;
		for(int i = 0; i < listeProfs.size(); i++) {
			if (listeProfs.get(i).getNom().equals(nom) && listeProfs.get(i).getPrenom().equals(prenom)){
				leprof = listeProfs.get(i);
				}
		}
		return leprof;
	}
	
	/** 
	 * Recherche le professeur dans la base de données pour qu'il puisse se connecter
	 * 
	 * @param nom     le nom de l'eleve
	 * @param prenom  le prenom de l'eleve
	 * @return leleve l'eleve 
	 */
	public Eleve ConnexionEleve(String nom, String prenom){
		Eleve leleve = null;
		for(int i = 0; i < listeEleves.size(); i++) {
			if (listeEleves.get(i).getNom().equals(nom) && listeEleves.get(i).getPrenom().equals(prenom)){
				leleve = listeEleves.get(i);
				}
		}
		return leleve;
	}
	
	/** Au lancement du programme, la m�thode lit les fichiers textes de chacune des liste de LeBonCours pour charger les donn�es */
	public void LancementFichTXT () {
		try {
			File fbio = new File("bio.txt");
			ObjectInputStream flux = new ObjectInputStream(new FileInputStream(fbio));
			Prof leprof = (Prof) flux.readObject();
			while (null != leprof) {
				try {
					this.bio.add(leprof);
					leprof = (Prof) flux.readObject();
				} catch (EOFException eofe) {
					break;
					// Exception lev�e si la lecture s'arr�te � un moment non pr�vu
				}
			}
			flux.close();
		} catch (IOException ioe) {
			System.err.println(ioe);
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe);
			// ??
		}
		
		try {
			File fmaths = new File("maths.txt");
			ObjectInputStream flux = new ObjectInputStream(new FileInputStream(fmaths));
			Prof leprof = (Prof) flux.readObject();
			while (null != leprof) {
				try {
					this.maths.add(leprof);
					leprof = (Prof) flux.readObject();
				} catch (EOFException eofe) {
					break;
				}
			}
			flux.close();
		} catch (IOException ioe) {
			System.err.println(ioe);
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe);
		}
		
		try {
			File fphy = new File("phy.txt");
			ObjectInputStream flux = new ObjectInputStream(new FileInputStream(fphy));
			Prof leprof = (Prof) flux.readObject();
			while (null != leprof) {
				try {
					this.phy.add(leprof);
					leprof = (Prof) flux.readObject();
				} catch (EOFException eofe) {
					break;
				}
			}
			flux.close();
		} catch (IOException ioe) {
			System.err.println(ioe);
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe);
		}
		
		try {
			File flangues = new File("langues.txt");
			ObjectInputStream flux = new ObjectInputStream(new FileInputStream(flangues));
			Prof leprof = (Prof) flux.readObject();
			while (null != leprof) {
				try {
					this.langues.add(leprof);
					leprof = (Prof) flux.readObject();
				} catch (EOFException eofe) {
					break;
				}
			}
			flux.close();
		} catch (IOException ioe) {
			System.err.println(ioe);
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe);
		}
		
		try {
			File flitt = new File("litt.txt");
			ObjectInputStream flux = new ObjectInputStream(new FileInputStream(flitt));
			Prof leprof = (Prof) flux.readObject();
			while (null != leprof) {
				try {
					this.litt.add(leprof);
					leprof = (Prof) flux.readObject();
				} catch (EOFException eofe) {
					break;
				}
			}
			flux.close();
		} catch (IOException ioe) {
			System.err.println(ioe);
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe);
		}
		
		try {
			File feco = new File("eco.txt");
			ObjectInputStream flux = new ObjectInputStream(new FileInputStream(feco));
			Prof leprof = (Prof) flux.readObject();
			while (null != leprof) {
				try {
					this.eco.add(leprof);
					leprof = (Prof) flux.readObject();
				} catch (EOFException eofe) {
					break;
				}
			}
			flux.close();
		} catch (IOException ioe) {
			System.err.println(ioe);
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe);
		}
		
		try {
			File flisteProfs = new File("listeProfs.txt");
			ObjectInputStream flux = new ObjectInputStream(new FileInputStream(flisteProfs));
			Prof leprof = (Prof) flux.readObject();
			while (null != leprof) {
				try {
					this.listeProfs.add(leprof);
					leprof = (Prof) flux.readObject();
				} catch (EOFException eofe) {
					break;
				}
			}
			flux.close();
		} catch (IOException ioe) {
			System.err.println(ioe);
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe);
		}
		
		try {
			File flisteEleves = new File("listeEleves.txt");
			ObjectInputStream flux = new ObjectInputStream(new FileInputStream(flisteEleves));
			Eleve leleve = (Eleve) flux.readObject();
			while (null != leleve) {
				try {
					this.listeEleves.add(leleve);
					leleve = (Eleve) flux.readObject();
				} catch (EOFException eofe) {
					break;
				}
			}
			flux.close();
		} catch (IOException ioe) {
			System.err.println(ioe);
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe);
		}
		
	}
	
	/** A la fermeture du programme, la m�thode enregistre les attributs de LeBonCours sur un ficher texte*/
	public void EnregistreFichTXT () {
		try {
			File bio = new File("bio.txt"); // Cr�ation d'un objet de la classe File
			ObjectOutputStream flux = new ObjectOutputStream(new FileOutputStream(bio)); // Cr�ation d'un objet de flux d'�criture d'objets
			for (int i = 0; i < this.bio.size(); i++) {
				flux.writeObject(this.bio.get(i));
			} // Ecriture de tous les objets de la liste bio dans le fichier texte
			flux.close(); // Fermeture du flux
		} catch (IOException ioe) {
			System.err.println(ioe); 
			// Exception lev�e si interruption des op�rations de flux entr�e/sortie.
		}
		
		try {
			File maths = new File("maths.txt");
			ObjectOutputStream flux = new ObjectOutputStream(new FileOutputStream(maths)); 
			for (int i = 0; i < this.maths.size(); i++) {
				flux.writeObject(this.maths.get(i));
			}
			flux.close();
		} catch (IOException ioe) {
			System.err.println(ioe); 
		}
		
		try {
			File phy = new File("phy.txt");
			ObjectOutputStream flux = new ObjectOutputStream(new FileOutputStream(phy)); 
			for (int i = 0; i < (this.phy).size(); i++) {
				flux.writeObject(this.phy.get(i));
			}
			flux.close();
		} catch (IOException ioe) {
			System.err.println(ioe); 
		}
		
		try {
			File langues = new File("langues.txt");
			ObjectOutputStream flux = new ObjectOutputStream(new FileOutputStream(langues)); 
			for (int i = 0; i < (this.langues).size(); i++) {
				flux.writeObject(this.langues.get(i));
			}
			flux.close();
		} catch (IOException ioe) {
			System.err.println(ioe); 
		}
		
		try {
			File litt = new File("litt.txt");
			ObjectOutputStream flux = new ObjectOutputStream(new FileOutputStream(litt)); 
			for (int i = 0; i < this.litt.size(); i++) {
				flux.writeObject(this.litt.get(i));
			}
			flux.close();
		} catch (IOException ioe) {
			System.err.println(ioe); 
		}
		
		try {
			File eco = new File("eco.txt");
			ObjectOutputStream flux = new ObjectOutputStream(new FileOutputStream(eco)); 
			for (int i = 0; i < (this.eco).size(); i++) {
				flux.writeObject(this.eco.get(i));
			}
			flux.close();
		} catch (IOException ioe) {
			System.err.println(ioe); 
		}
		
		try {
			File listeProfs = new File("listeProfs.txt");
			ObjectOutputStream flux = new ObjectOutputStream(new FileOutputStream(listeProfs)); 
			for (int i = 0; i < (this.listeProfs).size(); i++) {
				flux.writeObject(this.listeProfs.get(i));
			}
			flux.close();
		} catch (IOException ioe) {
			System.err.println(ioe); 
		}
		
		try {
			File listeEleves = new File("listeEleves.txt");
			ObjectOutputStream flux = new ObjectOutputStream(new FileOutputStream(listeEleves)); 
			for (int i = 0; i < (this.listeEleves).size(); i++) {
				flux.writeObject(this.listeEleves.get(i));
			}
			flux.close();
		} catch (IOException ioe) {
			System.err.println(ioe); 
		}
	}
	
	public static void main(String[] args) throws DejaEnregistreeEleve, DejaEnregistreeProf{
		// Main qui permet d'initialiser les fichiers textes, � ne faire qu'une seule fois au d�but
		System.out.println("Bienvenue sur leboncours.fr");
		LeBonCours leboncours = new LeBonCours();
		ArrayList<ReservationEleve> resael = new ArrayList<ReservationEleve>();
		ArrayList<ReservationProf> resapr = new ArrayList<ReservationProf>();
		ArrayList<ReservationProf> demandes = new ArrayList<ReservationProf>();
		int[][] dispo = new int[7][11];
		for (int i=0;i<7;i++) {
			for (int j=0;j<11;j++) dispo[i][j]=1;
		}
		EmploiDuTemps edt = new EmploiDuTemps(dispo,resapr,demandes);
		Eleve e1 = new Eleve("Foxx","Jamie","M",15,-2,76250,resael);
		Eleve e2 = new Eleve("Alba","Jessica","F",18,0,76000,resael);
		Eleve e3 = new Eleve("Leto","Jared","M",14,-3,76100,resael);
		Prof p1 = new Prof("Al-Jijakli","Tarek","M",21,4,76100,20,true,edt);
		Prof p2 = new Prof("Wade","Barth","M",22,4,76000,17,true,edt);
		Prof p3 = new Prof("Guilloteau","Claire","F",21,4,76100,15,true,edt);
		Prof p4 = new Prof("DiCaprio","Leonardo","M",19,1,76900,25,false,edt);
		leboncours.ajouterEleve(e1);
		leboncours.ajouterEleve(e2);
		leboncours.ajouterEleve(e3);
		leboncours.ajouterProf(leboncours.getBio(),p1);
		leboncours.ajouterProf(leboncours.getBio(),p4);
		leboncours.ajouterProf(leboncours.getMaths(),p1);
		leboncours.ajouterProf(leboncours.getMaths(),p3);
		leboncours.ajouterProf(leboncours.getPhy(),p2);
		leboncours.ajouterProf(leboncours.getPhy(),p4);
		leboncours.ajouterProf(leboncours.getEco(),p1);
		leboncours.ajouterProf(leboncours.getEco(),p4);
		leboncours.ajouterProf(leboncours.getLangues(),p2);
		leboncours.ajouterProf(leboncours.getLitt(),p3);
		leboncours.EnregistreFichTXT();
	}
	
}
