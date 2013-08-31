import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;


class ChatClient extends JFrame{

  public static final int WIDTH = 475;
  public static final int HEIGHT = 350;
  //以下用于定义UI变量
  JPanel panel = new JPanel();         //总面板
   
  JPanel panel1 = new JPanel();        //用于放置输入姓名和连接两个按钮
  JPanel panel2 = new JPanel();        //用于放置聊天信息显示
  JPanel panel3 = new JPanel();        //用于放置发送信息区域
  JLabel label1 = new JLabel();
  JTextField name_txt = new JTextField(15);
  JButton button1 = new JButton("连接");
  JButton button2 = new JButton("断开连接");
  JTextArea chat_txt = new JTextArea(15,30);
  JLabel label2 = new JLabel();
  JButton button3 = new JButton("发送");
  JTextField msg_txt = new JTextField(20);
   
  //以下定义数据流和网络变量
  Socket soc = null;                    //定义连接套接字
  PrintStream ps = null;                //定义打印流 
  Listen listen = null;                 //定义一个客户端线程
  boolean ifquit = true;
  	
  public ChatClient()
  {
		panel.setLayout(new BorderLayout());
		
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());    
		label1.setText("姓名：");
		
		chat_txt.setEditable(false);
		panel2.setBackground(Color.cyan);
		panel1.setBackground(Color.pink);
		
		label2.setText("聊天信息：");
		msg_txt.setText("请输入聊天信息");
		panel3.setBackground(Color.cyan);
		
		panel.add(panel1, BorderLayout.NORTH);
		panel1.add(label1, null);
		panel1.add(name_txt, null);
		panel1.add(button1, null);
		panel1.add(button2, null);
		button1.addActionListener(new CommandAction());
		button2.addActionListener(new CommandAction());
		
		panel.add(panel2, BorderLayout.CENTER);
		panel2.add(new JScrollPane(chat_txt), null);
		
		panel.add(panel3,  BorderLayout.SOUTH);
		panel3.add(label2, null);
		panel3.add(msg_txt, null);
		panel3.add(button3, null);
		button3.addActionListener(new CommandAction());    
		
		setTitle("Client");
		setSize(WIDTH,HEIGHT);
		Container contentPanel = getContentPane();
		contentPanel.add(panel);
  }	

  public void disconnect()                                         //客户端点击断开连接要运行的方法
  {
     if(soc!=null)
	 {
	     try
		 {
		 	ifquit = true;
		 	ps.println("QUIT");                                    //用打印流发送QUIT信息通知服务器断开此次通信
			ps.flush();
			soc.close();                                            //关闭套接字
			soc = null;
		 }
		 catch(IOException e)
		 {
		     System.out.println(e);
		 }
	 }// end of if
  }


class CommandAction implements ActionListener
{
	public void actionPerformed(ActionEvent event)
	{
		ifquit = false;
		String command = event.getActionCommand();
		if(command.equals("连接"))
		{
		     if(soc==null)
			 {
			     try
				 {
					 soc=new Socket(InetAddress.getLocalHost(),2525);     //使用端口2525实例化一个本地套接字
					 System.out.println(soc);                             //在控制台打印实例化的结果
					 ps=new PrintStream(soc.getOutputStream());           //将ps指向soc的输出流
					 StringBuffer info=new StringBuffer("INFO: ");        //定义一个字符缓冲存储发送信息
					                                                      //其中INFO为关键字让服务器识别为连接信息
																		  //并将name和ip用"："分开，在服务器端将用一个
																		  //StringTokenizer类来读取数据
					 String userinfo=name_txt.getText()+":"+InetAddress.getLocalHost().toString();
					 ps.println(info.append(userinfo));	
					 ps.flush();
					 
					 listen=new Listen(ChatClient.this,name_txt.getText(),soc);    //将客户端线程实例化  
					 listen.start();    //启动线程
				 }
				 catch(IOException e)
				 {
				     System.out.println("Error:"+e);
					 disconnect();
				 }
			 }
		}
		if(command.equals("断开连接"))
		{
			System.out.println("now, I want to quit this chatroom!");
		    disconnect();
		}
		if(command.equals("发送"))
		{
	         if(soc!=null)
			 {
			     StringBuffer msg=new StringBuffer("MSG: ");        //定义并实例化一个字符缓冲存储发送的聊天信息
				                                                    //其中MSG为关键词
				 try
				 {
				     String msgtxt=new String(msg_txt.getText());
				 }
				 catch(Exception e)
				 {
				 	System.out.println("Error:"+e);
				 }			 
				 ps.println(msg.append(msg_txt.getText()));          //用打印流发送聊天信息
				 ps.flush();
			 }		
		}
		
	}
}
////////////////////////////////////////////////////////////////////////////////////////////////////
class Listen extends Thread            //客户端线程类用来监听服务器传来的信息
{
   String name=null;                   //用来存储客户端连接后的name信息
   //DataInputStream dis=null;           //用来实现客户端接受服务器数据的输入流
   BufferedReader br = null;
   
   PrintStream ps=null;                //用来实现从客户端发送数据到服务器的打印流
   Socket socket=null;                 //用来存储客户端的socket信息
   ChatClient parent=null;             //用来存储当前运行的界面实例
   
   boolean boo = false;

   public Listen(ChatClient p,String n,Socket s)   //Listen类的构造器
   {
	     //接受参数
		 parent=p;
		 name=n;
		 socket=s;
	
		 try
		 {
			 //实例化两个数据流
		     br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			 ps = new PrintStream(s.getOutputStream());
		 }
		 catch(IOException e)
		 {
		     System.out.println("Error:"+e);
			 parent.disconnect();
		 }
    }//end of Listen constractor
  
    public void run()                               //线程运行方法
	{
        String msg=null;
	    while(true)
		{
			 if(ifquit)
			 {
				return;
			 }
		     try
		     {
		     	msg= br.readLine(); //读取从服务器传来的信息
		     }                 
			 catch(IOException e)
			 {
			    System.out.println("Error:"+e);
				parent.disconnect();
			 }
			 if (msg==null)                           //如果从服务器传来的信息为空则断开此次连接
			 {
			   parent.listen=null;              
			   parent.soc=null;
			   return;
			 }
			 StringTokenizer st=new StringTokenizer(msg,":");   //用StringTokenizer类来实现读取分段字符
			 String keyword=st.nextToken();                     //读取信息头即关键字用来识别是何种信息
	
		    if(keyword.equals("PEOPLEIN"))                      //如果是PEOPLEIN则是服务器发来的客户连接信息
			{
			    while(st.hasMoreTokens())                     //遍历st取得目前所连接的客户
				{
			        String str=st.nextToken();
				    parent.chat_txt.append(str+"进入房间！");
				    parent.chat_txt.append("\n\n");
			    }
			    System.out.println("in room!\n");
		    }
		    if(keyword.equals("PEOPLEOUT"))                      //如果是PEOPLEIN则是服务器发来的客户连接信息
			{
			    while(st.hasMoreTokens())                     //遍历st取得目前所连接的客户
				{
			        String str=st.nextToken();
				    parent.chat_txt.append(str+"退出房间！");
				    parent.chat_txt.append("\n\n");
			    }
		    }	
	        if(keyword.equals("MSG"))                        //如果关键字是MSG则是服务器传来的聊天信息
			{                                               //主要用来刷新客户端聊天信息区将每个客户的聊天内容显示出来
			     String usr=st.nextToken();
				 parent.chat_txt.append(usr);
				 parent.chat_txt.append(st.nextToken("\0"));
				 parent.chat_txt.append("\n\n");
			}
			if(keyword.equals("QUIT"))                 //如果关键字是QUIT则是服务器关闭的信息，用来切断此次连接
			{                                         
			     System.out.println("服务器给出信息Quit，释放连接");
			     try
			     {
			     	//parent.listen.stop();
			        parent.listen=null;
			        parent.soc.close();
				    parent.soc=null;
				    
	             }
	             catch(IOException e)
	             {
	             	System.out.println("Error:"+e);
	             }
				 return;
		    }
			if(keyword.equals("TAKEN"))                 //如果关键字是TAKEN则是重名，切断此次连接
			{                                         
			     System.out.println("登陆名重名，释放连接 \n");
			     try
			     {
			     	//parent.listen.stop();//
			        parent.listen=null;
			        parent.soc.close();
				    parent.soc=null;
				    
	             }
	             catch(IOException e)
	             {
	             	System.out.println("Error:"+e);
	             }
				 return;
			 }			 
		  }
      }   //end of run method
}      //end of Listen inner class


	public static void main(String[] args)
	{
		ChatClient frame = new ChatClient();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}		
}
