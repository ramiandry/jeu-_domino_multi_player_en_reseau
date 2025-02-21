package Serveur;

public class Domino {
	private int partie1;
	private int partie2;
	private Double rotation;
	public Domino(int p1, int p2, Double r) {
		this.partie1=p1;
		this.partie2=p2;
		this.rotation=r;
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
	public String getDomino() {
		return getPartie1()+":"+getPartie2()+","+rotation;
	}
	public Double getRotation() {
		return rotation;
	}
	public void setRotation(Double rotation) {
		this.rotation = rotation;
	}

}
