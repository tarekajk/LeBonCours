package insa.projet.leboncours.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import insa.projet.leboncours.DejaEnregistreeEleve;
import insa.projet.leboncours.DejaEnregistreeProf;
import insa.projet.leboncours.Eleve;
import insa.projet.leboncours.LeBonCours;
import insa.projet.leboncours.PersonneInconnue;
import insa.projet.leboncours.Prof;

/**
 * Le serveur RMI pour un serveur de "leboncours"
 * 
 * @author Barthelemy Wade & Tarek Al-Jijakli & Claire Guilloteau
 * @version 1.2
 * @since 1.2
 */
@SuppressWarnings("serial")
public class RMIServeurImpl extends UnicastRemoteObject implements RMIServeur {
	
	/**
	 * Une instance le LeBonCours
	 */
	public static LeBonCours leboncours;
	
	/**
	 * Le constructeur
	 * @throws RemoteException
	 */
	public RMIServeurImpl() throws RemoteException {
 		super();
 		leboncours = new LeBonCours();
 		}
 	
	/**
	 * Retourne l'objet de la classe LeBonCours associe au serveur RMI
	 */
 	public LeBonCours getLeBonCours() throws RemoteException {
 		return leboncours;
 	}
 	
	/**
	 * Inscrit un prof sur leboncours
	 * 
	 * @param matiere la matiere que dispense le prof
	 * @param listeProf la liste de tous les profs de leboncours
	 * @param leprof le prof a inscrire
	 * @throws DejaEnregistreeProf si le prof est deja enregistre sur leboncours
	 */
 	public void InscriptionProf(ArrayList<Prof> matiere, ArrayList<Prof> listeProf, Prof leprof) throws RemoteException, DejaEnregistreeProf {
 		leboncours.ajouterProf(matiere, leprof);
 	}
	
 	/**
	 * Inscrit un eleve sur leboncours
	 * 
	 * @param listeEleves la liste de tous les eleves de leboncours
	 * @param leleve l'eleve a inscrire
	 * @throws DejaEnregistreeEleve si l'eleve est deja enregistre sur leboncours
	 */
	public void InscriptionEleve(ArrayList<Eleve> listeEleves, Eleve leleve) throws RemoteException, DejaEnregistreeEleve {
		leboncours.ajouterEleve(leleve);
	}
	
	/**
	 * Connecte un prof ou un eleve a partir de son nom et son prenom sur leboncours
	 * 
	 * @param nom le nom de la personne qui souhaite se connecter
	 * @param prenom le prenom de cette personne
	 * @throws PersonneInconnue si la personne n'est pas inscrite sur leboncours
	 */
	public int Connexion(String nom, String prenom) throws RemoteException, PersonneInconnue {
		int prof=0;
		for(int i = 0; i < leboncours.getListeProfs().size(); i++) {
			if ((leboncours.getListeProfs().get(i).getNom()==nom) && (leboncours.getListeProfs().get(i).getPrenom()==prenom)) {
				prof=1;
			}
		}
		for(int i = 0; i < leboncours.getListeEleve().size(); i++) {
			if ((leboncours.getListeEleve().get(i).getNom()==nom) && (leboncours.getListeProfs().get(i).getPrenom()==prenom)) {
				prof=2;
			}
		}
		if (prof==0) throw new PersonneInconnue(nom,prenom);
		return prof;
	}
	
	/**
	 * Recherche un prof disponible pour une matiere, un jour et une heure
	 * 
	 * @param matiere la matière recherchee
	 * @param jour le jour de cours souhaite
	 * @param heure l'heure de cours souhaitee
	 */
	public ArrayList<Prof> RechercheProfDispo(ArrayList<Prof> matiere, int jour, int heure) throws RemoteException {
		return leboncours.RechercheProf(matiere, jour, heure);
	}
	
	
	/**
	 * La methode main qui demarre et enregistre un service RMI pour leboncours
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RMIServeurImpl m = new RMIServeurImpl();
			Registry registry;
			if (args.length > 0) registry = LocateRegistry.getRegistry(args[0]);
			else registry = LocateRegistry.getRegistry();
			registry.rebind("leboncours", m);
			System.out.println("Bienvenue sur leboncours.fr");
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}
}
