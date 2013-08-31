import java.io.*;//实现cat的选择是否用-n的操作
public class cat{
   public static void main(String[] args) {
      String thisLine;
      if(args[0].equals("-n"))
      {
      for (int i = 1; i < args.length; i++) {
    	  try {
            LineNumberReader br = new LineNumberReader(new FileReader(args[i]));
            while ((thisLine = br.readLine()) != null) {
            	System.out.println(br.getLineNumber()+"."+thisLine);
            }
         }
         catch (IOException ex) {
            System.err.println(ex);
         }
      }//end of for
      }//end of if
      else
      {
    	  for (int i = 0; i < args.length; i++) {
    	    try {
    	         LineNumberReader br = new LineNumberReader(new FileReader(args[i]));
    	         while ((thisLine = br.readLine()) != null) {
    	           System.out.println(thisLine);
    	           }
    	         }
    	         catch (IOException ex) {
    	            System.err.println(ex);
    	         }
    	      }//end of for
    	  
      }
   }//end of main 
}//end of class
