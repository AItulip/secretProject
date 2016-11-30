
package db;

import java.sql.*;
import java.util.ArrayList;

public class Database {

	private static Database single=null; 

	String url="jdbc:mysql://localhost:3306/cw"; 
	
	public static Database getInstance() {  
		if (single == null) {    
			single = new Database();  
		}    
		return single;  
	}  

	private Database() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL run");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}  


	public String register(String username, String password) {
		try {
			Connection conn = DriverManager.getConnection(url, "root", "Wangsaiu1");
			System.out.println("connect to dababase");
			
			PreparedStatement ps = conn.prepareStatement("select * from user where username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			String res;
			
			if (rs.next()){
				res = "the name has been used";
			}else{
				PreparedStatement ps2 = conn.prepareStatement("insert into user (username,password) values(?,?)");
				ps2.setString(1, username);
				ps2.setString(2, password);
				int count = ps2.executeUpdate();
				
				if (count>0) {
					res = "successful";
				}else{
					res = "invalid name";
				}
				ps2.close();
			}
			
			rs.close();
			ps.close();
			conn.close();
			System.out.println("close to dababase");
			
			
			System.out.println(res);
			
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public String[] login(String username, String password) {
		
		String[] res = {"",null};
		
		try {
			Connection conn = DriverManager.getConnection(url, "root", "Wangsaiu1");
			System.out.println("connect to dababase");
			
			PreparedStatement ps = conn.prepareStatement("select * from user where username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			
			
			if (rs.next()){
				
				if (rs.getString(3).equals(password)){
					res[0] = "successful";
					res[1] = rs.getString(4); 
				}else{
					res[0] = "wrong password";
				}
			}else{
				res[0] = "not exist";
			}
			
			rs.close();
			ps.close();
			conn.close();
			System.out.println("close to dababase");
			
			
			System.out.println(res.toString());
			
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public String editNickname(String username,String nickname) {
		try {
			Connection conn = DriverManager.getConnection(url, "root", "Wangsaiu1");
			System.out.println("connect to dababase");
			
			PreparedStatement ps = conn.prepareStatement("update user set nickname ="+nickname+"where username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			String res;
			
			int count = ps.executeUpdate();
			
			if (count>0) {
				res = "successful";
			}else{
				res = "invalid name";
			}
			
			rs.close();
			ps.close();
			conn.close();
			System.out.println("close to dababase");
			
			
			System.out.println(res);
			
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	

	public ArrayList<String> allUsers() {
		try {
			
			ArrayList<String> res = new ArrayList<String>();
			
			Connection conn = DriverManager.getConnection(url, "root", "Wangsaiu1");
			System.out.println("connect to dababase");
			
			PreparedStatement ps = conn.prepareStatement("select * from user");
			ResultSet rs = ps.executeQuery();
		
			
			
			while (rs.next()) {
				res.add(rs.getString(2));
			}
			
			rs.close();
			ps.close();
			conn.close();
			System.out.println("close to dababase");
			
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

    

}
