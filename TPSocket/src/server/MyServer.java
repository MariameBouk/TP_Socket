package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

	public static void main(String[] args) throws IOException {
		//cr�er objet ServerSocket
		ServerSocket ss=new ServerSocket(1234);
        System.out.println("J'attend la connexion...");
        //la m�thode accept va retourner une socket si un client est conn�cter
        Socket s=ss.accept();
        //afficher l'@ip de client
        System.out.println("Connexion d'un client "+s.getRemoteSocketAddress());
        InputStream is=s.getInputStream();
        OutputStream os=s.getOutputStream();
        System.out.println("J'attend que le client envoie un octet");
        int nb=is.read(); //lire un octet
        System.out.println("J'ai re�u un nombre "+nb);
        int rep=nb*5;
        System.out.println("J'envoie la r�ponse "+rep);
        os.write(rep);
        //fermer la connexion
        s.close();        
	}
}
