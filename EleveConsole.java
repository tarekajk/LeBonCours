package insa.projet.leboncours;

import java.util.ArrayList;
import java.util.Scanner;

public class EleveConsole {
	
	private Eleve eleve;
	
	private LeBonCours leboncours;

	public EleveConsole(Eleve eleve, LeBonCours leboncours) {
		this.eleve = eleve;
		this.leboncours = leboncours;
	}
	
	public void run() {
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
				System.out.println("Donner la matiere que vous voulez etudier parmi : biologie(tapez 1),maths(2),physique(3),langues(4),litterature(5),economie(6)");
				int matiere = Integer.parseInt(lecture.nextLine());
				System.out.println("Donner le jour et l'heure pour lesquels vous etes disponible");
				jour = Integer.parseInt(lecture.nextLine())-1;
				heure = Integer.parseInt(lecture.nextLine())-1;
				switch (matiere){
					case 1 :
						liste = this.leboncours.bio;
						break;
					case 2 :
						liste = this.leboncours.maths;
						break;
					case 3 :
						liste = this.leboncours.phy;
						break;
					case 4 :
						liste = this.leboncours.langues;
						break;
					case 5 :
						liste = this.leboncours.litt;
						break;
					case 6 :
						liste = this.leboncours.eco;
						break;
				}
				listeDispo = this.leboncours.RechercheProf(liste, jour, heure);
				for (int i=0;i<listeDispo.size();i++) {
					System.out.println(listeDispo.get(i).getNom());}
					if (listeDispo.size() == 0) {
						System.out.println("Il n'y a pas de prof dispo");
					}
					}	
		}
	
		lecture.close();
	}
	
	

}
