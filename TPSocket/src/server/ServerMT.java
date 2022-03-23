package server;
import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMT extends Thread{ 
	private int numeroClient;
	public static void main(String[] args) {
		new ServerMT().start(); /*si on cr�ent un objet de la classe ServerMT automatiquement on cr�ent un thread
		                         *start() pour d�marer le thread et c'est la m�thode run qui va s'ex�cuter
		                         */
		//je ne suis pas bloqu�
	    System.out.println("Suite de l'application ...");
	}
	@Override
	public void run() {
		try {
			ServerSocket ss=new ServerSocket(1235);
			System.out.println("D�marrage de serveur ...");
			while(true) {
				Socket s=ss.accept();
				++numeroClient;
				new Conversation(s,numeroClient).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	class Conversation extends Thread{
		private Socket socket;
		private int numeroClient;
		public Conversation(Socket s,int num) {
			socket=s;
			numeroClient=num;
		}
		@Override
		public void run() {
			try {
				InputStream is=socket.getInputStream();
				InputStreamReader isr=new InputStreamReader(is);
				BufferedReader br=new BufferedReader(isr);
				
				OutputStream os=socket.getOutputStream();
				PrintWriter pw=new PrintWriter(os,true);
				System.out.println("Connexion du client num�ro "+numeroClient+" @IP="+socket.getRemoteSocketAddress().toString());
				pw.println("Bien venue vous etes le client num�ro "+numeroClient);
				while(true) {
					String req=br.readLine();
					System.out.println("Le client "+socket.getRemoteSocketAddress().toString()+" a envoy� la requete "+req);
					pw.println(req.length());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}

}
