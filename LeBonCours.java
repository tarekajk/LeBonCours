+package insa.projet.leboncours;
 +
 +import java.io.EOFException;
 +import java.io.File;
 +import java.io.FileInputStream;
 +import java.io.FileOutputStream;
 +import java.io.IOException;
 +import java.io.ObjectInputStream;
 +import java.io.ObjectOutputStream;
 +import java.util.ArrayList;
 +
 +public class LeBonCours {
 +
 +	public ArrayList<Prof> bio;
 +	public ArrayList<Prof> maths;
 +	public ArrayList<Prof> phy;
 +	public ArrayList<Prof> langues;
 +	public ArrayList<Prof> litt;
 +	public ArrayList<Prof> eco;
 +	public ArrayList<Prof> listeProfs;
 +	public ArrayList<Eleve> listeEleves;
 +	
 +
 +	public ArrayList<Prof> getBio() {
 +		return bio;
 +	}
 +	public void setBio(ArrayList<Prof> bio) {
 +		this.bio = bio;
 +	}
 +	public ArrayList<Prof> getMaths() {
 +		return maths;
 +	}
 +	public void setMaths(ArrayList<Prof> maths) {
 +		this.maths = maths;
 +	}
 +	public ArrayList<Prof> getPhy() {
 +		return phy;
 +	}
 +	public void setPhy(ArrayList<Prof> phy) {
 +		this.phy = phy;
 +	}
 +	public ArrayList<Prof> getLangues() {
 +		return langues;
 +	}
 +	public void setLangues(ArrayList<Prof> langues) {
 +		this.langues = langues;
 +	}
 +	public ArrayList<Prof> getLitt() {
 +		return litt;
 +	}
 +	public void setLitt(ArrayList<Prof> litt) {
 +		this.litt = litt;
 +	}
 +	public ArrayList<Prof> getEco() {
 +		return eco;
 +	}
 +	public void setEco(ArrayList<Prof> eco) {
 +		this.eco = eco;
 +	}
 +	public ArrayList<Prof> getListeProfs() {
 +		return listeProfs;
 +	}
 +	public void setListeProfs(ArrayList<Prof> listeProfs) {
 +		this.listeProfs = listeProfs;
 +	}
 +	public ArrayList<Eleve> getListeEleve() {
 +		return listeEleves;
 +	}
 +	public void setListeEleve(ArrayList<Eleve> listeEleve) {
 +		this.listeEleves = listeEleve;
 +	}
 +	
 +	public LeBonCours(ArrayList<Prof> bio, ArrayList<Prof> maths, ArrayList<Prof> phy, ArrayList<Prof> langues,
 +			ArrayList<Prof> litt, ArrayList<Prof> eco, ArrayList<Prof> listeProfs, ArrayList<Eleve> listeEleves) {
 +		super();
 +		this.bio = bio;
 +		this.maths = maths;
 +		this.phy = phy;
 +		this.langues = langues;
 +		this.litt = litt;
 +		this.eco = eco;
 +		this.listeProfs = listeProfs;
 +		this.listeEleves = listeEleves;
 +	}
 +	
 +	public void ajouterProf(ArrayList<Prof> matiere,ArrayList<Prof> listeProfs,Prof leprof) throws DejaEnregistreeProf{
 +		if (matiere.contains(leprof)) throw new DejaEnregistreeProf(leprof);
 +		matiere.add(leprof);
 +		if (!listeProfs.contains(leprof)) {
 +		listeProfs.add(leprof); };	
 +	}
 +	
 +	public void ajouterEleve(ArrayList<Eleve> listeEleve, Eleve leleve) throws DejaEnregistreeEleve{
 +		if (listeEleve.contains(leleve)) throw new DejaEnregistreeEleve(leleve);
 +		listeEleve.add(leleve);
 +	}
 +	
 +	public void supprimerProf(Prof leprof) {
 +		this.listeProfs.remove(leprof);
 +		if (this.bio.contains(leprof)){
 +			this.bio.remove(leprof);
 +		}
 +		if (this.maths.contains(leprof)){
 +			this.maths.remove(leprof);
 +		}
 +		if (this.phy.contains(leprof)){
 +			this.phy.remove(leprof);
 +		}
 +		if (this.langues.contains(leprof)){
 +			this.langues.remove(leprof);
 +		}
 +		if (this.litt.contains(leprof)){
 +			this.litt.remove(leprof);
 +		}
 +		if (this.eco.contains(leprof)){
 +			this.eco.remove(leprof);
 +		}
 +	}
 +
 +	public void supprimerEleve(Eleve leleve){
 +		this.listeEleves.remove(leleve);
 +		}
 +	
 +	public ArrayList<Prof> RechercheProf(ArrayList<Prof> matiere, int jour, int heure) {
 +		ArrayList<Prof> profdispo = new ArrayList<Prof>();
 +		for(int i = 0; i < matiere.size(); i++) {
 +			if (matiere.get(i).getEdt().dispo[jour][heure]==1) {
 +				profdispo.add(matiere.get(i));
 +			}
 +		}
 +		return profdispo;
 +	}
 +	
 +	public Prof ConnexionProf(String nom, String prenom){
 +		Prof leprof = null;
 +		for(int i = 0; i < listeProfs.size(); i++) {
 +			if (listeProfs.get(i).getNom()==nom && listeProfs.get(i).getPrenom()==prenom){
 +				leprof = listeProfs.get(i);
 +				}
 +		}
 +		return leprof;
 +	}
 +	
 +	public Eleve ConnexionEleve(String nom, String prenom){
 +		Eleve leleve = null;
 +		for(int i = 0; i < listeEleves.size(); i++) {
 +			if (listeEleves.get(i).getNom()==nom && listeEleves.get(i).getPrenom()==prenom){
 +				leleve = listeEleves.get(i);
 +				}
 +		}
 +		return leleve;
 +	}
 +	
 +	// Au lancement du progamme, la méthode lit les fichiers textes de chacune des liste de LeBonCours pour charger les données
 +	public void LancementFichTXT () {
 +		try {
 +			File fbio = new File("bio.txt");
 +			ObjectInputStream flux = new ObjectInputStream(new FileInputStream(fbio));
 +			Prof leprof = (Prof) flux.readObject();
 +			while (null != leprof) {
 +				try {
 +					this.bio.add(leprof);
 +					leprof = (Prof) flux.readObject();
 +				} catch (EOFException eofe) {
 +					break;
 +					// Exception levée si la lecture s'arrête à un moment non prévu
 +				}
 +			}
 +			flux.close();
 +		} catch (IOException ioe) {
 +			System.err.println(ioe);
 +		} catch (ClassNotFoundException cnfe) {
 +			System.err.println(cnfe);
 +			// ??
 +		}
 +		
 +		try {
 +			File fmaths = new File("maths.txt");
 +			ObjectInputStream flux = new ObjectInputStream(new FileInputStream(fmaths));
 +			Prof leprof = (Prof) flux.readObject();
 +			while (null != leprof) {
 +				try {
 +					this.maths.add(leprof);
 +					leprof = (Prof) flux.readObject();
 +				} catch (EOFException eofe) {
 +					break;
 +				}
 +			}
 +			flux.close();
 +		} catch (IOException ioe) {
 +			System.err.println(ioe);
 +		} catch (ClassNotFoundException cnfe) {
 +			System.err.println(cnfe);
 +		}
 +		
 +		try {
 +			File fphy = new File("phy.txt");
 +			ObjectInputStream flux = new ObjectInputStream(new FileInputStream(fphy));
 +			Prof leprof = (Prof) flux.readObject();
 +			while (null != leprof) {
 +				try {
 +					this.phy.add(leprof);
 +					leprof = (Prof) flux.readObject();
 +				} catch (EOFException eofe) {
 +					break;
 +				}
 +			}
 +			flux.close();
 +		} catch (IOException ioe) {
 +			System.err.println(ioe);
 +		} catch (ClassNotFoundException cnfe) {
 +			System.err.println(cnfe);
 +		}
 +		
 +		try {
 +			File flangues = new File("langues.txt");
 +			ObjectInputStream flux = new ObjectInputStream(new FileInputStream(flangues));
 +			Prof leprof = (Prof) flux.readObject();
 +			while (null != leprof) {
 +				try {
 +					this.langues.add(leprof);
 +					leprof = (Prof) flux.readObject();
 +				} catch (EOFException eofe) {
 +					break;
 +				}
 +			}
 +			flux.close();
 +		} catch (IOException ioe) {
 +			System.err.println(ioe);
 +		} catch (ClassNotFoundException cnfe) {
 +			System.err.println(cnfe);
 +		}
 +		
 +		try {
 +			File flitt = new File("litt.txt");
 +			ObjectInputStream flux = new ObjectInputStream(new FileInputStream(flitt));
 +			Prof leprof = (Prof) flux.readObject();
 +			while (null != leprof) {
 +				try {
 +					this.litt.add(leprof);
 +					leprof = (Prof) flux.readObject();
 +				} catch (EOFException eofe) {
 +					break;
 +				}
 +			}
 +			flux.close();
 +		} catch (IOException ioe) {
 +			System.err.println(ioe);
 +		} catch (ClassNotFoundException cnfe) {
 +			System.err.println(cnfe);
 +		}
 +		
 +		try {
 +			File feco = new File("eco.txt");
 +			ObjectInputStream flux = new ObjectInputStream(new FileInputStream(feco));
 +			Prof leprof = (Prof) flux.readObject();
 +			while (null != leprof) {
 +				try {
 +					this.eco.add(leprof);
 +					leprof = (Prof) flux.readObject();
 +				} catch (EOFException eofe) {
 +					break;
 +				}
 +			}
 +			flux.close();
 +		} catch (IOException ioe) {
 +			System.err.println(ioe);
 +		} catch (ClassNotFoundException cnfe) {
 +			System.err.println(cnfe);
 +		}
 +		
 +		try {
 +			File flisteProfs = new File("listeProfs.txt");
 +			ObjectInputStream flux = new ObjectInputStream(new FileInputStream(flisteProfs));
 +			Prof leprof = (Prof) flux.readObject();
 +			while (null != leprof) {
 +				try {
 +					this.listeProfs.add(leprof);
 +					leprof = (Prof) flux.readObject();
 +				} catch (EOFException eofe) {
 +					break;
 +				}
 +			}
 +			flux.close();
 +		} catch (IOException ioe) {
 +			System.err.println(ioe);
 +		} catch (ClassNotFoundException cnfe) {
 +			System.err.println(cnfe);
 +		}
 +		
 +		try {
 +			File flisteEleves = new File("listeEleves.txt");
 +			ObjectInputStream flux = new ObjectInputStream(new FileInputStream(flisteEleves));
 +			Eleve leleve = (Eleve) flux.readObject();
 +			while (null != leleve) {
 +				try {
 +					this.listeEleves.add(leleve);
 +					leleve = (Eleve) flux.readObject();
 +				} catch (EOFException eofe) {
 +					break;
 +				}
 +			}
 +			flux.close();
 +		} catch (IOException ioe) {
 +			System.err.println(ioe);
 +		} catch (ClassNotFoundException cnfe) {
 +			System.err.println(cnfe);
 +		}
 +		
 +	}
 +	
 +	// A la fermeture du programme, la méthode enregistre les attributs de LeBonCours sur un ficher texte
 +	public void EnregistreFichTXT () {
 +		try {
 +			File bio = new File("bio.txt"); // Création d'un objet de la classe File
 +			ObjectOutputStream flux = new ObjectOutputStream(new FileOutputStream(bio)); // Création d'un objet de flux d'écriture d'objets
 +			for (int i = 0; i < this.bio.size(); i++) {
 +				flux.writeObject(this.bio.get(i));
 +			} // Ecriture de tous les objets de la liste bio dans le fichier texte
 +			flux.close(); // Fermeture du flux
 +		} catch (IOException ioe) {
 +			System.err.println(ioe); 
 +			// Exception levée si interruption des opérations de flux entrée/sortie.
 +		}
 +		
 +		try {
 +			File maths = new File("maths.txt");
 +			ObjectOutputStream flux = new ObjectOutputStream(new FileOutputStream(maths)); 
 +			for (int i = 0; i < this.maths.size(); i++) {
 +				flux.writeObject(this.maths.get(i));
 +			}
 +			flux.close();
 +		} catch (IOException ioe) {
 +			System.err.println(ioe); 
 +		}
 +		
 +		try {
 +			File phy = new File("phy.txt");
 +			ObjectOutputStream flux = new ObjectOutputStream(new FileOutputStream(phy)); 
 +			for (int i = 0; i < (this.phy).size(); i++) {
 +				flux.writeObject(this.phy.get(i));
 +			}
 +			flux.close();
 +		} catch (IOException ioe) {
 +			System.err.println(ioe); 
 +		}
 +		
 +		try {
 +			File langues = new File("langues.txt");
 +			ObjectOutputStream flux = new ObjectOutputStream(new FileOutputStream(langues)); 
 +			for (int i = 0; i < (this.langues).size(); i++) {
 +				flux.writeObject(this.langues.get(i));
 +			}
 +			flux.close();
 +		} catch (IOException ioe) {
 +			System.err.println(ioe); 
 +		}
 +		
 +		try {
 +			File litt = new File("litt.txt");
 +			ObjectOutputStream flux = new ObjectOutputStream(new FileOutputStream(litt)); 
 +			for (int i = 0; i < this.litt.size(); i++) {
 +				flux.writeObject(this.litt.get(i));
 +			}
 +			flux.close();
 +		} catch (IOException ioe) {
 +			System.err.println(ioe); 
 +		}
 +		
 +		try {
 +			File eco = new File("eco.txt");
 +			ObjectOutputStream flux = new ObjectOutputStream(new FileOutputStream(eco)); 
 +			for (int i = 0; i < (this.eco).size(); i++) {
 +				flux.writeObject(this.eco.get(i));
 +			}
 +			flux.close();
 +		} catch (IOException ioe) {
 +			System.err.println(ioe); 
 +		}
 +		
 +		try {
 +			File listeProfs = new File("listeProfs.txt");
 +			ObjectOutputStream flux = new ObjectOutputStream(new FileOutputStream(listeProfs)); 
 +			for (int i = 0; i < (this.listeProfs).size(); i++) {
 +				flux.writeObject(this.listeProfs.get(i));
 +			}
 +			flux.close();
 +		} catch (IOException ioe) {
 +			System.err.println(ioe); 
 +		}
 +		
 +		try {
 +			File listeEleves = new File("listeEleves.txt");
 +			ObjectOutputStream flux = new ObjectOutputStream(new FileOutputStream(listeEleves)); 
 +			for (int i = 0; i < (this.listeEleves).size(); i++) {
 +				flux.writeObject(this.listeEleves.get(i));
 +			}
 +			flux.close();
 +		} catch (IOException ioe) {
 +			System.err.println(ioe); 
 +		}
 +	}
 +	
 +	public void main(String[] args){
 +		System.out.println("Bienvenue sur LE BON COURS");
 +	}
 +	
 +}
