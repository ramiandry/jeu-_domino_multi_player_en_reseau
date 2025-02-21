package Fenetre;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;

import javax.swing.border.LineBorder;

import Client.Client;
import Client.IPAdress;

//import Client.Main;

public class Acceuil extends JFrame {
	JPanel fenetrePrincipale=new JPanel();
	JPanel logo=new JPanel();
	JPanel contLogo=new JPanel();
	JLabel lblLogo=new JLabel("Dominos Games");
	JPanel jouer=new JPanel();
	JButton btnJoindre=new JButton("Rejoindre une partie");
	JButton btnparam=new JButton("Parametres");
	JButton btnQuitter=new JButton("Quitter");
	JPanel paneBtnCont=new JPanel();
	JPanel paneBtn=new JPanel();
	JPanel contPaneIP=new JPanel();
	JPanel paneIP=new JPanel();
	JLabel lblIp=new JLabel();
	JPanel paneContainer=new JPanel();
	JPanel paneStatistique=new JPanel();
	JPanel paneContStatistique=new JPanel();
	JPanel paneMenu=new JPanel();

	public Acceuil() {
		logo.setBackground(new Color(136, 138, 133));
		logo.setLayout(new BorderLayout());
		logo.setPreferredSize(new Dimension(1000,140));
		contLogo.add(logo);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.add(lblLogo, BorderLayout.CENTER);
		lblLogo.setForeground(new Color(255, 255, 255));
		lblLogo.setFont(new Font("Dialog", Font.BOLD, 65));
		btnparam.setVerticalAlignment(SwingConstants.BOTTOM);
		btnparam.setBounds(50, 50, 250, 40);
		
		GridLayout gl_jouer = new GridLayout(0,1);
		gl_jouer.setVgap(30);
		gl_jouer.setHgap(30);
		paneBtn.setLayout(gl_jouer);
		paneBtn.add(btnJoindre);
		btnJoindre.addActionListener(runClient());
		paneBtn.add(btnparam);
		paneBtn.add(btnQuitter);
		paneBtnCont.add(paneBtn);
		jouer.add(paneBtnCont);
		
		lblIp.setFont(new Font("Dialog", Font.BOLD, 22));
		lblIp.setText("IP: "+IPAdress.getInterfaces());
		paneIP.add(lblIp);
		
		contPaneIP.add(paneIP);
		paneMenu.setLayout(new BorderLayout());
		paneMenu.add(jouer, BorderLayout.NORTH);
		paneMenu.add(contPaneIP, BorderLayout.SOUTH);
		paneMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		paneContStatistique.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		paneStatistique.setLayout(new GridLayout(1,4));
		paneStatistique.add(new JLabel("N° partie"));
		paneStatistique.add(new JLabel("Nombre de joueur"));
		paneStatistique.add(new JLabel("Score"));
		paneStatistique.add(new JLabel("Gagnant"));
		paneContStatistique.add(paneStatistique);
		paneContainer.setLayout(new BorderLayout());
		paneContainer.add(paneMenu, BorderLayout.WEST);
		paneContainer.add(paneContStatistique, BorderLayout.CENTER);
		fenetrePrincipale.setLayout(new BorderLayout());
		fenetrePrincipale.add(contLogo, BorderLayout.NORTH);
		fenetrePrincipale.add(paneContainer, BorderLayout.CENTER);
		//fenetrePrincipale.add(contPaneIP, BorderLayout.SOUTH);
		this.setContentPane(fenetrePrincipale);
		this.setSize(new Dimension(1000,600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		new Client();
	}
	
	
	public ActionListener runClient() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fermer();
				Client.ouvrirMain();
			}
		};
	}
	
	public void fermer() {
		this.setVisible(false);
	}

}
