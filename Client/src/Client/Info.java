package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public interface Info {
	public static void AddInfo() {
		try {
			BufferedWriter bw =new BufferedWriter(new FileWriter("fichier/user.txt"));
			bw.write(1+","+"aina"+","+"photo");
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String ReadInfo() {
		try {
			BufferedReader br=new BufferedReader(new FileReader("fichier/user.txt"));
			String line = br.readLine();
			br.close();
			return line;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
