import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;


class ChatServer{
   
  static Vector clients=new Vector(10);   //用vector向量数组存储连接客户变量
  static ServerSocket server=null;        //建立服务器socket
  static int active_connects=0;           //用来存储目前连接的客户数
  static Socket socket=null;               //用来存储一个套接字连接
  
  public ChatServer()                         
  {
  }

  //以下实现各种方法
  public static void notifyRoom(String name,boolean in)   //用来刷新客户端进入聊天室的用户信息
  {
  	 StringBuffer people;
  	 if(in)
        people=new StringBuffer("PEOPLEIN");
     else
        people=new StringBuffer("PEOPLEOUT");	
                         
     sendClients(people.append(":"+name));  //用sendClients方法向客户端发送信息
  }

  public static synchronized void sendClients(StringBuffer msg)   //实现sendClients方法专用来向每个连接的客户端发送信息
  {
     for(int i=0;i<clients.size();i++)
	 {
	     Client c=(Client)clients.elementAt(i);
		 c.send(msg);
	 }
  }

  public static boolean checkName(Client newclient)         //实现检查连接客户的socket信息是否合法
  {
     for(int i=0;i<clients.size();i++)
	 {
	     Client c=(Client)clients.elementAt(i);
		 if((c.name).equals(newclient.name))
			 return false;
	 }
	 return(true);
  }//end of checkName method

  public static synchronized void disconnect(Client c)         //实现断开单个客户的方法
  {
     try
	 {		 
		 ChatServer.active_connects--;                       //将连接数减1
	     c.send(new StringBuffer("QUIT"));                    //向客户发送断开连接信息
		 c.socket.close(); 
	 }
	 catch(IOException e)
	 {
	     System.out.println("Error:"+e);
	 }
	 finally
	 {
	     clients.removeElement(c);                          //从clients数组中删除此客户的相关socket等信息
	 }
  }


   public static void main(String[] args) {

	    ChatServer chatServer1=new ChatServer();      
		System.out.println("Server starting ...");
		
		try
		{
		    server=new ServerSocket(2525);      //使用端口2525初始化服务器套接字
		}
		catch(IOException e)
		   {
		    System.out.println("Error:"+e);
		}
		while(true)
	    {
		    if(clients.size()<=10)       //当客户数不大于10个时开始连接，仅仅是个参考值，同学们可以实验能够承受的最大连接数
			{
			    try
			    {
				    socket=server.accept();   //用来存储连接上的客户socket
				    if(socket!=null)
				    {
				        System.out.println(socket+" 已经连接上了！校验用户名！");    
				        Client c=new Client(socket);                     //定义并实例化一个Client线程类，每一个对应一个客户连接
				        
					    if(checkName(c))                                      //调用checkName方法验证c的合法性
					    {
					    	clients.addElement(c);                              //加入clients数组中
						    int connum = ++chatServer1.active_connects;         //定义connum来存储活动连接数
						    String constr="目前有"+connum+"客户相连";         
						    System.out.println(constr);
						    c.start();                                         //启动线程
							notifyRoom(c.name,true);                           //用notifyRoom方法来通知各客户聊天室连接变化
					    }
					    else
					    {
						    //如果名字不合法
						    c.ps.println("TAKEN");
							disconnect(c);
					    }
				    }
				}
				catch(IOException e)
				{
				    System.out.println("Error:"+e);
				}
			}
			else                                   //如果clients数组超过了预设值，则等候有人退出
			{
				try{Thread.sleep(200);}
				catch(InterruptedException e)
				{
					System.out.println("Error:"+e);
				}
			}
		}//end of while
	  }// end of main method
}// end of class ChatServer

class Client extends Thread                              //实现 Client线程类
{
    Socket socket;                                       //用来存储一个连接客户的socket信息
	String name;                                         //用来存储客户的连接姓名
	String ip;                                           //用来存储客户的ip信息
 	
 	BufferedReader dis = null;                           //用来实现接受从客户端发来的数据流
	PrintStream ps;                                      //用来实现向客户端发送信息的打印流

	public void send(StringBuffer msg)                   //实现向客户端发送信息的方法
	{
	   ps.println(msg);                                  //用打印流发送信息
	   ps.flush();
	}

	public  Client(Socket s)                             //Client线程类的构造器
	{
	   socket=s;                                           
	   try
	   {
	       dis = new BufferedReader(new InputStreamReader(s.getInputStream()));//存储特定客户socket的输入流接受s这个客户发送到服务器端的信息
		   ps=new PrintStream(s.getOutputStream());      //存储特定客户socket的输出流发送服务器给s这个客户的信息
		   String info=dis.readLine();                   //读取接受来的信息
		   
		   StringTokenizer stinfo=new StringTokenizer(info,":");  //用StringTokenizer类来读取用"："分段字符
		   String head=stinfo.nextToken();                        //head用来存储类似于关键字的头信息
           if(stinfo.hasMoreTokens())		   
		       name=stinfo.nextToken();                           //关键字后的第二段数据是客户名信息
		   if(stinfo.hasMoreTokens())
		       ip=stinfo.nextToken();                             //关键字后的第三段数据是客户ip信息
		   System.out.println(head);                               //在控制台打印头信息
	   }
	   catch(IOException e)
	   {
	       System.out.println("Error:"+e);
	   }
	}//end of Client constrator

	public void run()   
	{
	   while(true)
	   {
	       String line=null;
		   try
		   {
		       line=dis.readLine();       //读取客户端发来的数据流
		   }
		   catch(IOException e)
		   {
		       System.out.println("Error"+e);
			   ChatServer.disconnect(this);
			   ChatServer.notifyRoom(name,false);
			   return;
		   }

		   if(line==null)    //客户已离开
		   {
			   ChatServer.disconnect(this);
			   ChatServer.notifyRoom(name,false);
			   return;
		   }
		   
		   StringTokenizer st=new StringTokenizer(line,":");
		   String keyword=st.nextToken();

		   if(keyword.equals("MSG"))                          //如果关键字是MSG则是客户端发来的聊天信息
		   {
		       StringBuffer msg=new StringBuffer("MSG:");     //在服务器端再重新建立一个字符缓冲
			   msg.append(name);
			   msg.append(st.nextToken("\0"));
			   ChatServer.sendClients(msg);                   //再将某个客户发来的聊天信息发送到每个连接客户的聊天栏中
		   }
		   else if(keyword.equals("QUIT"))                   //如果关键字是QUIT则是客户端发来断开连接的信息
		   {
		   	   System.out.println("用户退出聊天室！");
		       ChatServer.disconnect(this);                  //服务器断开与这个客户的连接
			   ChatServer.notifyRoom(name,false);            //通知各个聊天室
			   //this.stop();
			   return;                                   
		   }
	   }
	}
  }  //end of class Client
