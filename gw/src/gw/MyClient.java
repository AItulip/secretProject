package gw;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/** 客户端双工 */
public class MyClient implements ActionListener{
	private JFrame frame;
	/** 边界布局的主面板 */
	private JPanel panelMain;
	private JPanel panelDown;
	private JTextArea ta;
	private JTextField txt;
	private JButton but;
	private JScrollPane jsp;
	private Font font;
	/**
	 * 服务器IP
	 */
	private String ip = "127.0.0.1";
	/**
	 * 服务器端口
	 */
	private int port = 6666;
	private BufferedReader br;
	private BufferedWriter bw;
	public MyClient() {
		frame = new JFrame("双工客户端1");
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
		but.addActionListener(this);
		frame.add(panelMain);
		frame.setBounds(500, 200, 400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭窗体时结束程序
		frame.setAlwaysOnTop(true);// 永远在所有窗体最上
		frame.setVisible(true);
		// 光标给消息文本框
		txt.requestFocus();
		linkServer();
	}
	/** 显示文本到文本域,并且追加一个换行
	 * @param msg	要显示的文本
	 */
	public void showTxt(String msg) {
		ta.append(msg+"\n");
	}

	public static void main(String[] args) {
		new MyClient();
	}
	//动作监听
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == but) {// 发送
			if (bw == null) {
				showTxt("当前没有客户端连接,无法发送消息");
				return;
			}
			String s = txt.getText().trim();// 得到文本框要发送的文字,去掉两端空格
			if (s.length() == 0) {
				showTxt("不可以发送空消息");
				return;
			}
			showTxt("我说:" + s);
			try {
				bw.write(s + "\n");// 发送网络消息给对方
				bw.flush();// 清空缓冲
			} catch (IOException e1) {
				showTxt("信息:" + s + " 发送失败");
			}
			// 清空文本框,得到焦点
			txt.setText("");
			txt.requestFocus();
		}
	}
	/**
	 * 连接服务器
	 */
	public void linkServer(){
		showTxt("准备连接服务器"+ip+":"+port);
		try {
			Socket jiaoYi = new Socket(ip,port);
			showTxt("连接服务器成功,开始进行通讯");
			// 得到输入和输出
			br = new BufferedReader(new InputStreamReader(
					jiaoYi.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(
					jiaoYi.getOutputStream()));
			String s = null;
			while ((s = br.readLine()) != null) {
				showTxt( s);
			}
		} catch (UnknownHostException e) {
			showTxt("连接服务器失败,网络连通错误");
		} catch (IOException e) {
			showTxt("与服务器通讯失败,已经断开连接");
		}
		showTxt("关闭");
	}
}
