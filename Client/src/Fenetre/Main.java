package Fenetre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Client.Domino;
import Client.Joueur;
import Client.dataDomino;
import Client.Client;

public class Main extends JFrame{
	JPanel fenetrePrincipale=new JPanel();
	JPanel contPaneDom=new JPanel();
	JPanel contPaneDomClient=new JPanel();
	JPanel paneDom=new JPanel();
	JPanel paneDomClient=new JPanel();
	JPanel paneGauche=new JPanel();
	JPanel paneDroite=new JPanel();
	TableDom paneCentre=new TableDom();
	JPanel joueurAutre=new JPanel();
	String message="";
	JButton btnPass;
	
	public Main() {

		paneDom.setBackground(Color.GREEN);
		paneDom.setPreferredSize(new Dimension(1600, 600));
		//paneDomClient.
		//paneDomClient.setPreferredSize(new Dimension(1500, 250));
		paneDom.setLayout(new BorderLayout());
		paneDom.add(paneDroite, BorderLayout.EAST);
		paneDroite.setBackground(Color.cyan);
		paneDom.add(paneCentre, BorderLayout.CENTER);
		paneDom.add(paneGauche,BorderLayout.WEST);
		paneGauche.setBackground(Color.BLACK);

		paneDom.setLayout(new BorderLayout());
		paneDom.add(paneDroite, BorderLayout.EAST);
		paneDroite.setBackground(Color.cyan);
		paneDom.add(paneCentre, BorderLayout.CENTER);
		paneDom.add(paneGauche,BorderLayout.WEST);
		paneGauche.setBackground(Color.BLACK);
		
		contPaneDom.add(paneDom);
		contPaneDomClient.setBackground(Color.LIGHT_GRAY);
		contPaneDomClient.add(paneDomClient);
		fenetrePrincipale.setLayout(new BorderLayout());
		fenetrePrincipale.add(joueurAutre, BorderLayout.NORTH);
		fenetrePrincipale.add(contPaneDom, BorderLayout.CENTER);
		fenetrePrincipale.add(contPaneDomClient, BorderLayout.SOUTH);
		this.setContentPane(fenetrePrincipale);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionDomino() {
		paneDomClient.hide();
		paneDomClient.removeAll();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		tour();
		paneDomClient.setLayout(new GridLayout(1,dataDomino.domino.size()));
		for (Domino d : dataDomino.domino) {
			RotatedIcon ri = new RotatedIcon(iconImage(d.getPartie1()+""+d.getPartie2()), 90.0);
			JButton btn=new JButton(ri);
			paneDomClient.add(btn);
			btn.setEnabled(Client.tour);
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					message=d.getPartie1()+":"+d.getPartie2();
					
				}
			});
		}
		paneDomClient.show();
		
	}
	
	public  void tableDomino() {
		/*paneCentre.hide();
		paneCentre.removeAll();
		for (int i=0; i<DomEnvoyer.dominoAll.size();i++) {
			JLabel lbl=new JLabel(iconImage(DomEnvoyer.dominoAll.get(i).getPartie1()+""+DomEnvoyer.dominoAll.get(i).getPartie2()));
			paneCentre.add(lbl);
		}
		paneCentre.show();*/
		paneCentre.repaint();
		paneCentre.revalidate();
		paneCentre.removeAll();
		
		
		
	}
	public void actualiser() {
		joueur();
		actionDomino();
		gauche();
		droite();
		tableDomino();
		
	}
	
	public  void gauche() {
		paneGauche.removeAll();
		JButton btnGauche=new JButton("GAUCHE");
		paneGauche.add(btnGauche);
		btnGauche.setEnabled(Client.tour);
		btnGauche.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Client.envoi(message+",g");
				
			}
		});
	}
	
	public void droite() {
		paneDroite.removeAll();
		JButton btnDroite=new JButton("DROITE");
		paneDroite.add(btnDroite);
		btnDroite.setEnabled(Client.tour);
		btnDroite.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Client.envoi(message+",d");
			}
		});
	}
	
	public void tour() {
		JPanel paneTour=new JPanel();
		 btnPass=new JButton();
		paneDomClient.add(paneTour);
		paneTour.add(btnPass);
		btnPass.setForeground(Color.WHITE);
		//lblTour.setPreferredSize(new Dimension(50,50));
		if(Client.tour) {
			btnPass.setBackground(Color.GREEN);
			btnPass.setText("Passer");
			btnPass.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				Client.passer();						
				}
			});
		}else {
			btnPass.setBackground(Color.RED);
			btnPass.setText("Attente");
			btnPass.setEnabled(false);
		}
	}
	
	public static Icon iconImage(String path) {
		 ImageIcon icon =new ImageIcon("domino/"+path+".png");
		 Image img=icon.getImage().getScaledInstance(100, 70, Image.SCALE_DEFAULT);
		 icon.setImage(img);
		return icon;
	}
	
	public void confirmation(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Information",JOptionPane.INFORMATION_MESSAGE);
	}
	public void joueur() {
		joueurAutre.hide();
		joueurAutre.removeAll();
		joueurAutre.setLayout(new GridLayout(1, dataDomino.joueur.size()));
		for (Joueur c:dataDomino.joueur) {
			JPanel contJoueur=new JPanel();
			JPanel paneNom=new JPanel();
			paneNom.add(new JLabel(c.getNom()));
			contJoueur.add(paneNom);
			for (int i = 0; i < c.getNbr_dom(); i++) {
				JLabel dom=new JLabel("");
				dom.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				dom.setPreferredSize(new Dimension(40,70));
				contJoueur.add(dom);
			}
		joueurAutre.add(contJoueur);
		}
		joueurAutre.show();
	}
	
	public void ouvrir() {
		this.setVisible(true);
	}
}