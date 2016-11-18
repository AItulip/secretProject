package client;

import java.awt.BorderLayout;
import java.awt.Font;

import model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RunClient2 {



	private JFrame frame;
	/** 边界布局的主面板 */
	private JPanel panelMain;
	private JPanel panelDown;
	private JTextArea ta;
	private JTextField txt;
	private JButton but;
	private JScrollPane jsp;
	private Font font;

	final static int port = 6666;
	String ip = "127.0.0.1";

	private ClientHandler ch = null;

	private Socket socket = null;
	private ObjectInputStream objectInput = null; 
	private ObjectOutputStream objectOutput = null;


	public static void main(String[] arg){
		new RunClient2();
	}

	public RunClient2() {
		// TODO Auto-generated constructor stub

		startGUI();
		connectServer();

	}


	private void startGUI() {
		
		Date date=new Date();
	    Format format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		frame = new JFrame("chat with andres");
		panelMain = new JPanel(new BorderLayout());
		panelDown = new JPanel();
		ta = new JTextArea();
		txt = new JTextField(20);
		but = new JButton("发送");
		jsp = new JScrollPane(ta);
		// 粘贴界面
		panelDown.add(txt);
		panelDown.add(but);
		panelMain.add(jsp, BorderLayout.CENTER);
		panelMain.add(panelDown, BorderLayout.SOUTH);
		// 字体
		font = new Font("宋体", Font.BOLD, 18);
		txt.setFont(font);
		ta.setFont(font);
		but.setFont(font);
		// 文本域只读
		ta.setEditable(false);
		//按钮添加监听

		but.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub


				MakeRequest requestmaker = new MakeRequest();
				Datagram d = requestmaker.sendfriendmessage("andres", txt.getText());
				senddatagram(d);
				
				setText("wang  "+format.format(date));
				setText(txt.getText());
				
				//				ch.process(client.receivedatagram());

			}
		});

		frame.add(panelMain);
		frame.setBounds(500, 200, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭窗体时结束程序
		frame.setAlwaysOnTop(true);// 永远在所有窗体最上
		frame.setVisible(true);
		// 光标给消息文本框
		txt.requestFocus();
		
		
	}


	public void connectServer() {
		setText("Client Start");

		setSocket(ip, port);

		MakeRequest requestmaker = new MakeRequest();
		Datagram d;
		d = requestmaker.login("wang","sai");
		senddatagram(d);

		receivedatagram();
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

			setText("Receive Failure");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmp;
	}


	public boolean setSocket(String ipAddress, int port){
		try {
			socket = new Socket(ipAddress, port);						
			showConnectivity( socket);
			ch = new ClientHandler(ta);	


			try {
				objectOutput = new ObjectOutputStream(socket.getOutputStream());
				objectInput = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				setText("client: "+port+" connect fail");
				e.printStackTrace();
			}


			return true;
		}catch (IOException ex){
			ta.append("Cannot connect the server \n");
			return false;
		}
	}

	public void showConnectivity(Socket socket){
		if (socket!=null){
			setText("local ip:" + socket.getLocalAddress());
			setText("local port:" + socket.getLocalPort());
			setText("server ip:" + socket.getInetAddress());
			setText("server port:" + socket.getPort());
		}
		else{
			setText("error");
		}
	}	

	public  void senddatagram(Datagram data){
		try {
			System.out.println("send a datagram");
			objectOutput.writeObject(data);
			objectOutput.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}



	public void setText(String msg) {
		ta.append(msg+"\n");
	}

}
