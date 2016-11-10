import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
public class ConnectionServer {
	private  Socket socket = null;
	private  ObjectInputStream objectInput = null; 
	private  ObjectOutputStream objectOutput = null;
	private  ServerSocket myServerSocket = null;
	
	
	public ConnectionServer(int port){
		setSocket( port);
		oepnobjectio();
		
	}
	
	public void close(){
		closeobjectio();
		closesocket();
	
	}
	
	
	public boolean setSocket( int port){
		try {
			myServerSocket = new ServerSocket(port);
			socket = myServerSocket.accept();
			System.out.println("Server:find connection");
			return true;
		}catch (IOException ex){
			return false;
		}
	}
	
	public  void showConnectivity(Socket socket){
		if (socket!=null){
			System.out.println("local ip:" + socket.getLocalAddress());
			System.out.println("local port:" + socket.getLocalPort());
			System.out.println("server ip:" + socket.getInetAddress());
			System.out.println("server port:" + socket.getPort());
		}
		else{
			System.out.println("error");
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
                Object object =(Datagram) objectInput.readObject();
                tmp = (Datagram) object;           

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
		} catch (IOException e) {
			
			System.out.println("Receive Failure");
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return tmp;
		
	}
	
	public  void closesocket(){
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("Closesocket Failure");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void closeobjectio(){
		if(socket != null){
			try {
				objectInput.close();;
			} catch (IOException e) {
				System.out.println("objectInput Failure");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				objectOutput.close();
			} catch (IOException e) {
				System.out.println("objectOutput Failure");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("Pleas open socket first");
		}
		
		
	}
	public  boolean oepnobjectio(){
		boolean res = false;
		if(socket != null){
			
			try {
				objectOutput = new ObjectOutputStream(socket.getOutputStream());
				
			} catch (IOException e) {
				System.out.println("cannot open ObjectOutputStream");
				e.printStackTrace();
			}
			
			
			try {
				objectInput = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				System.out.println("cannot open ObjectInputStream");
				//e.printStackTrace();
			}
			res = true;
		}else{
			System.out.println("Pleas open socket first");
		}
		return res;
	}
	
}
