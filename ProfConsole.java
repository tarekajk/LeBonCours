package insa.projet.leboncours;

import java.util.Scanner;

public class ProfConsole {
	
	private Prof prof;
	
	private LeBonCours leboncours;

	public ProfConsole(Prof leprof, LeBonCours leboncours) {
		this.prof = leprof;
		this.leboncours = leboncours;
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
						jour = Integer.parseInt(lecture.nextLine()) -1;
						heure = Integer.parseInt(lecture.nextLine()) -1;
						System.out.println("Pour ce creneau vous etes : 0-Non Disponible 1- Disponible");
						etat = Integer.parseInt(lecture.nextLine());
						this.prof.getEdt().Modifier(this.prof.getEdt().getDispo(), jour, heure, etat);
					}	
		}
		lecture.close();
	}
	

}
