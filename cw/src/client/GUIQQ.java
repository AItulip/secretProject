package client;

import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class GUIQQ extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField username;

    private JPasswordField password;
 
    private JLabel jl1;
    private JLabel jl2;
    private JLabel jl3;
    private JLabel jl4;

    private JButton bu1;
    private JButton bu2;
    private JButton bu3;

    private JCheckBox jc1;
    private JCheckBox jc2;

    private JComboBox<String> jcb;

 
    public GUIQQ() {
        
        this.setTitle("CHATTING");
        
        init();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setLayout(null);
        this.setBounds(0, 0, 365, 265);
       
        Image image = new ImageIcon("e:/a.jpg").getImage();
        this.setIconImage(image);
        
        this.setResizable(false);

       
        this.setLocationRelativeTo(null);

        
        this.setVisible(true);
    }


 
    public void init() {
        
        Container con = this.getContentPane();
        jl1 = new JLabel();
        
        Image image1 = new ImageIcon("e:/background.jpg").getImage();
        jl1.setIcon(new ImageIcon(image1));
        jl1.setBounds(0, 0, 355, 265);

        // 
        jl2 = new JLabel();  
        Image image2 = new ImageIcon("e:/b.jpg").getImage();
        jl2.setIcon(new ImageIcon(image2));
        jl2.setBounds(40, 95, 50, 60);

        
        username = new JTextField();
        username.setBounds(100, 100, 150, 20);
        
        jl3 = new JLabel("UserName");
        jl3.setBounds(260, 100, 70, 20);
        
        
        password = new JPasswordField();
        password.setBounds(100, 130, 150, 20);
        
        jl4 = new JLabel("Password");
        jl4.setBounds(260, 130, 70, 20);
        
        // input
        jc1 = new JCheckBox("Rememb");
        jc1.setBounds(105, 155, 80, 15);
        jc2 = new JCheckBox("Autologin");
        jc2.setBounds(185, 155, 80, 15);
        
        // Choice
        jcb = new JComboBox<String>();
        jcb.addItem("Online");
        jcb.addItem("Invisible");
        jcb.addItem("Leaving");
        jcb.setBounds(40, 150, 55, 20);
        
        // Button set
        bu1 = new JButton("Login");
        bu1.setBounds(280, 200, 65, 20);
        bu1.addActionListener(new ActionListener(){
        	
        	@Override
        	public void actionPerformed(ActionEvent e){
        		String str=e.getActionCommand();
        		if("Login".equals(str)){
        			String getName =username.getText();
//        			String getPwd =password.getText();
        			JOptionPane.showConfirmDialog(null, "You username is"+getName);
        		}
        	}
        });
        
        bu2 = new JButton("Register");
        bu2.setBounds(5, 200, 85, 20);
        bu3 = new JButton("Settings");
        bu3.setBounds(100, 200, 85, 20);
        
        jl1.add(jl2);
        jl1.add(jl3);
        jl1.add(jl4);
        jl1.add(jc1);
        jl1.add(jc2);
        jl1.add(jcb);
        jl1.add(bu1);
        jl1.add(bu2);
        jl1.add(bu3);
        con.add(jl1);
        con.add(username);
        con.add(password);
    }
    
    public static void main(String[] args) {
    	 new GUIQQ();
    }
}