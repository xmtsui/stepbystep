import java.util.*;

/**
 * This program demonstrates the use of a priority queue.
 * @version 1.00 2004-08-03
 * @author Cay Horstmann
 */
public class TestPriorityQueue
{
   public static void main(String[] args)
   {
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
      pq.add(new Integer(10)); 
      pq.add(new Integer(201)); 
      pq.add(new Integer(38)); 
      pq.add(new Integer(49)); 

      System.out.println("Iterating over elements...");
      for (Integer item : pq)
         System.out.println(item);
      System.out.println("Removing elements...");
      while (!pq.isEmpty())
         System.out.println(pq.remove());
   }
}
