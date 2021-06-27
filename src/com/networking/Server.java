package com.networking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Server {
	JPanel p1;
	JTextField tf1;
	JButton btn;
	static JFrame f1=new JFrame();
	static JPanel ta; 
	static  ServerSocket ss;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	static String msg;
	boolean typing;
	static Box vertical=Box.createVerticalBox();
	Server(){
		
		//Header Area
		p1=new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(7,94,84));
		p1.setBounds(0,0,500,70);
		
		ImageIcon i1=new ImageIcon("C://Users//Sujeet//eclipse-workspace//ChatApplication//Icons/3.png");
		Image i2=i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		JLabel l1 =new JLabel(i3);
		l1.setBounds(20,30,25,15);
		l1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				System.exit(0);
			}
		});
		
		ImageIcon i4=new ImageIcon("C://Users//Sujeet//eclipse-workspace//ChatApplication//Icons/car.png");
		Image i5=i4.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
		ImageIcon i6=new ImageIcon(i5);
		JLabel l2 =new JLabel(i6);
		l2.setBounds(40,5,60,60);
		
		JLabel l3=new JLabel("Server");
		l3.setBounds(100,15,100,15);
		l3.setFont(new Font("Times New Roman",Font.BOLD,18));
		l3.setForeground(Color.white);
		
		JLabel l4=new JLabel("Active now");
		l4.setBounds(100,35,100,15);
		l4.setFont(new Font("Times New Roman",Font.ITALIC,12));
		l4.setForeground(Color.white);
		
		Timer t=new Timer(1,new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(typing) {
					l4.setText("Active now");
				}
				
			}
			
		});
		t.setInitialDelay(2000);
		
		ImageIcon i7=new ImageIcon("C://Users//Sujeet//eclipse-workspace//ChatApplication//Icons/video.png");
		Image i8=i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
		ImageIcon i9=new ImageIcon(i8);
		JLabel l5 =new JLabel(i9);
		l5.setBounds(325,5,60,60);
		
		ImageIcon i10=new ImageIcon("C://Users//Sujeet//eclipse-workspace//ChatApplication//Icons/phone.png");
		Image i11=i10.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
		ImageIcon i12=new ImageIcon(i11);
		JLabel l6 =new JLabel(i12);
		l6.setBounds(380,5,60,60);
		
		ImageIcon i13=new ImageIcon("C://Users//Sujeet//eclipse-workspace//ChatApplication//Icons/logo.png");
		Image i14=i13.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
		ImageIcon i15=new ImageIcon(i14);
		JLabel l7 =new JLabel(i15);
		l7.setBounds(425,5,60,60);
		
		p1.add(l1);
		p1.add(l2);
		p1.add(l3);
		p1.add(l4);
		p1.add(l5);
		p1.add(l6);
		p1.add(l7);
		
		//Panel 2 Message area
		 ta=new JPanel();
		 ta.setBounds(1,72,481,255);
		 ta.setBackground(Color.lightGray);
		 f1.add(ta);
		 ta.setFont(new Font("Times New Roman",Font.PLAIN,16));
		
		//Footer Area
		tf1=new JTextField();
		tf1.setBounds(1,330,350,25);
		tf1.setFont(new Font("Times New Roman",Font.ITALIC,15));
		f1.add(tf1);
		tf1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				l4.setText("Typing...");
				t.stop();
				typing=true;
			}
			@Override
			public void keyReleased(KeyEvent ke) {
				if(!t.isRunning()) {
					t.start();
				}
			}
		});
		
		btn=new JButton("Send");
		btn.setBounds(355,330,120,25);
		btn.setBackground(new Color(7,94,84));
		btn.setForeground(Color.white);
		f1.add(btn);
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			 try {
				
				JPanel p3=formatLabel(tf1.getText());
				
				ta.setLayout(new BorderLayout());
				JPanel right=new JPanel(new BorderLayout());
				right.add(p3,BorderLayout.LINE_END);
				vertical.add(right,BorderLayout.PAGE_START);
				ta.add(vertical);
				f1.validate();
				dout.writeUTF(tf1.getText());
				tf1.setText("");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
				
			}
		});
		
		f1.setLayout(null);
		f1.add(p1);
		
		f1.setSize(500,400);
		f1.setLocation(400,200);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	static public JPanel formatLabel(String sout) {
		JPanel p3=new JPanel();
		JLabel l=new JLabel("<html><p style=\"width :150px\">"+sout+"</p></html>");
		l.setBackground(Color.cyan);
		l.setOpaque(true);
		l.setBorder(new EmptyBorder(5,5,5,5));
		l.setFont(new Font("Tahoma",Font.PLAIN,16));
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("hh:mm");
		JLabel l1=new JLabel();
		l1.setText(sdf.format(cal.getTime()));
		p3.add(l);
		p3.add(l1);
		p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));
		return p3;
	}
	public static void main(String[] args)  {
		new Server().f1.setVisible(true);
		try {
			 ss=new ServerSocket(6001);
			 s=ss.accept();
			 din=new DataInputStream(s.getInputStream());
			 dout=new DataOutputStream(s.getOutputStream());
			 while(true) {
			 msg=din.readUTF();
			 JPanel p2=formatLabel(msg);
		      JPanel left=new JPanel(new BorderLayout());
		      left.add(p2,BorderLayout.LINE_START);
		      vertical.add(left,BorderLayout.PAGE_START);
		      ta.add(vertical);
		      f1.validate();
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
