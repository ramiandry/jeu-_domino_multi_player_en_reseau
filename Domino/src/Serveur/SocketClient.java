package Serveur;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public interface SocketClient {
	ArrayList<Socket> socketClient =new ArrayList<>();
	static ArrayList<Client> client=new ArrayList<>();

	public static int rechercherSocket(Socket socketJoueur) {
		for (int i = 0; i < socketClient.size(); i++) {
			if(socketClient.get(i)==socketJoueur) {
				if(i==socketClient.size()-1) {
					return 0;
				}else {
					return i+1;
				}
					
			}
		}
		return 0;
	}
	
	public static void afficherValeur() {
		for (Client c : client) {
			System.out.println(c.getNom()+" "+c.getNbr_dom());
		}
	}
	
	public static void modifierClient(Socket s) {
		for (Client c : client) {
			if(c.getSocket()==s) {
				c.setNbr_dom(c.getNbr_dom()-1);
				break;
			}
		}

	}
}
