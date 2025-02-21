package Serveur;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Serveur{
    final int port = 9636;
    static int nbr_joueur=0;
    static Socket socketJoueur;
    static int accepter=0;
    static int d=0;
    static int g=0;
    static int temp=300;
    Score s=new Score();
    Partie p=new Partie();
    private Socket socket=null; //pour stocker la connexion avec le client
    Scanner sc = new Scanner(System.in);
    static Socket socketClient;
    static ServerSocket socketServeur = null; //pour garder le serveur
    static int passer=0;
    PrintStream out;
    BufferedReader in;
    static int res=0;
    static int envRes=0;
    static String gagnant;
    public Serveur(Socket client) {
    	//Acceuil a=new Acceuil();
        socket = client;
    }
    User u=new User();

    public Serveur(int max_joueur) {
    	Partage.initialisation();
        try {
            socketServeur = new ServerSocket(port);
            System.out.println("Lancement du serveur");
            while(nbr_joueur<max_joueur) {
           
					try {
					socketClient = socketServeur.accept();
					Serveur t = new Serveur(socketClient);
					SocketClient.socketClient.add(socketClient);
		            if (nbr_joueur==0) {
		            	socketJoueur=socketClient;
					}
		            t.traitements();
		            nbr_joueur++;
		       		System.out.println(nbr_joueur);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void traitements() {
    	
        try {
            //on prent les info du client connecté
            System.out.println("Connexion avec le client : " + socket.getInetAddress());
            System.out.print("Server: ");
            // on crée les input et output pour cette connexion
            User user=new User();
	        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        String msg=in.readLine();
	        String[] info=msg.split(",");
			System.out.println(msg);
			SocketClient.client.add(new Client(socket, info[0], Integer.parseInt(info[1])));
			if(user.searchUser(info[0])==-1) {
				user.setPseudo(info[0]);
				user.addUser();
			}
			SocketClient.afficherValeur();
	        partage(nbr_joueur);
	        info();
	      
           Thread receiver=new Thread(new Runnable() {
				public void run() {
					String msg;
					try {
						msg=in.readLine();
						
						while (msg!=null) {
							System.out.println("-->"+msg);
								if (msg.equals("passer")) {
									tour();
									info();
									passer++;
									System.out.println("passer " +passer+"  " +nbr_joueur);
									if(passer==nbr_joueur) {										
										demandeRes();
									}
								}else if (msg.split(":")[0].equals("reste")) {
									envRes++;
									System.out.print(">"+temp+"---->"+Integer.parseInt(msg.split(":")[1]));
									if(temp>Integer.parseInt(msg.split(":")[1])) {
										temp=Integer.parseInt(msg.split(":")[1]);
												gagnant=msg.split(":")[2];
										}
									
									s.setScore(0);
									s.setId_user(user.searchUser(msg.split(":")[2]));
									s.setId_partie(p.lastPartie());
									s.addScore();
									res+=Integer.parseInt(msg.split(":")[1]);
									System.out.print(">"+envRes+"-->"+nbr_joueur);
									if(envRes==nbr_joueur) {
										System.out.println("id_user"+user.searchUser(msg.split(":")[2]));
										s.setScore(res);
										s.updateScore(u.searchUser(gagnant), s.searchId(u.searchUser(gagnant)));
										terminer();
									}
								}else if(msg.length()==5) {
									accepter=0;
									String[] dom=msg.substring(0,3).split(":");
									if(Domclient.domclient.size()==0) {
										Domclient.addDomclient(new Domino(Integer.parseInt(dom[0]),Integer.parseInt(dom[1]),0.0),msg.substring(4));
										accepter++;
										g=Integer.parseInt(dom[0]);
										d=Integer.parseInt(dom[1]);
										//actualisation();
									}else if(msg.substring(4).equals("d") ){
										if(d==Integer.parseInt(dom[0])) {
											d=Integer.parseInt(dom[1]);
											Domclient.addDomclient(new Domino(Integer.parseInt(dom[0]),Integer.parseInt(dom[1]),0.0),msg.substring(4));
											accepter++;
										}else if(d==Integer.parseInt(dom[1])) {
											d=Integer.parseInt(dom[0]);
											Domclient.addDomclient(new Domino(Integer.parseInt(dom[0]),Integer.parseInt(dom[1]),180.0),msg.substring(4));
											accepter++;
											//actualisation();
										}else {
											refuser();
										}
									}else if ( msg.substring(4).equals("g")){
										if(g==Integer.parseInt(dom[0])) {
											g=Integer.parseInt(dom[1]);
											Domclient.addDomclient(new Domino(Integer.parseInt(dom[0]),Integer.parseInt(dom[1]),180.0),msg.substring(4));
											accepter++;
											//actualisation();
										}else if(g==Integer.parseInt(dom[1])) {
											g=Integer.parseInt(dom[0]);
											Domclient.addDomclient(new Domino(Integer.parseInt(dom[0]),Integer.parseInt(dom[1]),0.0),msg.substring(4));
											accepter++;
											//actualisation();
										}else {
											refuser();
										}
									} 
									
									if(accepter!=0) {
										accepter();
									}
						}
								
							msg=in.readLine();
						}
	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}


			});
           
            receiver.start();

            //on attend une reponse
           
            //on ferme la connexion
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public void accepter() {
    	
    	 try {
    		passer=0;
			out = new PrintStream(socketJoueur.getOutputStream());
			System.out.println(SocketClient.rechercherSocket(socketJoueur));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 SocketClient.modifierClient(socketJoueur);
    	out.print("true\n");
		out.flush();
		listeDomino();
		tour();
		info();
	}
    
    public void refuser() {
    	 try {
			out = new PrintStream(socketJoueur.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	out.print("false\n");
		out.flush();
	}
    
    public void listeDomino() {
    	for (Socket s : SocketClient.socketClient) {
    		try {
				out= new PrintStream(s.getOutputStream());
	    		String data="";
	        	for (Domino d : Domclient.domclient) {
	    			data+=d.getDomino()+";";
	    		}
	        	out.print("all:"+data+"\n");
	        	out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
	}
    	
	
    
    public void partage(int nbr) throws IOException {

			String message = "";
			out=new PrintStream(SocketClient.socketClient.get(nbr_joueur).getOutputStream());
	        for (int j = 0; j <7 ; j++) {
				int index= (int) (Math.random()*Partage.domino.size());
				message+=Partage.domino.get(index).getDomino()+";";
				Partage.domino.remove(index);
			}
	        System.out.println("partage");
	        if(nbr_joueur==0) {
	        	out.print("dom:"+message+"tour\n");
	        }else {
	        	out.print("dom:"+message+"\n");
			}
			
			out.flush();
    }
    
   public void tour() {
	   int index=SocketClient.rechercherSocket(socketJoueur);
	   
	   
	   for (int i = 0; i <SocketClient.socketClient.size(); i++) {
		   System.out.println(SocketClient.socketClient.get(i).getRemoteSocketAddress().toString()+" "+socketJoueur.toString()+"---->"+i);
		  
			if(i==index) {
				try {
					out= new PrintStream(SocketClient.socketClient.get(i).getOutputStream());
					out.print("tour\n");
					in = new BufferedReader(new InputStreamReader(SocketClient.socketClient.get(i).getInputStream()));
					socketJoueur=SocketClient.socketClient.get(i);
					out.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else {
				try {
					out= new PrintStream(SocketClient.socketClient.get(i).getOutputStream());
					out.print("pas tour\n");
					out.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
   }
   
   public void info() {
	   for (Socket s : SocketClient.socketClient) {
   		try {
				out= new PrintStream(s.getOutputStream());
	    		String data="";
	        	for (Client c : SocketClient.client) {
	    			data+=c.getNom()+","+c.getNbr_dom()+";";
	    		}
	        	out.print("info:"+data+"\n");
	        	out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   	}
	   verificationPartieTerminer();
   }
   
   public void demandeRes() {
	   for (Socket s : SocketClient.socketClient) {
	   		try {
					out= new PrintStream(s.getOutputStream());
		        	out.print("resultat\n");
		        	out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   	}
   }
   
   public void terminer() {
	   for (Socket s : SocketClient.socketClient) {
	   		try {
					out= new PrintStream(s.getOutputStream());
		        	out.print("ter:Le jeu est terminé."+gagnant+" gagne "+res+"pts\n");
		        	out.flush();
		        	p.updatePartie(u.searchUser(gagnant), p.lastPartie());
		        	nbr_joueur=0;
		        	SocketClient.client.clear();
		        	Partage.domino.clear();
		        	Partage.initialisation();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   	}
   }
   
   public void verificationPartieTerminer() {
	   for (Client c : SocketClient.client) {
		if(c.nbr_dom==0) {
			gagnant=c.getNom();
			System.out.println("Verification gagnant!!");
			demandeRes();
		}
	   }
   }
}