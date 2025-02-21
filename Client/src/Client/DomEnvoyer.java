package Client;

import java.util.ArrayList;

public interface DomEnvoyer {
	static ArrayList<Domino> dominoAll=new ArrayList<>();
	public static void addDominoAll(int p1,int p2,Double r) {
		dominoAll.add(new Domino(p1, p2,r));
	}

}
