package insa.projet.leboncours.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import insa.projet.leboncours.*;

public interface RMIServeur extends Remote {
	
	public void InscriptionProf(ArrayList<Prof> matiere, ArrayList<Prof> listeProf, Prof leprof) throws RemoteException, DejaEnregistreeProf;
	
	public void InscriptionEleve(ArrayList<Eleve> listeEleves, Eleve leleve) throws RemoteException, DejaEnregistreeEleve;
	
	public int Connexion(String nom, String prenom, LeBonCours leboncours) throws RemoteException, PersonneInconnue;
	
	public ArrayList<Prof> RechercheProfDispo(ArrayList<Prof> matiere, int jour, int heure) throws RemoteException;

	public LeBonCours getLeBonCours() throws RemoteException;
}
