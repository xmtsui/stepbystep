/**
 * 一个排好序的数组，找出两数之和为m的所有组合
 */
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
public class Test1{
	public static void main(String[] args)
	{
		int[] a = {1,2,4,3,5,5,6,7,8,9};
		Map<Integer, Integer> pair = doFind(a, 10);
		if(!pair.isEmpty())
		{
			Set<Map.Entry<Integer,Integer>> setpair = pair.entrySet();
			Iterator<Map.Entry<Integer,Integer>> itr = setpair.iterator();
			while(itr.hasNext())
			{
				Map.Entry<Integer,Integer> entry = itr.next();
				System.out.print(entry.getKey());
				System.out.print(entry.getValue());
				System.out.print("\n");
			}
		}

	}

	public static Map<Integer, Integer> doFind(int[] a, int b)
	{
		if(a==null)
			return null;
		int len = a.length;
		Map<Integer, Integer> pair = new HashMap<Integer, Integer>();
		for(int i=0; i<len; ++i)
		{
			int target = b - a[i];
			if(doSearch(a, i, target))
				pair.put(a[i], target);
		}
		return pair;
	}

	public static boolean doSearch(int[] a, int i, int b)
	{
		if(a==null)
			return false;
		int len = a.length;
		int s = 0;
		int e = len-1;
		while(s<e)
		{
			int mid = (s+e)/2;
			if(b>a[mid])
				s = mid+1;
			else if(b<a[mid])
				e = mid;
			else
			{
				if(mid != i)
					return true;
				else
					return false;
			}
		}
		return false;
	}
}