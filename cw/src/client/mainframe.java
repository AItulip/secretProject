package client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class mainframe extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<String> jlist= new JList<String>(); 
	private JMenuBar mb = new JMenuBar();
	private JMenu jmenu = null;
	private JMenuItem jmm1 = null;
	private JMenuItem jmm2 = null; 
	private JPanel jpp = null; 
	private JTextArea save=null;
	private JTextArea sendd=null; 
	private JTextField jjtt1 =new JTextField(15);
	private JTextField jjtt2 =new JTextField(); 
	private JTextField jjtt3 =new JTextField();
	private JTextField jjtt4 =new JTextField();
	private JTextField jjtt5 =new JTextField();

	
	public mainframe(){
		super("CHATTING");
	
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
		}catch(Exception e){
			
		}
		 jmenu = new JMenu("Start"); 
		 jmm1 = new JMenuItem("My Information"); 
		 jmm2 = new JMenuItem("Exit"); 
		 jpp = new JPanel(); 
		 final CardLayout card=new CardLayout(); 
		 jpp.setLayout(card); 
		 save=new JTextArea(21,33);
		 sendd=new JTextArea(5,33); 
		 Container contain = getContentPane();
	     contain.setLayout(new BorderLayout());
		 JLabel jjll=new JLabel(); 
		 JPanel jpp1=new JPanel(); 
		 JPanel jpp2=new JPanel();
		 JPanel zjpp1 =new JPanel(); 
		 JPanel zjpp2 =new JPanel(); 
		 JPanel zjpp3=new JPanel(); 
		 
		 JLabel jll1=new JLabel("I D");
		 JLabel jll2=new JLabel("Username");
		 JLabel jll3=new JLabel("Note");
		 JLabel jll4=new JLabel("Group"); 
		 JLabel jll5=new JLabel("Birthday"); 
		 
		 JPanel left =new JPanel();
		 left.setBorder(new TitledBorder("Friend Information")); 
		 
		 zjpp1.setBorder(new TitledBorder("Friend/Group List")); 
		 zjpp2.setBorder(new TitledBorder("Chat Messages")); 
		 zjpp3.setBorder(new TitledBorder("User-defined")); 
		 
		 JButton senn =new JButton("Enter"); 
		 JButton qpg=new JButton("File"); 
		 JButton shp =new JButton("emoji");
		 
		 save.setLineWrap(true);save.setEditable(false);
		 JScrollPane scroll = new JScrollPane(save); 
		 scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		 scroll.setWheelScrollingEnabled(true);
		 save.setCaretPosition(save.getText().length()); 
		 sendd.setLineWrap(true);
		 JScrollPane scrol = new JScrollPane(sendd); 
		 sendd.setCaretPosition(0); 
		 scrol.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		 jjtt1.setEditable(false);
		 jjtt2.setEditable(false);
		 jjtt3.setEditable(false); 
		 jjtt4.setEditable(false);
		 jjtt5.setEditable(false);
		 
		 jjll.setText("WELCOME"); 
		 jpp1.add(jjll); 
		 left.setLayout(new GridLayout(10, 1)); 
		 left.add(jll1);
		 left.add(jjtt1); 
		 left.add(jll2);
		 left.add(jjtt2); 
		 left.add(jll3);
		 left.add(jjtt3); 
		 left.add(jll4);
		 left.add(jjtt4); 
		 left.add(jll5);
		 left.add(jjtt5); 
		 jmenu.add(jmm1); 
		 jmenu.add(jmm2); 
		 mb.add(jmenu); 
		 setJMenuBar(mb); 
		 JToolBar gjt= new JToolBar();
		 gjt.setFloatable(false); 
		 gjt.add(qpg);
		 gjt.add(shp);
		 gjt.add(senn); 
		 
		 zjpp1.setBounds(10, 0, 200, 580); 
		 zjpp2.setBounds(220, 0, 400, 580); 
		 zjpp3.setBounds(630, 0, 210, 580); 
		 jlist.setFixedCellHeight(23);
		 jlist.setFixedCellWidth(150); 
		 jlist.setBounds(2, 5, 180, 500); 
		 jlist.setVisibleRowCount(8); 
		 JScrollPane ssc=new JScrollPane(jlist); 
		 ssc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 jpp2.add(zjpp1); 
		 jpp2.add(zjpp2); 
		 jpp2.add(zjpp3);
		 
		 zjpp1.add(ssc); 
		 zjpp1.add(left); 
		 zjpp2.add(scroll); 
		 zjpp2.add(scrol);
		 zjpp2.add(gjt); 
		 jpp2.setLayout(null);
		 
		 jmm1.addActionListener(new ActionListener(){
			 
			 @Override 
			 public void actionPerformed(ActionEvent e){
				 card.last(jpp); 
			 }
		 });
//		 
		 jmm2.addActionListener(new ActionListener(){
			 
			 @Override 
			 public void actionPerformed(ActionEvent e){
				 System.exit(0); 
			 }
		 });
		 
		 qpg.addActionListener(new ActionListener(){
			 
			 @Override
			 public void actionPerformed(ActionEvent e){
				 save.setText(""); 
			 }
			 
		 });
		 
		 sendd.addKeyListener(new KeyListener(){
			 public synchronized void keyPressed(KeyEvent e){
				 if (e.getKeyCode() == KeyEvent.VK_ENTER ){
					 
					 if( sendd.getText().trim().equals("")){
						 JOptionPane.showMessageDialog(mainframe.this, "Input Field Is Empty!");
					 }else{
						 save.append("Me"+"\t\n "+sendd.getText().trim()+"\n");
						 save.setSelectionStart(save.getText().length());
						 
						 sendd.setText("");
					 }
				 }
			 }
			 
			 @Override 
			 public void keyTyped(KeyEvent e){}
			 @Override 
			 public void keyReleased(KeyEvent e){}
		 });
		 
		 jlist.addMouseListener(new MouseListener(){
			 
			 @Override 
			 public void mouseReleased(MouseEvent e){}
			 @Override 
			 public void mousePressed(MouseEvent e){}
			 @Override 
			 public void mouseExited(MouseEvent e){}
			 @Override 
			 public void mouseEntered(MouseEvent e){}
			 @Override 
			 public void mouseClicked(MouseEvent e){}
			 
		 });
		 
		 senn.addActionListener(new ActionListener(){
			 @Override 
			 public void actionPerformed(ActionEvent e){
				 if( sendd.getText().trim().equals("")){
					 JOptionPane.showMessageDialog(mainframe.this, "Input Field Is Empty!");
				 }else{ 
					 save.append("Me"+"\t\n "+sendd.getText().trim()+"\n"); sendd.setText("");
					 } 
			 }
		 });
		 
		 jpp.add(jpp1);
		 jpp.add(jpp2); 
		 add(jpp); 
		 setLayout(new CardLayout()); 
		 setBounds(300, 200, 860, 640); 
		 setVisible(true); 
//		 setResizable(false); 
//		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[]args){ 
		new mainframe(); 	
	}

}
