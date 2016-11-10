package server;

import java.util.HashMap;

public class ServerHandler {

	private MakeResponse responsemaker = new MakeResponse();
	
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
		case "requestlogin":  out = handlelogin(data); break;
		case "requestregister": handleregister(data);break;
		case "requestgetallfriend": ;
		case "requestaddfriend": ;
		case "requestremovefriend": ;
		case "requestaddgroup": ;
		case "requestremovgroup": ;	
		case "requestgetgroupmember": ;	
		case "requestgetallgroup": ;
		case "requestsendfriendmessagep": ;	
		case "requestsendgroupmessage": ;
		
		}
		return out;				
	}
	
	
	// write all request handler
	// saisai wang's job
	
	
	public  Datagram handlelogin(Datagram indata){
//		System.out.println(indata.getdatagram().size());
		
		HashMap<String, String> data = getdata(indata);
		
		Datagram out = null;
		if(data.get("username").equals("sfz")){
			if(data.get("password").equals("123")){
			out	= responsemaker.login("SUCCESS");
			System.out.println("sfz login");
			}
			else{
				out	= responsemaker.login("wrong");
			} 
		
		}else{
			out	= responsemaker.login("unknow");
		}
		// return the result
		return out;

				
	}
	
	
	public  Datagram handleregister(Datagram data){

		return null;
		
	}
}
