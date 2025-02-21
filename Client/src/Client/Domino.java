package Client;

public class Domino {
	int partie1;
	int partie2;
	Double rotation;
	public Domino(int p1, int p2, Double d) {
		this.partie1=p1;
		this.partie2=p2;
		this.rotation=d;
	}
	public int getPartie1() {
		return partie1;
	}
	public void setPartie1(int partie1) {
		this.partie1 = partie1;
	}
	public int getPartie2() {
		return partie2;
	}
	public void setPartie2(int partie2) {
		this.partie2 = partie2;
	}
	public Double getRotation() {
		return rotation;
	}
	public void setRotation(Double rotation) {
		this.rotation = rotation;
	}

}
