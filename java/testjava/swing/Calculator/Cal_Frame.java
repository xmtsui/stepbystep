import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cal_Frame extends Frame implements ActionListener{
	private String input;//接收输入的字符串
	boolean decimal_p;
	TextField monitor,time;//显示屏和时间
	Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18;
	Panel p0,p1,p2,p3,p4,p5;
	

	public Cal_Frame()
	{
		monitor=new TextField();
		time=new TextField();
		input=new String("");
		p0=new Panel();
		p1=new Panel();
		p2=new Panel();
		p3=new Panel();
		p4=new Panel();
		p5=new Panel();
		b1=new Button("1");
		b1.addActionListener(this);
		b2=new Button("2");
		b2.addActionListener(this);
		b3=new Button("3");
		b3.addActionListener(this);
		b4=new Button("4");
		b4.addActionListener(this);
		b5=new Button("5");
		b5.addActionListener(this);
		b6=new Button("6");
		b6.addActionListener(this);
		b7=new Button("7");
		b7.addActionListener(this);
		b8=new Button("8");
		b8.addActionListener(this);
		b9=new Button("9");
		b9.addActionListener(this);
		b10=new Button("0");
		b10.addActionListener(this);
		b11=new Button("=");
		b11.addActionListener(this);
		b12=new Button(".");
		b12.addActionListener(this);
		b13=new Button("/");
		b13.addActionListener(this);
		b14=new Button("*");
		b14.addActionListener(this);
		b15=new Button("-");
		b15.addActionListener(this);
		b16=new Button("+");
		b16.addActionListener(this);
		b17=new Button("Backspace");
		b17.addActionListener(this);
		b18=new Button("c");
		b18.addActionListener(this);
		
		setLayout(new GridLayout(7,1));
		monitor.setEditable(false);
		time.setEditable(false);
		add(p5);
		add(monitor);
		add(p0);
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		
		p5.setLayout(new GridLayout(1,1));
		p5.add(time);
		p0.setLayout(new GridLayout(1,4));
		p0.add(b17);
		p0.add(b18);
		p1.setLayout(new GridLayout(1,4));
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b13);
		p2.setLayout(new GridLayout(1,4));
		p2.add(b4);
		p2.add(b5);
		p2.add(b6);
		p2.add(b14);
		p3.setLayout(new GridLayout(1,4));
		p3.add(b7);
		p3.add(b8);
		p3.add(b9);
		p3.add(b15);
		p4.setLayout(new GridLayout(1,4));
		p4.add(b10);
		p4.add(b12);
		p4.add(b11);
		p4.add(b16);

		addWindowListener(new HandleWin());
		setSize(300,200);
		setTitle("My Digit Calculator");
		setVisible(true);
	}
	
	public void setTime(String t){
		time.setText("\t  "+t);
		
	}
	
	public void actionPerformed(ActionEvent e){
	        
	        if(e.getSource()==b1)
	           input=input+"1";
	        else if(e.getSource()==b2)
	        	input=input+"2";
	        else if(e.getSource()==b3)
	        	input=input+"3";
	        else if(e.getSource()==b4)
	        	input=input+"4";
	        else if(e.getSource()==b5)
	        	input=input+"5";
	        else if(e.getSource()==b6)
	        	input=input+"6";
	        else if(e.getSource()==b7)
	        	input=input+"7";
	        else if(e.getSource()==b8)
	        	input=input+"8";
	        else if(e.getSource()==b9)
	        	input=input+"9";
	        else if(e.getSource()==b10)
	        	input=input+"0";
	        else if(e.getSource()==b16)
	        {
	            if(input.length()!=0&&!input.endsWith("+")&&!input.endsWith("-")&&
	               !input.endsWith("*")&&!input.endsWith("/")&&!input.endsWith("."))
	            {
	                decimal_p=false;
	                input=input+"+";
	            }
	        }
	        else if(e.getSource()==b15)
	        {
	            if(input.length()!=0&&!input.endsWith("+")&&!input.endsWith("-")&&
	               !input.endsWith("*")&&!input.endsWith("/")&&!input.endsWith("."))
	            {
	                decimal_p=false;
	                input=input+"-";
	            }
	        }
	        else if(e.getSource()==b14)
	        {
	            if(input.length()!=0&&!input.endsWith("+")&&!input.endsWith("-")&&
	               !input.endsWith("*")&&!input.endsWith("/")&&!input.endsWith("."))
	            {
	                decimal_p=false;
	                input=input+"*";
	            }
	        }
	        else if(e.getSource()==b13)
	        {
	            if(input.length()!=0&&!input.endsWith("+")&&!input.endsWith("-")&&
	               !input.endsWith("*")&&!input.endsWith("/")&&!input.endsWith("."))
	            {
	                decimal_p=false;
	                input=input+"/";
	            }
	        }
	        else if(e.getSource()==b12)
	        {
	            if(input.length()!=0&&decimal_p==false&&!input.endsWith("+")
	                &&!input.endsWith("-")&&!input.endsWith("*")
	                &&!input.endsWith("/")&&!input.endsWith("."))
	            {
	                input=input+".";
	                decimal_p=true;
	            }
	        }
	        else if(e.getSource()==b17&&input.length()!=0)
	            input=input.substring(0,input.length()-1);
	        else if(e.getSource()==b18)
	            input="";
	        monitor.setText(input);
	        if(e.getSource()==b11)
	        {
	            input=Calculate.getResult(input);
	            monitor.setText(input);
	            input="";
	        }	        
	 }
}