package insa.projet.leboncours.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import insa.projet.leboncours.*;

/**
 * L'interface RMI pour un serveur de "leboncours"
 * 
 * @author Barthélémy Wade & Tarek Al-Jijakli & Claire Guilloteau
 * @version 1.2
 * @since 1.2
 */
public interface RMIServeur extends Remote {
	
	/**
	 * Inscrit un prof sur leboncours
	 * 
	 * @param matiere la matiere que dispense le prof
	 * @param listeProf la liste de tous les profs de leboncours
	 * @param leprof le prof a inscrire
	 * @throws DejaEnregistreeProf si le prof est deja enregistre sur leboncours
	 */
	public void InscriptionProf(ArrayList<Prof> matiere, ArrayList<Prof> listeProf, Prof leprof) throws RemoteException, DejaEnregistreeProf;
	
	/**
	 * Inscrit un eleve sur leboncours
	 * 
	 * @param listeEleves la liste de tous les eleves de leboncours
	 * @param leleve l'eleve a inscrire
	 * @throws DejaEnregistreeEleve si l'eleve est deja enregistre sur leboncours
	 */
	public void InscriptionEleve(ArrayList<Eleve> listeEleves, Eleve leleve) throws RemoteException, DejaEnregistreeEleve;
	
	/**
	 * Connecte un prof ou un eleve a partir de son nom et son prenom sur leboncours
	 * 
	 * @param nom le nom de la personne qui souhaite se connecter
	 * @param prenom le prenom de cette personne
	 * @throws PersonneInconnue si la personne n'est pas inscrite sur leboncours
	 */
	public int Connexion(String nom, String prenom) throws RemoteException, PersonneInconnue;
	
	/**
	 * Recherche un prof disponible pour une matiere, un jour et une heure
	 * 
	 * @param matiere la matière recherchee
	 * @param jour le jour de cours souhaite
	 * @param heure l'heure de cours souhaitee
	 */
	public ArrayList<Prof> RechercheProfDispo(ArrayList<Prof> matiere, int jour, int heure) throws RemoteException;

	/**
	 * Retourne l'objet de la classe LeBonCours associe au serveur RMI
	 */
	public LeBonCours getLeBonCours() throws RemoteException;
}
