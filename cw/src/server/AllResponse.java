package server;

import java.io.FileInputStream;
import java.util.HashMap;

public abstract class AllResponse {
	//reply login mesg: success, password incorrect, username not found, unknown error
	public abstract Datagram login(String mesg);
	
	//reply register mesg: success, username existing, unknown error
	public abstract Datagram register(String mesg);
	
	
	//datahashmap: friend_1, friendname
	public abstract Datagram getallfriends(HashMap<String, String> data);
	
	//reply addfriend mesg : success, friend not found, unknown error
	public abstract Datagram addfriend(String mesg);
	
	//reply addfriend mesg: success, failure, unknown error
	public abstract Datagram removefriend(String mesg);
	

	//datahashmap: group_1, groupname	
	public abstract Datagram getallgroup(HashMap<String, String> data);
	

	//datahashmap:groupmember_1, groupmembername	
	public abstract Datagram getgroupmember(HashMap<String, String> data);	
	
	//reply addgroup : success, group not found, unknown error
	public abstract Datagram addgroup(String mesg);
	
	//reply removgroup mesg: success, unknown error
	public abstract Datagram removegroup(String mesg);
	
	//reply sendfriendmessage mesg: success, friend not online, unknown error
	public abstract Datagram sendfriendmessage(String mesg);	
	
	//datahashmap:groupmember_1, groupmembername	
	public abstract Datagram sendgroupmessage(String mesg);	
	
	//reply sendfriendfile mesg: success, unknown error	
	public abstract Datagram sendfriendfile(String mesg);
	
	
	
	
	
	
	//friendmessagerelay 
	//data: from previous
	public abstract Datagram friendmessagerelay(HashMap<String, String> data);
	
	//groupmessagerelay
	//data: from previous
	public abstract Datagram groupmessagerelay(HashMap<String, String> data);
	
	//friendfilerelay
	//data: from previous
	public abstract Datagram friendfilerelay(HashMap<String, String> data);	
	

}
