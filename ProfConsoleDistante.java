package insa.projet.leboncours.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Scanner;

import insa.projet.leboncours.Prof;
import insa.projet.leboncours.EmploiDuTemps;
import insa.projet.leboncours.Reservation;


public class ProfConsoleDistante {

	
	private Prof prof;
	private RMIServeur leboncoursDistant;

	public ProfConsoleDistante(Prof p, RMIServeur l) {
		prof = p;
		leboncoursDistant = l;
	}

	public void run() {
		int choix = 1;
		int jour = 0;
		int heure = 0;
		int etat = 0;
		Scanner lecture = new Scanner(System.in);
		while (choix != 0) {
			System.out.println("Que voulez-vous faire ?");
			System.out.println("0 - Quitter");
			System.out.println("1 - Modifier votre emploi du temps");
			choix = Integer.parseInt(lecture.nextLine());
			if (choix == 1) {
				//while (choix2 != 0) {
					//System.out.println("Avez-vous fini de modifier votre emploi du temps?");
					//System.out.println("0 - Oui");
					//System.out.println("1 - Non");
					//choix2 = Integer.parseInt(lecture.nextLine());
					//if (choix2 == 1) {
						System.out.println("Donner le jour et l'heure (en chiffre:lundi=1...) pour lesquels vous voulez modifier vos disponibilites");
						jour = Integer.parseInt(lecture.nextLine())-1;
						heure = Integer.parseInt(lecture.nextLine())-1;
						System.out.println("Pour ce creneau vous etes : 0-Non Disponible 1- Disponible");
						etat = Integer.parseInt(lecture.nextLine());
						this.prof.getEdt().Modifier(this.prof.getEdt().getDispo(), jour, heure, etat);
					}	
		}
		lecture.close();
	}

	/**
	 * La methode main qui lance un client de connexion prof a la plateforme leboncours
	 * 
	 * @param args le premier parametre peut indiquer l'hote du registre RMI (sinon c'est localhost qui est contacte)
	 * @throws RemoteException 
	 */
	public void main(String[] args) throws RemoteException {
		Scanner lecture = new Scanner(System.in);
		System.out.print("Entrer votre nom : ");
		String nom = lecture.nextLine();
		System.out.print("Entrer votre prenom : ");
		String prenom = lecture.nextLine();
		
		int[][] dispo = null;
		ArrayList<Reservation> reserv = null;
		EmploiDuTemps edt = new EmploiDuTemps(dispo,reserv);
		prof = this.leboncoursDistant.getLeBonCours().TrouveUnProf(this.leboncoursDistant.getLeBonCours().getListeProfs(),nom,prenom);
		if (prof==null) {
			// En graphique : ouvrir page inscription Prof puis recuperer les infos (tp7)
			System.out.println("Vous n'etes pas encore inscrit, veuillez indiquer votre sexe (M ou F");
			String sexe = lecture.nextLine();
			System.out.print("Votre age :");
			int age = Integer.parseInt(lecture.nextLine());
			System.out.print("Votre niveau (Tle : 0, 1ere : -1, etc...");
			int niveau = Integer.parseInt(lecture.nextLine());
			System.out.print("Votre Code Postal :");
			int cp = Integer.parseInt(lecture.nextLine());
			System.out.print("Votre prix de l'heure :");
			int prix = Integer.parseInt(lecture.nextLine());
			boolean voiture = true;
			String res = "";
			while (res!="oui" || res !="non") {
				System.out.println("Avez-vous une voiture ? oui ou non");
				res = lecture.nextLine();
			}
			if (res=="non") {
				voiture = false;
			}
			prof = new Prof(nom,prenom,sexe,age,niveau,cp,prix,voiture,edt);
		}
		RMIServeur l;
		try {
			if (args.length == 0) l = (RMIServeur)LocateRegistry.getRegistry().lookup("leboncours");
			else l = (RMIServeur)LocateRegistry.getRegistry(args[0]).lookup("leboncours");
			
			ProfConsoleDistante console = new ProfConsoleDistante(prof,l);
			console.run();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		} catch (NotBoundException ex) {
			ex.printStackTrace(); 
		}
		lecture.close();
	}
	
}
