package Fenetre;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Client.DomEnvoyer;

public class TableDom extends JPanel {
		public TableDom() {
			this.setBackground(Color.GREEN);
			this.setPreferredSize(new Dimension(1000,500));
		}
	@Override
	public void paintComponent(Graphics g) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i=0; i<DomEnvoyer.dominoAll.size();i++) {
			RotatedIcon ri = new RotatedIcon(Main.iconImage(DomEnvoyer.dominoAll.get(i).getPartie1()+""+DomEnvoyer.dominoAll.get(i).getPartie2()),DomEnvoyer.dominoAll.get(i).getRotation());
			JLabel lbl=new JLabel(ri);
			this.add(lbl);
		}
	}
}
