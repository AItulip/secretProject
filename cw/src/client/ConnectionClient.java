package client;

import model.*;
import java.io.*;
import java.net.*;

import javax.swing.JTextArea;
public class ConnectionClient {
	private Socket socket = null;
	private ObjectInputStream objectInput = null; 
	private ObjectOutputStream objectOutput = null;
	private JTextArea ta;
	private ClientHandler ch;


	public ConnectionClient(String ipAddress, int port,JTextArea ta,int a){

		this.ta = ta;

		setSocket(ipAddress, port);
		oepnobjectio();

		MakeRequest requestmaker = new MakeRequest();

		Datagram d;
		if (a==2){
			d = requestmaker.login("wang","sai");
		}else{
			d = requestmaker.login("andres","iniesta");
		}

		senddatagram(d);

		receivedatagram();


	}
	
	public void close(){
		closeobjectio();
		closesocket();
	}



	public boolean setSocket(String ipAddress, int port){
		try {
			socket = new Socket(ipAddress, port);						
			showConnectivity( socket);
			ch = new ClientHandler(ta);	

			return true;
		}catch (IOException ex){
			ta.append("Cannot connect the server \n");
			return false;
		}
	}	


	public void showConnectivity(Socket socket){
		if (socket!=null){
			ta.append("local ip:" + socket.getLocalAddress() + "\n");
			ta.append("local port:" + socket.getLocalPort()+ "\n");
			ta.append("server ip:" + socket.getInetAddress()+ "\n");
			ta.append("server port:" + socket.getPort()+ "\n");
		}
		else{
			ta.append("error"+ "\n");
		}
	}	
	public Socket getsocket(){
		return socket;
	}

	public  void senddatagram(Datagram data){
		try {
			ta.append("send a datagram"+ "\n");
			objectOutput.writeObject(data);
			objectOutput.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return;
	}
	public Datagram receivedatagram (){
		Datagram tmp = null;
		try {

			try {

				while ((tmp = (Datagram)objectInput.readObject()) != null) {
					System.out.println("clientReadObject");
					ch.process(tmp);
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {

			ta.append("Receive Failure"+ "\n");
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return tmp;

	}

	public void closesocket(){
		try {
			socket.close();
		} catch (IOException e) {
			ta.append("Closesocket Failure"+ "\n");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void closeobjectio(){
		if(socket != null){
			try {
				objectInput.close();;
			} catch (IOException e) {
				ta.append("objectInput Failure"+ "\n");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				objectOutput.close();
			} catch (IOException e) {
				ta.append("objectOutput Failure"+ "\n");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			ta.append("Pleas open socket first"+ "\n");
		}


	}
	public  boolean oepnobjectio(){
		boolean res = false;
		if(socket != null){

			try {
				objectOutput = new ObjectOutputStream(socket.getOutputStream());

			} catch (IOException e) {
				ta.append("cannot open ObjectOutputStream"+ "\n");
				e.printStackTrace();
			}


			try {
				objectInput = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				ta.append("cannot open ObjectInputStream"+ "\n");
				//e.printStackTrace();
			}
			res = true;
		}else{
			ta.append("Pleas open socket first"+ "\n");
		}
		return res;
	}


}
