package Fenetre;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Username extends JFrame {
	JLabel bienvenu=new JLabel("Bienvenu à Dominos Games!!");
	JLabel demandeNom=new JLabel("Veuillez ecrire votre username");
	JButton btnValider=new JButton("Valider");
	JPanel paneBienvenu=new JPanel();
	JPanel paneContaine=new JPanel();
	JPanel paneForm=new JPanel();
	JPanel paneContForm=new JPanel();
	static JTextField txtNom=new JTextField();
	JPanel paneValider=new JPanel();

	JPanel contentPane=new JPanel();
	public Username() {
		paneBienvenu.add(bienvenu);
		paneForm.setLayout(new GridLayout(2,1));
		paneForm.add(demandeNom);
		paneForm.add(txtNom);
		
		paneForm.setPreferredSize(new Dimension(300,80));
		paneValider.add(btnValider);
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fermer();
				new Acceuil();
				
			}
		});
		paneContForm.add(paneForm);
		contentPane.setLayout(new BorderLayout());
		contentPane.add(paneBienvenu, BorderLayout.NORTH);
		contentPane.add(paneContForm, BorderLayout.CENTER);
		contentPane.add(paneValider,BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setVisible(true);
	}
	
	public static String getNomJoueur() {
		
		return txtNom.getText().toString().trim();
	}
	
	public void fermer() {
		this.setVisible(false);
	}

}
