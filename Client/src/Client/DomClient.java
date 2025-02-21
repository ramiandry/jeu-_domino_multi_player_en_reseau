package Client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

import Fenetre.Main;

public class DomClient extends JPanel {
	DomClient(){
		
		
	}
	@Override
	public void paintComponent(Graphics g) {
		//Main.tour();
		this.revalidate();

		this.setLayout(new GridLayout(1,dataDomino.domino.size()+1));
		for (Domino d : dataDomino.domino) {
			JButton btn=new JButton(Main.iconImage(d.getPartie1()+""+d.getPartie2()));
			this.add(btn);
			System.out.println("eeee");
			
		}

	}
	
	public void changer() {

		this.revalidate();
		this.repaint();
	}
}
