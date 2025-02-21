package Serveur;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public interface Partage {
	static ArrayList<Domino> domino=new ArrayList<>();
	public static void initialisation() {
		try {
			BufferedReader br=new BufferedReader(new FileReader("fichier/domino.txt"));
			String line;
			while ((line=br.readLine())!=null) {
				String[] partie=line.split(",");
				domino.add(new Domino(Integer.parseInt(partie[0]), Integer.parseInt(partie[1]),0.0));
			}
			br.close();
			/*for (Domino dom : domino) {
				System.out.println(dom.getPartie1()+":"+dom.getPartie2());
			}*/
			//partager();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void partager() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 7; j++) {
				int index= (int) (Math.random()*domino.size());
				System.out.println(domino.get(index).getDomino());
				domino.remove(index);
			}
		}
	}
}
