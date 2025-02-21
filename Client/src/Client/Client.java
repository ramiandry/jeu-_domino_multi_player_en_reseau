package Client;
import java.awt.desktop.AboutEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.channels.AcceptPendingException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Fenetre.Acceuil;
import Fenetre.Main;
import Fenetre.Username;

public class Client {
    final int port = 9636;
    static Boolean actualiser=true;
     static BufferedReader in;
     static PrintStream out ;
     public static Boolean tour=false;
     static String message="";
     static int index;
     static String nom=Username.getNomJoueur();;
     static Main m = new Main();
	
    public Client(){
    	
    	String message = "";
        Socket socket; //pour stocker la connexion
        try {
            
            InetAddress serveur = InetAddress.getByName("127.0.0.1");
            socket = new Socket(serveur, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           out = new PrintStream(socket.getOutputStream()); 
          
           envoiInfo();

           Thread receiver=new Thread(new Runnable() {
				public void run() {
					String msg;
					try {
						msg=in.readLine();
			            
						while (msg!=null) {
							System.out.println("> "+msg);

							if(msg.substring(0, 3).equals("dom")) {
								if(msg.substring(msg.length()-4).equals("tour")) {
									tour=true;
									String d[]=msg.substring(4, msg.length()-5).split(";");
									for (int i = 0; i < d.length; i++) {
										String[] res=d[i].split(",");
										dataDomino.domino.add(new Domino(Integer.parseInt(res[0].split(":")[0]), Integer.parseInt(res[0].split(":")[1]),Double.parseDouble(res[1])));
									}
									
									msg="";
									
								}else {
									String d[]=msg.substring(4, msg.length()-1).split(";");
									for (int i = 0; i < d.length; i++) {
										String[] res=d[i].split(",");
										dataDomino.domino.add(new Domino(Integer.parseInt(res[0].split(":")[0]), Integer.parseInt(res[0].split(":")[1]),Double.parseDouble(res[1])));
									}
								}

								for (Domino dom : dataDomino.domino) {
									System.out.println(dom.getPartie1()+":"+dom.getPartie2());
								}
								
								//m.actionDomino() ;
							} else if(msg.equals("true")){
								dataDomino.domino.remove(index);
								System.out.println("supprimer!!"+index);
								index=0;
						           System.out.println("---------------------------"+index);
						           for (Domino domino : dataDomino.domino) {
						        	   System.out.println(domino.getPartie1()+":"+domino.getPartie2());
						           }
							} else if (msg.substring(0, 4).equals("tour")) {
								tour=true;
							}else if (msg.equals("pas tour")) {
								tour=false;
							}else if(msg.equals("false")){
							    m.confirmation("Le domino ne correspond pas");
								index=0;
								System.out.println("---------------------------"+index);
						           for (Domino domino : dataDomino.domino) {
						        	   System.out.println(domino.getPartie1()+":"+domino.getPartie2());
						           }
						     
							}else if (msg.substring(0, 3).equals("all")) {
								DomEnvoyer.dominoAll.clear();
								String d[]=msg.substring(4, msg.length()-1).split(";");
								for (int i = 0; i < d.length; i++) {
									String[] res=d[i].split(",");
									DomEnvoyer.addDominoAll(Integer.parseInt(res[0].split(":")[0]), Integer.parseInt(res[0].split(":")[1]),Double.parseDouble(res[1]));
								}
							}else if (msg.substring(0, 4).equals("info")) {
								String[] joueur=msg.substring(5, msg.length()-1).split(";");
								System.out.println(msg.substring(5, msg.length()-1));
								for (int i=0; i<joueur.length;i++) {
									if(!joueur[i].split(",")[0].equals(nom)) {
										if(dataDomino.rechercher(joueur[i].split(",")[0])!=-1) {
											dataDomino.modifierJoueur(dataDomino.rechercher(joueur[i].split(",")[0]), joueur[i].split(",")[1]);
										}else {
											dataDomino.addJoueur(joueur[i].split(",")[0], 7);
										}
								}
								}
								m.actualiser();
								for (Joueur j :dataDomino.joueur) {
									System.out.println("info:"+j.getNom()+","+j.getNbr_dom());
								}
								//Main.joueur();
							}else if (msg.equals("resultat")) {
								if(dataDomino.domino.size()!=0) {
									resteDom();
								}else {
									out.println("reste:"+0+":"+nom+"\n"); 
									System.out.println("reste:"+0+":"+nom+"\n"); 
								}
								out.flush();
								
							}else if (msg.split(":")[0].equals("ter")) {
								m.confirmation(msg.split(":")[1]);
								m.setVisible(false);
								new Acceuil();
							}
							System.out.print("> ");
							msg=in.readLine();	
	
							
						}
	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
           
           receiver.start();
            
            //on ferme la connexion
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    	public static void envoi(String s) {
    		index=0;
    		int trouver=0;
           message = s;
           String[] msg=message.substring(0, 3).split(":");
           for (Domino dom : dataDomino.domino) {
			  if(dom.getPartie1()==Integer.parseInt(msg[0]) && dom.getPartie2()==Integer.parseInt(msg[1])) {
				System.out.println(dom.getPartie1()+" "+Integer.parseInt(msg[0])+"--------->"+dom.getPartie2()+" "+Integer.parseInt(msg[1]));
				trouver++;
				break;
			}
			index++;
           }
			if(trouver!=0) {
			out.println(message); 
			out.flush();
			}
		}
    	
    	public static void passer() {
			out.println("passer\n"); 
			out.flush();
		}
    	
    	public void envoiInfo() {
			out.println(nom+",7"+"\n"); 
			System.out.println("info:"+Info.ReadInfo()+"\n"); 
			out.flush();
		}
    	
    	public void resteDom() {
    		int sum=0;
			for (Domino d : dataDomino.domino) {
				sum+=d.getPartie1()+d.getPartie2();
			}
			out.println("reste:"+sum+":"+nom+"\n"); 
			System.out.println("reste:"+sum+":"+nom+"\n"); 
			out.flush();
		}
    	
    	public static void ouvrirMain() {
			m.ouvrir();
		}
    }
