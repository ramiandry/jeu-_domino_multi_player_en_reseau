package Serveur;

import java.net.*;
import java.util.*;
import java.io.*;
import java.nio.*;

public interface IPAdress {
 public static String  getInterfaces (){
	 String ipAd = " 127.0.0.1";
      try {
         Enumeration e = NetworkInterface.getNetworkInterfaces();

         while(e.hasMoreElements()) {
            NetworkInterface ni = (NetworkInterface) e.nextElement();
           // System.out.println("Net interface: "+ni.getName());

            Enumeration e2 = ni.getInetAddresses();

            while (e2.hasMoreElements()){
               InetAddress ip = (InetAddress) e2.nextElement();
               if(ip.toString().substring(0, 4).equals("/192")) {
            	   ipAd=ip.toString();
               }
            }
         }
        return ipAd.substring(1);
      }
      catch (Exception e) {
         e.printStackTrace();
      }
	return ipAd;
   }

/*   public static void main(String[] args) {
    IPAdress ip = new IPAdress();
    ip.getInterfaces();
   }*/
}