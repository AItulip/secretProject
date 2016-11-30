package server;

import model.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Vector;


public class ConnectionServer {
	private  Socket socket = null;
	private  ObjectInputStream objectInput = null; 
	private  ObjectOutputStream objectOutput = null;
	private  ServerSocket myServerSocket = null;
	private  RunServer server = null;
	
	
	Vector<ClientSocket> onlineUsers = new Vector<ClientSocket>();
	
	public ConnectionServer(int port,RunServer server){
		
		this.server = server;
		
		setSocket( port);
		
		
//		openobjectio();
	}
	
	public void setSocket( int port){
		try {
			myServerSocket = new ServerSocket(port);
			
			while (true){
				
				socket = myServerSocket.accept();
				server.setText("Server:find new connection");
				showConnectivity(socket);
				 
				new ClientSocket(socket, onlineUsers, server);
			}
			
		}catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	
	public void close(){
		closeobjectio();
		closesocket();
	}
	
	
	
	
	public  void showConnectivity(Socket socket){
		if (socket!=null){
			server.setText("local ip:" + socket.getLocalAddress());
			server.setText("local port:" + socket.getLocalPort());
			server.setText("server ip:" + socket.getInetAddress());
			server.setText("server port:" + socket.getPort());
		}
		else{
			server.setText("error");
		}
	}	
	public  Socket getsocket(){
		return socket;
	}
	
	public  void senddatagram(Datagram data){
		try {

			objectOutput.writeObject(data);
			objectOutput.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return;
	}
	public  Datagram receivedatagram (){
		Datagram tmp = null;
		try {
			try {
				
				while ((tmp = (Datagram)objectInput.readObject()) != null) {
					Object object =(Datagram) objectInput.readObject();
	                tmp = (Datagram) object;
				}
				
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
		} catch (IOException e) {
			
			server.setText("Receive Failure");
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return tmp;
		
	}
	
	public  void closesocket(){
		try {
			socket.close();
		} catch (IOException e) {
			server.setText("Closesocket Failure");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void closeobjectio(){
		if(socket != null){
			try {
				objectInput.close();;
			} catch (IOException e) {
				server.setText("objectInput Failure");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				objectOutput.close();
			} catch (IOException e) {
				server.setText("objectOutput Failure");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			server.setText("Pleas open socket first");
		}
		
		
	}
	public  boolean openobjectio(){
		boolean res = false;
		if(socket != null){
			
			try {
				objectOutput = new ObjectOutputStream(socket.getOutputStream());
				
			} catch (IOException e) {
				server.setText("cannot open ObjectOutputStream");
				e.printStackTrace();
			}
			
			
			try {
				objectInput = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				server.setText("cannot open ObjectInputStream");
				//e.printStackTrace();
			}
			res = true;
		}else{
			server.setText("Pleas open socket first");
		}
		return res;
	}
	
}
