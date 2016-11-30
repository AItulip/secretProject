package client;

import java.util.HashMap;

import javax.swing.JTextArea;

import model.*;

public class ClientHandler {
	private MakeRequest resuqestemaker = new MakeRequest();
	private JTextArea ta;
	
	
	public  HashMap<String, String> getinformation(Datagram data){
		return data.getdatagram().get("information");
		
	}
	
	public  HashMap<String, String> getdata(Datagram data){
		return data.getdatagram().get("data");
		
	}
	public  String getname(Datagram data){
		return getinformation(data).get("name");
		
	}
	
	public ClientHandler(JTextArea ta) {
		// TODO Auto-generated constructor stub
		this.ta = ta;
	}
	
	
	public Datagram process(Datagram data){
		Datagram out = null;
		
		switch (getname(data)){
		case "replylogin":  out = handlelogin(data); break;
		case "replyregister": out = handleregister(data);break;
		case "replygetallfriend": ;
		case "replyaddfriend": ;
		case "replyremovefriend": ;
		case "replyaddgroup": ;
		case "replyremovgroup": ;	
		case "replygetgroupmember": ;	
		case "replygetallgroup": ;
		case "replysendfriendmessagep": ;	
		case "replysendgroupmessage": ;
		case "friendmessagerelay": out = handleReceiveFriendMessage(data);break;
		case "groupmessagerelay": ;
		case "friendfilerelay":;
		
		
		
		}
		return out;				
	}
	
	//write all method to show the input information
	//zhongxiao chen's job
	
	
	
	
	
	public  Datagram handlelogin(Datagram indata){
//		System.out.println(indata.getdatagram().size());
		System.out.println(getdata(indata).get("result")+ "\n");

//	just show information and reply nothing
		return null;
	}
	
	
	public  Datagram handleregister(Datagram indata){

		System.out.println(getdata(indata).get("result")+"\n");
		
		return null;
	}
	
	public Datagram handleReceiveFriendMessage(Datagram indata) {
		
		System.out.println("revice");
		
		ta.append(getdata(indata).get("rname")+"   "+ getdata(indata).get("rtime") + "\n" );
		ta.append(getdata(indata).get("msg")+"\n");
		
		
		return null;
	}
	


}
