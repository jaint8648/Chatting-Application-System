package Chatting.application.system;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.*;

public class Server implements ActionListener {
JTextField text;
JPanel p2;
static Box vertical=Box.createVerticalBox();
static JFrame f=new JFrame();
static DataOutputStream dout;
	Server(){

		f.setIconImage(Toolkit.getDefaultToolkit().getImage(Server.class.getResource("/Gallery/whatsappicon.png")));
		f.setLayout(null);
		
		
		JPanel p1=new JPanel();
		p1.setBackground(new Color(7,94,84));
		p1.setBounds(0,0,450,70);
		p1.setLayout(null);
		f.add(p1);
		
		ImageIcon i1 = new ImageIcon(Server.class.getResource("/Gallery/arrow.png"));
		Image i2=i1.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
	    ImageIcon i3=new ImageIcon(i2);
		JLabel back=new JLabel(i3);
		back.setBounds(5,10,50,50);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
		p1.add(back);
         
          
         back.addMouseListener(new MouseAdapter(){
        	 public void mouseClicked(MouseEvent ae) {
        		 System.exit(0);
        	 }
         });
         
        ImageIcon i4 = new ImageIcon(Server.class.getResource("/Gallery/elonmusk.png"));
 		Image i5=i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
 	    ImageIcon i6=new ImageIcon(i5);
 		JLabel profile=new JLabel(i6);
 		profile.setBounds(60,10,50,50);
 		p1.add(profile);
         
 		ImageIcon i7 = new ImageIcon(Server.class.getResource("/Gallery/videoicon2.png"));
 		Image i8=i7.getImage().getScaledInstance(50,33,Image.SCALE_DEFAULT);
 	    ImageIcon i9=new ImageIcon(i8);
 		JLabel videoicon=new JLabel(i9);
 		videoicon.setBounds(320,22,50,33);
 		p1.add(videoicon);
          
 		ImageIcon i10 = new ImageIcon(Server.class.getResource("/Gallery/phoneicon3.png"));
 		Image i11=i10.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
 	    ImageIcon i12=new ImageIcon(i11);
 		JLabel phoneicon=new JLabel(i12);
 		phoneicon.setBounds(380,21,30,30);
 		p1.add(phoneicon);
          
 		ImageIcon i13 = new ImageIcon(Server.class.getResource("/Gallery/doticon2.png"));
 		Image i14=i13.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT);
 	    ImageIcon i15=new ImageIcon(i14);
 		JLabel doticon=new JLabel(i15);
 		doticon.setBounds(415,15,40,40);
 		p1.add(doticon);
            
		JLabel name=new JLabel("Elon Musk");
		name.setBounds(130, 25, 100, 18);
		name.setForeground(Color.white);
		name.setFont(new Font("Eras Bold ITC",Font.PLAIN,18));
		p1.add(name);
		
		JLabel status=new JLabel("online");
		status.setBounds(130, 40, 100, 18);
		status.setForeground(Color.white);
		status.setFont(new Font("Eras Bold ITC",Font.PLAIN,12));
		p1.add(status);
		
		p2=new JPanel();
		p2.setBounds(0, 75, 450,700);
		p2.setBackground(Color.CYAN);
        f.add(p2);
        		
        
        text=new JTextField();
        text.setBounds(7, 655, 310, 40);
        text.setFont(new Font("SANS_SERIF",Font.PLAIN,12));
        text.setBorder(BorderFactory.createEmptyBorder());
        f.add(text);
        
        JButton send=new JButton("Send");
        send.setBackground(Color.green);
        send.setForeground(Color.white);
        send.addActionListener(this);
        send.setFont(new Font("Tahoma",Font.BOLD,15));
        send.setBounds(322, 655,120, 40);
        f.add(send);
        
        
        
        		
		f.setUndecorated(true);
		f.setBounds(200,20,450,700);
		f.getContentPane().setBackground(Color.white);
		f.setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
                 new Server();
	try {
		@SuppressWarnings("resource")
		ServerSocket skt=new ServerSocket(6020);
		while(true) {
			Socket s=skt.accept();
			DataInputStream din=new DataInputStream(s.getInputStream());
			dout=new DataOutputStream(s.getOutputStream());
			
			while(true) {
				String msg=din.readUTF();
				JPanel panel=FormatLabel(msg);
            
				JPanel left=new JPanel(new BorderLayout());
                   left.add(panel,BorderLayout.LINE_START);
                   vertical.add(left);			
                     f.validate();      
			}
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	
	} 


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			String out=text.getText();
			
			JPanel p3=FormatLabel(out);
			
			p2.setLayout(new BorderLayout());
               
			JPanel right=new JPanel(new BorderLayout());
			right.add(p3,BorderLayout.LINE_END);
			vertical.add(right);
			vertical.add(Box.createVerticalStrut(15));
			p2.add(vertical,BorderLayout.PAGE_START);
			
			
			dout.writeUTF(out);
			
			text.setText("");
			
			f.repaint();
			f.invalidate();
			f.validate();
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		}
	
	
	public static JPanel FormatLabel(String out) {
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

		JLabel output=new JLabel("<html><p style=\"width:120px\">"+ out +"</p></html>");
		output.setFont(new Font("Tahoma",Font.PLAIN,16));
		output.setBackground(Color.green);
		output.setOpaque(true);
		output.setBorder(new EmptyBorder(15,15,15,50));
		
		panel.add(output);
		
		
		
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat tus=new SimpleDateFormat("HH:mm");

		JLabel time=new JLabel();
		time.setText(tus.format(cal.getTime()));
		panel.add(time);
		

		
		
		return panel;
		
		
	}

}
