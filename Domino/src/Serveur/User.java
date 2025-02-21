package Serveur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
	String pseudo;
	String photo;
	Connection con;
	Statement stmt;
	public User() {

		try {
			this.pseudo="";
			this.photo="";
			this.con=Connexion.connexion();
			this.stmt = this.con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void addUser() {
		
			try {
				
				this.stmt.executeUpdate("INSERT INTO user VALUE(null,'"+getPseudo()+"','"+getPhoto()+"');");
				System.out.println("add succesfully!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		
	}
	
	public void updateUser(int id) {
		try {
			this.stmt.executeUpdate("UPDATE user SET pseudo='"+getPseudo()+"',photo='"+getPhoto()+"' WHERE id_user="+id);
			System.out.println("update succesfully!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showAllUser() {
		ResultSet res;
		try {
			res = stmt.executeQuery("SELECT * FROM user");
			
			while (res.next()) {
				System.out.println(res.getInt(1)+" "+res.getString(2)+" "+res.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public int searchUser(String user) {
		ResultSet res;
		try {
			
			res = stmt.executeQuery("SELECT * FROM user WHERE pseudo='"+user+"'");
			
			while (res.next()) {
				if(res.getString(2).equals(user)) {
					System.out.println(res.getInt(1)+" "+res.getString(2)+" "+res.getString(3));
					return res.getInt(1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	
	}
	
}
