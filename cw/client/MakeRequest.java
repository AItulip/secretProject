import java.io.FileInputStream;
import java.util.HashMap;

public class MakeRequest extends AllRequest {
	
	@Override
	public Datagram login(String username, String password) {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("username", username);
		data.put("password", password);
		Datagram res = new Datagram("requestlogin",data);
			
		return res;
	}

	@Override
	public Datagram register(String username, String password) {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("username", username);
		data.put("password", password);
		Datagram res = new Datagram("requestregister",data);
		return res;
	}

	@Override
	public Datagram getallfriends() {
		HashMap<String, String> data = new HashMap<String, String>();
		Datagram res = new Datagram("requestgetallfriend",data);	

		return res;
	}

	@Override
	public Datagram addfriend(String friendname) {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("friendname", friendname);
		Datagram res = new Datagram("requestaddfriend",data);	


		return res;
		
	}

	@Override
	public Datagram removefriend(String friendname) {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("friendname", friendname);
		Datagram res = new Datagram("requestremovefriend",data);	


		return res;
	}

	@Override
	public Datagram getallgroup() {
		HashMap<String, String> data = new HashMap<String, String>();
		Datagram res = new Datagram("requestgetallgroup",data);	


		return res;
	}

	@Override
	public Datagram addgroup(String groupname) {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("groupname", groupname);
		Datagram res = new Datagram("requestaddgroup",data);	


		return res;
	}

	@Override
	public Datagram removgroup(String groupname) {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("groupname", groupname);
		Datagram res = new Datagram("requestremovgroup",data);	


		return res;
	}
	
	
	@Override	
	public Datagram getgroupmember(String groupname){
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("groupname", groupname);
		Datagram res = new Datagram("requestgetgroupmember",data);	


		return res;
		
	}
	

	@Override
	public Datagram sendfriendmessage(String dst, String mesg) {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("dst", dst);
		data.put("mesg", mesg);
		Datagram res = new Datagram("requestsendfriendmessagep",data);	


		return res;
	}

	@Override
	public Datagram sendgroupmessage(String dst, String mesg) {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("dst", dst);
		data.put("mesg", mesg);
		Datagram res = new Datagram("requestsendgroupmessage",data);	


		return res;
	}
	
	


	@Override
	public Datagram sendfriendfile(String username, FileInputStream fin) {
//		HashMap<String, String> data = new HashMap<String, String>();
//		data.put("groupname", groupname);
//		Datagram res = new Datagram("requestaddgroup",data);	
//		ConnectionClient.senddatagram(res);
//		Datagram dg = ConnectionClient.receivedatagram();
//
//		return dg;
		return null;
	}


}
