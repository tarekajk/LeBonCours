package insa.projet.leboncours.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Scanner;

import insa.projet.leboncours.Eleve;
import insa.projet.leboncours.Prof;

public class EleveConsoleDistante {
	
	private Eleve leleve;
	private RMIServeur leboncoursDistant;

	public EleveConsoleDistante(Eleve lel, RMIServeur l) {
		leleve = lel;
		leboncoursDistant = l;
	}

	public void run() throws RemoteException {
		int choix = 1;
		int jour = 0;
		int heure = 0;
		ArrayList<Prof> liste = new ArrayList<Prof>();
		ArrayList<Prof> listeDispo = new ArrayList<Prof>();
		Scanner lecture = new Scanner(System.in);
		while (choix != 0) {
			System.out.println("Que voulez-vous faire ?");
			System.out.println("0 - Quitter");
			System.out.println("1 - Rechercher un prof dans une matiere");
			choix = Integer.parseInt(lecture.nextLine());
			if (choix == 1) {
				System.out.println("Donner la matiere que vous voulez etudier parmi : biologie(tapez bio),maths(maths),physique(phy),langues(langues),litt�rature(litt),economie(eco)");
				int matiere = Integer.parseInt(lecture.nextLine());
				System.out.println("Donner le jour et l'heure pour lesquels vous etes disponible");
				jour = Integer.parseInt(lecture.nextLine())-1;
				heure = Integer.parseInt(lecture.nextLine())-1;
				switch (matiere){
					case 1 :
						liste = this.leboncoursDistant.getLeBonCours().bio;
						break;
					case 2 :
						liste = this.leboncoursDistant.getLeBonCours().maths;
						break;
					case 3 :
						liste = this.leboncoursDistant.getLeBonCours().phy;
						break;
					case 4 :
						liste = this.leboncoursDistant.getLeBonCours().langues;
						break;
					case 5 :
						liste = this.leboncoursDistant.getLeBonCours().litt;
						break;
					case 6 :
						liste = this.leboncoursDistant.getLeBonCours().eco;
						break;
				}
				listeDispo = this.leboncoursDistant.RechercheProfDispo(liste, jour, heure);
				for (int i=0;i<listeDispo.size();i++) {
					System.out.println(listeDispo.get(i).getNom());}
					if (listeDispo.size() == 0) {
						System.out.println("Il n'y a pas de prof dispo");
					}
					}	
		}
		
		lecture.close();
	}

	/**
	 * La methode main qui lance un client de connexion eleve a la plateforme leboncours
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
		leleve = this.leboncoursDistant.getLeBonCours().TrouveUnEleve(this.leboncoursDistant.getLeBonCours().getListeEleve(),nom,prenom);
		if (leleve==null) {
			// En graphique : ouvrir page inscription Eleve puis recuperer les infos (tp7)
			System.out.println("Vous n'etes pas encore inscrit, veuillez indiquer votre sexe (M ou F");
			String sexe = lecture.nextLine();
			System.out.print("Votre age :");
			int age = Integer.parseInt(lecture.nextLine());
			System.out.print("Votre niveau (Tle : 0, 1ere : -1, etc...");
			int niveau = Integer.parseInt(lecture.nextLine());
			System.out.print("Votre Code Postal :");
			int cp = Integer.parseInt(lecture.nextLine());
			leleve = new Eleve(nom,prenom,sexe,age,niveau,cp);
		}
		RMIServeur l;
		try {
			if (args.length == 0) l = (RMIServeur)LocateRegistry.getRegistry().lookup("leboncours");
			else l = (RMIServeur)LocateRegistry.getRegistry(args[0]).lookup("leboncours");
			EleveConsoleDistante console = new EleveConsoleDistante(leleve,l);
			console.run();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		} catch (NotBoundException ex) {
			ex.printStackTrace(); 
		}
		lecture.close();
	}
	

}
