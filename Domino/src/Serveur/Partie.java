package Serveur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Partie {
	private int nbr_joueur;
	private Connection con;
	private Statement stmt;
	public Partie() {

		try {
			this.nbr_joueur=0;
			this.con=Connexion.connexion();
			this.stmt = this.con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getNbr_joueur() {
		return nbr_joueur;
	}
	public void setNbr_joueur(int nbr_joueur) {
		this.nbr_joueur = nbr_joueur;
	}
	
	public void addPartie() {
		
		try {
			this.stmt = this.con.createStatement();
			this.stmt.executeUpdate("INSERT INTO partie VALUE(null,"+getNbr_joueur()+",null);");
			System.out.println("add succesfully!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

	public void updatePartie(int id_user, int id_partie) {
		try {
			this.stmt = this.con.createStatement();
			this.stmt.executeUpdate("UPDATE partie SET id_user="+id_user+" WHERE id_partie="+id_partie);
			System.out.println("update succesfully!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showAllPartie() {
		ResultSet res;
		try {
			res = stmt.executeQuery("SELECT * FROM partie");
			
			while (res.next()) {
				System.out.println(res.getInt(1)+" "+res.getInt(2)+" "+res.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public int lastPartie() {
		ResultSet res;
		try {
			res = stmt.executeQuery("SELECT MAX(id_partie) FROM partie");
			
			while (res.next()) {
				return res.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
