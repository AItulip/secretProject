package gw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

/**
 * 服务员,线程类 
 * 在客户端连接后创建启动
 * 负责当前客户端的所有数据收发
 * 并且在业务需要时,将结果与服务器(老板)进行报告
 */
public class Waiter extends Thread{
	private Socket sc;
	private Vector<Waiter> dengJiBiao ;
	private MutilServer server;
	/**
	 * 客户端IP
	 */
	private String ip;
	private BufferedReader br;
	private BufferedWriter bw;
	/** 创建一个的新的服务员,负责当前传递的客户端连接(交易)
	 * 启动新线程
	 * @param sc	负责的交易
	 * @param dengJiBiao所有正在工作的服务员(所有交易)
	 * @param server	老板,也就是服务器
	 */
	public Waiter(Socket sc, Vector<Waiter> dengJiBiao,
			MutilServer server) {
		this.sc = sc;
		this.dengJiBiao = dengJiBiao;
		this.server = server;
		//初始化连接的准备工作
		ip = sc.getInetAddress().getHostAddress();
		// 得到输入和输出
		try {
			br = new BufferedReader(new InputStreamReader(
					sc.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(
					sc.getOutputStream()));
		} catch (IOException e) {
			this.server.showTxt("与客户端:"+ip+"建立通讯失败");
			e.printStackTrace();
			return;//无效客户,不再继续
		}
		this.server.showTxt("客户端:"+ip+"连接服务器成功");
		//启动线程
		this.start();
	}
	//线程
	public void run(){
		//开始时,登记开工
		dengJiBiao.addElement(this);
		System.out.println(this.getClass().getName());
		try {
			String s = null;
			while ((s = br.readLine()) != null) {
				server.showTxt("客户"+ip+"说:" + s);
				//当前客户发来的信息,其它客户也要能看得见.需要实现转发
				//从登记表找到所有正在干活的服务员
				for (int i = 0; i < dengJiBiao.size(); i++) {
					Waiter w = dengJiBiao.get(i);
					//排除掉当前服务员自己
					if(w!=this)
						w.send("客户"+ip+"说:" + s);
				}
			}
		} catch (Exception e) {
			server.showTxt("客户"+ip+"已经离开");
		}
		//结束时,登记下班
		dengJiBiao.removeElement(this);
	}

	/**	发送信息给当前服务员负责的客户端
	 * @param msg
	 */
	public void send(String msg){
		try {
			bw.write(msg+"\n");
			bw.flush();
		} catch (Exception e) {
			server.showTxt("给客户:"+ip+"发送信息"+msg+"失败");
		}
	}
}