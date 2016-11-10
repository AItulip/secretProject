import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class RunServer {
	final static int port = 6666;
	public static void main(String[] arg){
		System.out.println("Server Start");
		Scanner s = new Scanner(System.in);

		
//		ServerListener server = new ServerListener();
		ConnectionServer server = new ConnectionServer(port);
		ServerHandler sh = new ServerHandler();
		
		while(true){	
			
			Datagram d = server.receivedatagram();
			Datagram o = sh.process(d);
			server.senddatagram(o);
		}

	}


}
