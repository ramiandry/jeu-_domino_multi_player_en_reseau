package Serveur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public interface Connexion {

	public static Connection connexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/domino","root","raf");
			System.out.println(con);
			/*Statement stmt=con.createStatement();
			ResultSet res=stmt.executeQuery("SELECT * FROM user");
			
			while (res.next()) {
				System.out.println(res.getInt(1)+" "+res.getString(2));
			}
			*/
			return con;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

}
