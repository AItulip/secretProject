package client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class RunClient {
	final static int port = 6666;
	public static void main(String[] arg){
		System.out.println("Client Start");
		Scanner s = new Scanner(System.in);
		
		InetAddress hostip = null;
		try {
			hostip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
				
		ConnectionClient client = new ConnectionClient(hostip.getHostAddress(),port);
		ClientHandler ch = new ClientHandler();

		while(true){
			MakeRequest requestmaker = new MakeRequest();
			System.out.println("username");		
			String un = s.nextLine();
			System.out.println("password");		
			String ps = s.nextLine();	
			Datagram d = requestmaker.login(un, ps);
			client.senddatagram(d);
			
			
			ch.process(client.receivedatagram());
			System.out.println("sended press any key to contiune");	
			s.nextLine();	

		}
		
	}

}
