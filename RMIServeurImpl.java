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


@SuppressWarnings("serial")
public class RMIServeurImpl extends UnicastRemoteObject implements RMIServeur {
	
	public static LeBonCours leboncours;
	
	public RMIServeurImpl() throws RemoteException {
 		super();
 		leboncours = new LeBonCours();
 		}
 	
 	public LeBonCours getLeBonCours() throws RemoteException {
 		return leboncours;
 	}
 	
 	public void InscriptionProf(ArrayList<Prof> matiere, ArrayList<Prof> listeProf, Prof leprof) throws RemoteException, DejaEnregistreeProf {
 		leboncours.ajouterProf(matiere, listeProf, leprof);
 	}
	
	public void InscriptionEleve(ArrayList<Eleve> listeEleves, Eleve leleve) throws RemoteException, DejaEnregistreeEleve {
		leboncours.ajouterEleve(listeEleves, leleve);
	}
	
	public int Connexion(String nom, String prenom, LeBonCours leboncours) throws RemoteException, PersonneInconnue {
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
	
	public ArrayList<Prof> RechercheProfDispo(ArrayList<Prof> matiere, int jour, int heure) throws RemoteException {
		return leboncours.RechercheProf(matiere, jour, heure);
	}
	
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
