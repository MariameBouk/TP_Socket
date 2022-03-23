package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MyClientForMT {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Je me connecte au serveur");
		//pour se connecter il suffit de cr�er un objet socket
		Socket s=new Socket("localhost",1235);
		InputStream is=s.getInputStream();
		InputStreamReader isr=new InputStreamReader(is);
		BufferedReader br=new BufferedReader(isr);
		
		OutputStream os=s.getOutputStream();
		PrintWriter pw=new PrintWriter(os,true);
		
        Scanner scanner=new Scanner(System.in);
        System.out.print("Donner une chaine de caract�res : ");
        String cc=scanner.next();
        System.out.println("J'envoie la chaine de caract�res "+cc+" au serveur");
        pw.println(cc);
        System.out.println("J'attend la r�ponse du serveur...");
        int rep=br.read();
        System.out.println("La r�ponse de serveur est "+rep);
	}
}
