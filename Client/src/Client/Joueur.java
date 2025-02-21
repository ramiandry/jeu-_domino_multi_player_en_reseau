package Client;

public class Joueur {
	String nom;
	int nbr_dom;
	public Joueur(String nom, int nbr_dom) {
		super();
		this.nom = nom;
		this.nbr_dom = nbr_dom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getNbr_dom() {
		return nbr_dom;
	}
	public void setNbr_dom(int nbr_dom) {
		this.nbr_dom = nbr_dom;
	}
	
}
