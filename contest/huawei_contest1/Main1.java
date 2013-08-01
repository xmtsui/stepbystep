package com.huawei.contest;

import java.util.Scanner;
public class Main1 {
	public static final long MAX_MON=20000;
	public static final int MAX_PH=50;
	public static long[] getInput(){
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		if(num > MAX_PH)
			num = MAX_PH;
		long[] ph = new long[num];
		for(int i=0; i<num; ++i)
		{
			ph[i] = sc.nextLong();
		}
		return ph;
	}
	
	public static long[] insertSort(long[] elements) {
		for (int i = 1; i < elements.length; i++) {
			int j = -1;
			while (j < i && elements[i] > elements[++j])
				;
			if (j < i) {
				long temp = elements[i];
				for (int k = i - 1; k >= j; k--) {
					elements[k + 1] = elements[k];
				}
				elements[j] = temp;
			}
		}
		return elements;
	}
	
	public static void main(String[] args)
	{
		long[] a = insertSort(getInput());
		int len = a.length;
		long total=0;
		int buy=0;
		long type=0;
		type = a[len-1];
		int flag=0;
		
		if((total + a[len-1]) > MAX_MON)
			System.out.println(0);

		for(int i=len-1; i>=0; --i)
		{
			if(type == a[i])
			{
				flag++;
				if(flag>2)
				{
					continue;
				}
			}
			else
			{
				flag=0;
				type = a[i];
			}
			
			if((total+a[i]) <= MAX_MON)
				{
				total+=a[i];
//				System.out.println(total);
				buy++;
				}
			
			if((MAX_MON - total) < a[i-1])
			{
				break;
			}
		}
		System.out.println(buy);
	}
	
}
