package Serveur;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public interface Domclient {
	static ArrayList<Domino> domclient=new ArrayList<>();
	static String file="fichier/domclient.txt";
	
	public static void readData() {
		domclient.clear();
		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			String line;
			while ((line=br.readLine())!=null) {
				String[] res=line.split(",");
				domclient.add(new Domino(Integer.parseInt(res[0].split(":")[0]), Integer.parseInt(res[0].split(":")[1]),Double.parseDouble(res[1])));
				
			}
			for (Domino domino : domclient) {
				System.out.println(domino.getDomino());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void addDomclient(Domino d,String pos) {
		try {

			BufferedWriter bw=new BufferedWriter(new FileWriter(file));
			 if(pos.equals("g")){
				bw.write(d.getDomino());
				for (Domino domino : domclient) {
					bw.newLine();
					bw.write(domino.getDomino());
				}
			}else if (pos.equals("d")) {
				for (Domino domino : domclient) {
				bw.write(domino.getDomino());
				bw.newLine();
				}
				bw.write(d.getDomino());
			}
	
			bw.close();
			readData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
 