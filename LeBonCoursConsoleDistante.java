package insa.projet.leboncours.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import insa.projet.leboncours.ihm.FenetrePrincipale;

/**
 * Classe qui permet de lancer l'interface graphique en ouvrant la fenetre principale de connexion ou inscription
 * 
 * @author Barthelemy Wade & Tarek Al-Jijakli & Claire Guilloteau
 * @version 1.beaucoup
 * @since 1.beaucoup
 */
public class LeBonCoursConsoleDistante {
	
	/**
	 * Le serveur de "leboncours"
	 */
	private static RMIServeur leboncoursDistant;
	
	/**
	 * Le constructeur 
	 * Lit les donnees initiales sur le fichier texte
	 * 
	 * @throws RemoteException
	 */
	public LeBonCoursConsoleDistante() throws RemoteException{
		leboncoursDistant = new RMIServeurImpl();
		leboncoursDistant.getLeBonCours().LancementFichTXT();
	}
	
	/**
	 * Methode permettant d'ouvrir la fenetre principale de "leboncours"
	 * 
	 * @throws RemoteException
	 */
	public void run() throws RemoteException {
		FenetrePrincipale maFenetre = new FenetrePrincipale(leboncoursDistant);
		maFenetre.setVisible(true);
	}
	
	/**
	 * La methode main qui ouvre la fenetre principale du serveur "leboncours"
	 * 
	 * @param args le premier parametre peut indiquer l'hote du registre RMI
	 */
	public static void main(String[] args) {
		try {
			if (args.length == 0) leboncoursDistant = (RMIServeur)LocateRegistry.getRegistry().lookup("leboncours");
			else leboncoursDistant = (RMIServeur)LocateRegistry.getRegistry(args[0]).lookup("leboncours");
			LeBonCoursConsoleDistante console = new LeBonCoursConsoleDistante();
			console.run();
			// Pb enregistrement du fichier texte
			// leboncoursDistant.getLeBonCours().EnregistreFichTXT();
			// Voir à ajouter un WindowListener pour enregistrer sur le fichier texte
		} catch (RemoteException ex) {
			ex.printStackTrace();
		} catch (NotBoundException ex) {
			ex.printStackTrace(); 
		}
	}

}
