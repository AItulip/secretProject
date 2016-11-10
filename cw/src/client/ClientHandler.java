package client;

import java.util.HashMap;

public class ClientHandler {
	private MakeRequest resuqestemaker = new MakeRequest();
	
	
	public  HashMap<String, String> getinformation(Datagram data){
		return data.getdatagram().get("information");
		
	}
	
	public  HashMap<String, String> getdata(Datagram data){
		return data.getdatagram().get("data");
		
	}
	public  String getname(Datagram data){
		return getinformation(data).get("name");
		
	}
	
	
	
	
	public Datagram process(Datagram data){
		Datagram out = null;
		
		switch (getname(data)){
		case "replylogin":  out = handlelogin(data); break;
		case "replyregister": ;break;
		case "replygetallfriend": ;
		case "replyaddfriend": ;
		case "replyremovefriend": ;
		case "replyaddgroup": ;
		case "replyremovgroup": ;	
		case "replygetgroupmember": ;	
		case "replygetallgroup": ;
		case "replysendfriendmessagep": ;	
		case "replysendgroupmessage": ;
		case "friendmessagerelay": ;
		case "groupmessagerelay": ;
		case "friendfilerelay":;
		
		
		
		}
		return out;				
	}
	
	//write all method to show the input information
	//zhongxiao chen's job
	
	
	
	
	
	public  Datagram handlelogin(Datagram indata){
//		System.out.println(indata.getdatagram().size());
		System.out.println(getdata(indata).get("result"));

//	just show information and reply nothing
		return null;
	}
	
	
	public  Datagram handleregister(Datagram data){

		return null;
		
	}
	


}
