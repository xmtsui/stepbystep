package com.huawei.contest;

import java.util.Scanner;

public class Main2 {
	public static final int MAX=30;
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		if(num > MAX)
		{
			num=MAX;
			throw new Exception();
		}
		
		int[] a = new int[num];
		for(int i=0; i<num; i++)
		{
			a[i] = sc.nextInt();
		}
		
//		for(int i=0; i<num; i++)
//		{
//			System.out.print(a[i]+" ");
//		}
//		
//		System.out.print("\n");
		for(int i=0; i<num-1; i++)
		{
			if((a[i] == a[i+1]) && a[i] != 512)
			{
				a[i+1] += a[i];
				a[i] = 0;
			}
		}
		
		String t = "";
		for(int i=0; i<num; i++)
		{
//			System.out.print(a[i]+" ");
//			System.out.print(new String("" + a[i]));
			if(a[i] != 0)
			{
				if(i<num-1)
					t+=a[i]+" ";
				else
					t+=a[i];
			}
		}
//		System.out.print("\n");
			System.out.print(t);
//			System.out.print(t);
	}
}
