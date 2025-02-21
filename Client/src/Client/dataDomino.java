package Client;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public interface dataDomino {
	static ArrayList<Domino> domino=new ArrayList<>();
	static ArrayList<Joueur> joueur=new ArrayList<>();
	
	public static void addDomino(int p1,int p2, Double r) {
		domino.add(new Domino(p1, p2,r));
	}
	
	public static void addJoueur(String n, int nbr) {
		joueur.add(new Joueur(n, nbr));
		System.out.println("Ajout"+n);
	}
	
	public static void modifierJoueur(int pos, String nbr) {
			joueur.get(pos).setNbr_dom(Integer.parseInt(nbr));
	}
	
	public static int rechercher(String n) {
		int i=0;
		for (Joueur joueur : joueur) {
			if(joueur.getNom().equals(n)) {
				return i;
			}
			i++;
		}
		return -1;
	}


}
