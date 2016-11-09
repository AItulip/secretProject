package gw;

import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 双工服务器,多人 本服务器默认不提供服务,而是在客户端连接搜索时创建独立线程负责业务
 **/
public class MutilServer implements ActionListener {

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
	 * 当前服务器使用端口
	 */
	private int port = 6666;
	/**
	 * 远程客户端的IP
	 */
	private String clientIp;
	/**
	 * 记录所有正在工作的服务员的登记表
	 */
	private Vector<Waiter> dengJiBiao;

	public MutilServer() {
		frame = new JFrame("双工多人服务器");
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
		// 按钮添加监听
		but.addActionListener(this);
		frame.add(panelMain);
		frame.setBounds(100, 300, 400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭窗体时结束程序
		frame.setAlwaysOnTop(true);// 永远在所有窗体最上
		frame.setVisible(true);
		// 创建登记表
		dengJiBiao = new Vector<Waiter>();
		// 光标给消息文本框
		txt.requestFocus();
		createServer();
	}

	/**
	 * 显示文本到文本域,并且追加一个换行
	 * 
	 * @param msg
	 *            要显示的文本
	 */
	public void showTxt(String msg) {
		ta.append(msg + "\n");
	}

	public static void main(String[] args) {
		new MutilServer();
	}

	// 动作监听
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == but) {// 发送
			txt.requestFocus();
			String str = txt.getText().trim();
			if(str.length()==0){
				showTxt("不可以发送空消息");
				return;
			}
			if(dengJiBiao.size()==0){
				showTxt("当前木有客户连接,无法发送信息");
				return;
			}
			str ="服务器消息:"+str;
			//找到所有登记表中的服务员,实现群发
			for (int i = 0; i < dengJiBiao.size(); i++) {
				Waiter w = dengJiBiao.get(i);//得到当前循环的服务员
				w.send(str);
			}
			// 清空文本框,得到焦点
			txt.setText("");
		}
	}

	/**
	 * 启动网络服务器
	 */
	public void createServer() {
		showTxt("正在启动服务器,使用本机" + port + "端口...");
		try {
			ServerSocket server = new ServerSocket(port);
			showTxt("服务器启动成功,开始监听网络连接...");
			while (true) {
				Socket jiaoYi = server.accept();
				// 每得到一个交易,就是来了一个客户端.需要交给一个新的服务员去维护处理
				new Waiter(jiaoYi, dengJiBiao, this);
			}
		} catch (IOException e) {
			showTxt("服务器启动失败,可能端口被占用");
		}
	}
}
