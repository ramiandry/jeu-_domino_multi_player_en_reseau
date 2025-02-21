package Serveur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Score {
	int score;
	int id_user;
	int id_partie;
	Connection con;
	Statement stmt;
	public Score() {
		try {
			this.id_partie=0;
			this.id_user=0;
			this.score=0;
			this.con=Connexion.connexion();
			this.stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public int getId_partie() {
		return id_partie;
	}
	public void setId_partie(int id_partie) {
		this.id_partie = id_partie;
	}

	public void addScore() {

		try {
			stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO score VALUE(null,'"+this.score+"','"+this.id_user+"','"+this.id_partie+"');");
			System.out.println("insert score succes!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void updateScore(int id_user, int id_score) {
		try {
			this.stmt = this.con.createStatement();
			this.stmt.executeUpdate("UPDATE score SET score='"+getScore()+"' WHERE id_score="+id_score+" AND id_user="+id_user);
			System.out.println("update succesfully!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showAllScore() {
		ResultSet res;
		try {
			res = stmt.executeQuery("SELECT * FROM score");
			
			while (res.next()) {
				System.out.println(res.getInt(1)+" "+res.getInt(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public int searchId(int id_user) {
		ResultSet res;
		try {
			res = stmt.executeQuery("SELECT MAX(id_score) FROM score WHERE id_user="+id_user);
			
			while (res.next()) {
					System.out.println(res.getInt(1));
					return res.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	
	}
}
