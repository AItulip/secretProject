package client;

import java.io.FileInputStream;

public abstract class AllRequest {
		
	public abstract Datagram login(String username, String password);
	public abstract Datagram register(String username, String password);
	
	public abstract Datagram getallfriends();
	public abstract Datagram addfriend(String friendname);
	public abstract Datagram removefriend(String friendname);
	
	public abstract Datagram getallgroup();
	public abstract Datagram getgroupmember(String groupname);	
	public abstract Datagram addgroup(String groupname);
	public abstract Datagram removgroup(String groupname);
	
	public abstract Datagram sendfriendmessage(String dst, String mesg);	
	public abstract Datagram sendgroupmessage(String dst, String mesg);	
	public abstract Datagram sendfriendfile(String username, FileInputStream fin);
	
	
}
