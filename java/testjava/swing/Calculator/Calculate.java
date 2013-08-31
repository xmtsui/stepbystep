import java.util.*;
public class Calculate{
    public static String getResult(String input){
        input=input+"#";
        int i=0,j=0;
        double num1=0,num2=0,result=0;
        Stack oprs=new Stack();
        Stack nums=new Stack();
        oprs.push('#');
        while(i<input.length()&&!oprs.empty())
        {
            j=i;
            while((input.charAt(j)>='0'&&input.charAt(j)<='9')||input.charAt(j)=='.')
                j++;
            if(i!=j)
            {
                num1=Double.valueOf(input.substring(i,j));
                nums.push(num1);
                i=j;
            }
            if(PRI.getIsp(((Character)oprs.peek()).charValue())<PRI.getIcp(input.charAt(i)))
                oprs.push(input.charAt(i));
            else
            {
                while(PRI.getIsp(((Character)oprs.peek()).charValue())>PRI.getIcp(input.charAt(i)))
                {
                    num1=((Double)nums.pop()).doubleValue();
                    num2=((Double)nums.pop()).doubleValue();         
                    switch(((Character)oprs.pop()).charValue())
                    {
                        case '+':result=num1+num2;
                                 break;
                        case '-':result=num2-num1;
                                 break;
                        case '*':result=num1*num2;                                                          
                                 break;
                        case '/':result=num2/num1;
                                 break;
                        default:System.out.println("Error!");
                    }
                    nums.push(result);
                }
                    oprs.push(input.charAt(i));
            }
            i++;
        }
        return new Double(result).toString();
    }
};