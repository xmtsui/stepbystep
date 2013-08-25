class Person{
private int a;
public int change(int m){
   return m;
}
}

public class Teacher extends Person {
public int b;
public static void main(String arg[]){
   Person p=new Person();
   Teacher t=new Teacher();
   int i;
   //point x  
   // i=m; 
   // i=t.b;
   // i=p.a; 
   i=p.change(30);
} 
}