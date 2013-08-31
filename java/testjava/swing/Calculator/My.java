import java.util.*;
public class My extends Thread{
		public static void main(String args[])
		{
			Cal_Frame cal=new Cal_Frame();
		
			for(int i=0;;i++)
			{ 
				Date d=new Date();
				cal.setTime(d.toString());
				try{
					sleep(1000);
				}
				catch(InterruptedException e){};
			}
		}
}

