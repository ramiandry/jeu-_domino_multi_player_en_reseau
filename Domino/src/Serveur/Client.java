package Serveur;

import java.net.Socket;

public class Client {
	Socket socket;
	String nom;
	int nbr_dom;
	 
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
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

	public Client(Socket s, String n, int nbr) {
		this.socket=s;
		this.nom=n;
		this.nbr_dom=nbr;
	}

}
